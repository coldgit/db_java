
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
	static Vector rows = new Vector();
	public static void Query(String sql)
	{
	try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con  = DriverManager.getConnection(dsn[0],dsn[1],dsn[2]);
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stm.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			Vector memories = new Vector();
			int col = rsmd.getColumnCount();
			while(rs.next())
		    {
		    	memories.clear();
     			for(int inner = 0; inner < col; inner++)
 				{
 					memories.add(rs.getString(inner+1));
 				}
 				// rows(memories);
 				System.out.println(rows(memories));
		    }
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static Vector rows(Vector x)
	{
		rows.add(x);
		x.clear();
		return rows;
	}
	public static void main(String[] args)
	{
		Query("SELECT user_id,username,password FROM users");
		System.out.println(rows);
	}

}