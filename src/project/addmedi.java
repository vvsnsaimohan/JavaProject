package project;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

abstract class addapp{
	JLabel l3,l4,l5,l6,l7,l8,l9;JButton a,b;
	JTextField t1,t2,t3,d1,m1,y1,d2,m2,y2,t4,t5;
	JFrame f;JPanel p;JMenuBar mb;
	JMenu b1,m,ms,b4,b5,b6;
	JMenuItem i1,i2,i3,s1,s2,s3,bs1,bs2,be1,bs15,bi1,bi2;
	abstract void add();
}
public class addmedi extends addapp{
	String cn,type;
	public addmedi(String c) {
		cn=c;
		// TODO Auto-generated constructor stub
		f = new JFrame("Add Medicine");
		
		p = new JPanel();
		p.setLayout(null);
	    p.setBounds(0,0,1070,580);
	    p.setBackground(Color.decode("#B5EAAA"));
	    f.add(p);
	    
		l3 = new JLabel("Medicine Id:");
		l3.setFont(new Font("Verdana", Font.ITALIC, 20));
		l3.setBounds(160,65,500,20);
		t1 = new JTextField();
		t1.setBounds(380, 60, 200, 30);
		
		l4 = new JLabel("Medicine Name :");
		l4.setFont(new Font("Verdana", Font.ITALIC, 20));
		l4.setBounds(160,115,500,20);
		t2 = new JTextField();
		t2.setBounds(380, 110, 200, 30);
		
		l5 = new JLabel("MFG date:");
		l5.setFont(new Font("Verdana", Font.ITALIC, 20));
		l5.setBounds(160,165,500,20);
		d1 = new JTextField();d1.setBounds(380, 160, 30, 30);
		m1 = new JTextField();m1.setBounds(420, 160, 30, 30);
		y1 = new JTextField();y1.setBounds(460, 160, 60, 30);

		l6 = new JLabel("Expiry date:");
		l6.setFont(new Font("Verdana", Font.ITALIC, 20));
		l6.setBounds(160,215,500,20);
		d2 = new JTextField();d2.setBounds(380, 210, 30, 30);
		m2 = new JTextField();m2.setBounds(420, 210, 30, 30);
		y2 = new JTextField();y2.setBounds(460, 210, 60, 30);
		
		l7 = new JLabel("M R P:");
		l7.setFont(new Font("Verdana", Font.ITALIC, 20));
		l7.setBounds(160,265,500,20);
		t4 = new JTextField();
		t4.setBounds(380, 260, 200, 30);
		
		l8 = new JLabel("	Quantity:");
		l8.setFont(new Font("Verdana", Font.ITALIC, 20));
		l8.setBounds(160,315,500,20);
		t5 = new JTextField();
		t5.setBounds(380, 310, 200, 30);
		
		String ar[]= {"Tablet","Capsule","Syrup","Insulin","Cream","Balm","Drop","Granul","Oil","Powder"};
		l9 = new JLabel("Med Type:");
		l9.setFont(new Font("Verdana", Font.ITALIC, 20));
		l9.setBounds(160,365,500,20);
		JComboBox<String> l = new JComboBox<>(ar);
		l.setBounds(380, 360, 110, 30);
		
		a = new JButton("Add");
		a.setBounds(270, 430, 80, 30);
		b = new JButton("Cancel");
		b.setBounds(515, 430, 80, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});


		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type=(String) l.getSelectedItem();
				add();
				
			}
		});
		
		p.add(a);p.add(b);p.add(l9);p.add(l);
		p.add(l3);p.add(l4);p.add(l5);p.add(l6);p.add(l7);p.add(l8);p.add(t1);p.add(t2);
		p.add(d1);p.add(m1);p.add(y1);p.add(d2);p.add(m2);p.add(y2);p.add(t4);p.add(t5);
		f.setSize(760,560);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

	@Override
	void add() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement smt = null;
			JOptionPane.showMessageDialog(null,"Added new Medicine", "add",JOptionPane.INFORMATION_MESSAGE);
			try {
				String dt1 = y1.getText() + m1.getText() + d1.getText();
				String dt2 = y2.getText() + m2.getText() + d2.getText();
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
				smt = c.createStatement();
				String sql1="insert into  "+cn+" values('"+cn+"',"+ Integer.parseInt(t1.getText()) +",'" + t2.getText() + "','" + dt1 + "','" + dt2
						+ "'," + Double.parseDouble(t4.getText()) + "," + Integer.parseInt(t5.getText()) + ",'"+type+"')";
				String sql2="insert into  medicine values('"+cn+"',"+ Integer.parseInt(t1.getText()) +",'" + t2.getText() + "','" + dt1 + "','" + dt2
						+ "'," + Double.parseDouble(t4.getText()) + "," + Integer.parseInt(t5.getText()) + ",'"+type+"')";
				smt.addBatch(sql1);
				smt.addBatch(sql2);
				smt.executeBatch();
				
				smt.close();
		        c.close();
		        
			} 
			catch (Exception we) {
				System.out.println(we);
				JOptionPane.showMessageDialog(null,"SQL Error:"+we);
			}
	}
}