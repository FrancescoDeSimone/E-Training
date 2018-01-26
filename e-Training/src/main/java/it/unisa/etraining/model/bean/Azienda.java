package it.unisa.etraining.model.bean;

/**
 * Un oggetto <code>Azienda</code> rappresenta un'azienda che può utilizzare le
 * funzionalità del sistema.
 *
 * @author Esposito Flavio
 */

public class Azienda extends Utente {
  
  /**
   * Costruttore vuoto di <code>Azienda</code>.
   * 
   */
  public Azienda() {
    super();
  }

  /**
   * Metodo getter di <code>nome</code>.
   * 
   * @return il nome dell'azienda
   */

  public String getNome() {
    return nome;
  }

  /**
   * Metodo setter di <code>nome</code>.
   * 
   * @param nome
   *          il nome dell'azienda
   */

  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Metodo getter di <code>partitaIva</code>.
   * 
   * @return la partita Iva dell'azienda
   */

  public String getPartitaIva() {
    return partitaIva;
  }

  /**
   * Metodo setter di <code>partitaIva</code>.
   * 
   * @param partitaIva
   *          la partita Iva dell'azienda
   */

  public void setPartitaIva(String partitaIva) {
    this.partitaIva = partitaIva;
  }

  /**
   * Metodo getter di <code>sede</code>.
   * 
   * @return la sede dell'azienda
   */

  public String getSede() {
    return sede;
  }

  /**
   * Metodo setter di <code>sede</code>.
   * 
   * @param sede
   *          la sede dell'azienda
   */

  public void setSede(String sede) {
    this.sede = sede;
  }

  /**
   * Metodo getter di <code>citta</code>.
   * 
   * @return la citta dell'azienda
   */

  public String getCitta() {
    return citta;
  }

  /**
   * Metodo setter di <code>citta</code>.
   * 
   * @param citta
   *          la città dell'azienda
   */

  public void setCitta(String citta) {
    this.citta = citta;
  }

  /**
   * Metodo getter di <code>titolareAzienda</code>.
   * 
   * @return il titolare dell'azienda
   */

  public TitolareAzienda getTitolareAzienda() {
    return titolareAzienda;
  }

  /**
   * Metodo setter di <code>titolareAzienda</code>.
   * 
   * @param titolareAzienda
   *          il titolare dell'azienda
   */

  public void setTitolareAzienda(TitolareAzienda titolareAzienda) {
    this.titolareAzienda = titolareAzienda;
  }

  /**
   * Metodo getter di <code>tutorAziendale</code>.
   * 
   * @return il tutor aziendale dell'azienda
   */

  public TutorAziendale[] getTutorAziendali() {
    return tutorAziendali;
  }

  /**
   * Metodo setter di <code>tutorAziendale</code>.
   * 
   * @param tutorAziendali
   *          il tutor aziendale dell'azienda
   */

  public void setTutorAziendali(TutorAziendale[] tutorAziendali) {
    this.tutorAziendali = tutorAziendali;
  }

  private String nome;
  private String partitaIva;
  private String sede;
  private String citta;
  private TitolareAzienda titolareAzienda;
  private TutorAziendale[] tutorAziendali;
}
