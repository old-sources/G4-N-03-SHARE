package org.imie.tp2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LauncherTP2 {
	public static void main(String[] args) {
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
				
//				addButton.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						panelCenter.add(
//								new JLabel(addTexteField.getText()),
//								BorderLayout.CENTER);
//						frame.pack();
//
//					}
//				});
				
				panelNorth.add(addButton);
				frame.getContentPane().add(panelNorth,BorderLayout.NORTH);
				frame.getContentPane().add(new JScrollPane(panelCenter),BorderLayout.CENTER);

				// Display the window.
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
