package it.unisa.etraining.model.connection;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisa.etraining.model.connection.DriverManagerConnectionPool;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConnection {

  @Test
  public void test1_DriverManagerConnectionPool() {
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    DriverManagerConnectionPool pool = null;
    
    try {
      pool = new DriverManagerConnectionPool(connessione, utente, password, driver);
    } catch  (Exception e) { }
    
    assertNotNull(pool);
  }
   
  @Test
  public void test2_Connection() {
    Connection con = null;
    
    try {
      con = DriverManagerConnectionPool.getConnection();
    } catch  (Exception e) {}
    
    assertNotNull(con);   
    
    try {
      DriverManagerConnectionPool.releaseConnection(con);
    } catch (Exception e) {
      fail();
    }
  }

}
