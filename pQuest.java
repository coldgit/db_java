// to compile -- >javac -cp "mysql-connector-java-5.1.39\mysql-connector-java-5.1.39-bin.jar"; pQuest.java
//to run      -->java -cp "mysql-connector-java-5.1.39\mysql-connector-java-5.1.39-bin.jar"; pQuest
import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.Event.*;
import javax.swing.*;


class pQuest extends JFrame
{
	public static String[] dsn = {
								"jdbc:mysql://localhost/db_java",
								"root",
								""};
	private static JPanel[] panels = new JPanel[10]; 
	public pQuest(JPanel panel)
	{
		Container cp = this.getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Exit the program when the close_window button clicked
		setTitle("......"); // "super" JFrame sets title
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
			//stm.executeQuery(x);
			System.out.println("connected!");
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}



	public static void main(String[] args)
	{
		for(int x = 0 ; x < 10; x++)
		{
			String y = "hello";
		new pQuest(panels[x].add(new JButton(y)));
		}
		// InetAddress x =  InetAddress.getLocalHost();
		 System.out.println("dex");//dsn[0]);
	}

}