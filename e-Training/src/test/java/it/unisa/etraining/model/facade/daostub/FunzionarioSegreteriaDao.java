package it.unisa.etraining.model.facade.daostub;

import it.unisa.etraining.model.bean.FunzionarioSegreteria;

public class FunzionarioSegreteriaDao {
  
  public static boolean loginFunzionario(FunzionarioSegreteria funzionarioSegreteria) {
    if (funzionarioSegreteria.getEmail().equals("a.ahamed@unisa.it") 
        && funzionarioSegreteria.getPassword().equals("password")) {
      funzionarioSegreteria.setNome("Aldeandra");
    }
    
    return true;
  }
}
