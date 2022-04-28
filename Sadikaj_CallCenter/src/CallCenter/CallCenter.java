package CallCenter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CallCenter {

	private Map<String, Cliente> clienti;
	private Map<String, Operatore> operatori;
	private Map<String, List<Telefonata>> telefonate;

	public CallCenter() {
		clienti = new HashMap<>();
		operatori = new HashMap<>();
		telefonate = new HashMap<>();
	}

	public void inserisciOperatore(Operatore o) {
		operatori.put(o.getCodice(), o);
	}

	public boolean eliminaOperatore(String c) {
//		boolean temp = operatori.remove(c) != null;
//		return temp;
		return operatori.remove(c) != null;
	}

	public void inserisciOperatore(Cliente c) {
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

	public void riceviChiamata() {

		if (clienti.isEmpty()) {
			System.out.println(
					"Nessun cliente presente nel sistema. Inserire almeno un cliente nel sistema e riprovare.");
			return;
		}

		if (operatori.isEmpty()) {
			System.out.println("Nessun operatore disponibile al momento.");
			return;
		}

		Random random = new Random();
		Input in = new Input();
		List<Cliente> clientiPresenti = new ArrayList<>(clienti.values());

		System.out.println("---------Clienti---------");
		for (Cliente c : clientiPresenti) {
			System.out.printf("| %-4d) %-12s %-12s |", clientiPresenti.indexOf(c), c.getNome(), c.getCognome());
		}
		System.out.println("-------------------------");

		int index = in.inputInt("Cliente scelto:", 1, clienti.size()) - 1;

		Operatore[] operatoriPresenti = operatori.values().toArray(new Operatore[0]);
		Operatore o;
		do {
			o = operatoriPresenti[random.nextInt(operatoriPresenti.length)];
		} while (o == null);

		Cliente c = clientiPresenti.get(index);
		System.out.printf("Chiamata in arrivo da: %s %s - codice: %s\n", c.getNome(), c.getCognome(), c.getCodice());
		System.out.printf("Operatore in risposta: %s %s - codice: %s\n", o.getNome(), o.getCognome(), o.getCodice());

		LocalDateTime dataOraInizio = LocalDateTime.now();
		LocalDateTime dataOraFine;

		System.out.println("Chiamata iniziata");
		while (in.inputInt("Premere 0 per terminare:") != 0);
		System.out.println("Chiamata terminata");
		dataOraFine = LocalDateTime.now();

		Telefonata t = new Telefonata(o, dataOraInizio, dataOraFine, c.getTelefono());
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
