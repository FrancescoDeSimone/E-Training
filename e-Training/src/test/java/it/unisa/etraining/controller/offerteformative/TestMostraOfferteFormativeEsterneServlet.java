package it.unisa.etraining.controller.offerteformative;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

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

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.facade.OfferteFormativeFacade;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMostraOfferteFormativeEsterneServlet {

  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession sessione;
  @Mock
  RequestDispatcher dispatcher;
  @Mock
  Azienda azienda;
  @Mock
  OfferteFormativeFacade offerteFormativeFacade;
  @Mock
  ArrayList<OffertaFormativaTirocinioEsterno> listaOfferte;
  
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void Test01_SessioneNulla() {
    
    when(request.getSession()).thenReturn(sessione);
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

    try {
      new MostraOfferteFormativeEsterneServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  @Test
  public void Test02_ErroreDatabase() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(sessione.getAttribute("utente")).thenReturn(azienda);
    when(request.getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel connettersi al database.")).thenReturn(dispatcher);
    try {
      new MostraOfferteFormativeEsterneServlet().doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  @Test
  public void Test03_ErroreDatabase() throws Exception {
    when(request.getSession(false)).thenReturn(sessione);
    when(sessione.getAttribute("utente")).thenReturn(azienda);
    
    when(request.getRequestDispatcher("/mostraOfferteFormativeEsterne.jsp?aggiunta=1")).thenReturn(dispatcher);
    try {
      new MostraOfferteFormativeEsterneServlet().doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  
}
