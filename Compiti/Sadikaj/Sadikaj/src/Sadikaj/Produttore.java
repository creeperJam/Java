package Sadikaj;

public class Produttore implements Runnable{
	private int n;
	private Buffer b;
	
	public Produttore(int n, Buffer b) {
		this.n = n;
		this.b = b;
	}
	
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
