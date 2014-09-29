/**
 * 
 */
package org.imie.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author imie
 *
 */
public class ADAO {
	protected Connection openConnection() throws SQLException {
		Connection connection;
		connection = DriverManager
				.getConnection("jdbc:postgresql://localhost:5432/imie",
						"postgres", "postgres");
		return connection;
	}

	protected void closeJDBC(Connection connection, Statement statement,
			ResultSet resultSet) {
		try {
			if (resultSet != null && !resultSet.isClosed()) {
				resultSet.close();
			}
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
