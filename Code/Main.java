import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.awt.event.ActionEvent;
public class Main extends JFrame{
	

	public static JFrame intialWindow = new JFrame();
	public static JFrame quizWindow = new JFrame();
	public static JFrame resultsWindow = new JFrame();

	//initial variables
	public static int totalQuestions;
	public static int testableQuestions;
	public static String category = "Geography";
	
	//Quiz variables
	public static JRadioButton bRadioButton = new JRadioButton("b)");
	public static JRadioButton cRadioButton = new JRadioButton("c)");
	public static JRadioButton dRadioButton = new JRadioButton("d)");
	public static JRadioButton aRadioButton = new JRadioButton("a)");	
	public static JButton submitButton = new JButton("Submit");
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
	public static long timeElapsed;

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
			
			JLabel actualNumber = new JLabel();
			actualNumber.setText(Integer.toString(db.getQuestionCount("Geography")));
			actualNumber.setToolTipText("The number of Questions available to study in this subject");
			actualNumber.setBounds(431, 135, 206, 24);
			contentPane.add(actualNumber);
			
			/*
			 * Subject A is autoselected to start 
			 */
			JComboBox subjectComboBox = new JComboBox();
			subjectComboBox.setToolTipText("Click the arrow and select the course you would like to study");
			subjectComboBox.setModel(new DefaultComboBoxModel(new String[] {"Geography", "Math", "History"}));
			subjectComboBox.setBounds(109, 50, 246, 24);
			contentPane.add(subjectComboBox);
			subjectComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e2) {
					category = (String) subjectComboBox.getSelectedItem();
					if(category == "Geography") {
						int questionCountG;
						try {
							questionCountG = db.getQuestionCount("Geography");
							actualNumber.setText(Integer.toString(questionCountG));

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
							}
					if(category == "Math") {
						int questionCountM;
						try {
							questionCountM = db.getQuestionCount("Math");
							actualNumber.setText(Integer.toString(questionCountM));

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
					}
					if(category == "History") {
						int questionCountM;
						try {
							questionCountM = db.getQuestionCount("History");
							actualNumber.setText(Integer.toString(questionCountM));

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
					}

				}
			});
			
			
			JLabel numberOfQuestionsAvailable = new JLabel("# of Questions Available");
			numberOfQuestionsAvailable.setToolTipText("The number of Questions available to study in this subject");
			numberOfQuestionsAvailable.setFont(new Font("Monospaced", Font.BOLD, 15));
			numberOfQuestionsAvailable.setBounds(138, 124, 309, 47);
			contentPane.add(numberOfQuestionsAvailable);
	
			
			
			
			JLabel EnterLabel = new JLabel("Enter the # of Questions to study:");
			EnterLabel.setToolTipText("");
			EnterLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 13));
			EnterLabel.setBounds(138, 204, 354, 40);
			contentPane.add(EnterLabel);
			
			
			 
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
						
						questionCountTotal = Integer.parseInt(userNumberOfQuestionsTF.getText());
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
			aRadioButton.setForeground(Color.BLACK);
			bRadioButton.setForeground(Color.BLACK);
			cRadioButton.setForeground(Color.BLACK);
			dRadioButton.setForeground(Color.BLACK);
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
				endQuiz();
			}
		}
		
		private static void endQuiz() {
			score = (score / questionCountTotal)*100;
			quizWindow.dispose();
			try {
				resultsGUI();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private static void checkAnswer() throws Exception {
			answered = true;
			if (aRadioButton.isSelected()==true) {
				if (1 == (currentQuestion.getChoiceChoice1().isCorrect())) {
					score++;
				}
			}
			if (bRadioButton.isSelected()==true) {
				if (1 == (currentQuestion.getChoiceChoice2().isCorrect())) {
					score++;
				}
			}
			if (cRadioButton.isSelected()==true) {
				if (1 == (currentQuestion.getChoiceChoice3().isCorrect())) {
					score++;
				}
			}
			if (dRadioButton.isSelected()==true) {
				if (1 == (currentQuestion.getChoiceChoice4().isCorrect())) {
					score++;
				}
			}
				highlightAnswer();
		}
		
		private static void highlightAnswer() {
			aRadioButton.setForeground(Color.RED);
			bRadioButton.setForeground(Color.RED);
			cRadioButton.setForeground(Color.RED);
			dRadioButton.setForeground(Color.RED);

			if (currentQuestion.getChoiceChoice1().isCorrect()==1) {
				aRadioButton.setForeground(Color.GREEN);
			}
			if (currentQuestion.getChoiceChoice2().isCorrect()==1) {
				bRadioButton.setForeground(Color.GREEN);
			}
			if (currentQuestion.getChoiceChoice3().isCorrect()==1) {
				cRadioButton.setForeground(Color.GREEN);
			}
			if (currentQuestion.getChoiceChoice4().isCorrect()==1) {
				dRadioButton.setForeground(Color.GREEN);
			}
			if (questionCounter<questionCountTotal) {
				submitButton.setText("Next");
			}
			else {
				submitButton.setText("Turn In");
			}
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
			
			submitButton.setToolTipText("Click submit to answer the question\n");
			
			lblQuestion.setBounds(20, 12, 132, 36);
			contentPane.add(lblQuestion);
			
			submitButton.setBounds(509, 355, 182, 43);
			contentPane.add(submitButton);
						
			Database db = new Database();
			questionList = db.getQuestions(category);
			List<Question> qList = Arrays.asList(questionList);
			Collections.shuffle(qList);
			qList.toArray(questionList);
			
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
						i=0;
						score=0;
						questionCounter=0;
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
		
		
		
		
		


			
			
		