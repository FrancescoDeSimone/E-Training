package it.unisa.etraining.model.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.TutorDidatticoDao;

/**
 * 
 * @author Basso Emanuele
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTutorDidatticoDao {

  private static TutorDidattico unTutorDidattico;
  
  @BeforeClass
  public static void init() throws Exception{
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    new DriverManagerConnectionPool(connessione, utente, password, driver);
    
    unTutorDidattico = new TutorDidattico();
    unTutorDidattico.setEmail("bassoemanuele1704@gmail.com");
    unTutorDidattico.setPassword("Ciao");
    unTutorDidattico.setNome("Emanuele");
    unTutorDidattico.setCognome("Basso");
    unTutorDidattico.setCodiceFiscale("BSSMNLXXX475UOI6");
    unTutorDidattico.setDisponibilita(true);
    String[] insegnamenti = {"IS","SO"};
    unTutorDidattico.setInsegnamenti(insegnamenti);
    String[] campiInteresse = {"Compilatori lazy","Database coordinati misti"};
    unTutorDidattico.setCampiInteresse(campiInteresse);
  }
  
  @Test
  public void test1_InserimentoTutorDidattico(){
    boolean risultato = TutorDidatticoDao.salvaTutorDidattico(unTutorDidattico);
    
    assertTrue(risultato);
  }
  
  @Test
  public void test2_LoginTutorDidattico(){
    TutorDidattico tutorDidattico = new TutorDidattico();
    tutorDidattico.setEmail(unTutorDidattico.getEmail());
    tutorDidattico.setPassword(unTutorDidattico.getPassword());
    
    TutorDidatticoDao.loginTutorDidattico(tutorDidattico);
    
    assertEquals(tutorDidattico.getNome(),unTutorDidattico.getNome());
  }
  
  @Test
  public void test3_RicercaTutorDidattico(){
    TutorDidattico tutorDidattico = new TutorDidattico();
    tutorDidattico.setEmail(unTutorDidattico.getEmail());
    
    TutorDidatticoDao.ricercaTutorDidattico(tutorDidattico);
    
    assertEquals(unTutorDidattico.getPassword(),tutorDidattico.getPassword());
    assertEquals(unTutorDidattico.getCampiInteresse()[0],tutorDidattico.getCampiInteresse()[0]);
    assertEquals(unTutorDidattico.getInsegnamenti()[0],tutorDidattico.getInsegnamenti()[0]);
  }
  
  @Test
  public void test4_RicercaTutorDidattici(){
    ArrayList<TutorDidattico> tuttiTutorDidattici = new ArrayList<>();
    TutorDidatticoDao.ricercaTuttiTutorDidattici(tuttiTutorDidattici);
    
    assertEquals(tuttiTutorDidattici.size(),1);
  }
  
  @Test
  public void test5_AggiornaDisponibilita(){
    unTutorDidattico.setDisponibilita(false);
    
    boolean risultato = TutorDidatticoDao.aggiornaDisponibilita(unTutorDidattico);
    
    assertTrue(risultato);

    TutorDidattico tutorDidattico = new TutorDidattico();
    tutorDidattico.setEmail(unTutorDidattico.getEmail());
    
    TutorDidatticoDao.ricercaTutorDidattico(tutorDidattico);
        
    assertEquals(unTutorDidattico.isDisponibilita(),tutorDidattico.isDisponibilita());
  }
  
  @Test
  public void test6_RimozioneTutorDidattico(){
    boolean risultato = TutorDidatticoDao.rimuoviTutorDidattico(unTutorDidattico);
    
    assertTrue(risultato);
  }
 

}
