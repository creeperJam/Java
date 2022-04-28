package CallCenter;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Telefonata {
	
	private Operatore o;
	private LocalDateTime dataOraInizio;
	private LocalDateTime dataOraFine;
	private String numero; // numero di telefono del chiamante, non dell'operatore

	public Telefonata(Operatore o, LocalDateTime dataOraInizio, LocalDateTime dataOraFine, String numero) {
		super();
		this.o = o;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
	}

	public Telefonata(Telefonata t) {
		super();
		this.dataOraInizio = t.dataOraInizio;
		this.dataOraFine = t.dataOraFine;
		this.numero = t.numero;
	}
	
	public Operatore getOperatore() {
		return this.o;
	}
	
	public String getNumero() {
		return numero;
	}

	public LocalDateTime getDataOraInizio() {
		return dataOraInizio;
	}

	public LocalDateTime getDataOraFine() {
		return dataOraFine;
	}

	public long durata() {
		return SECONDS.between(dataOraInizio, dataOraFine);
	}
	
	private String toStringDateTime(LocalDateTime ldt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String formattedDateTime = ldt.format(formatter);
		return formattedDateTime;
	}
	
	
}
