package org.imie.DAO;

import java.sql.Connection;

import org.imie.DTO.PersonneDTO;
import org.imie.exception.ImieException;

public interface IPersonneDAO extends IDAO<PersonneDTO> {

	PersonneDTO update(PersonneDTO personneToUpdate, Connection connection) throws ImieException;



}