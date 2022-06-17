package Windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import BaseDAO.*;
import Constants.*;
import DAO.*;

public class Login extends JFrame {
	private static final long serialVersionUID = -5278598737087831336L;
	private JPanel jPanelCenter, jPanelSouth;
	private JTextField username;
	private JPasswordField password;
	private JButton loginButton, resetButton;

	public Login() {
		init();
	}

	private void init() {
		this.setTitle(Constants.LOGIN);

		jPanelCenter = new JPanel();
		jPanelCenter.setLayout(new GridLayout(2, 2));
		jPanelCenter.add(new JLabel(Constants.LOGIN_USERNAME));
		username = new JTextField();
		jPanelCenter.add(username);
		jPanelCenter.add(new JLabel(Constants.LOGIN_PASSWORD));
		password = new JPasswordField();
		// enter key listener
		password.addKeyListener(new LoginListener());
		jPanelCenter.add(password);

		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(1, 2));
		loginButton = new JButton(Constants.LOGIN);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		jPanelSouth.add(loginButton);
		resetButton = new JButton(Constants.RESET);
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}
		});
		jPanelSouth.add(resetButton);

		this.add(jPanelCenter, BorderLayout.CENTER);
		this.add(jPanelSouth, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(450, 250, 350, 140);
		this.setResizable(false);
		this.setVisible(true);
	}

	private class LoginListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				check();
			}
		}
	}

	private void check() {
		AdminDAO adminDAO = (AdminDAO) BaseDAO.getAbilityDAO(DAO.AdminDAO);
		if (adminDAO.queryForLogin(username.getText(), String.valueOf(password.getPassword()))) {
			dispose();
			new MainWindow();
		} else {
			username.setText("");
			password.setText("");
			JOptionPane.showMessageDialog(jPanelCenter, "username or password wrong", "Error",JOptionPane.WARNING_MESSAGE);  
		}
	}
}
