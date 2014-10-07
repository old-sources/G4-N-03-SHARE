/**
 * 
 */
package org.imie.tp3;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.imie.DTO.PersonneDTO;

/**
 * @author imie
 *
 */
public class PersonneRow extends JPanel {
	private PersonneDTO personneDTO;
	private JLabel label;
	private JButton button;
	private JPanel panelContainer;
	
	public PersonneRow(PersonneDTO personneDTO,JPanel panelContainer) {
		super();
		this.personneDTO = personneDTO;
		this.panelContainer = panelContainer;
		
		label = new JLabel(personneDTO.getNom());
		label.setPreferredSize(new Dimension(200, 30));
		this.setMaximumSize(new Dimension(400, 100));
		this.add(label);
		JButton button = new JButton("delete");
		button.addActionListener(new DeleteButtonListener(panelContainer,this));
		this.add(button);
		//panelContainer.add(this);
		
		
	}
	
	
	

}
