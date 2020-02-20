import java.util.ArrayList;
import java.util.List;
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
   //     Database db = new Database();
   //     db.setCategory("Geography");
   //    for(int i=0; i <= 10; i++) {
   //         System.out.println("Question " + i);
   //         System.out.println(db.getQuestion(i));
   //        System.out.println();
   //     }
        //InitialWindow gui = new InitialWindow();
        //gui.setVisible(true);
	//getQuestions("Geography");
	    InitialWindow iW = new InitialWindow();
	    iW.setVisible(true);
    }
        
}

