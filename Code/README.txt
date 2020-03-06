Modular Study Program

Team Lead Kory Bennett
Program Manager Alton Brock
Lead Tech Dylan Sawyer
SQL Tech Justin Casey
GUI Tech Cale Ward
Aux Tech Rich Page



GitHub Link:
https://github.com/Rsqswmr69/Modular-Study-Repo



Running the Program:

To run the program, simply double-click "study.jar"
- Make sure the files from this archive are in the same directory



Troubleshooting:

If nothing happens, open up a command line (cmd.exe)
Navigate to the location of the code files (cd command)
try java -jar study.jar

If java is not a recognized command:
- Install Java Runtime Environment

If a connection to the database cannot be established:
- Contact a project POC to ensure database is online

If there is a class version error:
- Delete all class files (.class extension)
- Recompile (javac -cp ".;./mysql-connector-java-8.0.19.jar" *.java)
- Run again

If the jar simply fails to execute
- Try running manually (java -cp ".;./mysql-connector-java-8.0.19.jar" Main)
- Note: Replace ; with : on Linux for java commands