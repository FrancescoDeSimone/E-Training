package it.unisa.etraining.controller.tirocinio;

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

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.AziendaDao;

/*
 * @author Flavio Esposito
*/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEffettuareRichiestaTirocinioServlet {

  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession sessione;
  @Mock
  RequestDispatcher dispatcher;

  private static Azienda unAzienda;


  @BeforeClass
  public static void init() throws Exception {
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
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void test01_SessioneNull() throws ServletException, IOException {
    when(request.getSession(false)).thenReturn(null);
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

    try {
      new EffettuareRichiestaTirocinioServlet().doGet(request, response);
    } catch (NullPointerException e) {
      fail();
    }

  }

  @Test
  public void test02_SessioneNotNull() throws ServletException, IOException {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getRequestDispatcher("/effettuaRichiestaTirocinio.jsp")).thenReturn(dispatcher);

    try {
      new EffettuareRichiestaTirocinioServlet().doGet(request, response);
    } catch (NullPointerException e) {
      fail();
    }

  }

  @AfterClass
  public static void close() {

    AziendaDao.rimuoviAzienda(unAzienda);
  }

}
