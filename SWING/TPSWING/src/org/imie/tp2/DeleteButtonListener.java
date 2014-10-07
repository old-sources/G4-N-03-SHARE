package org.imie.tp2;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class DeleteButtonListener implements ActionListener{
	JPanel panelCenter;
	JPanel panelToDelete;
	
	
	
	public DeleteButtonListener(JPanel panelCenter, JPanel panelToDelete) {
		super();
		this.panelCenter = panelCenter;
		this.panelToDelete = panelToDelete;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		panelCenter.remove(panelToDelete);
		panelCenter.revalidate();
		panelCenter.repaint();
	}
}
