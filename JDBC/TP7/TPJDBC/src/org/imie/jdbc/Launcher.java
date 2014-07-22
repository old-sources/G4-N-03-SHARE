package org.imie.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Launcher {

	public static List<Integer> listePersonneAffichee = new ArrayList<Integer>();

	// public static Integer personneSelectionnee;

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
			System.out.println("4 - TP7 - supprimer une personne");
			System.out.println("5 - TP7 - modifier une personne");
			System.out.println("X - END");
			String saisie = scanner.nextLine();
			if (saisie.compareTo("X") == 0) {
				endAppli = true;
				continue;
			}

			String nom;
			String prenom;
			Date dateNaiss;

			String numeroLigneString;
			Integer numeroLigne;
			Integer personneId;

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
					System.out.println("saisir prenom:");
					prenom = scanner.nextLine();
					prenom = checkNullInput(scanner, prenom);
					findByNomPrenom(nom, prenom, dateFormat);
					break;
				case 3:
					System.out.println("saisir nom:");
					nom = scanner.nextLine();
					nom = checkNullInput(scanner, nom);
					System.out.println("saisir prenom:");
					prenom = scanner.nextLine();
					prenom = checkNullInput(scanner, prenom);
					System.out.println("saisir date Naissance:");
					dateNaiss = saisirDate(dateFormat, scanner);
					create(nom, prenom, dateNaiss, dateFormat);

					break;
				case 4:
					findAll(dateFormat);
					System.out.println("saisir la personne à supprimer:");
					numeroLigneString = scanner.nextLine();
					numeroLigne = Integer.valueOf(numeroLigneString);
					personneId = listePersonneAffichee.get(numeroLigne - 1);
					delete(personneId);
					break;
				case 5:
					findAll(dateFormat);
					System.out.println("saisir la personne à modifier:");
					numeroLigneString = scanner.nextLine();
					numeroLigne = Integer.valueOf(numeroLigneString);
					personneId = listePersonneAffichee.get(numeroLigne - 1);
					System.out.format("saisir nom (%s):","");
					nom = scanner.nextLine();
					nom = checkNullInput(scanner, nom);
					System.out.format("saisir prenom (%s):","");
					prenom = scanner.nextLine();
					prenom = checkNullInput(scanner, prenom);
					System.out.format("saisir date Naissance (%s):","");
					dateNaiss = saisirDate(dateFormat, scanner);
					update(personneId,nom,prenom,dateNaiss);
					break;
				default:
					break;
				}
			} catch (NumberFormatException e) {
				// conversion en choix numérique impossible : retour au menu
			}
		}

	}

	private static Date saisirDate(SimpleDateFormat dateFormat,
			Scanner scanner) {
		String dateNaissString;
		Date dateNaiss=null;
		do {
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
		return dateNaiss;
	}

	private static void update(Integer personneId, String nom, String prenom, Date dateNaiss) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");

			String sqlQuery = "update personne set nom=?,prenom=?,datenaiss=? where id=?";
			stm = con.prepareStatement(sqlQuery);
			stm.setString(1, nom);
			stm.setString(2, prenom);
			stm.setDate(3,new java.sql.Date(dateNaiss.getTime()));
			stm.setInt(4, personneId);
			
			Integer nbLigneDeleted = stm.executeUpdate();
			System.out.println(String.format("%d personne modifiée(s)",
					nbLigneDeleted));

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

	private static void delete(Integer personneId) {
		Connection con = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/imie", "postgres",
					"postgres");

			String sqlQuery = "delete from personne where id=?";
			stm = con.prepareStatement(sqlQuery);
			stm.setInt(1, personneId);
			Integer nbLigneDeleted = stm.executeUpdate();
			System.out.println(String.format("%d personne supprimée(s)",
					nbLigneDeleted));

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
			rs = stm.executeQuery("select id,nom,prenom,datenaiss from personne");
			listePersonneAffichee.clear();
			Integer cpt = 1;
			while (rs.next()) {
				listePersonneAffichee.add(rs.getInt("id"));
				System.out.print(String.format("- %d -", cpt++));
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
		Date dateNaiss = rs.getDate("datenaiss");
		// System.out.println(String.format("nom : %s | prenom : %s | date de naissance : %td/%tm/%tY",
		// nom,prenom,dateNaiss,dateNaiss,dateNaiss));
		System.out.println(String.format(
				"nom : %s | prenom : %s | date de naissance : %s", nom, prenom,
				dateFormat.format(dateNaiss)));
	}

}
