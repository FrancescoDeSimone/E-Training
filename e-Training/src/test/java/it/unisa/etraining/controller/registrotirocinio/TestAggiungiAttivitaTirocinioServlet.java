package it.unisa.etraining.controller.registrotirocinio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.unisa.etraining.model.bean.Tirocinante;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAggiungiAttivitaTirocinioServlet {

  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession sessione;
  @Mock
  RequestDispatcher dispatcher;
  
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void test01_SenzaSessione() throws Exception {
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);    
    try {
      new AggiungiAttivitaTirocinioServlet().doPost(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test02_AttivitaNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("attivita")).thenReturn(null);
    when(request.getParameter("dataInizio")).thenReturn("2016-01-01");
    when(request.getParameter("dataFine")).thenReturn("2016-02-02");
    when(request.getParameter("ore")).thenReturn("10");
    when(request.getParameter("idOfferta")).thenReturn("1");
    when(request.getRequestDispatcher("/compilaRegistroTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new AggiungiAttivitaTirocinioServlet().doPost(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test03_DataInizioNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("attivita")).thenReturn("attivita");
    when(request.getParameter("dataInizio")).thenReturn(null);
    when(request.getParameter("dataFine")).thenReturn("2016-02-02");
    when(request.getParameter("ore")).thenReturn("10");
    when(request.getParameter("idOfferta")).thenReturn("1");
    when(request.getRequestDispatcher("/compilaRegistroTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new AggiungiAttivitaTirocinioServlet().doPost(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test04_DataFineNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("attivita")).thenReturn("attivita");
    when(request.getParameter("dataInizio")).thenReturn("2016-01-01");
    when(request.getParameter("dataFine")).thenReturn(null);
    when(request.getParameter("ore")).thenReturn("10");
    when(request.getParameter("idOfferta")).thenReturn("1");
    when(request.getRequestDispatcher("/compilaRegistroTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new AggiungiAttivitaTirocinioServlet().doPost(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test05_OreNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("attivita")).thenReturn("attivita");
    when(request.getParameter("dataInizio")).thenReturn("2016-01-01");
    when(request.getParameter("dataFine")).thenReturn("2016-02-02");
    when(request.getParameter("ore")).thenReturn(null);
    when(request.getParameter("idOfferta")).thenReturn("1");
    when(request.getRequestDispatcher("/compilaRegistroTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new AggiungiAttivitaTirocinioServlet().doPost(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test06_AttivitaErrato() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("attivita")).thenReturn(" attività");
    when(request.getParameter("dataInizio")).thenReturn("2016-01-01");
    when(request.getParameter("dataFine")).thenReturn("2016-02-02");
    when(request.getParameter("ore")).thenReturn("10");
    when(request.getParameter("idOfferta")).thenReturn("1");
    when(request.getRequestDispatcher("/compilaRegistroTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new AggiungiAttivitaTirocinioServlet().doPost(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test07_DataInizioErrata() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("attivita")).thenReturn("attivita");
    when(request.getParameter("dataInizio")).thenReturn("2016-001-01");
    when(request.getParameter("dataFine")).thenReturn("2016-02-02");
    when(request.getParameter("ore")).thenReturn("10");
    when(request.getParameter("idOfferta")).thenReturn("1");
    when(request.getRequestDispatcher("/compilaRegistroTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new AggiungiAttivitaTirocinioServlet().doPost(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test08_DataFineErrata() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("attivita")).thenReturn("attivita");
    when(request.getParameter("dataInizio")).thenReturn("2016-01-01");
    when(request.getParameter("dataFine")).thenReturn("2016-002-02");
    when(request.getParameter("ore")).thenReturn("10");
    when(request.getParameter("idOfferta")).thenReturn("1");
    when(request.getRequestDispatcher("/compilaRegistroTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new AggiungiAttivitaTirocinioServlet().doPost(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test09_OreErrate() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("attivita")).thenReturn("attivita");
    when(request.getParameter("dataInizio")).thenReturn("2016-01-01");
    when(request.getParameter("dataFine")).thenReturn("2016-02-02");
    when(request.getParameter("ore")).thenReturn("999");
    when(request.getParameter("idOfferta")).thenReturn("1");
    when(request.getRequestDispatcher("/compilaRegistroTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new AggiungiAttivitaTirocinioServlet().doPost(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test10_DateOrdineErrato() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("attivita")).thenReturn("attivita");
    when(request.getParameter("dataInizio")).thenReturn("2016-02-02");
    when(request.getParameter("dataFine")).thenReturn("2016-01-01");
    when(request.getParameter("ore")).thenReturn("10");
    when(request.getParameter("idOfferta")).thenReturn("1");
    when(request.getRequestDispatcher("/compilaRegistroTirocinante.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new AggiungiAttivitaTirocinioServlet().doPost(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test11_Successo() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("attivita")).thenReturn("attivita");
    when(request.getParameter("dataInizio")).thenReturn("2016-01-01");
    when(request.getParameter("dataFine")).thenReturn("2016-02-02");
    when(request.getParameter("ore")).thenReturn("10");
    when(request.getParameter("idOfferta")).thenReturn("1");
    when(request.getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel salvare l'attività.")).thenReturn(dispatcher);
    when(sessione.getAttribute("utente")).thenReturn(new Tirocinante());
    try {
      new AggiungiAttivitaTirocinioServlet().doPost(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }

}
