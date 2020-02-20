import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Run and compile with:
// javac -cp ".:./mysql-connector-java-8.0.19.jar" Main.java 
// java -cp ".:./mysql-connector-java-8.0.19.jar" Main
// On windows replace : with ;

// Main class will be initiliazed by user
public class Database {

    // Set static variables for database connection
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet results = null;
    private static String USER = "nEHRVrOue2";
    private static String PASSWORD = "PH7592K95R";
    private static String DATABASE = "study";
    private static String DOMAIN = "nehrvroue2.cb1ite2ngo4u.us-east-2.rds.amazonaws.com";
    private static String URL = "jdbc:mysql://" + DOMAIN + "/" + DATABASE;
    private static ArrayList<String> categories = new ArrayList<String>();
    private static ArrayList<HashMap<String, String>> questions = new ArrayList<HashMap<String, String>>();

    // Default database constructor
    public Database() throws Exception {
        try {
            // Setup initial database connection
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            String q = String.format("SELECT * from categories_tbl");
            results = conn.createStatement().executeQuery(q);

            // Extract categories and save them to class variable
            while (results.next()) {
                String category = results.getString("category");
                categories.add(category);
            }
        } catch (SQLException e) {e.printStackTrace();}
    }

    // Returns all categories from database
    public static ArrayList<String> getCategories() {return categories;}
    
    // Returns a single question for a given question ID
    public static ArrayList<HashMap<String, String>> getQuestion(int questionID) {

        ArrayList<HashMap<String, String>> question = new ArrayList<HashMap<String, String>>();

        for (HashMap<String, String> q: questions)
        {
            if (Integer.parseInt(q.get("id")) == questionID) {question.add(q);}
        }
        return question;
    }

    //Puts questions and answers into a Question container and then puts those Question objects into a List
    public  List<Question> createQuestionList(String category) throws SQLException {
            String q = String.format("SELECT questions_tbl.question_id, questions_tbl.question_text, categories_tbl.category, choices_tbl.choice_text, choices_tbl.is_correct FROM questions_tbl INNER JOIN categories_tbl ON questions_tbl.question_category = categories_tbl.category_id INNER JOIN choices_tbl on questions_tbl.question_id = choices_tbl.question_id WHERE category = '%s'", category);
            results = conn.createStatement().executeQuery(q);
            List<Question> questionList = new ArrayList<>();
            // Collect all questions from category and store them in an ArrayList of questions
            while (results.next()) {
                Question question = new Question();
                question.setQuestion(results.getString("question_id"));
                question.setOption1(results.getString("question_text"));
                question.setOption2(results.getString("choice_text"));
                question.setOption3(results.getString("is_correct"));
          
                questionList.add(question);
            }
		return questionList;
    }
    
    
    
    // Set category and initialize all questions from that category
    public static void setCategory(String category) throws Exception  {
        try {
            String q = String.format("SELECT questions_tbl.question_id, questions_tbl.question_text, categories_tbl.category, choices_tbl.choice_text, choices_tbl.is_correct FROM questions_tbl INNER JOIN categories_tbl ON questions_tbl.question_category = categories_tbl.category_id INNER JOIN choices_tbl on questions_tbl.question_id = choices_tbl.question_id WHERE category = '%s'", category);
            results = conn.createStatement().executeQuery(q);

            // Collect all questions from category and store them in an ArrayList of questions
            while (results.next()) {
                HashMap<String, String> question = new HashMap<>();
                question.put("id", results.getString("question_id"));
                question.put("question", results.getString("question_text"));
                question.put("choice", results.getString("choice_text"));
                question.put("value", results.getString("is_correct"));
                questions.add(question);
            }
        } catch (SQLException e) {e.printStackTrace();}
    }
}

