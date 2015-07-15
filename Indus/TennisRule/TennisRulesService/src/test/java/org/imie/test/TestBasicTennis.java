package org.imie.test;

import static org.junit.Assert.assertEquals;

import org.imie.Jeux;
import org.junit.Before;
import org.junit.Test;

public class TestBasicTennis {

	private Jeux jeux;
	
	@Before
	public void setUp() throws Exception {
		jeux=new Jeux();
	}
	
	@Test
	public void score_0_0() {
		assertEquals("0-0", jeux.getScore()); 
	}
	
	@Test
	public void score_15_0() {
		jeux.marquerJoueur1();
		assertEquals("15-0", jeux.getScore()); 
	}
	
	@Test
	public void score_15_15() {
		jeux.marquerJoueur2();
		jeux.marquerJoueur1();
		assertEquals("15-15", jeux.getScore()); 
	}
	
	@Test
	public void score_30_15() {
		jeux.marquerJoueur2();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		assertEquals("30-15", jeux.getScore()); 
	}
	
	@Test
	public void score_30_30() {
		jeux.marquerJoueur2();
		jeux.marquerJoueur2();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		assertEquals("30-30", jeux.getScore()); 
	}
	
	@Test
	public void score_40_0() {
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		assertEquals("40-0", jeux.getScore()); 
	}
	
	@Test
	public void score_egalite() {
		initToEgalite();
		assertEquals("egalite", jeux.getScore()); 
	}


	
	@Test
	public void score_avantageJ1() {
		initToEgalite();
		jeux.marquerJoueur1();
		assertEquals("avantage j1", jeux.getScore()); 
	}
	
	@Test
	public void score_avantageJ2() {
		initToEgalite();
		jeux.marquerJoueur2();
		assertEquals("avantage j2", jeux.getScore()); 
	}
	
	@Test
	public void score_retourEgalite() {
		initToEgalite();
		jeux.marquerJoueur2();
		jeux.marquerJoueur1();
		assertEquals("egalite", jeux.getScore()); 
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
