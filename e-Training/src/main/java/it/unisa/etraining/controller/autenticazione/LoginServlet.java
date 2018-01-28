package it.unisa.etraining.controller.autenticazione;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.FunzionarioSegreteria;
import it.unisa.etraining.model.bean.Tirocinante;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.bean.Utente;
import it.unisa.etraining.model.facade.AutenticazioneFacade;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet LoginServlet permette di gestire le richieste di login degli
 * utenti.
 *
 * @author Antonino Celentano
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

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

    String email = request.getParameter("inputEmail");
    String password = request.getParameter("inputPassword");
    
    if (email == null || password == null || !email.matches(regexEmail) 
        || !password.matches(regexPassword)) {
      RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp?errore=1");
      dispatcher.forward(request, response);
      return;
    }
    
    String tipo = request.getParameter("inputTipo");
    
    Utente utente = null;
    
    try {
      if (tipo.equals("tutorDidattico")) {
        TutorDidattico tutorDidattico = new TutorDidattico();
        tutorDidattico.setEmail(email);
        tutorDidattico.setPassword(password);
        
        AutenticazioneFacade facade = new AutenticazioneFacade();
        utente = facade.login(tutorDidattico);
      } else if (tipo.equals("tutorAziendale")) {
        TutorAziendale tutorAziendale = new TutorAziendale();
        tutorAziendale.setEmail(email);
        tutorAziendale.setPassword(password);
        
        AutenticazioneFacade facade = new AutenticazioneFacade();
        utente = facade.login(tutorAziendale);
      } else if (tipo.equals("funzionario")) {
        FunzionarioSegreteria funzionarioSegreteria = new FunzionarioSegreteria();
        funzionarioSegreteria.setEmail(email);
        funzionarioSegreteria.setPassword(password);
        
        AutenticazioneFacade facade = new AutenticazioneFacade();
        utente = facade.login(funzionarioSegreteria);
      } else if (tipo.equals("azienda")) {
        Azienda azienda = new Azienda();
        azienda.setEmail(email);
        azienda.setPassword(password);
        
        AutenticazioneFacade facade = new AutenticazioneFacade();
        utente = facade.login(azienda);
      } else if (tipo.equals("tirocinante")) {
        Tirocinante tirocinante = new Tirocinante();
        tirocinante.setEmail(email);
        tirocinante.setPassword(password);
        
        AutenticazioneFacade facade = new AutenticazioneFacade();
        utente = facade.login(tirocinante);
      }
    } catch (Exception e) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/paginaErrore.jsp?errore=Errore nel connettersi al database.");
      dispatcher.forward(request, response);
      return;
    }
    
    if (utente == null) {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/login.jsp?errore=Credenziali errate!");
      dispatcher.forward(request, response);
      return;
    } else {
      if (tipo.equals("tutorDidattico")) {
        request.getSession().setAttribute("nome",((TutorDidattico) utente).getNome());
      } else if (tipo.equals("tutorAziendale")) {
        request.getSession().setAttribute("nome",((TutorAziendale) utente).getNome());
      } else if (tipo.equals("funzionario")) {
        request.getSession().setAttribute("nome",((FunzionarioSegreteria) utente).getNome());
      } else if (tipo.equals("azienda")) {
        request.getSession().setAttribute("nome",((Azienda) utente).getNome());
      } else if (tipo.equals("tirocinante")) {
        request.getSession().setAttribute("nome",((Tirocinante) utente).getNome());
      }      
      
      request.getSession().setMaxInactiveInterval(60 * 30);
      
      request.getSession().setAttribute("utente",utente);
      request.getSession().setAttribute("tipo",tipo);
      request.getSession().setAttribute("email",email);
      
      if (tipo.equals("funzionario")) {
        RequestDispatcher dispatcher = request
            .getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
        return;
      } else {
        RequestDispatcher dispatcher = request
            .getRequestDispatcher("/TirociniAttiviStoricoServlet");
        dispatcher.forward(request, response);
      }
    }
  }

  private static final long serialVersionUID = 1L;
  private static final String regexEmail = 
      "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))"
      + "@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)"
      + "+[a-zA-Z]{2,}))$";
  private static final String regexPassword = "^\\S{6,20}$";
}
