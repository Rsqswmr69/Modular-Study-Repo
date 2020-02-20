import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Main class will be initiliazed by user
public class Database {

    // Set static variables for database connection
    private static Connection conn = null;
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
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(query);
            
            // Extract results
            while (results.next()) {
                String category = results.getString("category");
                cList.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return ArrayList as String[]
        return cList.toArray(new String[cList.size()]);
    }

    // Returns int number of questions for a given category
    public static int getQuestionCount(String category) throws Exception {
        ResultSet results = null;

        try {
            
            String query = String.format("select count(*) from questions_tbl join categories_tbl on questions_tbl.question_category = categories_tbl.category_id where category = '%s'", category);
            Statement stmt = conn.createStatement();
            results = stmt.executeQuery(query);
            results.last();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results.getInt("count(*)");
    }

    // Returns an array of question objects for every question in the database
    public static Question[] getQuestions(String category) throws Exception  {
        ArrayList<Question> qList = new ArrayList<Question>();
        ArrayList<Choice> cList = new ArrayList<>();

        // Request questions
        try {
            String query = String.format("SELECT questions_tbl.question_id, questions_tbl.question_text, categories_tbl.category, group_concat(choices_tbl.choice_text), group_concat(choices_tbl.is_correct) FROM questions_tbl JOIN categories_tbl ON questions_tbl.question_category = categories_tbl.category_id JOIN choices_tbl ON questions_tbl.question_id = choices_tbl.question_id WHERE category = '%s' group by questions_tbl.question_id", category);
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(query);

            while (results.next()) {
                int questiond_id = results.getInt("question_id");
                String question_text = results.getString("question_text");
                String[] choices = results.getString(4).split(",");
                String[] answers = results.getString(5).split(",");
                
                // create the question object
                Question q = new Question();
                q.setQuestion(question_text);
                q.setOption1(choices[0]);
                q.setOption2(choices[1]);
                q.setOption3(choices[2]);
                q.setOption4(choices[3]);
                q.setAnswerNr(1);
                
                // add to arraylist
                qList.add(q);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return qList.toArray(new Question[qList.size()]);
    }
}

