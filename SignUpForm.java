import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mysql.cj.Query;
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

public class SignUpForm extends JFrame {

	private JPanel contentPane;
	private JTextField tfid;
	private JTextField tfpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpForm frame = new SignUpForm();
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
	public SignUpForm() {
		setTitle("Employers Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Employers_IDLabel = new JLabel("Employers_ID");
		Employers_IDLabel.setBounds(32, 73, 87, 24);
		contentPane.add(Employers_IDLabel);
		
		tfid = new JTextField();
		tfid.setBounds(129, 75, 188, 20);
		contentPane.add(tfid);
		tfid.setColumns(10);
		
		JLabel Employers_password = new JLabel("Password");
		Employers_password.setBounds(32, 134, 66, 24);
		contentPane.add(Employers_password);
		
		tfpassword = new JTextField();
		tfpassword.setBounds(133, 136, 169, 20);
		contentPane.add(tfpassword);
		tfpassword.setColumns(10);
		
		JButton Submit = new JButton("Sign Up");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					Statement statement = c.createStatement();
					String sql = "SELECT * FROM sport_store.employers_info where Employers_id='"+tfid.getText()+"'and Employers_password='"+tfpassword.getText()+"'";
					ResultSet result = statement.executeQuery(sql);
					if(result.next()) {
						JOptionPane.showMessageDialog(null, "Welcome "+result.getString(2));
						EmployeeDetails details = new EmployeeDetails();
						details.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Password invalid");
					}
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		});
		Submit.setBounds(127, 211, 89, 23);
		contentPane.add(Submit);
	}
}
