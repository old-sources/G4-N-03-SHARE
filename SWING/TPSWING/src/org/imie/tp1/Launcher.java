package org.imie.tp1;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//Create and set up the window.
		        JFrame frame = new JFrame("HelloWorldSwing");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		        //Add the ubiquitous "Hello World" label.
		        JLabel label = new JLabel("Hello World");
		        frame.getContentPane().add(label);

		        //Display the window.
		        frame.pack();
		        frame.setVisible(true);
			}
		});

	}
}
