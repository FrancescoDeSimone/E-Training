package it.unisa.etraining.model.facade.daostub;

import java.util.ArrayList;

import it.unisa.etraining.model.bean.Azienda;

public class AziendaDao {

  public static boolean loginAzienda(Azienda azienda) {
    if (azienda.getEmail().equals("aldeandra.Garofalo@gmail.com") 
        && azienda.getPassword().equals("password")) {
      azienda.setNome("Aldeandra");
    }
    
    return true;
  }
  
  public static boolean ricercaAzienda(Azienda azienda) {
    if (azienda.getEmail().equals("aldeandra.Garofalo@gmail.com")) 
      azienda.setNome("Garofalo");
    
    return true;
  }
  
  public static boolean ricercaTutteAzienda(ArrayList<Azienda> tutteAziende) {
    tutteAziende.add(new Azienda());
    
    return true;
  }
}
