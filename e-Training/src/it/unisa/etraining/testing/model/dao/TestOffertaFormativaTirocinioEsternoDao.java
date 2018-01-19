package it.unisa.etraining.testing.model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import it.unisa.etraining.model.dao.AziendaDao;
import it.unisa.etraining.model.dao.OffertaFormativaTirocinioEsternoDao;
import it.unisa.etraining.model.dao.TutorAziendaleDao;
import it.unisa.etraining.model.dao.TutorDidatticoDao;

/**
 * @author Esposito Flavio
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOffertaFormativaTirocinioEsternoDao {

  private static OffertaFormativaTirocinioEsterno unOfferta;
  private static TutorAziendale unTutorAziendale;
  private static Azienda unAzienda;
  private static TutorDidattico unTutorDidattico;

  @BeforeClass
  public static void init() throws Exception{    
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
  }
  
  @Test
  public void test1_InserimentoOffertaFormativa(){
    boolean risultato = OffertaFormativaTirocinioEsternoDao.salvaOffertaFormativaTirocinioEsterno(unOfferta);
    
    assertTrue(risultato);
  }
  
  @Test
  public void test2_ricercaOffertaFormativaTirocinioEsterno(){
    OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
    offerta.setAzienda(unOfferta.getAzienda());
    offerta.setTema(unOfferta.getTema());
    OffertaFormativaTirocinioEsternoDao.ricercaOffertaFormativaTirocinioEsterno(offerta); 

    assertEquals(offerta.getTema(),unOfferta.getTema());
  }
  
  @Test
  public void test3_ricercaTutteOfferteFormativaTirocinioEsterno(){
    ArrayList<OffertaFormativaTirocinioEsterno> offerte = new ArrayList<>();
    
    OffertaFormativaTirocinioEsternoDao.ricercaTutteOfferteFormativeTirocinioEsterno(offerte, unAzienda);
    
    for (OffertaFormativaTirocinioEsterno off : offerte) {
        assertEquals(off.getAzienda().getEmail(), unAzienda.getEmail());
    }
  }

  @Test
  public void test4_aggiornaOffertaFormativaTirocinioEsterno(){
    String valorePrecedente = unOfferta.getFacilitazioni()[0];
    
    String[] facilitazioni = {"prova1","prova2"};
    unOfferta.setFacilitazioni(facilitazioni);
    OffertaFormativaTirocinioEsternoDao.aggiornaOffertaFormativaTirocinioEsterno(unOfferta);
    
    OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
    Azienda azienda = new Azienda();
    azienda.setEmail(unOfferta.getAzienda().getEmail());

    offerta.setTema(unOfferta.getTema());
    offerta.setAzienda(azienda);
    OffertaFormativaTirocinioEsternoDao.ricercaOffertaFormativaTirocinioEsterno(offerta);
    
    
    assertNotEquals(valorePrecedente, offerta.getFacilitazioni()[0]);
    
  }

  
  @Test
  public void test5_aggiornaStatusOffertaFormativaTirocinioEsterno(){
    unOfferta.setStatus(OffertaFormativaTirocinioEsterno.DA_VALUTARE);
    OffertaFormativaTirocinioEsternoDao.aggiornaStatusOffertaFormativaTirocinioEsterno(unOfferta);
    
    OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
    offerta.setAzienda(unOfferta.getAzienda());
    offerta.setTema(unOfferta.getTema());
    assertFalse(OffertaFormativaTirocinioEsternoDao.ricercaOffertaFormativaTirocinioEsterno(offerta)); 
    
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
