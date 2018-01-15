package it.unisa.etraining.model.bean;

/**
 * Un oggetto <code>TutorDidattico</code> rappresenta un tutor didattico che può
 * utilizzare le funzionalità del sistema.
 *
 * @author Esposito Flavio
 */

public class TutorDidattico extends Utente {

  /**
   * Costruttore vuoto di <code>TutorDidattico</code>.
   */

  public TutorDidattico() {
    super();
  }

  /**
   * Metodo getter di <code>nome</code>.
   * 
   * @return il nome del tutor didattico
   */

  public String getNome() {
    return nome;
  }

  /**
   * Metodo setter di <code>nome</code>.
   * 
   * @param nome
   *          il nome del tutor didattico
   */

  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Metodo getter di <code>cognome</code>.
   * 
   * @return il cognome del tutor didattico
   */

  public String getCognome() {
    return cognome;
  }

  /**
   * Metodo setter di <code>cognome</code>.
   * 
   * @param cognome
   *          il cognome del tutor didattico
   */

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Metodo getter di <code>codiceFiscale</code>.
   * 
   * @return il codice fiscale del tutor didattico
   */

  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  /**
   * Metodo setter di <code>codiceFiscale</code>.
   * 
   * @param codiceFiscale
   *          il codice fiscale del tutor didattico
   */

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * Metodo getter di <code>campiInteresse</code>.
   * 
   * @return i campi di interesse del tutor didattico
   */

  public String[] getCampiInteresse() {
    return campiInteresse;
  }

  /**
   * Metodo setter di <code>campiInteresse</code>.
   * 
   * @param campiInteresse
   *          i campi di interesse del tutor didattico
   */

  public void setCampiInteresse(String[] campiInteresse) {
    this.campiInteresse = campiInteresse;
  }

  /**
   * Metodo getter di <code>insegnamenti</code>.
   * 
   * @return gli insegnamenti del tutor didattico
   */

  public String[] getInsegnamenti() {
    return insegnamenti;
  }

  /**
   * Metodo setter di <code>insegnamenti</code>.
   * 
   * @param insegnamenti
   *          gli insegnamenti del tutor didattico
   */

  public void setInsegnamenti(String[] insegnamenti) {
    this.insegnamenti = insegnamenti;
  }

  /**
   * Metodo getter di <code>disponibilita</code>.
   * 
   * @return <code>true</code> se il tutor didattico è disponibile,
   *         <code>false</code> altrimenti.
   */

  public boolean isDisponibilita() {
    return disponibilita;
  }

  /**
   * Metodo setter di <code>disponibilita</code>.
   * 
   * @param disponibilita
   *          la disponibilità del tutor didattico
   */

  public void setDisponibilita(boolean disponibilita) {
    this.disponibilita = disponibilita;
  }

  private String nome;
  private String cognome;
  private String codiceFiscale;
  private String[] campiInteresse;
  private String[] insegnamenti;
  private boolean disponibilita;
}
