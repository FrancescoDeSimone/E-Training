package it.unisa.etraining.model.dao;

import it.unisa.etraining.model.bean.TitolareAzienda;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Questa classe fornisce i metodi per gestire l'entità TitolareAzienda in maniera persistente.
 *
 * @author Esposito Flavio
 */
public class TitolareAziendaDao {

  /**
   * Questo metodo permette di salvare un oggetto TitolareAzienda sul database.
   * @param unTitolareAzienda l'oggetto da salvare sul database.
   * @return true se il titolare viene correttamente salvato, false altrimenti.
   */
  public static synchronized boolean salvaTitolareAzienda(TitolareAzienda unTitolareAzienda) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(SALVA_TITOLARE);
      ps.setString(1,unTitolareAzienda.getAzienda().getEmail());
      ps.setString(2,unTitolareAzienda.getNome());
      ps.setString(3,unTitolareAzienda.getCognome());
      ps.setString(4,unTitolareAzienda.getCodiceFiscale());
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TitolareAziendaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TitolareAziendaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare un oggetto TitolareAzienda sul database.
   * @param unTitolareAzienda l'oggetto da salvare sul database.
   * @return true se il titolare viene correttamente ricercato, false altrimenti.
   */
  public static synchronized boolean ricercaTitolare(TitolareAzienda unTitolareAzienda) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_TITOLARE);
      ps.setString(1,unTitolareAzienda.getAzienda().getEmail());
      
      ResultSet rs = ps.executeQuery();
      
      rs.next();
      unTitolareAzienda.setNome(rs.getString("Nome"));
      unTitolareAzienda.setCognome(rs.getString("Cognome"));
      unTitolareAzienda.setCodiceFiscale(rs.getString("CodiceFiscale"));
            
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TitolareAziendaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TitolareAziendaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di eliminare un oggetto TitolareAzienda sul database.
   * @param unTitolareAzienda l'oggetto da rimuovere dal database.
   * @return true se il titolare viene correttamente eliminato, false altrimenti.
   */
  public static synchronized boolean rimuoviTitolare(TitolareAzienda unTitolareAzienda) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_TITOLARE);
      ps.setString(1,unTitolareAzienda.getAzienda().getEmail());
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("TitolareAziendaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("TitolareAziendaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  
  private static final String NOME_TABELLA = " eTraining.TitolareAzienda ";
  
  private static final String SALVA_TITOLARE = "INSERT INTO" + NOME_TABELLA
                                             + "VALUES(?,?,?,?)";
  
  private static final String RICERCA_TITOLARE = "SELECT * "
                                               + "FROM" + NOME_TABELLA 
                                               + "WHERE Azienda = ?";
  
  private static final String RIMUOVI_TITOLARE = "DELETE FROM" + NOME_TABELLA
                                               + "WHERE Azienda = ?";
}
