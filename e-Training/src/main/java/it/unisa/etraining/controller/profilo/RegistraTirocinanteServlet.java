package it.unisa.etraining.controller.profilo;

import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.facade.ProfiloFacade;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet RegistraTirocinanteServlet permette di registrare un 
 * nuovo tirocinante nel sistema.
 * @author Basso Emanuele
 */
@WebServlet("/RegistraTirocinanteServlet")
public class RegistraTirocinanteServlet extends HttpServlet {

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

    String nome = request.getParameter("nome");
    String cognome = request.getParameter("cognome");
    String email = request.getParameter("email");
    String matricola = request.getParameter("matricola");
    String annoIscrizione = request.getParameter("anno");
    String password = request.getParameter("password");
    String password2 = request.getParameter("password2");
    
    if (nome == null || cognome == null || email == null || matricola == null
        || annoIscrizione == null || password == null || password2 == null 
        || !nome.matches(regexNome) || !cognome.matches(regexCognome)
        || !email.matches(regexEmailIstituzionale) || !matricola.matches(regexMatricola)
        || !annoIscrizione.matches(regexAnno) || !password.matches(regexPassword) 
        || !password2.matches(regexPassword) || !password.equals(password2)) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/registraTirocinante.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }
    
    Tirocinante tirocinante = new Tirocinante();
    tirocinante.setEmail(email);
    tirocinante.setPassword(password);
    tirocinante.setNome(nome);
    tirocinante.setCognome(cognome);
    tirocinante.setAnnoIscrizione(annoIscrizione);
    tirocinante.setMatricola(matricola);
    
    try {
      ProfiloFacade profiloFacade = new ProfiloFacade();
      
      if (profiloFacade.contrallaRichiestaTirocinioGiaInviata(email)) {
        RequestDispatcher dispatcher = request
            .getRequestDispatcher("/registraTirocinante.jsp?errore=2");
        dispatcher.forward(request, response);
        return;
      }
      
      profiloFacade.aggiungiTirocinante(tirocinante);
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?Errore nel salvare l'account.");
      dispatcher.forward(request, response);
      return;
    }
    
    request.setAttribute("registrazione","1");
    
    request.getSession().setAttribute("nome",tirocinante.getNome());
    request.getSession().setAttribute("utente",tirocinante);
    request.getSession().setAttribute("tipo",tirocinante);
    request.getSession().setAttribute("email",tirocinante.getEmail());
    
    RequestDispatcher dispatcher = request
        .getRequestDispatcher("/TirociniAttiviStoricoServlet");
    dispatcher.forward(request, response);
  }
  
  private static final long serialVersionUID = 1L;
  private static final String regexNome = "^[a-zA-Zòàèù ]{2,50}$";
  private static final String regexCognome = "^[a-zA-Zòàèù' ]{2,50}$";
  private static final String regexEmailIstituzionale = "^[a-z].[a-z]+[0-9]*@studenti.unisa.it$";
  private static final String regexMatricola = "^(05121){1}[0-9]{5}$";
  private static final String regexAnno = "^[0-9]{4}\\/[0-9]{4}$";
  private static final String regexPassword = "^\\S{6,20}$";
}
