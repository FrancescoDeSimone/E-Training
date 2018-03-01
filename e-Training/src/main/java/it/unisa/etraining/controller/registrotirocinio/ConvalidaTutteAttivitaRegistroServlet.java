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
 * La servlet ConvalidaTutteAttivitaRegistroServlet permette di convalidare tutte le
 * attività di un registro di un tirocinante.
 * @author Basso Emanuele
 */
@WebServlet("/ConvalidaTutteAttivitaRegistroServlet")
public class ConvalidaTutteAttivitaRegistroServlet extends HttpServlet {
  
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
    
    String idOfferta = request.getParameter("id");
    String email = request.getParameter("email");
    
    if (idOfferta == null || email == null
        || idOfferta.isEmpty() || email.isEmpty()) {
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
    
    Utente utente = (Utente) sessione.getAttribute("utente");

    AttivitaRegistro attivitaRegistro = new AttivitaRegistro();
    if (utente instanceof TutorDidattico) {
      attivitaRegistro.setConvalida(AttivitaRegistro.CONVALIDATA);
    } else if (utente instanceof TutorAziendale) {
      attivitaRegistro.setConvalida(AttivitaRegistro.CONVALIDATA_TUTOR_AZIENDALE);
    }
    AttivitaRegistro[] arrayAttivita = {attivitaRegistro};
    
    tirocinio.setAttivitaSvolte(arrayAttivita);
    
    try {
      RegistroTirocinioFacade registroTirocinioFacade = new RegistroTirocinioFacade();
      
      registroTirocinioFacade.convalidaTutteAttivita(tirocinio);
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
}
