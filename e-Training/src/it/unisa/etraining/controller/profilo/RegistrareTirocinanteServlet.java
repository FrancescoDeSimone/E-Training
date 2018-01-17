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
 * La servlet <code>RegistrareTirocinanteServlet</code> gestisce le registrazioni dei tirocinanti.
 *
 */
@WebServlet("/RegistrareTirocinanteServlet")
public class RegistrareTirocinanteServlet extends HttpServlet {
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
    String email = request.getParameter("email");
    String matricola = request.getParameter("matricola");
    String anno = request.getParameter("anno");
    String password = request.getParameter("password");
    if (nome == null || cognome == null || email == null || matricola == null || anno == null
        || password == null || !email.matches(regexEmail) || !password.matches(regexPassword)) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/registrareTirocinante.jsp?errore=Credenziali%20errate!");
      dispatcher.forward(request, response);
    }
    Tirocinante tirocinante = new Tirocinante();
    tirocinante.setNome(nome);
    tirocinante.setCognome(cognome);
    tirocinante.setEmail(email);
    tirocinante.setMatricola(matricola);
    tirocinante.setAnnoIscrizione(anno);
    tirocinante.setPassword(password);
    ProfiloFacade profiloFacade = new ProfiloFacade();
    boolean flag = profiloFacade.aggiungiTirocinante(tirocinante);
    if (!flag) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/registrareTirocinante.jsp?errore=Database!");
      dispatcher.forward(request, response);
    } else {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/registrareTirocinante.jsp?errore=null!");
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
