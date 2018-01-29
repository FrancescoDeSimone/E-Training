package it.unisa.etraining.model.facade.daostub;

import java.util.ArrayList;

import it.unisa.etraining.model.bean.TutorDidattico;

public class TutorDidatticoDao {

  public static boolean loginTutorDidattico(TutorDidattico tutorDidattico) {
    if (tutorDidattico.getEmail().equals("a.cataldi@unisa.it") 
        && tutorDidattico.getPassword().equals("password")) {
      tutorDidattico.setNome("Aldo");
    }
    
    return true;
  }
  
  public static boolean ricercaTutorDidattico(TutorDidattico tutorDidattico) {
    if (tutorDidattico.getEmail().equals("a.cataldi@unisa.it"))
      return true;
    else
      return false;
  }
  
  public static boolean ricercaTuttiTutorDidattici(ArrayList<TutorDidattico> listaTutor) {
    listaTutor.add(new TutorDidattico());
    
    return true;
  }
}
