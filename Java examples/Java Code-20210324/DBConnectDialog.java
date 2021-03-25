package chapter22;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class DBConnectDialog extends JDialog {
  // Connection to the database
  private java.sql.Connection connection;

  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  FlowLayout flowLayout1 = new FlowLayout();
  JButton jbtConnect = new JButton();
  JButton jbtCancel = new JButton();
  TitledBorder titledBorder1;
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  GridLayout gridLayout2 = new GridLayout();
  JComboBox jcboDriver = new JComboBox();
  JComboBox jcboURL = new JComboBox();
  JTextField jtfUsername = new JTextField();
  JPasswordField jpfPassword = new JPasswordField();

  /**Construct a dialog with specified frame, title, and modal*/
  public DBConnectDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**Default constructor*/
  public DBConnectDialog() {
    this(null, "", false);
  }

  void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    panel1.setLayout(borderLayout1);
    jPanel2.setLayout(flowLayout1);
    flowLayout1.setAlignment(2);
    jbtConnect.setText("Connect");
    jbtConnect.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        jbtConnect_actionPerformed(e);
      }
    });
    jbtCancel.setText("Cancel");
    jbtCancel.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        jbtCancel_actionPerformed(e);
      }
    });
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(borderLayout2);
    titledBorder1.setTitle("Enter Database Information");
    jPanel3.setLayout(gridLayout1);
    jLabel1.setText("JDBC Driver");
    jLabel2.setText("Database URL");
    jLabel3.setText("Username");
    jLabel4.setText("Password");
    gridLayout1.setColumns(1);
    gridLayout1.setRows(4);
    jPanel4.setLayout(gridLayout2);
    gridLayout2.setColumns(1);
    gridLayout2.setHgap(10);
    gridLayout2.setRows(4);
    this.setTitle("Connect to a database");
    this.setModal(true);
    jcboDriver.setEditable(true);
    jcboURL.setEditable(true);
    getContentPane().add(panel1);
    panel1.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel3, BorderLayout.WEST);
    jPanel3.add(jLabel1, null);
    jPanel3.add(jLabel2, null);
    jPanel3.add(jLabel3, null);
    jPanel3.add(jLabel4, null);
    jPanel1.add(jPanel4, BorderLayout.CENTER);
    jPanel4.add(jcboDriver, null);
    jPanel4.add(jcboURL, null);
    jPanel4.add(jtfUsername, null);
    jPanel4.add(jpfPassword, null);
    panel1.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jbtConnect, null);
    jPanel2.add(jbtCancel, null);

    // Add items to the combo boxes
    jcboDriver.addItem("sun.jdbc.odbc.JdbcOdbcDriver");
    jcboURL.addItem("jdbc:odbc:LiangBookDB_MDB");
    jcboDriver.addItem("oracle.jdbc.driver.OracleDriver");
    jcboURL.addItem
      ("jdbc:oracle:thin:@liang.armstrong.edu:1521:test");
    // Oracle Type 2 JDBC Connection
    jcboURL.addItem("jdbc:oracle:oci8:@liang");
    jcboDriver.addItem(
      "com.borland.datastore.jdbc.DataStoreDriver");
    jcboURL.addItem
      ("jdbc:borland:dslocal:C:\\radjb\\LiangBookDB.jds");
    jcboDriver.addItem("interbase.interclient.Driver");
    jcboURL.addItem
      ("jdbc:interbase://localhost/C:/LiangBook/LiangBookDB.gdb");

    /** Username and password for Interbase SYSDBA/masterkey*/
    /** Username and password for Oracle scott/tiger*/
    /** Username and password for JDataStore system/manager*/
  }

  /**Handle the Connect button*/
  void jbtConnect_actionPerformed(ActionEvent e) {
    // Get database information from the user input
    String driver = (String)jcboDriver.getSelectedItem();
    String url = (String)jcboURL.getSelectedItem();
    String username = jtfUsername.getText().trim();
    String password = new String(jpfPassword.getPassword());

    // Connection to the database
    try {
      Class.forName(driver);
      System.out.println("Driver " + driver + "loaded\n");
      connection = DriverManager.getConnection(
        url, username, password);
      System.out.println("Connected to " + url + '\n');
      setVisible(false);
    }
    catch (java.lang.Exception ex) {
      ex.printStackTrace();
    }
  }

  /**Handle the Cancel button*/
  void jbtCancel_actionPerformed(ActionEvent e) {
    // Close the dialog box
    setVisible(false);
  }

  /**Return connection*/
  public java.sql.Connection getConnection() {
    return connection;
  }
}