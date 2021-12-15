package project;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class search extends distapp{
	search(int i){
		f = new JFrame("Search");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		f.setSize(455, height/2);
		
		f.setLocationRelativeTo(null);
		p = new JPanel();
		p.setLayout(null);
	    p.setBounds(0,0,1070,450);
	    p.setBackground(Color.decode("#B5EAAA"));
	    f.add(p);
	    
	    l2 = new JLabel("By Company Name :");
		l2.setFont(new Font("Verdana", Font.ITALIC, 20));
		l2.setBounds(100,65,300,20);
		t = new JTextField();
		t.setBounds(100, 105, 200, 30);
	    
		l3 = new JLabel("By Medicine Id:");
		l3.setFont(new Font("Verdana", Font.ITALIC, 20));
		l3.setBounds(100,65,300,20);
		
		l4 = new JLabel("By Medicine Name :");
		l4.setFont(new Font("Verdana", Font.ITALIC, 20));
		l4.setBounds(100,65,300,20);
		
		p.add(t);
		if(i==1) {
			p.add(l2);
		}
		else if(i==2) {
			p.add(l4);
		}
		else {
			p.add(l3);
		}
		frame1 = new JFrame("Database Search Result");
		frame1.setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel();
	    JTable tabGrid = new JTable(model);
	    JScrollPane scrlPane = new JScrollPane(tabGrid);
	    model.addColumn("cname");model.addColumn("mid");model.addColumn("mname");model.addColumn("mfgdate");
	  	model.addColumn("expdate");model.addColumn("price");model.addColumn("quantity");model.addColumn("mtype");
	    a = new JButton("Search");
		a.setBounds(80, 160, 80, 30);
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrlPane.setBounds(0,0,900,600);
			    frame1.add(scrlPane);
			    tabGrid.setFont(new Font ("Times New Roman",0,15));
				Connection c = null;
				Statement smt = null;
				int te=0;
					String s = t.getText();
					 
					
					int r = 0;
					try {
						c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
						smt = c.createStatement();
						String sql;
						if(i==1) {
							sql="select * from medicine where cname='"+s+"'";
						}
						else if(i==2) {
							sql="select * from medicine where mname='"+s+"'";
						}
						else {
							sql="select * from medicine where mid='"+Integer.parseInt(s)+"'";
						}
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
						frame1.setVisible(true);
						frame1.setSize(500,300);
					}
			}
		});
		b = new JButton("Cancel");
		b.setBounds(230, 160, 80, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
		p.add(a);p.add(b);
		//f.setSize(455,320);
		f.setLayout(null);
		f.setVisible(true);
		f.setBackground(Color.BLACK);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}


























