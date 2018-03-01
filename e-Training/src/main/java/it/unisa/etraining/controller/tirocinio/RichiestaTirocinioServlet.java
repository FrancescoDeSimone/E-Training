package it.unisa.etraining.controller.tirocinio;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.facade.TirocinioFacade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet RichiestaTirocinioServlet permette di salvare una richiesta
 * di tirocinio inviata da un tirocinante.
 *
 * @author Antonino Celentano
 */
@WebServlet("/RichiestaTirocinioServlet")
public class RichiestaTirocinioServlet extends HttpServlet {       

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
    
    Tirocinante tirocinante = (Tirocinante) sessione.getAttribute("utente");
    
    String obb1 = request.getParameter("label0");
    String obb2 = request.getParameter("label1");
    String obb3 = request.getParameter("label2");
    String obb4 = request.getParameter("label3");
    String obb5 = request.getParameter("label4");
    
    ArrayList<String> obiettiviTmp = new ArrayList<>();
    obiettiviTmp.add(obb1);
    obiettiviTmp.add(obb2);
    obiettiviTmp.add(obb3);
    obiettiviTmp.add(obb4);
    obiettiviTmp.add(obb5);

    obiettiviTmp.removeAll(Collections.singleton(null));
    obiettiviTmp.removeAll(Collections.singleton(""));
    
    String[] obiettivi = new String[obiettiviTmp.size()];
    for (int i = 0; i < obiettiviTmp.size(); i++) {
      obiettivi[i] = obiettiviTmp.get(i);
    }
    
    String aziendaEmail = request.getParameter("azienda");
    String tema = request.getParameter("offertaFormativa");
    String cfu = request.getParameter("cfu");
    
    if (aziendaEmail == null || tema == null || cfu == null
        || !aziendaEmail.matches(regexEmail) || !tema.matches(regexTema)
        || !cfu.matches(regexCfu) || obiettiviTmp.size() == 0) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/inviaRichiesteDiTirocinio.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }
    for (String s : obiettivi) {
      if (s == null || !s.matches(regexObiettivi)) {
        RequestDispatcher dispatcher = request
            .getRequestDispatcher("/inviaRichiesteDiTirocinio.jsp?errore=1");
        dispatcher.forward(request, response);
        return;
      }
    }
    
    int cfuInteger = Integer.parseInt(cfu);
    
    Azienda azienda = new Azienda();
    azienda.setEmail(aziendaEmail);
    
    OffertaFormativaTirocinioEsterno offertaFormativa = new OffertaFormativaTirocinioEsterno();
    offertaFormativa.setAzienda(azienda);
    offertaFormativa.setTema(tema);
    
    try {
      TirocinioFacade tirocinioFacade = new TirocinioFacade();

      if (tirocinioFacade.contrallaRichiestaTirocinioGiaInviata(tirocinante,offertaFormativa)) {
        RequestDispatcher dispatcher = request
            .getRequestDispatcher("/effettuaRichiestaTirocinio.jsp?errore=2");
        dispatcher.forward(request, response);
        return;
      }
          
      tirocinioFacade.richiediTirocinio(tirocinante, offertaFormativa, cfuInteger, obiettivi);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel "
              + "salvare la richiesta di tirocinio.");
      dispatcher.forward(request, response);
      return;
    }

    request.setAttribute("richiestaTirocinio","1");
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/TirociniAttiviStoricoServlet");
    dispatcher.forward(request, response);
  }
  
  private static final long serialVersionUID = 1L;
  private static final String regexEmail = 
      "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))"
      + "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)"
      + "+[a-zA-Z]{2,}))$";
  private static final String regexTema = "^(?=\\S)(?=[a-zA-Z0-9òàèù' ])"
      + "([a-zA-Z0-9òàèù' ]{4,249})$";
  private static final String regexCfu = "^(6|12)$";
  private static final String regexObiettivi = "^(?=\\S)(?=[a-zA-Z0-9òàèù' ])"
      + "([a-zA-Z0-9òàèù' ]{0,49})$";
}
