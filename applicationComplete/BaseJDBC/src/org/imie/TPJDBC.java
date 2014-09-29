package org.imie;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.imie.DAO.IPersonneDAO;
import org.imie.DAO.IPromotionDAO;
import org.imie.DAO.PersonneDAO;
import org.imie.DAO.PromotionDAO;
import org.imie.DTO.PersonneDTO;
import org.imie.DTO.PromotionDTO;
import org.imie.exception.ImieException;

public class TPJDBC {

	public static void main(String[] args) {

		IPersonneDAO personneDao = new PersonneDAO();
		IPromotionDAO promotionDao = new PromotionDAO();
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
						dtos = personneDao.findAll();
						displayListPersonne(dtos, simpleDateFormat);
						break;

					case 2:
						PersonneDTO personneToInsert = typePersonne(scanner,
								simpleDateFormat);
						PersonneDTO personneInserted = personneDao
								.insert(personneToInsert);
						displayPersonne(personneInserted, simpleDateFormat);
						System.out.format("personne insérée avec l'id %d\n",
								personneInserted.getId());
						break;

					case 3:
						dtos = personneDao.findAll();
						displayListPersonne(dtos, simpleDateFormat);
						System.out.println("saisir une personne à supprimer");
						saisie = scanner.nextLine();
						lineNumber = Integer.valueOf(saisie);
						selectedPersonne = dtos.get(lineNumber - 1);
						deletedLines = personneDao.delete(selectedPersonne);
						System.out.format("%d lignes supprimée(s)\n",
								deletedLines);
						break;

					case 4:
						dtos = personneDao.findAll();
						displayListPersonne(dtos, simpleDateFormat);
						System.out.println("saisir une personne à modifier");
						saisie = scanner.nextLine();
						lineNumber = Integer.valueOf(saisie);
						selectedPersonne = dtos.get(lineNumber - 1);

						PersonneDTO personneToUpdate = typePersonne(scanner,
								simpleDateFormat);
						selectedPersonne.setNom(personneToUpdate.getNom());
						selectedPersonne
								.setPrenom(personneToUpdate.getPrenom());
						selectedPersonne.setDateNaiss(personneToUpdate
								.getDateNaiss());

						PersonneDTO personneUpdated = personneDao
								.update(selectedPersonne);
						displayPersonne(personneUpdated, simpleDateFormat);
						break;

					case 5:
						System.out.println("saisir une personne à rechercher");
						PersonneDTO personneToSearch = typePersonne(scanner,
								simpleDateFormat);
						List<PersonneDTO> presonnesFinded = personneDao
								.findByDTO(personneToSearch);
						displayListPersonne(presonnesFinded, simpleDateFormat);
						break;

					case 6:

						promotionDTOs = promotionDao.findByDTO(new PromotionDTO());
						displayListPromotion(promotionDTOs, simpleDateFormat);
						System.out.println("saisir une promotion à supprimer");
						saisie = scanner.nextLine();
						lineNumber = Integer.valueOf(saisie);
						selectedPromotion = promotionDTOs.get(lineNumber - 1);
						deletedLines = promotionDao.delete(selectedPromotion);
						System.out.format("%d lignes supprimée(s)\n",
								deletedLines);
						break;

					default:
						break;
					}
				}
			} catch (ImieException e) {
				System.out.println(e.getMessage());
				if (e.getCause()!=null){
					System.out.println("-> ".concat(e.getCause().getMessage()));
				}
			} catch (NumberFormatException e) {
				System.out.println("Menu erroné");
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
