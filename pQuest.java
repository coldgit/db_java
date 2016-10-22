
// to compile -- >javac -cp "mysql-connector-java-5.1.39\mysql-connector-java-5.1.39-bin.jar"; pQuest.java
//to run      -->java -cp "mysql-connector-java-5.1.39\mysql-connector-java-5.1.39-bin.jar"; pQuest

import java.util.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class pQuest extends JFrame
{
	public static String[] dsn = {"jdbc:mysql://localhost:3306/db_java","root",""};

	public static void Query(String sql)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con  = DriverManager.getConnection(dsn[0],dsn[1],dsn[2]);
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stm.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int col = rsmd.getColumnCount();
			Vector memories = new Vector();
			final Vector rows = new Vector();
			int outer=0;
			while(rs.next())
		    {
     			for(int inner = 0; inner < col; inner++)
 				{
 					memories.add(rs.getString(inner+1));
 				}
 				rows.add(memories);
 				System.out.println("Memory to DELETE:"+rows.get(0));
 				memories.clear();
 				System.out.println("Memory DELETED:"+rows.get(0));
		    }
			System.out.println("Memories After Being Removed! : "+memories.size());
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public static void main(String[] args)
	{
		Query("SELECT user_id,username,password FROM users");
	}

}