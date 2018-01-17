package it.unisa.etraining.controller.profilo;

import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.facade.ProfiloFacade;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet <code>AggiungereTutorAziendaleServlet</code> gestisce le registrazioni dei
 * tutor aziendali.
 *
 */
@WebServlet("/AggiungereTutorAziendaleServlet")
public class AggiungereTutorAziendaleServlet extends HttpServlet {
  
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
    String nome = request.getParameter("nome");
    String cognome = request.getParameter("cognome");
    String codiceFiscale = request.getParameter("CF");
    String settoreLavoro = request.getParameter("lavoro");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    if (nome == null || cognome == null || codiceFiscale == null || settoreLavoro == null
        || email == null | password == null || !email.matches(regexEmail)
        || !password.matches(regexPassword)) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/aggiungiTutorAziendale.jsp?errore=Credenziali%20errate!");
      dispatcher.forward(request, response);
    }
    TutorAziendale tutorAziendale = new TutorAziendale();
    tutorAziendale.setNome(nome);
    tutorAziendale.setCognome(cognome);
    tutorAziendale.setCodiceFiscale(codiceFiscale);
    tutorAziendale.setSettoreLavoro(settoreLavoro);
    tutorAziendale.setEmail(email);
    tutorAziendale.setPassword(password);
    tutorAziendale.setAzienda(null);
    ProfiloFacade profiloFacade = new ProfiloFacade();
    boolean flag = profiloFacade.aggiungiTutorAziendale(tutorAziendale);
    if (!flag) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/aggiungiTutorAziendale.jsp?errore=Database!");
      dispatcher.forward(request, response);
    } else {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/aggiungiTutorAziendale.jsp?errore=null!");
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
