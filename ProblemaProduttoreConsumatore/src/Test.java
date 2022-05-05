package ProblemaProduttoreConsumatore.src;

public class Test {

	public static void main(String[] args) {
		Risorsa r = new Risorsa();
		Produttore p = new Produttore(r);
		Consumatore c = new Consumatore(r);
		Thread t_prod = new Thread(p);
		Thread t_cons = new Thread(c);
		t_prod.start();
		t_cons.start();
		
	}

}
