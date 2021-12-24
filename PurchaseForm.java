import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JTextField;

import javax.swing.JButton;

import javax.swing.JTextArea;

import java.awt.event.ActionListener;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.awt.event.ActionEvent;

public class PurchaseForm extends JFrame {

	private JPanel contentPane;

	private JTextField PCode;

	private JTextField Ptname;

	private JTextField QProduct;

	private JTextField P_price;

	private JTextField T_price;

	/**
	 * 
	 * 
	 * 
	 * Launch the application.
	 * 
	 * 
	 * 
	 */

	int Price = 0, price = 0, quantity = 0, Total = 0, SubTotal = 0, Tax = 0, i = 0, j = 0, k = 0, t = 0;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					PurchaseForm frame = new PurchaseForm();

					frame.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		});

	}

	/**
	 * 
	 * 
	 * 
	 * Create the frame.
	 * 
	 * 
	 * 
	 */

	public PurchaseForm() {

		setTitle("Purchase Form");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 603, 455);

		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		contentPane.setLayout(null);

		JLabel Purchase_CodeLabel = new JLabel("Purchase Code");

		Purchase_CodeLabel.setBounds(20, 50, 93, 24);

		contentPane.add(Purchase_CodeLabel);

		PCode = new JTextField();

		PCode.setBounds(20, 73, 93, 20);

		contentPane.add(PCode);

		PCode.setColumns(10);

		JLabel Product_NameLabel = new JLabel("Product Name");

		Product_NameLabel.setBounds(32, 120, 81, 24);

		contentPane.add(Product_NameLabel);

		Ptname = new JTextField();

		Ptname.setBounds(27, 142, 86, 20);

		contentPane.add(Ptname);

		Ptname.setColumns(10);

		JLabel QuantityofProductLabel = new JLabel("Quantity");

		QuantityofProductLabel.setBounds(166, 52, 73, 20);

		contentPane.add(QuantityofProductLabel);

		QProduct = new JTextField();

		QProduct.setBounds(142, 73, 86, 20);

		contentPane.add(QProduct);

		QProduct.setColumns(10);

		JLabel Product_PriceLabel = new JLabel("Product price");

		Product_PriceLabel.setBounds(147, 120, 81, 14);

		contentPane.add(Product_PriceLabel);

		P_price = new JTextField();

		P_price.setBounds(142, 142, 86, 20);

		contentPane.add(P_price);

		P_price.setColumns(10);

		T_price = new JTextField();

		T_price.setBounds(22, 207, 86, 20);

		contentPane.add(T_price);

		T_price.setColumns(10);

		JTextArea BillArea = new JTextArea();

		BillArea.setBounds(249, 52, 328, 298);

		contentPane.add(BillArea);

		JButton btnNewButton = new JButton("Purchase ");

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					Class.forName("com.mysql.cj.jdbc.Driver");

					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Sport_store", "root",

							"1234");

					PreparedStatement ps = con.prepareStatement(

							"insert into transaction_info(purchase_code,product_name,product_quantity,product_price,total_price)values(?,?,?,?,?)");

					ps.setString(1, PCode.getText());

					ps.setString(2, Ptname.getText());

					ps.setString(3, QProduct.getText());

					ps.setString(4, P_price.getText());

					ps.setString(5, T_price.getText());

					int x = ps.executeUpdate();

					int total = 0, Sub_total = 0, price = 0, quantity = 0, i = 0, j = 0, Price = 0;

					if (PCode.getText().isEmpty() || Ptname.getText().isEmpty() || QProduct.getText().isEmpty()

							|| P_price.getText().isEmpty()) {

						JOptionPane.showMessageDialog(null, "No Information is given");

					}

					else {

						JOptionPane.showMessageDialog(null, " The purchase is Added");

					}

				}

				catch (Exception e5) {

					System.out.println(e5);

				}

			}

		});

		btnNewButton.setBounds(10, 369, 98, 34);

		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Recipt ");

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				price = Integer.parseInt(P_price.getText());

				quantity = Integer.parseInt(QProduct.getText());

				if (i == 0) {

					i++;

					j = price * quantity;

					SubTotal = j;

				}

				else {

					j = price * quantity;

					SubTotal += j;

				}

				int f = 0;

				if (f == 0) {

					f++;

					BillArea.setText(BillArea.getText() + " Product Name \t Quantity \t Price \n" + Ptname.getText()

							+ "\t" + QProduct.getText() + "\t" + T_price.getText() + j + "\n");

				}

				else {

					BillArea.setText(BillArea.getText() + "" + Ptname.getText() + "\t" + QProduct.getText() + "\t"

							+ T_price.getText() + j);

				}

			}

		});

		btnNewButton_1.setBounds(10, 320, 89, 34);

		contentPane.add(btnNewButton_1);

		JButton btnNewButton_3 = new JButton("Print");

		btnNewButton_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					BillArea.print();

				} catch (Exception e15) {

					System.out.println(e15);

				}

			}

		});

		btnNewButton_3.setBounds(292, 361, 252, 34);

		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Total");

		btnNewButton_4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (SubTotal < 100) {

					Tax = 2;

					t = (SubTotal * 2) / 100;

					Total = t + SubTotal;

					BillArea.setText(BillArea.getText() + "\n\n\n\nSubTotal= " + SubTotal);

					BillArea.setText(BillArea.getText() + "\nTax = " + t + "\nTotal= " + Total);

				} else if (SubTotal > 100) {

					Tax = 5;

					t = (SubTotal * 5) / 100;

					Total = t + SubTotal;

					BillArea.setText(BillArea.getText() + "\n\n\n\nSubTotal"+"\t" + SubTotal);

					BillArea.setText(BillArea.getText() + "\nTax \t" + t +"\t"+"\t"+"\t"+"\nTotal  " + Total);

				} else {

					Tax = 10;

					t = (SubTotal * 10) / 100;

					Total = t + SubTotal;

					BillArea.setText(BillArea.getText() + "\n\n\n\nSubTotal \t " + SubTotal);

					BillArea.setText(BillArea.getText() + "\nTax = " + t + "\nTotal "+"\t" +"\t"+ Total);

				}

			}

		});

		btnNewButton_4.setBounds(118, 320, 89, 30);

		contentPane.add(btnNewButton_4);

		JLabel lblNewLabel = new JLabel("Total");

		lblNewLabel.setBounds(21, 182, 46, 14);

		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_5 = new JButton("Payment");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payment pay = new Payment();
				pay.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(118, 369, 89, 34);
		contentPane.add(btnNewButton_5);

	}

}