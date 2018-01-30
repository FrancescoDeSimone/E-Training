package it.unisa.etraining.controller.offerteformative;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.facade.OfferteFormativeFacade;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet VisualizzaOfferteFormativeEsterneInviateServlet permette di ricercare
 * le proposte di offerte formative esterne inviate da un'azienda.
 * @author Francesco De Simone
 */
@WebServlet("/VisualizzaOfferteFormativeEsterneInviateServlet")
public class VisualizzaOfferteFormativeEsterneInviateServlet extends HttpServlet {
  
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
  
    Azienda azienda = (Azienda) sessione.getAttribute("utente");
    
    OfferteFormativeFacade offerteFormativeFacade = new OfferteFormativeFacade();

    ArrayList<OffertaFormativaTirocinioEsterno> listaProposte = null;
    try {
      listaProposte = offerteFormativeFacade.mostraOfferteFormativeEsterneInviate(azienda);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel connettersi al database.");
      dispatcher.forward(request, response);
      return;
    }
    
    sessione.setAttribute("listaProposte",listaProposte);
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/mostraProposteOfferteFormativeEsterneInviate.jsp");
    dispatcher.forward(request, response);
  }
  
  private static final long serialVersionUID = 1L;
}
