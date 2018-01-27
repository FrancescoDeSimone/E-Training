package it.unisa.etraining.model.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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

/**
 * 
 * @author Basso Emanuele
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTirocinioDao {

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
    String[] insegnamenti = {"IS","SO"};
    unTutorDidattico.setInsegnamenti(insegnamenti);
    String[] campiInteresse = {"Compilatori lazy","Database coordinati misti"};
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
    String[] falicitazioni = {"pasti","disabili"};
    unOfferta.setFacilitazioni(falicitazioni);
  
    OffertaFormativaTirocinioEsternoDao.salvaOffertaFormativaTirocinioEsterno(unOfferta);
    
    unTirocinio = new Tirocinio();
    unTirocinio.setOfferta(unOfferta);
    unTirocinio.setTirocinante(unTirocinante);
    unTirocinio.setCfu(6);
    unTirocinio.setOreRimanenti(120);
    unTirocinio.setStatus(Tirocinio.VALUTAZIONE_DOCENTE);
    String[] obiettivi = {"Aiutare l'empowerment dell'individuo","Migliorare i database misti"};
    unTirocinio.setObiettivi(obiettivi);
  }
  
  @Test
  public void test1_InserimentoTirocinio(){
    boolean risultato = TirocinioDao.salvaTirocinio(unTirocinio);
    
    assertTrue(risultato);
  }
  
  @Test
  public void test2_RicercaTirocinio(){
    Tirocinio tirocinio = new Tirocinio();
    tirocinio.setTirocinante(unTirocinante);
    
    OffertaFormativaTirocinioEsternoDao.ricercaOffertaFormativaTirocinioEsterno(unOfferta);
    tirocinio.setOfferta(unOfferta);
    
    TirocinioDao.ricercaTirocinio(tirocinio);
    
    assertEquals(unTirocinio.getCfu(),tirocinio.getCfu());
    assertEquals(unTirocinio.getObiettivi()[0],tirocinio.getObiettivi()[0]);
  }
  
  @Test
  public void test3_RicercaTirociniInviati(){       
    ArrayList<Tirocinio> listaTirociniInviati = new ArrayList<>();
    TirocinioDao.ricercaTirociniInviati(listaTirociniInviati,unTirocinante);
    
    assertEquals(listaTirociniInviati.size(),1);
  }
  
  @Test
  public void test4_RicercaRichiesteTirocinio(){       
    ArrayList<Tirocinio> listaRichiesteTirocinio = new ArrayList<>();
    TirocinioDao.ricercaRichiesteTirocinio(listaRichiesteTirocinio,unTutorDidattico);
    
    assertEquals(listaRichiesteTirocinio.size(),1);
  }
  
  @Test
  public void test5_AggiornaStatusTirocinio(){       
    unTirocinio.setStatus(Tirocinio.IN_CORSO);
    boolean risultato = TirocinioDao.aggiornaStatusTirocinio(unTirocinio);
    
    assertTrue(risultato);
  }
  
  @Test
  public void test6_AggiornaOreTirocinio(){       
    unTirocinio.setOreRimanenti(110);
    boolean risultato = TirocinioDao.aggiornOreTirocinio(unTirocinio);
    
    assertTrue(risultato);
    
    Tirocinio tirocinio = new Tirocinio();
    tirocinio.setTirocinante(unTirocinante);
    tirocinio.setOfferta(unOfferta);
    
    TirocinioDao.ricercaTirocinio(tirocinio);
  
    assertEquals(110,tirocinio.getOreRimanenti());
  }
  
  @Test
  public void test7_RicercaTirociniAttivi(){       
    ArrayList<Tirocinio> listaTirociniAttivi = new ArrayList<>();
    TirocinioDao.ricercaTirociniAttivi(listaTirociniAttivi,unTirocinante);
    
    assertEquals(listaTirociniAttivi.size(),1);
  }
  
  @Test
  public void test8_RicercaTirociniStorico(){
    unTirocinio.setStatus(Tirocinio.TERMINATO);
    TirocinioDao.aggiornaStatusTirocinio(unTirocinio);
    
    ArrayList<Tirocinio> listaTirociniStorico = new ArrayList<>();
    TirocinioDao.ricercaTirociniStorico(listaTirociniStorico,unTirocinante);
    
    assertEquals(listaTirociniStorico.size(),1);
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
  
  private static void eliminaTirocinio(){
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
  
  private static void eliminaObiettivi(){
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
