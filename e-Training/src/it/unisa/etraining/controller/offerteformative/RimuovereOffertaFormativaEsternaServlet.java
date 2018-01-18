package it.unisa.etraining.controller.offerteformative;

import it.unisa.etraining.model.facade.OfferteFormativeFacade;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet <code>RimuovereOffertaFormativaEsternaServlet</code> serve per rimuovere
 * un'offerta formativa di tirocinio esterno.
 */
@WebServlet("/RimuovereOffertaFormativaEsternaServlet")
public class RimuovereOffertaFormativaEsternaServlet extends HttpServlet {
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
    int id = Integer.parseInt(request.getParameter("id"));
    OfferteFormativeFacade offerteFormativeFacade = new OfferteFormativeFacade();
    boolean flag = offerteFormativeFacade.rimuovereOffertaFormativaEsterna(id);
    if (!flag) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/visualizzareOffertaFormativa.jsp?errore=Database");
      dispatcher.forward(request, response);
    } else {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/visualizzareOffertaFormativa.jsp?errore=null");
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
