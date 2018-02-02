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

import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestConvalidaAttivitàRegistroServlet {

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
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);      
    } catch(NullPointerException e) {
      assertTrue(false);
      return;
    }    
    assertTrue(true);
  }
  
  @Test
  public void test02_IdNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("id")).thenReturn(null);
    when(request.getParameter("scelta")).thenReturn("convalida");
    when(request.getParameter("email")).thenReturn("tutordidattico@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("motivazione");
    when(request.getParameter("attivitaSvolta")).thenReturn("attivita");
    when(request.getRequestDispatcher("/convalidaRegistriTutor.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test03_SceltaNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("scelta")).thenReturn(null);
    when(request.getParameter("email")).thenReturn("tutordidattico@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("motivazione");
    when(request.getParameter("attivitaSvolta")).thenReturn("attivita");
    when(request.getRequestDispatcher("/convalidaRegistriTutor.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test04_EmailNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("scelta")).thenReturn("convalida");
    when(request.getParameter("email")).thenReturn(null);
    when(request.getParameter("motivazione")).thenReturn("motivazione");
    when(request.getParameter("attivitaSvolta")).thenReturn("attivita");
    when(request.getRequestDispatcher("/convalidaRegistriTutor.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test05_AttivitaNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("scelta")).thenReturn("convalida");
    when(request.getParameter("email")).thenReturn("tutordidattico@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("motivazione");
    when(request.getParameter("attivitaSvolta")).thenReturn(null);
    when(request.getRequestDispatcher("/convalidaRegistriTutor.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test06_IdEmpty() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("id")).thenReturn("");
    when(request.getParameter("scelta")).thenReturn("convalida");
    when(request.getParameter("email")).thenReturn("tutordidattico@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("motivazione");
    when(request.getParameter("attivitaSvolta")).thenReturn("attivita");
    when(request.getRequestDispatcher("/convalidaRegistriTutor.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test07_SceltaEmpty() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("scelta")).thenReturn("");
    when(request.getParameter("email")).thenReturn("tutordidattico@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("motivazione");
    when(request.getParameter("attivitaSvolta")).thenReturn("attivita");
    when(request.getRequestDispatcher("/convalidaRegistriTutor.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test08_EmailEmpty() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("scelta")).thenReturn("convalida");
    when(request.getParameter("email")).thenReturn("");
    when(request.getParameter("motivazione")).thenReturn("motivazione");
    when(request.getParameter("attivitaSvolta")).thenReturn("attivita");
    when(request.getRequestDispatcher("/convalidaRegistriTutor.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test09_AttivitaEmpty() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("id")).thenReturn(null);
    when(request.getParameter("scelta")).thenReturn("convalida");
    when(request.getParameter("email")).thenReturn("tutordidattico@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("motivazione");
    when(request.getParameter("attivitaSvolta")).thenReturn("");
    when(request.getRequestDispatcher("/convalidaRegistriTutor.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test10_ConvalidaTutorDidattico() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(sessione.getAttribute("utente")).thenReturn(new TutorDidattico());
    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("scelta")).thenReturn("convalida");
    when(request.getParameter("email")).thenReturn("tutordidattico@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("motivazione");
    when(request.getParameter("attivitaSvolta")).thenReturn("attivita");
    when(request.getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel modificare l'attività.")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test11_ConvalidaTutorAziendale() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(sessione.getAttribute("utente")).thenReturn(new TutorAziendale());
    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("scelta")).thenReturn("convalida");
    when(request.getParameter("email")).thenReturn("tutoraziendale@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("motivazione");
    when(request.getParameter("attivitaSvolta")).thenReturn("attivita");
    when(request.getRequestDispatcher("/VisualizzaRegistriTutorServlet")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test12_NonConvalidaMotivazioneNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("scelta")).thenReturn("nonConvalida");
    when(request.getParameter("email")).thenReturn("tutoraziendale@unisa.it");
    when(request.getParameter("motivazione")).thenReturn(null);
    when(request.getParameter("attivitaSvolta")).thenReturn("attivita");
    when(request.getRequestDispatcher("/convalidaRegistriTutor.jsp?errore=1")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }
  
  @Test
  public void test13_NonConvalidaMotivazioneNonNull() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("id")).thenReturn("1");
    when(request.getParameter("scelta")).thenReturn("nonConvalida");
    when(request.getParameter("email")).thenReturn("tutoraziendale@unisa.it");
    when(request.getParameter("motivazione")).thenReturn("motivazione");
    when(request.getParameter("attivitaSvolta")).thenReturn("attivita");
    when(request.getRequestDispatcher("/VisualizzaRegistriTutorServlet")).thenReturn(dispatcher);
    try {
      new ConvalidaAttivitaRegistroServlet().doGet(request, response);
    } catch (NullPointerException e) {
      assertTrue(false);
    }
    assertTrue(true);
    
  }

}
