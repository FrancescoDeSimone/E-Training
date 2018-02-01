package it.unisa.etraining.controller.profilo;

import static org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.mockito.Mockito.*;
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
public class TestRimuoviTirocinanteServlet {

  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession sessione;
  @Mock
  RequestDispatcher dispatcher;
  @Mock
  Tirocinante tirocinante;
  
  private static Tirocinante unTirocinante;

  @BeforeClass
  public static void init() throws Exception{
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    new DriverManagerConnectionPool(connessione, utente, password, driver);
    
    unTirocinante = new Tirocinante();
    unTirocinante.setEmail("d.abbondio104@studenti.unisa.it");
    unTirocinante.setPassword("Ciao");
    unTirocinante.setNome("Emanuele");
    unTirocinante.setCognome("Basso");
    unTirocinante.setAnnoIscrizione("2015/2016");
    unTirocinante.setMatricola("0512100000");
  }
  
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void Test01_SessioneNulla() {

    when(request.getSession(false)).thenReturn(null);
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

    try {
      new RimuoviTirocinanteServlet().doGet(request, response);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void Test02_ErroreRimozione() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(sessione.getAttribute("utente")).thenReturn(tirocinante);
    when(request.getRequestDispatcher("/paginaErrore.jsp?Errore nel rimuovere l'account.")).thenReturn(dispatcher);

    try {
      new RimuoviTirocinanteServlet().doGet(request, response);
    } catch (Exception e) {
     fail();
    }
  }
  
  @Test
  public void Test03_TirociniAttivi() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(sessione.getAttribute("utente")).thenReturn(tirocinante);
    when(request.getRequestDispatcher("/paginaErrore.jsp?Errore nel rimuovere l'account.")).thenReturn(dispatcher);
    try {
      new RimuoviTirocinanteServlet().doGet(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }

  
  @Test
  public void Test04_Rimozione() throws Exception {
    TirocinanteDao.salvaTirocinante(unTirocinante);
    when(request.getSession(false)).thenReturn(sessione);
    when(sessione.getAttribute("utente")).thenReturn(tirocinante);
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);
    try {
      new RimuoviTirocinanteServlet().doGet(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    verify(sessione).removeAttribute("nome");
    verify(sessione).removeAttribute("tipo");
    verify(sessione).removeAttribute("email");
    verify(sessione).invalidate();
    verify(request).setAttribute("accountEliminato","1");
    TirocinanteDao.rimuoviTirocinante(unTirocinante);
    assertTrue(true);
  }
  
  @Test
  public void Test05_TirociniAttivi() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(sessione.getAttribute("utente")).thenReturn(tirocinante);
    when(request.getRequestDispatcher("/rimuoviTirocinante.jsp")).thenReturn(dispatcher);
    try {
      new RimuoviTirocinanteServlet().doGet(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    verify(request).setAttribute("tirociniAttivi","1");
    assertTrue(true);
  }
}
