class Question {
    private int questionId;
    private String questionText;
    private String questionCategory;
        
    public Question(int questionId, String questionText, String questionCategory) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.questionCategory = questionCategory;
    }
        
    public int getQuestionId() {
        return questionId;
    }
        
    public void setQuestionId(int id) {
        this.questionId = id;
    }
        
    public String getQuestionText() {
        return questionId;
    }
        
    public void setQuestionText(String text) {
        this.questionText = text;
    }
        
    public String getQuestionCategory() {
        return questionCategory;
    }
        
    public void setQuestionCategory(String category) {
        this.questionCategory = category;
    }
        
    @Override
    public String toString() {
        return questionText;
    }
}
