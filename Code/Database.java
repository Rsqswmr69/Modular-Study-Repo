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

    // Default database constructor, sets up initial connection to database
    public Database() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {e.printStackTrace();}
    }

    // Returns all categories from database
    public static String[] getCategories() {

        ArrayList<String> cList = new ArrayList<String>();

        //Request categories
        try {
            
            String query = String.format("SELECT * from categories_tbl");
            results = conn.createStatement().executeQuery(query);
            
            // Extract results
            while (results.next()) {
                String category = results.getString("category");
                cList.add(category);
            }

        } catch (SQLException e) {e.printStackTrace();}

        // Return ArrayList as String[]
        return cList.toArray(new String[cList.size()]);
    }

    // Returns int number of questions for a given category
    public static int getQuestionCount(String category) throws Exception {

        try {
            
            String query = String.format("SELECT questions_tbl.question_id, questions_tbl.question_text, categories_tbl.category, group_concat(choices_tbl.choice_text), group_concat(choices_tbl.is_correct) FROM questions_tbl JOIN categories_tbl ON questions_tbl.question_category = categories_tbl.category_id JOIN choices_tbl ON questions_tbl.question_id = choices_tbl.question_id WHERE category = '%s' group by questions_tbl.question_id", category);
            results = conn.createStatement().executeQuery(query);

        } catch (SQLException e) {e.printStackTrace();}
        
        results.last();
        return results.getRow();
    }

    // Returns an array of question objects for every question in the database
    public static Question[] getQuestions(String category) throws Exception  {
        
        ArrayList<Question> qList = new ArrayList<Question>();

        // Request questions
        try {
            String query = String.format("SELECT questions_tbl.question_id, questions_tbl.question_text, categories_tbl.category, group_concat(choices_tbl.choice_text), group_concat(choices_tbl.is_correct) FROM questions_tbl JOIN categories_tbl ON questions_tbl.question_category = categories_tbl.category_id JOIN choices_tbl ON questions_tbl.question_id = choices_tbl.question_id WHERE category = '%s' group by questions_tbl.question_id", category);

            // Create question objects from results
            // Question(String question, String option1, String option2, String option3, String option4, int answerNr)

            // SQL ResultSet is acting as if it does not have anything for results.next() for some reason???
            System.out.println("UH HELLO?");
            System.out.println(results.getString(2));

            // Just need to parse query resultSet into Question objects, and add those into the qList
            while (results.next()) {
                Question q = new Question();
                q.setQuestion(results.getString("question_text"));
                q.setOption1(results.getString("choice_text"));
                qList.add(q);
                System.out.println(q);
                System.out.println(qList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return qList.toArray(new Question[qList.size()]);
    }
}

