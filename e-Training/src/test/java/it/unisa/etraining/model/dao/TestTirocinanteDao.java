package it.unisa.etraining.model.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.TirocinanteDao;

/**
 * 
 * @author Basso Emanuele
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTirocinanteDao {

  private static Tirocinante unTirocinante;
  
  @BeforeClass
  public static void init() throws Exception{
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    new DriverManagerConnectionPool(connessione, utente, password, driver);
    
    unTirocinante = new Tirocinante();
    unTirocinante.setEmail("bassoemanuele1704@gmail.com");
    unTirocinante.setPassword("Password");
    unTirocinante.setNome("Emanuele");
    unTirocinante.setCognome("Basso");
    unTirocinante.setAnnoIscrizione("2015/2016");
    unTirocinante.setMatricola("0512100000");
  }
  
  @Test
  public void test1_InserimentoTirocinante(){
    boolean risulatato = TirocinanteDao.salvaTirocinante(unTirocinante);
    
    assertTrue(risulatato);
  }
  
  @Test
  public void test2_LoginTirocinante(){
    Tirocinante tirocinante = new Tirocinante();
    tirocinante.setEmail(unTirocinante.getEmail());
    tirocinante.setPassword(unTirocinante.getPassword());
    
    TirocinanteDao.loginTirocinante(tirocinante);
    
    assertEquals(tirocinante.getNome(),unTirocinante.getNome());
  }
  
  @Test
  public void test3_RicercaTirocinante(){
    Tirocinante tirocinante = new Tirocinante();
    tirocinante.setEmail(unTirocinante.getEmail());
    
    TirocinanteDao.ricercaTirocinante(tirocinante);

    assertEquals(unTirocinante.getPassword(),tirocinante.getPassword());
  }
  
  @Test
  public void test4_RimozioneTirocinante() throws Exception{
    boolean risultato = TirocinanteDao.rimuoviTirocinante(unTirocinante);   
    
    assertTrue(risultato);
  }

}
