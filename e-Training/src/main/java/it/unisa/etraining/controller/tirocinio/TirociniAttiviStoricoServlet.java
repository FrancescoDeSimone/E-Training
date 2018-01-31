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
 * La servlet TirociniAttiviStoricoServlet permette di ricercare tutti i tirocini
 * attivi e conclusi di un tirocinante, tutor o azienda.
 *
 * @author Antonino Celentano
 */
@WebServlet("/TirociniAttiviStoricoServlet")
public class TirociniAttiviStoricoServlet extends HttpServlet {

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
    
    ArrayList<Tirocinio> tirociniAttivi = null;
    ArrayList<Tirocinio> tirociniStorico = null;
    try {
      TirocinioFacade tirocinioFacade = new TirocinioFacade();
    
      tirociniAttivi = tirocinioFacade.mostraTirociniAttivi(utente);
      tirociniStorico = tirocinioFacade.mostraTirociniStorico(utente);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?Errore nel connettersi al database.");
      dispatcher.forward(request, response);
      return;
    }
    
    request.setAttribute("tirociniAttivi",tirociniAttivi);
    request.setAttribute("tirociniStorico",tirociniStorico);
    
    String registrazione = (String) request.getAttribute("registrazione");
    String richiestaTirocinio = (String) request.getAttribute("richiestaTirocinio");
    String url = "/tirociniAttiviStorico.jsp";
       
    if (registrazione != null) {
      url += "?registrazione=1";
    } else if (richiestaTirocinio != null) {
      url += "?richiestaTirocinio=1";
    }
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(url);
    dispatcher.forward(request, response);
  }
  
  /**
   * Metodo doPost.
   * 
   * @param request la servlet request
   * @param response la servlet response
   * 
   * @throws ServletException nel caso l'eccezione venga sollevata dalla servlet
   * @throws IOException nel caso l'eccezione venga sollevata da un errore di IO
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
  
  private static final long serialVersionUID = 1L;
}
