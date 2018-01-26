package it.unisa.etraining.model.bean;

import java.util.GregorianCalendar;

/**
 * Un oggetto <code>OffertaFormativaTirocinioEsterno</code> rappresenta
 * un'offerta formativa di tirocinio esterno.
 *
 * @author Esposito Flavio
 */

public class OffertaFormativaTirocinioEsterno {

  /**
   * Costruttore vuoto di <code>OffertaFormativaTirocinioEsterno</code>.
   */

  public OffertaFormativaTirocinioEsterno() {

  }

  /**
   * Metodo getter di <code>id</code>.
   * 
   * @return l'id dell'offerta formativa
   */

  public int getId() {
    return id;
  }

  /**
   * Metodo setter di <code>id</code>.
   * 
   * @param id
   *          l'id dell'offerta formativa
   */

  public void setId(int id) {
    this.id = id;
  }

  /**
   * Metodo getter di <code>tema</code>.
   * 
   * @return il tema dell'offerta formativa
   */

  public String getTema() {
    return tema;
  }

  /**
   * Metodo setter di <code>tema</code>.
   * 
   * @param tema
   *          il tema dell'offerta formativa
   */

  public void setTema(String tema) {
    this.tema = tema;
  }

  /**
   * Metodo getter di <code>inizioTirocinio</code>.
   * 
   * @return la data di inizio del tirocinio
   */

  public GregorianCalendar getInizioTirocinio() {
    return inizioTirocinio;
  }

  /**
   * Metodo setter di <code>inizioTirocinio</code>.
   * 
   * @param inizioTirocinio
   *          la data di inizio del tirocinio
   */

  public void setInizioTirocinio(GregorianCalendar inizioTirocinio) {
    this.inizioTirocinio = inizioTirocinio;
  }

  /**
   * Metodo getter di <code>fineTirocinio</code>.
   * 
   * @return la data di fine del tirocinio
   */

  public GregorianCalendar getFineTirocinio() {
    return fineTirocinio;
  }

  /**
   * Metodo setter di <code>fineTirocinio</code>.
   * 
   * @param fineTirocinio
   *          la data di fine del tirocinio
   */

  public void setFineTirocinio(GregorianCalendar fineTirocinio) {
    this.fineTirocinio = fineTirocinio;
  }

  /**
   * Metodo getter di <code>validita</code>.
   * 
   * @return <code>true</code> se l'offerta è valida, <code>false</code>
   *         altrimenti.
   */

  public boolean isValidita() {
    return validita;
  }

  /**
   * Metodo setter di <code>validita</code>.
   * 
   * @param validita
   *          la validità dell'offerta formativa
   */

  public void setValidita(boolean validita) {
    this.validita = validita;
  }

  /**
   * Metodo getter di <code>facilitazioni</code>.
   * 
   * @return le facilitazioni previste dall'offerta formativa
   */

  public String[] getFacilitazioni() {
    return facilitazioni;
  }

  /**
   * Metodo setter di <code>facilitazioni</code>.
   * 
   * @param facilitazioni
   *          le facilitazioni previste dall'offerta formativa
   */

  public void setFacilitazioni(String[] facilitazioni) {
    this.facilitazioni = facilitazioni;
  }

  /**
   * Metodo getter di <code>status</code>.
   * 
   * @return lo status dell'offerta formativa
   */

  public String getStatus() {
    return status;
  }

  /**
   * Metodo setter di <code>status</code>.
   * 
   * @param status
   *          lo status dell'offerta formativa
   */

  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Metodo getter di <code>tutorDidattico</code>.
   * 
   * @return il tutor didattico dell'offerta formativa
   */

  public TutorDidattico getTutorDidattico() {
    return tutorDidattico;
  }

  /**
   * Metodo setter di <code>tutorDidattico</code>.
   * 
   * @param tutorDidattico
   *          il tutor didattico dell'offerta formativa
   */

  public void setTutorDidattico(TutorDidattico tutorDidattico) {
    this.tutorDidattico = tutorDidattico;
  }

  /**
   * Metodo getter di <code>tutorAziendale</code>.
   * 
   * @return il tutor aziendale dell'offerta formativa
   */

  public TutorAziendale getTutorAziendale() {
    return tutorAziendale;
  }

  /**
   * Metodo setter di <code>tutorAziendale</code>.
   * 
   * @param tutorAziendale
   *          il tutor aziendale dell'offerta formativa
   */

  public void setTutorAziendale(TutorAziendale tutorAziendale) {
    this.tutorAziendale = tutorAziendale;
  }

  /**
   * Metodo getter di <code>azienda</code>.
   * 
   * @return l'azienda dell'offerta formativa
   */

  public Azienda getAzienda() {
    return azienda;
  }

  /**
   * Metodo setter di <code>azienda</code>.
   * 
   * @param azienda
   *          l'azienda dell'offerta formativa
   */

  public void setAzienda(Azienda azienda) {
    this.azienda = azienda;
  }

  private int id;
  private String tema;
  private GregorianCalendar inizioTirocinio;
  private GregorianCalendar fineTirocinio;
  private boolean validita;
  private String[] facilitazioni;
  private String status;
  private TutorDidattico tutorDidattico;
  private TutorAziendale tutorAziendale;
  private Azienda azienda;
  
  public static final String DA_VALUTARE = "In attesa di valutazione del Tutor Didattico";
  public static final String ATTIVA = "Attiva";
  public static final String DA_MODIFICARE = "Da Modificare";
  public static final String NON_ATTIVA = "Non Attiva";
}
