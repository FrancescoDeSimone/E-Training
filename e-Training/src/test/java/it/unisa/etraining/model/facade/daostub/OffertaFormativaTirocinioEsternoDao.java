package it.unisa.etraining.model.facade.daostub;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;

public class OffertaFormativaTirocinioEsternoDao {

  public static boolean ricercaTutteOfferteFormativeTirocinioEsterno(
      ArrayList<OffertaFormativaTirocinioEsterno> offerte, Azienda azienda) {
    if (azienda.getEmail().equals("aldeandra.Garofalo@gmail.com")) {
      TutorAziendale tutorAziendale = new TutorAziendale();
      tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
      TutorDidattico tutorDidattico = new TutorDidattico();
      tutorDidattico.setEmail("a.cataldi@unisa.it");
      
      OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
      offerta.setTutorDidattico(tutorDidattico);
      offerta.setTutorAziendale(tutorAziendale);
      
      offerte.add(offerta);
    }
    
    return true;
  }
  
  public static boolean salvaOffertaFormativaTirocinioEsterno(OffertaFormativaTirocinioEsterno
      offertaFormativaTirocinioEsterno) {
    if (offertaFormativaTirocinioEsterno.getTutorDidattico().getEmail().equals("a.cataldi@unisa.it")
        && offertaFormativaTirocinioEsterno.getTutorAziendale().getEmail().equals("alearda.lucchesini@gmail.com")
        && offertaFormativaTirocinioEsterno.getTema().equals("Database distribuiti sequenziali")
        && offertaFormativaTirocinioEsterno.getInizioTirocinio().equals(new GregorianCalendar(2018,01,11))
        && offertaFormativaTirocinioEsterno.getFineTirocinio().equals(new GregorianCalendar(2018,01,14))
        && offertaFormativaTirocinioEsterno.isValidita() == false
        && offertaFormativaTirocinioEsterno.getStatus().equals(OffertaFormativaTirocinioEsterno.DA_VALUTARE)
        && offertaFormativaTirocinioEsterno.getAzienda().getEmail().equals("aldeandra.Garofalo@gmail.com")) {
      return true;
    }
    
    return false;
  }
  
  public static boolean ricercaOfferteFormativeTirocinioEsternoRicevuteTutorDidattico(
      ArrayList<OffertaFormativaTirocinioEsterno> listaOfferte, TutorDidattico tutorDidattico) {
    if(tutorDidattico.getEmail().equals("a.cataldi@unisa.it")) {
      OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
      
      TutorAziendale tutorAziendale = new TutorAziendale();
      tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
    
      Azienda azienda = new Azienda();
      azienda.setEmail("aldeandra.Garofalo@gmail.com");
      
      offerta.setTutorAziendale(tutorAziendale);
      offerta.setTutorDidattico(tutorDidattico);
      offerta.setAzienda(azienda);
      
      listaOfferte.add(offerta);
    }
    
    return true;
  }
  
  public static boolean ricercaOffertaFormativaById(
      OffertaFormativaTirocinioEsterno unOfferta) {
    if (unOfferta.getId() == 1)
      return true;
    else
      return false;
  }
  
  public static boolean aggiornaStatusOffertaFormativaTirocinioEsterno(
      OffertaFormativaTirocinioEsterno unOfferta) {
    if (unOfferta.getStatus().equals(OffertaFormativaTirocinioEsterno.DA_VALUTARE)
        && unOfferta.isValidita() == false
        && unOfferta.getAzienda().getEmail().equals("aldeandra.Garofalo@gmail.com")
        && unOfferta.getTema().equals("Database distribuiti sequenziali")) {
      return true;
    }
    
    return false;
  }
  
  public static boolean ricercaOfferteFormativaTirocinioEsternoInviate(
      ArrayList<OffertaFormativaTirocinioEsterno> offerte, Azienda azienda) {
    if (azienda.getEmail().equals("aldeandra.Garofalo@gmail.com")){
      OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
      
      TutorAziendale tutorAziendale = new TutorAziendale();
      tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
      TutorDidattico tutorDidattico = new TutorDidattico();
      tutorDidattico.setEmail("a.cataldi@unisa.it");
    
      offerta.setTutorAziendale(tutorAziendale);
      offerta.setTutorDidattico(tutorDidattico);
      
      offerte.add(offerta);
    }
      
    return true;
  }
  
  public static boolean ricercaOffertaFormativaTirocinioEsterno(
      OffertaFormativaTirocinioEsterno unOfferta) {
    if(unOfferta.getAzienda().getEmail().equals("aldeandra.Garofalo@gmail.com")
       && unOfferta.getTema().equals("Database distribuiti sequenziali")) {
      TutorAziendale tutorAziendale = new TutorAziendale();
      tutorAziendale.setEmail("alearda.lucchesini@gmail.com");
      TutorDidattico tutorDidattico = new TutorDidattico();
      tutorDidattico.setEmail("a.cataldi@unisa.it");
    
      unOfferta.setTutorAziendale(tutorAziendale);
      unOfferta.setTutorDidattico(tutorDidattico);
      return true;
    }
    
    return false;
  }
}



