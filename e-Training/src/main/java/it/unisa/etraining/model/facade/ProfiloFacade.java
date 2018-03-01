package it.unisa.etraining.model.facade;

import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;

//        Stubs
//import it.unisa.etraining.model.facade.daostub.*;

//        Implementazione  
import it.unisa.etraining.model.dao.TirocinanteDao;
import it.unisa.etraining.model.dao.TirocinioDao;

import java.util.ArrayList;

/*
 * Questa classe fornisce un'interfaccia unificata per la gestione completa del
 * sottosistema relativo alle gestione del profilo.
 * @author Flavio Esposito
 */
public class ProfiloFacade {

  /**
   * Questo metodo controlla se un Tirocinante si è gia iscritto alla piattaforma.
   * @param email l'email del tirocinante di cui verificare l'iscrizione.
   * @return true se è gia iscritto, false altrimenti.
   */
  public boolean contrallaRichiestaTirocinioGiaInviata(String email) throws Exception {
    Tirocinante tirocinante = new Tirocinante();
    tirocinante.setEmail(email);
    
    boolean risultato = TirocinanteDao.ricercaTirocinante(tirocinante);
   
    if (!risultato) {
      throw new Exception("Database Error");
    }
   
    return (tirocinante.getNome() != null);
  }
  
  /**
   * Questo metodo permette di aggiungere un Tirocinante al sistema.
   * @param tirocinante l'oggetto da salvare.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public void aggiungiTirocinante(Tirocinante tirocinante) throws Exception {
    boolean risultato = TirocinanteDao.salvaTirocinante(tirocinante);
    
    if (!risultato) {
      throw new Exception("Database Error");
    }
  }
  
  /**
   * Questo metodo permette di rimuovere un Tirocinante dal sistema.
   * @param tirocinante l'oggetto da rimuovere dal sistema.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public void rimuoviTirocinante(Tirocinante tirocinante) throws Exception {
    boolean risultato = TirocinanteDao.rimuoviTirocinante(tirocinante);
    
    if (!risultato) {
      throw new Exception("Database Error");
    }
  }
  
  /**
   * Questo metodo permette di verificare se un tirocinante ha ancora dei
   * tirocini attivi.
   * @param tirocinante l'oggetto di cui verificare i tirocini attivi.
   * @return true se il tirocinante non ha tirocini attivi, false altrimenti.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public boolean verificaTirociniAttiviTirocinante(Tirocinante tirocinante) throws Exception {
    ArrayList<Tirocinio> tirociniAttivi = new ArrayList<>();
    
    boolean risultato = TirocinioDao.ricercaTirociniAttivi(tirociniAttivi,tirocinante);
    
    if (!risultato) {
      throw new Exception("Database Error");
    }
    
    return (tirociniAttivi.size() == 0);
  }
}
