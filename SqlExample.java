 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Statement;
 
 public class SqlExample {
 
   public static void main(String[] args) throws Exception {
     // write your code here
     Connection conn = null;
     Statement stmt = null;
     ResultSet results = null;
 
     String USER = "nEHRVrOue2";
     String PASSWORD = "PH7592K95R";
     String DATABASE = "nEHRVrOue2";
     String DOMAIN = "remotemysql.com";
 
     try {
 
       Class.forName("com.mysql.cj.jdbc.Driver");
 
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

