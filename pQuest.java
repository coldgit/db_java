
// to compile -- >javac -cp "mysql-connector-java-5.1.39\mysql-connector-java-5.1.39-bin.jar"; pQuest.java
//to run      -->java -cp "mysql-connector-java-5.1.39\mysql-connector-java-5.1.39-bin.jar"; pQuest

import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class pQuest extends JFrame
{

	public static String[] dsn = {
								"jdbc:mysql://localhost/db_java",
								"root",
								""};

	public pQuest(JPanel panel)
	{
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Exit the program when the close_window button clicked
		setTitle("Questioner!"); // "super" JFrame sets title
		setSize(300, 150); // "super" JFrame sets initial size (or pack())
		setVisible(true); // "super" JFrame shows

		//Query();
	}

	public static void Query()
	{
		Connection con = null;
		Statement stm = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con  = DriverManager.getConnection(dsn[0],dsn[1],dsn[2]);
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(x);

			System.out.println("connected!",rs);
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}



	public static void main(String[] args)
	{
		new pQuest(new JPanel(new GridLayout(12,1)));
	}

}