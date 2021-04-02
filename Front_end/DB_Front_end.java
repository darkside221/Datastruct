import java.sql.Connection;
//this library lets us conect to the DB

import java.sql.DriverManager;
//this library keeps track of our Drivers for conecting to a data base 

import java.sql.PreparedStatement;
// this is an SQL statment that has been pre compiled

public class ConnectToDatabase {
//this class is the class that will let us conect to the DB

    public static void main(String[] args) {
    //this subclass is the class that will atemt to conect to the DB

        String MySQLURL = "jdbc:mysql://localhost:3306/web?useSSL=false";
        //sets the URL where the front end will try and look to for the DB so when you set this up you neet to clange the URL:jdbc:mysql://localhost:3306/web?useSSL=false to the info spasific to your DB

        String databseUserName = "root";
        //this lets the program know what the user name is so that it can conect to the DB so if your username is anything difrent then root you need to change it 

        String databasePassword = "Solarwinds123";
        //this lets the program know what password to try so you WILL need to change the password as Solarwinds123 should never be used as a pasword unless you like being hacked

        Connection con = null;
           //this sets the conection pointer to NULL so we can thest later to make sure that the conection has sucsessfully conected 

        try {


            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            /*this takes the information that gave the class and atemts to conect to the DB
            assuming that this information is corect your program is now nonected to the DB*/

            if (con != null) {
                //this statment is there to make sure that the inital conection was set

                System.out.println("Database connection is successful !!!!");
                //this lets you know that the conection was sucsesfull 

            }


        } catch (Exception e) {
            e.printStackTrace();
            //this lets you know if something went wrong and will give you info to help dbug 
      }
   }
}