package org.imie.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.imie.DTO.PersonneDTO;
import org.imie.DTO.PromotionDTO;
import org.imie.exception.ImieException;

public class PersonneDAO extends ADAO implements IPersonneDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IDAO#insert(java.lang.Object)
	 */
	@Override
	public PersonneDTO insert(PersonneDTO personneToInsert)
			throws ImieException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		PersonneDTO retour = null;
		try {
			connection = openConnection();

			List<ParamJDBC> paramsList = new ArrayList<ParamJDBC>();
			if (personneToInsert.getNom() != null) {
				paramsList.add(new ParamJDBC("nom", personneToInsert.getNom()));
			}
			if (personneToInsert.getPrenom() != null) {
				paramsList.add(new ParamJDBC("prenom", personneToInsert
						.getPrenom()));
			}
			if (personneToInsert.getDateNaiss() != null) {
				paramsList.add(new ParamJDBC("datenaiss", new Date(
						personneToInsert.getDateNaiss().getTime())));
			}

			String fields = "";
			String params = "";
			Boolean firstField = true;
			for (ParamJDBC paramJDBC : paramsList) {
				fields = fields.concat(firstField ? "" : ",").concat(
						paramJDBC.getNom());
				params = params.concat(firstField ? "" : ",").concat("?");
				firstField = false;
			}

			String query = "INSERT into personne ("
					.concat(fields)
					.concat(") values (")
					.concat(params)
					.concat(") returning id, nom, prenom, datenaiss,promotion_id");

			statement = connection.prepareStatement(query);

			Integer paraNumber = 1;
			for (ParamJDBC paramJDBC : paramsList) {
				statement.setObject(paraNumber++, paramJDBC.getValue());
			}

			resultSet = statement.executeQuery();
			resultSet.next();
			retour = buildDTOFromResultset(resultSet);

		} catch (SQLException e) {
			throw new ImieException("impossible d'insérer une personne", e);
		} finally {
			closeJDBC(connection, statement, resultSet);
		}
		return retour;
	}

	@Override
	public PersonneDTO update(PersonneDTO personneToUpdate,
			Connection connection) throws ImieException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		PersonneDTO retour = null;
		Boolean slave = false;
		if (connection != null) {
			slave = true;
		}

		try {
			if (personneToUpdate.getId()==29){
				throw new ImieException("pour de rire");
			}
			if (!slave) {
				connection = openConnection();
			}
			String query = "UPDATE personne set nom=?, prenom=?, datenaiss=?, promotion_id=? WHERE id =? returning id,nom,prenom,datenaiss,promotion_id";

			statement = connection.prepareStatement(query);
			statement.setString(1, personneToUpdate.getNom());
			statement.setString(2, personneToUpdate.getPrenom());
			statement.setDate(3, new Date(personneToUpdate.getDateNaiss()
					.getTime()));
			PromotionDTO promotionDTO = personneToUpdate.getPromotionDTO();

			statement.setObject(4,
					promotionDTO == null ? null : promotionDTO.getId());
			statement.setInt(5, personneToUpdate.getId());

			resultSet = statement.executeQuery();
			resultSet.next();
			retour = buildDTOFromResultset(resultSet);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeJDBC(slave ? null : connection, statement, resultSet);
		}
		return retour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IDAO#update(java.lang.Object)
	 */
	@Override
	public PersonneDTO update(PersonneDTO personneToUpdate) throws ImieException {
		return update(personneToUpdate, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IDAO#delete(java.lang.Object)
	 */
	@Override
	public Integer delete(PersonneDTO personneToDelete) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Integer retour = null;
		try {
			connection = openConnection();
			String query = "DELETE from personne WHERE id=?";
			statement = connection.prepareStatement(query);
			statement.setInt(1, personneToDelete.getId());

			retour = statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeJDBC(connection, statement, resultSet);
		}
		return retour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IDAO#findAll()
	 */
	@Override
	public List<PersonneDTO> findAll() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<PersonneDTO> retour = new ArrayList<PersonneDTO>();
		try {
			connection = openConnection();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT id,nom,prenom,datenaiss,promotion_id FROM personne");

			while (resultSet.next()) {
				PersonneDTO personneDTO = buildDTOFromResultset(resultSet);
				retour.add(personneDTO);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeJDBC(connection, statement, resultSet);
		}
		return retour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.DAO.IDAO#findByDTO(java.lang.Object)
	 */
	@Override
	public List<PersonneDTO> findByDTO(PersonneDTO personneDTOSearched) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<PersonneDTO> retour = new ArrayList<PersonneDTO>();
		try {
			connection = openConnection();

			Boolean firstRestriction = true;
			String query = "SELECT id,nom,prenom,datenaiss,promotion_id FROM personne ";
			if (personneDTOSearched.getNom() != null) {
				query = query.concat(firstRestriction ? "WHERE " : "AND ")
						.concat("nom ilike ? ");
				firstRestriction = false;
			}
			if (personneDTOSearched.getPrenom() != null) {
				query = query.concat(firstRestriction ? "WHERE " : "AND ")
						.concat("prenom ilike ? ");
				firstRestriction = false;
			}
			if (personneDTOSearched.getDateNaiss() != null) {
				query = query.concat(firstRestriction ? "WHERE " : "AND ")
						.concat("datenaiss = ? ");
				firstRestriction = false;
			}
			if (personneDTOSearched.getPromotionDTO() != null) {
				query = query.concat(firstRestriction ? "WHERE " : "AND ")
						.concat("promotion_id = ? ");
				firstRestriction = false;
			}

			statement = connection.prepareStatement(query);

			Integer paramNumber = 1;
			if (personneDTOSearched.getNom() != null) {
				statement.setString(paramNumber++,
						String.format("%%%s%%", personneDTOSearched.getNom()));
			}
			if (personneDTOSearched.getPrenom() != null) {
				statement.setString(paramNumber++, String.format("%%%s%%",
						personneDTOSearched.getPrenom()));
			}
			if (personneDTOSearched.getDateNaiss() != null) {
				statement.setDate(paramNumber++, new Date(personneDTOSearched
						.getDateNaiss().getTime()));
			}

			if (personneDTOSearched.getPromotionDTO() != null) {
				statement.setInt(paramNumber++, personneDTOSearched
						.getPromotionDTO().getId());
			}

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				PersonneDTO personneDTO = buildDTOFromResultset(resultSet);
				retour.add(personneDTO);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeJDBC(connection, statement, resultSet);
		}
		return retour;
	}

	private PersonneDTO buildDTOFromResultset(ResultSet resultSet)
			throws SQLException {
		PersonneDTO retour;
		retour = new PersonneDTO();
		retour.setId(resultSet.getInt("id"));
		retour.setNom(resultSet.getString("nom"));
		retour.setPrenom(resultSet.getString("prenom"));
		retour.setDateNaiss(resultSet.getDate("datenaiss"));
		Integer promotionId = resultSet.getInt("promotion_id");
		if (!resultSet.wasNull()) {
			PromotionDTO promotionDTO = new PromotionDTO();
			promotionDTO.setId(promotionId);
			PromotionDAO promotionDAO = new PromotionDAO();
			promotionDTO = promotionDAO.findByDTO(promotionDTO).get(0);
			retour.setPromotionDTO(promotionDTO);
		}
		return retour;
	}

}
