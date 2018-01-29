package it.unisa.etraining.model.facade.daostub;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import it.unisa.etraining.model.bean.AttivitaRegistro;
import it.unisa.etraining.model.bean.Tirocinio;

public class AttivitaRegistroDao {

  public static boolean ricercaTutteAttivitaRegistro(ArrayList<AttivitaRegistro> 
  tutteAttivita, Tirocinio tirocinio) {
    if (tirocinio.getTirocinante().getEmail().equals("d.abbondio104@studenti.unisa.it")    
        && tirocinio.getOfferta().getId() == 1) {
      tutteAttivita.add(new AttivitaRegistro());
    }

    return true;
  }
  
  public static boolean salvaAttivitaRegistro(AttivitaRegistro 
      unAttivita) {
    if (unAttivita.getTirocinio().getTirocinante().getEmail().equals("d.abbondio104@studenti.unisa.it")    
        && unAttivita.getTirocinio().getOfferta().getId() == 1
        && unAttivita.getAttivitaSvolta().equals("Non so che dire")
        && unAttivita.getInizio().equals(new GregorianCalendar(2018,01,11))
        && unAttivita.getFine().equals(new GregorianCalendar(2018,01,14))
        && unAttivita.getConvalida().equals(AttivitaRegistro.IN_CONVALIDA)) {
      return true;
    }
    
    return false;
  }
  
  public static boolean aggiornaConvalidaAttivita(AttivitaRegistro unAttivita) {
    if (unAttivita.getTirocinio().getTirocinante().getEmail().equals("d.abbondio104@studenti.unisa.it")    
        && unAttivita.getTirocinio().getOfferta().getId() == 1
        && unAttivita.getAttivitaSvolta().equals("Non so che dire")) {
      return true;
    }
    
    return false;
  }
  
  public static boolean ricercaAttivitaRegistro(AttivitaRegistro unAttivita) {
    if (unAttivita.getTirocinio().getTirocinante().getEmail().equals("d.abbondio104@studenti.unisa.it")    
        && unAttivita.getTirocinio().getOfferta().getId() == 1
        && unAttivita.getAttivitaSvolta().equals("Non so che dire")) {
      unAttivita.setOreSvolte(4);
    }
    
    return true;
  }
}
