package it.unisa.etraining.controller.tirocinio;

import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.facade.TirocinioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet RicercaOfferteFormativeAziendaServlet permette di ricercare tutte le 
 * offerte formative di una determinata azienda.
 *
 * @author Antonino Celentano
 */
@WebServlet("/RicercaOfferteFormativeAziendaServlet")
public class RicercaOfferteFormativeAziendaServlet extends HttpServlet {

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
    request.setCharacterEncoding("UTF-8");

    HttpSession sessione = request.getSession(false);
    
    if (sessione == null) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
      return;
    }
    
    String azienda = request.getParameter("azienda");
    
    if (azienda == null || azienda.isEmpty()) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/effettuaRichiestaTirocinio.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }
    
    ArrayList<OffertaFormativaTirocinioEsterno> listaOfferte = new ArrayList<>();
    try {
      TirocinioFacade tirocinioFacade = new TirocinioFacade();
    
      listaOfferte = tirocinioFacade.mostraTutteOfferteFormativeAzienda(azienda);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?Errore nel connettersi al database.");
      dispatcher.forward(request, response);
    }
    
    JsonArrayBuilder builder = Json.createArrayBuilder();

    for (OffertaFormativaTirocinioEsterno o : listaOfferte) {
      builder.add(Json.createObjectBuilder().add("id","" + o.getId()).add("tema",o.getTema()));
    }
    
    JsonArray jarr = builder.build();

    response.setContentType("application/json");
    PrintWriter out = response.getWriter();
    out.print(jarr);
    out.flush();
  }
  
  private static final long serialVersionUID = 1L;
}
