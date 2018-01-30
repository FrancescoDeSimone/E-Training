package it.unisa.etraining.controller.offerteformative;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
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
 * La servlet MostraOfferteFormativeEsterneServlet permette di ricercare
 * le offerte formative di tirocinio esterno di un'azienda.
 * @author Francesco De Simone
 */
@WebServlet("/MostraOfferteFormativeEsterneServlet")
public class MostraOfferteFormativeEsterneServlet extends HttpServlet {

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
    
    ArrayList<OffertaFormativaTirocinioEsterno> listaOfferte = null;
    try {
      listaOfferte = offerteFormativeFacade.mostraOfferteFormativeEsterne(azienda);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel connettersi al database.");
      dispatcher.forward(request, response);
      return;
    }
    
    request.setAttribute("listaOfferte",listaOfferte);
    
    ArrayList<TutorDidattico> listaTutorD = null;
    ArrayList<TutorAziendale> listaTutorA = null;
    try {
      listaTutorD = offerteFormativeFacade.mostraTuttiTutorDidattici();
      listaTutorA = offerteFormativeFacade.mostraTuttiTutorAziendali(azienda);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?Errore nel connettersi al database.");
      dispatcher.forward(request, response);
      return;
    }
    
    sessione.setAttribute("tutorDidattici",listaTutorD);
    sessione.setAttribute("tutorAziendali",listaTutorA);
    
    String aggiunta = (String) request.getAttribute("aggiunta");
    String url = "/mostraOfferteFormativeEsterne.jsp";
       
    if (aggiunta != null) {
      url += "?aggiunta=1";
    }
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher(url);
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
    doGet(request, response);
  }
  
  private static final long serialVersionUID = 1L;
}
