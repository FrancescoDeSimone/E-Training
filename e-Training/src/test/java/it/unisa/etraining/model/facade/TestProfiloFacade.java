package it.unisa.etraining.model.facade;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.facade.ProfiloFacade;

/**
 * 
 * @author Flavio Esposito
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProfiloFacade {

  private static ProfiloFacade profiloFacade;
  private static Tirocinante tirocinante;
  
  @BeforeClass
  public static void init() throws Exception {
    profiloFacade = new ProfiloFacade();
    
    tirocinante = new Tirocinante();
    tirocinante.setEmail("d.abbondio104@studenti.unisa.it");
    tirocinante.setPassword("password");
    tirocinante.setNome("Don");
    tirocinante.setCognome("Abbondio");
    tirocinante.setMatricola("0512103121");
    tirocinante.setAnnoIscrizione("2015/2016");
  }
  
  @Test
  public void test1_aggiungiTirocinante() {
    try {
      profiloFacade.aggiungiTirocinante(tirocinante);
    } catch (Exception e) {
      fail();
    }
  }
  
  @Test
  public void test2_rimuoviTirocinante() throws Exception {
    try {
      profiloFacade.rimuoviTirocinante(tirocinante);
    } catch (Exception e) {
      fail();
    }
    
  }
  
  @Test
  public void test3_verificaTirociniAttiviTirocinante() throws Exception {
    boolean risultato = profiloFacade.verificaTirociniAttiviTirocinante(tirocinante);
    
    assertEquals(risultato,false);
  }
}
