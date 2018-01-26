package it.unisa.etraining.model.bean;

/**
 * Un oggetto <code>TutorAziendale</code> rappresenta un tutor aziendale che può
 * utilizzare le funzionalità del sistema.
 *
 * @author Esposito Flavio
 */

public class TutorAziendale extends Utente {

  /**
   * Costruttore vuoto di <code>TutorAziendale</code>.
   */

  public TutorAziendale() {

  }

  /**
   * Metodo getter di <code>nome</code>.
   * 
   * @return il nome del tutor aziendale
   */

  public String getNome() {
    return nome;
  }

  /**
   * Metodo setter di <code>nome</code>.
   * 
   * @param nome
   *          il nome del tutor aziendale
   */

  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Metodo getter di <code>cognome</code>.
   * 
   * @return il cognome del tutor aziendale
   */

  public String getCognome() {
    return cognome;
  }

  /**
   * Metodo setter di <code>cognome</code>.
   * 
   * @param cognome
   *          il cognome del tutor aziendale
   */

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Metodo getter di <code>codiceFiscale</code>.
   * 
   * @return il codice fiscale del tutor aziendale
   */

  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  /**
   * Metodo setter di <code>codiceFiscale</code>.
   * 
   * @param codiceFiscale
   *          il codice fiscale del tutor aziendale
   */

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * Metodo getter di <code>settoreLavoro</code>.
   * 
   * @return il settore di lavoro del tutor aziendale
   */

  public String getSettoreLavoro() {
    return settoreLavoro;
  }

  /**
   * Metodo setter di <code>settoreLavoro</code>.
   * 
   * @param settoreLavoro
   *          il settore di lavoro del tutor aziendale
   */

  public void setSettoreLavoro(String settoreLavoro) {
    this.settoreLavoro = settoreLavoro;
  }

  /**
   * Metodo getter di <code>azienda</code>.
   * 
   * @return l'azienda del tutor aziendale
   */

  public Azienda getAzienda() {
    return azienda;
  }

  /**
   * Metodo setter di <code>azienda</code>.
   * 
   * @param azienda
   *          l'azienda del tutor aziendale
   */

  public void setAzienda(Azienda azienda) {
    this.azienda = azienda;
  }

  private String nome;
  private String cognome;
  private String codiceFiscale;
  private String settoreLavoro;
  private Azienda azienda;
}
