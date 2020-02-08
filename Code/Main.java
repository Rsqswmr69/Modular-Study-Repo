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
public class Main {

    // Method calls and sequencing will go here
    public static void main(String[] args) throws Exception {
        sqlDump();
    }

    // Parser method
    // GUI Window calling methods
    // Need to figure out the sequence of calling questions from SQL to GUI

    // Testing SQL connection and dumping contents
    public static void sqlDump() throws Exception {

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
            String sql = "SELECT * FROM questions_tbl";

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

