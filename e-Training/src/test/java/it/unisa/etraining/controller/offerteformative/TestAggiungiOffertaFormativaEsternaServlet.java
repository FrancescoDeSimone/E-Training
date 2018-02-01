package it.unisa.etraining.controller.offerteformative;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;

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
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.AziendaDao;
import it.unisa.etraining.model.dao.TutorAziendaleDao;
import it.unisa.etraining.model.dao.TutorDidatticoDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAggiungiOffertaFormativaEsternaServlet {

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

  private static OffertaFormativaTirocinioEsterno unOfferta;
  private static TutorAziendale unTutorAziendale;
  private static Azienda unAzienda;
  private static TutorDidattico unTutorDidattico;

  @BeforeClass
  public static void init() throws Exception {
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
    String[] insegnamenti = { "IS", "SO" };
    unTutorDidattico.setInsegnamenti(insegnamenti);
    String[] campiInteresse = { "Compilatori lazy", "Database coordinati misti" };
    unTutorDidattico.setCampiInteresse(campiInteresse);

    TutorDidatticoDao.salvaTutorDidattico(unTutorDidattico);

    unAzienda = new Azienda();
    unAzienda.setEmail("2Streaming@gmail.com");
    unAzienda.setPassword("Ciao");
    unAzienda.setNome("2Stream");
    unAzienda.setPartitaIva("GHPL59HG51D");
    unAzienda.setSede("Via Roma, 6");
    unAzienda.setCitta("Milano");

    AziendaDao.salvaAzienda(unAzienda);

    unTutorAziendale = new TutorAziendale();
    unTutorAziendale.setEmail("piermenti@gmail.com");
    unTutorAziendale.setAzienda(unAzienda);
    unTutorAziendale.setPassword("Ciao");
    unTutorAziendale.setNome("Emanuele");
    unTutorAziendale.setCognome("Basso");
    unTutorAziendale.setCodiceFiscale("BSSMNLXXX475UOI6");
    unTutorAziendale.setSettoreLavoro("Database distribuiti");

    TutorAziendaleDao.salvaTutorAziendale(unTutorAziendale);

    GregorianCalendar dataInizio = new GregorianCalendar();
    GregorianCalendar dataFine = new GregorianCalendar();
    dataFine.add(GregorianCalendar.MONTH, 3);

    unOfferta = new OffertaFormativaTirocinioEsterno();
    unOfferta.setInizioTirocinio(dataInizio);
    unOfferta.setFineTirocinio(dataFine);
    unOfferta.setTema("Testare le classi fatte");
    unOfferta.setStatus(OffertaFormativaTirocinioEsterno.ATTIVA);
    unOfferta.setTutorAziendale(unTutorAziendale);
    unOfferta.setTutorDidattico(unTutorDidattico);
    unOfferta.setValidita(true);
    unOfferta.setAzienda(unAzienda);

    String[] falicitazioni = { "pasti", "disabili" };
    unOfferta.setFacilitazioni(falicitazioni);
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void Test01_SessioneNulla() {

    when(request.getSession()).thenReturn(null);
    when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }

  @Test
  public void Test02_temaErrato() {

    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("label0")).thenReturn("asdasd as");
    when(request.getParameter("label1")).thenReturn("asdasd as");
    when(request.getParameter("label2")).thenReturn("asdasd as");
    when(request.getParameter("label3")).thenReturn("asdasd as");
    when(request.getParameter("label4")).thenReturn("asdasd as");
    when(request.getParameter("tema")).thenReturn("   ");
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn("2018-01-01");
    when(request.getParameter("tutorAccademico")).thenReturn("gianni");
    when(request.getParameter("tutorAziendale")).thenReturn("gianni");
    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  @Test
  public void Test03_DataInizioErrata() {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("label0")).thenReturn("asdasd");
    when(request.getParameter("label1")).thenReturn("asdasd");
    when(request.getParameter("label2")).thenReturn("asdasd");
    when(request.getParameter("label3")).thenReturn("asdasd");
    when(request.getParameter("label4")).thenReturn("asdasd");
    when(request.getParameter("tema")).thenReturn("asdasd");
    when(request.getParameter("dataInizio")).thenReturn("  ");
    when(request.getParameter("dataFine")).thenReturn("2018-01-01");
    when(request.getParameter("tutorAccademico")).thenReturn("gianni");
    when(request.getParameter("tutorAziendale")).thenReturn("gianni");
when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  @Test
  public void Test04_DataFineErrata() {

    when(request.getParameter("label0")).thenReturn("aasdasd");
    when(request.getParameter("label1")).thenReturn("aasdasd");
    when(request.getParameter("label2")).thenReturn("aasdasd");
    when(request.getParameter("label3")).thenReturn("aasdasd");
    when(request.getParameter("label4")).thenReturn("aasdasd");
    when(request.getParameter("tema")).thenReturn("aasdasd");
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn("   ");
    when(request.getParameter("tutorAccademico")).thenReturn("asdasdasd");
    when(request.getParameter("tutorAziendale")).thenReturn("asdasdasd");
    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  @Test
  public void Test05_DataFineErrata() {
    when(request.getParameter("label0")).thenReturn("asdasdasd");
    when(request.getParameter("label1")).thenReturn("asdasdasd");
    when(request.getParameter("label2")).thenReturn("asdasdasd");
    when(request.getParameter("label3")).thenReturn("asdasdasd");
    when(request.getParameter("label4")).thenReturn("asdasdasd");
    when(request.getParameter("tema")).thenReturn("asdasdasd");
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn("   ");
    when(request.getParameter("tutorAccademico")).thenReturn("asdasdasd");
    when(request.getParameter("tutorAziendale")).thenReturn("asdasdasd");
    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  @Test
  public void Test06_TutorAziendaleErrato() {
    when(request.getParameter("label0")).thenReturn("asdasdasd");
    when(request.getParameter("label1")).thenReturn("asdasdasd");
    when(request.getParameter("label2")).thenReturn("asdasdasd");
    when(request.getParameter("label3")).thenReturn("asdasdasd");
    when(request.getParameter("label4")).thenReturn("asdasdasd");
    when(request.getParameter("tema")).thenReturn("asdasdasd");
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn("2018-02-01");
    when(request.getParameter("tutorAccademico")).thenReturn("asdasdasd");
    when(request.getParameter("tutorAziendale")).thenReturn("  ");
    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  @Test
  public void Test07_TutorAccademicoErrato() {
    when(request.getParameter("label0")).thenReturn("asdasdasd");
    when(request.getParameter("label1")).thenReturn("asdasdasd");
    when(request.getParameter("label2")).thenReturn("asdasdasd");
    when(request.getParameter("label3")).thenReturn("asdasdasd");
    when(request.getParameter("label4")).thenReturn("asdasdasd");
    when(request.getParameter("tema")).thenReturn("asdasdasd");
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn("2018-01-01");
    when(request.getParameter("tutorAccademico")).thenReturn("  ");
    when(request.getParameter("tutorAziendale")).thenReturn(unTutorAziendale.getNome());
    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  @Test
  public void Test08_FacilitazioniErrate() {
    when(request.getParameter("label0")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label1")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label2")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label3")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label4")).thenReturn("");
    when(request.getParameter("tema")).thenReturn(unOfferta.getTema());
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn("2018-01-01");
    when(request.getParameter("tutorAccademico")).thenReturn(unTutorAziendale.getNome());
    when(request.getParameter("tutorAziendale")).thenReturn(unTutorAziendale.getNome());
    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }

  @Test
  public void Test09_TemaNull() {
    when(request.getParameter("label0")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label1")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label2")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label3")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label4")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("tema")).thenReturn(null);
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn("2018-01-01");
    when(request.getParameter("tutorAccademico")).thenReturn(unTutorAziendale.getNome());
    when(request.getParameter("tutorAziendale")).thenReturn(unTutorAziendale.getNome());
    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }

  @Test
  public void Test10_DataInzioNull() {

    when(request.getParameter("label0")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label1")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label2")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label3")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label4")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("tema")).thenReturn(unOfferta.getTema());
    when(request.getParameter("dataInizio")).thenReturn(null);
    when(request.getParameter("dataFine")).thenReturn("2018-01-01");
    when(request.getParameter("tutorAccademico")).thenReturn(unTutorAziendale.getNome());
    when(request.getParameter("tutorAziendale")).thenReturn(unTutorAziendale.getNome());
    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  @Test
  public void Test11_DataFineNull() {
    when(request.getParameter("label0")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label1")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label2")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label3")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label4")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("tema")).thenReturn(unOfferta.getTema());
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn(null);
    when(request.getParameter("tutorAccademico")).thenReturn(unTutorAziendale.getNome());
    when(request.getParameter("tutorAziendale")).thenReturn(unTutorAziendale.getNome());
    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  
  @Test
  public void Test12_TutorAziendaleNull() {
    when(request.getParameter("label0")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label1")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label2")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label3")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label4")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("tema")).thenReturn(unOfferta.getTema());
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn("2018-01-01");
    when(request.getParameter("tutorAccademico")).thenReturn(unTutorAziendale.getNome());
    when(request.getParameter("tutorAziendale")).thenReturn(null);
    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  @Test
  public void Test13_TutorDidatticoNull() {
    when(request.getParameter("label0")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label1")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label2")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label3")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("label4")).thenReturn(unOfferta.getFacilitazioni()[0]);
    when(request.getParameter("tema")).thenReturn(unOfferta.getTema());
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn("2018-01-01");
    when(request.getParameter("tutorAccademico")).thenReturn(null);
    when(request.getParameter("tutorAziendale")).thenReturn(unTutorAziendale.getNome());
    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }
  
  @Test
  public void Test13_DateInvertite() {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("label0")).thenReturn("facasdas");
    when(request.getParameter("label1")).thenReturn("ssadasdsad");
    when(request.getParameter("label2")).thenReturn("asfsafasdsad");
    when(request.getParameter("label3")).thenReturn("sdfasdfasdsad");
    when(request.getParameter("label4")).thenReturn("sdfsadasdas");
    when(request.getParameter("tema")).thenReturn("Ciao a tutti");
    when(request.getParameter("dataInizio")).thenReturn("2018-02-01");
    when(request.getParameter("dataFine")).thenReturn("2018-01-01");
    when(request.getParameter("tutorAccademico")).thenReturn("gianni");
    when(request.getParameter("tutorAziendale")).thenReturn("bello");

    when(request.getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1")).thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      assertTrue(false);
      return;
    }
    assertTrue(true);
  }

  @Test
  public void Test14_ErroreSalvataggio() {
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("label0")).thenReturn("facasdas");
    when(request.getParameter("label1")).thenReturn("ssadasdsad");
    when(request.getParameter("label2")).thenReturn("asfsafasdsad");
    when(request.getParameter("label3")).thenReturn("sdfasdfasdsad");
    when(request.getParameter("label4")).thenReturn("sdfsadasdas");
    when(request.getParameter("tema")).thenReturn("Ciao a tutti");
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn("2018-02-01");
    when(request.getParameter("tutorAccademico")).thenReturn("gianni");
    when(request.getParameter("tutorAziendale")).thenReturn("bello");
    when(sessione.getAttribute("utente")).thenReturn(azienda);
    when(request.getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel salvare l'offerta formativa."))
        .thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      assertTrue(false);
      return;
    }

    assertTrue(true);
  }
  
  @Test
  public void Test15_SalvataggioOffertaFormativa(){
    when(request.getSession(false)).thenReturn(sessione);
    when(request.getParameter("label0")).thenReturn("facasdas");
    when(request.getParameter("label1")).thenReturn("ssadasdsad");
    when(request.getParameter("label2")).thenReturn("asfsafasdsad");
    when(request.getParameter("label3")).thenReturn("sdfasdfasdsad");
    when(request.getParameter("label4")).thenReturn("sdfsadasdas");
    when(request.getParameter("tema")).thenReturn("Ciao a tutti");
    when(request.getParameter("dataInizio")).thenReturn("2018-01-01");
    when(request.getParameter("dataFine")).thenReturn("2018-02-01");
    when(request.getParameter("tutorAccademico")).thenReturn("gianni");
    when(request.getParameter("tutorAziendale")).thenReturn("bello");
    when(sessione.getAttribute("utente")).thenReturn(azienda);
    when(request.getRequestDispatcher("/MostraOfferteFormativeEsterneServlet"))
        .thenReturn(dispatcher);

    try {
      new AggiungiOffertaFormativaEsternaServlet().doPost(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

  }
  
  @AfterClass
  public static void close(){
    TutorAziendaleDao.rimuoviTutorAziendale(unTutorAziendale);
    TutorDidatticoDao.rimuoviTutorDidattico(unTutorDidattico);
    AziendaDao.rimuoviAzienda(unAzienda);
    
    // Rimuoviamo l'OffertaFormativaTirocinioEsterno e le Facilitazioni dal database 
    // con un nostro metodo nel test perchè non è offerta questa funzionalità dalle 
    // corrispondenti calssi DAO.
    
    eliminaOfferta();
    eliminaFacilitazioni();
  }
  
  private static void eliminaOfferta(){
    Connection con = null;
    String RIMUOVI_OFFERTA = "DELETE FROM eTraining.OffertaFormativaTirocinioEsterno ";
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_OFFERTA);
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      // Solo per Testing
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        // Solo per Testing
      }
    }
  }
  private static void eliminaFacilitazioni(){
    Connection con = null;
    String RIMUOVI_OFFERTA = "DELETE FROM eTraining.Facilitazione ";
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_OFFERTA);
      
      ps.executeUpdate();
      
      ps.close();
    } catch (SQLException e) {
      // Solo per Testing
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        // Solo per Testing
      }
    }
  }
}
