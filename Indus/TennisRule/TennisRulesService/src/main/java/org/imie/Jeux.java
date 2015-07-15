package org.imie;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
public class Jeux implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5095427833317835509L;
	private Integer score1 = 0;
	private Integer score2 = 0;
	private Boolean victoire = false;
	@Inject
	@Named("serialiser")
	private transient ISerialiser serialiser;

	public Integer getScore1() {
		return score1;
	}

	public Integer getScore2() {
		return score2;
	}

	public void setSerialiser(ISerialiser serialiser) {
		this.serialiser = serialiser;
	}

	public Jeux() {
		super();
		serialiser = new Serialiser();
	}

	public Object getScore() {

		String retour = "";
		if (score1 == 3 && score2 == 3) {
			retour = "egalite";
		} else if (score1 >= 4 || score2 >= 4) {
			if ((score1 == 3 && score2 == 4) || (score1 == 4 && score2 == 3)) {
				retour = "avantage ";
				if (score1 > score2) {
					retour = retour.concat("j1");
				} else if (score2 > score1) {
					retour = retour.concat("j2");
				}

			} else {
				retour = "victoire ";
				if (score1 > score2) {
					retour = retour.concat("J1");
				} else if (score2 > score1) {
					retour = retour.concat("J2");
				}
			}

		} else {
			retour = String.format("%s-%s", translate(score1),
					translate(score2));
		}

		return retour;
	}

	private Object translate(Integer score) {
		String retour = "";
		switch (score) {
		case 0:
			retour = "0";
			break;
		case 1:
			retour = "15";
			break;
		case 2:
			retour = "30";
			break;
		case 3:
			retour = "40";
			break;
		default:
			break;
		}
		return retour;
	}

	public void marquerJoueur1() {
		if (!victoire) {
			if (score2 <= 3) {
				score1++;
			} else {
				score2--;
			}
			victoire();
		}else {
			throw new RuntimeException("jeux déjà en victoire");
		}
	}

	public void marquerJoueur2() {
		if (!victoire) {
			if (score1 <= 3) {
				score2++;
			} else {
				score1--;
			}
			victoire();
		}else{
			throw new RuntimeException("jeux déjà en victoire");
		}

	}

	public void save() {
		serialiser.persist(this);

	}

	public void reset() {
		score1 = 0;
		score2 = 0;
		victoire = false;

	}

	public void load() {
		Jeux loadJeux = serialiser.read();
		score1 = loadJeux.getScore1();
		score2 = loadJeux.getScore2();

	}

	public void victoire() {
		if ((score1 >= 4 || score2 >= 4) && (Math.abs(score1 - score2) > 1)) {
			victoire = true;
		}
	}

}
