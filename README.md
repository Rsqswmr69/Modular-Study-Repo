 # Modular-Study-Repo
Here is where we will collaborate on the UMUC 495 Capstone Project. The idea is that a user can load different databases of quiz questions to study any desired topic. The program would also keep track of what score they achieved, when they achieved it, and which database of questions they used to help them track their studying progress.

### Setup Database
1. Install mariaDB 
2. Start mysql
```
mysql -u root
<enter password>
```
3. Create user 
```
CREATE USER username@localhost IDENTIFIED BY 'password';
```
4. Create database
```
create database study;
```
5. grant user permissions
```
GRANT ALL PRIVILEGES ON study.* to 'user'@'localhost';
```
6. Exit mysql and import database
```
exit
mysql study < studydb.sql
```
7. Start mysql
```
mysql -u username -p study
<enter password>
```
8. Test
```
SELECT * FROM questions_tbl;
```

### Team:  
__Team Lead__: Kory Bennett   / Documentation Organizer     
__Project Manager__: Brock Allton   / Documentation Organizer       
__Developer__: Dylan Sawyer   / Database->Parser  
__Developer__: Richard "Dan" Page   / Parser->GUI  
__Developer__: Justin Casey   / Database   
__Developer__: Cale Ward   / Gui Developer 
