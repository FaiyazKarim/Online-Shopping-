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
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class UserLogin extends JFrame {

	private JPanel contentPane;
	private JTextField User_name;
	private JTextField User_email;
	private JTextField User_password;
	private JTextField U_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
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
	public UserLogin() {
		setTitle("Buyers Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel User_nameLabel = new JLabel(" User name");
		User_nameLabel.setBounds(29, 102, 65, 32);
		contentPane.add(User_nameLabel);
		
		User_name = new JTextField();
		User_name.setBounds(104, 108, 190, 20);
		contentPane.add(User_name);
		User_name.setColumns(10);
		
		
		
		JLabel User_passwordLabel = new JLabel("Password");
		User_passwordLabel.setBounds(29, 177, 46, 14);
		contentPane.add(User_passwordLabel);
		
		User_password = new JTextField();
		User_password.setBounds(104, 174, 190, 20);
		contentPane.add(User_password);
		User_password.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					PreparedStatement ps = connect.prepareStatement("insert into user_info(user_id,user_name,user_password)values(?,?,?)");
					ps.setString(1, U_id.getText());
					ps.setString(2, User_name.getText());
					ps.setString(3, User_password.getText());
					int x= ps.executeUpdate();
					if (x > 0) {
						JOptionPane.showMessageDialog(null,"The user is successfully sign in");	
					}
					else {
						JOptionPane.showMessageDialog(null," The user is not signed in");
						ProductInformation info = new ProductInformation();
						info.setVisible(true);
					}
					
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton.setBounds(26, 221, 89, 32);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					Statement statement = con.createStatement();
					String sql = "SELECT * FROM sport_store. user_info where user_id='"+U_id.getText()+"'and user_password='"+User_password.getText()+"'";
					ResultSet set = statement.executeQuery(sql);
					  if(set.next()){
						  JOptionPane.showMessageDialog(null, "Welcome "+set.getString(2));
						  ProductInformation info = new ProductInformation();
							info.setVisible(true);
					}
					else {
						
						 JOptionPane.showMessageDialog(null, "Missing Information");
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		btnNewButton_1.setBounds(205, 221, 89, 32);
		contentPane.add(btnNewButton_1);
		
		JLabel User_IDLabel = new JLabel("User Id");
		User_IDLabel.setBounds(29, 45, 46, 14);
		contentPane.add(User_IDLabel);
		
		U_id = new JTextField();
		U_id.setBounds(104, 42, 179, 20);
		contentPane.add(U_id);
		U_id.setColumns(10);
	}
}
