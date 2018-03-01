package it.unisa.etraining.model.facade;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.bean.Utente;

//      Stubs
//import it.unisa.etraining.model.facade.daostub.*;

//      Implementazione
import it.unisa.etraining.model.dao.AziendaDao;
import it.unisa.etraining.model.dao.OffertaFormativaTirocinioEsternoDao;
import it.unisa.etraining.model.dao.TirocinanteDao;
import it.unisa.etraining.model.dao.TirocinioDao;
import it.unisa.etraining.model.dao.TutorAziendaleDao;
import it.unisa.etraining.model.dao.TutorDidatticoDao;

import java.util.ArrayList;

/**
 * Questa classe fornisce un'interfaccia unificata per la gestione completa del
 * sottosistema relativo al tirocinio.
 * @author Basso Emanuele
 */
public class TirocinioFacade {

  /**
   * Questo metodo permette di ricercare i tirocini attivi di un dato utente.
   * @param utente l'utente di cui ricercare i tirocini attivi.
   * @return la lista dei tirocini attivi dell'utente.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<Tirocinio> mostraTirociniAttivi(Utente utente) throws Exception {
    ArrayList<Tirocinio> tirociniAttivi = new ArrayList<>();
    
    boolean risultato1 = TirocinioDao.ricercaTirociniAttivi(tirociniAttivi,utente);
          
    boolean risultato2 = true;
    boolean risultato3 = true;
    boolean risultato4 = true;
    boolean risultato5 = true;
    for (Tirocinio t : tirociniAttivi) {
      OffertaFormativaTirocinioEsterno offerta = t.getOfferta();
      risultato2 = OffertaFormativaTirocinioEsternoDao.ricercaOffertaFormativaById(offerta);
         
      risultato3 = TutorAziendaleDao.ricercaTutorAziendale(offerta.getTutorAziendale());
      
      risultato4 = TutorDidatticoDao.ricercaTutorDidattico(offerta.getTutorDidattico());
      
      risultato5 = TirocinanteDao.ricercaTirocinante(t.getTirocinante());
    }
     
    if (!risultato1 || !risultato2 || !risultato3 || !risultato4 || !risultato5) {
      throw new Exception("Database Error");
    }
    
    return tirociniAttivi;
  }
  
  /**
   * Questo metodo permette di ricercare i tirocini conclusi di un dato utente.
   * @param utente l'utente di cui ricercare i tirocini conclusi.
   * @return la lista dei tirocini conclusi dell'utente.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<Tirocinio> mostraTirociniStorico(Utente utente) throws Exception {
    ArrayList<Tirocinio> tirociniStorico = new ArrayList<>();
    
    boolean risultato1 = TirocinioDao.ricercaTirociniStorico(tirociniStorico,utente);

    boolean risultato2 = true;
    boolean risultato3 = true;
    boolean risultato4 = true;
    boolean risultato5 = true;
    for (Tirocinio t : tirociniStorico) {
      OffertaFormativaTirocinioEsterno offerta = t.getOfferta();
      risultato2 = OffertaFormativaTirocinioEsternoDao.ricercaOffertaFormativaById(offerta);
      
      risultato3 = TutorAziendaleDao.ricercaTutorAziendale(offerta.getTutorAziendale());
      
      risultato4 = TutorDidatticoDao.ricercaTutorDidattico(offerta.getTutorDidattico());
      
      risultato5 = TirocinanteDao.ricercaTirocinante(t.getTirocinante());
    }
    
    if (!risultato1 || !risultato2 || !risultato3 || !risultato4 || !risultato5) {
      throw new Exception("Database Error");
    }
    
    return tirociniStorico;
  }
  
  /**
   * Questo metodo permette di ricercare tutte le aziende presenti nel sistema.
   * @return la lista delle aziende presenti nel sistema.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<Azienda> mostraTutteAziende() throws Exception {
    ArrayList<Azienda> listaAziende = new ArrayList<>();
    
    boolean risultato = AziendaDao.ricercaTutteAzienda(listaAziende);
    
    if (!risultato) {
      throw new Exception("Database Error");
    }
    
    return listaAziende;
  }
  
  /**
   * Questo metodo permette di ricercare tutte le offerte formative di un'azienda 
   * presenti nel sistema.
   * @param aziendaEmail l'email dell'azienda di cui ricercare le offerte formative.
   * @return la lista delle offerte formative dell'azienda.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<OffertaFormativaTirocinioEsterno> mostraTutteOfferteFormativeAzienda(
      String aziendaEmail) throws Exception {
    Azienda unAzienda = new Azienda();
    unAzienda.setEmail(aziendaEmail);
    boolean risultato1 = AziendaDao.ricercaAzienda(unAzienda);
    
    ArrayList<OffertaFormativaTirocinioEsterno> listaOfferte = new ArrayList<>();
    boolean risultato2 = OffertaFormativaTirocinioEsternoDao
        .ricercaTutteOfferteFormativeTirocinioEsterno(listaOfferte,unAzienda);
    
    if (!risultato1 || !risultato2) {
      throw new Exception("Database Error");
    }
    
    return listaOfferte;
  }
  
  /**
   * Questo metodo permette di ricercare un'offerta formativa di un'azienda
   * sul sistema.
   * @param tema il tema dell'offerta formativa da ricercare.
   * @param azienda l'azienda che ha creato l'offerta formativa. 
   * @return l'offerta formativa ricercata.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public OffertaFormativaTirocinioEsterno mostraOffertaFormativaAzienda(String tema, 
      Azienda azienda) throws Exception {
    
    OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
    offerta.setTema(tema);
    offerta.setAzienda(azienda);
    boolean risultato1 = OffertaFormativaTirocinioEsternoDao
        .ricercaOffertaFormativaTirocinioEsterno(offerta);
    
    boolean risultato2 = TutorDidatticoDao.ricercaTutorDidattico(offerta.getTutorDidattico());
    
    boolean risultato3 = TutorAziendaleDao.ricercaTutorAziendale(offerta.getTutorAziendale());
    
    if (!risultato1 || !risultato2 || !risultato3) {
      throw new Exception("Database Error");
    }
    
    return offerta;
  }
  
  /**
   * Questo metodo controlla se un Tirocinante ha gia richiesto un tirocinio per quella
   * data Offerta Formativa.
   * @param tirocinante il tirocinante di cui verificare la richiesta.
   * @param offerta l'offerta da controllare se gia è stata richiesta.
   * @return true se è gia stata fatta richiesta, false altrimenti.
   */
  public boolean contrallaRichiestaTirocinioGiaInviata(Tirocinante tirocinante,
      OffertaFormativaTirocinioEsterno offerta) throws Exception {
    Tirocinio tirocinio = new Tirocinio();
    tirocinio.setTirocinante(tirocinante);
    tirocinio.setOfferta(offerta);
   
    boolean risultato1 = OffertaFormativaTirocinioEsternoDao
        .ricercaOffertaFormativaTirocinioEsterno(offerta);
   
    boolean risultato2 = TirocinioDao.ricercaTirocinio(tirocinio);
   
    if (!risultato1 || !risultato2) {
      throw new Exception("Database Error");
    }
   
    return (tirocinio.getCfu() != 0);
  }
   
  /**
   * Questo metodo permette di salvare la richiesta di tirocinio di un dato tirocinante.
   * @param tirocinante colui che vuole richiedere il tirocinio
   * @param offertaFormativa l'offerta formativa relativa al tirocinio che si vuole richiedere.
   * @param cfu i cfu del tirocinio richiesto.
   * @param obiettivi la lista degli obiettivi del tirocinio richiesto.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public void richiediTirocinio(Tirocinante tirocinante,
        OffertaFormativaTirocinioEsterno offertaFormativa,
      int cfu, String[] obiettivi) throws Exception {
    Tirocinio unTirocinio = new Tirocinio();
    unTirocinio.setCfu(cfu);
    unTirocinio.setObiettivi(obiettivi);
    unTirocinio.setOfferta(offertaFormativa);
    unTirocinio.setOreRimanenti(cfu * 25);
    unTirocinio.setStatus(Tirocinio.VALUTAZIONE_DOCENTE);
    unTirocinio.setTirocinante(tirocinante);
    
    boolean risultato1 = OffertaFormativaTirocinioEsternoDao
        .ricercaOffertaFormativaTirocinioEsterno(offertaFormativa);
    
    boolean risultato2 = TirocinioDao.salvaTirocinio(unTirocinio);
    
    if (!risultato1 || !risultato2) {
      throw new Exception("Database Error");
    }
  }
  
  /**
   * Questo metodo permette di ricercare le richieste di tirocinio inviate da un tirocininate.
   * @param tirocinante colui che ha inviato le richieste di tirocinio.
   * @return la lista delle richieste di tirocinio inviate dal tirocininate.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<Tirocinio> mostraRichiesteTirocinioInviate(Tirocinante tirocinante) 
      throws Exception {
    ArrayList<Tirocinio> tirociniInviati = new ArrayList<>();
    
    boolean risultato1 = TirocinioDao.ricercaTirociniInviati(tirociniInviati,tirocinante);
    
    boolean risultato2 = true;
    boolean risultato3 = true;
    boolean risultato4 = true;
    for (Tirocinio t : tirociniInviati) {
      OffertaFormativaTirocinioEsterno offerta = t.getOfferta();
      risultato2 = OffertaFormativaTirocinioEsternoDao.ricercaOffertaFormativaById(offerta);
      
      risultato3 = TutorAziendaleDao.ricercaTutorAziendale(offerta.getTutorAziendale());
      
      risultato4 = TutorDidatticoDao.ricercaTutorDidattico(offerta.getTutorDidattico());
    }
    
    if (!risultato1 || !risultato2 || !risultato3 || !risultato4) {
      throw new Exception("Database Error");
    }
    
    return tirociniInviati;
  }
  
  /**
   * Questo metodo permette di ricercare le richieste di tirocinio pervenute a un tutor didattico
   * o a un'azienda.
   * @param utente l'oggetto di cui ricercare le richieste di tirocinio.
   * @return la lista delle richieste di tirocinio pervenute.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<Tirocinio> mostraRichiesteTirocinio(Utente utente) throws Exception {
    ArrayList<Tirocinio> listaRichieste = new ArrayList<>();
    
    boolean risultato1 = TirocinioDao.ricercaRichiesteTirocinio(listaRichieste,utente);
    
    boolean risultato2 = true;
    boolean risultato3 = true;
    boolean risultato4 = true;
    boolean risultato5 = true;
    boolean risultato6 = true;
    for (Tirocinio t : listaRichieste) {
      Tirocinante tirocinante = t.getTirocinante();
      risultato2 = TirocinanteDao.ricercaTirocinante(tirocinante);
      
      OffertaFormativaTirocinioEsterno offerta = t.getOfferta();
      risultato3 = OffertaFormativaTirocinioEsternoDao.ricercaOffertaFormativaById(offerta);
      
      risultato4 = AziendaDao.ricercaAzienda(offerta.getAzienda());      
      
      risultato5 = TutorAziendaleDao.ricercaTutorAziendale(offerta.getTutorAziendale());
      
      risultato6 = TutorDidatticoDao.ricercaTutorDidattico(offerta.getTutorDidattico());
    }
    
    if (!risultato1 || !risultato2 || !risultato3 || !risultato4 || !risultato5 || !risultato6) {
      throw new Exception("Database Error");
    }
    
    return listaRichieste;
  }
  
  /**
   * Questo metodo permette di salvare la scelta di accettare o rifiutare una richiesta di 
   * tirocinio di un tutor didattico o di un'azienda.
   * @param unTirocinio l'oggetto di cui salvare la scelta se accettare o rifiutare.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public void accettaRifiutaRichiestaTirocinio(Tirocinio unTirocinio) throws Exception {
    boolean risultato = TirocinioDao.aggiornaStatusTirocinio(unTirocinio);
    
    if (!risultato) {
      throw new Exception("Database Error");
    }
  }
  
}
