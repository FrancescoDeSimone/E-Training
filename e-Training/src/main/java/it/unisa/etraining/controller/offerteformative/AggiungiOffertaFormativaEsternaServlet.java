package it.unisa.etraining.controller.offerteformative;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.facade.OfferteFormativeFacade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet AggiungereOffertaFormativaEsternaServlet permette di aggiungere
 * un'offerta formativa di tirocinio esterno.
 * @author Francesco De Simone
 */
@WebServlet("/AggiungiOffertaFormativaEsternaServlet")
public class AggiungiOffertaFormativaEsternaServlet extends HttpServlet {
  
  /**
   * Metodo doPost.
   * 
   * @param request la richiesta che il client ha mandato alla servlet
   * @param response la risposta che manda la servlet al client
   * 
   * @throws ServletException nel caso non può essere gestita la richiesta
   * @throws IOException nel caso sorge un errore di IO
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    HttpSession sessione = request.getSession(false);
    
    if (sessione == null) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
      return;
    }
    
    String fac1 = request.getParameter("label0");
    String fac2 = request.getParameter("label1");
    String fac3 = request.getParameter("label2");
    String fac4 = request.getParameter("label3");
    String fac5 = request.getParameter("label4");
        
    ArrayList<String> facilitazioniTmp = new ArrayList<>();
    facilitazioniTmp.add(fac1);
    facilitazioniTmp.add(fac2);
    facilitazioniTmp.add(fac3);
    facilitazioniTmp.add(fac4);
    facilitazioniTmp.add(fac5);

    facilitazioniTmp.removeAll(Collections.singleton(null));
    facilitazioniTmp.removeAll(Collections.singleton(""));
    
    String disabilita = request.getParameter("disabilita");
    if (null != disabilita) {
      facilitazioniTmp.add("disabilita");
    }
    
    String[] facilitazioni = new String[facilitazioniTmp.size()];
    for (int i = 0; i < facilitazioniTmp.size(); i++) {
      facilitazioni[i] = facilitazioniTmp.get(i);
    }
    
    String tema = request.getParameter("tema");
    String dataInizio = request.getParameter("dataInizio");
    String dataFine = request.getParameter("dataFine");
    String tutorDidatticoString = request.getParameter("tutorAccademico");
    String tutorAziendaleString = request.getParameter("tutorAziendale");
    
    if (tema == null || dataInizio == null || dataFine == null
        || tutorAziendaleString == null || tutorDidatticoString == null
        || !tema.matches(regexTema) || !dataInizio.matches(regexData)
        || !dataFine.matches(regexData) || !tutorAziendaleString.matches(regexTutor) 
        || !tutorDidatticoString.matches(regexTutor)) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }
    
    if (facilitazioniTmp.size() != 0) {     
      for (String s : facilitazioni) {
        if (s == null || !s.matches(regexFacilitazione)) {
          RequestDispatcher dispatcher = request
              .getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1");
          dispatcher.forward(request, response);
          return;
        }
      }
    }
    
    String[] dataInizioSplit = dataInizio.split("-");
    String[] dataFineSplit = dataFine.split("-"); 

    GregorianCalendar dataInizioGc = new GregorianCalendar(
        Integer.parseInt(dataInizioSplit[0]), 
        Integer.parseInt(dataInizioSplit[1]), 
        Integer.parseInt(dataInizioSplit[2]));
    
    GregorianCalendar dataFineGc = new GregorianCalendar(
        Integer.parseInt(dataFineSplit[0]), 
        Integer.parseInt(dataFineSplit[1]), 
        Integer.parseInt(dataFineSplit[2]));
    
    if (dataFineGc.before(dataInizioGc)) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/aggiungiOffertaTirocioniEsterno.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }

    TutorDidattico tutorDidattico = new TutorDidattico();
    tutorDidattico.setEmail(tutorDidatticoString);
    TutorAziendale tutorAziendale = new TutorAziendale();
    tutorAziendale.setEmail(tutorAziendaleString);
    
    Azienda azienda = (Azienda) sessione.getAttribute("utente");
    
    OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
    offerta.setTema(tema);
    offerta.setTutorAziendale(tutorAziendale);
    offerta.setTutorDidattico(tutorDidattico);
    offerta.setAzienda(azienda);
    offerta.setInizioTirocinio(dataInizioGc);
    offerta.setFineTirocinio(dataFineGc);
    
    offerta.setValidita(false);
    offerta.setStatus(OffertaFormativaTirocinioEsterno.DA_VALUTARE);
    
    if (facilitazioniTmp.size() != 0) {
      offerta.setFacilitazioni(facilitazioni);
    }

    try {
      OfferteFormativeFacade offerteFormativeFacade = new OfferteFormativeFacade();
    
      offerteFormativeFacade.aggiungereOffertaFormativaEsterna(offerta);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel salvare l'offerta formativa.");
      dispatcher.forward(request, response);
      return;
    }

    request.setAttribute("aggiunta","1");
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/MostraOfferteFormativeEsterneServlet");
    dispatcher.forward(request, response);
    return;
  }
  
  private static final long serialVersionUID = 1L;
  private static final String regexTema = "^(?=\\S)(?=[a-zA-Z0-9òàèù' ])([a-zA-Z0-9òàèù' ]"
      + "{4,249})$";
  private static final String regexData = "^(?:\\d{4})-(?:(?:(09-|04-|06-|11-)"
      + "(?:01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|"
      + "23|24|25|26|27|28|29|30))|(?:(?:01-|03-|05-|07-|08-|10-|12-)(?:01|02|"
      + "03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|"
      + "26|27|28|29|30|31))|(?:02-(?:01|02|03|04|05|06|07|08|09|10|11|12|13|"
      + "14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29)))$";
  private static final String regexTutor = 
      "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))"
      + "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)"
      + "+[a-zA-Z]{2,}))$";
  private static final String regexFacilitazione = "^(?=\\S)(?=[a-zA-Z0-9òàèù' ])"
      + "([a-zA-Z0-9òàèù' ]{4,249})$";
}
