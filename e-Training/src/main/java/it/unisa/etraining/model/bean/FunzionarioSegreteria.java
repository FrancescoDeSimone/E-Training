package it.unisa.etraining.model.bean;

/**
 * Un oggetto <code>FunzionarioSegreteria</code> rappresenta un funzionario che può
 * utilizzare le funzionalità del sistema.
 *
 * @author Esposito Flavio
 */

public class FunzionarioSegreteria extends Utente {

  /**
   * Costruttore vuoto di <code>FunzionarioSegreteria</code>.
   */

  public FunzionarioSegreteria() {
    super();
  }

  /**
   * Metodo getter di <code>nome</code>.
   * 
   * @return il nome del funzionario
   */

  public String getNome() {
    return nome;
  }

  /**
   * Metodo setter di <code>nome</code>.
   * 
   * @param nome
   *          il nome del funzionario
   */

  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Metodo getter di <code>cognome</code>.
   * 
   * @return il cognome del funzionario
   */

  public String getCognome() {
    return cognome;
  }

  /**
   * Metodo setter di <code>cognome</code>.
   * 
   * @param cognome
   *          il cognome del funzionario
   */

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Metodo getter di <code>codiceFiscale</code>.
   * 
   * @return il codice fiscale del funzionario
   */

  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  /**
   * Metodo setter di <code>codiceFiscale</code>.
   * 
   * @param codiceFiscale
   *          il codice fiscale del funzionario
   */

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * Metodo getter di <code>qualifiche</code>.
   * 
   * @return le qualifiche del funzionario
   */

  public String[] getQualifiche() {
    return qualifiche;
  }

  /**
   * Metodo setter di <code>qualifiche</code>.
   * 
   * @param qualifiche
   *          le qualifiche del funzionario
   */

  public void setQualifiche(String[] qualifiche) {
    this.qualifiche = qualifiche;
  }

  private String nome;
  private String cognome;
  private String codiceFiscale;
  private String[] qualifiche;
}
