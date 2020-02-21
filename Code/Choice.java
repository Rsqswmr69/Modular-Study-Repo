public class Choice {
    private int choiceId;
    private String choiceText;
    private int isCorrect;

    public Choice() {}

    public int getChoiceId() {
        return choiceId;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public int isCorrect() {
        return isCorrect;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public String toString() {
        return choiceText + " " + isCorrect;
    }
}