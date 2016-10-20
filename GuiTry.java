import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;
import java.util.*;
import java.sql.*;

class GuiTry extends JFrame{
	
	public static String[] dsn = {
								"jdbc:mysql://localhost/db_java",
								"root",
								""};
	private static JFrame content = new JFrame(),admin_frame = new JFrame(),teacher_frame = new JFrame(), student_frame = new JFrame();
	static Vector setComboBox = new Vector(),
					  subjectV = new Vector(),
					   lessonV = new Vector(),
					 studentsV = new Vector(),
					  teacherV = new Vector(),
					  sectionV = new Vector();
		
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

	public static Vector setComboBoxV(String sql,String field,Vector var)
	{
		var.clear();
		try{
				ResultSet x = Query(sql);
				while(x.next())
				{
					var.addElement(x.getString(field));
				}
			}catch(Exception e)
			{
				System.out.println(e);
			}
		return var;
	}


	// public static JPanel answers_form()
	// {
	// 	JLabel answer_l[] = new JLabel[3];
	// 		answer_l[0] = new JLabel("Question :");
	// 		answer_l[2] = new JLabel("Description :");
			
 //   	    JComboBox quest = new JComboBox(new DefaultComboBoxModel(setComboBoxV("SELECT quest_desc FROM questions","quest_desc"))); 
	// 	JTextField answer_t = new JTextField();

	// 	JButton submit_btn = new JButton("Add Choices");
	// 		submit_btn.addActionListener(new ActionListener() {
	// 	         public void actionPerformed(ActionEvent e) {
	// 	         String q = " ";
	// 	         	if (quest.getSelectedIndex() != -1) {                     
	// 	               q = (String)quest.getItemAt
	// 	                    (quest.getSelectedIndex());             
	// 	            }        
	//             try{
	// 				String sql = "SELECT quest_id FROM questions WHERE quest_desc = '"+q+"'";
	// 				ResultSet x = Query(sql);
	// 				while(x.next())
	// 				{
	// 					q = x.getString("quest_id");
	// 				}
	// 			}catch(Exception el)
	// 				{
	// 					System.out.println(el);
	// 				}
	// 	         try{
	// 				updateQuery("INSERT INTO choices(q_id,`desc`) VALUES("+q+",'"+answer_t.getText()+"')");
	// 				answer_t.setText("");
	// 				}catch(Exception el)
	// 					{
	// 						System.out.println(el);
	// 					}   
	// 			}   
	// 	      });

			
	// 	JPanel answer = new JPanel(new GridLayout(2,1));
	// 		int x =100,y=100,w=120,h=30;
	// 		answer_l[0].setBounds(x,y,w,h);	quest.setBounds(x+200,y,w,h);
	// 		answer_l[2].setBounds(x,y+=70,w,h); answer_t.setBounds(x+200,y,w,h);
	// 		submit_btn.setBounds(x,y+=70,w,h);

	// 		answer.add(answer_l[0]);answer.add(quest);
	// 		answer.add(answer_l[2]);answer.add(answer_t);	
	// 		answer.add(submit_btn);
	// 		answer.setLayout(null);

	// 	return answer;
	// }

	
	// public static JPanel questions_form()
	// {
	// 	JLabel question_l[] = new JLabel[4];
	// 		question_l[0] = new JLabel("Lesson Title :");
	// 		question_l[1] = new JLabel("Question :");
	// 		question_l[2] = new JLabel("key :");

	// 	JComboBox lesson = new JComboBox(new DefaultComboBoxModel(setComboBoxV("SELECT title FROM lessons","title")));  
	// 	JTextField question_t[] = new JTextField[2];
	// 		question_t[0] = new JTextField();
	// 		question_t[1] = new JTextField();

	// 	JButton submit_btn = new JButton("New Question");
	// 	submit_btn.addActionListener(new ActionListener() {
	// 	    public void actionPerformed(ActionEvent e) {
	// 	        String lesson_ = "";
	// 	        if (lesson.getSelectedIndex() != -1) {                     
	// 	               lesson_ = (String)lesson.getItemAt(lesson.getSelectedIndex());             
	// 	         } 
	//             try{
	// 				String sql = "SELECT lesson_id FROM lessons WHERE title = '"+lesson_+"'";
	// 				ResultSet x = Query(sql);
	// 				while(x.next())
	// 				{
	// 					lesson_ = x.getString("lesson_id");
	// 				}
	// 			}catch(Exception el)
	// 				{
	// 					System.out.println(el);
	// 				}

	// 			try{
	// 				updateQuery("INSERT INTO questions(lesson_id,quest_desc,answer_key) VALUES("+lesson_+",'"+question_t[0].getText()+"','"+question_t[1].getText()+"')");
	// 				question_t[0].setText("");question_t[1].setText("");
	// 				setComboBoxV("SELECT quest_desc FROM questions","quest_desc");
	// 			}catch(Exception el)
	// 				{
	// 					System.out.println(el);
	// 				}
	// 		      }          
	// 	      });

	// 	JPanel question = new JPanel();
	// 		int x = 150,y = 100,w = 100,h = 30;
	// 		question_l[0].setBounds(x,y,w,h);		lesson.setBounds(x+120,y,w+30,h);
	// 		question_l[1].setBounds(x,y+=70,w,h);	question_t[0].setBounds(x+120,y,w+30,h);
	// 		question_l[2].setBounds(x,y+=70,w,h);	question_t[1].setBounds(x+120,y,w+30,h);
	// 		submit_btn.setBounds(x,y+=70,w+30,h);

	// 		question.add(question_l[0]); question.add(lesson);
	// 		question.add(question_l[1]);question.add(question_t[0]);	
	// 		question.add(question_l[2]);question.add(question_t[1]);	
	// 		question.add(submit_btn);
	// 		question.setLayout(null);

	// 	return question;
	// }

	
	// //public static Vector subjectV(dapat nay argument nga id sa teacher)

	// public static JPanel lessons_form()
	// {
	// 	JLabel lessons_l[] = new JLabel[3];
	// 		   lessons_l[0] = new JLabel("Subject :");
	// 		   lessons_l[1] = new JLabel("Title :");
	// 		   lessons_l[2] = new JLabel("Content :");
	// 	JComboBox comboBox = new JComboBox(new DefaultComboBoxModel(setComboBoxV("SELECT subject_desc FROM subjects","subject_desc")));
	// 	JTextField lessons_t =  new JTextField();
	// 	JTextArea  lessons_c = new JTextArea(100, 50);
	// 	lessons_c.setEditable(true);
		
	// 	JScrollPane scrollPane = new JScrollPane(lessons_c); 
		 
	// 	JButton submit_btn = new JButton("New Lesson");
	// 		submit_btn.addActionListener(new ActionListener() {
	// 	         public void actionPerformed(ActionEvent e) {
	// 	         	String subj = "";
	// 	         	if (comboBox.getSelectedIndex() != -1) {                     
	// 	               subj = (String)comboBox.getItemAt(comboBox.getSelectedIndex());             
	// 	            } 
	// 	            try{
	// 					String sql = "SELECT subject_id FROM subjects WHERE subject_desc = '"+subj+"'";
	// 					ResultSet x = Query(sql);
	// 					while(x.next())
	// 					{
	// 						subj = x.getString("subject_id");
	// 					}
						
	// 				}catch(Exception es)
	// 				{
	// 					System.out.println(es);
	// 				}
	// 	           try{
	// 	           	updateQuery("INSERT INTO lessons(sub_id,title,content_) VALUES("+subj+",'"+lessons_t.getText()+"','"+lessons_c.getText()+"')");
	// 	           	lessons_t.setText("");lessons_c.setText("");
	// 	           	setComboBoxV("SELECT title FROM lessons","title");
	// 	         	}catch(Exception el)	
	// 	           {
	// 	           	System.out.println(el);
	// 	           }  
	// 	         }          
	// 	      });
	
		
	// 	JPanel lessons = new JPanel();
	// 		int x = 100,y = 50,w = 100,h = 30;
	// 		lessons_l[0].setBounds(x,y,w,h);	comboBox.setBounds(x+100,y,w+30,h);
	// 		lessons_l[1].setBounds(x,y+=30,w,h);	lessons_t.setBounds(x,y+=30,w+150,h);
	// 		lessons_l[2].setBounds(x,y+=30,w,h);	lessons_c.setBounds(x,y+=30,w+150,h+100);
	// 		submit_btn.setBounds(x,y+=170,w+30,h);

	// 		lessons.add(lessons_l[0]); lessons.add(comboBox);	
	// 		lessons.add(lessons_l[1]); lessons.add(lessons_t);
	// 		lessons.add(lessons_l[2]); lessons.add(lessons_c);	
	// 		lessons.add(submit_btn);
			
	// 		lessons.setLayout(null);

	// 	return lessons;
	// }
	public static JPanel assign_teacher_subject_section()
	{
		JLabel teacher_l = new JLabel("Teacher");
		JComboBox teachers = new JComboBox(new DefaultComboBoxModel(setComboBoxV("SELECT name FROM users WHERE user_type='Teacher'","name",teacherV))); 
		JLabel section_l = new JLabel("Section");
		JComboBox section = new JComboBox(new DefaultComboBoxModel(setComboBoxV("SELECT section_description FROM section","section_description",sectionV))); 
		JLabel subject_l = new JLabel("Subject");
		JComboBox subject = new JComboBox(new DefaultComboBoxModel(setComboBoxV("SELECT subject_desc FROM subjects","subject_desc",subjectV))); 
		JButton assign_btn = new JButton("Assign");
		assign_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		         	String tea = " ";
		         	if (teachers.getSelectedIndex() != -1) {                     
		               tea = (String)teachers.getItemAt
		                    (teachers.getSelectedIndex());             
		            } 
		             try{
						ResultSet x = Query("SELECT user_id FROM users WHERE name = '"+tea+"'");
						while(x.next())
						{
							tea = x.getString("user_id");
						}
					}catch(Exception es)
					{
						System.out.println(es);
					}
		            String sec = " ";
		         	if (section.getSelectedIndex() != -1) {                     
		              	sec = (String)section.getItemAt
		                    (section.getSelectedIndex());             
		            }
		            try{
						ResultSet x = Query("SELECT sec_id FROM section WHERE section_description = '"+sec+"'");
						while(x.next())
						{
							sec = x.getString("sec_id");
						}
					}catch(Exception es)
					{
						System.out.println(es);
					}
		            String sub = " ";
		         	if (subject.getSelectedIndex() != -1) {                     
		              	sub = (String)subject.getItemAt
		                    (subject.getSelectedIndex());             
		            }
		            try{
						ResultSet x = Query("SELECT subject_id FROM subjects WHERE subject_desc = '"+sub+"'");
						while(x.next())
						{
							sub = x.getString("subject_id");
						}
					}catch(Exception es)
					{
						System.out.println(es);
					} 
		         	try{
		         		updateQuery("INSERT INTO assign_teacher_subject_section(t_id,sec_id,sub_id) VALUES("+tea+","+sec+","+sub+")");
		         	}catch(Exception er){
		         		System.out.println(er);
		         	}
		         	System.out.println(tea+" "+sec+" "+sub);
		          }          
		      });	
		JPanel panel = new JPanel();
		teacher_l.setBounds(100,50,100,30); teachers.setBounds(170,50,100,30);
		section_l.setBounds(100,100,100,30); section.setBounds(170,100,100,30);
		subject_l.setBounds(100,150,100,30); subject.setBounds(170,150,100,30);
		assign_btn.setBounds(100,200,100,30);

		panel.add(teacher_l);panel.add(teachers);
		panel.add(section_l);panel.add(section);
		panel.add(subject_l);panel.add(subject);
		panel.add(assign_btn);
		panel.setLayout(null);
		return panel;
	}

	public static JPanel enroll_student()
	{
		JLabel student_l = new JLabel("Student");
		JComboBox students = new JComboBox(new DefaultComboBoxModel(setComboBoxV("SELECT name FROM users WHERE user_type='Student'","name",studentsV))); 
		JLabel section_l = new JLabel("Section");
		JComboBox section = new JComboBox(new DefaultComboBoxModel(setComboBoxV("SELECT section_description FROM section","section_description",sectionV))); 
		JLabel subject_l = new JLabel("Subject");
		JComboBox subject = new JComboBox(new DefaultComboBoxModel(setComboBoxV("SELECT subject_desc FROM subjects","subject_desc",subjectV))); 
		JButton enroll_btn = new JButton("Enroll");
		enroll_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		         	String student = " ";
		         	if (students.getSelectedIndex() != -1) {                     
		               student = (String)students.getItemAt
		                    (students.getSelectedIndex());             
		            } 
		             try{
						ResultSet x = Query("SELECT user_id FROM users WHERE name = '"+student+"'");
						while(x.next())
						{
							student = x.getString("user_id");
						}
					}catch(Exception es)
					{
						System.out.println(es);
					}
		            String sec = " ";
		         	if (section.getSelectedIndex() != -1) {                     
		              	sec = (String)section.getItemAt
		                    (section.getSelectedIndex());             
		            }
		            try{
						ResultSet x = Query("SELECT sec_id FROM section WHERE section_description = '"+sec+"'");
						while(x.next())
						{
							sec = x.getString("sec_id");
						}
					}catch(Exception es)
					{
						System.out.println(es);
					}
		            String sub = " ";
		         	if (subject.getSelectedIndex() != -1) {                     
		              	sub = (String)subject.getItemAt
		                    (subject.getSelectedIndex());             
		            }
		            try{
						ResultSet x = Query("SELECT subject_id FROM subjects WHERE subject_desc = '"+sub+"'");
						while(x.next())
						{
							sub = x.getString("subject_id");
						}
					}catch(Exception es)
					{
						System.out.println(es);
					} 
		         	try{
		         		updateQuery("INSERT INTO enroll_student_section_subject(student_id,sec_id,sub_id) VALUES("+student+","+sec+","+sub+")");
		         	}catch(Exception er){
		         		System.out.println(er);
		         	}
		         	System.out.println(student+" "+sec+" "+sub);
		          }          
		      });	
		JPanel panel = new JPanel();
		student_l.setBounds(100,50,100,30); students.setBounds(170,50,100,30);
		section_l.setBounds(100,100,100,30); section.setBounds(170,100,100,30);
		subject_l.setBounds(100,150,100,30); subject.setBounds(170,150,100,30);
		enroll_btn.setBounds(100,200,100,30);

		panel.add(student_l);panel.add(students);
		panel.add(section_l);panel.add(section);
		panel.add(subject_l);panel.add(subject);
		panel.add(enroll_btn);
		panel.setLayout(null);
		return panel;
	}

	public static JPanel section_form()
	{
		JLabel section_l = new JLabel("Section Code");
		JTextField section_t = new JTextField();
		JButton section_btn = new JButton("New Section");
		section_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		         	try{
		         		updateQuery("INSERT INTO section(section_description) VALUES('"+section_t.getText()+"')");
		         		section_t.setText("");
		         	}catch(Exception er){
		         		System.out.println(er);
		         	}
		          }          
		      });		
		JPanel panel = new JPanel();
		section_l.setBounds(100,50,100,30);
		section_t.setBounds(100,100,100,30);
		section_btn.setBounds(100,150,150,30);

		panel.add(section_l);
		panel.add(section_t);
		panel.add(section_btn);
		panel.setLayout(null);
		return panel;
	}

	public static JPanel subjects_form()
	{
		JLabel subject_l = new JLabel("Subject Code");
		JTextField subject_t = new JTextField();
		JButton subject_btn = new JButton("New Subject");
		subject_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		         	try{
		         		updateQuery("INSERT INTO subjects(subject_des) VALUES('"+subject_t.getText()+"')");
		         		section_t.setText("");
		         	}catch(Exception er){
		         		System.out.println(er);
		         	}
		          }          
		      });		
		JPanel panel = new JPanel();
		subject_l.setBounds(100,50,100,30);
		subject_t.setBounds(100,100,100,30);
		subject_btn.setBounds(100,150,150,30);

		panel.add(subject_l);
		panel.add(subject_t);
		panel.add(subject_btn);
		panel.setLayout(null);
		return panel;
	}

	public static JPanel registration_form()
	{
		String[] user = {"Student","Teacher"};
		JComboBox user_type = new JComboBox(user); 
		JLabel user_type_l = new JLabel("UserType: ");
		JLabel reg_l[] = new JLabel[3];
			reg_l[0] = new JLabel("Username :");
			reg_l[1] = new JLabel("Password :");
			reg_l[2] = new JLabel("Name :");
			
		JTextField reg_t[] = new JTextField[reg_l.length];
			reg_t[0] = new JTextField();
			reg_t[1] = new JTextField();
			reg_t[2] = new JTextField();
			
		JButton submit_btn = new JButton("REGISTER");
		//submit_btn.setBounds(10,10,250,100);
		//submit_btn.setFont(new Font("Arial", Font.PLAIN, 10));
			submit_btn.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		         	 String userstype = " ";
		         	if (user_type.getSelectedIndex() != -1) {                     
		               userstype = (String)user_type.getItemAt
		                    (user_type.getSelectedIndex());             
		            }   
		         	try{
		         		updateQuery("INSERT INTO users(username,password,name,user_type) VALUES('"+reg_t[0].getText()+"','"+reg_t[1].getText()+"','"+reg_t[2].getText()+"','"+userstype+"')");
		         		reg_t[0].setText("");reg_t[1].setText("");reg_t[2].setText("");
						}catch(Exception er){
		         		System.out.println(er);
		         	}
		          }          
		      });		
		
		JPanel reg = new JPanel();
			user_type_l.setBounds(150,50,70,30);user_type.setBounds(250,50,120,30);
			reg_l[0].setBounds(150,100,70,30); reg_t[0].setBounds(250,100,120,30);
			reg_l[1].setBounds(150,170,70,30); reg_t[1].setBounds(250,170,120,30);
			reg_l[2].setBounds(150,240,70,30); reg_t[2].setBounds(250,240,120,30);
			submit_btn.setBounds(150,380,100,30);
			
			reg.add(user_type_l);reg.add(user_type);
			reg.add(reg_l[0]);reg.add(reg_t[0]);
			reg.add(reg_l[1]);reg.add(reg_t[1]);
			reg.add(reg_l[2]);reg.add(reg_t[2]);
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

					String sql = "SELECT COUNT(users.username) AS result_num,username,`password`,name,user_type FROM users WHERE username = '"+login_t[0].getText()+"' AND password = '"+login_t[1].getText()+"'";
					ResultSet x = Query(sql);
					while(x.next())
						{
							if(x.getInt(1) != 0)
							{
									login_l[2].setText("Welcome to DarkJAva!");
									switch(x.getString("user_type"))
									{
										case "Admin":
										content.setVisible(false);
										admin_frame.getContentPane().add(admin_dashboard());
										admin_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
										admin_frame.setSize(500,500);
										admin_frame.setVisible(true);
										break;
										case "Teacher":
										content.setVisible(false);
										teacher_frame.getContentPane().add(teacher_dashboard());
										teacher_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
										teacher_frame.setSize(500,500);
										teacher_frame.setVisible(true);
										break;
										case "Student":
											System.out.println(x.getString("user_type"));
										break;
									}
									login_t[0].setText("");
									login_t[1].setText("");
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

	public static JPanel admin_dashboard()
	{
		JTable student = new JTable();
		JTable teacher = new JTable();
		JTabbedPane tab = new JTabbedPane();
			tab.addTab("Register",null,registration_form(),"Register");
			tab.addTab("Subjects",null,subjects_form(),"Subjects");
			tab.addTab("Section",null,section_form(),"Section");
			tab.addTab("Students",null,enroll_student(),"Students");
			tab.addTab("Teachers",null,assign_teacher_subject_section(),"Teachers");

			 tab.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent changeEvent) {
		        JTabbedPane tab = (JTabbedPane) changeEvent.getSource();
		        
				setComboBoxV("SELECT name FROM users WHERE user_type='Student'","name",studentsV); 
				setComboBoxV("SELECT section_description FROM section","section_description",sectionV); 
				setComboBoxV("SELECT subject_desc FROM subjects","subject_desc",subjectV); 
		      }
		    });
		JPanel panel  = new JPanel ();
			panel.add(tab);
			panel.setLayout(new GridLayout(1,1));
		return panel;
	}

	public static JPanel teacher_dashboard()
	{
		// JTabbedPane tab = new JTabbedPane();
		// tab.addTab("Cover",null,cover(),"Cover!");
		// tab.addTab("Lessons",null,lessons_form(),"Lessons");
		// tab.addTab("Questions",null,questions_form(),"Questions");
		// tab.addTab("Answers",null,answers_form(),"Answers");
		// tab.addChangeListener(new ChangeListener() {
		//       public void stateChanged(ChangeEvent changeEvent) {
		//         JTabbedPane tab = (JTabbedPane) changeEvent.getSource();
		//         int index = tab.getSelectedIndex();
		//         switch(tab.getTitleAt(index))
		//         {
		//         	case "Lessons":
		//         	System.out.println(tab.getTitleAt(index));
		//         		setComboBoxV("SELECT subject_desc FROM subjects","subject_desc");
		//         	break;
		//         	case "Questions":
		//         	System.out.println(tab.getTitleAt(index));
		//         		setComboBoxV("SELECT title FROM lessons","title");
		//         	break;
		//         	case "Answers":
		//         	System.out.println(tab.getTitleAt(index));
		//         		setComboBoxV("SELECT quest_desc FROM questions","quest_desc");
		//         	break;
		//         }
		        
		//       }
		//     });
		JPanel panel  = new JPanel ();
		
		// panel.add(tab);
		panel.setLayout(new GridLayout(1,1));
		return panel;
	}
	public static JPanel cover()
	{
		JLabel picLabel = new JLabel("");
		ImageIcon image = new ImageIcon("DarkJava.jpg");
		JLabel label = new JLabel("", image, JLabel.CENTER);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add( label, BorderLayout.CENTER );
		return panel;
	}

	public static void main(String[] args) 
	{	
		new GuiTry(login_form());
	}
}
