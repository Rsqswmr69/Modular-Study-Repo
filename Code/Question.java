//Authors:Cale Ward
//Date:13Jan-09Mar
//Purpose:To set up the Question class for a Java quiz app with a MySQL database

/*
import java.util.ArrayList;
import java.util.List;


public class Question {

	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private int answerNr;
	
	public Question () {}
	
	public Question(String question, String option1, String option2, String option3, String option4, int answerNr) {
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answerNr = answerNr;
	}

	
	 

	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getOption1() {
		return option1;
	}


	public void setOption1(String option1) {
		this.option1 = option1;
	}


	public String getOption2() {
		return option2;
	}


	public void setOption2(String option2) {
		this.option2 = option2;
	}


	public String getOption3() {
		return option3;
	}


	public void setOption3(String option3) {
		this.option3 = option3;
	}


	public String getOption4() {
		return option4;
	}


	public void setOption4(String option4) {
		this.option4 = option4;
	}


	public int getAnswerNr() {
		return answerNr;
	}


	public void setAnswerNr(int answerNr) {
		this.answerNr = answerNr;
	}
		
}
*/

//Authors:Cale Ward
//Date:13Jan-09Mar
//Purpose:To set up the Question class for a Java quiz app with a MySQL database

import java.util.ArrayList;
import java.util.List;


public class Question {

	private String question;
	private Choice choice1;
	private Choice choice2;
	private Choice choice3;
	private Choice choice4;
	public Question () {}
	
	public Question(String question, Choice c1, Choice c2, Choice c3, Choice c4) {
		this.question = question;
		this.choice1 = c1;
		this.choice2 = c2;
		this.choice3 = c3;
		this.choice4 = c4;
	}

	// Getters
    public String getQuestion() {return question;}
	public String getChoice1() {return choice1.getChoiceText();}
    public String getChoice2() {return choice2.getChoiceText();}
	public String getChoice3() {return choice3.getChoiceText();}
    public String getChoice4() {return choice4.getChoiceText();}
	public Choice getChoiceChoice1() {return choice1;}
	public Choice getChoiceChoice2() {return choice2;}
	public Choice getChoiceChoice3() {return choice3;}
	public Choice getChoiceChoice4() {return choice4;}

	
    // Setters
	public void setQuestion(String question) {this.question = question;}
	public void setOption1(Choice choice1) {this.choice1 = choice1;}
	public void setOption2(Choice choice2) {this.choice2 = choice2;}
	public void setOption3(Choice choice3) {this.choice3 = choice3;}
	public void setOption4(Choice option4) {this.choice4 = choice4;}
		
    @Override
    public String toString() {
        String str = String.format("Question: %s\nChoice1: %s\nChoice2: %s\nChoice3: %s\nChoice4: %s\n", this.question, this.choice1.toString(), this.choice2.toString(), this.choice3.toString(), this.choice4.toString());
        return str;
    }

}

