package it.unisa.etraining.controller.tirocinio;

import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.bean.Utente;
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
 * La servlet ValutareRichiestaTirocinioServlet permette di ricercare tutte 
 * le richieste di tirocinio pervenute a un'azienda a un tutor didattico.
 *
 * @author Antonino Celentano
 */
@WebServlet("/ValutareRichiestaTirocinioServlet")
public class ValutareRichiestaTirocinioServlet extends HttpServlet {
  
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
    
    Utente utente = (Utente) sessione.getAttribute("utente");
    
    TirocinioFacade tirocinioFacade = new TirocinioFacade();
    
    ArrayList<Tirocinio> listaRichieste = new ArrayList<>();
    try {
      listaRichieste = tirocinioFacade.mostraRichiesteTirocinio(utente);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel connettersi al database.");
      dispatcher.forward(request, response);
      return;
    }
    
    sessione.setAttribute("listaRichieste",listaRichieste);

    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/accettareRifiutareRichiestaTirocinio.jsp");
    dispatcher.forward(request, response);
  }
  
  private static final long serialVersionUID = 1L;
}
