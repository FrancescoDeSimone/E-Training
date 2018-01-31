package it.unisa.etraining.controller.profilo;

import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.facade.ProfiloFacade;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet RimuoviTirocinanteServlet permette di rimuovere un 
 * tirocinante dal sistema.
 * @author Basso Emanuele
 */
@WebServlet("/RimuoviTirocinanteServlet")
public class RimuoviTirocinanteServlet extends HttpServlet {

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
    
    Tirocinante tirocinante = (Tirocinante) sessione.getAttribute("utente");
    
    ProfiloFacade profiloFacade = new ProfiloFacade();
    
    boolean tirociniAncoraAttivi = false;
    try {      
      tirociniAncoraAttivi = profiloFacade.verificaTirociniAttiviTirocinante(tirocinante);
      
      if (tirociniAncoraAttivi) {
        profiloFacade.rimuoviTirocinante(tirocinante);
      }
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?Errore nel rimuovere l'account.");
      dispatcher.forward(request, response);
      return;
    }
    
    if (tirociniAncoraAttivi) {
      sessione.removeAttribute("nome");
      sessione.removeAttribute("tipo");
      sessione.removeAttribute("email");
      
      sessione.invalidate();
      
      request.setAttribute("accountEliminato","1");
      
      RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
      return;
    } else {
      request.setAttribute("tirociniAttivi","1");
      
      RequestDispatcher dispatcher = request.getRequestDispatcher("/rimuoviTirocinante.jsp");
      dispatcher.forward(request, response);
    }

  }
  
  private static final long serialVersionUID = 1L;

}
