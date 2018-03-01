package it.unisa.etraining.model.dao;

import it.unisa.etraining.model.bean.AttivitaRegistro;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Questa classe fornisce i metodi per gestire l'entità AttivitaRegistro in maniera persistente.
 * @author Basso Emanuele
 *
 */
public class AttivitaRegistroDao {
  
  /**
   * Questo metodo permette di salvare un oggetto AttivitaRegistro sul database.
   * @param unAttivita l'oggetto da salvare sul database.
   * @return true se l'attivita' viene correttamente salvata, false altrimenti.
   */
  public static synchronized boolean salvaAttivitaRegistro(AttivitaRegistro 
      unAttivita) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(SALVA_ATTIVITA_REGISTRO);
      ps.setString(1,unAttivita.getTirocinio().getTirocinante().getEmail());
      ps.setInt(2,unAttivita.getTirocinio().getOfferta().getId());
      ps.setString(3,unAttivita.getAttivitaSvolta());
      ps.setDate(4,new Date(unAttivita.getInizio().getTimeInMillis()));
      ps.setDate(5,new Date(unAttivita.getFine().getTimeInMillis()));
      ps.setInt(6,unAttivita.getOreSvolte());
      ps.setString(7,unAttivita.getConvalida());
      ps.setNull(8,Types.VARCHAR);
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("AttivitaRegistroDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("AttivitaRegistroDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare un oggetto AttivitaRegistro sul database.
   * @param unAttivita l'oggetto da ricercare sul database.
   * @return true se l'attivita' viene correttamente ricercata, false altrimenti.
   */
  public static synchronized boolean ricercaAttivitaRegistro(AttivitaRegistro unAttivita) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_ATTIVITA_REGISTRO);
      ps.setString(1,unAttivita.getTirocinio().getTirocinante().getEmail());
      ps.setInt(2,unAttivita.getTirocinio().getOfferta().getId());
      ps.setString(3,unAttivita.getAttivitaSvolta());
      
      ResultSet rs = ps.executeQuery();
      
      rs.next();
      GregorianCalendar dataLetta = new GregorianCalendar();
      
      dataLetta.setTime(rs.getDate("Inizio"));
      unAttivita.setInizio(dataLetta);
      dataLetta.setTime(rs.getDate("Fine"));
      unAttivita.setFine(dataLetta);
      unAttivita.setOreSvolte(rs.getInt("OreSvolte"));
      unAttivita.setConvalida(rs.getString("Convalida"));
      
      String motivazioneRifiuto = rs.getString("MotivazioneRifiuto");
      if (rs.wasNull()) { 
        unAttivita.setMotivazioneRifiuto(null);
      } else {
        unAttivita.setMotivazioneRifiuto(motivazioneRifiuto);
      }       
        
      rs.close();
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("AttivitaRegistroDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("AttivitaRegistroDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare tutti gli oggetti AttivitaRegistro 
   * di un determinato oggetto Tirocinio sul database.
   * @param tutteAttivita la lista di oggetti di attivita' da riempire
   * @param tirocinio l'oggetto Tirocinio di cui ricercare le attivita'.
   * @return true se le attivita' vengono correttamente ricercate, false altrimenti.
   */
  public static synchronized boolean ricercaTutteAttivitaRegistro(ArrayList<AttivitaRegistro> 
        tutteAttivita, Tirocinio tirocinio) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RICERCA_TUTTE_ATTIVITA_REGISTRO);
      ps.setString(1,tirocinio.getTirocinante().getEmail());
      ps.setInt(2,tirocinio.getOfferta().getId());
      
      ResultSet rs = ps.executeQuery();
      
      while (rs.next()) {
        AttivitaRegistro unAttivita = new AttivitaRegistro();
        
        GregorianCalendar dataLetta = new GregorianCalendar();
        
        unAttivita.setAttivitaSvolta(rs.getString("AttivitaSvolta"));
        dataLetta.setTime(rs.getDate("Inizio"));
        unAttivita.setInizio(dataLetta);
        dataLetta.setTime(rs.getDate("Fine"));
        unAttivita.setFine(dataLetta);
        unAttivita.setOreSvolte(rs.getInt("OreSvolte"));
        unAttivita.setConvalida(rs.getString("Convalida"));
        
        String motivazioneRifiuto = rs.getString("MotivazioneRifiuto");
        if (rs.wasNull()) { 
          unAttivita.setMotivazioneRifiuto(null);
        } else {
          unAttivita.setMotivazioneRifiuto(motivazioneRifiuto);
        }       
        
        tutteAttivita.add(unAttivita);
      }

      rs.close();
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("AttivitaRegistroDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("AttivitaRegistroDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di aggiornare la convalida di un oggetto AttivitaRegistro sul database.
   * @param unAttivita l'oggetto da aggiornare sul database.
   * @return true se l'attivita' viene correttamente aggiornata, false altrimenti.
   */
  public static synchronized boolean aggiornaConvalidaAttivita(AttivitaRegistro unAttivita) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(AGGIORNA_CONVALIDA_ATTIVITA);
      
      ps.setString(1,unAttivita.getConvalida());
      
      String motivazioneRifiuto = unAttivita.getMotivazioneRifiuto();
      if (null == motivazioneRifiuto) {
        ps.setNull(2,Types.VARCHAR);
      } else {
        ps.setString(2,motivazioneRifiuto);
      }
           
      ps.setString(3,unAttivita.getTirocinio().getTirocinante().getEmail());
      ps.setInt(4,unAttivita.getTirocinio().getOfferta().getId());
      ps.setString(5,unAttivita.getAttivitaSvolta());
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("AttivitaRegistroDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("AttivitaRegistroDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }
  
  /**
   * Questo metodo permette di aggiornare la convalida di tutte le AttivitaRegistro di 
   * un Tirocinio sul database.
   * @param unTirocinio l'oggetto di cui aggiornare le AttivitaRegistro sul database.
   * @return true se le attivita' vengono correttamente aggiornate, false altrimenti.
   */
  public static synchronized boolean aggiornaConvalidaTutteAttivita(Tirocinio unTirocinio) {
    Connection con = null;
    boolean notError = true;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(AGGIORNA_CONVALIDA_TUTTE_ATTIVITA);
      
      String convalida = unTirocinio.getAttivitaSvolte()[0].getConvalida();
      
      ps.setString(1,convalida);
      ps.setString(2,unTirocinio.getTirocinante().getEmail());
      ps.setInt(3,unTirocinio.getOfferta().getId());
      
      if (convalida.equals(AttivitaRegistro.CONVALIDATA_TUTOR_AZIENDALE)) {
        ps.setString(4,AttivitaRegistro.IN_CONVALIDA);
      } else if (convalida.equals(AttivitaRegistro.CONVALIDATA)) {
        ps.setString(4,AttivitaRegistro.CONVALIDATA_TUTOR_AZIENDALE);
      }
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("AttivitaRegistroDao").log(Level.SEVERE,"Database Error",e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("AttivitaRegistroDao").log(Level.SEVERE,"Database Error",e);
        notError = false;
      }
    }
    
    return notError;
  }

  private static final String NOME_TABELLA = " eTraining.AttivitaRegistro ";
  
  private static final String SALVA_ATTIVITA_REGISTRO = "INSERT INTO" + NOME_TABELLA
                                                      + "VALUES (?,?,?,?,?,?,?,?)";
  
  private static final String RICERCA_ATTIVITA_REGISTRO = "SELECT * "
                                                     + "FROM" + NOME_TABELLA
                                                     + "WHERE Tirocinante = ? "
                                                     + "AND IdOffertaFormativaTirocinioEsterno = ? "
                                                     + "AND AttivitaSvolta = ?";
  
  private static final String RICERCA_TUTTE_ATTIVITA_REGISTRO = "SELECT * "
                                                              + "FROM" + NOME_TABELLA
                                                              + "WHERE Tirocinante = ? "
                                                     + "AND IdOffertaFormativaTirocinioEsterno = ?";

  private static final String AGGIORNA_CONVALIDA_ATTIVITA = "UPDATE" + NOME_TABELLA
                                                     + "SET Convalida = ?, MotivazioneRifiuto = ? "
                                                     + "WHERE Tirocinante = ? "
                                                     + "AND IdOffertaFormativaTirocinioEsterno = ? "
                                                     + "AND AttivitaSvolta = ?";
  
  private static final String AGGIORNA_CONVALIDA_TUTTE_ATTIVITA = "UPDATE" + NOME_TABELLA
                                                    + "SET Convalida = ? "
                                                    + "WHERE Tirocinante = ? "
                                                    + "AND IdOffertaFormativaTirocinioEsterno = ? "
                                                    + "AND Convalida = ?";
}
