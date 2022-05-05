public class Test {

	public static void main(String[] args) {
		Risorsa r = new Risorsa();
		Produttore p = new Produttore(r);
		Consumatore c1 = new Consumatore(r);
		Consumatore c2 = new Consumatore(r);
		Thread t_prod = new Thread(p);
		Thread t_cons1 = new Thread(c1);
		Thread t_cons2 = new Thread(c2);
		
		t_cons1.setName("Consumatore 1");
		t_cons2.setName("Consumatore 2");
		
		t_prod.start();
		t_cons1.start();
		t_cons2.start();
		
		
		// Con molteplici consumatori comincia una gara 
		// a chi si aggiudica per primo il prodotto, lasciando
		// gli altri ad attendere il prossimo.
	}

}
