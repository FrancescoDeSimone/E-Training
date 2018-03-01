package it.unisa.etraining.model.dao;

import it.unisa.etraining.model.bean.Azienda;
import it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno;
import it.unisa.etraining.model.bean.TutorAziendale;
import it.unisa.etraining.model.bean.TutorDidattico;
import it.unisa.etraining.model.connection.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Questa classe fornisce i metodi per gestire l'entità OffertaFormativaTirocinioEsterno in maniera
 * persistente.
 * 
 * @author Flavio Esposito
 */
public class OffertaFormativaTirocinioEsternoDao {

  /**
   * Questo metodo permette di salvare un oggetto OffertaFormativaTirocinioEsterno sul database.
   * 
   * @param unOfferta
   *          l'oggetto da salvare sul database.
   * @return true se l'offerta formativa viene correttamente salvata, false altrimenti.
   */
  public static synchronized boolean salvaOffertaFormativaTirocinioEsterno(
      OffertaFormativaTirocinioEsterno unOfferta) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(SALVA_OFFERTA_FORMATIVA);
      ps.setString(1, unOfferta.getTutorDidattico().getEmail());
      ps.setString(2, unOfferta.getTutorAziendale().getEmail());
      ps.setString(3, unOfferta.getTema());
      ps.setDate(4, new Date(unOfferta.getInizioTirocinio().getTimeInMillis()));
      ps.setDate(5, new Date(unOfferta.getFineTirocinio().getTimeInMillis()));
      ps.setBoolean(6, unOfferta.isValidita());
      ps.setString(7, unOfferta.getStatus());
      ps.setString(8, unOfferta.getAzienda().getEmail());

      ps.executeUpdate();

      ricercaIdOffertaFormativa(unOfferta);
      
      if (unOfferta.getFacilitazioni() != null) {
        notError = inserisciFacilitazioni(unOfferta);
      }
        
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }

    return notError;
  }


  /**
   * Questo metodo permette di ricercare un oggetto di tipo OffertaFormativaTirocinioEsterno 
   * inserito nel database attraverso il suo indentificativo.
   * 
   * @param unOfferta
   *          l'oggetto che contiene l'id e che deve essere riempito.
   * @return true se la ricerca avviene con successo.
   */
  public static synchronized boolean ricercaOffertaFormativaById(
      OffertaFormativaTirocinioEsterno unOfferta) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(RICERCA_OFFERTA_FORMATIVA_BY_ID);
      ps.setInt(1, unOfferta.getId());
      ResultSet rs = ps.executeQuery();
      
      rs.next();

      unOfferta.setTema(rs.getString("Tema"));
      unOfferta.setValidita(rs.getBoolean("Validita"));
      unOfferta.setStatus(rs.getString("Status"));
      
      Date dataLetta = rs.getDate("InizioTirocinio");
      GregorianCalendar dataInizio = new GregorianCalendar();
      dataInizio.setTime(dataLetta);
      unOfferta.setInizioTirocinio(dataInizio);
      
      dataLetta = rs.getDate("FineTirocinio");
      GregorianCalendar dataFine = new GregorianCalendar();
      dataFine.setTime(dataLetta);
      unOfferta.setFineTirocinio(dataFine);
      
      Azienda azienda = new Azienda();
      azienda.setEmail(rs.getString("Azienda"));
      unOfferta.setAzienda(azienda);
      
      TutorDidattico tutorDidattico = new TutorDidattico();
      tutorDidattico.setEmail(rs.getString("TutorDidattico"));
      unOfferta.setTutorDidattico(tutorDidattico);
      
      TutorAziendale tutorAziendale = new TutorAziendale();
      tutorAziendale.setEmail(rs.getString("TutorAziendale"));
      unOfferta.setTutorAziendale(tutorAziendale);
      
      rs.close();
      ps.close();
      
      notError = ricercaFacilitazioni(unOfferta);

    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }
    return notError;
  }

  
  /**
   * Questo metodo permette di ricercare l'ultimo id dell'OffertaFormativaTirocinioEsterno inserito
   * nel database.
   * 
   * @param unOfferta
   *          l'oggetto a cui assegnare l'id.
   * @return true se l'id viene correttamente trovato, false altrimenti.
   */
  private static synchronized boolean ricercaIdOffertaFormativa(
      OffertaFormativaTirocinioEsterno unOfferta) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(RICERCA_ID_OFFERTA_FORMATIVA);

      ResultSet rs = ps.executeQuery();

      rs.next();

      unOfferta.setId(rs.getInt("max(Id)"));

      rs.close();
      ps.close();

    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }
    return notError;
  }

  /**
   * Questo metodo permette di ricercare un oggetto OffertaFormativaTirocinioEsterno sul database.
   * 
   * @param unOfferta
   *          l'oggetto da cercare sul database.
   * @return true se il l'offerta formativa viene correttamente ricercata, false altrimenti.
   */
  public static synchronized boolean ricercaOffertaFormativaTirocinioEsterno(
      OffertaFormativaTirocinioEsterno unOfferta) {
    Connection con = null;
    boolean notError = false;
    
    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(RICERCA_OFFERTA_FORMATIVA);
      ps.setString(1, unOfferta.getTema());
      ps.setString(2, unOfferta.getAzienda().getEmail());
      ps.setString(3, OffertaFormativaTirocinioEsterno.ATTIVA);

      ResultSet rs = ps.executeQuery();

      rs.next();
      notError = true;
      TutorDidattico tutorDidattico = new TutorDidattico();
      tutorDidattico.setEmail(rs.getString("TutorDidattico"));
      unOfferta.setTutorDidattico(tutorDidattico);

      TutorAziendale tutorAziendale = new TutorAziendale();
      tutorAziendale.setEmail(rs.getString("TutorAziendale"));
      unOfferta.setTutorAziendale(tutorAziendale);

      Azienda azienda = new Azienda();
      azienda.setEmail(rs.getString("Azienda"));

      unOfferta.setId(rs.getInt("Id"));
      unOfferta.setTema(rs.getString("tema"));

      Date dataLetta = rs.getDate("InizioTirocinio");
      GregorianCalendar dataInizio = new GregorianCalendar();
      dataInizio.setTime(dataLetta);
      unOfferta.setInizioTirocinio(dataInizio);

      dataLetta = rs.getDate("FineTirocinio");
      GregorianCalendar dataFine = new GregorianCalendar();
      dataFine.setTime(dataLetta);
      unOfferta.setFineTirocinio(dataFine);

      unOfferta.setValidita(rs.getBoolean("Validita"));
      unOfferta.setStatus(rs.getString("Status"));

      unOfferta.setAzienda(azienda);
      
      notError = ricercaFacilitazioni(unOfferta);

      rs.close();
      ps.close();

      if (notError) {
        notError = ricercaFacilitazioni(unOfferta);
      }
    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }
    return notError;
  }

  /**
   * Questo metodo permette di ricercare tutti gli oggetti OffertaFormativaTirocinioEsterno sul
   * database relative a una determinata azienda.
   * 
   * @param offerte
   *          l'oggetto da riempire con le offerte trovate sul database.
   * @param azienda
   *          l'oggetto azienda da cui si prende l'email per effettuare la ricerca.
   * @return true se le offerte formative vengono correttamente ricercate, false altrimenti.
   */
  public static synchronized boolean ricercaTutteOfferteFormativeTirocinioEsterno(
      ArrayList<OffertaFormativaTirocinioEsterno> offerte, Azienda azienda) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(RICERCA_TUTTE_OFFERTE_FORMATIVE_AZIENDA);
      ps.setString(1, azienda.getEmail());
      ps.setString(2, OffertaFormativaTirocinioEsterno.ATTIVA);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        OffertaFormativaTirocinioEsterno unOfferta = new OffertaFormativaTirocinioEsterno();
        
        TutorDidattico tutorDidattico = new TutorDidattico();
        tutorDidattico.setEmail(rs.getString("TutorDidattico"));
        unOfferta.setTutorDidattico(tutorDidattico);

        TutorAziendale tutorAziendale = new TutorAziendale();
        tutorAziendale.setEmail(rs.getString("TutorAziendale"));
        unOfferta.setTutorAziendale(tutorAziendale);

        unOfferta.setId(rs.getInt("Id"));
        unOfferta.setTema(rs.getString("Tema"));

        Date dataLetta = rs.getDate("InizioTirocinio");
        GregorianCalendar dataInizio = new GregorianCalendar();
        dataInizio.setTime(dataLetta);
        unOfferta.setInizioTirocinio(dataInizio);

        dataLetta = rs.getDate("FineTirocinio");
        GregorianCalendar dataFine = new GregorianCalendar();
        dataFine.setTime(dataLetta);
        unOfferta.setFineTirocinio(dataFine);

        unOfferta.setValidita(rs.getBoolean("Validita"));
        unOfferta.setStatus(rs.getString("Status"));

        unOfferta.setAzienda(azienda);

        notError = ricercaFacilitazioni(unOfferta);

        offerte.add(unOfferta);
      }
      
      rs.close();
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }

    return notError;
  }
 
  /**
   * Questo metodo permette di ricercare tutti gli oggetti OffertaFormativaTirocinioEsterno 
   * in corso di valutazione database relative a una determinata azienda.
   * 
   * @param offerte
   *          l'oggetto da riempire con le offerte trovate sul database.
   * @param azienda
   *          l'oggetto azienda da cui si prende l'email per effettuare la ricerca.
   * @return true se le offerte formative vengono correttamente ricercate, false altrimenti.
   */
  public static synchronized boolean ricercaOfferteFormativaTirocinioEsternoInviate(
      ArrayList<OffertaFormativaTirocinioEsterno> offerte, Azienda azienda) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(RICERCA_OFFERTE_AZIENDA_DA_VALUTARE);
      ps.setString(1, azienda.getEmail());
      ps.setString(2, OffertaFormativaTirocinioEsterno.DA_VALUTARE);

      ResultSet rs = ps.executeQuery();

      OffertaFormativaTirocinioEsterno unOfferta = new OffertaFormativaTirocinioEsterno();
      while (rs.next()) {

        TutorDidattico tutorDidattico = new TutorDidattico();
        tutorDidattico.setEmail(rs.getString("TutorDidattico"));
        unOfferta.setTutorDidattico(tutorDidattico);

        TutorAziendale tutorAziendale = new TutorAziendale();
        tutorAziendale.setEmail(rs.getString("TutorAziendale"));
        unOfferta.setTutorAziendale(tutorAziendale);

        unOfferta.setId(rs.getInt("Id"));
        unOfferta.setTema(rs.getString("tema"));

        Date dataLetta = rs.getDate("InizioTirocinio");
        GregorianCalendar dataInizio = new GregorianCalendar();
        dataInizio.setTime(dataLetta);
        unOfferta.setInizioTirocinio(dataInizio);

        dataLetta = rs.getDate("FineTirocinio");
        GregorianCalendar dataFine = new GregorianCalendar();
        dataFine.setTime(dataLetta);
        unOfferta.setFineTirocinio(dataFine);

        unOfferta.setValidita(rs.getBoolean("Validita"));
        unOfferta.setStatus(rs.getString("Status"));

        unOfferta.setAzienda(azienda);

        notError = ricercaFacilitazioni(unOfferta);

        offerte.add(unOfferta);
      }
      
      rs.close();
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }

    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare tutti gli oggetti OffertaFormativaTirocinioEsterno 
   * in corso di valutazione di un dato tutor didattico.
   * 
   * @param offerte
   *          l'oggetto da riempire con le offerte trovate sul database.
   * @param tutorDidattico
   *          l'oggetto tutor didattico dal quale prendere l'email per effettuare la ricerca.
   * @return true se le offerte formative vengono correttamente ricercate, false altrimenti.
   */
 
  public static synchronized boolean ricercaOfferteFormativeTirocinioEsternoRicevuteTutorDidattico(
      ArrayList<OffertaFormativaTirocinioEsterno> offerte, TutorDidattico tutorDidattico) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(RICERCA_OFFERTE_FORMATIVE_RICEVUTE_TUTOR);
      ps.setString(1, tutorDidattico.getEmail());
      ps.setString(2, OffertaFormativaTirocinioEsterno.DA_VALUTARE);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {

        OffertaFormativaTirocinioEsterno unOfferta = new OffertaFormativaTirocinioEsterno();

        unOfferta.setTutorDidattico(tutorDidattico);

        Azienda azienda = new Azienda();
        azienda.setEmail(rs.getString("Azienda"));
        unOfferta.setAzienda(azienda);
        
        TutorAziendale tutorAziendale = new TutorAziendale();
        tutorAziendale.setEmail(rs.getString("TutorAziendale"));
        unOfferta.setTutorAziendale(tutorAziendale);

        unOfferta.setId(rs.getInt("Id"));
        unOfferta.setTema(rs.getString("tema"));

        Date dataLetta = rs.getDate("InizioTirocinio");
        GregorianCalendar dataInizio = new GregorianCalendar();
        dataInizio.setTime(dataLetta);
        unOfferta.setInizioTirocinio(dataInizio);

        dataLetta = rs.getDate("FineTirocinio");
        GregorianCalendar dataFine = new GregorianCalendar();
        dataFine.setTime(dataLetta);
        unOfferta.setFineTirocinio(dataFine);

        unOfferta.setValidita(rs.getBoolean("Validita"));
        unOfferta.setStatus(rs.getString("Status"));

        notError = ricercaFacilitazioni(unOfferta);

        offerte.add(unOfferta);
      }
      rs.close();
      ps.close();

    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }

    return notError;
  }
  
  
  /**
   * Questo metodo permette di aggiornare un oggetto OffertaFormativaTirocinioEsterno 
   * specificando validita e status da aggiornare.
   * 
   * @param unOfferta
   *          l'oggetto da cui prendere i nuovi valori da aggiornare.
   * @return true se l'offerta formativa viene correttamente aggiornata, false altrimenti.
   */
  public static synchronized boolean aggiornaOffertaFormativaTirocinioEsterno(
      OffertaFormativaTirocinioEsterno unOfferta) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(GET_ID);
      ps.setString(1, unOfferta.getTema());
      ps.setString(2, unOfferta.getAzienda().getEmail());
      ps.setString(3, OffertaFormativaTirocinioEsterno.ATTIVA);
      ResultSet rs = ps.executeQuery();

      rs.next();

      unOfferta.setId(rs.getInt("Id"));

      ps = con.prepareStatement(AGGIORNA_OFFERTA_FORMATIVA_ESTERNA);

      ps.setBoolean(1, unOfferta.isValidita());
      ps.setString(2, unOfferta.getStatus());
      ps.setString(3, unOfferta.getAzienda().getEmail());
      ps.setString(4, unOfferta.getTema());
      ps.setString(5, OffertaFormativaTirocinioEsterno.ATTIVA);

      ps.executeUpdate();

      rimuoviFacilitazioni(unOfferta);
      inserisciFacilitazioni(unOfferta);

      rs.close();
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }

    return notError;
  }

  
  /**
   * Questo metodo permette di aggiornare un oggetto OffertaFormativaTirocinioEsterno 
   * specificando lo status da aggiornare.
   * 
   * @param unOfferta
   *          l'oggetto da cui prendere i nuovi valori da aggiornare.
   * @return true se l'offerta formativa viene correttamente aggiornata, false altrimenti.
   */
  public static synchronized boolean aggiornaStatusOffertaFormativaTirocinioEsterno(
      OffertaFormativaTirocinioEsterno unOfferta) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(AGGIORNA_STATUS_OFFERTA_FORMATIVA_ESTERNA);
      ps.setString(1, unOfferta.getStatus());
      ps.setBoolean(2, unOfferta.isValidita());
      ps.setString(3, unOfferta.getAzienda().getEmail());
      ps.setString(4, unOfferta.getTema());
      ps.setString(5, OffertaFormativaTirocinioEsterno.NON_ATTIVA);
      ps.executeUpdate();

      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }

    return notError;
  }

  /**
   * Questo metodo permette di aggiornare un oggetto OffertaFormativaTirocinioEsterno 
   * specificando la validita da aggiornare.
   * 
   * @param unOfferta
   *          l'oggetto da cui prendere i nuovi valori da aggiornare.
   * @return true se l'offerta formativa viene correttamente aggiornata, false altrimenti.
   */
  public static synchronized boolean aggiornaValiditaOffertaFormativaTirocinioEsterno(
      OffertaFormativaTirocinioEsterno unOfferta) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(AGGIORNA_VALIDITA_OFFERTA_FORMATIVA_ESTERNA);
      ps.setBoolean(1, unOfferta.isValidita());
      ps.setString(2, unOfferta.getAzienda().getEmail());
      ps.setString(3, unOfferta.getTema());
      ps.setString(4, OffertaFormativaTirocinioEsterno.ATTIVA);
      ps.executeUpdate();

      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }

    return notError;
  }

  private static synchronized boolean inserisciFacilitazioni(
      OffertaFormativaTirocinioEsterno unOfferta) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      String[] facilitazioni = unOfferta.getFacilitazioni();

      for (String facilitazione : facilitazioni) {
        PreparedStatement ps = con.prepareStatement(INSERISCI_FACILITAZIONE);
        ps.setInt(1, unOfferta.getId());
        ps.setString(2, facilitazione);
        ps.executeUpdate();

        ps.close();
      }
    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }

    return notError;
  }
  
  /**
   * Questo metodo permette di ricercare un array di oggetti Facilitazione 
   * relative a un'offerta formativa.
   * 
   * @param unOfferta
   *          l'oggetto in cui settare l'array
   * @return true se le facilitazioni vengono correttamente trovate, false altrimenti.
   */

  private static synchronized boolean ricercaFacilitazioni(
      OffertaFormativaTirocinioEsterno unOfferta) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(RICERCA_FACILITAZIONE);
      ps.setInt(1, unOfferta.getId());

      ResultSet rs = ps.executeQuery();

      ArrayList<String> facilitazioniArrayList = new ArrayList<>();
      while (rs.next()) {
        String facilitazione = rs.getString("Descrizione");
        facilitazioniArrayList.add(facilitazione);
      }

      String[] facilitazioni = new String[facilitazioniArrayList.size()];
      for (int i = 0; i < facilitazioni.length; i++) {
        facilitazioni[i] = facilitazioniArrayList.get(i);
      }

      unOfferta.setFacilitazioni(facilitazioni);

      rs.close();
      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }
    return notError;
  }

  /**
   * Questo metodo permette di rimuovere gli oggetti Facilitazione relative a un'offerta formativa.
   * 
   * @param unOfferta
   *          l'oggetto offerta formativa di cui si vuole eliminare le facilitazioni.
   * @return true se le facilitazioni vengono correttamente eliminate, false altrimenti.
   */
  private static synchronized boolean rimuoviFacilitazioni(
      OffertaFormativaTirocinioEsterno unOfferta) {
    Connection con = null;
    boolean notError = true;

    try {
      con = DriverManagerConnectionPool.getConnection();

      PreparedStatement ps = con.prepareStatement(RIMUOVI_FACILITAZIONI);
      ps.setInt(1, unOfferta.getId());

      ps.executeUpdate();

      ps.close();
    } catch (SQLException e) {
      Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
          e);
      notError = false;
    } finally {
      try {
        DriverManagerConnectionPool.releaseConnection(con);
      } catch (SQLException e) {
        Logger.getLogger("OffertaFormativaTirocinioEsternoDao").log(Level.SEVERE, "Database Error",
            e);
        notError = false;
      }
    }
    return notError;
  }

  private static final String NOME_TABELLA_OFFERTA_FORMATIVA = 
      " eTraining.OffertaFormativaTirocinioEsterno ";

  private static final String NOME_TABELLA_FACILITAZIONE = " eTraining.Facilitazione ";

  private static final String SALVA_OFFERTA_FORMATIVA = "INSERT INTO" 
      + NOME_TABELLA_OFFERTA_FORMATIVA
      + "(TutorDidattico,TutorAziendale,Tema,InizioTirocinio,"
      + "FineTirocinio,Validita,Status,Azienda) "
      + "VALUES(?,?,?,?,?,?,?,?)";

  private static final String INSERISCI_FACILITAZIONE = "INSERT INTO" + NOME_TABELLA_FACILITAZIONE
      + "VALUES(?,?)";

  private static final String RICERCA_OFFERTA_FORMATIVA = "SELECT * " + "FROM "
      + NOME_TABELLA_OFFERTA_FORMATIVA
      + "WHERE Tema = ? AND Azienda=? AND Validita=1 AND Status = ?";
  
  private static final String RICERCA_OFFERTA_FORMATIVA_BY_ID = "SELECT * " + "FROM "
      + NOME_TABELLA_OFFERTA_FORMATIVA
      + "WHERE Id = ?";

  private static final String GET_ID = "SELECT Id " + "FROM " + NOME_TABELLA_OFFERTA_FORMATIVA
      + "WHERE Tema = ? AND Azienda=? AND Validita=1 AND Status = ?";

  private static final String RICERCA_TUTTE_OFFERTE_FORMATIVE_AZIENDA = "SELECT * " + "FROM "
      + NOME_TABELLA_OFFERTA_FORMATIVA + "WHERE Azienda=? AND Validita=1 AND Status = ?";

  private static final String RICERCA_OFFERTE_AZIENDA_DA_VALUTARE = "SELECT * " + "FROM "
      + NOME_TABELLA_OFFERTA_FORMATIVA + "WHERE Azienda=? AND Validita=0 AND Status = ?";

  private static final String RICERCA_OFFERTE_FORMATIVE_RICEVUTE_TUTOR = "SELECT *" + "FROM "
      + NOME_TABELLA_OFFERTA_FORMATIVA + "WHERE TutorDidattico = ? AND Status = ?";
  
  private static final String AGGIORNA_OFFERTA_FORMATIVA_ESTERNA = "UPDATE "
      + NOME_TABELLA_OFFERTA_FORMATIVA + "SET Validita = ?, Status = ? "
      + "WHERE Azienda = ? AND Tema = ? AND Validita=1 AND Status = ?";
  
  private static final String AGGIORNA_STATUS_OFFERTA_FORMATIVA_ESTERNA = "UPDATE "
      + NOME_TABELLA_OFFERTA_FORMATIVA + "SET Status = ?, Validita = ? "
      + "WHERE Azienda = ? AND Tema = ? AND Validita=0 AND Status <> ?";

  private static final String AGGIORNA_VALIDITA_OFFERTA_FORMATIVA_ESTERNA = "UPDATE "
      + NOME_TABELLA_OFFERTA_FORMATIVA + "SET Validita = ? "
      + "WHERE Azienda = ? AND Tema = ? AND Validita=1 AND Status = ?";

  private static final String RICERCA_FACILITAZIONE = "SELECT * " + "FROM"
      + NOME_TABELLA_FACILITAZIONE + "WHERE IdOffertaFormativaTirocinioEsterno = ?";

  private static final String RIMUOVI_FACILITAZIONI = "DELETE FROM " + NOME_TABELLA_FACILITAZIONE
      + "WHERE IdOffertaFormativaTirocinioEsterno = ?";

  private static final String RICERCA_ID_OFFERTA_FORMATIVA = "SELECT max(Id) " + "FROM"
      + NOME_TABELLA_OFFERTA_FORMATIVA;

}
