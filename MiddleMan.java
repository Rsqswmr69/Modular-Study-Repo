import java.sql.*;

public class MiddleMan {

    public static void main(String[] args) throws Exception {
        mariaDB();
    }

    public static void mariaDB() {
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet results = null;

        String URL = "jdbc:mysql://remotemysql.com:3306/nEHRVrOue2";
        String USER = "nEHRVrOue2";
        String PASSWORD = "PH7592K95R";

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            System.out.println("Connecting");

            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected");

            stmt = conn.createStatement();
            String sql = "SELECT * FROM questions_tbl";
            
            results = stmt.executeQuery(sql);

            while (results.next()) {
                int questionId = results.getInt("question_id");
                String questionText = results.getString("question_text");
            
                System.out.println(questionText);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
