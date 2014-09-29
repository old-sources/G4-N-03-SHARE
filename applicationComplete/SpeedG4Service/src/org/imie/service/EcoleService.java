package org.imie.service;

import java.util.List;

import org.imie.DAO.IPersonneDAO;
import org.imie.DAO.PersonneDAO;
import org.imie.DTO.PersonneDTO;

public class EcoleService implements IEcoleService {
	
	/* (non-Javadoc)
	 * @see org.imie.service.IPersonneService#getAllPersonne()
	 */
	@Override
	public List<PersonneDTO> getAllPersonne(){
		IPersonneDAO dao = PersonneDAO.getInstance();
		List<PersonneDTO> retour= dao.findAll();
		return retour;
	}
	
	

}
