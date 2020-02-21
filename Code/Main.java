import java.util.*;
import java.awt.EventQueue;

// Run and compile with:
// javac -cp ".:./mysql-connector-java-8.0.19.jar" Main.java 
// java -cp ".:./mysql-connector-java-8.0.19.jar" Main
// On windows replace : with ;

// TODO
// Randomize questions pulled from database
// Handle individul GUI components from Main.java, track scores

// Main class will be initiliazed by user
public class Main {

    // Method calls and sequencing will go here
    public static void main(String[] args) throws Exception {

        // Initialize database
        Database db = new Database();

        // Send categories to GUI via new constructor example
        //InitialWindow iw = new InitialWindow(db.getCategories());
        //iw.setVisible(true);

        // TODO connect this to the GUI as user selects category
        System.out.println(String.format("Question Count: %d", db.getQuestionCount("Geography")));

        // Use this to grab all Question objects, then randomize and feed to GUI  
        Question[] questions = db.getQuestions("Geography"); 

        // Database selected, update number of questions in GUI
        // Quiz started, grab questions from database for subject
        // Provide randomized questions to GUI
        // Track correct answers
        // Quiz completed, send score to GUI


        //InitialWindow gui = new InitialWindow();
        //gui.setVisible(true);
        //getQuestions("Geography");
    }
}

