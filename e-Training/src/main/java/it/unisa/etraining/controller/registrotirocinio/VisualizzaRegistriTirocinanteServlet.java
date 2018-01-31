package it.unisa.etraining.controller.registrotirocinio;

import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.facade.RegistroTirocinioFacade;
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
 * La servlet VisualizzaRegistriTirocinanteServlet permette di ricercare tutti i
 * registri di un tirocinante.
 * @author Basso Emanuele
 */
@WebServlet("/VisualizzaRegistriTirocinanteServlet")
public class VisualizzaRegistriTirocinanteServlet extends HttpServlet {

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
    
    RegistroTirocinioFacade registroTirocinioFacade = new RegistroTirocinioFacade();
    
    ArrayList<Tirocinio> listaRegistri = new ArrayList<>();
    try {
      listaRegistri = registroTirocinioFacade.mostraRegistriTirocini(tirocinante);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel connettersi al database.");
      dispatcher.forward(request, response);
      return;
    }
    
    sessione.setAttribute("listaRegistri",listaRegistri);
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/mostraInformazioniRegistriTirocinante.jsp");
    dispatcher.forward(request, response);
  }
  
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
    doGet(request,response);
  }
  
  private static final long serialVersionUID = 1L;
}
