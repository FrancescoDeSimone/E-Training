package it.unisa.etraining.model.config;

import it.unisa.etraining.model.connection.DriverManagerConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Questa classe implementa @WebListener e serve per istanziare la connessione con il database.
 * 
 * @author Esposito Flavio
 */
@WebListener
public class Inizializzazione implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    try {
      new DriverManagerConnectionPool(connessione, utente, password, driver);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
  
  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    //  VUOTO
  }
  
}
