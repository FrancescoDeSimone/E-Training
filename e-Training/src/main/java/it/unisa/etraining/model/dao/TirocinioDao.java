package it.unisa.etraining.model.dao;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.bean.Utente;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Questa classe fornisce i metodi per gestire l'entità Tirocinio in maniera persistente.
 * @author Basso Emanuele
 *
 */
public class TirocinioDao {
  
  /**
   * Questo metodo permette di salvare un oggetto Tirocinio sul database.
   * @param unTirocinio l'oggetto da salvare sul database.
   * @return true se il tirocinio viene correttamente salvato, false altrimenti.
   */
  public static synchronized boolean salvaTirocinio(Tirocinio unTirocinio) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(SALVA_TIROCINIO);
      ps.setString(1,unTirocinio.getTirocinante().getEmail());
      ps.setInt(2,unTirocinio.getOfferta().getId());
      ps.setInt(3,unTirocinio.getCfu());
      ps.setInt(4,unTirocinio.getOreRimanenti());
      ps.setString(5,unTirocinio.getStatus());
      ps.executeUpdate();
      
      notError = inserisciObiettivi(unTirocinio);
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare un oggetto Tirocinio sul database.
   * @param unTirocinio l'oggetto da ricercare sul database.
   * @return true se il tirocinio viene correttamente ricercato, false altrimenti.
   */
  public static synchronized boolean ricercaTirocinio(Tirocinio unTirocinio) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_TIROCINIO);
      ps.setString(1,unTirocinio.getTirocinante().getEmail());
      ps.setInt(2,unTirocinio.getOfferta().getId());
           
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        unTirocinio.setCfu(rs.getInt("CFU"));
        unTirocinio.setOreRimanenti(rs.getInt("OreRimanenti"));
        unTirocinio.setStatus(rs.getString("Status"));
      }
                  
      rs.close();    
      ps.close();
      
      notError = ricercaObiettivi(unTirocinio);
    } catch (SQLException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare una lista di Tirocinio inviati da un Tirocinante 
   * sul database.
   * @param tirociniInviati la lista di tirocini inviati da ricercare.
   * @param tirocinante l'oggetto di cui ricercare i tirocini inviati.
   * @return true se i tirocini vengono correttamente ricercati, false altrimenti.
   */
  public static synchronized boolean ricercaTirociniInviati(ArrayList<Tirocinio> tirociniInviati, 
      Tirocinante tirocinante) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_TIROCINI_INVIATI);
      ps.setString(1,tirocinante.getEmail());
      ps.setString(2,Tirocinio.VALUTAZIONE_DOCENTE);
      ps.setString(3,Tirocinio.VALUTAZIONE_AZIENDA);
      
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        Tirocinio tirocinio = new Tirocinio();
        tirocinio.setTirocinante(tirocinante);
        OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
        offerta.setId(rs.getInt("IdOffertaFormativaTirocinioEsterno"));
        tirocinio.setOfferta(offerta);
        tirocinio.setCfu(rs.getInt("CFU"));
        tirocinio.setOreRimanenti(rs.getInt("OreRimanenti"));
        tirocinio.setStatus(rs.getString("Status"));
        
        notError = ricercaObiettivi(tirocinio);
        
        tirociniInviati.add(tirocinio);
      }
      
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare una lista di richieste di Tirocinio 
   * ricevute da un'Azienda o da un Tutor Didattico sul database.
   * @param tirociniRichieste la lista di richieste di tirocinio da ricercare.
   * @param utente l'oggetto di cui ricercare le richieste di tirocinio.
   * @return true se i tirocini vengono correttamente ricercati, false altrimenti.
   */
  public static synchronized boolean ricercaRichiesteTirocinio(
      ArrayList<Tirocinio> tirociniRichieste, Utente utente) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps;
      if (utente instanceof TutorDidattico) {
        ps = con.prepareStatement(RICERCA_RICHIESTE_TIROCINI_TUTOR_DIDATTICO);
        ps.setString(1,Tirocinio.VALUTAZIONE_DOCENTE);
      } else if (utente instanceof Azienda) {
        ps = con.prepareStatement(RICERCA_RICHIESTE_TIROCINI_AZIENDA);
        ps.setString(1,Tirocinio.VALUTAZIONE_AZIENDA);
      } else {
        throw new RuntimeException();
      }
      
      ps.setString(2,utente.getEmail());
      
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        Tirocinio tirocinio = new Tirocinio();
        Tirocinante tirocinante = new Tirocinante();
        tirocinante.setEmail(rs.getString("Tirocinante"));
        tirocinio.setTirocinante(tirocinante);
        OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
        offerta.setId(rs.getInt("IdOffertaFormativaTirocinioEsterno"));
        tirocinio.setOfferta(offerta);
        tirocinio.setCfu(rs.getInt("CFU"));
        tirocinio.setOreRimanenti(rs.getInt("OreRimanenti"));
        tirocinio.setStatus(rs.getString("Status"));
        
        notError = ricercaObiettivi(tirocinio);
        
        tirociniRichieste.add(tirocinio);
      }
      
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare una lista di Tirocinio attivi di un Utente sul database.
   * @param tirociniAttivi la lista di tirocini attivi da ricercare.
   * @param utente l'oggetto di cui ricercare i tirocini attivi.
   * @return true se i tirocini vengono correttamente ricercati, false altrimenti.
   */
  public static synchronized boolean ricercaTirociniAttivi(ArrayList<Tirocinio> tirociniAttivi, 
      Utente utente) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps;
      if (utente instanceof Tirocinante) {
        ps = con.prepareStatement(RICERCA_TIROCINI_TIROCINANTE);
      } else if (utente instanceof TutorDidattico) {
        ps = con.prepareStatement(RICERCA_TIROCINI_TUTOR_DIDATTICO);
      } else if (utente instanceof TutorAziendale) {
        ps = con.prepareStatement(RICERCA_TIROCINI_TUTOR_AZIENDALE);
      } else if (utente instanceof Azienda) {
        ps = con.prepareStatement(RICERCA_TIROCINI_AZIENDA);
      } else {
        throw new RuntimeException();
      }
            
      ps.setString(1,Tirocinio.IN_CORSO);
      ps.setString(2,utente.getEmail());
     
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        Tirocinio tirocinio = new Tirocinio();
        Tirocinante tirocinante = new Tirocinante();
        tirocinante.setEmail(rs.getString("Tirocinante"));
        tirocinio.setTirocinante(tirocinante);
        OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
        offerta.setId(rs.getInt("IdOffertaFormativaTirocinioEsterno"));
        tirocinio.setOfferta(offerta);
        tirocinio.setCfu(rs.getInt("CFU"));
        tirocinio.setOreRimanenti(rs.getInt("OreRimanenti"));
        tirocinio.setStatus(rs.getString("Status"));
        
        notError = ricercaObiettivi(tirocinio);
        
        tirociniAttivi.add(tirocinio);
      }
                  
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } catch (RuntimeException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare una lista di Tirocinio chiusi di un Utente sul database.
   * @param tirociniStorico la lista di tirocini chiusi da ricercare.
   * @param utente l'oggetto di cui ricercare i tirocini chius.
   * @return true se i tirocini vengono correttamente ricercati, false altrimenti.
   */
  public static synchronized boolean ricercaTirociniStorico(ArrayList<Tirocinio> tirociniStorico, 
      Utente utente) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps;
      if (utente instanceof Tirocinante) {
        ps = con.prepareStatement(RICERCA_TIROCINI_TIROCINANTE);
      } else if (utente instanceof TutorDidattico) {
        ps = con.prepareStatement(RICERCA_TIROCINI_TUTOR_DIDATTICO);
      } else if (utente instanceof TutorAziendale) {
        ps = con.prepareStatement(RICERCA_TIROCINI_TUTOR_AZIENDALE);
      } else if (utente instanceof Azienda) {
        ps = con.prepareStatement(RICERCA_TIROCINI_AZIENDA);
      } else {
        throw new RuntimeException();
      }
            
      ps.setString(1,Tirocinio.TERMINATO);
      ps.setString(2,utente.getEmail());
     
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        Tirocinio tirocinio = new Tirocinio();
        Tirocinante tirocinante = new Tirocinante();
        tirocinante.setEmail(rs.getString("Tirocinante"));
        tirocinio.setTirocinante(tirocinante);
        OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
        offerta.setId(rs.getInt("IdOffertaFormativaTirocinioEsterno"));
        tirocinio.setOfferta(offerta);
        tirocinio.setCfu(rs.getInt("CFU"));
        tirocinio.setOreRimanenti(rs.getInt("OreRimanenti"));
        tirocinio.setStatus(rs.getString("Status"));
        
        notError = ricercaObiettivi(tirocinio);
        
        tirociniStorico.add(tirocinio);
      }
                  
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } catch (RuntimeException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di aggiornare lo status di un oggetto Tirocinio sul database.
   * @param unTirocinio l'oggetto di cui aggiornare lo status sul database.
   * @return true se lo status viene correttamente aggiornato, false altrimenti.
   */
  public static synchronized boolean aggiornaStatusTirocinio(Tirocinio unTirocinio) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
            
      PreparedStatement ps = con.prepareStatement(AGGIORNA_STATUS);
      ps.setString(1,unTirocinio.getStatus());
      ps.setString(2,unTirocinio.getTirocinante().getEmail());
      ps.setInt(3,unTirocinio.getOfferta().getId());
           
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  } 
  
  /**
   * Questo metodo permette di aggiornare le ore di un oggetto Tirocinio sul database.
   * @param unTirocinio l'oggetto di cui aggiornare le ore sul database.
   * @return true se le ore vengono correttamente aggiornate, false altrimenti.
   */
  public static synchronized boolean aggiornOreTirocinio(Tirocinio unTirocinio) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
            
      PreparedStatement ps = con.prepareStatement(AGGIORNA_ORE);
      ps.setInt(1,unTirocinio.getOreRimanenti());
      ps.setString(2,unTirocinio.getTirocinante().getEmail());
      ps.setInt(3,unTirocinio.getOfferta().getId());
           
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  } 
  
  /**
   * Questo metodo permette di salvare gli obiettivi del Tirocinio sul database.
   * @param unTirocinio l'oggetto di cui salvare gli obiettivi sul database.
   * @return true se gli obiettivi vengono correttamente salvati, false altrimenti.
   */
  private static synchronized boolean inserisciObiettivi(Tirocinio unTirocinio) { 
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      String[] obiettivi = unTirocinio.getObiettivi();
 
      for (String obiettivo : obiettivi)  {
        PreparedStatement ps = con.prepareStatement(INSERISCI_OBIETTIVO);
        ps.setString(1,unTirocinio.getTirocinante().getEmail());
        ps.setInt(2,unTirocinio.getOfferta().getId());
        ps.setString(3,obiettivo);
        ps.executeUpdate();
 
        ps.close();
      }
    } catch (SQLException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare gli obiettiv del Tirocinio sul database.
   * @param unTirocinio l'oggetto di cui ricercare gli obiettivi sul database.
   * @return true se gli obiettivi vengono correttamente ricercati, false altrimenti.
   */
  private static synchronized boolean ricercaObiettivi(Tirocinio unTirocinio) { 
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_OBIETTIVI);
      ps.setString(1,unTirocinio.getTirocinante().getEmail());
      ps.setInt(2,unTirocinio.getOfferta().getId());
      
      ResultSet rs = ps.executeQuery();
      
      ArrayList<String> obiettiviArrayList = new ArrayList<>();      
      while (rs.next()) {
        String obiettivo = rs.getString("Descrizione");
        obiettiviArrayList.add(obiettivo);
      }
      
      String[] obiettivi = new String[obiettiviArrayList.size()];
      for (int i = 0; i < obiettivi.length; i++) {
        obiettivi[i] = obiettiviArrayList.get(i);
      }
      
      unTirocinio.setObiettivi(obiettivi);
            
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinioDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }

  private static final String NOME_TABELLA_TIROCINIO = " eTraining.Tirocinio ";
  private static final String NOME_TABELLA_OBIETTIVO = " eTraining.Obiettivo ";
  
  private static final String SALVA_TIROCINIO = "INSERT INTO" + NOME_TABELLA_TIROCINIO
                                              + "VALUES (?,?,?,?,?)";
  
  private static final String RICERCA_TIROCINIO = "SELECT * "
                                                + "FROM" + NOME_TABELLA_TIROCINIO
                                                + "WHERE Tirocinante = ? "
                                                + "AND IdOffertaFormativaTirocinioEsterno = ?";
  
  private static final String RICERCA_TIROCINI_INVIATI = "SELECT * "
                                                       + "FROM" + NOME_TABELLA_TIROCINIO
                                                       + "WHERE Tirocinante = ? "
                                                       + "AND (Status = ? OR Status = ?)";
  
  private static final String RICERCA_TIROCINI_TIROCINANTE = "SELECT * "
                                                           + "FROM" + NOME_TABELLA_TIROCINIO
                                                           + "WHERE Status = ? "
                                                           + "AND Tirocinante = ? ";
  
  private static final String RICERCA_RICHIESTE_TIROCINI_TUTOR_DIDATTICO = "SELECT * "
                                                        + "FROM" + NOME_TABELLA_TIROCINIO
                                                        + "WHERE Status = ? AND "
                                                        + "IdOffertaFormativaTirocinioEsterno "
                                                        + "IN (SELECT Id "
                                                        + "FROM "
                                                        + "OffertaFormativaTirocinioEsterno "
                                                        + "WHERE TutorDidattico = ?)";
  
  private static final String RICERCA_RICHIESTE_TIROCINI_AZIENDA = "SELECT * "
                                                        + "FROM" + NOME_TABELLA_TIROCINIO
                                                        + "WHERE Status = ? AND "
                                                        + "IdOffertaFormativaTirocinioEsterno "
                                                        + "IN (SELECT Id "
                                                        + "FROM "
                                                        + "OffertaFormativaTirocinioEsterno "
                                                        + "WHERE Azienda = ?)";
  
  private static final String RICERCA_TIROCINI_TUTOR_DIDATTICO = "SELECT * "
                                                            + "FROM" + NOME_TABELLA_TIROCINIO
                                                            + "WHERE Status = ? AND "
                                                            + "IdOffertaFormativaTirocinioEsterno "
                                                            + "IN (SELECT Id "
                                                            + "FROM "
                                                            + "OffertaFormativaTirocinioEsterno "
                                                            + "WHERE TutorDidattico = ?)";
  
  private static final String RICERCA_TIROCINI_TUTOR_AZIENDALE = "SELECT * "
                                                             + "FROM" + NOME_TABELLA_TIROCINIO
                                                             + "WHERE Status = ? AND "
                                                             + "IdOffertaFormativaTirocinioEsterno "
                                                             + "IN (SELECT Id "
                                                             + "FROM "
                                                             + "OffertaFormativaTirocinioEsterno "
                                                             + "WHERE TutorAziendale = ?)";
  
  private static final String RICERCA_TIROCINI_AZIENDA = "SELECT * "
                                                        + "FROM" + NOME_TABELLA_TIROCINIO
                                                        + "WHERE Status = ? AND "
                                                        + "IdOffertaFormativaTirocinioEsterno IN "
                                                        + "(SELECT Id "
                                                        + "FROM OffertaFormativaTirocinioEsterno "
                                                        + "WHERE Azienda = ?)";
  
  private static final String AGGIORNA_STATUS = "UPDATE" + NOME_TABELLA_TIROCINIO
                                              + "SET Status = ? "
                                              + "WHERE Tirocinante = ? "
                                              + "AND IdOffertaFormativaTirocinioEsterno = ?";
  
  private static final String AGGIORNA_ORE = "UPDATE" + NOME_TABELLA_TIROCINIO
                                              + "SET OreRimanenti= ? "
                                              + "WHERE Tirocinante = ? "
                                              + "AND IdOffertaFormativaTirocinioEsterno = ?";
  
  private static final String INSERISCI_OBIETTIVO = "INSERT INTO" + NOME_TABELLA_OBIETTIVO
                                                  + "VALUES (?,?,?)";
                                                  
  private static final String RICERCA_OBIETTIVI = "SELECT * "
                                                + "FROM" + NOME_TABELLA_OBIETTIVO
                                                + "WHERE Tirocinante = ? "
                                                + "AND IdOffertaFormativaTirocinioEsterno = ?";
  
}
