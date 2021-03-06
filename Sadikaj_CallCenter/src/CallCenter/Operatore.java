package CallCenter;

import java.io.Serializable;

public class Operatore implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codice;
	private String nome;
	private String cognome;
	
	public Operatore(String codice, String nome, String cognome) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String toString() {
		return String.format("| %-10s | %-26s |", codice, (nome + cognome));
	}
}
