package org.imie.launcher;

import org.imie.factory.IFactory;
import org.imie.ihm.IIHM;

public class Launcher {

	public static void main(String[] args) {
		IFactory fact = new Factory1();
		IIHM ihm = fact.createIHM();
		ihm.start();

	}

}
