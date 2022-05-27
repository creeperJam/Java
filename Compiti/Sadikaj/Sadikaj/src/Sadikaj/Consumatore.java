package Sadikaj;

public class Consumatore implements Runnable {
	private int n;
	private Buffer b;
	
	/**
	 * Costruttore di ogni Consumatore
	 * @param n --> Numero di prodotti che si creeranno
	 * @param b --> Buffer che si dichiara nel Test
	 */
	public Consumatore(int n, Buffer b) {
		this.b = b;
		this.n = n;
	}
	
	/**
	 * Costruttore di copia
	 * @param c --> Consumatore del quale si vogliono copiare gli attributi
	 */
	public Consumatore(Consumatore c) {
		this.b = c.b;
		this.n = c.n;
	}
	
	@Override
	public void run() {
		while (b.getNProdotti() < n) {
			b.get();
		}
	}

}
