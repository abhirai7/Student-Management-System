package StudentManagement;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;


import javax.swing.*;

import javax.swing.GroupLayout.Alignment;
import javax.swing.table.JTableHeader;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewStudent extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStudent frame = new ViewStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 782, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(249, 199, 132));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 760, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(423, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("Student Details");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(255, 27, 225, 52);
		desktopPane.add(lblNewLabel);

		String data[][] = new String[100][5];
		data[0][0] = "NAME";
		data[0][1] = "ENTRY NO.";
		data[0][2] = "EMAIL";
		data[0][3] = "CONTACT NO.";
		data[0][4] = "HOMETOWN";


		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/studentmanagementsystem";
			Connection connection = DriverManager.getConnection(url, "root", "Abhishek@1234");
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM STUDENT;");
			ResultSet rs =  statement.executeQuery();
			int count = 1;
			while(rs.next()){
				String[] tmp = {"0","0","0","0","0"};
				tmp[0] = rs.getString("name");
				tmp[1] = rs.getString("entrynumber");
				tmp[2] = rs.getString("email");
				tmp[3] =  rs.getString("contactnumber");
				tmp[4] = rs.getString("homecity");
				data[count] = tmp;
				count++;
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		// Column Names
		String[] columnNames = { "NAME", "ENTRY NO.", "EMAIL","CONTACT NO.", "HOMETOWN"};
		JTable jt = new JTable(data,columnNames);
		jt.setBackground(new Color(78, 89, 140));
		jt.setForeground(Color.WHITE);
		jt.setBounds(0, 155, 770, 611);
		jt.getColumnModel().getColumn(0).setPreferredWidth(100);
		jt.getColumnModel().getColumn(1).setPreferredWidth(12);
		jt.getColumnModel().getColumn(2).setPreferredWidth(120);
		jt.getColumnModel().getColumn(3).setPreferredWidth(25);
		jt.getColumnModel().getColumn(4).setPreferredWidth(35);



		jt.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		jt.setRowHeight(30);
		jt.setFont(new Font("Arial", Font.PLAIN, 16));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		jt.setDefaultRenderer(Object.class, centerRenderer);
		jt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		jt.getTableHeader().setBackground(new Color(78, 89, 140));
		getContentPane().add(jt);




		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.show();
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(10, 100, 113, 32);
		desktopPane.add(btnNewButton);
		contentPane.setLayout(gl_contentPane);






	}
}
