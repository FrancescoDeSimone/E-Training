package it.unisa.etraining.model.bean;

/**
 * Un oggetto <code>Tirocinante</code> rappresenta un tirocinante che può
 * utilizzare le funzionalità del sistema.
 * 
 *  @author Esposito Flavio
 */

public class Tirocinante extends Utente {

  /**
   * Costruttore vuoto di <code>Tirocinante</code>.
   */

  public Tirocinante() {
    super();
  }

  /**
   * Metodo getter per <code>nome</code>.
   * 
   * @return il nome del tirocinante
   */

  public String getNome() {
    return nome;
  }

  /**
   * Metodo setter per <code>nome</code>.
   * 
   * @param nome
   *          il nome del tirocinante
   */

  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Metodo getter per <code>cognome</code>.
   * 
   * @return il cognome del tirocinante
   */

  public String getCognome() {
    return cognome;
  }

  /**
   * Metodo setter per <code>cognome</code>.
   * 
   * @param cognome
   *          il cognome del tirocinante
   */

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Metodo getter per <code>matricola</code>.
   * 
   * @return la matricola del tirocinante
   */

  public String getMatricola() {
    return matricola;
  }

  /**
   * Metodo setter per <code>matricola</code>.
   * 
   * @param matricola
   *          la matricola del tirocinante
   */

  public void setMatricola(String matricola) {
    this.matricola = matricola;
  }

  /**
   * Metodo getter per <code>annoIscrizione</code>.
   * 
   * @return l'anno di iscrizione del tirocinante
   */

  public String getAnnoIscrizione() {
    return annoIscrizione;
  }

  /**
   * Metodo setter per <code>annoIscrizione</code>.
   * 
   * @param annoIscrizione
   *          l'anno di iscrizione del tirocinante
   */

  public void setAnnoIscrizione(String annoIscrizione) {
    this.annoIscrizione = annoIscrizione;
  }

  private String nome;
  private String cognome;
  private String matricola;
  private String annoIscrizione;
}
