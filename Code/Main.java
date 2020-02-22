import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
public class Main extends JFrame{
	

	public static JFrame intialWindow = new JFrame();
	public static JFrame quizWindow = new JFrame();
	public static JFrame resultsWindow = new JFrame();

	//initial variables
	public static int totalQuestions;
	public static int testableQuestions;
	
	//Quiz variables
	public static JRadioButton bRadioButton = new JRadioButton("b)");
	public static JRadioButton cRadioButton = new JRadioButton("c)");
	public static JRadioButton dRadioButton = new JRadioButton("d)");
	public static JRadioButton aRadioButton = new JRadioButton("a)");	
	public static JLabel questionLabel = new JLabel("");
	public static JPanel contentPane;
	public static final ButtonGroup buttonGroup = new ButtonGroup();
	public static Question[] questionList;
	public static int questionCounter;
	public static int questionCountTotal;
	public static Question currentQuestion;
	public static JLabel lblQuestion = new JLabel();
	public static int i=0;
	public static double score;
	public static boolean answered;

	//results variables
	
	
		public static void main(String args[]) throws Exception {

			initialGUI();
			
		}
		
			
		public static void initialGUI() throws Exception {	
			Database db = new Database();

			JPanel contentPane;
			JTextField userNumberOfQuestionsTF;		
			
			intialWindow.setResizable(false);
			intialWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			intialWindow.setBounds(100, 100, 739, 457);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			intialWindow.setContentPane(contentPane);
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
				int questionCountG= db.getQuestionCount("Geography");			
				actualNumber.setText(Integer.toString(questionCountG));
					}
			if(subjectComboBox.getSelectedItem().equals("Math")) {
				int questionCountM= db.getQuestionCount("Math");			
				actualNumber.setText(Integer.toString(questionCountM));
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
						intialWindow.dispose();
						try {
							questionsGUI();
						} catch (Exception e1) {
								// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			startButton.setBounds(238, 323, 254, 47);
			contentPane.add(startButton);	
			intialWindow.setVisible(true);
		}//end initialGUI
		
		
		
		
		
		
		
		private static void showQuestion() throws Exception {
			/*
			 * Clears the selected button and resets the question
			 * based with the next question
			 */
			buttonGroup.clearSelection();
	
			if(questionCounter < questionCountTotal) {
				currentQuestion = questionList[i];
				questionLabel.setText(currentQuestion.getQuestion());
				aRadioButton.setText(currentQuestion.getChoice1());
				bRadioButton.setText(currentQuestion.getChoice2());
				cRadioButton.setText(currentQuestion.getChoice3());
				dRadioButton.setText(currentQuestion.getChoice4());
				questionCounter++;
				lblQuestion.setText("Question:"+ questionCounter+"/"+questionCountTotal);
				answered=false;
				i++;
				
			}
			else {
				score = (score / questionCountTotal)*100;
				quizWindow.dispose();
				try {
					resultsGUI();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		private static void checkAnswer() throws Exception {
			
			if (aRadioButton.isSelected()==true) {
				int answerNr = 1;
				if (answerNr == (currentQuestion.getChoiceChoice1().isCorrect())) {
					score++;
				}
			}
			if (bRadioButton.isSelected()==true) {
				int answerNr = 1;
				if (answerNr == (currentQuestion.getChoiceChoice2().isCorrect())) {
					score++;
				}
			}
			if (cRadioButton.isSelected()==true) {
				int answerNr = 1;
				if (answerNr == (currentQuestion.getChoiceChoice3().isCorrect())) {
					score++;
				}
			}
			if (dRadioButton.isSelected()==true) {
				int answerNr = 1;
				if (answerNr == (currentQuestion.getChoiceChoice4().isCorrect())) {
					score++;
				}
			}
			showQuestion();
		}
		
		//QuestionWindowGUI
		public static void questionsGUI() throws Exception {	
			quizWindow.setResizable(false);
			quizWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			quizWindow.	setBounds(100, 100, 739, 457);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			quizWindow.	setContentPane(contentPane);
			contentPane.setLayout(null);
					
			questionLabel.setBounds(169, 34, 477, 88);
			contentPane.add(questionLabel);
			
			buttonGroup.add(aRadioButton);
			aRadioButton.setBounds(148, 150, 543, 30);
			contentPane.add(aRadioButton);
			
			buttonGroup.add(bRadioButton);
			bRadioButton.setBounds(148, 195, 543, 30);
			contentPane.add(bRadioButton);
			
			buttonGroup.add(cRadioButton);
			cRadioButton.setBounds(148, 240, 543, 30);
			contentPane.add(cRadioButton);
			
			buttonGroup.add(dRadioButton);
			dRadioButton.setBounds(148, 285, 543, 30);
			contentPane.add(dRadioButton);
			
			JButton submitButton = new JButton("Submit");
			submitButton.setToolTipText("Click submit to answer the question\n");
			
			lblQuestion.setBounds(20, 12, 132, 36);
			contentPane.add(lblQuestion);
			
			submitButton.setBounds(509, 355, 182, 43);
			contentPane.add(submitButton);
						
			Database db = new Database();
			questionList = db.getQuestions("Geography");
			questionCountTotal = db.getQuestionCount("Geography");
			
			showQuestion();
				
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						/*
						 * Check to see if a button is selected
						 */
					if(!answered) {
						if (aRadioButton.isSelected()||bRadioButton.isSelected()||cRadioButton.isSelected()||dRadioButton.isSelected()) {
							try {
								checkAnswer();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						else {
							JOptionPane.showMessageDialog(submitButton, "Why not guess an answer even if you do not know it?");
						}
					}
					else {
						try {
							showQuestion();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
				}
					
			});
			quizWindow.setVisible(true);
		}//end quizGUI
		
		
		//results GUI
		public static void resultsGUI() throws Exception {	
		
			JPanel contentPane;

			resultsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			resultsWindow.	setBounds(100, 100, 739, 457);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			resultsWindow.	setContentPane(contentPane);
			contentPane.setLayout(null);
				
			JLabel completeLabel = new JLabel("Complete!");
			completeLabel.setBounds(156, 49, 320, 80);
			contentPane.add(completeLabel);				
				
			JLabel scoreLabel = new JLabel("Score:      " + score+" %");
			scoreLabel.setBounds(156, 162, 305, 46);
			contentPane.add(scoreLabel);
			
			JButton restartButton = new JButton("Restart");
			restartButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						resultsWindow.dispose();
						initialGUI();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
				}
			});
			restartButton.setBounds(87, 278, 233, 66);
			contentPane.add(restartButton);
				
			JButton exitButton = new JButton("Exit");
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					resultsWindow.dispose();
				}
			});
			exitButton.setBounds(413, 278, 233, 66);
			contentPane.add(exitButton);
			resultsWindow.setVisible(true);
		}//end resultsGIU
		
		
}
		
		
		
		
		


			
			
		