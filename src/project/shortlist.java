package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class shortlist extends distapp{
	shortlist(int i,int j){
		if(i==1) {
			f = new JFrame("Add to Shortlist");
			p = new JPanel();
			p.setLayout(null);
		    p.setBounds(0,0,1070,420);
		    p.setBackground(Color.decode("#B5EAAA"));
		    
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int height = screenSize.height;
			f.setSize(500, height-400);
		    
		    f.setLocationRelativeTo(null);
		    f.add(p);
			
			l3 = new JLabel("Medicine Id:");
			l3.setFont(new Font("Verdana", Font.ITALIC, 20));
			l3.setBounds(20,65,500,20);
			t2 = new JTextField();
			t2.setBounds(240, 60, 200, 30);
			
			l4 = new JLabel("Company Name :");
			l4.setFont(new Font("Verdana", Font.ITALIC, 20));
			l4.setBounds(20,115,500,20);
			t3 = new JTextField();
			t3.setBounds(240, 110, 200, 30);
			p.add(l4);p.add(l3);
			p.add(t2);p.add(t3);
			
			a = new JButton("Shortlist");
			a.setBounds(180, 190, 100, 30);
			a.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Connection c = null;
					Statement smt = null;
						String s2 = t2.getText();
						String s3 = t3.getText();
						try {
						    if((s2.equals(""))||(s3.equals(""))) {
					    		 JOptionPane.showMessageDialog(null,"* Details are Missing !","Warning!!!",JOptionPane.WARNING_MESSAGE);
					    	}
							c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
							smt = c.createStatement();
							String sql1="select * from medicine where mid="+Integer.parseInt(s2)+" and cname='"+s3+"'";
							String sql2;
							ResultSet rs = smt.executeQuery(sql1);
							
							if(rs.next()) {
								sql2="insert into shortlist values('"+rs.getString(1)+"',"+rs.getInt(2)+",'"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"',"+rs.getDouble(6)+","+rs.getInt(7)+",'"+rs.getString(8)+"')";
								
								smt.executeQuery(sql2);
							}
							else {
								JOptionPane.showMessageDialog(null, "No Record Found","Error",JOptionPane.ERROR_MESSAGE);
							}
							smt.close();
					        c.close();
					        
						} 
						catch (Exception we) {
							System.out.println(we);
							JOptionPane.showMessageDialog(null,"Added to shortlist.");
						}
				}
			});
			
			b = new JButton("Cancel");
			b.setBounds(380, 190, 80, 30);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f.dispose();
				}
			});
			
			p.add(a);p.add(b);
			//f.setSize(500,320);
			f.setLayout(null);
			if(j==1) {
				f.setVisible(true);
			}
			f.setBackground(Color.BLACK);
		}
		
		f1 = new JFrame("DATA");
		f1.setLayout(null);
		DefaultTableModel model = new DefaultTableModel();
	    JTable tabGrid = new JTable(model);
	    JScrollPane scrlPane = new JScrollPane(tabGrid);
	    am = new JButton("Cancel");
	    am.setBounds(720,15, 80, 30);
	    am.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f1.dispose();
			}
		});
	    f1.add(am);
		if(i==2){
			
			scrlPane.setBounds(0,0,700,600);
		    f1.add(scrlPane);
		    
		    tabGrid.setFont(new Font ("Times New Roman",0,15));
			Connection c = null;
			Statement smt = null;
			int te=0;
				 
				model.addColumn("cname");model.addColumn("mid");model.addColumn("mname");model.addColumn("mfgdate");
			  	model.addColumn("expdate");model.addColumn("price");model.addColumn("quantity");model.addColumn("mtype");
				int r = 0;
				try {
					c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
					smt = c.createStatement();
					String sql;
					sql="select * from shortlist";
					
					ResultSet rs = smt.executeQuery(sql);
					while(rs.next()) {
						model.insertRow(r++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});
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
					f1.setVisible(true);
					f1.setSize(870,500);
				}
		}
	}
}
