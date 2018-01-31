package it.unisa.etraining.controller.tirocinio;

import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.facade.TirocinioFacade;
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
 * La servlet RichiesteTirocinioInviateServlet permette di ricercare tutte
 * le richieste di tirocinio inviate da un tirocinante.
 *
 * @author Antonino Celentano
 */
@WebServlet("/RichiesteTirocinioInviateServlet")
public class RichiesteTirocinioInviateServlet extends HttpServlet {

  /**
   * Metodo doGet.
   * 
   * @param request la servlet request
   * @param response la servlet response
   * 
   * @throws ServletException nel caso l'eccezione venga sollevata dalla servlet
   * @throws IOException nel caso l'eccezione venga sollevata da un errore di IO
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
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
    
    Tirocinante tirocinante = (Tirocinante) sessione.getAttribute("utente");

    ArrayList<Tirocinio> listaProposte = null;
    try {
      TirocinioFacade tirocinioFacade = new TirocinioFacade();
      
      listaProposte = tirocinioFacade.mostraRichiesteTirocinioInviate(tirocinante);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?Errore nel connettersi al database.");
      dispatcher.forward(request, response);
      return;
    }
    
    request.setAttribute("listaProposte",listaProposte);
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/visualizzaRichiesteTirocinioInviate.jsp");
    dispatcher.forward(request, response);
  }
  
  private static final long serialVersionUID = 1L;
}
