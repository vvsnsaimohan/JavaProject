package project;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
interface payapp{
	void pay();
	void bill();
}
public class payment extends distapp implements payapp{
	private long accno;
	private String ifsc,name,type;
	private double money;
	@Override
	public void pay() {
		// TODO Auto-generated method stub
		f = new JFrame("Payment Page");
		p = new JPanel();
		p.setLayout(null);
	    p.setBounds(0,0,1070,580);
	    p.setBackground(Color.decode("#B5EAAA"));
	    f.add(p);
	    
		l3 = new JLabel("Accoutn No:");
		l3.setFont(new Font("Verdana", Font.ITALIC, 20));
		l3.setBounds(160,65,500,20);
		t1 = new JTextField();
		t1.setBounds(380, 60, 200, 30);
		
		l4 = new JLabel("Name :");
		l4.setFont(new Font("Verdana", Font.ITALIC, 20));
		l4.setBounds(160,115,500,20);
		t2 = new JTextField();
		t2.setBounds(380, 110, 200, 30);
		
		l5 = new JLabel("IFSC Code:");
		l5.setFont(new Font("Verdana", Font.ITALIC, 20));
		l5.setBounds(160,165,500,20);
		d1 = new JTextField();
		d1.setBounds(380, 160, 200, 30);

		l6 = new JLabel("Branch :");
		l6.setFont(new Font("Verdana", Font.ITALIC, 20));
		l6.setBounds(160,215,500,20);
		d2 = new JTextField();
		d2.setBounds(380, 210, 200, 30);
		
		l7 = new JLabel("Money:");
		l7.setFont(new Font("Verdana", Font.ITALIC, 20));
		l7.setBounds(160,265,500,20);
		t4 = new JTextField();
		t4.setBounds(380, 260, 200, 30);
		
		
		String ar[]= {"Debit Card","Credit Card","Net Banking","GPay","PhonePay"};
		l9 = new JLabel("Type:");
		l9.setFont(new Font("Verdana", Font.ITALIC, 20));
		l9.setBounds(160,315,500,20);
		JComboBox<String> l = new JComboBox<>(ar);
		l.setBounds(380, 310, 110, 30);
		
		a = new JButton("Pay");
		a.setBounds(270, 380, 80, 30);
		b = new JButton("Cancel");
		b.setBounds(515, 380, 80, 30);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});


		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bill();
				JOptionPane.showMessageDialog(null, "Payment Succesfull...!","Succesfull",JOptionPane.INFORMATION_MESSAGE);
				f.dispose();
			}
		});
		
		p.add(a);p.add(b);p.add(l9);p.add(l);
		p.add(l3);p.add(l4);p.add(l5);p.add(l6);p.add(l7);p.add(t1);p.add(t2);
		p.add(d1);p.add(d2);p.add(t4);
		f.setSize(760,520);
		f.setLayout(null);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

	@Override
	public void bill() {
		// TODO Auto-generated method stub
		DefaultTableModel model = new DefaultTableModel();
	    JTable tabGrid = new JTable(model);
	    JScrollPane scrlPane = new JScrollPane(tabGrid);
	    f1 = new JFrame("Invoice");
		scrlPane.setBounds(0,0,700,600);
	    f1.add(scrlPane);
	    
	    tabGrid.setFont(new Font ("Times New Roman",0,15));
		Connection c = null;
		Statement smt = null;
		int te=0;
			 
			model.addColumn("mid");model.addColumn("mname");model.addColumn("price");model.addColumn("quantity");model.addColumn("mtype");
			int r = 0;
			try {
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JAVA", "postgres", "postgres");
				smt = c.createStatement();
				String sql;
				sql="select * from bills";
				
				ResultSet rs = smt.executeQuery(sql);
				while(rs.next()) {
					model.insertRow(r++,new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
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
				JOptionPane.showMessageDialog(null, "No Bills Found","Error",JOptionPane.ERROR_MESSAGE);
			}
			else {
				f1.setVisible(true);
				f1.setSize(870,500);
			}
	}
	public static void main(String[] args) {
		payment a = new payment();
		a.pay();
	}
}
