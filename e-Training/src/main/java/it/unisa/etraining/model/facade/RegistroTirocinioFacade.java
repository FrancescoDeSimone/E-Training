package it.unisa.etraining.model.facade;

import it.unisa.etraining.model.bean.AttivitaRegistro;
import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.bean.Utente;

//      Stubs
//import it.unisa.etraining.model.facade.daostub.*;

//      Implementazione
import it.unisa.etraining.model.dao.AttivitaRegistroDao;
import it.unisa.etraining.model.dao.AziendaDao;
import it.unisa.etraining.model.dao.OffertaFormativaTirocinioEsternoDao;
import it.unisa.etraining.model.dao.TirocinanteDao;
import it.unisa.etraining.model.dao.TirocinioDao;
import it.unisa.etraining.model.dao.TutorAziendaleDao;
import it.unisa.etraining.model.dao.TutorDidatticoDao;
import java.util.ArrayList;
import java.util.Comparator;


/*
 * Questa classe fornisce un'interfaccia unificata per la gestione completa del
 * sottosistema relativo al registro del tirocinio.
 * @author Flavio Esposito
 */
public class RegistroTirocinioFacade {

  /**
   * Questo metodo permette di mostrare tutti i registri dei tirocini di
   * un determinato utente.
   * @param utente l'oggetto di cui ricercare i registri dei tirocini.
   * @return la lista dei registri dei tirocini dell'utente.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public ArrayList<Tirocinio> mostraRegistriTirocini(Utente utente) throws Exception {
    ArrayList<Tirocinio> listaRegistri = new ArrayList<>();
    
    boolean risultato1 = TirocinioDao.ricercaTirociniAttivi(listaRegistri,utente);
    
    boolean risultato2 = true;
    boolean risultato3 = true;
    boolean risultato4 = true;
    boolean risultato5 = true;
    boolean risultato6 = true;
    boolean risultato7 = true;
    for (Tirocinio t : listaRegistri) {
      OffertaFormativaTirocinioEsterno offerta = t.getOfferta();
      risultato2 = OffertaFormativaTirocinioEsternoDao.ricercaOffertaFormativaById(offerta);
      
      Azienda azienda = offerta.getAzienda();
      risultato3 = AziendaDao.ricercaAzienda(azienda);
      
      risultato4 = TutorAziendaleDao.ricercaTutorAziendale(offerta.getTutorAziendale());
      
      risultato5 = TutorDidatticoDao.ricercaTutorDidattico(offerta.getTutorDidattico());
      
      risultato6 = TirocinanteDao.ricercaTirocinante(t.getTirocinante());
      
      ArrayList<AttivitaRegistro> tutteAttivita = new ArrayList<>();
      
      risultato6 = AttivitaRegistroDao.ricercaTutteAttivitaRegistro(tutteAttivita,t);
      
      tutteAttivita.sort(new Comparator<AttivitaRegistro>() {
        public int compare(AttivitaRegistro a1,AttivitaRegistro a2) {
          if (a1.getInizio().before(a2.getInizio())) {
            return 1;
          } else if (a1.getInizio().after(a2.getInizio())) {
            return -1;
          } else {
            return 0;
          }
        }
      });
      
      AttivitaRegistro[] listaAttivitaRegistro = new AttivitaRegistro[tutteAttivita.size()];
      for (int i = 0; i < listaAttivitaRegistro.length; i++) {
        listaAttivitaRegistro[i] = tutteAttivita.get(i);
      }
      
      t.setAttivitaSvolte(listaAttivitaRegistro);
    }
    
    if (!risultato1 || !risultato2 || !risultato3 || !risultato4 || !risultato5 
        || !risultato6 || !risultato7) {
      throw new Exception("Database Error");
    }
    
    return listaRegistri;
  }
  
  /**
   * Questo metodo permette di aggiungere un attività al registro
   * del tirocinio.
   * @param attivitaRegistro l'oggetto da aggiugere al registro del tirocinio.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public void aggiungiAttivita(AttivitaRegistro attivitaRegistro) throws Exception {   
    boolean risultato = AttivitaRegistroDao.salvaAttivitaRegistro(attivitaRegistro);
    
    if (!risultato) {
      throw new Exception("Database Error");
    }
  }
  
  /**
   * Questo metodo permette di salvare la scelta di un tutor sulla convalida o non convalida 
   * di un attività.
   * @param attivitaRegistro l'oggetto che è stato convalidato o non.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public void convalidaAttivita(AttivitaRegistro attivitaRegistro) throws Exception {
    boolean risultato1 = AttivitaRegistroDao.aggiornaConvalidaAttivita(attivitaRegistro);
    
    boolean risultato2 = true;
    boolean risultato3 = true;
    boolean risultato4 = true;
    boolean risultato5 = true;
    if (attivitaRegistro.getConvalida().equals(AttivitaRegistro.CONVALIDATA)) {
    
      risultato2 = AttivitaRegistroDao.ricercaAttivitaRegistro(attivitaRegistro);
      
      Tirocinio tirocinio = attivitaRegistro.getTirocinio();
      risultato3 = TirocinioDao.ricercaTirocinio(tirocinio);
      
      int oreAttivita = attivitaRegistro.getOreSvolte();
      int oreRimanenti = tirocinio.getOreRimanenti();
      
      int ore;
      
      if (oreRimanenti < oreAttivita) {
        ore = 0;
      } else {
        ore = oreRimanenti - oreAttivita;
      }
      
      tirocinio.setOreRimanenti(ore);
      risultato4 = TirocinioDao.aggiornOreTirocinio(tirocinio);
      
      if (ore == 0) {
        tirocinio.setStatus(Tirocinio.TERMINATO);
        risultato5 = TirocinioDao.aggiornaStatusTirocinio(tirocinio);
      }
    }
    
    if (!risultato1 || !risultato2 || !risultato3 || !risultato4 || !risultato5) {
      throw new Exception("Database Error");
    }
  }
  
  /**
   * Questo metodo permette di salvare la scelta di un tutor sulla convalida  
   * di tutte le attività di tirocinio.
   * @param tirocinio l'oggetto di cui convalidare tutte le attività.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public void convalidaTutteAttivita(Tirocinio tirocinio) throws Exception {
    boolean risultato1 = AttivitaRegistroDao.aggiornaConvalidaTutteAttivita(tirocinio);
    
    boolean risultato2 = true;
    boolean risultato3 = true;
    boolean risultato4 = true;
    boolean risultato5 = true;
    if (tirocinio.getAttivitaSvolte()[0].getConvalida().equals(AttivitaRegistro.CONVALIDATA)) {
      ArrayList<AttivitaRegistro> tutteAttivita = new ArrayList<>();
      
      risultato2 = AttivitaRegistroDao.ricercaTutteAttivitaRegistro(tutteAttivita,tirocinio);

      int oreAttivita = 0;
      for (int indice = 0; indice < tutteAttivita.size(); indice++) {
        if (tutteAttivita.get(indice).getConvalida().equals(AttivitaRegistro.CONVALIDATA)) {
          oreAttivita += tutteAttivita.get(indice).getOreSvolte();
        }
      }
      
      risultato3 = TirocinioDao.ricercaTirocinio(tirocinio);
      
      int oreTotali = tirocinio.getCfu() * 25;
      
      int ore;
      if (oreTotali < oreAttivita) {
        ore = 0;
      } else {
        ore = oreTotali - oreAttivita;
      }
      
      tirocinio.setOreRimanenti(ore);
      risultato4 = TirocinioDao.aggiornOreTirocinio(tirocinio);
      
      if (ore == 0) {
        tirocinio.setStatus(Tirocinio.TERMINATO);
        risultato5 = TirocinioDao.aggiornaStatusTirocinio(tirocinio);
      }
    }
    
    if (!risultato1 || !risultato2 || !risultato3 || !risultato4 || !risultato5) {
      throw new Exception("Database Error");
    }
  }
}
