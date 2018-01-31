package it.unisa.etraining.controller.tirocinio;

import it.unisa.etraining.model.bean.Azienda;
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
 * La servlet EffettuareRichiestaTirocinioServlet permette di mostrare il form
 * per la richiesta di tirocinio da parte di un tirocinante.
 *
 * @author Antonino Celentano
 */
@WebServlet("/EffettuareRichiestaTirocinioServlet")
public class EffettuareRichiestaTirocinioServlet extends HttpServlet {

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
    request.setCharacterEncoding("UTF-8");

    HttpSession sessione = request.getSession(false);
    
    if (sessione == null) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
      return;
    }
    
    ArrayList<Azienda> listaAzienda = new ArrayList<>();
    try {
      TirocinioFacade tirocinioFacade = new TirocinioFacade();
    
      listaAzienda = tirocinioFacade.mostraTutteAziende();
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?Errore nel connettersi al database.");
      dispatcher.forward(request, response);
      return;
    }
    
    sessione.setAttribute("aziende",listaAzienda);
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/effettuaRichiestaTirocinio.jsp");
    dispatcher.forward(request, response);
  }
  
  private static final long serialVersionUID = 1L;
}
