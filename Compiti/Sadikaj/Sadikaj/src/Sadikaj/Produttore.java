package Sadikaj;

public class Produttore implements Runnable{
	private int n;
	private Buffer b;
	
	/**
	 * Costruttore di ogni Produttore
	 * @param n --> Numero di prodotti da creare
	 * @param b --> Buffer dichiarato nel Test
	 */
	public Produttore(int n, Buffer b) {
		this.n = n;
		this.b = b;
	}
	/**
	 * Costruttore di copia
	 * @param p --> Produttore del quale si vogliono copiare gli attributi
	 */
	public Produttore(Produttore p) {
		this.n = p.n;
		this.b = p.b;
	}

	@Override
	public void run() {
		for (int i = 0; i < n; i++) {
			b.put();
		}
	}
}
