package it.unisa.etraining.model.bean;

/**
 * Un oggetto <code>TitolareAzienda</code> rappresenta un titolare di un'azienda
 * che può utilizzare le funzionalità del sistema.
 *
 * @author Esposito Flavio
 */

public class TitolareAzienda {

  /**
   * Costruttore vuoto di <code>TitolareAzienda</code>.
   */

  public TitolareAzienda() {

  }

  /**
   * Metodo getter di <code>nome</code>.
   * 
   * @return il nome del titolare dell'azienda
   */

  public String getNome() {
    return nome;
  }

  /**
   * Metodo setter di <code>nome</code>.
   * 
   * @param nome
   *          il nome del titolare dell'azienda
   */

  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Metodo getter di <code>cognome</code>.
   * 
   * @return il cognome del titolare dell'azienda
   */

  public String getCognome() {
    return cognome;
  }

  /**
   * Metodo setter di <code>cognome</code>.
   * 
   * @param cognome
   *          il cognome del titolare dell'azienda
   */

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Metodo getter di <code>codiceFiscale</code>.
   * 
   * @return il codice fiscale del titolare dell'azienda
   */

  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  /**
   * Metodo setter di <code>codiceFiscale</code>.
   * 
   * @param codiceFiscale
   *          il codice fiscale del titolare dell'azienda
   */

  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * Metodo getter di <code>azienda</code>.
   * 
   * @return l'azienda del titolare
   */

  public Azienda getAzienda() {
    return azienda;
  }

  /**
   * Metodo setter di <code>azienda</code>.
   * 
   * @param azienda
   *          l'azienda del titolare
   */

  public void setAzienda(Azienda azienda) {
    this.azienda = azienda;
  }

  private String nome;
  private String cognome;
  private String codiceFiscale;
  private Azienda azienda;
}
