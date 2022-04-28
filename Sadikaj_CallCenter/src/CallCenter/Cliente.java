package CallCenter;

public class Cliente {
	
	private String codice;
	private String nome;
	private String cognome;
	private String telefono;
	private Telefonata ultima;
	
	public Cliente(String codice, String nome, String cognome, String telefono, Telefonata ultima) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.ultima = ultima;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Telefonata getUltima() {
		return ultima;
	}

	public void setUltima(Telefonata ultima) {
		this.ultima = ultima;
	}
	
	
	
}
