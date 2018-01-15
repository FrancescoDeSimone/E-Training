package it.unisa.etraining.model.bean;

/**
 * Un oggetto <code>Utente</code> rappresenta un attore che può usare le
 * funzionalità del sistema, come ad esempio "Tirocinante".
 *
 * @author Esposito Flavio
 */

public class Utente {

  /**
   * Costruttore vuoto della classe <code>Utente</code>.
   */

  public Utente() {

  }

  /**
   * Metodo getter per <code>email</code>.
   * 
   * @return l'email dell'utente
   */

  public String getEmail() {
    return email;
  }

  /**
   * Metodo setter per <code>email</code>.
   * 
   * @param email
   *          l'email dell'utente
   */

  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Metodo getter per <code>password</code>.
   * 
   * @return la password dell'utente
   */

  public String getPassword() {
    return password;
  }

  /**
   * Metodo setter per <code>password</code>.
   * 
   * @param password
   *          la password dell'utente
   */

  public void setPassword(String password) {
    this.password = password;
  }

  private String email;
  private String password;
}
