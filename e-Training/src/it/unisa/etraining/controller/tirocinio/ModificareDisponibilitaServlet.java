package it.unisa.etraining.controller.tirocinio;

import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.facade.ProfiloFacade;
import it.unisa.etraining.model.facade.TirocinioFacade;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet <code>ModificareDisponibilitaServlet</code> gestisce la disponibilità dei
 * tutor didattici.
 */
@WebServlet("/ModificareDisponibilitaServlet")
public class ModificareDisponibilitaServlet extends HttpServlet {
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
    String disponibilita = request.getParameter("disponibilita");    
    boolean isDisponibile = false;
    if (disponibilita.equals("disponibile")) {
      isDisponibile = true;
    } else if (disponibilita.equals("nonDisponibile")) {
      isDisponibile = false;
    }    
    ProfiloFacade profiloFacade = new ProfiloFacade();
    TutorDidattico tutorDidattico = profiloFacade.getTutorDidattico((String) request.getSession()
        .getAttribute("email"));
    TirocinioFacade tirocinioFacade = new TirocinioFacade();
    boolean flag = tirocinioFacade.cambiaDisponibilita(tutorDidattico, isDisponibile);
    if (!flag) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/disponibilitàRichiesteTirocinio.jsp?errore=Database");
      dispatcher.forward(request, response);
    } else {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/inviaRichiesteDiTirocinio.jsp?errore=null");
      dispatcher.forward(request, response);
    }
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
