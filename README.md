# jdbc-Database-Queries

Within directory "src/main" contains "java" directory that contains the Main.java class. 

This file contains the main function that contains all of the implementation. It connects to a localhost postgresql database using String url = "jdbc:postgresql://localhost:5432/Assignment3"; change 5432 to the port number of postgresql application being used and change Assignment3 to the name of the database you wish to connect to.

String user = "postgres"; change postgres to your user name for the postgresql application #line 11

String password = "HaveFun:)"; change HaveFun:) to your password for the postgresql application #line 13

The program will use this information to connect to the database using jdbc, here's a schema of the database, fields represent the columns of the data rows.

Database Schema:

    Table name: students
    Fields:
        student_id: Integer, Primary Key, Auto-increment
        first_name: Text, Not Null
        last_name: Text, Not Null
        email: Text, Not Null, Unique
        enrollment_date: Date

The application provides a user interface like this:

Please enter a number in the range 0-4 to select one of the options below
1: Display all records from the students table.
2: Add student to table.
3: Update student email.
4: Delete student.
0: EXIT.

User inputs a number from 0 to 4 to use the desired implementation.

When changing names or emails, the function takes in input in the form of **strings** with **no spaces** (takes singles arguments) and does not have data validation.

student_id values are to be inputted as **integers**, takes **single arguments**

enrollment_date takes input in the form of sql Date type **("YYYY-MM-DD")**, takes **single arguments**

As described in the menu the application gives the user access to view all the data in the database, the ability to add a student row, the ability to update an email in a row based on the student_id primary key, and lastly the ability to delete a student row in the database based on the primary key student_id

