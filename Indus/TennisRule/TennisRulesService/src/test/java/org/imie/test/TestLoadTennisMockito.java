package org.imie.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.imie.ISerialiser;
import org.imie.Jeux;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestLoadTennisMockito {

	Jeux jeux;
	
	@Mock
	ISerialiser serialiser;
	
	@Before
	public void setUp() throws Exception {
		jeux = new Jeux();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		Jeux savedJeux = new Jeux();
		savedJeux.marquerJoueur1();
		savedJeux.marquerJoueur1();
		savedJeux.marquerJoueur2();
		savedJeux.marquerJoueur2();
		
		//MockedSerialiser mockedSerialiser= new MockedSerialiser();
		//mockedSerialiser.setJeux(savedJeux);
		when(serialiser.read()).thenReturn(savedJeux);
		jeux.setSerialiser(serialiser);
		
		jeux.marquerJoueur1();
		jeux.marquerJoueur1();
		jeux.marquerJoueur2();
		jeux.marquerJoueur2();
		jeux.save();
		jeux.reset();
		jeux.load();
		assertEquals("30-30", jeux.getScore());
	}

}
