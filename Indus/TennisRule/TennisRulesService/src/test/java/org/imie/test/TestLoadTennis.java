/**
 * 
 */
package org.imie.test;

import static org.junit.Assert.assertEquals;

import org.imie.Jeux;
import org.junit.Before;
import org.junit.Test;

/**
 * @author imie
 *
 */
public class TestLoadTennis {

	Jeux jeux;


	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		jeux = new Jeux();
	}

	@Test
	public void testIntegrationReset() {
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur2();
		jeux.marquerJoueur2();
		jeux.save();
		jeux.reset();
		assertEquals("0-0", jeux.getScore());
	}
	
	@Test
	public void testIntegrationSave() {
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur2();
		jeux.marquerJoueur2();
		jeux.save();
		jeux.reset();
		jeux.load();	
		assertEquals("30-30", jeux.getScore());
	}
	
	@Test
	public void testUnitaireReset() {
		

		
		MockedSerialiser mockedSerialiser= new MockedSerialiser();
		jeux.setSerialiser(mockedSerialiser);
		
		
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur2();
		jeux.marquerJoueur2();

		jeux.save();
		jeux.reset();
		assertEquals("0-0", jeux.getScore());	
	}
	
	@Test
	public void testUnitaireSave() {
		Jeux savedJeux = new Jeux();
		savedJeux.marquerJoueur1();
		savedJeux.marquerJoueur1();
		savedJeux.marquerJoueur2();
		savedJeux.marquerJoueur2();
		
		MockedSerialiser mockedSerialiser= new MockedSerialiser();
		mockedSerialiser.setJeux(savedJeux);
		jeux.setSerialiser(mockedSerialiser);
		
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur2();
		jeux.marquerJoueur2();
		jeux.save();
		jeux.reset();
		jeux.load();
		assertEquals("30-30", jeux.getScore());
	}
	
	@Test
	public void testUnitaireSaveBis() {

		Jeux savedJeux = new Jeux();
		savedJeux.marquerJoueur1();
		savedJeux.marquerJoueur1();
		savedJeux.marquerJoueur2();
		
		MockedSerialiser mockedSerialiser= new MockedSerialiser();
		mockedSerialiser.setJeux(savedJeux);
		jeux.setSerialiser(mockedSerialiser);
		
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur2();
		jeux.save();
		jeux.reset();
		jeux.load();
		assertEquals("30-15", jeux.getScore());
	}


}
