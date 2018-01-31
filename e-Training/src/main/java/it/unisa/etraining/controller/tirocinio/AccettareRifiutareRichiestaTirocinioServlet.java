package it.unisa.etraining.controller.tirocinio;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.bean.Utente;
import it.unisa.etraining.model.facade.TirocinioFacade;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet AccettareRifiutareRichiestaTirocinioServlet permette di far accettare a
 * un'azienda o a un tutor didattico una richiesta di tirocinio.
 *
 * @author Antonino Celentano
 */
@WebServlet("/AccettareRifiutareRichiestaTirocinioServlet")
public class AccettareRifiutareRichiestaTirocinioServlet extends HttpServlet {

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
    
    Utente utente = (Utente) sessione.getAttribute("utente");
    
    String scelta = request.getParameter("scelta");
    String tirocinanteEmail = request.getParameter("tirocinante");
    String idOffertaFormativa = request.getParameter("id");
    
    if (scelta == null || tirocinanteEmail == null || idOffertaFormativa == null
        || !scelta.matches(regexScelta) || !tirocinanteEmail.matches(regexEmailIstituzionale) 
        || idOffertaFormativa.equals("")) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/accettareRifiutareRichiestaTirocinio.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }
    
    Tirocinio tirocinio = new Tirocinio();
    
    Tirocinante tirocinante = new Tirocinante();
    tirocinante.setEmail(tirocinanteEmail);
    
    OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
    offerta.setId(Integer.parseInt(idOffertaFormativa));
    
    tirocinio.setTirocinante(tirocinante);
    tirocinio.setOfferta(offerta);
    
    if (scelta.equals("accetta")) {
      if (utente instanceof TutorDidattico) {
        tirocinio.setStatus(Tirocinio.VALUTAZIONE_AZIENDA);
      } else if (utente instanceof Azienda) {
        tirocinio.setStatus(Tirocinio.IN_CORSO);
      }
    } else if (scelta.equals("rifiuta")) {
      tirocinio.setStatus(Tirocinio.RIFIUTATO);
    }
    
    try {
      TirocinioFacade tirocinioFacade = new TirocinioFacade();
      
      tirocinioFacade.accettaRifiutaRichiestaTirocinio(tirocinio);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?errore="
              + "Errore nel salvare la scelta sul database.");
      dispatcher.forward(request, response);
      return;
    }
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/ValutareRichiestaTirocinioServlet");
    dispatcher.forward(request, response);
  }
  
  private static final long serialVersionUID = 1L;
  private static final String regexScelta = "^(accetta)|(rifiuta)$";
  private static final String regexEmailIstituzionale = "^[a-z].[a-z]+[0-9]*@studenti.unisa.it$";
}
