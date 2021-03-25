import java.sql.*;

public class SimpleJdbc2 {
  public static void main(String[] args)
    throws SQLException, ClassNotFoundException {


    // Load the JDBC driver
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    System.out.println("Driver loaded");


    // Establish a connection
    Connection connection = DriverManager.getConnection
      ("jdbc:odbc:exampleMDB.mdb");
    System.out.println("Database connected");


    // Create a statement
    Statement stmt = connection.createStatement();

	String myString = "select * from Course";
		System.out.println(myString);
    // Select the columns from the Student table
    ResultSet rset = stmt.executeQuery(myString);


    // Iterate through the result and print the student names
    while (rset.next())
      System.out.println(rset.getString(1) + " " + rset.getString(2)
        + " " + rset.getString(3)+ " " + rset.getString(4)
		  + " " + rset.getString(5));


    // close the connection
    connection.close();
  }
}