import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Run and compile with:
// javac -cp ".:./mysql-connector-java-8.0.19.jar" Main.java 
// java -cp ".:./mysql-connector-java-8.0.19.jar" Main
// On windows replace : with ;

// TODO
// Parse stuff from SQL into a test format (see queries.txt)
// Handle individul GUI components from Main.java, track scores

// Main class will be initiliazed by user
public class Main {

    // Method calls and sequencing will go here
    public static void main(String[] args) throws Exception {
        
        InitialWindow gui = new InitialWindow();
        gui.setVisible(true);
        getQuestions("Geography");
    }



    // Testing SQL connection and dumping contents
    public static void getQuestions(String category) throws Exception {

        // write your code here
        Connection conn = null;
        Statement stmt = null;
        ResultSet results = null;

        String USER = "nEHRVrOue2";
        String PASSWORD = "PH7592K95R";
        String DATABASE = "nEHRVrOue2";
        String DOMAIN = "remotemysql.com";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Connecting");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + DOMAIN + "/" + DATABASE, USER, PASSWORD);
            System.out.println("Connected");

            // create query
            stmt = conn.createStatement();
            String sql = String.format("SELECT questions_tbl.question_id, questions_tbl.question_text, categories_tbl.category FROM questions_tbl INNER JOIN categories_tbl ON questions_tbl.question_category = categories_tbl.category_id WHERE category = '%s'", category);

            // execute query store results into results
            results = stmt.executeQuery(sql);

            // loop through results
            while (results.next()) {
                // store variable based on column name
                int questionId = results.getInt("question_id");
                String questionText = results.getString("question_text");

                // prints all questions
                System.out.println(questionText);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

