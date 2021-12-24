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

public class ControlPanel extends JFrame {

	private JPanel contentPane;
	private JTable table2;
	private JTextField PCode;
	private JTextField Pname;
	private JTextField C_Stock;
	private JTextField P_Price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlPanel frame = new ControlPanel();
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
	public ControlPanel() {
		setTitle("Shop Control Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 766, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(279, 11, 461, 348);
		contentPane.add(scrollPane);
		
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Purchase_Code", "Product_Name", "Current_Stock", "Purchase_Price"
			}
		));
		table2.getColumnModel().getColumn(0).setPreferredWidth(85);
		table2.getColumnModel().getColumn(1).setPreferredWidth(85);
		table2.getColumnModel().getColumn(2).setPreferredWidth(85);
		table2.getColumnModel().getColumn(3).setPreferredWidth(85);
		table2.setColumnSelectionAllowed(true);
		table2.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table2);
		
		JLabel Product_CodeLabel = new JLabel("Purchase_Code");
		Product_CodeLabel.setBounds(11, 18, 94, 27);
		contentPane.add(Product_CodeLabel);
		
		PCode = new JTextField();
		PCode.setBounds(11, 43, 86, 20);
		contentPane.add(PCode);
		PCode.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Product_name");
		lblNewLabel.setBounds(11, 74, 86, 27);
		contentPane.add(lblNewLabel);
		
		Pname = new JTextField();
		Pname.setBounds(11, 98, 94, 20);
		contentPane.add(Pname);
		Pname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Current_Stock");
		lblNewLabel_1.setBounds(135, 24, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		C_Stock = new JTextField();
		C_Stock.setBounds(124, 43, 86, 20);
		contentPane.add(C_Stock);
		C_Stock.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Product_Price");
		lblNewLabel_2.setBounds(11, 138, 86, 20);
		contentPane.add(lblNewLabel_2);
		
		P_Price = new JTextField();
		P_Price.setBounds(11, 160, 86, 20);
		contentPane.add(P_Price);
		P_Price.setColumns(10);
		
		JButton btnNewButton = new JButton("Show Table");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					Statement statement = c.createStatement();
					String sql = "SELECT * FROM sport_store.product_detail";
					ResultSet rs = statement.executeQuery(sql);
					
					while(rs.next()) {

						String purchase_code = String.valueOf(rs.getString(1));
						String product_name = String.valueOf(rs.getString(2));
						String current_stock = String.valueOf(rs.getString(3));
						String product_price = String.valueOf(rs.getString(4));
						
						String tbdata[] = {purchase_code,product_name,current_stock,product_price};
						DefaultTableModel tbModel = (DefaultTableModel)table2.getModel();
						tbModel.addRow(tbdata);
						
					}
				} catch (Exception e4) {
				  System.out.println(e4);
				}
			}
		});
		btnNewButton.setBounds(8, 212, 114, 32);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
			         String sql = "Update sport_store.product_detail set purchase_code='"+PCode.getText()+"',product_name='"+Pname.getText()+"',current_stock='"+C_Stock.getText()+"',product_price='"+P_Price.getText()+"'where purchase_code='"+PCode.getText()+"'";
			         PreparedStatement statement = c.prepareStatement(sql);
			         statement.execute();
			         JOptionPane.showMessageDialog(null, "The information is updated");
			         statement.close();
				} catch (Exception e10) {
					System.out.println(e10);
				}
			}
		});
		btnNewButton_1.setBounds(148, 264, 87, 32);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					String sql = "Delete from product_detail where purchase_code =?";
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1,PCode.getText());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "The Information is deleted successfully");
					
				} catch (Exception e11) {
					System.out.println(e11);
				}
			}
		});
		btnNewButton_2.setBounds(11, 264, 89, 32);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Sign Out");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int y;
			     y=JOptionPane.showConfirmDialog(null,"Do you want to sign out of the Application","select",JOptionPane.YES_NO_OPTION);
				if(y==0) {
				    setVisible(false);
				}
			}
		});
		btnNewButton_3.setBounds(8, 327, 89, 32);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Add");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					PreparedStatement ps = connect.prepareStatement("insert into product_detail(purchase_code,product_name,current_stock,product_price)values(?,?,?,?)");
					ps.setString(1, PCode.getText());
					ps.setString(2, Pname.getText());
					ps.setString(3, C_Stock.getText());
					ps.setString(4, P_Price.getText());
					int x= ps.executeUpdate();
					if ( x > 0) {
						JOptionPane.showMessageDialog(null,"The data is inserted successfully");
						
					}
					else {
						JOptionPane.showMessageDialog(null,"The data is not inserted into the table ");
					}
					
				} catch (Exception e7) {
					System.out.println(e7);
				}
			}
		});
		btnNewButton_4.setBounds(148, 212, 89, 32);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Search");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store","root","1234");
					String sql = "SELECT * FROM sport_store.product_detail where purchase_code='"+PCode.getText()+"'";
					PreparedStatement ps = c.prepareStatement(sql);
					ResultSet rs = ps.executeQuery(sql);
					
					while(rs.next()) {
						String purchase_code = String.valueOf(rs.getString(1));
						String product_name = String.valueOf(rs.getString(2));
						String current_stock = String.valueOf(rs.getString(3));
						String product_price = String.valueOf(rs.getString(4));
						
						String tbdata[] = {purchase_code,product_name,current_stock,product_price};
						DefaultTableModel tbModel = (DefaultTableModel)table2.getModel();
						tbModel.addRow(tbdata);
					}
					
					JOptionPane.showMessageDialog(null, "The Information doesn't exist");
					
				} catch (Exception e12) {
					System.out.println(e12);
				}
			}
		});
		btnNewButton_5.setBounds(148, 154, 89, 32);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Payment Info");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaymentInformation paymentInfo = new PaymentInformation();
				paymentInfo.setVisible(true);
			}
		});
		btnNewButton_6.setBounds(148, 327, 121, 32);
		contentPane.add(btnNewButton_6);
	}
}
