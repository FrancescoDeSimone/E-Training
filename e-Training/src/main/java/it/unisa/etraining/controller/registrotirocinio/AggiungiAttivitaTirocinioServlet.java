package it.unisa.etraining.controller.registrotirocinio;

import it.unisa.etraining.model.bean.AttivitaRegistro;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.Tirocinio;
import it.unisa.etraining.model.facade.RegistroTirocinioFacade;
import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * La servlet AggiungiAttivitaTirocinioServlet permette di aggiungere una
 * nuova attività a un registro di un tirocinante.
 * @author Basso Emanuele
 */
@WebServlet("/AggiungiAttivitaTirocinioServlet")
public class AggiungiAttivitaTirocinioServlet extends HttpServlet {

  /**
   * Metodo doPost.
   * 
   * @param request la richiesta che il client ha mandato alla servlet
   * @param response la risposta che manda la servlet al client
   * 
   * @throws ServletException nel caso non può essere gestita la richiesta
   * @throws IOException nel caso sorge un errore di IO
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");

    HttpSession sessione = request.getSession(false);
    
    if (sessione == null) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/login.jsp");
      dispatcher.forward(request, response);
      return;
    }
    
    String attivita = request.getParameter("attivita");
    String dataInizio = request.getParameter("dataInizio");
    String dataFine = request.getParameter("dataFine");
    String ore = request.getParameter("ore");
    String idOfferta = request.getParameter("idOfferta");
    
    if (attivita == null || dataInizio == null || dataFine == null 
        || ore == null || !attivita.matches(regexAttivita) || !dataInizio.matches(regexData) 
        || !dataFine.matches(regexData) || !ore.matches(regexOre)) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/compilaRegistroTirocinante.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }
    
    String[] dataInizioSplit = dataInizio.split("-");
    String[] dataFineSplit = dataFine.split("-"); 

    GregorianCalendar dataInizioGc = new GregorianCalendar(
        Integer.parseInt(dataInizioSplit[0]), 
        Integer.parseInt(dataInizioSplit[1]), 
        Integer.parseInt(dataInizioSplit[2]));
    
    GregorianCalendar dataFineGc = new GregorianCalendar(
        Integer.parseInt(dataFineSplit[0]), 
        Integer.parseInt(dataFineSplit[1]), 
        Integer.parseInt(dataFineSplit[2]));
    
    if (dataFineGc.before(dataInizioGc)) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/compilaRegistroTirocinante.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }
    
    OffertaFormativaTirocinioEsterno offerta = new OffertaFormativaTirocinioEsterno();
    offerta.setId(Integer.parseInt(idOfferta));
    
    Tirocinante tirocinante = (Tirocinante) sessione.getAttribute("utente");
    
    Tirocinio tirocinio = new Tirocinio();
    tirocinio.setTirocinante(tirocinante);
    tirocinio.setOfferta(offerta);
    
    AttivitaRegistro attivitaRegistro = new AttivitaRegistro();
    attivitaRegistro.setAttivitaSvolta(attivita);
    attivitaRegistro.setInizio(dataInizioGc);
    attivitaRegistro.setFine(dataFineGc);
    attivitaRegistro.setOreSvolte(Integer.parseInt(ore));
    attivitaRegistro.setConvalida(AttivitaRegistro.IN_CONVALIDA);
    attivitaRegistro.setTirocinio(tirocinio);    
    
    try {
      RegistroTirocinioFacade registroTirocinioFacade = new RegistroTirocinioFacade();
      
      registroTirocinioFacade.aggiungiAttivita(attivitaRegistro);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel salvare l'attività.");
      dispatcher.forward(request, response);
      return;
    }
    
    request.setAttribute("aggiunta","1");
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/VisualizzaRegistriTirocinanteServlet");
    dispatcher.forward(request, response);
  }
  
  private static final long serialVersionUID = 1L;
  private static final String regexAttivita = "^(?=\\S)(?=[a-zA-Z0-9òàèù' ])([a-zA-Z0-9òàèù' ]"
      + "{0,49})$";
  private static final String regexData = "^(?:\\d{4})-(?:(?:(09-|04-|06-|11-)"
      + "(?:01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|"
      + "23|24|25|26|27|28|29|30))|(?:(?:01-|03-|05-|07-|08-|10-|12-)(?:01|02|"
      + "03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|"
      + "26|27|28|29|30|31))|(?:02-(?:01|02|03|04|05|06|07|08|09|10|11|12|13|"
      + "14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29)))$";
  private static final String regexOre = "^\\d{1,2}$";
}
