package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the "personne" database table.
 * 
 */
@Entity
@Table(name = "personne")
@NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p")
public class Personne implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name = "datenaiss")
	private Date datenaiss;

	@Column(name = "nom")
	private String nom;

	@Column(name = "prenom")
	private String prenom;

	// bi-directional many-to-one association to Promotion
	@ManyToOne
	@JoinColumn(name="promotion_id")
	private Promotion promotion;

	public Personne() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatenaiss() {
		return this.datenaiss;
	}

	public void setDatenaiss(Date datenaiss) {
		this.datenaiss = datenaiss;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Promotion getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Promotion promotion) {

	
		this.promotion = promotion;

	}

}