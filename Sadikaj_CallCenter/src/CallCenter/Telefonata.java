package CallCenter;

import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Telefonata {
	
	private Operatore o;
	private LocalDateTime dataOraInizio;
	private LocalDateTime dataOraFine;
	private Cliente c;

	public Telefonata(Operatore o, LocalDateTime dataOraInizio, LocalDateTime dataOraFine, Cliente c) {
		super();
		this.o = o;
		this.dataOraInizio = dataOraInizio;
		this.dataOraFine = dataOraFine;
		this.c = c;
	}

	public Telefonata(Telefonata t) {
		super();
		this.o = t.o;
		this.dataOraInizio = t.dataOraInizio;
		this.dataOraFine = t.dataOraFine;
		this.c = t.c;
	}
	
	public Operatore getOperatore() {
		return this.o;
	}
	
	public Cliente getCliente() {
		return this.c;
	}

	public LocalDateTime getDataOraInizio() {
		return this.dataOraInizio;
	}

	public LocalDateTime getDataOraFine() {
		return this.dataOraFine;
	}

	public long durata() {
		return SECONDS.between(dataOraInizio, dataOraFine);
	}
	
	private String toStringDateTime(LocalDateTime ldt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String formattedDateTime = ldt.format(formatter);
		return formattedDateTime;
	}
	
	
	public String toString() {
		return String.format("| %-10s | %-16s | %-14s |\n", c.getCodice(), toStringDateTime(dataOraInizio), toStringDateTime(dataOraFine));
	}
}
