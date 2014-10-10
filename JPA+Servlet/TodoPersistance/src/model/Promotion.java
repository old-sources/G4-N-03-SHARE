package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "promotion" database table.
 * 
 */
@Entity
@Table(name="promotion")
@NamedQuery(name="Promotion.findAll", query="SELECT p FROM Promotion p")
public class Promotion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="libelle")
	private String libelle;

	//bi-directional many-to-one association to Personne
	@OneToMany(mappedBy="promotion")
	private List<Personne> personnes;

	public Promotion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Personne> getPersonnes() {
		return this.personnes;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}

	public Personne addPersonne(Personne personne) {
		getPersonnes().add(personne);
		personne.setPromotion(this);

		return personne;
	}

	public Personne removePersonne(Personne personne) {
		getPersonnes().remove(personne);
		personne.setPromotion(null);

		return personne;
	}

}