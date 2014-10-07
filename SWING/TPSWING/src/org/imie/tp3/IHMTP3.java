package org.imie.tp3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.imie.DTO.PersonneDTO;
import org.imie.factory.IFactory;
import org.imie.ihm.IIHM;
import org.imie.service.IEcoleService;

public class IHMTP3 implements IIHM{

	private IFactory factory;
	
	
	public IHMTP3(IFactory factory) {
		super();
		this.factory = factory;
	}


	@Override
	public void start() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Create and set up the window.
				JFrame frame = new JFrame("ToDoSwing");
				frame.setPreferredSize(new Dimension(500,500));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(new BorderLayout());

				JPanel panelNorth = new JPanel();
				JPanel panelCenter = new JPanel();
				panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
				
				// Add the ubiquitous "Hello World" label.
				JTextField addTexteField = new JTextField();
				addTexteField.setPreferredSize(new Dimension(150, 20));
				panelNorth.add(addTexteField, BorderLayout.NORTH);

				JButton addButton = new JButton("add");
				addButton.addActionListener(new AddButtonListener(panelCenter, addTexteField, frame));
				
				
				panelNorth.add(addButton);
				frame.getContentPane().add(panelNorth,BorderLayout.NORTH);
				frame.getContentPane().add(new JScrollPane(panelCenter),BorderLayout.CENTER);

				IEcoleService service= factory.createEcoleService();
				List<PersonneDTO> personnes= service.getAllPersonne();
				
				for (PersonneDTO personneDTO : personnes) {
					PersonneRow personneRow = new PersonneRow(personneDTO, panelCenter);
					panelCenter.add(personneRow);
				}
				frame.pack();
				
				
				
				// Display the window.
				frame.pack();
				frame.setVisible(true);
			}
		});
		
	}
}
