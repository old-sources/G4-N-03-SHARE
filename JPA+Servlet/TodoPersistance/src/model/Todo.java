package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "todo" database table.
 * 
 */
@Entity
@Table(name="\"todo\"")
@NamedQuery(name="Todo.findAll", query="SELECT t FROM Todo t")
public class Todo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"id\"")
	private int id;

	@Column(name="\"description\"")
	private String description;

	public Todo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}