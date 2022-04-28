package CallCenter;

public class Test {

	public static void main(String[] args) {
		Input in = new Input();
		CallCenter cc = new CallCenter();
		int scelta;

		do {
			System.out.println("---------------------MENU---------------------");
			System.out.println("| 1) Inserisci operatore                     |");
			System.out.println("| 2) Elimina operatore                       |");
			System.out.println("| 3) Inserisci cliente                       |");
			System.out.println("| 4) Elimina cliente                         |");
			System.out.println("| 5) Ricevi chiamata                         |");
			System.out.println("| 6) Mostra chiamate con codice operatore    |");
			System.out.println("| 7) Mostra clienti presenti                 |");
			System.out.println("| 8) Salva dati su file                      |");
			System.out.println("| 9) Carica dati da file                     |");
			System.out.println("| 0) Esci                                    |");
			System.out.println("----------------------------------------------");

			scelta = in.inputInt("Scelta:", 0, 9);
			System.out.println("\n");

			switch (scelta) {
			case 1: {
				cc.inserisciOperatore();
				break;
			}

			case 2: {
				if (cc.eliminaOperatore(in.inputString("Codice operatore da rimuovere:"))) {
					System.out.println("Operatore rimosso con successo.");
				} else {
					System.out.println("Errore durante rimozione. (operatore non presente)");
				}
				break;
			}

			case 3: {
				cc.inserisciCliente();
				break;
			}

			case 4: {
				if (cc.eliminaCliente(in.inputString("Codice cliente da rimuovere:"))) {
					System.out.println("Cliente rimosso con successo.");
				} else {
					System.out.println("Errore durante rimozione. (cliente non presente)");
				}
				break;
			}
			
			case 5: {
				cc.riceviChiamata();
				break;
			}

			case 6: {
				cc.stampaChiamate(in.inputString("Codice dell'operatore del quale si desiderano le chiamate:"));
				break;
			}
			
			case 7: {
				cc.stampaClienti();
				break;
			}
			
			case 8: {
				cc.salva();
				break;
			}
			
			case 9: {
				cc.carica();
				break;
			}
			}
		} while (scelta != 0);
	}

}
