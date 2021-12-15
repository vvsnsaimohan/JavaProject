package project;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
public class login{
	private String username,password,role;//PRIVATE members
	void logsetdata(String a,String d,String e) {//paramet constr
		username=a;password=d;role=e;
	}
	
	login(){
		String[] ar = {"select","Admin","Company Manager","Distributer"};
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setBounds(350, 50, 570, 520);
		pan.setBackground(Color.decode("#B5EAAA"));
		JFrame f = new JFrame("login");
		f.getContentPane().setBackground(Color.decode("#50C878"));

		
		
		ImageIcon i = new ImageIcon("lo.png");
		JLabel img = new JLabel("",i,JLabel.CENTER);
		img.setBounds(720, 150, 200, 200);
		f.add(img);
		
		ImageIcon i1 = new ImageIcon("u.png");
		JLabel img1 = new JLabel("",i1,JLabel.CENTER);
		img1.setBounds(290, 190, 200, 50);
		f.add(img1);
		
		ImageIcon i2 = new ImageIcon("p.png");
		JLabel img2 = new JLabel("",i2,JLabel.CENTER);
		img2.setBounds(290, 250, 200, 50);
		f.add(img2);
		
		ImageIcon i3 = new ImageIcon("r.png");
		JLabel img3 = new JLabel("",i3,JLabel.CENTER);
		img3.setBounds(290, 310, 200, 50);
		f.add(img3);
		
		JLabel l1 = new JLabel("Pharmacy Management System");
		l1.setBounds(50, 0, 500, 50);
		l1.setFont(new Font("Eras Bold ITC", Font.BOLD, 27));
		l1.setForeground(new Color(0,60,255));
		
		JLabel l2 = new JLabel("LOG IN");
		l2.setBounds(230, 60, 100, 50);
		l2.setFont(new Font("Verdana", Font.BOLD, 20));
		l2.setForeground(new Color(255,0,0));
		
		JLabel l3 = new JLabel("UserId");
		l3.setBounds(80, 140, 200, 50);
		l3.setFont(new Font("Verdana", Font.ITALIC, 20));
		
		JLabel l4 = new JLabel("Password");
		l4.setBounds(80, 200, 200, 50);
		l4.setFont(new Font("Verdana", Font.ITALIC, 20));
		
		JLabel l5 = new JLabel("Role:");
		l5.setBounds(80, 260, 200, 50);
		l5.setFont(new Font("Verdana", Font.ITALIC, 20));
		
		JLabel l6 = new JLabel("Don't you have account?");
		l6.setBounds(190, 420, 200, 50);
		
		JTextField t1 = new JTextField();
		t1.setBounds(190, 150, 200, 30);
		JPasswordField t2 = new JPasswordField();
		t2.setBounds(190, 210, 200, 30);
		
		JComboBox<String> l = new JComboBox<>(ar);
		l.setBounds(190, 270, 110, 30);
		
		JButton b = new JButton("Log In");
		b.setBounds(350, 360, 80, 30);
		b.setFont(new Font("Verdana", Font.PLAIN, 15));
		b.setBackground(Color.yellow);
		
		JButton b1 = new JButton("Register");
		b1.setBounds(340, 430, 100, 30);
		b1.setFont(new Font("Verdana", Font.PLAIN, 15));
		b1.setBackground(Color.yellow);
		
		pan.add(l1);pan.add(l2);pan.add(l3);pan.add(l4);pan.add(l5);pan.add(t1);pan.add(t2);pan.add(l);pan.add(b);
		pan.add(l6);pan.add(b1);
		f.add(pan);
		f.setSize(2000,675);
		f.setLayout(null);
		f.setVisible(true);
		f.setBackground(Color.BLACK);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				username = t1.getText();
				password = new String(t2.getPassword());
				role = (String) l.getSelectedItem();
				if(l.getSelectedItem()=="Admin") {
		        	 if(username.equals("group12@csec") && password.equals("bbmvgroup12")) {
		        		 
		        		 new admin();
		        		 f.dispose();
		        	 }
		        	 else {
		        		 JOptionPane.showMessageDialog(null,"Incorrect Credentials", "error",JOptionPane.ERROR_MESSAGE);
		        	 }
				}
				Connection c = null;
				Statement stmt = null;
			      try {
			         Class.forName("org.postgresql.Driver");
			         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
			         stmt = c.createStatement();
			         String sql;
			         if(role.equals("Company Manager")) {
			        	 sql = "select * from compman where email='"+username+"' and password='"+password+"' and role='"+role+"'";;
			         }
			         else {
			        	 sql = "select * from distributer where email='"+username+"' and password='"+password+"' and role='"+role+"'";
			         }
			         
			         ResultSet rs = stmt.executeQuery(sql);
			         
			         if(rs.next()) {
			        	 if(l.getSelectedItem()=="Company Manager") {
			        		 new compman(rs.getString(6));
			        	 }
			        	 if(l.getSelectedItem()=="Distributer") {
			        		 new distributer();
			        	 }
			         }
			         else {
			        	 JOptionPane.showMessageDialog(null,"Incorrect Credentials", "error",JOptionPane.ERROR_MESSAGE);
			         }
			         
			         stmt.close();
			         c.close();
			         
			        
			      
			      } catch (Exception e1) {
			         e1.printStackTrace();
			         System.err.println(e1.getClass().getName()+": "+e1.getMessage());
			         System.exit(0);
			      }
				
			}
		});
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new register();
				f.dispose();
			}
		});
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new login();
	}

}















