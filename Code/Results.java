//Authors:Cale Ward
//Date:13Jan-09Mar
//Purpose:To set up the Results window for a Java quiz app with a MySQL database




import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Results extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Results frame = new Results();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Results() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel completeLabel = new JLabel("Complete!");
		completeLabel.setBounds(156, 49, 320, 80);
		contentPane.add(completeLabel);
		
		JLabel scoreLabel = new JLabel("Score:");
		scoreLabel.setBounds(156, 162, 305, 46);
		contentPane.add(scoreLabel);
		
		JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InitialWindow iW = new InitialWindow();
				dispose();
				iW.setVisible(true);
				
			}
		});
		restartButton.setBounds(87, 278, 233, 66);
		contentPane.add(restartButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exitButton.setBounds(413, 278, 233, 66);
		contentPane.add(exitButton);
	}
}
