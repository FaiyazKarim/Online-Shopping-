import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstLogin {

	private JFrame firstFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstLogin window = new FirstLogin();
					window.firstFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		firstFrame = new JFrame();
		firstFrame.setTitle("Welcome to the SDUT Sports store");
		firstFrame.setBounds(100, 100, 375, 218);
		firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firstFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SDUT Sports Store");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(84, 47, 186, 33);
		firstFrame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Buyer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstFrame.dispose();
				UserLogin login = new UserLogin();
				login.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(25, 122, 89, 33);
		firstFrame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Employee");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstFrame.dispose();
				SignUpForm employeeForm = new SignUpForm();
				employeeForm.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(238, 122, 89, 33);
		firstFrame.getContentPane().add(btnNewButton_1);
	}
}
