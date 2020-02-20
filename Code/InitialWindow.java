//Authors:Cale Ward
//Date:13Jan-09Mar
//Purpose:To set up the inital window for a Java quiz app with a MySQL database

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class InitialWindow extends JFrame {

	
	public int totalQuestions;
	public int testableQuestions;
	
	private JPanel contentPane;
	private JTextField userNumberOfQuestionsTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialWindow frame = new InitialWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public InitialWindow() throws Exception {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/*
		 * Subject A is autoselected to start 
		 */
		JComboBox subjectComboBox = new JComboBox();
		subjectComboBox.setToolTipText("Click the arrow and select the course you would like to study");
		subjectComboBox.setModel(new DefaultComboBoxModel(new String[] {"Geography", "Math", "Subject C", "Subject D"}));
		subjectComboBox.setBounds(109, 50, 246, 24);
		contentPane.add(subjectComboBox);
				
		JLabel numberOfQuestionsAvailable = new JLabel("# of Questions Available");
		numberOfQuestionsAvailable.setToolTipText("The number of Questions available to study in this subject");
		numberOfQuestionsAvailable.setFont(new Font("Monospaced", Font.BOLD, 15));
		numberOfQuestionsAvailable.setBounds(138, 124, 309, 47);
		contentPane.add(numberOfQuestionsAvailable);
		
		/*
		 * This is where the initial set up is with the first drop down menu item selected. 
		 * The parser should find the number of questions available and display it where I 
		 * have hardcoded a 9 in. 
		 */
		
		JLabel actualNumber = new JLabel();

		if(subjectComboBox.getSelectedItem().equals("Geography")) {
			int questionCountTotal;
			List <Question> questionList = new ArrayList<>();
			Database db = new Database();
			questionList = db.createQuestionList("Geography");
			questionCountTotal = questionList.size();
			actualNumber.setText(Integer.toString(questionCountTotal));
		}
		if(subjectComboBox.getSelectedItem().equals("Math")) {
			int questionCountTotal;
			List <Question> questionList = new ArrayList<>();
			Database db = new Database();
			questionList = db.createQuestionList("Math");
			questionCountTotal = questionList.size();
			actualNumber.setText(Integer.toString(questionCountTotal));
		}
		actualNumber.setToolTipText("The number of Questions available to study in this subject");
		actualNumber.setBounds(431, 135, 206, 24);
		contentPane.add(actualNumber);
		
		JLabel EnterLabel = new JLabel("Enter the # of Questions to study:");
		EnterLabel.setToolTipText("");
		EnterLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 13));
		EnterLabel.setBounds(138, 204, 354, 40);
		contentPane.add(EnterLabel);
		
		/*
		 * This is where the user would type in the number of questions they want. 
		 */
		userNumberOfQuestionsTF = new JTextField();
		userNumberOfQuestionsTF.setBounds(431, 215, 114, 19);
		contentPane.add(userNumberOfQuestionsTF);
		userNumberOfQuestionsTF.setColumns(10);
		
		JButton startButton = new JButton("Start");
		startButton.setToolTipText("Click start to begin the test once you have entered a subject and the number of questions");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Gets the # from the textfield from the user and from the database
				 */
				try {
				totalQuestions= Integer.parseInt(userNumberOfQuestionsTF.getText());
				testableQuestions = Integer.parseInt(actualNumber.getText());
				}
				catch (NumberFormatException f){			
				}
				/*
				 * Checks whether the number enter is between 0 and the number of possible questions
				 */
				if(totalQuestions < 1 || totalQuestions > testableQuestions) {
					JOptionPane.showMessageDialog(startButton, "Please enter a number > 0 and less than the # of questions available");
				}
				/*
				 * Closes the window and opens up the question window
				 */
				else {
					dispose();
					Questions questions;
					try {
						questions = new Questions();
						questions.setVisible(true);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		startButton.setBounds(238, 323, 254, 47);
		contentPane.add(startButton);
		
		
		
		
	}
	
	
}
