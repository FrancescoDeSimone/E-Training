package it.unisa.etraining.controller.offerteformative;

import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.facade.OfferteFormativeFacade;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet <code>ModificareOffertaFormativaEsternaServlet</code> serve per modificare
 * un'offerta formativa di tirocinio esterno.
 * 
 * @author Celentano Antonino
 */
@WebServlet("/ModificareOffertaFormativaEsternaServlet")
public class ModificareOffertaFormativaEsternaServlet extends HttpServlet {
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
    String tema = request.getParameter("tema");
    String dataInizio = request.getParameter("dataInizio");
    String dataFine = request.getParameter("dataFine");
    String tutorAccademico = request.getParameter("tutorAccademico");
    String tutorAziendale = request.getParameter("tutorAziendale");
    String fac = request.getParameter("facilitazioni");
    OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
    offerta.setAzienda(null);
    String[] facilitazioni = fac.split(";");
    offerta.setFacilitazioni(facilitazioni);
    String[] dataInizioSplit = dataInizio.split("-");
    offerta.setInizioTirocinio(new GregorianCalendar(
        Integer.parseInt(dataInizioSplit[0]), 
        Integer.parseInt(dataInizioSplit[1]), 
        Integer.parseInt(dataInizioSplit[2])));
    String[] dataFineSplit = dataFine.split("-"); 
    offerta.setFineTirocinio(new GregorianCalendar(
        Integer.parseInt(dataFineSplit[0]), 
        Integer.parseInt(dataFineSplit[1]), 
        Integer.parseInt(dataFineSplit[2])));
    offerta.setId(id);
    offerta.setStatus("inAttesa");
    offerta.setTema(tema);
    offerta.setTutorAziendale(null);
    offerta.setTutorDidattico(null);
    offerta.setValidita(false);
    OfferteFormativeFacade offerteFormativeFacade = new OfferteFormativeFacade();
    boolean flag = offerteFormativeFacade.modificareOffertaFormativaEsterna(offerta);
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
