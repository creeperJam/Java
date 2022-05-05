public class Consumatore implements Runnable {
	private Risorsa r;
	
	Consumatore(Risorsa r) {
		this.r = r;
	}
	
	@Override
	public void run() {
		
		for (int i=1; i<=5; i++ ) {
			int val = r.consuma();
		}
	}
	
}
