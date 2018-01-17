package it.unisa.etraining.testing.model.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.AziendaDao;

/**
 * 
 * @author Basso Emanuele
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAziendaDao {

  private static Azienda unAzienda;
  
  @BeforeClass
  public static void init() throws Exception{
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    new DriverManagerConnectionPool(connessione, utente, password, driver);
    
    unAzienda = new Azienda();
    unAzienda.setEmail("bassoemanuele1704@gmail.com");
    unAzienda.setPassword("Ciao");
    unAzienda.setNome("2Stream");
    unAzienda.setPartitaIva("GHPL59HG51D");
    unAzienda.setSede("Via Roma, 6");
    unAzienda.setCitta("Milano");
  }
  
  @Test
  public void test1_InserimentoAzienda(){
    boolean risulatato = AziendaDao.salvaAzienda(unAzienda);
    
    assertTrue(risulatato);
  }
  
  @Test
  public void test2_RicercaAzienda(){
    Azienda azienda = new Azienda();
    azienda.setEmail(unAzienda.getEmail());
    
    AziendaDao.ricercaAzienda(azienda);
    
    assertEquals(azienda.getPassword(),unAzienda.getPassword());
  }
  
  @Test
  public void test3_RicercaAziende(){
    ArrayList<Azienda> tutteAziende = new ArrayList<>();
    AziendaDao.ricercaTutteAzienda(tutteAziende);
    
    assertEquals(tutteAziende.size(),1);
  }
  
  @Test
  public void test4_RimozioneAzienda() throws Exception{
    boolean risultato = AziendaDao.rimuoviAzienda(unAzienda);
    
    assertTrue(risultato);
  }

}
