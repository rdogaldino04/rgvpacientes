package com.galdino.rgvpacientes.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JpPatient extends JPanel {

	private static final long serialVersionUID = 5184868289664191139L;

	public JpPatient() {
		Box box = Box.createHorizontalBox();		
//		box.add(new JButton(new AbstractAction() {		
//			private static final long serialVersionUID = 1L;
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("ok.........");
//			}
//		}));
		JTextField jTextField = new JTextField("Test");
		box.add(jTextField);
		box.add (Box.createVerticalStrut (8));
		setLayout(new BorderLayout());
		add(box, BorderLayout.CENTER);
	}

}
