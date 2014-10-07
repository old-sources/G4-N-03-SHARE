package org.imie.tp3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
		JLabel label = new JLabel(addTexteField.getText());
		label.setPreferredSize(new Dimension(200, 30));
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(400, 100));
		panel.add(label);
		JButton button = new JButton("delete");
		button.addActionListener(new DeleteButtonListener(panelCenter,panel));
		panel.add(button);
		panelCenter.add(panel);
		frame.pack();

	}

}
