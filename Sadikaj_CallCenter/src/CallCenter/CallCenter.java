package CallCenter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

	public void stampaClienti() {
		if (clienti.isEmpty()) {
			System.out.println("Nessun cliente presente nel sistema.");
			return;
		}

		Cliente temp[] = clienti.values().toArray(new Cliente[0]);

		System.out.println("------------------------CLIENTI-------------------------");
		System.out.printf("| %-10s | %-26s | %-10s |\n", "Codice", "Nome e cognome", "Telefono");
		for (Cliente c : temp) {
			System.out.println(c.toString());
		}
		System.out.println("--------------------------------------------------------");
	}

	public boolean stampaChiamate(String codice) {
		if (!telefonate.containsKey(codice))
			return false;

		List<Telefonata> telefonate = this.telefonate.get(codice);

		telefonate.sort(Comparator.comparing(Telefonata::getDataOraInizio));

		System.out.println("Chiamate ricevute dall'operatore " + codice);
		System.out.println("----------------------------------------------------------");
		System.out.printf("");
		for (Telefonata t : telefonate) {
			System.out.println(t.toString());
		}
		System.out.println("----------------------------------------------------------");

		return true;
	}

	public Operatore ricercaOperatore(String codice) {
		return operatori.get(codice);
	}

	public Cliente ricercaCliente(String numero) {
		Cliente temp[] = clienti.values().toArray(new Cliente[0]);

		for (Cliente c : temp) {
			if (c.getTelefono() == numero)
				return c;
		}

		return null;
	}
	
	private String estraiCliente() {
		Cliente temp[] = clienti.values().toArray(new Cliente[0]);

		int i = 1;
		for (Cliente c : temp) {
			System.out.println((i++) + "°) " + c.getNome() + " " + c.getCognome() + " - " + c.getTelefono());
		}
		
		return temp[in.inputInt("\nQuale si vuole usare?", 1, i - 1) - 1].getTelefono();
	}

	public void riceviChiamata() {
		int val = 1;

		System.out.println("Ciao mondo" + (val++));
		if (operatori.isEmpty()) {
			System.out.println("Nessun operatore disponibile al momento.");
			return;
		}

		System.out.println("--------MENU--------");
		System.out.println("| 1) Nuovo cliente |");
		System.out.println("| 2) Presente      |");
		System.out.println("--------------------");

		int scelta = in.inputInt("Che si vuole fare?");
		String numero;

		if (scelta == 1) {
			System.out.println("ATTENZIONE: Il cliente che si inserirà verrà aggiunto nel sistema!!");
			Cliente c = infoCliente();
			numero = c.getTelefono();
			clienti.put(c.getCodice(), c);
		} else {
			if (clienti.isEmpty()) {
				System.out.println("Nessun clientepresente nel sistema.");
				return;
			}
			
			numero = estraiCliente();
			System.out.println("Numero: " + numero);
		}
		System.out.println("Ciao mondo" + (val++));
		if (!clienti.containsKey(ricercaCliente(numero).getCodice())) {
			System.out.println("Nessun cliente presente nel sistema col numero di telefono inserito.");
			return;
		}

		System.out.println("Ciao mondo" + (val++));

		Operatore[] operatoriPresenti = operatori.values().toArray(new Operatore[0]);
		Random random = new Random();
		Cliente c = clienti.get(ricercaCliente(numero).getCodice());
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

	public void salva() {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("clienti.bin"));
			oos.writeObject(clienti);
			oos.close();
			System.out.println("Dati salvati nel file clienti.bin");

			oos = new ObjectOutputStream(new FileOutputStream("operatori.bin"));
			oos.writeObject(operatori);
			oos.close();
			System.out.println("Dati salvati nel file operatori.bin");

			oos = new ObjectOutputStream(new FileOutputStream("telefonate.bin"));
			oos.writeObject(telefonate);
			oos.close();
			System.out.println("Dati salvati nel file telefonate.bin");
		} catch (IOException e) {
			System.out.println("Errore nella scrittura del file");
		}
	}

	public void carica() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("clienti.bin"));
			clienti = (Map<String, Cliente>) ois.readObject();
			ois.close();
			System.out.println("Lettura dati dal file clienti.bin");
			ois = new ObjectInputStream(new FileInputStream("operatori.bin"));

			operatori = (Map<String, Operatore>) ois.readObject();
			ois.close();
			System.out.println("Lettura dati dal file operatori.bin");
			ois = new ObjectInputStream(new FileInputStream("telefonate.bin"));

			telefonate = (Map<String, List<Telefonata>>) ois.readObject();
			ois.close();
			System.out.println("Lettura dati dal file telefonate.bin");

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Errore nella lettura da file");
		}
	}
}