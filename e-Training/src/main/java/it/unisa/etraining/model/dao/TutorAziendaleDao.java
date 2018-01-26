package it.unisa.etraining.model.dao;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Questa classe fornisce i metodi per gestire l'entità TutorAziendale in maniera persistente.
 *
 * @author Esposito Flavio
 */
public class TutorAziendaleDao {
  /**
   * Questo metodo permette di salvare un oggetto TutorAziendale sul database.
   * @param unTutorAziendale l'oggetto da salvare sul database.
   * @return true se il tutor aziendale viene correttamente salvato, false altrimenti.
   */
  public static synchronized boolean salvaTutorAziendale(TutorAziendale
      unTutorAziendale) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(SALVA_TUTOR_AZIENDALE);
      ps.setString(1,unTutorAziendale.getEmail());
      ps.setString(2,unTutorAziendale.getAzienda().getEmail());
      ps.setString(3,unTutorAziendale.getPassword());
      ps.setString(4,unTutorAziendale.getNome());
      ps.setString(5,unTutorAziendale.getCognome());
      ps.setString(6,unTutorAziendale.getCodiceFiscale());
      ps.setString(7,unTutorAziendale.getSettoreLavoro());
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorAziendaleDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorAziendaleDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di verificare le credenziali di TutorAziendale sul database.
   * @param unTutorAziendale l'oggetto di cui verificare le credenziali.
   * @return true se le credenziali vengono correttamente verificate, false altrimenti.
   */
  public static synchronized boolean loginTutorAziendale(TutorAziendale unTutorAziendale) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(LOGIN_TUTOR_AZIENDALE);
      ps.setString(1,unTutorAziendale.getEmail());
      ps.setString(2,unTutorAziendale.getPassword());
      
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        unTutorAziendale.setNome(rs.getString("Nome"));
        unTutorAziendale.setCognome(rs.getString("Cognome"));
        unTutorAziendale.setCodiceFiscale(rs.getString("CodiceFiscale"));
        unTutorAziendale.setSettoreLavoro(rs.getString("SettoreLavoro"));
      
        Azienda azienda = new Azienda();
        azienda.setEmail(rs.getString("Azienda"));
        AziendaDao.ricercaAzienda(azienda);
        
        unTutorAziendale.setAzienda(azienda);
      }
        
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorAziendaleDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorAziendaleDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare un oggetto TutorAziendale sul database.
   * @param unTutorAziendale l'oggetto da salvare sul database.
   * @return true se il tutor aziendale viene correttamente ricercato, false altrimenti.
   */
  public static synchronized boolean ricercaTutorAziendale(TutorAziendale 
      unTutorAziendale) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_TUTOR_AZIENDALE);
      ps.setString(1,unTutorAziendale.getEmail());
            
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        unTutorAziendale.setPassword(rs.getString("Password"));
        unTutorAziendale.setNome(rs.getString("Nome"));
        unTutorAziendale.setCognome(rs.getString("Cognome"));
        unTutorAziendale.setCodiceFiscale(rs.getString("CodiceFiscale"));
        unTutorAziendale.setSettoreLavoro(rs.getString("SettoreLavoro"));
      
        Azienda azienda = new Azienda();
        azienda.setEmail(rs.getString("Azienda"));
        AziendaDao.ricercaAzienda(azienda);
        
        unTutorAziendale.setAzienda(azienda);
      } 
        
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorAziendaleDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorAziendaleDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare tutti gli oggetti TutorAziendale di una data
   * Azienda sul database.
   * @param tuttiTutorAziendali la lista di tutor aziendali da riempire.
   * @param azienda l'azienda di cui cercare i tutor aziendali.
   * @return true se i tutor aziendali vengono correttamente ricercati, false altrimenti.
   */
  public static synchronized boolean ricercaTuttiTutorAziendali(ArrayList<TutorAziendale> 
      tuttiTutorAziendali, Azienda azienda) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_TUTOR_AZIENDALI_AZIENDA);
      ps.setString(1,azienda.getEmail());
      
      ResultSet rs = ps.executeQuery();
      
      AziendaDao.ricercaAzienda(azienda);
      
      while (rs.next()) {
        TutorAziendale unTutorAziendale = new TutorAziendale();
        
        unTutorAziendale.setEmail(rs.getString("Email"));
        unTutorAziendale.setAzienda(azienda);
        unTutorAziendale.setPassword(rs.getString("Password"));
        unTutorAziendale.setNome(rs.getString("Nome"));
        unTutorAziendale.setCognome(rs.getString("Cognome"));
        unTutorAziendale.setCodiceFiscale(rs.getString("CodiceFiscale"));
        unTutorAziendale.setSettoreLavoro(rs.getString("SettoreLavoro"));
        
        tuttiTutorAziendali.add(unTutorAziendale);
      }
            
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorAziendaleDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorAziendaleDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
    
  /**
   * Questo metodo permette di eliminare un oggetto TutorAziendale sul database.
   * @param unTutorAziendale l'oggetto da rimuovere dal database.
   * @return true se il tutor aziendale viene correttamente eliminato, false altrimenti.
   */
  public static synchronized boolean rimuoviTutorAziendale(TutorAziendale 
      unTutorAziendale) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_TUTOR_AZIENDALE);
      ps.setString(1,unTutorAziendale.getEmail());
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TutorAziendaleDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TutorAziendaleDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  private static final String NOME_TABELLA = " eTraining.TutorAziendale ";
  
  private static final String SALVA_TUTOR_AZIENDALE = "INSERT INTO" + NOME_TABELLA
                                                    + "VALUES(?,?,?,?,?,?,?)";

  private static final String RICERCA_TUTOR_AZIENDALE = "SELECT * "
                                                      + "FROM" + NOME_TABELLA
                                                      + "WHERE Email = ?";
  
  private static final String LOGIN_TUTOR_AZIENDALE = "SELECT * "
                                                      + "FROM" + NOME_TABELLA
                                                      + "WHERE Email = ? AND Password = ?";

  private static final String RICERCA_TUTOR_AZIENDALI_AZIENDA = "SELECT * "
                                                              + "FROM" + NOME_TABELLA
                                                              + "WHERE Azienda = ?";

  private static final String RIMUOVI_TUTOR_AZIENDALE = "DELETE FROM" + NOME_TABELLA
                                                      + "WHERE Email = ?";

}
