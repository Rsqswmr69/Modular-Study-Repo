//Authors:Cale Ward
//Date:13Jan-09Mar
//Purpose:To set up the dbHelper for a Java quiz app with a MySQL database


import java.util.ArrayList;
import java.util.List;

public class dbHelper {
	
	
	
	public List<Question> getQuestions() {
		List<Question> questionList = new ArrayList<>();
		//access database
		Question question = new Question();
		question.setQuestion("add question from db");
		question.setOption1("opt 1 from db");
		question.setOption2("opt 2 from db");
		question.setOption3("opt 3 from db");
		question.setOption4("opt 4 from db");
		question.setAnswerNr(2); //from db
		questionList.add(question);
		
		return questionList;

		
	}
}
