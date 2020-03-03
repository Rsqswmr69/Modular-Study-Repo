package modularStudy;

// Helper class for storing answers for Question class
public class Choice {
    private int choiceId;
    private String choiceText;
    private int isCorrect;

	// Getters
    public int getChoiceId() {return choiceId;}
    public String getChoiceText() {return choiceText;}
    
	// Setters
    public void setChoiceId(int choiceId) {this.choiceId = choiceId;}
    public void setChoiceText(String choiceText) {this.choiceText = choiceText;}
    public void setIsCorrect(int isCorrect) {this.isCorrect = isCorrect;}

	// Misc
	@Override
    public String toString() {return choiceText + " " + isCorrect;}
	public int isCorrect() {return isCorrect;}
}