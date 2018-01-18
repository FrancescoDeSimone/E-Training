package it.unisa.etraining.controller.offerteformative;

import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.facade.OfferteFormativeFacade;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet <code>ValutareOffertaFormativaTirocinioEsternoServlet</code> gestisce la valutazione delle
 * offerte formative di tirocinio esterno.
 */
@WebServlet("/ValutareOffertaFormativaTirocinioEsternoServlet")
public class ValutareOffertaFormativaTirocinioEsternoServlet extends HttpServlet {
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
    String scelta = request.getParameter("scelta");
    int id = Integer.parseInt(request.getParameter("id"));
    OfferteFormativeFacade offerteFormativeFacade = new OfferteFormativeFacade();
    OffertaFormativaTirocinioEsterno offerta = offerteFormativeFacade
        .getOffertaFormativaTirocinioEsterno(id);
    boolean flag = false;
    if (scelta.equals("Accetta")) {
      flag = offerteFormativeFacade
          .accettareRifiutarePropostaOffertaFormativaEsterna(offerta, "Accettata");
    } else if (scelta.equals("Rifiuta")) {
      flag = offerteFormativeFacade
          .accettareRifiutarePropostaOffertaFormativaEsterna(offerta, "Rifiutata");
    }
    if (!flag) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/visualizzareOfferteFormative.jsp?errore=Database");
      dispatcher.forward(request, response);
    } else {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/visualizzareOfferteFormative.jsp?errore=null");
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
