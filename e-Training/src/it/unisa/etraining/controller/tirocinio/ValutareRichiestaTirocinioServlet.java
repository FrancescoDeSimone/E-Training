package it.unisa.etraining.controller.tirocinio;

import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.facade.OfferteFormativeFacade;
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
 * La servlet <code>ValutareRichiestaTirocinioServlet</code> gestisce la valutazione delle
 * richieste di tirocinio.
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
    String scelta = request.getParameter("scelta");
    String email = request.getParameter("email");
    int off = Integer.parseInt(request.getParameter("id"));
    ProfiloFacade profiloFacade = new ProfiloFacade();
    Tirocinante tirocinante = profiloFacade.getTirocinante(email);
    OfferteFormativeFacade offerteFormativeFacade = new OfferteFormativeFacade();
    OffertaFormativaTirocinioEsterno offerta = offerteFormativeFacade
        .getOffertaFormativaTirocinioEsterno(off);
    TirocinioFacade tirocinioFacade = new TirocinioFacade();
    Tirocinio tirocinio = tirocinioFacade.getTirocinio(tirocinante, offerta);
    boolean flag = false;
    if (scelta.equals("Accetta")) {
      flag = tirocinioFacade.accettareRifiutareRichiestaTirocinio(tirocinio, true);
    } else if (scelta.equals("Rifiuta")) {
      flag = tirocinioFacade.accettareRifiutareRichiestaTirocinio(tirocinio, false);
    }
    if (!flag) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/visualizzareRichiesteTirocinio.jsp?errore=Database");
      dispatcher.forward(request, response);
    } else {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/visualizzareRichiesteTirocinio.jsp?errore=null");
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
