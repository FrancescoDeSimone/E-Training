package it.unisa.etraining.model.facade;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;

//        Stubs
//import it.unisa.etraining.model.facade.daostub.*;

//        Implementazione
import it.unisa.etraining.model.dao.AziendaDao;
import it.unisa.etraining.model.dao.OffertaFormativaTirocinioEsternoDao;
import it.unisa.etraining.model.dao.TutorAziendaleDao;
import it.unisa.etraining.model.dao.TutorDidatticoDao;

import java.util.ArrayList;

/**
 * Questa classe fornisce un'interfaccia unificata per la gestione completa del
 * sottosistema relativo alle offerte formative esterne.
 * @author Basso Emanuele
 */
public class OfferteFormativeFacade {

  /**
   * Questo metodo permette di ricercare tutte le offerte formative di
   * una determinata azienda.
   * @param azienda l'oggetto di cui ricercare le offerte formative esterne.
   * @return l'array delle offerte formative esterne.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<OffertaFormativaTirocinioEsterno> mostraOfferteFormativeEsterne(Azienda azienda) 
      throws Exception {
    ArrayList<OffertaFormativaTirocinioEsterno> listaOfferte = new ArrayList<>();
    
    boolean risultato1 = OffertaFormativaTirocinioEsternoDao
        .ricercaTutteOfferteFormativeTirocinioEsterno(listaOfferte,azienda);
    
    boolean risultato2 = true;
    boolean risultato3 = true;
    for (OffertaFormativaTirocinioEsterno o : listaOfferte) {
      risultato2 = TutorAziendaleDao.ricercaTutorAziendale(o.getTutorAziendale());
      
      risultato3 = TutorDidatticoDao.ricercaTutorDidattico(o.getTutorDidattico());
    }
    
    if (!risultato1 || !risultato2 || !risultato3) {
      throw new Exception("Database Error");
    }
    
    return listaOfferte;
  }
  
  /**
   * Questo metodo permette di ricercare tutti i tutor didattici presenti
   * nel sistema.
   * @return la lista dei tutor didattici.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<TutorDidattico> mostraTuttiTutorDidattici() throws Exception {
    ArrayList<TutorDidattico> listaTutor = new ArrayList<>();
    
    boolean risultato = TutorDidatticoDao.ricercaTuttiTutorDidattici(listaTutor);
        
    if (!risultato) {
      throw new Exception("Database Error");
    }
    
    return listaTutor;
  }
  
  /**
   * Questo metodo permette di ricercare tutti i tutor aziendali di una data
   * azienda presenti nel sistema.
   * @param azienda l'azienda di cui ricercare i tutor aziendali.
   * @return la lista di tutor aziendali dell'azienda.
   * @throws Exception Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<TutorAziendale> mostraTuttiTutorAziendali(Azienda azienda) throws Exception {
    ArrayList<TutorAziendale> listaTutor = new ArrayList<>();
    
    boolean risultato = TutorAziendaleDao.ricercaTuttiTutorAziendali(listaTutor,azienda);
        
    if (!risultato) {
      throw new Exception("Database Error");
    }
    
    return listaTutor;
  }
  
  /**
   * Quetso metodo permette di aggiungere un'offerta formativa di tirocinio
   * esterno di un'azienda.
   * @param offertaFormativa l'oggetto offerta formativa da aggiungere sul 
   *     database.
   * @throws Exception Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public void aggiungereOffertaFormativaEsterna(OffertaFormativaTirocinioEsterno offertaFormativa) 
      throws Exception {
    boolean risultato = OffertaFormativaTirocinioEsternoDao
        .salvaOffertaFormativaTirocinioEsterno(offertaFormativa);
    
    if (!risultato) {
      throw new Exception("Database Error");
    }
  }
  
  /**
   * Questo metodo permette di ricercare tutte le proposte di offerte
   * formative pervenute a un tutor didattico.
   * @param tutorDidattico l'oggetto di cui ricercare le proposte.
   * @return la lista di proposte ricercate.
   * @throws Exception Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<OffertaFormativaTirocinioEsterno> 
      mostraProposteOfferteFormativeEsterne(TutorDidattico tutorDidattico) throws Exception {
    ArrayList<OffertaFormativaTirocinioEsterno> listaOfferte = new ArrayList<>();
    
    boolean risultato1 = OffertaFormativaTirocinioEsternoDao
        .ricercaOfferteFormativeTirocinioEsternoRicevuteTutorDidattico(listaOfferte,tutorDidattico);
    
    boolean risultato2 = true;
    boolean risultato3 = true;
    for (OffertaFormativaTirocinioEsterno o : listaOfferte) {
      risultato2 = AziendaDao.ricercaAzienda(o.getAzienda());
      
      risultato3 = TutorAziendaleDao.ricercaTutorAziendale(o.getTutorAziendale());
    }
    
    if (!risultato1 || !risultato2 || !risultato3) {
      throw new Exception("Database Error");
    }
    
    return listaOfferte;
  }
  
  /**
   * Questo metodo permette di accettare/rifiutare una proposta di offerta
   * formativa pervenuta a un tutor didattico.
   * @param offertaFormativa l'oggetto di cui accettare/rifiutare la proposta.
   * @throws Exception Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public void accettareRifiutarePropostaOffertaFormativaEsterna(
      OffertaFormativaTirocinioEsterno offertaFormativa) throws Exception {
    String status = offertaFormativa.getStatus();
    
    boolean risultato1 = OffertaFormativaTirocinioEsternoDao
        .ricercaOffertaFormativaById(offertaFormativa);
    
    offertaFormativa.setStatus(status);
    
    if (offertaFormativa.getStatus().equals(OffertaFormativaTirocinioEsterno.ATTIVA)) {
      offertaFormativa.setValidita(true);
    }

    boolean risultato2 = OffertaFormativaTirocinioEsternoDao
        .aggiornaStatusOffertaFormativaTirocinioEsterno(offertaFormativa);
        
    if (!risultato1 || !risultato2) {
      throw new Exception("Database Error");
    }
  }
  
  /**
   * Questo metodo permette di ricercare le proposte di offerte formative
   * inviate da un'azienda ai tutor didattici.
   * @param azienda l'oggetto di cui ricercare le proposte inviate.
   * @return la lista di proposte inviate.
   * @throws Exception Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<OffertaFormativaTirocinioEsterno> 
      mostraOfferteFormativeEsterneInviate(Azienda azienda) 
      throws Exception {
    ArrayList<OffertaFormativaTirocinioEsterno> listaProposte = new ArrayList<>();
    
    boolean risultato1 = OffertaFormativaTirocinioEsternoDao
        .ricercaOfferteFormativaTirocinioEsternoInviate(listaProposte,azienda);

    boolean risultato2 = true;
    boolean risultato3 = true;
    for (OffertaFormativaTirocinioEsterno o : listaProposte) {
      risultato2 = TutorDidatticoDao.ricercaTutorDidattico(o.getTutorDidattico());
      
      risultato3 = TutorAziendaleDao.ricercaTutorAziendale(o.getTutorAziendale());
    }
    
    if (!risultato1 || !risultato2 || !risultato3) {
      throw new Exception("Database Error");
    }
    
    return listaProposte;
  }
  
}
