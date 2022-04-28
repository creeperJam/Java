package CallCenter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CallCenter {

	private Map<String, Cliente> clienti;
	private Map<String, Operatore> operatori;
	private Map<String, List<Telefonata>> telefonate;
	Input in = new Input();

	public CallCenter() {
		clienti = new HashMap<>();
		operatori = new HashMap<>();
		telefonate = new HashMap<>();
	}

	private Operatore infoOp() {
		return new Operatore(in.inputString("Codice operatore:"), in.inputString("Nome operatore:"),
				in.inputString("Cognome operatore:"));
	}

	private Cliente infoCliente() {
		return new Cliente(in.inputString("Codice cliente:"), in.inputString("Nome cliente:"),
				in.inputString("Cognome cliente:"), in.inputPhoneNumber(), null);
	}

	public void inserisciOperatore() {
		Operatore o = infoOp();
		operatori.put(o.getCodice(), o);
	}

	public boolean eliminaOperatore(String c) {
//		boolean temp = operatori.remove(c) != null;
//		return temp;
		return operatori.remove(c) != null;
	}

	public void inserisciCliente() {
		Cliente c = infoCliente();
		clienti.put(c.getCodice(), c);
	}

	public boolean eliminaCliente(String c) {
//		boolean temp = clienti.remove(c) != null;
//		return temp;
		return clienti.remove(c) != null;
	}

	public Map<String, Cliente> getClienti() {
		return clienti;
	}

	public Map<String, Operatore> getOperatori() {
		return operatori;
	}

	public Map<String, List<Telefonata>> getTelefonate() {
		return telefonate;
	}

	public boolean stampaChiamate(String codice) {
		if (!telefonate.containsKey(codice))
			return false;

		List<Telefonata> telefonate = this.telefonate.get(codice);
		
		telefonate.sort(Comparator.comparing(Telefonata::getDataOraInizio));

		System.out.println("Chiamate ricevute dall'operatore " + codice);
		System.out.println("----------------------------------------------------");
		System.out.printf("");
		for (Telefonata t : telefonate) {
			System.out.println(t.toString());
		}

		return true;
	}

	public Operatore ricercaOperatore(String codice) {
		if (operatori.containsKey(codice))
			return operatori.get(codice);
		return null;
	}

	public void riceviChiamata(String numero) {
		if (operatori.isEmpty()) {
			System.out.println("Nessun operatore disponibile al momento.");
			return;
		}
		if (clienti.isEmpty()) {
			System.out.println("Nessun cliente presente nel sistema. Inserire almeno un cliente nel sistema.");
			return;
		}
		if (!clienti.containsKey(numero)) {
			System.out.println("Nessun cliente presente nel sistema col numero di telefono inserito.");
			return;
		}

		Operatore[] operatoriPresenti = operatori.values().toArray(new Operatore[0]);
		Random random = new Random();
		Cliente c = clienti.get(numero);
		Operatore o = operatoriPresenti[random.nextInt(operatoriPresenti.length)];

		System.out.printf("Chiamata in arrivo da: %s %s - codice: %s\n", c.getNome(), c.getCognome(), c.getCodice());
		System.out.printf("Operatore in risposta: %s %s - codice: %s\n", o.getNome(), o.getCognome(), o.getCodice());

		LocalDateTime dataOraInizio = LocalDateTime.now();
		LocalDateTime dataOraFine;

		System.out.println("Chiamata iniziata");
		while (in.inputInt("Premere 0 per terminare:") != 0)
			;
		System.out.println("Chiamata terminata");
		dataOraFine = LocalDateTime.now();

		Telefonata t = new Telefonata(o, dataOraInizio, dataOraFine, c);
		List<Telefonata> temp = new ArrayList<>();

		temp.add(t);
		if (telefonate.containsKey(o.getCodice())) {
			temp.addAll(telefonate.get(o.getCodice()));
		}
		telefonate.put(o.getCodice(), temp);

		c.setUltima(t);
		clienti.put(c.getCodice(), c);

	}

}