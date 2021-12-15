package project;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class delmedi extends addapp{
	final private String cn;
	delmedi(String c){
		cn=c;
		f = new JFrame("Delete Medicine");
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
		
		l4 = new JLabel("Medicine Name :");
		l4.setFont(new Font("Verdana", Font.ITALIC, 20));
		l4.setBounds(20,115,500,20);
		t3 = new JTextField();
		t3.setBounds(240, 110, 200, 30);
		p.add(l4);p.add(l3);
		p.add(t2);p.add(t3);
		
		a = new JButton("Delete");
		a.setBounds(180, 190, 80, 30);
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
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
		f.setVisible(true);
		f.setBackground(Color.BLACK);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new delmedi("");
	}
	@Override
	void add() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement smt = null;
			String s2 = t2.getText();
			String s3 = t3.getText();
			try {
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
				smt = c.createStatement();
				String sql="delete from "+cn+" where mid='"+Integer.parseInt(s2)+"' and mname='"+s3+"'";
				smt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null,"Record is deleted.");
				smt.close();
		        c.close();
		        
			} 
			catch (Exception we) {
				System.out.println(we);
				JOptionPane.showMessageDialog(null,"SQL Error:"+we);
			}
	}
}
