import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

class GuiTry extends JFrame{
	
	public static String[] dsn = {
								"jdbc:mysql://localhost/db_java",
								"root",
								""};
	private static JFrame content = new JFrame();
	public static int counter(String sql)
	{
		ResultSetMetaData number = null;  
		int num = 0;
		try{
					ResultSet x = Query(sql);
					number = x.getMetaData();
					num = number.getColumnCount();

				}catch(Exception e)
				{
					System.out.println(e);
				}
		return num;
	}

	public static Statement Connection()
	{
		Statement stm = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con  = DriverManager.getConnection(dsn[0],dsn[1],dsn[2]);
			stm = con.createStatement();
		}catch(Exception e){
			System.out.println(e);
		}
		return stm;
	}

	public static String updateQuery(String sql)
	{
		try{
			Statement stmt = Connection();
			stmt.executeUpdate(sql);

		}catch(Exception e)
		{
			System.out.println(e);
		}
	
		return "Successfully";
	}

	public static ResultSet Query(String sql)
	{
		ResultSet rs = null;
		try{
			Statement stmt = Connection();
			rs = stmt.executeQuery(sql);
		
		}catch(Exception e)
		{
			System.out.println(e);
		}

		return rs;
	}

	public static JPanel answers_form()
	{
		JLabel answer_l[] = new JLabel[3];
			answer_l[0] = new JLabel("Question :");
			answer_l[2] = new JLabel("Description :");
			
		int number_of_questions = 0;
			try{
					String sql = "SELECT COUNT(questions.quest_id) AS number_of_questions FROM questions";
					ResultSet x = Query(sql);  
					while(x.next())
					{
						number_of_questions = x.getInt(1);
					}
				}catch(Exception e)
				{
					System.out.println(e);
				}
		 String questions[] = new String[number_of_questions];  
		 int set_q = 0;
			try{
				String sql = "SELECT quest_desc FROM questions";
				ResultSet x = Query(sql);
				while(x.next())
				{
					questions[set_q] = x.getString("quest_desc");
					set_q+=1;
				}
			}catch(Exception e)
			{
				System.out.println(e);
			}
		 	
      
   	    JComboBox quest = new JComboBox(questions); 
		JTextField answer_t[] = new JTextField[2];
			answer_t[0] = new JTextField();

		JButton submit_btn = new JButton("Add Choices");
			submit_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		         String q = " ";
		         	if (quest.getSelectedIndex() != -1) {                     
		               q = (String)quest.getItemAt
		                    (quest.getSelectedIndex());             
		            }        
		                
		           System.out.println(q+"--"+answer_t[0].getText());
		         }          
		      });

			
		JPanel answer = new JPanel(new GridLayout(2,1));
			int x =100,y=100,w=120,h=30;
			answer_l[0].setBounds(x,y,w,h);	quest.setBounds(x+200,y,w,h);
			answer_l[2].setBounds(x,y+=70,w,h); answer_t[0].setBounds(x+200,y,w,h);
			submit_btn.setBounds(x,y+=70,w,h);

			answer.add(answer_l[0]);answer.add(quest);
			answer.add(answer_l[2]);answer.add(answer_t[0]);	
			answer.add(submit_btn);
			answer.setLayout(null);

		return answer;
	}

	public static JPanel questions_form()
	{
		JLabel question_l[] = new JLabel[3];
			question_l[0] = new JLabel("Lesson Title :");
			question_l[1] = new JLabel("Question :");
			question_l[2] = new JLabel("key :");
			
		int number_of_lessons = 0;
			try{
				String sql = "SELECT COUNT(lessons.subject_id) AS number_of_lessons FROM lessons";
				ResultSet x = Query(sql);  
				while(x.next())
				{
					number_of_lessons = x.getInt(1);
				}
				System.out.println(number_of_lessons);
				}catch(Exception e)
				{
					System.out.println(e);
				}
				System.out.println(number_of_lessons);
		 String lessons[] = new String[number_of_lessons];  
		 int set_less = 0;
			try{
				String sql = "SELECT title FROM lessons";
				ResultSet x = Query(sql);
				while(x.next())
				{
					lessons[set_less] = x.getString("title");
					System.out.println(x.getString("title"));
					set_less+=1;
				}
			}catch(Exception e)
			{
				System.out.println(e);
			}
		 	
      
   	    JComboBox lesson = new JComboBox(lessons);  
		JTextField question_t[] = new JTextField[2];
			question_t[0] = new JTextField();
			question_t[1] = new JTextField();

		JButton submit_btn = new JButton("New Question");
			submit_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		         String subj = " ";
		         	if (lesson.getSelectedIndex() != -1) {                     
		               subj = (String)lesson.getItemAt
		                    (lesson.getSelectedIndex());             
		            }             
		           System.out.println(subj+"--"+question_t[0].getText()+"--"+question_t[1].getText());
		         }          
		      });

		JPanel question = new JPanel();
			int x = 150,y = 100,w = 100,h = 30;
			question_l[0].setBounds(x,y,w,h);		lesson.setBounds(x+120,y,w+30,h);
			question_l[1].setBounds(x,y+=70,w,h);	question_t[0].setBounds(x+120,y,w+30,h);
			question_l[2].setBounds(x,y+=70,w,h);	question_t[1].setBounds(x+120,y,w+30,h);
			submit_btn.setBounds(x,y+=70,w+30,h);

			question.add(question_l[0]); question.add(lesson);
			question.add(question_l[1]);question.add(question_t[0]);	
			question.add(question_l[2]);question.add(question_t[1]);	
			question.add(submit_btn);
			question.setLayout(null);

		return question;
	}

	// public static Vector subjectV()
	// {
	// 	Vector subjects = new Vector();
		
	// 	try{
	// 			String sql = "SELECT subject_desc FROM subjects";
	// 			ResultSet x = Query(sql);
	// 			while(x.next())
	// 			{

	// 				subjects.addElement(x.getString("subject_desc"));
	// 				System.out.println(x.getString("subject_desc"));
	// 			}
	// 		}catch(Exception e)
	// 		{
	// 			System.out.println(e);
	// 		}
			
	// 		System.out.println(x.elementAt(0));
	// 		x.remove(0);
	// 		System.out.println(x.size());
	// }
	public static JPanel lessons_form()
	{
	// 	JLabel lessons_l[] = new JLabel[3];
	// 		lessons_l[0] = new JLabel("Subject Name:");
	// 		lessons_l[1] = new JLabel("Title :");
	// 		lessons_l[2] = new JLabel("Content :");
			
	// 	int number_of_subjects = 0;
	// 		try{
	// 				String sql = "SELECT COUNT(subjects.subject_id) AS number_of_subjects FROM subjects";
	// 				ResultSet x = Query(sql);  
	// 				while(x.next())
	// 				{
	// 					number_of_subjects = x.getInt(1);
	// 				}
	// 				System.out.println(number_of_subjects);
	// 			}catch(Exception e){
	// 					System.out.println(e);
	// 			}
	// 	 String subjects[] = new String[number_of_subjects];  
	// 	 int set_subs = 0;
		 
 //   	    JComboBox subject = new JComboBox(subjects);  
	// 	JTextField lessons_t[] = new JTextField[2];
	// 		lessons_t[0] = new JTextField();
	// 		lessons_t[1] = new JTextField();

	// 	JButton submit_btn = new JButton("New Lesson");
	// 		submit_btn.addActionListener(new ActionListener() {
	// 	         public void actionPerformed(ActionEvent e) {
	// 	         String subj = " ";
	// 	         	if (subject.getSelectedIndex() != -1) {                     
	// 	               subj = (String)subject.getItemAt
	// 	                    (subject.getSelectedIndex());             
	// 	            }             
	// 	           System.out.println(subj+"--"+lessons_t[0].getText()+"--"+lessons_t[1].getText());
	// 	         }          
	// 	      });
	
		
		JPanel lessons = new JPanel();
	// 		int x = 150,y = 100,w = 100,h = 30;
	// 		lessons_l[0].setBounds(x,y,w,h);		subject.setBounds(x+120,y,w+30,h);
	// 		lessons_l[1].setBounds(x,y+=70,w,h);	lessons_t[0].setBounds(x+120,y,w+30,h);
	// 		lessons_l[2].setBounds(x,y+=70,w,h);	lessons_t[1].setBounds(x+120,y,w+30,h);
	// 		submit_btn.setBounds(x,y+=70,w+30,h);

	// 		lessons.add(lessons_l[0]);
	// 		lessons.add(subject);
	// 		lessons.add(lessons_l[1]);
	// 		lessons.add(lessons_t[0]);	
	// 		lessons.add(lessons_l[2]);
	// 		lessons.add(lessons_t[1]);	
	// 		lessons.add(submit_btn);
			
	// 		lessons.setLayout(null);

		return lessons;
	}

	public static JPanel subjects_form()
	{
		JLabel subjects_l[] = new JLabel[1];
			subjects_l[0] = new JLabel("Subject Name:");

		JTextField subjects_t[] = new JTextField[subjects_l.length];
			subjects_t[0] = new JTextField();

		JButton submit_btn = new JButton("New Subject");
		//submit_btn.setBounds(10,10,250,100);
		//submit_btn.setFont(new Font("Arial", Font.PLAIN, 10));
			submit_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            System.out.println(subjects_t[0].getText());
		            try{
						updateQuery("INSERT INTO subjects(subject_desc) VALUES('"+subjects_t[0].getText()+"')");  
						subjects_t[0].setText("");
					}catch(Exception es){
							System.out.println(es);
					}
		         }          
		      });

		JPanel subjects = new JPanel();
			int x = 100, y = 100, w = 150, h = 40;
			subjects_l[0].setBounds(x,y,w,h); subjects_t[0].setBounds(x*2+50,y,w,h);
			submit_btn.setBounds(x,y+70,w,h);	
			subjects.add(subjects_l[0]);
			subjects.add(subjects_t[0]);	
			subjects.add(submit_btn);
			subjects.setLayout(null);

		return subjects;
	}

	public static JPanel registration_form()
	{
		JLabel reg_l[] = new JLabel[4];
			reg_l[0] = new JLabel("Username :");
			reg_l[1] = new JLabel("Password :");
			reg_l[2] = new JLabel("Firstname :");
			reg_l[3] = new JLabel("Lastname :");

		JTextField reg_t[] = new JTextField[reg_l.length];
			reg_t[0] = new JTextField();
			reg_t[1] = new JTextField();
			reg_t[2] = new JTextField();
			reg_t[3] = new JTextField();

		JButton submit_btn = new JButton("REGISTER");
		//submit_btn.setBounds(10,10,250,100);
		//submit_btn.setFont(new Font("Arial", Font.PLAIN, 10));
			submit_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            System.out.println(reg_t[0].getText()+"--"+reg_t[1].getText()+"--"+reg_t[0].getText()+"--"+reg_t[1].getText());
		         }          
		      });		
		
		JPanel reg = new JPanel();
			reg_l[0].setBounds(150,100,70,30); reg_t[0].setBounds(250,100,120,30);
			reg_l[1].setBounds(150,170,70,30); reg_t[1].setBounds(250,170,120,30);
			reg_l[2].setBounds(150,240,70,30); reg_t[2].setBounds(250,240,120,30);
			reg_l[3].setBounds(150,310,70,30); reg_t[3].setBounds(250,310,120,30);
			submit_btn.setBounds(150,380,100,30);
			
			reg.add(reg_l[0]);reg.add(reg_t[0]);
			reg.add(reg_l[1]);reg.add(reg_t[1]);
			reg.add(reg_l[2]);reg.add(reg_t[2]);
			reg.add(reg_l[3]);reg.add(reg_t[3]);
			reg.add(submit_btn);

			reg.setLayout(null);

		return reg;
	}

	public static JPanel login_form()
	{

		JLabel login_l[] = new JLabel[3];
			login_l[0] = new JLabel("Username :");
			login_l[1] = new JLabel("Password :");
			login_l[2] = new JLabel(" ");
		JTextField login_t[] = new JTextField[2];
			login_t[0] = new JTextField();
			login_t[1] = new JTextField();

		JButton submit_btn = new JButton("LOGIN");
		submit_btn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	             try{

					String sql = "SELECT COUNT(users.username) AS result_num,username,`password`,fname,lname,user_type FROM users WHERE username = '"+login_t[0].getText()+"' AND password = '"+login_t[1].getText()+"'";
					ResultSet x = Query(sql);
					while(x.next())
						{
							if(x.getInt(1) != 0)
							{
									login_l[2].setText("Welcome to Welcome!");
									if(x.getString("user_type") == "Admin")
									{
										System.out.println(x.getString("user_type"));
									}else{
										System.out.println(x.getString("user_type"));
									}
							}else{
								login_l[2].setText("Invalid Username or Password!");
								login_t[0].setText("");
								login_t[1].setText("");
							}
						}
				
	            }catch(Exception ex)
	            {
	            	System.out.println(ex);
	            }
	         }          
	      });

		
		JPanel login = new JPanel();
			
			login_l[2].setBounds(150,200,200,200); 
			login_l[0].setBounds(100,100,70,30); login_t[0].setBounds(220,100,120,30);
			login_l[1].setBounds(100,170,70,30); login_t[1].setBounds(220,170,120,30);
			submit_btn.setBounds(100,240,70,30);

			login.add(login_l[2]);
			login.add(login_l[0]);login.add(login_t[0]);
			login.add(login_l[1]);login.add(login_t[1]);
			login.add(submit_btn);
			


			login.setLayout(null);

		return login;
	}

	GuiTry(JPanel x)
	{
		
		content.getContentPane().add(x);
		content.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		content.setSize(500,500);
		content.setVisible(true);
	}

	public static JPanel front()
	{
		JFrame collections[] = new JFrame[2];
			collections[0] = new JFrame("Login");
			collections[0].getContentPane().add(login_form());
			collections[0].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			collections[0].setSize(500,500);
			collections[0].setVisible(false);
			collections[1] = new JFrame("Registration");
			collections[1].getContentPane().add(registration_form());
			collections[1].setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			collections[1].setSize(500,500);
			collections[1].setVisible(false);
		JLabel front_l = new JLabel("Welcome to DarkJava");
		JButton login_btn = new JButton("Login");
		login_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            System.out.println(login_btn.getText());
		            collections[0].setVisible(true); collections[1].setVisible(false);
		            content.setVisible(false);
		         }       
			});

		JButton register_btn = new JButton("Register");
		register_btn.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            System.out.println(register_btn.getText());
	           collections[1].setVisible(true); collections[0].setVisible(false); 
	           content.setVisible(false);
	         }          
	       });
		
		JPanel login = new JPanel();
			
			front_l.setBounds(180,50,200,50);
			login_btn.setBounds(100,250,100,30);
			register_btn.setBounds(300,250,100,30);


			login.add(front_l);
			login.add(login_btn);
			login.add(register_btn);
			
			login.setLayout(null);
		
		return login;
	}

	public static JPanel admin_dashboard()
	{
		JTabbedPane controls = new JTabbedPane();
		controls.addTab("Subjects",null,subjects_form(),"Lessons");
		controls.addTab("Lessons",null,lessons_form(),"Lessons");
		controls.addTab("Questions",null,questions_form(),"Questions");
		controls.addTab("Answer",null,answers_form(),"Answer");
		JPanel panel  = new JPanel ();
		panel.add(controls);
		panel.setLayout(new GridLayout(1,1));
		return panel;
	}

	// show specific frame using frame id
	// public static void ShowHide(int frame_id)
	// {
	// 	for (int x= 0;x < number_of_frames ; x++ ) {
	// 		if(x == frame_id)
	// 		{
	// 			framep[x].setVisible(true);
	// 		}else{
	// 			framep[x].setVisible(false);
	// 		}
	// 	}
		
	// }
	public static void main(String[] args) 
	{	
			 //   Scanner sc = new Scanner(System.in);
    // 		 	String subject = sc.next();
			// new GuiTry(front());
				// System.out.println(subject);
		Vector x = new Vector();
		x.addElement("asd");		
		x.addElement("asd");
		x.addElement("asd");
		for (int y=0;y < x.size() ; y++) {
			System.out.println(x.elementAt(y));	
		}

		for (int xx=0;xx > x.size() ; xx++) {
			x.remove(xx);
		}

		System.out.println(x.size());
		
		for (int v=0;v < x.size() ; v++) {
			System.out.println(x.elementAt(v));	
		}//	new GuiTry(admin_dashboard());
			// new GuiTry(answers_form());
			// new GuiTry(questions_form());
			// new GuiTry(lessons_form());
			// new GuiTry(login_form());
			// new GuiTry(registration_form());
		}
}