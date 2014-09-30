package org.imie.service;

import java.util.List;

import org.imie.DAO.IPersonneDAO;
import org.imie.DTO.PersonneDTO;
import org.imie.factory.IFactory;

public class EcoleService implements IEcoleService {
	
	IFactory fact;
	


	public EcoleService() {
		super();
		// TODO Auto-generated constructor stub
	}



	public EcoleService(IFactory fact) {
		super();
		this.fact = fact;
	}



	/* (non-Javadoc)
	 * @see org.imie.service.IPersonneService#getAllPersonne()
	 */
	@Override
	public List<PersonneDTO> getAllPersonne(){
		//IPersonneDAO dao = PersonneDAO.getInstance();
		IPersonneDAO dao = fact.createPersonneDAO();
		List<PersonneDTO> retour= dao.findAll();
		return retour;
	}
	
	

}
