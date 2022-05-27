package Sadikaj;

import java.util.Random;


/**
 * 
 * @author Albi Sadikaj
 * 
 * Classe Buffer in cui vengono creati e serviti i prodotti ai consumatori
 *
 */

public class Buffer {
	private int prodotto;
	private int nProdotti;
	private boolean presente;
	Random rand = new Random();
	
	public Buffer() {
		nProdotti = 0;
	}
	
	/**
	 * 
	 * @return il numero di prodotti fatti e serviti fino al momento in cui si richiama il metodo
	 */
	public int getNProdotti() {
		return nProdotti;
	}
	
	/**
	 * Metodo che si occupa del servire i consumatori e di assicurarsi che il primo consumatore prenda i prodotti da 1 a 5 e il secondo da 6 a 10
	 */
	public synchronized void get() {
		while (!presente) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		while (Thread.currentThread().getName().compareToIgnoreCase("C1") == 0 && prodotto > 5) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		while (Thread.currentThread().getName().compareToIgnoreCase("C2") == 0 && prodotto < 6) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		presente = false;
		System.out.println(Thread.currentThread().getName() + " - consuma: " + prodotto);
		System.out.println();
		notifyAll();
		nProdotti++;
	}
	
	/**
	 * Metodo che si occupa della produzione dei prodotti che il produttore richiama n volte
	 */
	public synchronized void put() {
		while (presente) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		this.prodotto = rand.nextInt(10) + 1;
		presente = true;
		System.out.println("Prodotto: " + prodotto);
		notifyAll();
	}

}
