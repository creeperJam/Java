package ProblemaProduttoreConsumatore.src;

public class Risorsa {
	private int val;
	private boolean disponibile;
		
	Risorsa()  {
		val = 0;
		disponibile = false;
	}
	
	public synchronized int consuma() {
		while ( disponibile == false ) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		disponibile = false;
		System.out.println("Consumato valore " + val);
		notifyAll();
		return val;
	}
	
	public synchronized void produci(int val) {
		while ( disponibile == true ) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		this.val = val;
		disponibile = true;
		System.out.println("Prodotto valore " + val);
		notify();
	}
	
}
