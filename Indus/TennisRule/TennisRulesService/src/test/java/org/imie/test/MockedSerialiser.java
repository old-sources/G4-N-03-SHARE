package org.imie.test;

import org.imie.ISerialiser;
import org.imie.Jeux;

public class MockedSerialiser implements ISerialiser {

	Jeux jeux =null;
	
	public void setJeux(Jeux jeux) {
		this.jeux = jeux;
	}

	@Override
	public void persist(Jeux jeux) {
		// TODO Auto-generated method stub

	}

	@Override
	public Jeux read() {
//		Jeux jeux = new Jeux();
//		jeux.marquerJoueur1();
//		jeux.marquerJoueur1();
//		jeux.marquerJoueur2();
//		jeux.marquerJoueur2();
		return jeux;
	}

}
