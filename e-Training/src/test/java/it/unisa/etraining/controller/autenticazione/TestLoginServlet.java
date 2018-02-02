package it.unisa.etraining.controller.autenticazione;

import static org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.FunzionarioSegreteria;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.AziendaDao;
import it.unisa.etraining.model.dao.FunzionarioSegreteriaDao;
import it.unisa.etraining.model.dao.TirocinanteDao;
import it.unisa.etraining.model.dao.TutorAziendaleDao;
import it.unisa.etraining.model.dao.TutorDidatticoDao;

import static org.mockito.Mockito.when;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestLoginServlet {
  
  private static Tirocinante unTirocinante;
  private static TutorDidattico unTutorDidattico;
  private static Azienda unAzienda;
  private static TutorAziendale unTutorAziendale;
  private static FunzionarioSegreteria unFunzionarioSegreteria;
  
  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession session;
  @Mock
  RequestDispatcher dispatcher;
  
  @BeforeClass
  public static void init() throws Exception {
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    new DriverManagerConnectionPool(connessione, utente, password, driver);
    
    unTirocinante = new Tirocinante();
    unTirocinante.setEmail("e.basso1@studenti.unisa.it");
    unTirocinante.setPassword("password");
    unTirocinante.setNome("Emanuele");
    unTirocinante.setCognome("Basso");
    unTirocinante.setAnnoIscrizione("2015/2016");
    unTirocinante.setMatricola("0512100000");
    
    TirocinanteDao.salvaTirocinante(unTirocinante);
    
    unTutorDidattico = new TutorDidattico();
    unTutorDidattico.setEmail("bassoemanuele1704@gmail.com");
    unTutorDidattico.setPassword("password");
    unTutorDidattico.setNome("Emanuele");
    unTutorDidattico.setCognome("Basso");
    unTutorDidattico.setCodiceFiscale("BSSMNLXXX475UOI6");
    unTutorDidattico.setDisponibilita(true);
    String[] insegnamenti = {"IS","SO"};
    unTutorDidattico.setInsegnamenti(insegnamenti);
    String[] campiInteresse = {"Compilatori lazy","Database coordinati misti"};
    unTutorDidattico.setCampiInteresse(campiInteresse);
    
    TutorDidatticoDao.salvaTutorDidattico(unTutorDidattico);
    
    unAzienda = new Azienda();
    unAzienda.setEmail("2Streaming@gmail.com");
    unAzienda.setPassword("password");
    unAzienda.setNome("2Stream");
    unAzienda.setPartitaIva("GHPL59HG51D");
    unAzienda.setSede("Via Roma, 6");
    unAzienda.setCitta("Milano");
    
    AziendaDao.salvaAzienda(unAzienda);
    
    unTutorAziendale = new TutorAziendale();
    unTutorAziendale.setEmail("piermenti@gmail.com");
    unTutorAziendale.setAzienda(unAzienda);
    unTutorAziendale.setPassword("password");
    unTutorAziendale.setNome("Emanuele");
    unTutorAziendale.setCognome("Basso");
    unTutorAziendale.setCodiceFiscale("BSSMNLXXX475UOI6");
    unTutorAziendale.setSettoreLavoro("Database distribuiti");
    
    TutorAziendaleDao.salvaTutorAziendale(unTutorAziendale);
    
    unFunzionarioSegreteria = new FunzionarioSegreteria();
    unFunzionarioSegreteria.setEmail("bassoemanuele1704@gmail.com");
    unFunzionarioSegreteria.setPassword("password");
    unFunzionarioSegreteria.setNome("Emanuele");
    unFunzionarioSegreteria.setCognome("Basso");
    unFunzionarioSegreteria.setCodiceFiscale("BSSMNLXXX475UOI6");
    String[] qualifiche = {"Diploma","Media"};
    unFunzionarioSegreteria.setQualifiche(qualifiche);
    
    FunzionarioSegreteriaDao.salvaFunzionario(unFunzionarioSegreteria);
  }
  
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }  

  /*@After
  public void tearDown() throws Exception {
  }*/

  @Test
  public void test01_EmailNull() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn(null);
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("tirocinante");
    when(request.getRequestDispatcher("/login.jsp?errore=1")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test02_PasswordNull() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("e.basso1@studenti.unisa.it");
    when(request.getParameter("inputPassword")).thenReturn(null);
    when(request.getParameter("inputTipo")).thenReturn("tirocinante");
    when(request.getRequestDispatcher("/login.jsp?errore=1")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test03_EmailErrata() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("e.basso1@@studenti.unisa.it");
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("tirocinante");
    when(request.getRequestDispatcher("/login.jsp?errore=1")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test04_PasswordErrata() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("e.basso1@@studenti.unisa.it");
    when(request.getParameter("inputPassword")).thenReturn("ciao");
    when(request.getParameter("inputTipo")).thenReturn("tirocinante");
    when(request.getRequestDispatcher("/login.jsp?errore=1")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test05_NonInDatabaseTirocinante() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("e.basso2@studenti.unisa.it");
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("tirocinante");
    when(request.getRequestDispatcher("/login.jsp?errore=Credenziali errate!")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);    
  }
  
  @Test
  public void test06_NonInDatabaseTutorDidattico() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("tutordidattico@unisa.it");
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("tutorDidattico");
    when(request.getRequestDispatcher("/login.jsp?errore=Credenziali errate!")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);    
  }
  
  @Test
  public void test07_NonInDatabaseTutorAziendale() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("tutoraziendale@gmail.com");
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("tutorAziendale");
    when(request.getRequestDispatcher("/login.jsp?errore=Credenziali errate!")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);    
  }
  
  @Test
  public void test08_NonInDatabaseFunzionario() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("tutoraziendale@gmail.com");
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("funzionario");
    when(request.getRequestDispatcher("/login.jsp?errore=Credenziali errate!")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);    
  }
  
  @Test
  public void test09_NonInDatabaseAzienda() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("azienda@gmail.com");
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("azienda");
    when(request.getRequestDispatcher("/login.jsp?errore=Credenziali errate!")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);    
  }
  
  @Test
  public void test10_LoginSuccessoTirocinante() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("e.basso1@studenti.unisa.it");
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("tirocinante");
    when(request.getSession()).thenReturn(session);
    when(request.getRequestDispatcher("/TirociniAttiviStoricoServlet")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test11_LoginSuccessoTutorDidattico() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("bassoemanuele1704@gmail.com");
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("tutorDidattico");
    when(request.getSession()).thenReturn(session);
    when(request.getRequestDispatcher("/TirociniAttiviStoricoServlet")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test12_LoginSuccessoTutorAziendale() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("piermenti@gmail.com");
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("tutorAziendale");
    when(request.getSession()).thenReturn(session);
    when(request.getRequestDispatcher("/TirociniAttiviStoricoServlet")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test13_LoginSuccessoAzienda() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("2Streaming@gmail.com");
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("azienda");
    when(request.getSession()).thenReturn(session);
    when(request.getRequestDispatcher("/TirociniAttiviStoricoServlet")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test14_LoginSuccessoFunzionarioSegreteria() throws Exception {
    when(request.getParameter("inputEmail")).thenReturn("bassoemanuele1704@gmail.com");
    when(request.getParameter("inputPassword")).thenReturn("password");
    when(request.getParameter("inputTipo")).thenReturn("funzionario");
    when(request.getSession()).thenReturn(session);
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);    
    try {
      new LoginServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @AfterClass
  public static void close() {
    TirocinanteDao.rimuoviTirocinante(unTirocinante);
    TutorDidatticoDao.rimuoviTutorDidattico(unTutorDidattico);
    AziendaDao.rimuoviAzienda(unAzienda);
    TutorAziendaleDao.rimuoviTutorAziendale(unTutorAziendale);
    FunzionarioSegreteriaDao.rimuoviFunzionario(unFunzionarioSegreteria);
  }

}
