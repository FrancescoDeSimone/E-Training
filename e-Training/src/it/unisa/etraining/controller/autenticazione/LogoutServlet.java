package it.unisa.etraining.controller.autenticazione;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet <code>LogoutServlet</code> gestisce le richieste di logout degli
 * utenti.
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

  /**
   * Metodo doGet.
   * 
   * @param request
   *          la servlet request
   * @param response
   *          la servlet response
   * 
   * @throws ServletException
   *           nel caso l'eccezione venga sollevata dalla servlet
   * @throws IOException
   *           nel caso l'eccezione venga sollevata da un errore di IO
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getSession().removeAttribute("nome");
    request.getSession().removeAttribute("tipo");
    request.getSession().removeAttribute("email");
    request.getSession().invalidate();
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
    dispatcher.forward(request, response);
  }

  /**
   * Metodo doPost.
   * 
   * @param request
   *          la servlet request
   * @param response
   *          la servlet response
   * 
   * @throws ServletException
   *           nel caso l'eccezione venga sollevata dalla servlet
   * @throws IOException
   *           nel caso l'eccezione venga sollevata da un errore di IO
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

  private static final long serialVersionUID = 1L;

}
