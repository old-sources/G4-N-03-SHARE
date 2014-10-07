package org.imie.launcher;

import org.imie.DAO.IPersonneDAO;
import org.imie.DAO.PersonneDAO;
import org.imie.factory.IFactory;
import org.imie.ihm.IIHM;
import org.imie.service.EcoleService;
import org.imie.service.IEcoleService;
import org.imie.tp3.IHMTP3;

public class Factory1 implements IFactory {

	@Override
	public IEcoleService createEcoleService() {
		return new EcoleService(this);
	}

	@Override
	public IIHM createIHM() {
		// TODO Auto-generated method stub
		return new IHMTP3(this);
	}

	@Override
	public IPersonneDAO createPersonneDAO() {
		// TODO Auto-generated method stub
		return new PersonneDAO(this);
	}

}
