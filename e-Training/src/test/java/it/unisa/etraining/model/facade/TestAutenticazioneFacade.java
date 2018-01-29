package it.unisa.etraining.model.facade;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.FunzionarioSegreteria;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.bean.Utente;
import it.unisa.etraining.model.facade.AutenticazioneFacade;

/**
 * 
 * @author Flavio Esposito
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAutenticazioneFacade {

  private static Tirocinante tirocinante;
  private static TutorAziendale tutorAziendale;
  private static TutorDidattico tutorDidattico;
  private static Azienda azienda;
  private static FunzionarioSegreteria funzionarioSegreteria;
  
  @BeforeClass
  public static void init() {
    tirocinante = new Tirocinante();
    tirocinante.setEmail("f.esposito104@studenti.unisa.it");
    tirocinante.setPassword("password");
    tirocinante.setNome("Flavio");
    tirocinante.setCognome("Esposito");
    tirocinante.setMatricola("0512103467");
    tirocinante.setAnnoIscrizione("2015/2016");
    
    azienda = new Azienda();
    azienda.setEmail("aldeandra.Garofalo@gmail.com");
    azienda.setPassword("password");
    azienda.setCitta("Napoli");
    azienda.setNome("Lete Empire");
    azienda.setPartitaIva("12345678901");
    azienda.setSede("Via Roma, 4");
    
    tutorAziendale = new TutorAziendale();
    tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
    tutorAziendale.setPassword("password");
    tutorAziendale.setAzienda(azienda);
    tutorAziendale.setCodiceFiscale("0123456789145863");
    tutorAziendale.setNome("Antonio");
    tutorAziendale.setCognome("Esposito");
    tutorAziendale.setSettoreLavoro("Mobile graphic");
    
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
    
    funzionarioSegreteria = new FunzionarioSegreteria();
    funzionarioSegreteria.setEmail("a.ahamed@unisa.it");
    funzionarioSegreteria.setPassword("password");
    funzionarioSegreteria.setNome("Geppetto");
    funzionarioSegreteria.setCognome("Brignoli");
    funzionarioSegreteria.setCodiceFiscale("1234567890124589");
    String[] qualifiche = {"Scrivere a computer"};
    funzionarioSegreteria.setQualifiche(qualifiche);
  }
  
  @Test
  public void test1_login() throws Exception {
    AutenticazioneFacade autenticazioneFacade = new AutenticazioneFacade();
    
    Utente utente = autenticazioneFacade.login(tirocinante);
  
    assertNotNull(utente);
    
    utente = autenticazioneFacade.login(tutorAziendale);
    
    assertNotNull(utente);

    utente = autenticazioneFacade.login(tutorDidattico);
    
    assertNotNull(utente);
    
    utente = autenticazioneFacade.login(azienda);
    
    assertNotNull(utente);
    
    utente = autenticazioneFacade.login(funzionarioSegreteria);
    
    assertNotNull(utente);
  }
  
}
