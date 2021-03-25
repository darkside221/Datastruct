//package jdbc3;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class FindGradeUsingPreparedStatement extends JApplet {
  boolean isStandalone = false;
  JTextField jtfSSN = new JTextField();
  JTextField jtfCourseId = new JTextField();
  JButton jbtShowGrade = new JButton();

  // PreparedStatement for executing queries
  PreparedStatement pstmt;

  /** Initialize the applet */
  public void init() {
    // Initialize database connection and create a Statement object
    initializeDB();

    jtfSSN.setColumns(9);
    jtfCourseId.setColumns(5);
    jbtShowGrade.setText("Show Grade");
    jbtShowGrade.addActionListener(
      new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jbtShowGrade_actionPerformed(e);
      }
    });

    JPanel jPanel1 = new JPanel();
    jPanel1.add(new JLabel("SSN"));
    jPanel1.add(jtfSSN);
    jPanel1.add(new JLabel("Course ID"));
    jPanel1.add(jtfCourseId);
    jPanel1.add(jbtShowGrade);

    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
  }

  public void initializeDB() {
    try {
      // Load the JDBC driver
      // Class.forName("oracle.jdbc.driver.OracleDriver");
      // Use the following driver if you access an ODBC data source
      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
      System.out.println("Driver loaded");

      // Establish a connection
//      Connection connection = DriverManager.getConnection
//        ("jdbc:oracle:thin:@liang.armstrong.edu:1521:liangora9i",
//         "scott", "tiger");
      // Establish connection to a MS Access DB
      Connection connection = DriverManager.getConnection
        ("jdbc:odbc:exampleMDB.mdb");
      System.out.println("Database connected");
// Notice two occurances of "?" in the statement below, see lines 93-94 below !!
      String queryString = "select firstName, mi, " +
        "lastName, title, grade from Student, Enrollment, Course " +
        "where Student.ssn = ? and Enrollment.courseId = ? " +
        "and Enrollment.courseId = Course.courseId";

      // Create a statement
      pstmt = connection.prepareStatement(queryString);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /** Main method */
  public static void main(String[] args) {
    FindGrade applet = new FindGrade();
    applet.isStandalone = true;
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Find Grades");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(380, 80);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }

  void jbtShowGrade_actionPerformed(ActionEvent e) {
    String ssn = jtfSSN.getText();
    String courseId = jtfCourseId.getText();
    try {
      pstmt.setString(1, ssn);   // corresponds to the first ? above
      pstmt.setString(2, courseId);//                 second ? above !!
      ResultSet rset = pstmt.executeQuery();

      if (rset.next()) {
        String lastName = rset.getString(1);
        String mi = rset.getString(2);
        String firstName = rset.getString(3);
        String title = rset.getString(4);
        String grade = rset.getString(5);

        // Display result in a dialog box
        JOptionPane.showMessageDialog(null, firstName + " " + mi +
          " " + lastName + "'s grade on course " + title + " is " +
          grade);
      }
      else {
        // Display result in a dialog box
        JOptionPane.showMessageDialog(null, "Not found");
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}