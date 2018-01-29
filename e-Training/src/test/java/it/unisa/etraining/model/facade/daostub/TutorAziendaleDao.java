package it.unisa.etraining.model.facade.daostub;

import java.util.ArrayList;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.TutorAziendale;

public class TutorAziendaleDao {

  public static boolean loginTutorAziendale(TutorAziendale tutorAziendale) {
    if (tutorAziendale.getEmail().equals("alearda.lucchesini@gmail.com") 
        && tutorAziendale.getPassword().equals("password")) {
      tutorAziendale.setNome("Aldeandra");
    }
    
    return true;
  }
  
  public static boolean ricercaTutorAziendale(TutorAziendale tutorAziendale) {
    if (tutorAziendale.getEmail().equals("alearda.lucchesini@gmail.com"))
      return true;
    else
      return false;
  }  
  
  public static boolean ricercaTuttiTutorAziendali(ArrayList<TutorAziendale> listaTutor, Azienda azienda) {
    if (azienda.getEmail().equals("aldeandra.Garofalo@gmail.com")) {
      listaTutor.add(new TutorAziendale());
    }
      
    return true;
  }
}
