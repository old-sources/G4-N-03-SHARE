package org.imie.factory;

import org.imie.DAO.IPersonneDAO;
import org.imie.ihm.IIHM;
import org.imie.service.IEcoleService;


public interface IFactory {

	public IEcoleService createEcoleService();

	public IIHM createIHM();

	public IPersonneDAO createPersonneDAO();

}
