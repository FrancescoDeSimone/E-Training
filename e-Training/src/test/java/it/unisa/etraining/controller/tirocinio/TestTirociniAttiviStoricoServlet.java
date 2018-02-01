package it.unisa.etraining.controller.tirocinio;

import static org.junit.Assert.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;

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
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.AziendaDao;
import it.unisa.etraining.model.dao.OffertaFormativaTirocinioEsternoDao;
import it.unisa.etraining.model.dao.TirocinanteDao;
import it.unisa.etraining.model.dao.TirocinioDao;
import it.unisa.etraining.model.dao.TutorAziendaleDao;
import it.unisa.etraining.model.dao.TutorDidatticoDao;

/*
 * @author Flavio Esposito
*/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTirociniAttiviStoricoServlet {

  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession sessione;
  @Mock
  RequestDispatcher dispatcher;

  private static Tirocinio unTirocinio;
  private static Tirocinante unTirocinante;
  private static TutorDidattico unTutorDidattico;
  private static Azienda unAzienda;
  private static TutorAziendale unTutorAziendale;
  private static OffertaFormativaTirocinioEsterno unOfferta;

  @BeforeClass
  public static void init() throws Exception {
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";

    new DriverManagerConnectionPool(connessione, utente, password, driver);

    unTirocinante = new Tirocinante();
    unTirocinante.setEmail("bassoemanuele1704@gmail.com");
    unTirocinante.setPassword("Ciao");
    unTirocinante.setNome("Emanuele");
    unTirocinante.setCognome("Basso");
    unTirocinante.setAnnoIscrizione("2015/2016");
    unTirocinante.setMatricola("0512100000");

    TirocinanteDao.salvaTirocinante(unTirocinante);

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

    OffertaFormativaTirocinioEsternoDao.salvaOffertaFormativaTirocinioEsterno(unOfferta);

    unTirocinio = new Tirocinio();
    unTirocinio.setOfferta(unOfferta);
    unTirocinio.setTirocinante(unTirocinante);
    unTirocinio.setCfu(6);
    unTirocinio.setOreRimanenti(120);
    unTirocinio.setStatus(Tirocinio.VALUTAZIONE_DOCENTE);
    String[] obiettivi = { "Aiutare l'empowerment dell'individuo", "Migliorare i database misti" };
    unTirocinio.setObiettivi(obiettivi);

    TirocinioDao.salvaTirocinio(unTirocinio);
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
      new TirociniAttiviStoricoServlet().doGet(request, response);
    } catch (NullPointerException e) {
      fail();
    }

  }

  @Test
  public void test02_SessioneNotNull() throws ServletException, IOException {
    when(request.getSession(false)).thenReturn(sessione);
    when(sessione.getAttribute("utente")).thenReturn(unTirocinante);
    when(request.getRequestDispatcher("/tirociniAttiviStorico.jsp"))
        .thenReturn(dispatcher);

    try {
      new TirociniAttiviStoricoServlet().doGet(request, response);
    } catch (NullPointerException e) {
      fail();
    }

  }
  
  @Test
  public void test03_AttributeRegistrazione() throws ServletException, IOException {
    when(request.getSession(false)).thenReturn(sessione);
    when(sessione.getAttribute("utente")).thenReturn(unTirocinante);
    when(request.getRequestDispatcher("/tirociniAttiviStorico.jsp?registrazione=1"))
        .thenReturn(dispatcher);
    when(request.getAttribute("registrazione")).thenReturn("1");

    
    try {
      new TirociniAttiviStoricoServlet().doGet(request, response);
    } catch (NullPointerException e) {
      fail();
    }

  }
  
  @Test
  public void test04_AttributeRichiestaTirocinio() throws ServletException, IOException {
    when(request.getSession(false)).thenReturn(sessione);
    when(sessione.getAttribute("utente")).thenReturn(unTirocinante);
    when(request.getRequestDispatcher("/tirociniAttiviStorico.jsp?richiestaTirocinio=1"))
        .thenReturn(dispatcher);
    when(request.getAttribute("richiestaTirocinio")).thenReturn("1");

    try {
      new TirociniAttiviStoricoServlet().doGet(request, response);
    } catch (NullPointerException e) {
      fail();
    }

  }

  @AfterClass
  public static void close() {
    TirocinanteDao.rimuoviTirocinante(unTirocinante);
    TutorDidatticoDao.rimuoviTutorDidattico(unTutorDidattico);
    AziendaDao.rimuoviAzienda(unAzienda);
    TutorAziendaleDao.rimuoviTutorAziendale(unTutorAziendale);

    // Rimuoviamo l'OffertaFormativaTirocinioEsterno, le Facilitazioni, il Tirocinio e
    // gli Obiettivi dal database con un nostro metodo nel test perchè non è offerta
    // questa funzionalità dalle corrispondenti calssi DAO.

    eliminaOfferta();
    eliminaFacilitazioni();
    eliminaTirocinio();
    eliminaObiettivi();
  }

  private static void eliminaOfferta() {
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

  private static void eliminaFacilitazioni() {
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

  private static void eliminaTirocinio() {
    Connection con = null;
    String RIMUOVI_TIROCINIO = "DELETE FROM eTraining.Tirocinio ";

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(RIMUOVI_TIROCINIO);

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

  private static void eliminaObiettivi() {
    Connection con = null;
    String RIMUOVI_OBIETTIVO = "DELETE FROM eTraining.Obiettivo ";

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(RIMUOVI_OBIETTIVO);

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
