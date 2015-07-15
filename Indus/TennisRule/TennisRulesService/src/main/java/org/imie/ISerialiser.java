package org.imie;

import org.imie.Jeux;

public interface ISerialiser {

	public abstract void persist(Jeux jeux);

	public abstract Jeux read();

}