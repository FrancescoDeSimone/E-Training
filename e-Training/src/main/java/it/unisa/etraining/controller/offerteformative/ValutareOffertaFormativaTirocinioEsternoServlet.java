package it.unisa.etraining.controller.offerteformative;

import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.facade.OfferteFormativeFacade;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet ValutareOffertaFormativaTirocinioEsternoServlet 
 * permette di accettare o rifiutare un'offerta formativa di tirocinio esterno.
 * @author Francesco De Simone
 */
@WebServlet("/ValutareOffertaFormativaTirocinioEsternoServlet")
public class ValutareOffertaFormativaTirocinioEsternoServlet extends HttpServlet {
  
  /**
   * Metodo doGet.
   * 
   * @param request la richiesta che il client ha mandato alla servlet
   * @param response la risposta che manda la servlet al client
   * 
   * @throws ServletException nel caso non può essere gestita la richiesta
   * @throws IOException nel caso sorge un errore di IO
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
    
    HttpSession sessione = request.getSession(false);
    
    if (sessione == null) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
      return;
    }
        
    String scelta = request.getParameter("scelta");
    String idScelta = request.getParameter("id");

    if (scelta == null || idScelta == null || !scelta.matches(regexScelta) || idScelta.equals("")) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/mostraProposteOfferteFormativeEsterne.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }
    
    OffertaFormativaTirocinioEsterno offertaFormativaTirocinioEsterno = 
        new OffertaFormativaTirocinioEsterno();
    int id = Integer.parseInt(idScelta);
    offertaFormativaTirocinioEsterno.setId(id);
    
    try {
      OfferteFormativeFacade offerteFormativeFacade = new OfferteFormativeFacade();
    
      if (scelta.equals("accetta")) {
        offertaFormativaTirocinioEsterno.setStatus(OffertaFormativaTirocinioEsterno.ATTIVA);
      } else if (scelta.equals("rifiuta")) {
        offertaFormativaTirocinioEsterno.setStatus(OffertaFormativaTirocinioEsterno.NON_ATTIVA);
      }

      offerteFormativeFacade.accettareRifiutarePropostaOffertaFormativaEsterna(
          offertaFormativaTirocinioEsterno);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel connettersi al database.");
      dispatcher.forward(request, response);
      return;
    }

    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/VisualizzaProposteOfferteFormativeEsterneServlet");
    dispatcher.forward(request, response);
  }
    
  private static final long serialVersionUID = 1L;
  private static final String regexScelta = "^(accetta)|(rifiuta)$";
}
