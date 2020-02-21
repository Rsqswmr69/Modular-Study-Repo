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