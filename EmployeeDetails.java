import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class EmployeeDetails extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField E_ID;
	private JTextField E_Name;
	private JTextField E_Password;
	private JTextField E_Address;
	private JTextField E_Status;
	private JTextField E_Gender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeDetails frame = new EmployeeDetails();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeDetails() {
		setTitle("Employee Details ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 859, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(137, 11, 696, 365);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "Employee Name", "Employee Password", "Employee Address", "Employee Status","Employee Gender"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(94);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(99);
		table.getColumnModel().getColumn(3).setPreferredWidth(105);
		table.getColumnModel().getColumn(4).setPreferredWidth(87);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(20, 18, 46, 22);
		contentPane.add(lblNewLabel);
		
		E_ID = new JTextField();
		E_ID.setBounds(10, 44, 86, 20);
		contentPane.add(E_ID);
		E_ID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 75, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		E_Name = new JTextField();
		E_Name.setBounds(10, 100, 105, 20);
		contentPane.add(E_Name);
		E_Name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(10, 131, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		E_Password = new JTextField();
		E_Password.setBounds(10, 158, 99, 20);
		contentPane.add(E_Password);
		E_Password.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setBounds(10, 189, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		E_Address = new JTextField();
		E_Address.setBounds(10, 214, 105, 20);
		contentPane.add(E_Address);
		E_Address.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Status");
		lblNewLabel_4.setBounds(10, 257, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		E_Status = new JTextField();
		E_Status.setBounds(10, 282, 86, 20);
		contentPane.add(E_Status);
		E_Status.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Gender");
		lblNewLabel_5.setBounds(10, 321, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		E_Gender = new JTextField();
		E_Gender.setBounds(10, 346, 86, 20);
		contentPane.add(E_Gender);
		E_Gender.setColumns(10);
		
		JButton btnNewButton = new JButton("Show List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					Statement statement = c.createStatement();
					String sql = "SELECT * FROM sport_store.employers_info;";
					ResultSet result = statement.executeQuery(sql);
					
					while(result.next()) {

						String Employers_id = String.valueOf(result.getString(1));
						String Employers_name= String.valueOf(result.getString(2));
						String Employers_password = String.valueOf(result.getString(3));
						String Employers_address = String.valueOf(result.getString(4));
						String Employers_status = String.valueOf(result.getString(5));
						String Employers_gender = String.valueOf(result.getString(6));
						
						String tbdata[] = {Employers_id,Employers_name,Employers_password,Employers_address,Employers_status,Employers_gender};
						DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
						tbModel.addRow(tbdata);
					}
				} catch (Exception e23) {
					System.out.println(e23);
				}
			}
		});
		btnNewButton.setBounds(10, 390, 99, 36);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					PreparedStatement ps = connect.prepareStatement("insert into sport_store.employers_info(Employers_id,Employers_name,Employers_password,Employers_address,Employers_status,Employee_gender)values(?,?,?,?,?,?)");
					ps.setString(1, E_ID.getText());
					ps.setString(2, E_Name.getText());
					ps.setString(3, E_Password.getText());
					ps.setString(4, E_Address.getText());
					ps.setString(5, E_Status.getText());
					ps.setString(6, E_Gender.getText());
					int x= ps.executeUpdate();
					if ( x > 0) {
						JOptionPane.showMessageDialog(null,"The data is Added successfully");
						
					}
					else {
						JOptionPane.showMessageDialog(null,"The data is not Added into the table ");
					}
					
				} catch (Exception e7) {
					System.out.println(e7);
				}
			}
		});
		btnNewButton_1.setBounds(137, 390, 89, 36);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
			         String sql = "Update sport_store.employers_info set Employers_id='"+E_ID.getText()+"',Employers_name='"+E_Name.getText()+"',Employers_password='"+E_Password.getText()+"',Employers_address='"+E_Address.getText()+"',Employers_status='"+E_Status.getText()+"',Employee_gender='"+E_Gender.getText()+"'where Employers_id='"+E_ID.getText()+"'";
			         PreparedStatement statement = c.prepareStatement(sql);
			         statement.execute();
			         JOptionPane.showMessageDialog(null, "The information is updated");
			         statement.close();
				} catch (Exception e10) {
					System.out.println(e10);
				}
				
			}
		});
		btnNewButton_2.setBounds(281, 390, 89, 36);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Delete ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					String sql = "Delete from sport_store.employers_info where Employers_id =?";
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1,E_ID.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "The Information is deleted successfully");
					
				} catch (Exception e11) {
					System.out.println(e11);
				}
			}
		});
		btnNewButton_3.setBounds(416, 390, 89, 36);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Search");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					String sql = "SELECT * FROM sport_store.employers_info where Employers_id='"+E_ID.getText()+"'";
					PreparedStatement ps = c.prepareStatement(sql);
					ResultSet r = ps.executeQuery(sql);
					
					while(r.next()) {
						String Employers_id = String.valueOf(r.getString(1));
						String Employers_name= String.valueOf(r.getString(2));
						String Employers_password = String.valueOf(r.getString(3));
						String Employers_address = String.valueOf(r.getString(4));
						String Employers_status = String.valueOf(r.getString(5));
						String Employers_gender = String.valueOf(r.getString(6));
						
						String tbdata[] = {Employers_id,Employers_name,Employers_password,Employers_address,Employers_status,Employers_gender};
						DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
						tbModel.addRow(tbdata);
					}
					
					
				} catch (Exception e12) {
					System.out.println(e12);
				}
			}
		});
		btnNewButton_4.setBounds(572, 390, 89, 36);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Product Info");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlPanel panel = new ControlPanel();
				panel.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(716, 390, 105, 36);
		contentPane.add(btnNewButton_5);
	}
}
