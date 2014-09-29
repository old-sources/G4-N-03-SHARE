package org.imie.DTO;

import java.util.Date;

public class PersonneDTO {
	private Integer id;
	private String nom;
	private String prenom;
	private Date dateNaiss;
	
	private PromotionDTO promotionDTO;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	public PromotionDTO getPromotionDTO() {
		return promotionDTO;
	}
	public void setPromotionDTO(PromotionDTO promotionDTO) {
		this.promotionDTO = promotionDTO;
	}
	
	
	
}
