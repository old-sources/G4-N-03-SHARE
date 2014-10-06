package org.imie.tp2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddButtonListener implements ActionListener {

	JPanel panelCenter;
	JTextField addTexteField;
	JFrame frame;
	
	
	public AddButtonListener(JPanel panelCenter, JTextField addTexteField,
			JFrame frame) {
		super();
		this.panelCenter = panelCenter;
		this.addTexteField = addTexteField;
		this.frame = frame;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		panelCenter.add(
				new JLabel(addTexteField.getText()),
				BorderLayout.CENTER);
		frame.pack();

	}

}
