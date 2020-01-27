import mysql.connector
import itertools

def get_question(mycursor, question_id):
    '''
    returns tuple of question data
    question[0][0] = id
    question[0][1] = question text
    '''
    sql = "select question_id, question_text from questions_tbl where question_id = %s"
    val = (question_id,)
    mycursor.execute(sql, val)
    question = mycursor.fetchall()
    return question

def get_choice(mycursor, questions_id):
    '''
    returns list of choices
    choices[0][0] = choice text
    choices[0][1] = answer
    '''
    sql = "select choice_text, is_correct \
        from choices_tbl \
        where question_id = %s"
    val = (question_data[0][0],)
    mycursor.execute(sql, val)
    choices = mycursor.fetchall()
    return choices

def insert_into_scores(mycursor, user_id, score):
    '''
    inserts a users score into the db
    '''
    sql = "INSERT INTO scores_tbl (user_id, score) VALUES(%s, %s)"
    val = (user_id, score)
    mycursor.execute(sql, val)
    mydb.commit()

def print_scores(mycursor):
    '''
    prints all users scores
    '''
    sql = "select users_tbl.user_uname, score from scores_tbl inner join users_tbl on users_tbl.user_id"
    mycursor.execute(sql)
    scores = mycursor.fetchall()
    for score in scores:
        print(f"USER: {score[0]} SCORE: {score[1]}")

# connect to database
mydb = mysql.connector.connect(
  host="localhost",
  user="ADD_YOUR_USERNAME",
  passwd="ADD_YOUR_PASSWORD",
  database="study"
)
mycursor = mydb.cursor()

# get number of questions
mycursor.execute("SELECT count(*) from questions_tbl")
questions_count = mycursor.fetchall()
questions_count = questions_count[0][0]
correct_answer = 0


# loop through all questions
for i in range(1,questions_count + 1):
    # get question object
    question_data = get_question(mycursor, i)

    # get choices associated with question
    choices = get_choice(mycursor, question_data[0][0])

    # print qeustion number
    print("***Question" + str(i) + "***\n")
    # print question text
    print(question_data[0][1] + "?")

    # hold the correct answer index
    answer_number = 0
    # loop through all choices
    for i in range(len(choices)):
        # setting the answer to the correct index
        if choices[i][1] == 1:
            answer_number = i + 1
        print(str(i + 1) + ". " + choices[i][0])

    # get users answer
    answer = input("\nPress 1, 2, 3 for your answer\n")

    # display result
    if int(answer) == answer_number:
        correct_answer += 1
        print("CORRECT!!!")
    else: 
        print("INCORRECT!!!")

# print overall score
score = round((correct_answer / questions_count) * 100, 2)
print("Your score is " + str(score))

# insert score into database
insert_into_scores(mycursor, 1, score)

#print all users scores
print_scores(mycursor)