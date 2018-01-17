package it.unisa.etraining.controller.profilo;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.TitolareAzienda;
import it.unisa.etraining.model.facade.ProfiloFacade;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet <code>ConvenzionareAziendaServlet</code> gestisce le convenzioni delle aziende.
 *
 */
@WebServlet("/ConvenzionareAziendaServlet")
public class ConvenzionareAziendaServlet extends HttpServlet {

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
    String nome = request.getParameter("nomeAz");
    String partitaIva = request.getParameter("IVA");
    String sede = request.getParameter("sede");
    String citta = request.getParameter("citta");
    String nomeTitolare = request.getParameter("nome");
    String cognomeTitolare = request.getParameter("cognome");
    String codiceFiscale = request.getParameter("CF");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    if (nome == null || partitaIva == null || sede == null || citta == null || nomeTitolare == null
        || cognomeTitolare == null || codiceFiscale == null || email == null || password == null
        || !email.matches(regexEmail) || !password.matches(regexPassword)) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/aggiungereAziendaConvenzionata.jsp?errore=Credenziali%20errate!");
      dispatcher.forward(request, response);
    }
    Azienda azienda = new Azienda();
    azienda.setNome(nome);
    azienda.setPartitaIva(partitaIva);
    azienda.setSede(sede);
    azienda.setCitta(citta);
    TitolareAzienda titolareAzienda = new TitolareAzienda();
    titolareAzienda.setNome(nomeTitolare);
    titolareAzienda.setCognome(cognomeTitolare);
    titolareAzienda.setCodiceFiscale(null);
    titolareAzienda.setAzienda(null);
    azienda.setTitolareAzienda(titolareAzienda);
    azienda.setEmail(email);
    azienda.setPassword(password);
    azienda.setTutorAziendali(null);
    ProfiloFacade profiloFacade = new ProfiloFacade();
    boolean flag = profiloFacade.aggiungiAzienda(azienda);
    if (!flag) {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/aggiungereAziendaConvenzionata.jsp?errore=Database!");
      dispatcher.forward(request, response);
    } else {
      RequestDispatcher dispatcher = getServletContext()
          .getRequestDispatcher("/aggiungereAziendaConvenzionata.jsp?errore=null!");
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
