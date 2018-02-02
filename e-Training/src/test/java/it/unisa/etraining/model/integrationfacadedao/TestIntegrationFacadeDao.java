package it.unisa.etraining.model.integrationfacadedao;

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

import it.unisa.etraining.model.bean.AttivitaRegistro;
import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.bean.TitolareAzienda;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.AziendaDao;
import it.unisa.etraining.model.dao.TitolareAziendaDao;
import it.unisa.etraining.model.dao.TutorAziendaleDao;
import it.unisa.etraining.model.dao.TutorDidatticoDao;
import it.unisa.etraining.model.facade.AutenticazioneFacade;
import it.unisa.etraining.model.facade.OfferteFormativeFacade;
import it.unisa.etraining.model.facade.ProfiloFacade;
import it.unisa.etraining.model.facade.RegistroTirocinioFacade;
import it.unisa.etraining.model.facade.TirocinioFacade;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestIntegrationFacadeDao {

  private static AutenticazioneFacade autenticazioneFacade;
  private static OfferteFormativeFacade offerteFormativeFacade;
  private static ProfiloFacade profiloFacade;
  private static RegistroTirocinioFacade registroTirocinioFacade;
  private static TirocinioFacade tirocinioFacade;
  
  private static Tirocinante tirocinante;
  private static TutorDidattico tutorDidattico;
  private static TutorAziendale tutorAziendale;
  private static TitolareAzienda titolareAzienda;
  private static Azienda azienda;
  private static OffertaFormativaTirocinioEsterno offertaFormativaTirocinioEsterno;
  private static Tirocinio tirocinio;
  private static AttivitaRegistro attivitaRegistro;
  
  @BeforeClass
  public static void init() throws Exception{
    String connessione = "jdbc:mysql://localhost:3306/eTraining";
    String utente = "root";
    String password = "root";
    String driver = "com.mysql.jdbc.Driver";
    
    new DriverManagerConnectionPool(connessione, utente, password, driver);
    
    autenticazioneFacade = new AutenticazioneFacade();
    offerteFormativeFacade = new OfferteFormativeFacade();
    profiloFacade = new ProfiloFacade();
    registroTirocinioFacade = new RegistroTirocinioFacade();
    tirocinioFacade = new TirocinioFacade();
    
    tirocinante = new Tirocinante();
    tirocinante.setEmail("f.esposito104@studenti.unisa.it");
    tirocinante.setPassword("password");
    tirocinante.setNome("Flavio");
    tirocinante.setCognome("Esposito");
    tirocinante.setMatricola("0512103467");
    tirocinante.setAnnoIscrizione("2015/2016");
    
    tutorDidattico = new TutorDidattico();
    tutorDidattico.setCodiceFiscale("0123456789548623");
    tutorDidattico.setCognome("Ciano");
    tutorDidattico.setNome("Gianni");
    tutorDidattico.setDisponibilita(true);
    tutorDidattico.setEmail("a.cataldi@unisa.it");
    tutorDidattico.setPassword("password");
    String[] campiInteresse = {"Grafica avanzata"};
    tutorDidattico.setCampiInteresse(campiInteresse);
    String[] insegnamenti = {"Grafica e Interattiva"};
    tutorDidattico.setInsegnamenti(insegnamenti);
    
    azienda = new Azienda();
    azienda.setEmail("aldeandra.Garofalo@gmail.com");
    azienda.setPassword("password");
    azienda.setCitta("Napoli");
    azienda.setNome("Lete Empire");
    azienda.setPartitaIva("12345678901");
    azienda.setSede("Via Roma, 4");
    
    titolareAzienda = new TitolareAzienda();
    titolareAzienda.setCodiceFiscale("1234567890154879");
    titolareAzienda.setNome("Luigi");
    titolareAzienda.setCognome("Verdi");
    titolareAzienda.setAzienda(azienda);
    
    tutorAziendale = new TutorAziendale();
    tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
    tutorAziendale.setPassword("password");
    tutorAziendale.setAzienda(azienda);
    tutorAziendale.setCodiceFiscale("0123456789145863");
    tutorAziendale.setNome("Antonio");
    tutorAziendale.setCognome("Esposito");
    tutorAziendale.setSettoreLavoro("Mobile graphic");
    
    offertaFormativaTirocinioEsterno = new OffertaFormativaTirocinioEsterno();
    offertaFormativaTirocinioEsterno.setAzienda(azienda);
    offertaFormativaTirocinioEsterno.setInizioTirocinio(new GregorianCalendar(2016,01,15));
    offertaFormativaTirocinioEsterno.setFineTirocinio(new GregorianCalendar(2016,02,15));
    offertaFormativaTirocinioEsterno.setStatus(OffertaFormativaTirocinioEsterno.DA_VALUTARE);
    offertaFormativaTirocinioEsterno.setTema("Grafica");
    offertaFormativaTirocinioEsterno.setValidita(false);
    offertaFormativaTirocinioEsterno.setTutorAziendale(tutorAziendale);
    offertaFormativaTirocinioEsterno.setTutorDidattico(tutorDidattico);
    
    attivitaRegistro = new AttivitaRegistro();
    attivitaRegistro.setAttivitaSvolta("Usato Katalon");
    attivitaRegistro.setConvalida(AttivitaRegistro.IN_CONVALIDA);
    attivitaRegistro.setInizio(new GregorianCalendar(2016,02,20));
    attivitaRegistro.setFine(new GregorianCalendar(2016,02,05));
    attivitaRegistro.setOreSvolte(150);
  
    TutorDidatticoDao.salvaTutorDidattico(tutorDidattico);
    AziendaDao.salvaAzienda(azienda);
    TitolareAziendaDao.salvaTitolareAzienda(titolareAzienda);
    TutorAziendaleDao.salvaTutorAziendale(tutorAziendale);
  }
  
  @Test
  public void test01_aggiungiTirocinante() {
    try {
      profiloFacade.aggiungiTirocinante(tirocinante);
    } catch (Exception e) {
      fail();
    }
  }
 
  @Test
  public void test02_login() {
    try {
      Tirocinante t = (Tirocinante) autenticazioneFacade.login(tirocinante);
      
      assertNotNull(t);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test03_aggiungereOffertaFormativaEsterna() {
    try {
      offerteFormativeFacade.aggiungereOffertaFormativaEsterna(offertaFormativaTirocinioEsterno);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test04_mostraTuttiTutorDidattici() {
    try {
      ArrayList<TutorDidattico> lista = offerteFormativeFacade.mostraTuttiTutorDidattici();
      
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test05_mostraTuttiTutorAziendali() {
    try {
      ArrayList<TutorAziendale> lista = offerteFormativeFacade.mostraTuttiTutorAziendali(azienda);
      
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test06_mostraProposteOfferteFormativeEsterne() {
    try {
      ArrayList<OffertaFormativaTirocinioEsterno> lista = 
          offerteFormativeFacade.mostraProposteOfferteFormativeEsterne(tutorDidattico);
      
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test07_mostraOfferteFormativeEsterneInviate() {
    try {
      ArrayList<OffertaFormativaTirocinioEsterno> lista = 
          offerteFormativeFacade.mostraOfferteFormativeEsterneInviate(azienda);
      
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test08_accettareRifiutarePropostaOffertaFormativaEsterna() {
    try {
      offertaFormativaTirocinioEsterno.setStatus(OffertaFormativaTirocinioEsterno.ATTIVA);
      
      offerteFormativeFacade.accettareRifiutarePropostaOffertaFormativaEsterna(offertaFormativaTirocinioEsterno);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test09_mostraOfferteFormativeEsterne() {
    try {
      ArrayList<OffertaFormativaTirocinioEsterno> lista = 
          offerteFormativeFacade.mostraOfferteFormativeEsterne(azienda);
      
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test10_mostraTutteOfferteFormativeAzienda() {
    try {
      ArrayList<OffertaFormativaTirocinioEsterno> lista = 
          tirocinioFacade.mostraTutteOfferteFormativeAzienda(azienda.getEmail());
      
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test11_mostraTutteAziende() {
    try {
      ArrayList<Azienda> lista = tirocinioFacade.mostraTutteAziende();
      
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test12_mostraOffertaFormativaAzienda() {
    try {
      String tema = offertaFormativaTirocinioEsterno.getTema();
      
      OffertaFormativaTirocinioEsterno o = tirocinioFacade.mostraOffertaFormativaAzienda(tema,azienda);
      
      assertEquals(o.isValidita(),true);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test13_richiediTirocinio() {
    try {
      String[] obiettivi = {"Aiutare la grafica per i ciechi"};

      tirocinioFacade.richiediTirocinio(tirocinante,offertaFormativaTirocinioEsterno,6,obiettivi);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test14_mostraRichiesteTirocinioInviate() {
    try {
      ArrayList<Tirocinio> lista = tirocinioFacade
          .mostraRichiesteTirocinioInviate(tirocinante);
      
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test15_mostraRichiesteTirocinio() {
    try {
      ArrayList<Tirocinio> lista = tirocinioFacade
          .mostraRichiesteTirocinio(tutorDidattico);
      
      tirocinio = lista.get(0);
      
      assertEquals(lista.size(),1);
      
      lista = tirocinioFacade
          .mostraRichiesteTirocinio(azienda);
      
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test16_accettaRifiutaRichiestaTirocinio() {
    try {
      tirocinio.setStatus(Tirocinio.IN_CORSO);
      
      tirocinioFacade.accettaRifiutaRichiestaTirocinio(tirocinio);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test17_mostraTirociniAttivi() {
    try {
      ArrayList<Tirocinio> lista = tirocinioFacade.mostraTirociniAttivi(tirocinante);
      assertEquals(lista.size(),1);
      lista = tirocinioFacade.mostraTirociniAttivi(azienda);
      assertEquals(lista.size(),1);
      lista = tirocinioFacade.mostraTirociniAttivi(tutorDidattico);
      assertEquals(lista.size(),1);
      lista = tirocinioFacade.mostraTirociniAttivi(tutorAziendale);
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test18_mostraRegistriTirocini() {
    try {
      ArrayList<Tirocinio> lista = registroTirocinioFacade.mostraRegistriTirocini(tirocinante);
      
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test19_aggiungiAttivita() {
    try {
      attivitaRegistro.setTirocinio(tirocinio);
      
      registroTirocinioFacade.aggiungiAttivita(attivitaRegistro);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test20_verificaTirociniAttiviTirocinante() {
    try {
      boolean risultato = profiloFacade.verificaTirociniAttiviTirocinante(tirocinante);
      
      assertFalse(risultato);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test 
  public void test21_convalidaAttivita() {
    try {
      attivitaRegistro.setConvalida(AttivitaRegistro.CONVALIDATA);
      
      registroTirocinioFacade.convalidaAttivita(attivitaRegistro);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test22_mostraTirociniStorico() {
    try {
      ArrayList<Tirocinio> lista = tirocinioFacade.mostraTirociniStorico(tirocinante);
      assertEquals(lista.size(),1);
      lista = tirocinioFacade.mostraTirociniStorico(azienda);
      assertEquals(lista.size(),1);
      lista = tirocinioFacade.mostraTirociniStorico(tutorDidattico);
      assertEquals(lista.size(),1);
      lista = tirocinioFacade.mostraTirociniStorico(tutorAziendale);
      assertEquals(lista.size(),1);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test23_rimuoviTirocinante() {
    try {
      profiloFacade.rimuoviTirocinante(tirocinante);
    } catch (Exception e) {
      fail();
    }
  }
  
  @AfterClass
  public static void close() throws Exception{
    TutorDidatticoDao.rimuoviTutorDidattico(tutorDidattico);
    AziendaDao.rimuoviAzienda(azienda);
    TitolareAziendaDao.rimuoviTitolare(titolareAzienda);
    TutorAziendaleDao.rimuoviTutorAziendale(tutorAziendale);
    
    // Rimuoviamo gli oggetti dal database con un nostro metodo nel test 
    // perchè non è offerta questa funzionalità dalle corrispondenti calssi Facade.
    
    eliminaOfferta();
    eliminaTirocinio();
    eliminaObiettivi();
    eliminaAttivita();
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
  
  private static void eliminaAttivita(){
    Connection con = null;
    String RIMUOVI_ATTIVITA = "DELETE FROM eTraining.AttivitaRegistro ";
    
    try {
      con = DriverManagerConnectionPool.getConnection();
      
      PreparedStatement ps = con.prepareStatement(RIMUOVI_ATTIVITA);
      
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
