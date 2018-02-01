package it.unisa.etraining.controller.offerteformative;

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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestValutareOffertaFormativaTirocinioEsternoServlet {
  
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
      new ValutareOffertaFormativaTirocinioEsternoServlet().doGet(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test02_SceltaNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("scelta")).thenReturn(null);
    when(request.getParameter("id")).thenReturn("1");
    when(request.getRequestDispatcher("/mostraProposteOfferteFormativeEsterne.jsp?errore=1")).thenReturn(dispatcher);  
    try {
      new ValutareOffertaFormativaTirocinioEsternoServlet().doGet(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test03_IdNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("scelta")).thenReturn("accetta");
    when(request.getParameter("id")).thenReturn(null);
    when(request.getRequestDispatcher("/mostraProposteOfferteFormativeEsterne.jsp?errore=1")).thenReturn(dispatcher);  
    try {
      new ValutareOffertaFormativaTirocinioEsternoServlet().doGet(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test04_SceltaErrata() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("scelta")).thenReturn("oh no!");
    when(request.getParameter("id")).thenReturn("1");
    when(request.getRequestDispatcher("/mostraProposteOfferteFormativeEsterne.jsp?errore=1")).thenReturn(dispatcher);  
    try {
      new ValutareOffertaFormativaTirocinioEsternoServlet().doGet(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test04_IdErrato() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("scelta")).thenReturn("accetta");
    when(request.getParameter("id")).thenReturn("");
    when(request.getRequestDispatcher("/mostraProposteOfferteFormativeEsterne.jsp?errore=1")).thenReturn(dispatcher);  
    try {
      new ValutareOffertaFormativaTirocinioEsternoServlet().doGet(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test05_SceltaAccetta() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("scelta")).thenReturn("accetta");
    when(request.getParameter("id")).thenReturn("1");
    when(request.getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel connettersi al database.")).thenReturn(dispatcher);  
    try {
      new ValutareOffertaFormativaTirocinioEsternoServlet().doGet(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test06_SceltaRifiuta() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("scelta")).thenReturn("rifiuta");
    when(request.getParameter("id")).thenReturn("1");
    when(request.getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel connettersi al database.")).thenReturn(dispatcher);  
    try {
      new ValutareOffertaFormativaTirocinioEsternoServlet().doGet(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }

}
