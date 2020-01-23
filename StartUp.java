//Author:Cale Ward
//Date:13Jan-09Mar2020
//Purpose:To create a login screen for a quiz application

package quiz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPasswordField;

public class StartUp {

	public String uname;
	public String pword;
	private JFrame frame;
	private JTextField tfUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartUp window = new StartUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelUsername = new JLabel("Username:");
		labelUsername.setBounds(52, 72, 103, 15);
		frame.getContentPane().add(labelUsername);
		
		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setBounds(52, 112, 103, 24);
		frame.getContentPane().add(labelPassword);
		
		JLabel labelTitle = new JLabel("UMGC CMSC 495");
		labelTitle.setFont(new Font("Nimbus Sans L", Font.BOLD, 22));
		labelTitle.setBounds(44, 0, 366, 60);
		frame.getContentPane().add(labelTitle);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(158, 71, 179, 17);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		JButton buttonLogin = new JButton("Login");
		buttonLogin.setBounds(158,181,122,24);
		frame.getContentPane().add(buttonLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 115, 179, 15);
		frame.getContentPane().add(passwordField);

		buttonLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String uname= tfUsername.getText();
				String pword= passwordField.getText();
				
				if (uname.contentEquals("")||pword.contentEquals("")) {
					JOptionPane.showMessageDialog(frame, "Enter a valid username or password");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Login successful");
					
				}
			}	
		});
			
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	 	
}
