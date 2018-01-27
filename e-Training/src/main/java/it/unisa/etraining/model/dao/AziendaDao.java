package it.unisa.etraining.model.dao;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Questa classe fornisce i metodi per gestire l'entità Azienda in maniera persistente.
 * @author Basso Emanuele
 *
 */
public class AziendaDao {

  /**
   * Questo metodo permette di salvare un oggetto Azienda sul database.
   * @param unAzienda l'oggetto da salvare sul database.
   * @return true se l'azienda viene correttamente salvata, false altrimenti.
   */
  public static synchronized boolean salvaAzienda(Azienda unAzienda) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(SALVA_AZIENDA);
      ps.setString(1,unAzienda.getEmail());
      ps.setString(2,unAzienda.getPassword());
      ps.setString(3,unAzienda.getNome());
      ps.setString(4,unAzienda.getPartitaIva());
      ps.setString(5,unAzienda.getSede());
      ps.setString(6,unAzienda.getCitta());
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("AziendaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("AziendaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di verificare le credenziali di Azienda sul database.
   * @param unAzienda l'oggetto di cui verificare le credenziali.
   * @return true se le credenziali vengono correttamente verificate, false altrimenti.
   */
  public static synchronized boolean loginAzienda(Azienda unAzienda) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(LOGIN_AZIENDA);
      ps.setString(1,unAzienda.getEmail());
      ps.setString(2,unAzienda.getPassword());
      
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        unAzienda.setNome(rs.getString("Nome"));
        unAzienda.setPartitaIva(rs.getString("PartitaIva"));
        unAzienda.setSede(rs.getString("Sede"));
        unAzienda.setCitta(rs.getString("Citta"));
      }
        
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("AziendaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("AziendaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare un oggetto Azienda sul database.
   * @param unAzienda l'oggetto da ricercare sul database.
   * @return true se l'azienda viene correttamente ricercata, false altrimenti.
   */
  public static synchronized boolean ricercaAzienda(Azienda unAzienda) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_AZIENDA);
      ps.setString(1,unAzienda.getEmail());
      
      ResultSet rs = ps.executeQuery();
      
      rs.next();
      unAzienda.setPassword(rs.getString("Password"));
      unAzienda.setNome(rs.getString("Nome"));
      unAzienda.setPartitaIva(rs.getString("PartitaIva"));
      unAzienda.setSede(rs.getString("Sede"));
      unAzienda.setCitta(rs.getString("Citta"));
        
      rs.close();    
      ps.close();
      
    } catch (SQLException e) {
      Logger.getLogger("AziendaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("AziendaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare tutti gli oggetti Azienda sul database.
   * @param tutteAziende la lista di aziende da riempire.
   * @return true se le aziende vengono correttamente ricercate, false altrimenti.
   */
  public static synchronized boolean ricercaTutteAzienda(ArrayList<Azienda> tutteAziende) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_TUTTE_AZIENDE);
      
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        Azienda unAzienda = new Azienda();
        unAzienda.setEmail(rs.getString("Email"));
        unAzienda.setPassword(rs.getString("Password"));
        unAzienda.setNome(rs.getString("Nome"));
        unAzienda.setPartitaIva(rs.getString("PartitaIva"));
        unAzienda.setSede(rs.getString("Sede"));
        unAzienda.setCitta(rs.getString("Citta"));
        
        
        tutteAziende.add(unAzienda);
      }
            
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("AziendaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("AziendaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di eliminare un oggetto Azienda sul database.
   * @param unAzienda l'oggetto da rimuovere dal database.
   * @return true se l'azienda viene correttamente eliminata, false altrimenti.
   */
  public static synchronized boolean rimuoviAzienda(Azienda unAzienda) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_AZIENDA);
      ps.setString(1,unAzienda.getEmail());
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("AziendaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("AziendaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  private static final String NOME_TABELLA = " eTraining.Azienda ";
  
  private static final String SALVA_AZIENDA = "INSERT INTO" + NOME_TABELLA
                                            + "VALUES(?,?,?,?,?,?)";
  
  private static final String RICERCA_AZIENDA = "SELECT * "
                                              + "FROM" + NOME_TABELLA
                                              + "WHERE Email = ?";
  
  private static final String LOGIN_AZIENDA = "SELECT * "
                                              + "FROM" + NOME_TABELLA
                                              + "WHERE Email = ? AND Password = ?";
  
  private static final String RICERCA_TUTTE_AZIENDE = "SELECT * "
                                                    + "FROM" + NOME_TABELLA;
      
  private static final String RIMUOVI_AZIENDA = "DELETE FROM" + NOME_TABELLA
                                              + "WHERE Email = ?";
  
}
