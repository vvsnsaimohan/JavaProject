package project;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class register {
	private String name,email,phno,password,role,cname;//PRIVATE members
	void regsetdata(String a,String b,String c,String d,String e,String f) {
		name=a;email=b;phno=c;password=d;role=e;cname=f;
	}
	
	register(){
		String[] ar = {"select","Company Manager","Distributer"};
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBounds(340, 60, 570, 530);
		pan.setBackground(Color.decode("#B5EAAA"));
		
		JFrame f = new JFrame("register");
		f.getContentPane().setBackground(Color.decode("#50C878"));
		JLabel l1 = new JLabel("Pharmacy Management System");
		l1.setBounds(50, 0, 500, 50);
		l1.setFont(new Font("Verdana", Font.BOLD, 27));
		l1.setForeground(new Color(0,0,255));
		
		JLabel l2 = new JLabel("REGISTER");
		l2.setBounds(230, 60, 200, 50);
		l2.setFont(new Font("Verdana", Font.BOLD, 20));
		l2.setForeground(new Color(255,0,0));
		
		JLabel l3 = new JLabel("Name");
		l3.setBounds(80, 100, 200, 50);
		l3.setFont(new Font("Verdana", Font.ITALIC, 20));
		
		JLabel l4 = new JLabel("E-Mail");
		l4.setBounds(80, 160, 200, 50);
		l4.setFont(new Font("Verdana", Font.ITALIC, 20));
		
		JLabel l5 = new JLabel("Phone No.");
		l5.setBounds(80, 220, 200, 50);
		l5.setFont(new Font("Verdana", Font.ITALIC, 20));
		
		JLabel l6 = new JLabel("Password");
		l6.setBounds(80, 280, 200, 50);
		l6.setFont(new Font("Verdana", Font.ITALIC, 20));
		
		JLabel l7 = new JLabel("Role:");
		l7.setBounds(80, 340, 200, 50);
		l7.setFont(new Font("Verdana", Font.ITALIC, 20));
		
		JTextField t1 = new JTextField();
		t1.setBounds(280, 110, 200, 30);
		JTextField t2 = new JTextField();
		t2.setBounds(280, 170, 200, 30);
		JTextField t3 = new JTextField();
		t3.setBounds(280, 230, 200, 30);
		JPasswordField p = new JPasswordField();
		p.setBounds(280, 290, 200, 30);
		JLabel l9 = new JLabel("Already have an account?");
		l9.setBounds(50, 470, 200, 30);
		
		JButton b1 = new JButton("Register");
		b1.setBounds(380, 470, 100, 30);
		b1.setFont(new Font("Verdana", Font.PLAIN, 15));
		b1.setBackground(Color.yellow);
		
		JComboBox<String> l = new JComboBox<>(ar);
		l.setBounds(280, 350, 110, 30);
		JLabel l8 = new JLabel("Company Name");
		l8.setBounds(80, 400, 200, 50);
		l8.setFont(new Font("Verdana", Font.ITALIC, 20));
		JTextField t4 = new JTextField();
		t4.setBounds(280, 410, 200, 30);
		pan.add(l8);pan.add(t4);
		
		JButton b2 = new JButton("Login");
		b2.setBounds(200, 470, 100, 30);
		b2.setFont(new Font("Verdana", Font.PLAIN, 15));
		b2.setBackground(Color.yellow);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
				new login();
			}
		});
		
		
		l.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String role = (String) l.getSelectedItem();
				if(role.equals("Distributer")) {
					pan.remove(l8);pan.remove(t4);
					l9.setBounds(50, 400, 200, 30);
					b2.setBounds(200, 400, 100, 30);
					b1.setBounds(380, 400, 100, 30);
					pan.setBounds(340, 60, 570, 480);
				}
			}
		});
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				name = t1.getText();
				email = t2.getText();
				phno = t3.getText();
				password = new String(p.getPassword());
				role = (String) l.getSelectedItem();
				Connection c = null;
				Statement stmt = null;
				
			      try {
			    	  if((name.equals(""))||(email.equals(""))||(phno.equals(""))||(password.equals(""))||(role.equals(""))) {
			    		  JOptionPane.showMessageDialog(null,"* Details are Missing !","Warning!!!",JOptionPane.WARNING_MESSAGE);
			    	  }
			    	  if(!email.contains("@gmail.com")) {
			    		  JOptionPane.showMessageDialog(null,"* Enter Email Correctly !","Warning!!!",JOptionPane.WARNING_MESSAGE);
			    	  }
			    	  else {
			    		  Class.forName("org.postgresql.Driver");
			    		  JOptionPane.showMessageDialog(null,"Registered Succesfully...!");
			    		  new login();
					         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
					         stmt = c.createStatement();
					         String sql1,sql2;
					         if(role.equals("Company Manager")) {
					        	 cname = t4.getText();
					        	 sql2="create table "+cname+"(cname text,mid int,mname text,mfgdate date,expdate date,mrp decimal(10,2),quantity int,mtype text,primary key(mid))";
					        	 sql1 = "insert into compman values('"+name+"','"+email+"','"+phno+"','"+password+"','"+role+"','"+cname+"')";
					        	 stmt.addBatch(sql1);
						         stmt.addBatch(sql2);
					         }
					         else {
					        	 sql1 = "insert into distributer values('"+name+"','"+email+"','"+phno+"','"+password+"','"+role+"')";
					        	 stmt.addBatch(sql1);
					         }
					         stmt.executeBatch();
					         f.dispose();
					         new login();
					         stmt.close();
					         c.close();
			    	  }
			         
			      
			      } catch (Exception e1) {
			         e1.printStackTrace();
			         System.err.println(e1.getClass().getName()+": "+e1.getMessage());
			         System.exit(0);
			      }
			}
		});
		f.add(pan);
		pan.add(l1);pan.add(l2);pan.add(l3);pan.add(l4);pan.add(l5);pan.add(l6);pan.add(l7);
		pan.add(t1);pan.add(t2);pan.add(t3);pan.add(p);pan.add(l);pan.add(b1);pan.add(l9);pan.add(b2);
		f.setSize(2000,675);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new register();
	}
}
