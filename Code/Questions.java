//Authors:Cale Ward
//Date:13Jan-09Mar
//Purpose:To set up the Question window for a Java quiz app with a MySQL database


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Questions extends JFrame {
	
	public int totalQuestions;
	public int questionsLeft;
	public double score;
	boolean flag=false;
	
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Questions frame = new Questions();
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
	public Questions() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * Database Parser needs to input first question here and subsequent
		 * questions each time a new question is needed
		 */
		JLabel questionLabel = new JLabel("Questions Goes Here\n");
		questionLabel.setBounds(148, 12, 477, 88);
		contentPane.add(questionLabel);
		
		JRadioButton aRadioButton = new JRadioButton("a)");
		buttonGroup.add(aRadioButton);
		aRadioButton.setBounds(148, 150, 149, 23);
		contentPane.add(aRadioButton);
		
		JRadioButton bRadioButton = new JRadioButton("b)");
		buttonGroup.add(bRadioButton);
		bRadioButton.setBounds(148, 195, 149, 23);
		contentPane.add(bRadioButton);
		
		JRadioButton cRadioButton = new JRadioButton("c)");
		buttonGroup.add(cRadioButton);
		cRadioButton.setBounds(148, 240, 149, 23);
		contentPane.add(cRadioButton);
		
		JRadioButton dRadioButton = new JRadioButton("d)");
		buttonGroup.add(dRadioButton);
		dRadioButton.setBounds(148, 285, 149, 23);
		contentPane.add(dRadioButton);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(45, 355, 252, 43);
		contentPane.add(progressBar);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setToolTipText("Click submit to answer the question\n");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Check to see if a button is selected
				 */
				if (buttonGroup.getSelection()==null)
					flag = false;
				else 
					flag = true;
				
				
				/*
				 * Create the Results window when all questions have been answered, add one to the score
				 * 
				 */
				if(questionsLeft==0) {
					/*
					 * if no button is selected return
					 */
					if (flag==false) {
						JOptionPane.showMessageDialog(submitButton, "Why not guess an answer if you do not know it?");
						return;
					}
					/*
					 * if the right answer is selected add 1 to the score
					 */
					if(bRadioButton.isSelected()) {
						score++;
					}
					dispose(); 
					Results result = new Results();
					result.setVisible(true);
				}
				
				else {
					if (flag=false) {
						JOptionPane.showMessageDialog(submitButton, "Why not guess an answer if you do not know it?");
					
					}
					/*
					 * Checks whether the answer is correct and restarts the window with a new question
					 * 
					 */
					if(bRadioButton.isSelected()) {
						score++;
					}
					questionsLeft--;
				//	progressBar.update();
					dispose();
					Questions q = new Questions();
					q.setVisible(true);
					
				}
			}
		});
		submitButton.setBounds(509, 355, 182, 43);
		contentPane.add(submitButton);
		
	}
}
