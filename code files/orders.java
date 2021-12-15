package project;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class orders extends distapp{
	orders(){
	f1 = new JFrame("DATA");
	f1.setLayout(null);
	DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
    am = new JButton("BUY");
    am.setBounds(520,15, 80, 30);
    am.setFont(new Font ("verdana",Font.BOLD,15));
    am.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			buy();
				
		}
	});
    f1.add(am);
	scrlPane.setBounds(0,0,500,600);
	f1.add(scrlPane);
	    
	    tabGrid.setFont(new Font ("Times New Roman",0,15));
		Connection c = null;
		Statement smt = null;
		int te=0;
			 
			model.addColumn("cname");model.addColumn("mname");model.addColumn("price");model.addColumn("quantity");model.addColumn("mtype");
			int r = 0;
			try {
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
				smt = c.createStatement();
				String sql;
				sql="select * from shortlist";
				
				ResultSet rs = smt.executeQuery(sql);
				while(rs.next()) {
					model.insertRow(r++,new Object[]{rs.getString(1),rs.getString(3),rs.getString(6),rs.getString(7),rs.getString(8)});
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
				f1.setSize(670,500);
			}
	}
	void buy() {
		Connection c = null;
		Statement smt = null;
		
			try {
				buy(1);
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
				smt = c.createStatement();
				String sql3,sql1="select * from shortlist";
				payment t = new payment();
				t.pay();
				ResultSet rs = smt.executeQuery(sql1);
				if(rs.next()) {
					sql3="insert into bills values("+rs.getInt(2)+",'"+rs.getString(3)+"',"+rs.getDouble(6)+","+rs.getInt(7)+",'"+rs.getString(8)+"')";
					smt.executeQuery(sql3);
				}
				JOptionPane.showMessageDialog(null, "Thank You for Buying...!","Succesfull",JOptionPane.INFORMATION_MESSAGE);
				
				smt.close();
		        c.close();
		        
			}
			catch (Exception we) {
				System.out.println(we);
			}
			finally{
				String sql="delete from shortlist";
				try {
					smt.executeUpdate(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
	void buy(int i) {
		if(i==1) {
			f1.dispose();
		}
	}
}

