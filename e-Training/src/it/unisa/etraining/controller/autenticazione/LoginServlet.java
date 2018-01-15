package it.unisa.etraining.controller.autenticazione;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.FunzionarioSegreteria;
import it.unisa.etraining.model.bean.Tirocinante;
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
 * La servlet <code>LoginServlet</code> gestisce le richieste di login degli
 * utenti.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

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

    String email = request.getParameter("inputEmail");
    String password = request.getParameter("inputPassword");
    if (email == null || password == null || !email.matches(regexEmail) 
        || !password.matches(regexPassword)) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/login.jsp?errore=Credenziali%20errate!");
      dispatcher.forward(request, response);
    }
    Utente utente = new Utente();
    utente.setEmail(email);
    utente.setPassword(password);
    AutenticazioneFacade facade = new AutenticazioneFacade();
    utente = facade.login(utente);
    if (utente == null) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/login.jsp?errore=Credenziali%20errate!");
      dispatcher.forward(request, response);
    } else {
      if (utente instanceof Tirocinante) {
        request.getSession().setAttribute("nome", ((Tirocinante) utente).getNome());
      } else if (utente instanceof TutorDidattico) {
        request.getSession().setAttribute("nome", ((TutorDidattico) utente).getNome());
      } else if (utente instanceof Azienda) {
        request.getSession().setAttribute("nome", ((Azienda) utente).getNome());
      } else if (utente instanceof FunzionarioSegreteria) {
        request.getSession().setAttribute("nome", ((FunzionarioSegreteria) utente).getNome());
      }
      String tipo = request.getParameter("inputTipo");
      request.getSession().setAttribute("tipo", tipo);
      request.getSession().setAttribute("email", email);
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/tirociniAttiviStorico.jsp");
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
  private static final String regexEmail = 
      "/^\\w+([\\.-]?\\3w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$/";
  private static final String regexPassword = "/^\\S*.{6}$/";
}
