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
import javax.swing.ButtonModel;

import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;

public class Questions extends JFrame {
	
	/*
	 * List to hold the Questions and Answers
	 */
	private List<Question> questionList;
	private int questionCounter;
	private int questionCountTotal;
	private Question currentQuestion;
	
	public double score;
	boolean answered;
	
	
	
	JLabel lblQuestion = new JLabel();

	JRadioButton bRadioButton = new JRadioButton("b)");
	JRadioButton cRadioButton = new JRadioButton("c)");
	JRadioButton dRadioButton = new JRadioButton("d)");
	JRadioButton aRadioButton = new JRadioButton("a)");	
	JLabel questionLabel = new JLabel("");
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
			
		questionLabel.setBounds(148, 12, 477, 88);
		contentPane.add(questionLabel);
		
		buttonGroup.add(aRadioButton);
		aRadioButton.setBounds(148, 150, 149, 23);
		contentPane.add(aRadioButton);
		
		buttonGroup.add(bRadioButton);
		bRadioButton.setBounds(148, 195, 149, 23);
		contentPane.add(bRadioButton);
		
		buttonGroup.add(cRadioButton);
		cRadioButton.setBounds(148, 240, 149, 23);
		contentPane.add(cRadioButton);
		
		buttonGroup.add(dRadioButton);
		dRadioButton.setBounds(148, 285, 149, 23);
		contentPane.add(dRadioButton);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setToolTipText("Click submit to answer the question\n");
		
		lblQuestion.setBounds(20, 12, 94, 36);
		contentPane.add(lblQuestion);
		
		submitButton.setBounds(509, 355, 182, 43);
		contentPane.add(submitButton);
		
		
		dbHelper dbHelper = new dbHelper();
		questionList = dbHelper.getQuestions();
		questionCountTotal = questionList.size();
		Collections.shuffle(questionList);
		
		showQuestion();
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Check to see if a button is selected
				 */
				if(!answered) {
					if (aRadioButton.isSelected()||bRadioButton.isSelected()||cRadioButton.isSelected()||dRadioButton.isSelected()) {
						checkAnswer();
					}
					else {
						JOptionPane.showMessageDialog(submitButton, "Why not guess an answer even if you do not know it?");
					}
				}
				else {
					showQuestion();
				}
					
			}
			
		});
	
	
	}
	private void checkAnswer() {
		answered = true;
		
		/*
		 * If a) is selected and the answer is a) from the database
		 * add one to the score...and for b,c, & d
		 */
		if (aRadioButton.isSelected()==true) {
			int answerNr = 1;
			if (answerNr == currentQuestion.getAnswerNr()) {
				score++;
			}
		}
		if (bRadioButton.isSelected()==true) {
			int answerNr = 2;
			if (answerNr == currentQuestion.getAnswerNr()) {
				score++;
			}
		}
		if (cRadioButton.isSelected()==true) {
			int answerNr = 3;
			if (answerNr == currentQuestion.getAnswerNr()) {
				score++;
			}
		}
		if (dRadioButton.isSelected()==true) {
			int answerNr = 4;
			if (answerNr == currentQuestion.getAnswerNr()) {
				score++;
			}
		}
		
	}
		
		 
	
	private void showQuestion() {
		/*
		 * Clears the selected button and resets the question
		 * based with the next question
		 */
		lblQuestion.setText("Question:"+ questionCounter+"/"+questionCountTotal);
		buttonGroup.clearSelection();
		answered=false;
		
		
		if(questionCounter < questionCountTotal) {
			currentQuestion = questionList.get(questionCounter);
			questionLabel.setText(currentQuestion.getQuestion());
			aRadioButton.setText(currentQuestion.getOption1());
			bRadioButton.setText(currentQuestion.getOption2());
			cRadioButton.setText(currentQuestion.getOption3());
			dRadioButton.setText(currentQuestion.getOption4());
			questionCounter++;
			
		}
		else {
			dispose();
			Results results= new Results();
			results.setVisible(true);
		}
	}
}
      	
     
	