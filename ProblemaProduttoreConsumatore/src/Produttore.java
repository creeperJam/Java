public class Produttore implements Runnable {
	private Risorsa r;
	
	public Produttore(Risorsa r) {
		this.r = r;
	}

	@Override
	public void run() {
		
		for (int i=1; i<=5; i++) {
			r.produci(i);
		}
	}
	
	
	
}
