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
 * La servlet <code>RimuovereTirocinanteServlet</code> gestisce le rimozioni dei tirocinanti.
 *
 */
@WebServlet("/RimuovereTirocinanteServlet")
public class RimuovereTirocinanteServlet extends HttpServlet {

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
    String email = request.getParameter("email");
    Tirocinante tirocinante = new Tirocinante();
    tirocinante.setEmail(email);
    ProfiloFacade profiloFacade = new ProfiloFacade();
    boolean flag = profiloFacade.rimuoviTirocinante(tirocinante);
    if (!flag) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/rimuovereTirocinante.jsp?errore=Database!");
      dispatcher.forward(request, response);
    } else {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/rimuovereTirocinante.jsp?errore=null!");
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

}
