package it.unisa.etraining.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Questa classe permette di gestire un pool di connessioni verso il database.
 * @author Basso Emanuele
 *
 */
public class DriverManagerConnectionPool {

  private static List<Connection> freeDbConnection;
  private static String CONNESSIONE;
  private static String USER;
  private static String PASS;

  /**
   * Questo metodo permette di creare il pool di connessioni verso il database.
   * @param connessione l'url di connessione verso il database.
   * @param utente l'utente per connettersi al database.
   * @param password la password per connettersi al database.
   * @param driver il nome dei driver da caricare.
   * @throws Exception lanciata nel caso in cui non riesce a connettersi al database.
   */
  public DriverManagerConnectionPool(String connessione,String utente,String password,
      String driver) throws Exception {
    freeDbConnection = new LinkedList<>();

    CONNESSIONE = connessione;
    USER = utente;
    PASS = password;

    try {
      Class.forName(driver).newInstance();
    } catch (Exception e) {
      throw new Exception("Errore nel caricare i driver");
    }

    try {
      initializeConnections();
    } catch (SQLException e) {
      throw new Exception("Errore nella creazione del pool di connessioni");
    }
  }
  
  /**
   * Questo metodo inizializza le connessioni iniziali.
   * @throws SQLException lanciata nel caso in cui non riesce a connettersi al database.
   */
  private static synchronized void initializeConnections() throws SQLException {
    for (int i = 0; i < 5; i++) {
      Connection con = createDbConnection();
      freeDbConnection.add(con);
    }
  }
  
  /**
   * Questo metodi instanzia una connessione verso il database.
   * @return la connessione instanziata.
   * @throws SQLException lanciata nel caso in cui non riesce a connettersi al database.
   */

  private static Connection createDbConnection() throws SQLException {
    Connection con = DriverManager.getConnection(CONNESSIONE, USER, PASS);

    return con;
  }

  /**
   * Questo metodo restituisce una connessione libera dal pool.
   * @return la connessione libera.
   * @throws SQLException lanciata nel caso in cui non riesce a connettersi al database.
   */
  public static synchronized Connection getConnection() throws SQLException {
    Connection con;

    if (!freeDbConnection.isEmpty()) {
      con = freeDbConnection.get(0);
      freeDbConnection.remove(0);

      try {
        if (con.isClosed()) {
          con = getConnection();
        }
      } catch (SQLException e) {
        con.close();
        con = getConnection();
      }
    } else {
      con = createDbConnection();
    }

    return con;
  }
  
  /**
   * Questo metodo aggiunge la connessione passata al pool delle connessioni.
   * @param connessione la connessioe da aggiungere.
   * @throws SQLException lanciata nel caso in cui non riesce a connettersi al database.
   */
  public static synchronized void releaseConnection(Connection connessione) throws SQLException {
    if (connessione != null) {
      freeDbConnection.add(connessione);
      balanceConnection();
    }
  }

  /**
   * Questo metodo permette di rilasciare delle connessioni dal pool se sono troppe.
   * @throws SQLException lanciata nel caso in cui non riesce a connettersi al database.
   */
  private static synchronized void balanceConnection() throws SQLException {
    int size = freeDbConnection.size();
    
    if (size >= 100) {
      for (int i = 0; i < (size / 2); i++) {
        Connection con = freeDbConnection.remove(0);
        con.close();
      }
    }
  }

}
