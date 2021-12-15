package project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
abstract class adminapp{
	JFrame f,frame1,frame2;JPanel p;JLabel l,l1,l2,l3,l4,l5,l6;
	JMenuBar mb;
	JMenu b1,b2,b3,b6;
	JMenuItem i1,i2,i3,s1,s2,s3,bs1,bs2,be1,bsi1,bi1,bi2;
	abstract void dashboard();
	abstract void logout();
}
public class admin extends adminapp{
	
	admin(){
		 
		f = new JFrame("PMS");
		frame1 = new JFrame("No of company managers");
		frame2 = new JFrame("No of Distributers");
		f.getContentPane().setBackground(Color.decode("#50C878"));
		
		p = new JPanel();
		ImageIcon e = new ImageIcon(this.getClass().getResource("/3.jpg"));
		l = new JLabel(e);
		l.setSize(1070,450);
	    p.setBounds(120,160,1020,450);
	    p.setBackground(Color.LIGHT_GRAY);
	    p.setLayout(null);
	    p.setBackground(Color.decode("#B5EAAA"));
	    
	    l3 = new JLabel("Welcome,");
		l3.setBounds(40, 10, 900, 60);
		l3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 30));
		l3.setForeground(Color.black);
		l.add(l3);
		l4 = new JLabel("Admin");
		l4.setBounds(180, 50, 900, 60);
		l4.setFont(new Font("Lucida Calligraphy", Font.BOLD, 40));
		l4.setForeground(Color.black);
		l.add(l4);
		p.add(l);
		
		l1 = new JLabel("Pharmacy Management System");
		l1.setBounds(200, 0, 900, 60);
		l1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 49));
		l1.setOpaque(true);
		l1.setForeground(Color.decode("#FF2400"));
		l1.setBackground(Color.decode("#4B5320"));
		
		l2 = new JLabel("Admin");
		l2.setBounds(15,60,200,40);
		l2.setVerticalAlignment(JLabel.TOP);
	    l2.setFont(new Font("Eras Bold ITC", Font.BOLD, 30));
		l2.setPreferredSize(new Dimension(500, 400));
		l2.setForeground(Color.decode("#862323"));
		
	    b1 = new JMenu("     Dashboard");
	    b1.setFont(new Font("Segoe Print", Font.BOLD, 30));
		b1.setPreferredSize(new Dimension(260, 400));
	    b1.setForeground(new Color(225, 225, 0));
	    bsi1 = new JMenuItem("Dashboard");
	    bsi1.setFont(new Font("Segoe Print", Font.BOLD, 20));
	    b1.add(bsi1);
	    
	    b2 = new JMenu("     Manage CM");
	    b2.setFont(new Font("Segoe Print", Font.BOLD, 30));
		b2.setPreferredSize(new Dimension(260, 400));
	    b2.setForeground(new Color(225, 225, 0));
	    bi1 = new JMenuItem(" Manage CM");
	    bi1.setFont(new Font("Segoe Print", Font.BOLD, 20));
	    b2.add(bi1);
	    DefaultTableModel model = new DefaultTableModel();
	    JTable tabGrid = new JTable(model);
	    JScrollPane scrlPane = new JScrollPane(tabGrid);
	    model.addColumn("name");model.addColumn("email");model.addColumn("phno");model.addColumn("cname");
	    
		bi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrlPane.setBounds(0,0,900,600);
			    frame1.add(scrlPane);
			    tabGrid.setFont(new Font ("Times New Roman",0,15));
				Connection c = null;
				Statement smt = null;
				int te=0;
					 
					
					int r = 0;
					try {
						c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
						smt = c.createStatement();
						String sql="select * from compman";
						
						ResultSet rs = smt.executeQuery(sql);
						while(rs.next()) {
							model.insertRow(r++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(6)});
							te++;
						}
			
						smt.close();
				        c.close();
				        
					} 
					catch (Exception we) {
						System.out.println(we);
						JOptionPane.showMessageDialog(null,"SQL Error:"+we);
					}
					if(te==0) {
						JOptionPane.showMessageDialog(null, "No Record Found","Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
						frame1.setVisible(true);
						frame1.setSize(500,300);
					}
			}
		});
	    
	    b3 = new JMenu("     Manage D  ");
	    b3.setPreferredSize(new Dimension(150, 400));
	    b3.setFont(new Font("Segoe Print", Font.BOLD, 30));
		b3.setPreferredSize(new Dimension(290, 400));
	    b3.setForeground(new Color(225, 225, 0));
	    
	    bi2 = new JMenuItem(" Manage D");
	    bi2.setFont(new Font("Segoe Print", Font.BOLD, 20));
	    b3.add(bi2);
	    
	    DefaultTableModel model1 = new DefaultTableModel();
	    JTable tabGrid1 = new JTable(model1);
	    JScrollPane scrlPane1 = new JScrollPane(tabGrid1);
	    model1.addColumn("name");model1.addColumn("email");model1.addColumn("phno");
		bi2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				scrlPane1.setBounds(0,0,900,600);
			    frame2.add(scrlPane1);
			    tabGrid.setFont(new Font ("Times New Roman",0,15));
				Connection c = null;
				Statement smt = null;
				int te=0;
					 
					
					int r = 0;
					try {
						c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
						smt = c.createStatement();
						String sql="select * from distributer";
						
						ResultSet rs = smt.executeQuery(sql);
						while(rs.next()) {
							model1.insertRow(r++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3)});
							te++;
						}
			
						smt.close();
				        c.close();
				        
					} 
					catch (Exception we) {
						System.out.println(we);
						JOptionPane.showMessageDialog(null,"SQL Error:"+we);
					}
					if(te==0) {
						JOptionPane.showMessageDialog(null, "No Record Found","Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
						frame2.setVisible(true);
						frame2.setSize(500,300);
					}
			}
		});
	    
	    b6 = new JMenu("  LogOut  ");
	    bs1 = new JMenuItem("Yes");
	    bs1.setFont(new Font("Segoe Print", Font.BOLD, 20));
	    bs2 = new JMenuItem("NO");
	    bs2.setFont(new Font("Segoe Print", Font.BOLD, 20));
	    b6.setFont(new Font("Segoe Print", Font.BOLD, 30));
		b6.setPreferredSize(new Dimension(200, 400));
	    b6.setForeground(new Color(225, 225, 0));
	    b6.add(bs1);b6.add(bs2);
	    bs1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logout();
			}
	    	
	    });
	    bsi1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dashboard();
			}
			});
	    mb = new JMenuBar();
	    mb.add(b1);mb.add(b2);mb.add(b3);mb.add(b6);
	    mb.setBackground(Color.decode("#6200ad"));///////////////---)))----))))
	    mb.setBounds(130, 110, 1010, 40);
	    
	    f.add(mb);
		f.add(l1);f.add(l2);
		f.add(p);
		f.setSize(2000,670);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new admin();
	}
	@Override
	void dashboard() {
		// TODO Auto-generated method stub
		
				// TODO Auto-generated method stub
				String st = "Company Manager",str="Distributer";
				Connection c = null;
				Statement stmt = null,st1=null;
			      try {
			         Class.forName("org.postgresql.Driver");
			         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
			         stmt = c.createStatement();
			         st1 = c.createStatement();
			         String sql1,sql2;
			         sql1= "select count(role) as nocm from compman where role='"+st+"'";
			         sql2="select count(role) as nod from distributer where role='"+str+"'";
			         
			         ResultSet rs1 = stmt.executeQuery(sql1);
			         ResultSet rs2 = st1.executeQuery(sql2);
			         p.remove(l);
			         p.setBackground(Color.PINK);
			         if(rs1.next()) {
			        	 int noCM = rs1.getInt("nocm");
			        	 l5=new JLabel("No of Company Managers = "+noCM);
			        	 l5.setBounds(50,50,500,35);
			        	 l5.setFont(new Font("Lucida Calligraphy", Font.BOLD, 30));
			     		 l5.setPreferredSize(new Dimension(500, 400));
			        	 p.add(l5);
			        	 
			        	 //f.setSize(2000,675);
			         }
			         stmt.close();
			         if(rs2.next()) {
			        	 int nod = rs2.getInt("nod");
			        	 l6=new JLabel("No of Distributers = "+nod);
			        	 l6.setBounds(50,150,500,35);
			        	 l6.setFont(new Font("Lucida Calligraphy", Font.BOLD, 30));
			     		 l6.setPreferredSize(new Dimension(500, 400));
			        	 p.add(l6);
			        	 //f.setSize(2000,675);
			         }
			         
			         st1.close();
			         c.close();
			         
			        
			      
			      } catch (Exception e1) {
			         e1.printStackTrace();
			         System.err.println(e1.getClass().getName()+": "+e1.getMessage());
			         System.exit(0);
			      }
	}
	@Override
	void logout() {
		// TODO Auto-generated method stub
		new login();
		f.dispose();
	}
}