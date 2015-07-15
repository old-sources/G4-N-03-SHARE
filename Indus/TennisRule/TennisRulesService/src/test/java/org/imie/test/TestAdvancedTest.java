package org.imie.test;

import static org.junit.Assert.*;

import org.imie.Jeux;
import org.junit.Before;
import org.junit.Test;

public class TestAdvancedTest {


	private Jeux jeux;
	
	
	@Before
	public void setUp() throws Exception {
		jeux=new Jeux();

		
	}
	

	@Test
	public void gagneJ1() {
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		assertEquals("victoire J1", jeux.getScore()); 
	}
	
	@Test
	public void gagneJ2ApresEgalite() {
		initToEgalite();
		jeux.marquerJoueur2();
		jeux.marquerJoueur1();
		jeux.marquerJoueur2();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur2();
		jeux.marquerJoueur2();
		jeux.marquerJoueur2();
		assertEquals("victoire J2", jeux.getScore()); 
	}
	
	@Test
	public void vistoireJ1Bloquee() {
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		try{
			jeux.marquerJoueur2();
			fail("joueur 2 à réussi à marquer alors que joueur 1 avait dèjà gagné");
		}catch(Exception e){
			//le metier à refusé de aire jouer J1 : normal
		}
		
		
		assertEquals("victoire J1", jeux.getScore()); 
	}
	
	
	
	private void initToEgalite() {
		jeux.marquerJoueur2();
		jeux.marquerJoueur2();
		jeux.marquerJoueur2();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
	}

}
