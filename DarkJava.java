import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

class DarkJava extends JFrame
{
	DarkJava(JPanel x,int number)
	{
		JFrame container[] = new JFrame[3];
		container[number] = new JFrame("Main Container[number]");
		container[number].getContentPane().add(x);
		container[number].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		container[number].setSize(1366,768);
		container[number].setVisible(true);

	}

	public static void main(String[] args) {
		new DarkJava(new JPanel(),0);
	}
}