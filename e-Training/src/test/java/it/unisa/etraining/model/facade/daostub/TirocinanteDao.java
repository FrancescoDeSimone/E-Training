package it.unisa.etraining.model.facade.daostub;

import it.unisa.etraining.model.bean.Tirocinante;

public class TirocinanteDao {

  public static boolean loginTirocinante(Tirocinante unTirocinante) {
    if (unTirocinante.getEmail().equals("d.abbondio104@studenti.unisa.it") 
        && unTirocinante.getPassword().equals("password")) {
      unTirocinante.setNome("Don");
    }
    
    return true;
  }
  
  public static boolean salvaTirocinante(Tirocinante unTirocinante) {
    if(unTirocinante.getEmail().equals("d.abbondio104@studenti.unisa.it")
      && unTirocinante.getPassword().equals("password")
      && unTirocinante.getNome().equals("Don")
      && unTirocinante.getCognome().equals("Abbondio")
      && unTirocinante.getMatricola().equals("0512103121")
      && unTirocinante.getAnnoIscrizione().equals("2015/2016")) {
      return true;
    }
    
    return false;
  }
  
  public static boolean rimuoviTirocinante(Tirocinante unTirocinante) {
    if(unTirocinante.getEmail().equals("d.abbondio104@studenti.unisa.it"))
      return true;
    
    return false;
  }

  public static boolean ricercaTirocinante(Tirocinante unTirocinante) {
    if(unTirocinante.getEmail().equals("d.abbondio104@studenti.unisa.it"))
        return true;
    
    return false;
  }

}
