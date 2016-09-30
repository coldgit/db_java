import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.Event.*;
import javax.swing.*;


class pQuest extends JFrame
{
	public static String[] dsn = {"com.mysql.jdbc.Driver","jdbc:mysql://localhost/q_db","root"," "};
	pQuest()
	{
			Query();
	}

	public static void Query()
	{
		Connection con = null;
		Statement stm = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con  = DriverManager.getConnection(dsn[1],dsn[2],dsn[3]);
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
		new pQuest();
		
		// InetAddress x =  InetAddress.getLocalHost();
		 System.out.println("dex");//dsn[0]);
	}

}