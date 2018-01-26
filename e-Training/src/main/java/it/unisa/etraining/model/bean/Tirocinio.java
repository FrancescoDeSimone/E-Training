package it.unisa.etraining.model.bean;

/**
 * Un oggetto <code>Tirocinio</code> rappresenta un tirocinio che sarà svolto da
 * un tirocinante.
 * 
 * @author Esposito Flavio
 */

public class Tirocinio {

  /**
   * Costruttore vuoto di <code>Tirocinio</code>.
   */

  public Tirocinio() {

  }

  /**
   * Metodo getter di <code>tirocinante</code>.
   * 
   * @return il tirocinante del tirocinio
   */

  public Tirocinante getTirocinante() {
    return tirocinante;
  }

  /**
   * Metodo setter di <code>tirocinante</code>.
   * 
   * @param tirocinante
   *          il tirocinante del tirocinio
   */

  public void setTirocinante(Tirocinante tirocinante) {
    this.tirocinante = tirocinante;
  }

  /**
   * Metodo getter di <code>offerta</code>.
   * 
   * @return l'offerta del tirocinio
   */

  public OffertaFormativaTirocinioEsterno getOfferta() {
    return offerta;
  }

  /**
   * Metodo setter di <code>offerta</code>.
   * 
   * @param offerta
   *          l'offerta del tirocinio
   */

  public void setOfferta(OffertaFormativaTirocinioEsterno offerta) {
    this.offerta = offerta;
  }

  /**
   * Metodo getter di <code>cfu</code>.
   * 
   * @return i cfu del tirocinio
   */

  public int getCfu() {
    return cfu;
  }

  /**
   * Metodo setter di <code>cfu</code>.
   * 
   * @param cfu
   *          i cfu del tirocinio
   */

  public void setCfu(int cfu) {
    this.cfu = cfu;
  }

  /**
   * Metodo getter di <code>obiettivi</code>.
   * 
   * @return gli obiettivi del tirocinio
   */

  public String[] getObiettivi() {
    return obiettivi;
  }

  /**
   * Metodo setter di <code>obiettivi</code>.
   * 
   * @param obiettivi
   *          gli obiettivi del tirocinio
   */

  public void setObiettivi(String[] obiettivi) {
    this.obiettivi = obiettivi;
  }

  /**
   * Metodo getter di <code>status</code>.
   * 
   * @return lo status del tirocinio
   */

  public String getStatus() {
    return status;
  }

  /**
   * Metodo setter di <code>status</code>.
   * 
   * @param status
   *          lo status del tirocinio
   */

  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Metodo getter di <code>oreRimanenti</code>.
   * 
   * @return le ore rimanenti del tirocinio
   */

  public int getOreRimanenti() {
    return oreRimanenti;
  }

  /**
   * Metodo setter di <code>oreRimanenti</code>.
   * 
   * @param oreRimanenti
   *          le ore rimanenti del tirocinio
   */

  public void setOreRimanenti(int oreRimanenti) {
    this.oreRimanenti = oreRimanenti;
  }

  /**
   * Metodo getter di <code>attivitaSvolte</code>.
   * 
   * @return le attività svolte del tirocinio
   */

  public AttivitaRegistro[] getAttivitaSvolte() {
    return attivitaSvolte;
  }

  /**
   * Metodo setter di <code>attivitaSvolte</code>.
   * 
   * @param attivitaSvolte
   *          le attività svolte del tirocinio
   */

  public void setAttivitaSvolte(AttivitaRegistro[] attivitaSvolte) {
    this.attivitaSvolte = attivitaSvolte;
  }

  private Tirocinante tirocinante;
  private OffertaFormativaTirocinioEsterno offerta;
  private int cfu;
  private String[] obiettivi;
  private String status;
  private int oreRimanenti;
  private AttivitaRegistro[] attivitaSvolte;
  
  public static final String VALUTAZIONE_DOCENTE = "In attesa di valutazione del Tutor Didattico";
  public static final String VALUTAZIONE_AZIENDA = "In attesa di valutazione dell'Azienda";
  public static final String IN_CORSO = "In corso";
  public static final String RIFIUTATO = "Rifiutato";
  public static final String TERMINATO = "Terminato";
}
