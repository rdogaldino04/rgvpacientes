package com.galdino.rgvpacientes.view;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JPanel;

public class JpHome extends JPanel {

	private static final long serialVersionUID = 5184868289664191139L;

	public JpHome() {
		Box box = Box.createHorizontalBox();
		box.add (Box.createVerticalStrut (8));
		setLayout(new BorderLayout());
		add(box, BorderLayout.CENTER);
	}

}
