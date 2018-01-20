package it.unisa.etraining.controller.tirocinio;

import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinante;
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
 * La servlet <code>RichiedereTirocinioServlet</code> gestisce le richieste di tirocinio dei
 * tirocinanti.
 */
@WebServlet("/RichiedereTirocinioServlet")
public class RichiedereTirocinioServlet extends HttpServlet {       
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
    int off = Integer.parseInt(request.getParameter("offerta"));
    String cfu = request.getParameter("CFU");
    String ob = request.getParameter("obiettivi");
    
    if (cfu == null || ob == null) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/inviaRichiesteDiTirocinio.jsp?errore=Campi%20nulli!");
      dispatcher.forward(request, response);
    }
    ProfiloFacade profiloFacade = new ProfiloFacade();
    Tirocinante tirocinante = profiloFacade.getTirocinante((String) request.getSession()
        .getAttribute("email"));
    TirocinioFacade tirocinioFacade = new TirocinioFacade();
    String[] obiettivi = ob.split(";");
    OfferteFormativeFacade offerteFormativeFacade = new OfferteFormativeFacade();
    OffertaFormativaTirocinioEsterno offerta = offerteFormativeFacade
        .getOffertaFormativaTirocinioEsterno(off);
    int cfuNum = 6;
    try {
      cfuNum = Integer.parseInt(cfu);
    } catch (NumberFormatException e) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/inviaRichiesteDiTirocinio.jsp?errore=Campi%20errati!");
      dispatcher.forward(request, response);
    }
    boolean flag = tirocinioFacade.richiediTirocinio(tirocinante, offerta, cfuNum, obiettivi);
    if (!flag) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/inviaRichiesteDiTirocinio.jsp?errore=Database");
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
