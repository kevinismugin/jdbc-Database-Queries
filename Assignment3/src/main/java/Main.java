import java.sql.*;
import java.util.Scanner;



public class Main  {
    public static void main(String[] args) {
        //pgadmin postgresql jdbc connection url, database is called Assignment3
        String url = "jdbc:postgresql://localhost:5432/Assignment3";
        //my user name
        String user = "postgres";
        //my password
        String password = "HaveFun:)";

        try{
            //postgresql Driver Class
            Class.forName("org.postgresql.Driver");
            //connection to the local database
            Connection connection = DriverManager.getConnection(url, user, password);
            //initialized statement that will allow use to apply queries to the database
            Statement statement = connection.createStatement();
            //scanner for user input
            Scanner scanner = new Scanner(System.in);
            //while loop provides interface for user interaction
            while(true){
                //printMenu() provides a text interface that provides information on interactions available to the user
                printMenu();
                //grabs user choice
                int choice = scanner.nextInt();
                scanner.nextLine();
                //if 0 quit
                if (choice == 0) {
                    break;
                }
                //switch based on user choice and implements what the user selected based on printmenu
                switch (choice) {
                    //case 1 calls getAllStudents(Statement)
                    case 1:
                        //implements SELECT * query that returns all information from students table
                        getAllStudents(statement);
                        continue;
                    case 2:
                        //asks for first name
                        System.out.println("Enter first name:");
                        String firstName = scanner.nextLine();
                        //asks for last name
                        System.out.println("Enter the last name:");
                        String lastName = scanner.nextLine();
                        //asks for email
                        System.out.println("Enter the email (email@example.com):");
                        String email = scanner.nextLine();
                        //asks for enrollment dat
                        System.out.println("Enter the enrollment date (yyyy-mm-dd):");
                        String enrollmentDate = scanner.nextLine();
                        //uses user input variables as parameters for the addStudent function
                        //addStudent implements an insert query into the connected database
                        addStudent(statement, firstName, lastName, email, enrollmentDate);
                        continue;
                    case 3:
                        //gets student ID
                        System.out.println("Enter student ID of student to update email:");
                        int studentId = scanner.nextInt();
                        //gets new email to be updated
                        System.out.println("Please provide the new email:");
                        scanner.nextLine();
                        String newEmail = scanner.nextLine();
                        //uses updateStudentEmail to set the new email of the student
                        updateStudentEmail(statement, studentId, newEmail);
                        continue;
                    case 4:
                        //gets student id of student to delete
                        System.out.println("Enter student ID of student to delete:");
                        int delStudentId = scanner.nextInt();
                        //calls deleteStudent which calls a delete query using the parameters onto the connected database
                        deleteStudent(statement, delStudentId);
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    //getAllStudents(Statement statement) takes a statement parameter and uses it to call a query
    public static void getAllStudents(Statement statement) throws SQLException {
        //sql SELECT * query to grab all the student information from the table in the database
        statement.executeQuery("SELECT * FROM students");
        //get the result of the query
        ResultSet resultSet = statement.getResultSet();
        //loop through the results and print out the information of each column of the tuple
        while(resultSet.next()){
            System.out.print(resultSet.getInt("student_id") + " \t");
            System.out.print(resultSet.getString("first_name") + " \t");
            System.out.print(resultSet.getString("last_name") + " \t");
            System.out.print(resultSet.getString("email") + " \t");
            System.out.println(resultSet.getDate("enrollment_date"));
        }
        System.out.println();
    }

    //addStudent uses it's parameters to form a INSERT sql query and then uses the statement parameter to execute the query
    public static void addStudent(Statement statement, String firstName, String lastName, String email, String enrollmentDate) throws SQLException {
        String sql = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES ('" + firstName + "', '" + lastName + "', '" + email + "', '" + enrollmentDate + "')";
        statement.executeUpdate(sql);
    }

    //updateStudentEmail uses it's parameters to form an UPDATE sql query and then uses the statement parameter to execute the query
    public static void updateStudentEmail(Statement statement, int studentId, String newEmail) throws SQLException {
        String sql = "UPDATE students SET email = '" + newEmail + "' WHERE student_id = " + studentId;
        statement.executeUpdate(sql);
    }

    //deleteStudent uses it's parameters to form a DELETE sql query and then uses the statement parameter to execute the query
    public static void deleteStudent(Statement statement, int studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id = " + studentId;
        statement.executeUpdate(sql);
    }

    //printMenu() prints out function implementation information for the user so they can decide what they want to do with the database
    public static void printMenu(){
        //print functions that show all possible options of user interaction
        System.out.println("Please enter a number in the range 0-4 to select one of the options below");
        System.out.println("1: Display all records from the students table.");
        System.out.println("2: Add student to table.");
        System.out.println("3: Update student email.");
        System.out.println("4: Delete student.");
        System.out.println("0: EXIT.");
    }
}
