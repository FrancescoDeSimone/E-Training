package it.unisa.etraining.controller.registrotirocinio;

import it.unisa.etraining.model.bean.AttivitaRegistro;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.bean.Utente;
import it.unisa.etraining.model.facade.RegistroTirocinioFacade;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet ConvalidaAttivitaRegistroServlet permette di convalidare un'
 * attività di un registro di un tirocinante.
 * @author Basso Emanuele
 */
@WebServlet("/ConvalidaAttivitaRegistroServlet")
public class ConvalidaAttivitaRegistroServlet extends HttpServlet {
  
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
    
    String idOfferta = request.getParameter("id");
    String scelta = request.getParameter("scelta");
    String email = request.getParameter("email");
    String motivazione = request.getParameter("motivazione");
    String attivitaSvolta = request.getParameter("attivitaSvolta");
    
    if (idOfferta == null || scelta == null || email == null || attivitaSvolta == null
        || idOfferta.isEmpty() || email.isEmpty() || scelta.isEmpty() || attivitaSvolta.isEmpty()) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/convalidaRegistriTutor.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }
    
    Tirocinante tirocinante = new Tirocinante();
    tirocinante.setEmail(email);
    OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
    offerta.setId(Integer.parseInt(idOfferta));
    Tirocinio tirocinio = new Tirocinio();
    tirocinio.setTirocinante(tirocinante);
    tirocinio.setOfferta(offerta);
    
    AttivitaRegistro attivitaRegistro = new AttivitaRegistro();
    attivitaRegistro.setTirocinio(tirocinio);
    attivitaRegistro.setAttivitaSvolta(attivitaSvolta);
    
    if (scelta.equals("convalida")) {
      if (utente instanceof TutorDidattico) {
        attivitaRegistro.setConvalida(AttivitaRegistro.CONVALIDATA);
      } else if (utente instanceof TutorAziendale) {
        attivitaRegistro.setConvalida(AttivitaRegistro.CONVALIDATA_TUTOR_AZIENDALE);
      }
    } else if (scelta.equals("nonConvalida")) {
      attivitaRegistro.setConvalida(AttivitaRegistro.NON_CONVALIDATA);
      
      if (motivazione == null || !motivazione.matches(regexMotivazione)) {
        RequestDispatcher dispatcher = request
            .getRequestDispatcher("/convalidaRegistriTutor.jsp?errore=1");
        dispatcher.forward(request, response);
        return;
      } else {
        attivitaRegistro.setMotivazioneRifiuto(motivazione);
      }
    }
    
    try {
      RegistroTirocinioFacade registroTirocinioFacade = new RegistroTirocinioFacade();
      
      registroTirocinioFacade.convalidaAttivita(attivitaRegistro);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel modificare l'attività.");
      dispatcher.forward(request, response);
      return;
    }
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/VisualizzaRegistriTutorServlet");
    dispatcher.forward(request, response);
  }
  
  private static final long serialVersionUID = 1L;
  private static final String regexMotivazione = "^(?=\\S)(?=[a-zA-Z0-9òàèù' ])"
      + "([a-zA-Z0-9òàèù' ]{0,499})$";
}
