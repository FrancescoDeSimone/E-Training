package it.unisa.etraining.model.facade;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisa.etraining.model.bean.AttivitaRegistro;
import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.facade.RegistroTirocinioFacade;

/**
 * 
 * @author Flavio Esposito
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRegistroTirocinioFacade {

  private static RegistroTirocinioFacade registroTirocinioFacade;
  
  private static Tirocinante tirocinante;
  private static Azienda azienda;
  private static OffertaFormativaTirocinioEsterno offerta;
  private static TutorDidattico tutorDidattico;
  private static TutorAziendale tutorAziendale;
  private static Tirocinio tirocinio;
  private static AttivitaRegistro attivita;
  
  @BeforeClass
  public static void init() {
    registroTirocinioFacade = new RegistroTirocinioFacade();
    
    azienda = new Azienda();
    azienda.setEmail("aldeandra.Garofalo@gmail.com");
    
    tutorAziendale = new TutorAziendale();
    tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
    
    tutorDidattico = new TutorDidattico();
    tutorDidattico.setEmail("a.cataldi@unisa.it");
    
    tirocinante = new Tirocinante();
    tirocinante.setEmail("d.abbondio104@studenti.unisa.it");

    offerta = new OffertaFormativaTirocinioEsterno();
    offerta.setTutorDidattico(tutorDidattico);
    offerta.setTutorAziendale(tutorAziendale);
    offerta.setTema("Database distribuiti sequenziali");
    offerta.setInizioTirocinio(new GregorianCalendar(2018,01,11));
    offerta.setFineTirocinio(new GregorianCalendar(2018,01,14));
    offerta.setValidita(false);
    offerta.setAzienda(azienda);
    offerta.setStatus(OffertaFormativaTirocinioEsterno.DA_VALUTARE);
    offerta.setId(1);
    
    tirocinio = new Tirocinio();
    tirocinio.setTirocinante(tirocinante);
    tirocinio.setOfferta(offerta);
    
    attivita = new AttivitaRegistro();
    attivita.setTirocinio(tirocinio);
    attivita.setAttivitaSvolta("Non so che dire");
    attivita.setInizio(new GregorianCalendar(2018,01,11));
    attivita.setFine(new GregorianCalendar(2018,01,14));
    attivita.setConvalida(AttivitaRegistro.IN_CONVALIDA);
  }
  
  @Test
  public void test1_mostraRegistriTirocini() throws Exception {
    ArrayList<Tirocinio> lista = registroTirocinioFacade.mostraRegistriTirocini(tirocinante);
    
    assertEquals(lista.size(),1);
  }
  
  @Test
  public void test2_aggiungiAttivita() {
    try {
      registroTirocinioFacade.aggiungiAttivita(attivita);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test3_convalidaAttivita() {
    attivita.setConvalida(AttivitaRegistro.CONVALIDATA);
    
    try {
      registroTirocinioFacade.convalidaAttivita(attivita);
    } catch (Exception e) {
      fail();
    }
  }
  
}
