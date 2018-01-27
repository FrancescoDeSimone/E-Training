package it.unisa.etraining.model.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisa.etraining.model.bean.FunzionarioSegreteria;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.FunzionarioSegreteriaDao;

/**
 * 
 * @author Basso Emanuele
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFunzionarioSegreteriaDao {

  private static FunzionarioSegreteria unFunzionarioSegreteria;
  
  @BeforeClass
  public static void init() throws Exception{
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    new DriverManagerConnectionPool(connessione, utente, password, driver);
    
    unFunzionarioSegreteria = new FunzionarioSegreteria();
    unFunzionarioSegreteria.setEmail("bassoemanuele1704@gmail.com");
    unFunzionarioSegreteria.setPassword("Ciao");
    unFunzionarioSegreteria.setNome("Emanuele");
    unFunzionarioSegreteria.setCognome("Basso");
    unFunzionarioSegreteria.setCodiceFiscale("BSSMNLXXX475UOI6");
    String[] qualifiche = {"Diploma","Media"};
    unFunzionarioSegreteria.setQualifiche(qualifiche);
  }
  
  @Test
  public void test1_InserimentoFunzionario(){
    boolean risultato = FunzionarioSegreteriaDao.salvaFunzionario(unFunzionarioSegreteria);
    
    assertTrue(risultato);
  }
  
  @Test
  public void test2_LoginFunzionario(){
    FunzionarioSegreteria funzionarioSegreteria = new FunzionarioSegreteria();
    funzionarioSegreteria.setEmail(unFunzionarioSegreteria.getEmail());
    funzionarioSegreteria.setPassword(unFunzionarioSegreteria.getPassword());
    
    FunzionarioSegreteriaDao.loginFunzionario(funzionarioSegreteria);
    
    assertEquals(funzionarioSegreteria.getNome(),unFunzionarioSegreteria.getNome());
  }
  
  @Test
  public void test3_RicercaFunzionario(){
    FunzionarioSegreteria funzionarioSegreteria = new FunzionarioSegreteria();
    funzionarioSegreteria.setEmail(unFunzionarioSegreteria.getEmail());
    
    FunzionarioSegreteriaDao.ricercaFunzionario(funzionarioSegreteria);
    
    assertEquals(unFunzionarioSegreteria.getPassword(),funzionarioSegreteria.getPassword());
    assertEquals(unFunzionarioSegreteria.getQualifiche()[0],funzionarioSegreteria.getQualifiche()[0]);
  }
  
  @Test
  public void test4_RicercaFunzionari(){
    ArrayList<FunzionarioSegreteria> tuttiFunzionari = new ArrayList<>();
    FunzionarioSegreteriaDao.ricercaTuttiFunzionari(tuttiFunzionari);
    
    assertEquals(tuttiFunzionari.size(),1);
  }
  
  @Test
  public void test5_RimozioneFunzionario(){
    boolean risultato = FunzionarioSegreteriaDao.rimuoviFunzionario(unFunzionarioSegreteria);
    
    assertTrue(risultato);
  }

}
