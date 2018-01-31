package it.unisa.etraining.controller.tirocinio;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.facade.TirocinioFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet RicercaOffertaFormativaAziendaServlet permette di ricercare un'
 * offerta formativa di una determinata azienda.
 *
 * @author Antonino Celentano
 */
@WebServlet("/RicercaOffertaFormativaAziendaServlet")
public class RicercaOffertaFormativaAziendaServlet extends HttpServlet {

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
    
    String tema = request.getParameter("tema");
    String aziendaEmail = request.getParameter("azienda");
    
    if (tema == null || tema.isEmpty() || aziendaEmail == null || aziendaEmail.isEmpty()) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/effettuaRichiestaTirocinio.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }

    Azienda azienda = new Azienda();
    azienda.setEmail(aziendaEmail);
    
    OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
    try {
      TirocinioFacade tirocinioFacade = new TirocinioFacade();
    
      offerta = tirocinioFacade.mostraOffertaFormativaAzienda(tema,azienda);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?Errore nel connettersi al database.");
      dispatcher.forward(request, response);
    }
    
    GregorianCalendar g = offerta.getInizioTirocinio();
    String inizio = (g.get(GregorianCalendar.DAY_OF_MONTH) + "/" 
        + (g.get(GregorianCalendar.MONTH) + 1) + "/"
        + g.get(GregorianCalendar.YEAR));
    
    g = offerta.getFineTirocinio();
    String fine = (g.get(GregorianCalendar.DAY_OF_MONTH) + "/" 
        + (g.get(GregorianCalendar.MONTH) + 1) + "/"
        + g.get(GregorianCalendar.YEAR));
    
    JsonObjectBuilder builder = Json.createObjectBuilder();
    
    builder.add("tema","" + offerta.getTema())
        .add("inizio",inizio)
        .add("fine",fine)
        .add("tutorD",offerta.getTutorDidattico().getCognome() + " "
           + offerta.getTutorDidattico().getNome())
        .add("tutorA",offerta.getTutorAziendale().getCognome() + " "
           + offerta.getTutorAziendale().getNome());

    for (int i = 0; i < offerta.getFacilitazioni().length; i++) {
      builder.add("facilitazione" + i, offerta.getFacilitazioni()[i]);
    }
    
    JsonObject jo = builder.build();

    response.setContentType("application/json");
    PrintWriter out = response.getWriter();
    out.print(jo);
    out.flush();
  }
  
  private static final long serialVersionUID = 1L;
}
