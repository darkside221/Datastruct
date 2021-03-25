
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class SQLClient extends JApplet {
  // Connection to the database
  Connection connection;

  // Statement to execute SQL commands
  Statement statement;

  boolean isStandalone = false;
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jtasqlCommand = new JTextArea();
  TitledBorder titledBorder1;
  JScrollPane jScrollPane2 = new JScrollPane();
  JTextArea jtaSQLResult = new JTextArea();
  JPanel jPanel3 = new JPanel();
  JButton jbtExecuteSQL = new JButton();
  TitledBorder titledBorder2;
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  JLabel jLabel4 = new JLabel();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel6 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JTextField jtfUsername = new JTextField();
  JPanel jPanel7 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  GridLayout gridLayout2 = new GridLayout();
  JLabel jLabel3 = new JLabel();
  JPasswordField jpfPassword = new JPasswordField();
  JComboBox jcboURL = new JComboBox();
  JComboBox jcboDriver = new JComboBox();
  JPanel jPanel8 = new JPanel();
  JButton jbtConnectDB1 = new JButton();
  BorderLayout borderLayout6 = new BorderLayout();
  JLabel jlblConnectionStatus = new JLabel();
  JPanel jPanel9 = new JPanel();
  BorderLayout borderLayout7 = new BorderLayout();
  TitledBorder titledBorder3;
  TitledBorder titledBorder4;

  /** Initialize the applet */
  public void init() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  /** Component initialization */
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    titledBorder4 = new TitledBorder("Enter Database Information");
    this.setSize(new Dimension(644, 390));
    jPanel1.setLayout(borderLayout1);
    jtasqlCommand.setPreferredSize(new Dimension(57, 50));
    jScrollPane1.setVerticalScrollBarPolicy(
      JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane1.setBorder(titledBorder1);
    titledBorder1.setTitle("Enter a SQL Command");
    jbtExecuteSQL.setText("Execute SQL Command");
    jbtExecuteSQL.addActionListener(
      new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jbtExecuteSQL_actionPerformed(e);
      }
    });
    jPanel3.setLayout(borderLayout3);
    jScrollPane2.setBorder(titledBorder2);
    titledBorder2.setTitle("SQL Execution Result");
    jPanel4.setLayout(borderLayout4);
    jLabel4.setText("Password");
    gridLayout1.setRows(4);
    gridLayout1.setColumns(1);
    jPanel6.setLayout(borderLayout5);
    jPanel6.setBorder(titledBorder4);
    jLabel2.setText("Database URL");
    jPanel7.setLayout(gridLayout2);
    jLabel1.setText("JDBC Driver");
    gridLayout2.setColumns(1);
    gridLayout2.setHgap(10);
    gridLayout2.setRows(4);
    jLabel3.setText("Username");
    jcboURL.setEditable(true);
    jcboDriver.setEditable(true);
    jPanel8.setLayout(gridLayout1);
    jbtConnectDB1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jbtConnectDB1_actionPerformed(e);
      }
    });
    jbtConnectDB1.setText("Connect to Database");
    jbtConnectDB1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jbtConnectDB1_actionPerformed(e);
      }
    });
    jlblConnectionStatus.setText("No connection now");
    jPanel9.setLayout(borderLayout6);
    jPanel5.setLayout(borderLayout7);
    jPanel4.add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(jScrollPane1, BorderLayout.CENTER);
    jPanel1.add(jPanel3, BorderLayout.SOUTH);
    jPanel3.add(jbtExecuteSQL, BorderLayout.EAST);
    jPanel4.add(jPanel5, BorderLayout.WEST);
    jPanel9.add(jlblConnectionStatus, BorderLayout.CENTER);
    jPanel9.add(jbtConnectDB1, BorderLayout.EAST);
    jPanel5.add(jPanel9,  BorderLayout.SOUTH);
    jPanel5.add(jPanel6,  BorderLayout.CENTER);
    jPanel8.add(jLabel1, null);
    jPanel8.add(jLabel2, null);
    jPanel8.add(jLabel3, null);
    jPanel8.add(jLabel4, null);
    jPanel6.add(jPanel7, BorderLayout.CENTER);
    jPanel6.add(jPanel8, BorderLayout.WEST);
    jPanel7.add(jcboDriver, null);
    jPanel7.add(jcboURL, null);
    jPanel7.add(jtfUsername, null);
    jPanel7.add(jpfPassword, null);
    this.getContentPane().add(jScrollPane2, BorderLayout.CENTER);
    jScrollPane2.getViewport().add(jtaSQLResult, null);
    jScrollPane1.getViewport().add(jtasqlCommand, null);
    this.getContentPane().add(jPanel4, BorderLayout.NORTH);
    jcboURL.addItem("jdbc:odbc:exampleMDB.mdb");
    jcboURL.addItem("jdbc:oracle:thin:@liang.armstrong.edu:1521:ora9i");
    jcboDriver.addItem("sun.jdbc.odbc.JdbcOdbcDriver");
    jcboDriver.addItem("oracle.jdbc.driver.OracleDriver");
  }

  /** Main method */
  public static void main(String[] args) {
    SQLClient applet = new SQLClient();
    applet.isStandalone = true;
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("SQL Client");
    frame.getContentPane().add(applet, BorderLayout.CENTER);
    applet.init();
    applet.start();
    frame.setSize(700, 320);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation((d.width - frame.getSize().width) / 2,
      (d.height - frame.getSize().height) / 2);
    frame.setVisible(true);
  }

  /** Connect to DB */
  void jbtConnectDB1_actionPerformed(ActionEvent e) {
    // Get database information from the user input
    String driver = (String)jcboDriver.getSelectedItem();
    String url = (String)jcboURL.getSelectedItem();
    String username = jtfUsername.getText().trim();
    String password = new String(jpfPassword.getPassword());

    // Connection to the database
    try {
      Class.forName(driver);
      connection = DriverManager.getConnection(
        url, username, password);
      jlblConnectionStatus.setText("Connected to " + url);
    }
    catch (java.lang.Exception ex) {
      ex.printStackTrace();
    }
  }

  /** Execute SQL commands */
  void jbtExecuteSQL_actionPerformed(ActionEvent e) {
    if (connection == null) {
      jtaSQLResult.setText("Please connect to a database first");
      return;
    }
    else {
      String sqlCommand = jtasqlCommand.getText().trim();
      if (sqlCommand.toUpperCase().startsWith("SELECT")) {
        processSQLSelect(sqlCommand);
      }
      else {
        processSQLNonSelect(sqlCommand);
      }
    }
  }

  /** Execute SQL SELECT commands */
  private void processSQLSelect(String sqlCommand) {
    try {
      // Get a new statement for the current connection
      statement = connection.createStatement();

      // Execute a SELECT SQL command
      ResultSet resultSet = statement.executeQuery(sqlCommand);

      // Find the number of columns in the result set
      int columnCount = resultSet.getMetaData().getColumnCount();
      String row = "";
      jtaSQLResult.setText(null);

      // Display column names
      for (int i = 1; i <= columnCount; i++) {
        row += resultSet.getMetaData().getColumnName(i) + "\t";
      }

      jtaSQLResult.append(row + '\n');

      // Reset row to empty
      row = "";

      while (resultSet.next()) {
        for (int i = 1; i <= columnCount; i++) {
          row += resultSet.getString(i) + "\t";
        }

        jtaSQLResult.append(row + '\n');

        // Reset row to empty
        row = "";
      }
    }
    catch (SQLException ex) {
      jtaSQLResult.setText(ex.toString());
    }
  }

  /** Execute SQL DDL, and modification commands */
  private void processSQLNonSelect(String sqlCommand) {
    try {
      // Get a new statement for the current connection
      statement = connection.createStatement();

      // Execute a non-SELECT SQL command
      statement.executeUpdate(sqlCommand);

      jtaSQLResult.setText("SQL command executed");
    }
    catch (SQLException ex) {
      jtaSQLResult.setText(ex.toString());
    }
  }
}