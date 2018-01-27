package it.unisa.etraining.model.dao;

import it.unisa.etraining.model.bean.FunzionarioSegreteria;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Questa classe fornisce i metodi per gestire l'entità FunzionarioSegreteria in maniera 
 * persistente.
 * @author Basso Emanuele
 *
 */
public class FunzionarioSegreteriaDao {
  
  /**
   * Questo metodo permette di salvare un oggetto FunzionarioSegreteria sul database.
   * @param unFunzionarioSegreteria l'oggetto da salvare sul database.
   * @return true se il funzionario viene correttamente salvato, false altrimenti.
   */
  public static synchronized boolean salvaFunzionario(FunzionarioSegreteria 
      unFunzionarioSegreteria) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(SALVA_FUNZIONARIO);
      ps.setString(1,unFunzionarioSegreteria.getEmail());
      ps.setString(2,unFunzionarioSegreteria.getPassword());
      ps.setString(3,unFunzionarioSegreteria.getNome());
      ps.setString(4,unFunzionarioSegreteria.getCognome());
      ps.setString(5,unFunzionarioSegreteria.getCodiceFiscale());
      ps.executeUpdate();
      
      notError = inserisciQualifiche(unFunzionarioSegreteria);
           
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di verificare le credenziali di FunzionarioSegreteria sul database.
   * @param unFunzionarioSegreteria l'oggetto di cui verificare le credenziali.
   * @return true se le credenziali vengono correttamente verificate, false altrimenti.
   */
  public static synchronized boolean loginFunzionario(FunzionarioSegreteria 
      unFunzionarioSegreteria) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(LOGIN_FUNZIONARIO);
      ps.setString(1,unFunzionarioSegreteria.getEmail());
      ps.setString(2,unFunzionarioSegreteria.getPassword());
      
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
        unFunzionarioSegreteria.setNome(rs.getString("Nome"));
        unFunzionarioSegreteria.setCognome(rs.getString("Cognome"));
        unFunzionarioSegreteria.setCodiceFiscale(rs.getString("CodiceFiscale"));
      }
        
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare un oggetto FunzionarioSegreteria sul database.
   * @param unFunzionarioSegreteria l'oggetto da ricercare sul database.
   * @return true se il funzionario viene correttamente ricercato, false altrimenti.
   */
  public static synchronized boolean ricercaFunzionario(FunzionarioSegreteria 
      unFunzionarioSegreteria) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_FUNZIONARIO);
      ps.setString(1,unFunzionarioSegreteria.getEmail());
      
      ResultSet rs = ps.executeQuery();
      
      rs.next();
      unFunzionarioSegreteria.setPassword(rs.getString("Password"));
      unFunzionarioSegreteria.setNome(rs.getString("Nome"));
      unFunzionarioSegreteria.setCognome(rs.getString("Cognome"));
      unFunzionarioSegreteria.setCodiceFiscale(rs.getString("CodiceFiscale"));
                  
      rs.close();    
      ps.close();
      
      notError = ricercaQualifiche(unFunzionarioSegreteria);
    } catch (SQLException e) {
      Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare tutti gli oggetti FunzionarioSegreteria sul database.
   * @param tuttiFunzionari la lista di funzionari da riempire.
   * @return true se i funzionari vengono correttamente ricercati, false altrimenti.
   */
  public static synchronized boolean ricercaTuttiFunzionari(ArrayList<FunzionarioSegreteria> 
      tuttiFunzionari) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_TUTTI_FUNZIONARI);
      
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        FunzionarioSegreteria unFunzionarioSegreteria = new FunzionarioSegreteria();
        unFunzionarioSegreteria.setEmail(rs.getString("Email"));
        unFunzionarioSegreteria.setPassword(rs.getString("Password"));
        unFunzionarioSegreteria.setNome(rs.getString("Nome"));
        unFunzionarioSegreteria.setCognome(rs.getString("Cognome"));
        unFunzionarioSegreteria.setCodiceFiscale(rs.getString("CodiceFiscale"));
        
        notError = ricercaQualifiche(unFunzionarioSegreteria);
        
        tuttiFunzionari.add(unFunzionarioSegreteria);
      }
            
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
    
  /**
   * Questo metodo permette di eliminare un oggetto FunzionarioSegreteria sul database.
   * @param unFunzionarioSegreteria l'oggetto da rimuovere dal database.
   * @return true se il funzionario viene correttamente eliminato, false altrimenti.
   */
  public static synchronized boolean rimuoviFunzionario(FunzionarioSegreteria 
      unFunzionarioSegreteria) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_FUNZIONARIO);
      ps.setString(1,unFunzionarioSegreteria.getEmail());
      
      ps.executeUpdate();
      
      ps.close();

      notError = rimuoviQualifiche(unFunzionarioSegreteria);      
    } catch (SQLException e) {
      Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di salvare delle qualifiche del FunzionarioSegreteria sul database.
   * @param unFunzionarioSegreteria l'oggetto di cui salvare le qualifiche sul database.
   * @return true se le qualifiche vengono correttamente salvate, false altrimenti.
   */
  private static synchronized boolean inserisciQualifiche(FunzionarioSegreteria 
      unFunzionarioSegreteria) { 
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      String[] qualifiche = unFunzionarioSegreteria.getQualifiche();
 
      for (String qualifica : qualifiche)  {
        PreparedStatement ps = con.prepareStatement(INSERISCI_QUALIFICA);
        ps.setString(1,unFunzionarioSegreteria.getEmail());
        ps.setString(2,qualifica);
        ps.executeUpdate();
 
        ps.close();
      }
    } catch (SQLException e) {
      Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
    
  /**
   * Questo metodo permette di ricercare delle qualifiche del FunzionarioSegreteria sul database.
   * @param unFunzionarioSegreteria l'oggetto di cui ricercare le qualifiche sul database.
   * @return true se le qualifiche vengono correttamente ricercate, false altrimenti.
   */
  private static synchronized boolean ricercaQualifiche(FunzionarioSegreteria 
      unFunzionarioSegreteria) { 
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_QUALIFICHE);
      ps.setString(1,unFunzionarioSegreteria.getEmail());
      
      ResultSet rs = ps.executeQuery();
      
      ArrayList<String> qualificheArrayList = new ArrayList<>();      
      while (rs.next()) {
        String qualifica = rs.getString("Descrizione");
        qualificheArrayList.add(qualifica);
      }
      
      String[] qualifiche = new String[qualificheArrayList.size()];
      for (int i = 0; i < qualifiche.length; i++) {
        qualifiche[i] = qualificheArrayList.get(i);
      }
      
      unFunzionarioSegreteria.setQualifiche(qualifiche);
            
      rs.close();    
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di rimuovere delle qualifiche del FunzionarioSegreteria sul database.
   * @param unFunzionarioSegreteria l'oggetto di cui rimuovere le qualifiche sul database.
   * @return true se le qualifiche vengono correttamente rimosse, false altrimenti.
   */
  private static synchronized boolean rimuoviQualifiche(FunzionarioSegreteria 
      unFunzionarioSegreteria) { 
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_QUALIFICHE);
      ps.setString(1,unFunzionarioSegreteria.getEmail());
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("FunzionarioSegreteriaDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  private static final String NOME_TABELLA_FUNZIONARIO = " eTraining.FunzionarioSegreteria ";
  private static final String NOME_TABELLA_QULIFICA = " eTraining.Qualifica ";
  
  private static final String SALVA_FUNZIONARIO = "INSERT INTO" + NOME_TABELLA_FUNZIONARIO
                                                + "VALUES(?,?,?,?,?)";
   
  private static final String RICERCA_FUNZIONARIO = "SELECT * "
                                                  + "FROM" + NOME_TABELLA_FUNZIONARIO
                                                  + "WHERE Email = ?";
  
  private static final String LOGIN_FUNZIONARIO = "SELECT * "
                                                  + "FROM" + NOME_TABELLA_FUNZIONARIO
                                                  + "WHERE Email = ? AND Password = ?";
  
  private static final String RICERCA_TUTTI_FUNZIONARI = "SELECT * "
                                                       + "FROM" + NOME_TABELLA_FUNZIONARIO;
  
  private static final String RIMUOVI_FUNZIONARIO = "DELETE FROM" + NOME_TABELLA_FUNZIONARIO
                                                  + "WHERE Email = ?";
  
  private static final String INSERISCI_QUALIFICA = "INSERT INTO" + NOME_TABELLA_QULIFICA
                                                  + "VALUES(?,?)";
  
  private static final String RICERCA_QUALIFICHE = "SELECT * "
                                                 + "FROM" + NOME_TABELLA_QULIFICA
                                                 + "WHERE FunzionarioSegreteria = ?";
                                                 
  private static final String RIMUOVI_QUALIFICHE = "DELETE FROM" + NOME_TABELLA_QULIFICA
                                                 + "WHERE FunzionarioSegreteria = ?";
}
