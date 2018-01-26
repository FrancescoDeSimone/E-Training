package it.unisa.etraining.model.dao;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.TitolareAzienda;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.TitolareAziendaDao;

/**
 * @author Esposito Flavio
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTitolareAziendaDao {

  private static TitolareAzienda unTitolareAzienda;
  
  @BeforeClass
  public static void init() throws Exception{
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    new DriverManagerConnectionPool(connessione, utente, password, driver);
    
    Azienda unAzienda = new Azienda();
    unAzienda.setEmail("bassoemanuele1704@gmail.com");
    
    unTitolareAzienda = new TitolareAzienda();
    unTitolareAzienda.setAzienda(unAzienda);
    unTitolareAzienda.setNome("Emanuele");
    unTitolareAzienda.setCognome("Basso");
    unTitolareAzienda.setCodiceFiscale("BSSMNLXXX475UOI6");
  }
  
  @Test
  public void test1_InserimentoTitolare(){
    boolean risulatato = TitolareAziendaDao.salvaTitolareAzienda(unTitolareAzienda);
    
    assertTrue(risulatato);
  }
  
  @Test
  public void test2_RicercaTitolare(){
    TitolareAzienda titolareAzienda = new TitolareAzienda();
    titolareAzienda.setAzienda(unTitolareAzienda.getAzienda());
    
    TitolareAziendaDao.ricercaTitolare(titolareAzienda);
    
    assertEquals(unTitolareAzienda.getNome(),titolareAzienda.getNome());
  }
  
  @Test
  public void test3_RimozioneTitolare() throws Exception{
    boolean risultato = TitolareAziendaDao.rimuoviTitolare(unTitolareAzienda);   
    
    assertTrue(risultato);
  }

}
