package org.imie.ihm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.imie.DTO.PersonneDTO;
import org.imie.DTO.PromotionDTO;
import org.imie.exception.ImieException;
import org.imie.service.EcoleService;
import org.imie.service.IEcoleService;

public class ConsoleMain {

	public static void main(String[] args) {
		// IPersonneDAO personneDao = PersonneDAO.getInstance();
		// IPromotionDAO promotionDao = new PromotionDAO();
		IEcoleService ecoleService = new EcoleService();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Scanner scanner = new Scanner(System.in);
		Boolean endApp = false;

		while (!endApp) {
			try {
				System.out.println("");
				System.out.println("_______________________________");
				System.out.println("1 - voir toutes les personnes");
				System.out.println("2 - créer une personne");
				System.out.println("3 - supprimer une personne");
				System.out.println("4 - modifier une personne");
				System.out.println("5 - rechercher par critère");
				System.out.println("6 - supprimer une promotion");
				System.out.println("X - sortir");
				System.out.println("_______________________________");
				String saisie = scanner.nextLine();
				if (saisie.compareTo("X") == 0) {
					endApp = true;
					continue;
				} else {
					Integer menu = Integer.valueOf(saisie);
					List<PersonneDTO> dtos;
					List<PromotionDTO> promotionDTOs;
					Integer lineNumber;
					PersonneDTO selectedPersonne;
					PromotionDTO selectedPromotion;
					Integer deletedLines;
					switch (menu) {
					case 1:
						dtos = ecoleService.getAllPersonne();
						displayListPersonne(dtos, simpleDateFormat);
						break;

					case 2:

						break;

					case 3:

						break;

					case 4:

						break;

					case 5:

						break;

					case 6:

						break;

					default:
						break;
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				if (e.getCause() != null) {
					System.out.println("-> ".concat(e.getCause().getMessage()));
				}
			} 
		}

	}

	private static PersonneDTO typePersonne(Scanner scanner,
			SimpleDateFormat simpleDateFormat) {
		System.out.println("saisir nom :");
		String nom = scanner.nextLine();
		nom = specifyNull(scanner, nom);
		System.out.println("saisir prenom :");
		String prenom = scanner.nextLine();
		prenom = specifyNull(scanner, prenom);
		String dateTypingLabel = "saisir date de naissance :";
		Date dateNaiss = typeDate(scanner, simpleDateFormat, dateTypingLabel);
		PersonneDTO personne = new PersonneDTO();
		personne.setNom(nom);
		personne.setPrenom(prenom);
		personne.setDateNaiss(dateNaiss);
		return personne;
	}

	private static Date typeDate(Scanner scanner,
			SimpleDateFormat simpleDateFormat, String dateTypingLabel) {
		Date dateNaiss = null;
		Boolean dateTyped = false;
		while (dateTyped == false) {
			System.out.println(dateTypingLabel);
			String dateNaissString = scanner.nextLine();
			dateNaissString = specifyNull(scanner, dateNaissString);
			if (dateNaissString != null) {
				try {
					dateNaiss = simpleDateFormat.parse(dateNaissString);
					dateTyped = true;
				} catch (ParseException e) {
					System.out.println("format incorect : jj/mm/yyyy");
				}
			} else {
				dateTyped = true;
			}
		}
		return dateNaiss;
	}

	private static String specifyNull(Scanner scanner, String saisie) {
		if (saisie.isEmpty()) {
			System.out.println("Valeur null(N) ou vide(v)");
			String emptyValue = scanner.nextLine();
			if (emptyValue.compareTo("N") == 0 || emptyValue.isEmpty()) {
				saisie = null;
			} else if (emptyValue.compareTo("v") == 0) {
				saisie = "";
			}
		}
		return saisie;
	}

	private static void displayListPersonne(List<PersonneDTO> dtos,
			SimpleDateFormat simpleDateFormat) {
		Integer i = 1;
		for (PersonneDTO personneDTO : dtos) {
			System.out.format("%d - ", i++);
			displayPersonne(personneDTO, simpleDateFormat);
		}
	}

	private static void displayListPromotion(List<PromotionDTO> dtos,
			SimpleDateFormat simpleDateFormat) {
		Integer i = 1;
		for (PromotionDTO promotionDTO : dtos) {
			System.out.format("%d - ", i++);
			displayPromotion(promotionDTO, simpleDateFormat);
		}

	}

	private static void displayPromotion(PromotionDTO promotionDTO,
			SimpleDateFormat simpleDateFormat) {

		System.out.format("libelle :%s\n", promotionDTO.getLibelle());

	}

	private static void displayPersonne(PersonneDTO personneDTO,
			SimpleDateFormat simpleDateFormat) {
		String displayedDate;
		if (personneDTO.getDateNaiss() == null) {
			displayedDate = "";
		} else {
			displayedDate = simpleDateFormat.format(personneDTO.getDateNaiss());
		}

		System.out
				.format("nom :%s | prenom:%s | date de naissance : %s | promotion : %s\n",
						personneDTO.getNom(), personneDTO.getPrenom(),
						displayedDate,
						personneDTO.getPromotionDTO() == null ? ""
								: personneDTO.getPromotionDTO().getLibelle());
	}

}
