package it.unisa.etraining.model.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.AziendaDao;
import it.unisa.etraining.model.dao.TutorAziendaleDao;

/**
 * @author Esposito Flavio
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTutorAziendaleDao {

  private static TutorAziendale unTutorAziendale;
  private static Azienda unAzienda;
  
  @BeforeClass
  public static void init() throws Exception{
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    new DriverManagerConnectionPool(connessione, utente, password, driver);
    
    unAzienda = new Azienda();
    unAzienda.setEmail("2Streaming@gmail.com");
    unAzienda.setPassword("Ciao");
    unAzienda.setNome("2Stream");
    unAzienda.setPartitaIva("GHPL59HG51D");
    unAzienda.setSede("Via Roma, 6");
    unAzienda.setCitta("Milano");
    
    AziendaDao.salvaAzienda(unAzienda);
    
    unTutorAziendale = new TutorAziendale();
    unTutorAziendale.setEmail("bassoemanuele1704@gmail.com");
    unTutorAziendale.setAzienda(unAzienda);
    unTutorAziendale.setPassword("Ciao");
    unTutorAziendale.setNome("Emanuele");
    unTutorAziendale.setCognome("Basso");
    unTutorAziendale.setCodiceFiscale("BSSMNLXXX475UOI6");
    unTutorAziendale.setSettoreLavoro("Database distribuiti");
  }

  @Test
  public void test1_InserimentoTutorAziendale(){
    boolean risultato = TutorAziendaleDao.salvaTutorAziendale(unTutorAziendale);
    
    assertTrue(risultato);
  }
  
  @Test
  public void test2_LoginTutorAziendale(){
    TutorAziendale tutorAziendale = new TutorAziendale();
    tutorAziendale.setEmail(unTutorAziendale.getEmail());
    tutorAziendale.setPassword(unTutorAziendale.getPassword());
    
    TutorAziendaleDao.loginTutorAziendale(tutorAziendale);
    
    assertEquals(tutorAziendale.getNome(),unTutorAziendale.getNome());
  }
  
  @Test
  public void test3_RicercaTutorAziendale(){
    TutorAziendale tutorAziendale = new TutorAziendale();
    tutorAziendale.setEmail(unTutorAziendale.getEmail());
    
    TutorAziendaleDao.ricercaTutorAziendale(tutorAziendale);
    
    assertEquals(unTutorAziendale.getPassword(),tutorAziendale.getPassword());
    assertEquals(tutorAziendale.getAzienda().getEmail(),unAzienda.getEmail());
  }
  
  @Test
  public void test4_RicercaFunzionari(){   
    ArrayList<TutorAziendale> tuttiTutorAziendali = new ArrayList<>();
    TutorAziendaleDao.ricercaTuttiTutorAziendali(tuttiTutorAziendali,unAzienda);
    
    assertEquals(tuttiTutorAziendali.size(),1);
    assertEquals(tuttiTutorAziendali.get(0).getAzienda().getEmail(),unAzienda.getEmail());
  }
  
  @Test
  public void test5_RimozioneFunzionario(){
    boolean risultato = TutorAziendaleDao.rimuoviTutorAziendale(unTutorAziendale);
    
    assertTrue(risultato);
  }
  
  @AfterClass
  public static void close(){
    AziendaDao.rimuoviAzienda(unAzienda);
  }
  
}
