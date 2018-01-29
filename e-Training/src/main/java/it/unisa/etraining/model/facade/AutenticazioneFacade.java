package it.unisa.etraining.model.facade;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.FunzionarioSegreteria;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.bean.Utente;

//        Stubs
//import it.unisa.etraining.model.facade.daostub.*;

//        Implementazione   
import it.unisa.etraining.model.dao.AziendaDao;
import it.unisa.etraining.model.dao.FunzionarioSegreteriaDao;
import it.unisa.etraining.model.dao.TirocinanteDao;
import it.unisa.etraining.model.dao.TutorAziendaleDao;
import it.unisa.etraining.model.dao.TutorDidatticoDao;

/*
 * Questa classe fornisce un'interfaccia unificata per la gestione completa del
 * sottosistema relativo all'autenticazione.
 * @author Flavio Esposito
 */
public class AutenticazioneFacade {
  
  /**
   * Questo metodo permette di effettuare l'autenticazione di un utente nel
   * sistema.
   * @param utente l'utente di cui effettuare l'autenticazione.
   * @return l'utente che è stato autenticato correttamente oppure null.
   * @throws Exception lanciata nel caso in cui si verificano errori sul 
   *     database.
   */
  public Utente login(Utente utente) throws Exception {
    Utente utenteCercato = null;
    boolean risultato = false;
    
    if (utente instanceof Tirocinante) {
      Tirocinante unTirocinante = (Tirocinante) utente;
      risultato = TirocinanteDao.loginTirocinante(unTirocinante);
       
      if (!risultato) {
        throw new Exception("Database Error");
      }
      
      if (unTirocinante.getNome() != null) {
        utenteCercato = unTirocinante;
      }
    } else if (utente instanceof TutorDidattico) {
      TutorDidattico tutorDidattico = (TutorDidattico) utente;
      risultato = TutorDidatticoDao.loginTutorDidattico(tutorDidattico);
      
      if (!risultato) {
        throw new Exception("Database Error");
      }
      
      if (tutorDidattico.getNome() != null) {
        utenteCercato = tutorDidattico;
      }
    } else if (utente instanceof TutorAziendale) {
      TutorAziendale tutorAziendale = (TutorAziendale) utente;
      risultato = TutorAziendaleDao.loginTutorAziendale(tutorAziendale);
      
      if (!risultato) {
        throw new Exception("Database Error");
      }
      
      if (tutorAziendale.getNome() != null) {
        utenteCercato = tutorAziendale;
      }
    } else if (utente instanceof Azienda) {
      Azienda azienda = (Azienda) utente;
      risultato = AziendaDao.loginAzienda(azienda);
      
      if (!risultato) {
        throw new Exception("Database Error");
      }
      
      if (azienda.getNome() != null) {
        utenteCercato = azienda;
      }
    } else if (utente instanceof FunzionarioSegreteria) {
      FunzionarioSegreteria funzionarioSegreteria = (FunzionarioSegreteria) utente;
      risultato = FunzionarioSegreteriaDao.loginFunzionario(funzionarioSegreteria);
      
      if (!risultato) {
        throw new Exception("Database Error");
      }
      
      if (funzionarioSegreteria.getNome() != null) {
        utenteCercato = funzionarioSegreteria;
      }
    }
    
    return utenteCercato;
  }
  
}
