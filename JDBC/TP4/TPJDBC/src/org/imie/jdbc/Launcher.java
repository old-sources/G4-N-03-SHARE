package org.imie.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Launcher {

	public static void main(String[] args) {
		Boolean endAppli = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		while (!endAppli) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("");
			System.out.println("-----------------");
			System.out.println("1 - TP1 - rechercher toutes les personnes");
			System.out
					.println("2 - TP2 - rechercher les personnes par nom et prenom");
			System.out.println("3 - TP4 - insérer une personne");
			System.out.println("X - END");
			String saisie = scanner.nextLine();
			if (saisie.compareTo("X") == 0) {
				endAppli = true;
				continue;
			}

			String nom;
			String prenom;
			Date dateNaiss;

			try {
				Integer choix = Integer.valueOf(saisie);
				switch (choix) {
				case 1:
					findAll(dateFormat);
					break;
				case 2:
					System.out.println("saisir nom:");
					nom = scanner.nextLine();
					nom = checkNullInput(scanner, nom);
					System.out.println("saisir prenom");
					prenom = scanner.nextLine();
					prenom = checkNullInput(scanner, prenom);
					findByNomPrenom(nom, prenom, dateFormat);
					break;
				case 3:
					System.out.println("saisir nom:");
					nom = scanner.nextLine();
					nom = checkNullInput(scanner, nom);
					System.out.println("saisir prenom");
					prenom = scanner.nextLine();
					prenom = checkNullInput(scanner, prenom);
					String dateNaissString = null;
					dateNaiss = null;
					do {
						System.out.println("saisir date Naissance");
						dateNaissString = scanner.nextLine();
						if (dateNaissString.compareTo("") == 0) {
							dateNaissString = null;
						}
						if (dateNaissString != null) {
							try {
								dateNaiss = dateFormat.parse(dateNaissString);
							} catch (ParseException e) {
								System.out
										.println("format de date : dd/mm/yyyy");
							}
						}
					} while (dateNaissString != null && dateNaiss == null);


					create(nom, prenom, dateNaiss,dateFormat);

					break;
				default:
					break;
				}
			} catch (NumberFormatException e) {
				// conversion en choix numérique impossible : retour au menu
			}
		}

	}

	private static void create(String nom, String prenom, Date dateNaiss,
			SimpleDateFormat dateFormat) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");
			stm = con.createStatement();
			String sqlQuery = String
					.format("insert into personne(nom,prenom,datenaiss) values ('%s','%s','%tF') returning nom,prenom,datenaiss",
							nom, prenom, dateNaiss);
			rs = stm.executeQuery(sqlQuery);
			while (rs.next()) {
				System.out.println("personne insérée :");
				affichePersonneResutset(rs, dateFormat);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static String checkNullInput(Scanner scanner, String input) {
		if (input.compareTo("") == 0) {
			System.out.println("voulez vous saisir une valeur vide (Y,n)");
			String versatilInput = scanner.nextLine();
			if (versatilInput.compareTo("Y") == 0
					|| versatilInput.length() == 0) {
				input = null;
			}
		}
		return input;
	}

	private static void findByNomPrenom(String nom, String prenom,
			SimpleDateFormat dateFormat) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");
			stm = con.createStatement();
			String sqlQuery = "select nom,prenom,datenaiss from personne";
			Boolean everWhere = false;
			if (nom != null) {
				sqlQuery = sqlQuery.concat(everWhere ? " and" : " where")
						.concat(" nom = '").concat(nom).concat("'");
				everWhere = true;
			}
			if (prenom != null) {
				sqlQuery = sqlQuery.concat(everWhere ? " and" : " where")
						.concat(" prenom = '").concat(prenom).concat("'");
				everWhere = true;
			}
			rs = stm.executeQuery(sqlQuery);
			while (rs.next()) {

				affichePersonneResutset(rs, dateFormat);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static void findAll(SimpleDateFormat dateFormat) {
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");
			stm = con.createStatement();
			rs = stm.executeQuery("select nom,prenom,datenaiss from personne");
			while (rs.next()) {
				affichePersonneResutset(rs, dateFormat);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static void affichePersonneResutset(ResultSet rs,
			SimpleDateFormat dateFormat) throws SQLException {
		String nom = rs.getString("nom");
		String prenom = rs.getString("prenom");
		Date dateNaiss = new Date(rs.getDate("datenaiss").getTime());
		// System.out.println(String.format("nom : %s | prenom : %s | date de naissance : %td/%tm/%tY",
		// nom,prenom,dateNaiss,dateNaiss,dateNaiss));
		System.out.println(String.format(
				"nom : %s | prenom : %s | date de naissance : %s", nom, prenom,
				dateFormat.format(dateNaiss)));
	}

}
