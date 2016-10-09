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

	public static String updateQuery()
	{
		ResultSet rs = null;
		try{
			Statement stmt = Connection();
			rs = stmt.executeUpdate(sql);

		}catch(Exception e)
		{
			System.out.println(e);
		}
		String done = "true";
		return done;
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
			answer_l[1] = new JLabel("Answer type :");
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
   	    int number_of_answer_types = 0;
			try{
				String sql = "SELECT COUNT(answer_types.answer_type_id) AS number_of_questions FROM answer_types";
					ResultSet x = Query(sql);  
					while(x.next())
					{
						number_of_answer_types = x.getInt(1);
					}
			}catch(Exception e)
				{
					System.out.println(e);
				}
		 String answer_types[] = new String[number_of_answer_types];  
		 int set_ans = 0;
			try{
				String sql = "SELECT answer_type_desc FROM answer_types";
				ResultSet x = Query(sql);
				while(x.next())
				{
					answer_types[set_ans] = x.getString("answer_type_desc");
					set_ans+=1;
				}
			}catch(Exception e)
			{
				System.out.println(e);
			}
		 	
      
   	    JComboBox ans = new JComboBox(answer_types);  
		JTextField answer_t[] = new JTextField[2];
			answer_t[0] = new JTextField();

		JButton submit_btn = new JButton("Add New Lesson");
			submit_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		         String q = " ";
		         	if (quest.getSelectedIndex() != -1) {                     
		               q = (String)quest.getItemAt
		                    (quest.getSelectedIndex());             
		            }        
		           String answer = " ";
		         	if (ans.getSelectedIndex() != -1) {                     
		               answer = (String)ans.getItemAt
		                    (ans.getSelectedIndex());             
		            }       
		           System.out.println(q+"--"+answer+"--"+answer_t[0].getText());
		         }          
		      });

		JPanel answer_data = new JPanel(new GridLayout(answer_l.length,answer_l.length));
			answer_data.add(answer_l[0]);
			answer_data.add(quest);
			answer_data.add(answer_l[1]);
			answer_data.add(ans);
			answer_data.add(answer_l[2]);
			answer_data.add(answer_t[0]);	
		
		JPanel answer = new JPanel(new GridLayout(2,1));
			answer.add(answer_data);
			answer.add(submit_btn);
			answer.setLocation(150,150);
			answer.setSize(400,100);
			answer.setVisible(true);

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

		JButton submit_btn = new JButton("Add New Lesson");
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

		JPanel question_data = new JPanel(new GridLayout(question_l.length,question_l.length));
			question_data.add(question_l[0]);
			question_data.add(lesson);
			question_data.add(question_l[1]);
			question_data.add(question_t[0]);	
			question_data.add(question_l[2]);
			question_data.add(question_t[1]);	
		
		JPanel question = new JPanel(new GridLayout(2,1));
			question.add(question_data);
			question.add(submit_btn);
			question.setLocation(150,150);
			question.setSize(400,100);
			question.setVisible(true);

		return question;
	}

	public static JPanel lessons_form()
	{
		JLabel lessons_l[] = new JLabel[3];
			lessons_l[0] = new JLabel("Subject Name:");
			lessons_l[1] = new JLabel("Title :");
			lessons_l[2] = new JLabel("Content :");
			
		int number_of_subjects = 0;
		try{
				String sql = "SELECT COUNT(subjects.subject_id) AS number_of_subjects FROM subjects";
				ResultSet x = Query(sql);  
				while(x.next())
				{
					number_of_subjects = x.getInt(1);
				}
				System.out.println(number_of_subjects);
				}catch(Exception e)
				{
					System.out.println(e);
				}
		 String subjects[] = new String[number_of_subjects];  
		 int set_subs = 0;
			try{
				String sql = "SELECT subject_desc FROM subjects";
				ResultSet x = Query(sql);
				while(x.next())
				{

					subjects[set_subs] = x.getString("subject_desc");
					System.out.println(x.getString("subject_desc"));
					set_subs+=1;
				}
				
			}catch(Exception e)
			{
				System.out.println(e);
			}
		 
   	    JComboBox subject = new JComboBox(subjects);  
		JTextField lessons_t[] = new JTextField[2];
			lessons_t[0] = new JTextField();
			lessons_t[1] = new JTextField();

		JButton submit_btn = new JButton("Add New Lesson");
			submit_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		         String subj = " ";
		         	if (subject.getSelectedIndex() != -1) {                     
		               subj = (String)subject.getItemAt
		                    (subject.getSelectedIndex());             
		            }             
		           System.out.println(subj+"--"+lessons_t[0].getText()+"--"+lessons_t[1].getText());
		         }          
		      });

		JPanel lessons_data = new JPanel(new GridLayout(lessons_l.length,lessons_l.length));
			lessons_data.add(lessons_l[0]);
			lessons_data.add(subject);
			lessons_data.add(lessons_l[1]);
			lessons_data.add(lessons_t[0]);	
			lessons_data.add(lessons_l[2]);
			lessons_data.add(lessons_t[1]);	
		
		JPanel lessons = new JPanel(new GridLayout(2,1));
			lessons.add(lessons_data);
			lessons.add(submit_btn);
			lessons.setLocation(150,150);
			lessons.setSize(400,100);
			lessons.setVisible(true);

		return lessons;
	}

	public static JPanel subjects_form()
	{
		JLabel subjects_l[] = new JLabel[1];
			subjects_l[0] = new JLabel("Subject Name:");

		JTextField subjects_t[] = new JTextField[subjects_l.length];
			subjects_t[0] = new JTextField();

		JButton submit_btn = new JButton("subjectsISTER");
		//submit_btn.setBounds(10,10,250,100);
		//submit_btn.setFont(new Font("Arial", Font.PLAIN, 10));
			submit_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            System.out.println(subjects_t[0].getText()+"--"+subjects_t[1].getText()+"--"+subjects_t[0].getText()+"--"+subjects_t[1].getText());
		         }          
		      });

		JPanel subjects_data = new JPanel(new GridLayout(subjects_l.length,subjects_l.length));
			subjects_data.add(subjects_l[0]);
			subjects_data.add(subjects_t[0]);	
		
		JPanel subjects = new JPanel(new GridLayout(2,1));
			subjects.add(subjects_data);
			subjects.add(submit_btn);
			subjects.setLocation(150,150);
			subjects.setSize(400,100);
			subjects.setVisible(true);

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

		JPanel reg_data = new JPanel(new GridLayout(reg_l.length,reg_l.length));
			reg_data.add(reg_l[0]);
			reg_data.add(reg_t[0]);

			reg_data.add(reg_l[1]);
			reg_data.add(reg_t[1]);
			
			reg_data.add(reg_l[2]);
			reg_data.add(reg_t[2]);
			
			reg_data.add(reg_l[3]);
			reg_data.add(reg_t[3]);
		
		JPanel reg = new JPanel(new GridLayout(2,1));
			reg.add(reg_data);
			reg.add(submit_btn);
			reg.setLocation(150,150);
			reg.setSize(400,100);
			reg.setVisible(true);

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

		JPanel login_data = new JPanel(new GridLayout(2,2));
			login_data.add(login_l[0]);
			login_data.add(login_t[0]);
			login_data.add(login_l[1]);
			login_data.add(login_t[1]);
		
		JPanel login = new JPanel(new GridLayout(3,1));
			login.add(login_l[2]);
			login.add(login_data);
			login.add(submit_btn);
			login.setLocation(150,150);
			login.setSize(300,100);
			login.setVisible(true);

		return login;
	}

	GuiTry(JPanel x)
	{
		
		Container c = this.getContentPane();

		c.setLayout(null);
		c.add(x);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
	   
	}



	// public String void(int number_of_choices, )
	// {

	// }
	
	public static String Encryptioner(String s)
	{
	    String pat = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@#$%^&*()<>{}|:/+_-";
	    String key = "+_-tklpqrsmn8%^o4567&*yEF(uvwxGH390IYZ12@#DJK$zABCLMXOPNTUVWQR/[]S{}|:)<cde>fghijab";
	    String es = " ";
		    if(s.charAt(0) == '!')
		    {

		      for (int input=0;input < s.length() ; input++ )
		      {
		          for (int pattern= 0 ;pattern < pat.length() ;pattern++ )
		          {
		              if(s.charAt(input) == pat.charAt(pattern))
		              {
		                  es += key.charAt(pattern);
		              }
		          }
		      }
		      return es;

		    }else{

		        for (int input=0;input < s.length() ; input++ )
		        {
		            for (int pattern= 0 ;pattern < key.length() ;pattern++ )
		            {
		                if(s.charAt(input) == key.charAt(pattern))
		                {
		                    es += pat.charAt(pattern);
		                }
		            }
		        }
		        return es;

		      }
		}

		// public static JPanel Admin()
		// {
		// 	JLabel admin_l[] = new JLabel[2];
		// 	admin_l[0] = new JLabel("Username :");
		// 	admin_l[1] = new JLabel("Password :");

		// 	JTextField admin_t[] = new JTextField[2];
		// 		admin_t[0] = new JTextField();
		// 		admin_t[1] = new JTextField();

		// 	JButton submit_btn = new JButton("admin");
		// 	submit_btn.addActionListener(new ActionListener() {
		//          public void actionPerformed(ActionEvent e) {
		//             System.out.println(admin_t[0].getText()+"--"+admin_t[1].getText());
		//          }          
		//       });

		// 	JPanel admin_data = new JPanel(new GridLayout(2,2));
		// 		admin_data.add(admin_l[0]);
		// 		admin_data.add(admin_t[0]);
		// 		admin_data.add(admin_l[1]);
		// 		admin_data.add(admin_t[1]);
			
		// 	JPanel admin = new JPanel(new GridLayout(2,1));
		// 		admin.add(admin_data);
		// 		admin.add(submit_btn);
		// 		admin.setLocation(150,150);
		// 		admin.setSize(300,100);
		// 		admin.setVisible(true);

		// 	return admin;
		// }
		public static JPanel front()
		{
			JLabel front_l = new JLabel("Welcome to DarkJava");
			JButton login_btn = new JButton("Login");
			login_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            System.out.println(login_btn.getText());
		            new GuiTry(login_form());
		         }          
		      });
			JButton register_btn = new JButton("Register");
			register_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		            System.out.println(register_btn.getText());
		            new GuiTry(registration_form());
		         }          
		      });
			JPanel choice_data = new JPanel(new GridLayout(1,2));
				choice_data.add(login_btn);
				choice_data.add(register_btn);
			
			JPanel login = new JPanel(new GridLayout(2,1));
				login.add(front_l);
				login.add(choice_data);
				login.setLocation(150,150);
				login.setSize(300,100);
				login.setVisible(true);

			return login;
		}
		public static void main(String[] args)
		{	
			 //   Scanner sc = new Scanner(System.in);
    // 		 	String subject = sc.next();
			new GuiTry(front());
				// System.out.println(subject);
			//new GuiTry(answers_form());
		//	new GuiTry(questions_form());
			//new GuiTry(lessons_form());
			//new GuiTry(login_form());
			//new GuiTry(registration_form());
		}
	}