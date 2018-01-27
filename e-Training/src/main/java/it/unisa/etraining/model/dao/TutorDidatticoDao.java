package it.unisa.etraining.model.dao;

import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Questa classe fornisce i metodi per gestire l'entità TutorDidattico in maniera persistente.
 * @author Basso Emanuele
 *
 */
public class TutorDidatticoDao {
  
  /**
   * Questo metodo permette di salvare un oggetto TutorDidattico sul database.
   * @param unTutorDidattico l'oggetto da salvare sul database.
   * @return true se il tutor didattico viene correttamente salvato, false altrimenti.
   */
  public static synchronized boolean salvaTutorDidattico(TutorDidattico 
      unTutorDidattico) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(SALVA_TUTOR_DIDATTICO);
      ps.setString(1,unTutorDidattico.getEmail());
      ps.setString(2,unTutorDidattico.getPassword());
      ps.setString(3,unTutorDidattico.getNome());
      ps.setString(4,unTutorDidattico.getCognome());
      ps.setString(5,unTutorDidattico.getCodiceFiscale());
      ps.setBoolean(6,unTutorDidattico.isDisponibilita());
      ps.executeUpdate();
      
      notError = inserisciCampiInteresse(unTutorDidattico);
      notError = inserisciInsegnamenti(unTutorDidattico);
           
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di verificare le credenziali di TutorDidattico sul database.
   * @param unTutorDidattico l'oggetto di cui verificare le credenziali.
   * @return true se le credenziali vengono correttamente verificate, false altrimenti.
   */
  public static synchronized boolean loginTutorDidattico(TutorDidattico unTutorDidattico) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(LOGIN_TUTOR_DIDATTICO);
      ps.setString(1,unTutorDidattico.getEmail());
      ps.setString(2,unTutorDidattico.getPassword());
      
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        unTutorDidattico.setNome(rs.getString("Nome"));
        unTutorDidattico.setCognome(rs.getString("Cognome"));
        unTutorDidattico.setCodiceFiscale(rs.getString("CodiceFiscale"));
        unTutorDidattico.setDisponibilita(rs.getBoolean("Disponibilita"));
      }
        
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare un oggetto TutorDidattico sul database.
   * @param unTutorDidattico l'oggetto da ricercare sul database.
   * @return true se il tutor didattico viene correttamente ricercato, false altrimenti.
   */
  public static synchronized boolean ricercaTutorDidattico(TutorDidattico 
      unTutorDidattico) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_TUTOR_DIDATTICO);
      ps.setString(1,unTutorDidattico.getEmail());
      
      ResultSet rs = ps.executeQuery();
      
      rs.next();
      unTutorDidattico.setPassword(rs.getString("Password"));
      unTutorDidattico.setNome(rs.getString("Nome"));
      unTutorDidattico.setCognome(rs.getString("Cognome"));
      unTutorDidattico.setCodiceFiscale(rs.getString("CodiceFiscale"));
      unTutorDidattico.setDisponibilita(rs.getBoolean("Disponibilita"));
                  
      rs.close();    
      ps.close();
      
      notError = ricercaInsegnamenti(unTutorDidattico);
      notError = ricercaCampiInteresse(unTutorDidattico);
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare tutti gli oggetti TutorDidattico sul database.
   * @param tuttiTutorDidattici la lista di tutor didattici da riempire.
   * @return true se i tutor didattici vengono correttamente ricercati, false altrimenti.
   */
  public static synchronized boolean ricercaTuttiTutorDidattici(ArrayList<TutorDidattico> 
      tuttiTutorDidattici) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_TUTTI_TUTOR_DIDATTICI);
      
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        TutorDidattico unTutorDidattico = new TutorDidattico();
        unTutorDidattico.setEmail(rs.getString("Email"));
        unTutorDidattico.setPassword(rs.getString("Password"));
        unTutorDidattico.setNome(rs.getString("Nome"));
        unTutorDidattico.setCognome(rs.getString("Cognome"));
        unTutorDidattico.setCodiceFiscale(rs.getString("CodiceFiscale"));
        unTutorDidattico.setDisponibilita(rs.getBoolean("Disponibilita"));
        
        notError = ricercaInsegnamenti(unTutorDidattico);
        notError = ricercaCampiInteresse(unTutorDidattico);
        
        tuttiTutorDidattici.add(unTutorDidattico);
      }
            
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di aggiornare la disponibilita di un oggetto 
   * TutorDidattico sul database.
   * @param unTutorDidattico l'oggetto di cui aggiornare la disponibilita sul database.
   * @return true se la disponibilita viene correttamente aggiornata, false altrimenti.
   */
  public static synchronized boolean aggiornaDisponibilita(TutorDidattico 
      unTutorDidattico) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
            
      PreparedStatement ps = con.prepareStatement(AGGIORNA_DISPONIBILITA_TUTOR_DIDATTICO);
      ps.setBoolean(1,unTutorDidattico.isDisponibilita());
      ps.setString(2,unTutorDidattico.getEmail());
           
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di eliminare un oggetto TutorDidattico sul database.
   * @param unTutorDidattico l'oggetto da rimuovere dal database.
   * @return true se il tutor didattico viene correttamente eliminato, false altrimenti.
   */
  public static synchronized boolean rimuoviTutorDidattico(TutorDidattico 
      unTutorDidattico) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_TUTOR_DIDATTICO);
      ps.setString(1,unTutorDidattico.getEmail());
      
      ps.executeUpdate();
      
      ps.close();

      notError = rimuoviInsegnamenti(unTutorDidattico);
      notError = rimuoviCampiInteresse(unTutorDidattico);
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di salvare dei campi di interesse del TutorDidattico sul database.
   * @param unTutorDidattico l'oggetto di cui salvare i campi di interesse sul database.
   * @return true se i campi di interesse vengono correttamente salvati, false altrimenti.
   */
  private static synchronized boolean inserisciCampiInteresse(TutorDidattico 
      unTutorDidattico) { 
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      String[] campiInteresse = unTutorDidattico.getCampiInteresse();
 
      for (String campoInteresse : campiInteresse)  {
        PreparedStatement ps = con.prepareStatement(INSERISCI_CAMPO_INTERESSE);
        ps.setString(1,unTutorDidattico.getEmail());
        ps.setString(2,campoInteresse);
        ps.executeUpdate();
 
        ps.close();
      }
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di salvare gli insegnamenti del TutorDidattico sul database.
   * @param unTutorDidattico l'oggetto di cui salvare gli insegnamenti sul database.
   * @return true se gli insegnamenti vengono correttamente salvati, false altrimenti.
   */
  private static synchronized boolean inserisciInsegnamenti(TutorDidattico 
      unTutorDidattico) { 
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      String[] insegnamenti = unTutorDidattico.getInsegnamenti();
 
      for (String insegnamento : insegnamenti)  {
        PreparedStatement ps = con.prepareStatement(INSERISCI_INSEGNAMENTO);
        ps.setString(1,unTutorDidattico.getEmail());
        ps.setString(2,insegnamento);
        ps.executeUpdate();
 
        ps.close();
      }
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare degli insegnamenti del TutorDidattico sul database.
   * @param unTutorDidattico l'oggetto di cui ricercare gli insegnamenti sul database.
   * @return true se gli insegnamenti vengono correttamente ricercati, false altrimenti.
   */
  private static synchronized boolean ricercaInsegnamenti(TutorDidattico 
      unTutorDidattico) { 
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_INSEGNAMENTI);
      ps.setString(1,unTutorDidattico.getEmail());
      
      ResultSet rs = ps.executeQuery();
      
      ArrayList<String> insegnamentiArrayList = new ArrayList<>();      
      while (rs.next()) {
        String insegnamento = rs.getString("Descrizione");
        insegnamentiArrayList.add(insegnamento);
      }
      
      String[] insegnamenti = new String[insegnamentiArrayList.size()];
      for (int i = 0; i < insegnamenti.length; i++) {
        insegnamenti[i] = insegnamentiArrayList.get(i);
      }
      
      unTutorDidattico.setInsegnamenti(insegnamenti);
            
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare i campi di interesse del TutorDidattico sul database.
   * @param unTutorDidattico l'oggetto di cui ricercare i campi di interesse sul database.
   * @return true se i campi di interesse vengono correttamente ricercati, false altrimenti.
   */
  private static synchronized boolean ricercaCampiInteresse(TutorDidattico 
      unTutorDidattico) { 
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_CAMPI_INTERESSE);
      ps.setString(1,unTutorDidattico.getEmail());
      
      ResultSet rs = ps.executeQuery();
      
      ArrayList<String> interessiArrayList = new ArrayList<>();      
      while (rs.next()) {
        String interesse = rs.getString("Descrizione");
        interessiArrayList.add(interesse);
      }
      
      String[] interessi = new String[interessiArrayList.size()];
      for (int i = 0; i < interessi.length; i++) {
        interessi[i] = interessiArrayList.get(i);
      }
      
      unTutorDidattico.setCampiInteresse(interessi);
            
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di rimuovere gli insegnamenti del TutorDidattico sul database.
   * @param unTutorDidattico l'oggetto di cui rimuovere gli insegnamenti sul database.
   * @return true se gli insegnamenti vengono correttamente rimossi, false altrimenti.
   */
  private static synchronized boolean rimuoviInsegnamenti(TutorDidattico 
      unTutorDidattico) { 
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_INSEGNAMENTI);
      ps.setString(1,unTutorDidattico.getEmail());
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di rimuovere i campi di interesse del TutorDidattico sul database.
   * @param unTutorDidattico l'oggetto di cui rimuovere i campi di interesse sul database.
   * @return true se i campi di interesse vengono correttamente rimossi, false altrimenti.
   */
  private static synchronized boolean rimuoviCampiInteresse(TutorDidattico 
      unTutorDidattico) { 
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_CAMPI_INTERESSE);
      ps.setString(1,unTutorDidattico.getEmail());
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorDidatticoDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  private static final String NOME_TABELLA_TUTOR_DIDATTICO = " eTraining.TutorDidattico ";
  private static final String NOME_TABELLA_CAMPO_INTERESSE = " eTraining.CampoInteresse ";
  private static final String NOME_TABELLA_INSEGNAMENTO = " eTraining.Insegnamento ";
  
  private static final String SALVA_TUTOR_DIDATTICO = "INSERT INTO" + NOME_TABELLA_TUTOR_DIDATTICO
                                                    + "VALUES(?,?,?,?,?,?)";
  
  private static final String RICERCA_TUTOR_DIDATTICO = "SELECT * "
                                                      + "FROM" + NOME_TABELLA_TUTOR_DIDATTICO
                                                      + "WHERE Email = ?";
  
  private static final String LOGIN_TUTOR_DIDATTICO = "SELECT * "
                                                    + "FROM" + NOME_TABELLA_TUTOR_DIDATTICO
                                                    + "WHERE Email = ? AND Password = ?";

  private static final String RICERCA_TUTTI_TUTOR_DIDATTICI = "SELECT * "
                                                          + "FROM" + NOME_TABELLA_TUTOR_DIDATTICO;

  private static final String AGGIORNA_DISPONIBILITA_TUTOR_DIDATTICO = "UPDATE" 
                                                                     + NOME_TABELLA_TUTOR_DIDATTICO
                                                                     + "SET Disponibilita = ? "
                                                                     + "WHERE Email = ?";
  
  private static final String RIMUOVI_TUTOR_DIDATTICO = "DELETE FROM" + NOME_TABELLA_TUTOR_DIDATTICO
                                                      + "WHERE Email = ?";
  
  private static final String INSERISCI_CAMPO_INTERESSE = "INSERT INTO" 
                                                        + NOME_TABELLA_CAMPO_INTERESSE
                                                        + "VALUES(?,?)";
  private static final String INSERISCI_INSEGNAMENTO = "INSERT INTO" + NOME_TABELLA_INSEGNAMENTO
                                                     + "VALUES(?,?)";
  
  private static final String RICERCA_CAMPI_INTERESSE = "SELECT * "
                                                      + "FROM" + NOME_TABELLA_CAMPO_INTERESSE
                                                      + "WHERE TutorDidattico = ?";
  
  private static final String RICERCA_INSEGNAMENTI = "SELECT * "
                                                   + "FROM" + NOME_TABELLA_INSEGNAMENTO
                                                   + "WHERE TutorDidattico = ?";
  
  private static final String RIMUOVI_CAMPI_INTERESSE = "DELETE FROM" + NOME_TABELLA_CAMPO_INTERESSE
                                                      + "WHERE TutorDidattico = ?";
    
  private static final String RIMUOVI_INSEGNAMENTI = "DELETE FROM" + NOME_TABELLA_INSEGNAMENTO
                                                   + "WHERE TutorDidattico = ?";
}
