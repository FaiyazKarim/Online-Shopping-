import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class PaymentInformation extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField U_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentInformation frame = new PaymentInformation();
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
	public PaymentInformation() {
		setTitle("Payment Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(141, 11, 508, 348);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"User ID", "User Name ", "Total Amount ", "Date  "
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Show Payment ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					Statement statement = c.createStatement();
					String sql = "SELECT * FROM sport_store.payment_info";
					ResultSet result = statement.executeQuery(sql);
					
					while(result.next()) {
						String user_id = String.valueOf(result.getString(1));
						String user_name = String.valueOf(result.getString(2));
						String total_amount = String.valueOf(result.getString(3));
						String purchase_date = String.valueOf(result.getString(4));
						
						
						String tbdata[] = {user_id, user_name, total_amount,purchase_date};
						DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
						tbModel.addRow(tbdata);
						
					}
					
				}
					
				catch (Exception e20) {
					JOptionPane.showMessageDialog(null,"The information doesnot exist");
				}
			}
		});
		btnNewButton_1.setBounds(10, 9, 121, 32);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Sign Out");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x;
			     x=JOptionPane.showConfirmDialog(null,"Do you want to sign out of the Application","select",JOptionPane.YES_NO_OPTION);
				if(x==0) {
				    setVisible(false);
				}
			}
		});
		btnNewButton_2.setBounds(7, 319, 111, 37);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("User ID");
		lblNewLabel.setBounds(22, 90, 61, 23);
		contentPane.add(lblNewLabel);
		
		U_id = new JTextField();
		U_id.setBounds(10, 116, 86, 20);
		contentPane.add(U_id);
		U_id.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					String sql = "SELECT * FROM sport_store.sport_store.payment_info where user_id='"+U_id.getText()+"'";
					PreparedStatement ps = c.prepareStatement(sql);
					ResultSet r = ps.executeQuery(sql);
					
					while(r.next()) {
						String user_id = String.valueOf(r.getString(1));
						String user_name =String.valueOf(r.getString(2));
						String total_amount = String.valueOf(r.getString(3));
						String purchase_date =String.valueOf(r.getString(4));
						
						String tbdata[] = {user_id, user_name , total_amount,purchase_date};
						DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
						tbModel.addRow(tbdata);
					}
					
					JOptionPane.showMessageDialog(null, "The Information doesn't exist");
					
				} catch (Exception e12) {
					System.out.println(e12);
				}
			}
		});
		btnNewButton.setBounds(10, 247, 108, 37);
		contentPane.add(btnNewButton);
	}
}
