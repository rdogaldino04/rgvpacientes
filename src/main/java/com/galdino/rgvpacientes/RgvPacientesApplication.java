package com.galdino.rgvpacientes;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.galdino.rgvpacientes.view.JpHome;
import com.galdino.rgvpacientes.view.JpPatient;

@SpringBootApplication
public class RgvPacientesApplication extends JFrame {

	private static final long serialVersionUID = -7767547372191785860L;

	public RgvPacientesApplication() {
		initUI();
	}

	private void initUI() {
//		JButton quitButton = new JButton("Quit");
		createMenu();

//		quitButton.addActionListener((ActionEvent event) -> {
//			System.exit(0);
//		});

//		createLayout(quitButton);

		getContentPane().setLayout(new CardLayout());
		getContentPane().add(new JpHome(), "JpHome");
		getContentPane().add(new JpPatient(), "JpPatient");

		setTitle("Registro de pacientes");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void switchTo(String page) {
		((CardLayout) getContentPane().getLayout()).show(getContentPane(), page);
	}

	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Cadastro");
		menu.setMnemonic(KeyEvent.VK_C);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("Pacientes", KeyEvent.VK_P);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(e -> switchTo("JpPatient"));
		menu.add(menuItem);

		setJMenuBar(menuBar);
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
