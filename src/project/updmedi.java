package project;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

public class updmedi extends addapp{
	String cn,type;
	updmedi(String c){
		cn=c;
		f = new JFrame("Update Medicine");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		f.setSize(600, height-100);
		f.setLocationRelativeTo(null);
		
		p = new JPanel();
		p.setLayout(null);
	    p.setBounds(0,0,1070,820);
	    p.setBackground(Color.decode("#B5EAAA"));
	    f.add(p);
		
		l3 = new JLabel("Medicine Id:");
		l3.setFont(new Font("Verdana", Font.BOLD, 20));
		l3.setBounds(20,80,200,20);
		t2 = new JTextField();
		t2.setBounds(240, 80, 200, 30);
		
		l4 = new JLabel("Medicine Name :");
		l4.setFont(new Font("Verdana", Font.BOLD, 20));
		l4.setBounds(20,140,200,20);
		t3 = new JTextField();
		t3.setBounds(240, 140, 200, 30);
		
		p.add(l4);p.add(l3);
		p.add(t2);p.add(t3);
		
		l5 = new JLabel("MFG date:");
		l5.setFont(new Font("Verdana", Font.ITALIC, 20));
		l5.setBounds(110,215,500,20);
		d1 = new JTextField();d1.setBounds(330, 210, 30, 30);
		m1 = new JTextField();m1.setBounds(370, 210, 30, 30);
		y1 = new JTextField();y1.setBounds(410, 210, 60, 30);

		l6 = new JLabel("Expiry date:");
		l6.setFont(new Font("Verdana", Font.ITALIC, 20));
		l6.setBounds(110,265,500,20);
		d2 = new JTextField();d2.setBounds(330, 260, 30, 30);
		m2 = new JTextField();m2.setBounds(370, 260, 30, 30);
		y2 = new JTextField();y2.setBounds(410, 260, 60, 30);
		
		l7 = new JLabel("M R P:");
		l7.setFont(new Font("Verdana", Font.ITALIC, 20));
		l7.setBounds(110,315,500,20);
		t4 = new JTextField();
		t4.setBounds(330, 310, 200, 30);
		
		l8 = new JLabel("	Quantity:");
		l8.setFont(new Font("Verdana", Font.ITALIC, 20));
		l8.setBounds(110,365,500,20);
		t5 = new JTextField();
		t5.setBounds(330, 360, 200, 30);
		
		String ar[]= {"Tablet","Capsule","Syrup","Insulin","Cream","Balm","Drop","Granul","Oil","Powder"};
		l9 = new JLabel("Med Type:");
		l9.setFont(new Font("Verdana", Font.ITALIC, 20));
		l9.setBounds(110,415,500,20);
		JComboBox<String> l = new JComboBox<>(ar);
		l.setBounds(330, 410, 110, 30);
		
		a = new JButton("Update");
		a.setBounds(220, 500, 80, 30);
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type=(String) l.getSelectedItem();
				add();
			}
		});
		
		b = new JButton("Cancel");
		b.setBounds(460, 500, 80, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		
		p.add(a);p.add(b);p.add(l9);p.add(l);
		p.add(l3);p.add(l4);p.add(l5);p.add(l6);p.add(l7);p.add(l8);p.add(t2);
		p.add(d1);p.add(m1);p.add(y1);p.add(d2);p.add(m2);p.add(y2);p.add(t4);p.add(t5);
		//f.setSize(600,630);
		f.setLayout(null);
		f.setVisible(true);
		f.setBackground(Color.BLACK);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	void add() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement smt = null;
			String s2 = t2.getText();
			String s3 = t3.getText();
			String s4 = t4.getText();
			String s5 = t5.getText();
			String dt1 = y1.getText() + m1.getText() + d1.getText();
			String dt2 = y2.getText() + m2.getText() + d2.getText();
			try {
				if(((t2.getText()).equals(""))||((t3.getText()).equals(""))||((t4.getText()).equals(""))||((t5.getText()).equals(""))||((d1.getText()).equals(""))||((d2.getText()).equals(""))||((y1.getText()).equals(""))||((y2.getText()).equals(""))){
			    JOptionPane.showMessageDialog(null,"* Detail are Missing !","Warning!!!",JOptionPane.WARNING_MESSAGE);
		        }
				else {
					c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
					smt = c.createStatement();
					String sql="update "+cn+" set mfgdate='"+dt1+"',expdate='"+dt2+"',mrp='"+s4+"',quantity='"+s5+"',mtype='"+type+"'where mid='"+Integer.parseInt(s2)+"' and mname='"+s3+"'";
					smt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Record is Updated.");
					smt.close();
			        c.close();
				}
			} 
			catch (Exception we) {
				System.out.println(we);
				JOptionPane.showMessageDialog(null,"SQL Error:"+we);
			}
	}
}
