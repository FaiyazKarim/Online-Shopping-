import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ProductInformation extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInformation frame = new ProductInformation();
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
	public ProductInformation() {
		setTitle("The list of Products into the Table");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 417, 302);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Purchase_Code", "Product_Name", "Current_Stock", "Product_price"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Load table");
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
						DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
						tbModel.addRow(tbdata);
						
					}
				} catch (Exception e4) {
				  System.out.println(e4);
				}
			}
		});
		btnNewButton.setBounds(437, 21, 117, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Purchase");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PurchaseForm form = new PurchaseForm();
				form.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(447, 258, 107, 37);
		contentPane.add(btnNewButton_1);
	}

}
