package com.galdino.rgvpacientes;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RgvPacientesApplication extends JFrame {

	private static final long serialVersionUID = -7767547372191785860L;

	public RgvPacientesApplication() {
		initUI();
	}

	private void initUI() {

		JButton quitButton = new JButton("Quit");

		quitButton.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});

		createLayout(quitButton);

		setTitle("Quit button");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createLayout(JComponent... arg) {

		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);

		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]));

		gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]));
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(RgvPacientesApplication.class).headless(false)
				.run(args);

		EventQueue.invokeLater(() -> {
			RgvPacientesApplication ex = ctx.getBean(RgvPacientesApplication.class);
			ex.setVisible(true);
		});
	}

}
