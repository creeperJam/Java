package Sadikaj;

import java.util.Random;

public class Buffer {
	private int prodotto;
	private int nProdotti;
	private boolean presente;
	Random rand = new Random();

	public Buffer() {
		nProdotti = 0;
	}
	
	public int getNProdotti() {
		return nProdotti;
	}

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
