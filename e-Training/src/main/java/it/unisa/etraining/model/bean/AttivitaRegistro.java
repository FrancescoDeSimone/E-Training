package it.unisa.etraining.model.bean;

import java.util.GregorianCalendar;

/**
 * Un oggetto <code>AttivitaRegistro</code> rappresenta un'attività svolta
 * durante un tirocinio.
 * 
 * @author Esposito Flavio
 */

public class AttivitaRegistro {

  /**
   * Costruttore vuoto <code>AttivitaRegistro</code>.
   */
  public AttivitaRegistro() {

  }

  /**
   * Metodo getter di <code>attivitaSvolta</code>.
   * 
   * @return l'attività svolta
   */

  public String getAttivitaSvolta() {
    return attivitaSvolta;
  }

  /**
   * Metodo setter di <code>attivitaSvolta</code>.
   * 
   * @param attivitaSvolta
   *          l'attività svolta
   */

  public void setAttivitaSvolta(String attivitaSvolta) {
    this.attivitaSvolta = attivitaSvolta;
  }

  /**
   * Metodo getter di <code>inizio</code>.
   * 
   * @return la data di inizio dell'attività
   */

  public GregorianCalendar getInizio() {
    return inizio;
  }

  /**
   * Metodo setter di <code>inizio</code>.
   * 
   * @param inizio
   *          la data di inizio dell'attività
   */

  public void setInizio(GregorianCalendar inizio) {
    this.inizio = inizio;
  }

  /**
   * Metodo getter di <code>fine</code>.
   * 
   * @return la data di fine dell'attività
   */

  public GregorianCalendar getFine() {
    return fine;
  }

  /**
   * Metodo setter di <code>fine</code>.
   * 
   * @param fine
   *          la data di fine dell'attività
   */

  public void setFine(GregorianCalendar fine) {
    this.fine = fine;
  }

  /**
   * Metodo getter di <code>oreSvolte</code>.
   * 
   * @return le ore di attività svolte
   */

  public int getOreSvolte() {
    return oreSvolte;
  }

  /**
   * Metodo setter di <code>oreSvolte</code>.
   * 
   * @param oreSvolte
   *          le ore di attività svolte
   */

  public void setOreSvolte(int oreSvolte) {
    this.oreSvolte = oreSvolte;
  }

  /**
   * Metodo getter di <code>convalida</code>.
   * 
   * @return <code>true</code> se l'attività è stata convalidata,
   *         <code>false</code> altrimenti.
   */

  public String getConvalida() {
    return convalida;
  }

  /**
   * Metodo setter di <code>convalida</code>.
   * 
   * @param convalida
   *          il flag di convalida
   */

  public void setConvalida(String convalida) {
    this.convalida = convalida;
  }

  /**
   * Metodo getter di <code>motivazioniRifiuto</code>.
   * 
   * @return le motivazioni del rifiuto dell'attività
   */

  public String getMotivazioneRifiuto() {
    return motivazioneRifiuto;
  }

  /**
   * Metodo setter di <code>motivazioneRifiuto</code>.
   * 
   * @param motivazioneRifiuto
   *          la motivazione del rifiuto dell'attività
   */

  public void setMotivazioneRifiuto(String motivazioneRifiuto) {
    this.motivazioneRifiuto = motivazioneRifiuto;
  }

  /**
   * Metodo getter di <code>tirocinio</code>.
   * 
   * @return il tirocinio in cui l'attività è stata svolta
   */

  public Tirocinio getTirocinio() {
    return tirocinio;
  }

  /**
   * Metodo setter di <code>tirocinio</code>.
   * 
   * @param tirocinio
   *          il tirocinio in cui l'attività è stata svolta
   */

  public void setTirocinio(Tirocinio tirocinio) {
    this.tirocinio = tirocinio;
  }
  
  private String attivitaSvolta;
  private GregorianCalendar inizio;
  private GregorianCalendar fine;
  private int oreSvolte;
  private String convalida;
  private String motivazioneRifiuto;
  private Tirocinio tirocinio;
  
  public static final String IN_CONVALIDA = "In attesa di convalida del Tutor Aziendale";
  public static final String CONVALIDATA_TUTOR_AZIENDALE = "In attesa di convalida "
      + "del Tutor Didattico";
  public static final String CONVALIDATA = "Convalidata";
  public static final String NON_CONVALIDATA = "Non convalidata";
}
