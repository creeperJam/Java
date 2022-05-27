package Sadikaj;


/**
 * 
 * @author Albi Sadikaj
 * 		   Fila A
 *         27/05/2022
 */

public class Test {
	public static void main(String[] args) {
		Buffer b = new Buffer();
		int n = 10;
		
		Produttore prod = new Produttore(n, b);
		Consumatore cons1 = new Consumatore(n, b);
		Consumatore cons2 = new Consumatore(n, b);
		
		Thread p = new Thread(new Produttore(prod));
		Thread c1 = new Thread(new Consumatore(cons1), "C1");
		Thread c2 = new Thread(new Consumatore(cons2), "C2");
		
		
		p.start();
		c1.start();
		c2.start();
	}
}
