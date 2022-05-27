package Sadikaj;

public class Consumatore implements Runnable {
	private int n;
	private Buffer b;

	public Consumatore(int n, Buffer b) {
		this.b = b;
		this.n = n;
	}

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
