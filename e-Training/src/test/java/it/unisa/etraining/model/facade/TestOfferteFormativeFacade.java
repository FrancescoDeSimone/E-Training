package it.unisa.etraining.model.facade;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisa.etraining.model.bean.Azienda;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.facade.OfferteFormativeFacade;

/**
 * 
 * @author Basso Emanuele
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOfferteFormativeFacade {

  private static OfferteFormativeFacade offerteFormativeFacade;
  private static Azienda azienda;
  private static OffertaFormativaTirocinioEsterno offerta;
  private static TutorDidattico tutorDidattico;
  private static TutorAziendale tutorAziendale;
  
  @BeforeClass
  public static void init() {
    offerteFormativeFacade = new OfferteFormativeFacade();
    
    azienda = new Azienda();
    azienda.setEmail("aldeandra.Garofalo@gmail.com");
    
    tutorAziendale = new TutorAziendale();
    tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
    
    tutorDidattico = new TutorDidattico();
    tutorDidattico.setEmail("a.cataldi@unisa.it");

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
  }
  
  @Test
  public void test1_mostraOfferteFormativeEsterne() throws Exception {
    ArrayList<OffertaFormativaTirocinioEsterno> listaOfferte = offerteFormativeFacade.mostraOfferteFormativeEsterne(azienda);
    
    assertEquals(listaOfferte.size(),1);
  }
  
  @Test
  public void test2_mostraTuttiTutorDidattici() throws Exception {
    ArrayList<TutorDidattico> listaTutor = offerteFormativeFacade.mostraTuttiTutorDidattici();
    
    assertEquals(listaTutor.size(),1);
  }
  
  @Test
  public void test3_mostraTuttiTutorAziendali() throws Exception {
    ArrayList<TutorAziendale> listaTutor = offerteFormativeFacade.mostraTuttiTutorAziendali(azienda);
    
    assertEquals(listaTutor.size(),1);
  }
  
  @Test
  public void test4_aggiungereOffertaFormativaEsterna() throws Exception {
    offerteFormativeFacade.aggiungereOffertaFormativaEsterna(offerta);
  }
  
  @Test
  public void test5_mostraProposteOfferteFormativeEsterne() throws Exception {
    ArrayList<OffertaFormativaTirocinioEsterno> listaOfferte = offerteFormativeFacade
        .mostraProposteOfferteFormativeEsterne(tutorDidattico);
    
    assertEquals(listaOfferte.size(),1);
  }
  
  @Test
  public void test6_accettareRifiutarePropostaOffertaFormativaEsterna() {
    try {
        offerteFormativeFacade.accettareRifiutarePropostaOffertaFormativaEsterna(offerta);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test7_mostraOfferteFormativeEsterneInviate() throws Exception {
    ArrayList<OffertaFormativaTirocinioEsterno> lista = 
        offerteFormativeFacade.mostraOfferteFormativeEsterneInviate(azienda);
    
    assertEquals(lista.size(),1);
  }
}
 
