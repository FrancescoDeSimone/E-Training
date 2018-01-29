package it.unisa.etraining.model.facade.daostub;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unisa.etraining.model.bean.AttivitaRegistro;
import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.bean.Utente;

public class TirocinioDao {
  public static boolean ricercaTirociniAttivi(ArrayList<Tirocinio> tirociniAttivi, 
      Utente utente) {
    if(utente.getEmail().equals("d.abbondio104@studenti.unisa.it")) {
      Azienda azienda = new Azienda();
      azienda.setEmail("aldeandra.Garofalo@gmail.com");
      
      TutorAziendale tutorAziendale = new TutorAziendale();
      tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
      
      TutorDidattico tutorDidattico = new TutorDidattico();
      tutorDidattico.setEmail("a.cataldi@unisa.it");
      
      OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
      offerta.setTutorDidattico(tutorDidattico);
      offerta.setTutorAziendale(tutorAziendale);
      offerta.setTema("Database distribuiti sequenziali");
      offerta.setInizioTirocinio(new GregorianCalendar(2018,01,11));
      offerta.setFineTirocinio(new GregorianCalendar(2018,01,14));
      offerta.setValidita(false);
      offerta.setAzienda(azienda);
      offerta.setStatus(OffertaFormativaTirocinioEsterno.DA_VALUTARE);
      offerta.setId(1);
      
      Tirocinio tirocinio = new Tirocinio();
      tirocinio.setTirocinante((Tirocinante) utente);
      tirocinio.setOfferta(offerta);
      
      AttivitaRegistro attivita = new AttivitaRegistro();
      attivita.setTirocinio(tirocinio);
      attivita.setAttivitaSvolta("Non so che dire");
      attivita.setInizio(new GregorianCalendar(2018,01,11));
      attivita.setFine(new GregorianCalendar(2018,01,14));
      
      
       tirociniAttivi.add(tirocinio);
    }
    
    return true;
  }
  
  public static boolean ricercaTirociniStorico(ArrayList<Tirocinio> tirociniStorico, 
      Utente utente) {
    if(utente.getEmail().equals("d.abbondio104@studenti.unisa.it")) {
      Azienda azienda = new Azienda();
      azienda.setEmail("aldeandra.Garofalo@gmail.com");
      
      TutorAziendale tutorAziendale = new TutorAziendale();
      tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
      
      TutorDidattico tutorDidattico = new TutorDidattico();
      tutorDidattico.setEmail("a.cataldi@unisa.it");
      
      OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
      offerta.setTutorDidattico(tutorDidattico);
      offerta.setTutorAziendale(tutorAziendale);
      offerta.setTema("Database distribuiti sequenziali");
      offerta.setInizioTirocinio(new GregorianCalendar(2018,01,11));
      offerta.setFineTirocinio(new GregorianCalendar(2018,01,14));
      offerta.setValidita(false);
      offerta.setAzienda(azienda);
      offerta.setStatus(OffertaFormativaTirocinioEsterno.DA_VALUTARE);
      offerta.setId(1);
      
      Tirocinio tirocinio = new Tirocinio();
      tirocinio.setTirocinante((Tirocinante) utente);
      tirocinio.setOfferta(offerta);
      
      AttivitaRegistro attivita = new AttivitaRegistro();
      attivita.setTirocinio(tirocinio);
      attivita.setAttivitaSvolta("Non so che dire");
      attivita.setInizio(new GregorianCalendar(2018,01,11));
      attivita.setFine(new GregorianCalendar(2018,01,14));
      
      
      tirociniStorico.add(tirocinio);
    }
    
    return true;
  }
  
  public static boolean ricercaTirocinio(Tirocinio unTirocinio) {
    if (unTirocinio.getTirocinante().getEmail().equals("d.abbondio104@studenti.unisa.it")    
        && unTirocinio.getOfferta().getId() == 1) {
      unTirocinio.setOreRimanenti(104);
    }
    
    return true;
  }
  
  public static boolean aggiornOreTirocinio(Tirocinio unTirocinio) {
    if (unTirocinio.getTirocinante().getEmail().equals("d.abbondio104@studenti.unisa.it")    
        && unTirocinio.getOfferta().getId() == 1
        && unTirocinio.getOreRimanenti() == 100) {
      return true;
    } 
    
    return false;
  }
  
  public static boolean salvaTirocinio(Tirocinio unTirocinio) {
    if(unTirocinio.getTirocinante().getEmail().equals("d.abbondio104@studenti.unisa.it")
       && unTirocinio.getOfferta().getId() == 1
       && unTirocinio.getCfu() == 6
       && unTirocinio.getStatus().equals(Tirocinio.VALUTAZIONE_DOCENTE)) {
      return true;
    }
    
    return false;
  }
  
  public static boolean ricercaTirociniInviati(ArrayList<Tirocinio> tirociniInviati, 
      Tirocinante tirocinante) {
    if (tirocinante.getEmail().equals("d.abbondio104@studenti.unisa.it")) {
      Azienda azienda = new Azienda();
      azienda.setEmail("aldeandra.Garofalo@gmail.com");
      
      TutorAziendale tutorAziendale = new TutorAziendale();
      tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
      
      TutorDidattico tutorDidattico = new TutorDidattico();
      tutorDidattico.setEmail("a.cataldi@unisa.it");
      
      OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
      offerta.setTutorDidattico(tutorDidattico);
      offerta.setTutorAziendale(tutorAziendale);
      offerta.setTema("Database distribuiti sequenziali");
      offerta.setInizioTirocinio(new GregorianCalendar(2018,01,11));
      offerta.setFineTirocinio(new GregorianCalendar(2018,01,14));
      offerta.setValidita(false);
      offerta.setAzienda(azienda);
      offerta.setStatus(OffertaFormativaTirocinioEsterno.DA_VALUTARE);
      offerta.setId(1);
      
      Tirocinio tirocinio = new Tirocinio();
      tirocinio.setTirocinante(tirocinante);
      tirocinio.setOfferta(offerta);
      
      AttivitaRegistro attivita = new AttivitaRegistro();
      attivita.setTirocinio(tirocinio);
      attivita.setAttivitaSvolta("Non so che dire");
      attivita.setInizio(new GregorianCalendar(2018,01,11));
      attivita.setFine(new GregorianCalendar(2018,01,14));
      
      tirociniInviati.add(tirocinio);
    }
    
    return true;
  }
  
  public static boolean ricercaRichiesteTirocinio(
      ArrayList<Tirocinio> tirociniRichieste, Utente utente) {
    if(utente.getEmail().equals("aldeandra.Garofalo@gmail.com")) {
      Azienda azienda = new Azienda();
      azienda.setEmail("aldeandra.Garofalo@gmail.com");
      
      TutorAziendale tutorAziendale = new TutorAziendale();
      tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
      
      TutorDidattico tutorDidattico = new TutorDidattico();
      tutorDidattico.setEmail("a.cataldi@unisa.it");
      
      OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
      offerta.setTutorDidattico(tutorDidattico);
      offerta.setTutorAziendale(tutorAziendale);
      offerta.setTema("Database distribuiti sequenziali");
      offerta.setInizioTirocinio(new GregorianCalendar(2018,01,11));
      offerta.setFineTirocinio(new GregorianCalendar(2018,01,14));
      offerta.setValidita(false);
      offerta.setAzienda(azienda);
      offerta.setStatus(OffertaFormativaTirocinioEsterno.DA_VALUTARE);
      offerta.setId(1);
      
      Tirocinante tirocinante = new Tirocinante();
      tirocinante.setEmail("d.abbondio104@studenti.unisa.it");

      Tirocinio tirocinio = new Tirocinio();
      tirocinio.setTirocinante(tirocinante);
      tirocinio.setOfferta(offerta);
      
      AttivitaRegistro attivita = new AttivitaRegistro();
      attivita.setTirocinio(tirocinio);
      attivita.setAttivitaSvolta("Non so che dire");
      attivita.setInizio(new GregorianCalendar(2018,01,11));
      attivita.setFine(new GregorianCalendar(2018,01,14));
      
      tirociniRichieste.add(tirocinio);
    }
    
    return true;
  }
  
  public static boolean aggiornaStatusTirocinio(Tirocinio unTirocinio) {
    if(unTirocinio.getStatus().equals(Tirocinio.IN_CORSO)
       && unTirocinio.getTirocinante().getEmail().equals("d.abbondio104@studenti.unisa.it")
       && unTirocinio.getOfferta().getId() == 1) {
      return true;
    }
    
    return false;
  }
}
