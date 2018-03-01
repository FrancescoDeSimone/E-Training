package it.unisa.etraining.model.dao;

import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Questa classe fornisce i metodi per gestire l'entità Tirocinante in maniera persistente.
 * @author Basso Emanuele
 *
 */
public class TirocinanteDao {
  
  /**
   * Questo metodo permette di salvare un oggetto Tirocinante sul database.
   * @param unTirocinante l'oggetto da salvare sul database.
   * @return true se il tirocinante viene correttamente salvato, false altrimenti.
   */
  public static synchronized boolean salvaTirocinante(Tirocinante unTirocinante) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(SALVA_TIROCINANTE);
      ps.setString(1,unTirocinante.getEmail());
      ps.setString(2,unTirocinante.getPassword());
      ps.setString(3,unTirocinante.getNome());
      ps.setString(4,unTirocinante.getCognome());
      ps.setString(5,unTirocinante.getMatricola());
      ps.setString(6,unTirocinante.getAnnoIscrizione());
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinanteDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinanteDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di verificare le credenziali di Tirocinante sul database.
   * @param unTirocinante l'oggetto di cui verificare le credenziali.
   * @return true se le credenziali vengono correttamente verificate, false altrimenti.
   */
  public static synchronized boolean loginTirocinante(Tirocinante unTirocinante) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(LOGIN_TIROCINANTE);
      ps.setString(1,unTirocinante.getEmail());
      ps.setString(2,unTirocinante.getPassword());
      
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        unTirocinante.setNome(rs.getString("Nome"));
        unTirocinante.setCognome(rs.getString("Cognome"));
        unTirocinante.setMatricola(rs.getString("Matricola"));
        unTirocinante.setAnnoIscrizione(rs.getString("AnnoIscrizione"));
      }
        
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinanteDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinanteDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare un oggetto Tirocinante sul database.
   * @param unTirocinante l'oggetto da salvare sul database.
   * @return true se il tirocinante viene correttamente ricercato, false altrimenti.
   */
  public static synchronized boolean ricercaTirocinante(Tirocinante unTirocinante) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_TIROCINANTE);
      ps.setString(1,unTirocinante.getEmail());
      
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        unTirocinante.setPassword(rs.getString("Password"));
        unTirocinante.setNome(rs.getString("Nome"));
        unTirocinante.setCognome(rs.getString("Cognome"));
        unTirocinante.setMatricola(rs.getString("Matricola"));
        unTirocinante.setAnnoIscrizione(rs.getString("AnnoIscrizione"));
      }
      
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinanteDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinanteDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di eliminare un oggetto Tirocinante sul database.
   * @param unTirocinante l'oggetto da rimuovere dal database.
   * @return true se il tirocinante viene correttamente eliminato, false altrimenti.
   */
  public static synchronized boolean rimuoviTirocinante(Tirocinante unTirocinante) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_TIROCINANTE);
      ps.setString(1,unTirocinante.getEmail());
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TirocinanteDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TirocinanteDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  private static final String NOME_TABELLA = " eTraining.Tirocinante ";
  
  private static final String SALVA_TIROCINANTE = "INSERT INTO" + NOME_TABELLA
                                                + "VALUES(?,?,?,?,?,?)";
  
  private static final String RICERCA_TIROCINANTE = "SELECT * "
                                                  + "FROM" + NOME_TABELLA 
                                                  + "WHERE Email = ? ";
  
  private static final String LOGIN_TIROCINANTE = "SELECT * "
                                                  + "FROM" + NOME_TABELLA 
                                                  + "WHERE Email = ? AND Password = ?";
  
  private static final String RIMUOVI_TIROCINANTE = "DELETE FROM" + NOME_TABELLA
                                                  + "WHERE Email = ?";
}
