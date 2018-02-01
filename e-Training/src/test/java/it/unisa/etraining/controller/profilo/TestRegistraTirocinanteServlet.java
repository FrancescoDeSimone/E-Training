package it.unisa.etraining.controller.profilo;

import static org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;


import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.TirocinanteDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRegistraTirocinanteServlet {

  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession sessione;
  @Mock
  RequestDispatcher dispatcher;

  
  private static Tirocinante unTirocinante;
  private static Tirocinante unTirocinante2;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    }
  
  @BeforeClass
  public static void init() throws Exception{
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    new DriverManagerConnectionPool(connessione, utente, password, driver);
    
    unTirocinante2 = new Tirocinante();
    unTirocinante2.setEmail("f.desimone1@studenti.unisa.it");
    unTirocinante2.setPassword("Password");
    unTirocinante2.setNome("Desi");
    unTirocinante2.setCognome("Desi");
    unTirocinante2.setAnnoIscrizione("2015/2016");
    unTirocinante2.setMatricola("0512100001");
    
    unTirocinante = new Tirocinante();
    unTirocinante.setEmail("e.basso1@studenti.unisa.it");
    unTirocinante.setPassword("Password");
    unTirocinante.setNome("Emanuele");
    unTirocinante.setCognome("Basso");
    unTirocinante.setAnnoIscrizione("2015/2016");
    unTirocinante.setMatricola("0512100000");
    TirocinanteDao.salvaTirocinante(unTirocinante);
  }
  
  @Test
  public void test01_NomeErrato() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn("123");
    when(request.getParameter("cognome")).thenReturn(unTirocinante.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn(unTirocinante.getMatricola());
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn(unTirocinante.getPassword());
    when(request.getParameter("password2")).thenReturn(unTirocinante.getPassword());
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test02_CognomeErrato() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn(unTirocinante.getNome());
    when(request.getParameter("cognome")).thenReturn("123");
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn(unTirocinante.getMatricola());
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn(unTirocinante.getPassword());
    when(request.getParameter("password2")).thenReturn(unTirocinante.getPassword());
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test03_matricolaErrato() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn(unTirocinante.getNome());
    when(request.getParameter("cognome")).thenReturn(unTirocinante.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn("lolz");
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn(unTirocinante.getPassword());
    when(request.getParameter("password2")).thenReturn(unTirocinante.getPassword());
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test04_AnnoIscrizioneErrato() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn(unTirocinante.getNome());
    when(request.getParameter("cognome")).thenReturn(unTirocinante.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn(unTirocinante.getMatricola());
    when(request.getParameter("anno")).thenReturn("2015lol2016");
    when(request.getParameter("password")).thenReturn(unTirocinante.getPassword());
    when(request.getParameter("password2")).thenReturn(unTirocinante.getPassword());
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test05_PasswordErrata() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn(unTirocinante.getNome());
    when(request.getParameter("cognome")).thenReturn(unTirocinante.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn(unTirocinante.getMatricola());
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn("lol");
    when(request.getParameter("password2")).thenReturn(unTirocinante.getPassword());
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test06_ConfermaPasswordErrata() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn(unTirocinante.getNome());
    when(request.getParameter("cognome")).thenReturn(unTirocinante.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn("03121");
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn(unTirocinante.getPassword());
    when(request.getParameter("password2")).thenReturn("lmao");
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test07_NomeNULL() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn(null);
    when(request.getParameter("cognome")).thenReturn(unTirocinante.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn(unTirocinante.getMatricola());
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn(unTirocinante.getPassword());
    when(request.getParameter("password2")).thenReturn(unTirocinante.getPassword());
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test08_CognomeNULL() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn(unTirocinante.getNome());
    when(request.getParameter("cognome")).thenReturn(null);
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn(unTirocinante.getMatricola());
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn(unTirocinante.getPassword());
    when(request.getParameter("password2")).thenReturn(unTirocinante.getPassword());
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test09_matricolaNULL() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn(unTirocinante.getNome());
    when(request.getParameter("cognome")).thenReturn(unTirocinante.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn(null);
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn(unTirocinante.getPassword());
    when(request.getParameter("password2")).thenReturn(unTirocinante.getPassword());
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test10_AnnoIscrizioneNULL() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn(unTirocinante.getNome());
    when(request.getParameter("cognome")).thenReturn(unTirocinante.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn(unTirocinante.getMatricola());
    when(request.getParameter("anno")).thenReturn(null);
    when(request.getParameter("password")).thenReturn(unTirocinante.getPassword());
    when(request.getParameter("password2")).thenReturn(unTirocinante.getPassword());
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test11_PasswordNULL() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn(unTirocinante.getNome());
    when(request.getParameter("cognome")).thenReturn(unTirocinante.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn(unTirocinante.getMatricola());
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn(null);
    when(request.getParameter("password2")).thenReturn(unTirocinante.getPassword());
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test12_ConfermaPasswordNULL() throws ServletException, IOException {
    when(request.getParameter("nome")).thenReturn(unTirocinante.getNome());
    when(request.getParameter("cognome")).thenReturn(unTirocinante.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn("03121");
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn(unTirocinante.getPassword());
    when(request.getParameter("password2")).thenReturn(null);
    when(request.getRequestDispatcher("/registraTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }
  }
  
  @Test
  public void test13_SalvataggioErrato() throws Exception {
    when(request.getParameter("nome")).thenReturn(unTirocinante.getNome());
    when(request.getParameter("cognome")).thenReturn(unTirocinante.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante.getEmail());
    when(request.getParameter("matricola")).thenReturn(unTirocinante.getMatricola());
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn(unTirocinante.getPassword());
    when(request.getParameter("password2")).thenReturn(unTirocinante.getPassword());
    when(request.getRequestDispatcher("/paginaErrore.jsp?Errore nel salvare l'account.")).thenReturn(dispatcher);
    when(request.getSession()).thenReturn(sessione);
    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(Exception e) {
      fail();
    }
  }
  
  @Test
  public void test14_Successo() throws ServletException, IOException {
    when(request.getSession()).thenReturn(sessione);
    when(request.getParameter("nome")).thenReturn(unTirocinante2.getNome());
    when(request.getParameter("cognome")).thenReturn(unTirocinante2.getCognome());
    when(request.getParameter("email")).thenReturn(unTirocinante2.getEmail());
    when(request.getParameter("matricola")).thenReturn(unTirocinante2.getMatricola());
    when(request.getParameter("anno")).thenReturn("2015/2016");
    when(request.getParameter("password")).thenReturn(unTirocinante2.getPassword());
    when(request.getParameter("password2")).thenReturn(unTirocinante2.getPassword());
    when(request.getRequestDispatcher("/TirociniAttiviStoricoServlet")).thenReturn(dispatcher);

    try {
      new RegistraTirocinanteServlet().doPost(request, response);
    }catch(NullPointerException e) {
      fail();
    }  
    
  }
  
  @AfterClass
  public static void rimuovi() throws Exception {
    TirocinanteDao.rimuoviTirocinante(unTirocinante);
    TirocinanteDao.rimuoviTirocinante(unTirocinante2);
  }
}
