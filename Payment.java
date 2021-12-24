import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class Payment extends JFrame {

	private JPanel contentPane;
	private JTextField U_id;
	private JTextField U_name;
	private JTextField TAmount;
	private JTextField Date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment();
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
	public Payment() {
		setTitle("Payment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User ID");
		lblNewLabel.setBounds(10, 22, 64, 22);
		contentPane.add(lblNewLabel);
		
		U_id = new JTextField();
		U_id.setBounds(84, 23, 104, 20);
		contentPane.add(U_id);
		U_id.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("User Name");
		lblNewLabel_1.setBounds(10, 80, 64, 14);
		contentPane.add(lblNewLabel_1);
		
		U_name = new JTextField();
		U_name.setBounds(89, 77, 99, 20);
		contentPane.add(U_name);
		U_name.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Total Amount");
		lblNewLabel_4.setBounds(209, 26, 87, 14);
		contentPane.add(lblNewLabel_4);
		
		TAmount = new JTextField();
		TAmount.setBounds(323, 23, 106, 20);
		contentPane.add(TAmount);
		TAmount.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Date");
		lblNewLabel_5.setBounds(209, 80, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		Date = new JTextField();
		Date.setBounds(250, 77, 179, 20);
		contentPane.add(Date);
		Date.setColumns(10);
		
		JButton btnNewButton = new JButton("Pay");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					PreparedStatement ps = c.prepareStatement("insert into sport_store.payment_info(user_id,user_name,total_amount,purchase_date)values(?,?,?,?)");
					ps.setString(1, U_id.getText());
					ps.setString(2, U_name.getText());
					ps.setString(3,TAmount.getText());
					ps.setString(4, Date.getText());
					int p= ps.executeUpdate();
					if(p > 0) {
						JOptionPane.showMessageDialog(null,"The Payment is complete");
					}
					else {
						JOptionPane.showMessageDialog(null, "The Payment in not complete");
					}
				} catch (Exception e19) {
					System.out.println(e19);
				}

			}
			
		});
		btnNewButton.setBounds(60, 145, 305, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign Out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int y;
			     y=JOptionPane.showConfirmDialog(null,"Do you want to sign out of the Application","select",JOptionPane.YES_NO_OPTION);
				if(y==0) {
				    setVisible(false);
				}
			}
		});
		btnNewButton_1.setBounds(138, 203, 158, 37);
		contentPane.add(btnNewButton_1);
	}

}
