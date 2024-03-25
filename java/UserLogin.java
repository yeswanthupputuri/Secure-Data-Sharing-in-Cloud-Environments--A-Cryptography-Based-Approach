import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserLogin implements ActionListener {

	JFrame jf;
	Container c;
	JLabel l1, l2,l3;
	JTextField t1,t4;
	JButton b1, b2, b3;
	JPasswordField t2,t3;
	JComboBox node1 ;
	public Font f3 = new Font("Times new Roman", Font.BOLD, 18);
	
	UserLogin() {

		jf = new JFrame("User-Login::A Blockchain Approach to Ensuring Provenance to Outsourced Cloud Data in a Sharing Ecosystem");
		c = jf.getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(216, 196, 198));
		l1 = new JLabel("User-Name");
		l2 = new JLabel("Password");
		l3 = new JLabel("User Login Page");
		t1 = new JTextField();
		t2 = new JPasswordField(15);
		t2.setEchoChar('*');
		b1 = new JButton("Login");
		b2 = new JButton("Reset");
		b3 = new JButton("Register");
		l1.setBounds(50, 110, 100, 25);
		l2.setBounds(50, 160, 100, 25);
		l3.setBounds(100, 50, 200, 25);
		l3.setFont(f3);
		t1.setBounds(150, 110, 150, 25);
		t2.setBounds(150, 160, 150, 25);
		b1.setBounds(40,280, 100, 30);
		b2.setBounds(260, 280, 100, 30);
		b3.setBounds(150, 280, 100, 30);
		c.add(l1);
		c.add(l2);
		c.add(l3);
		c.add(t1);
		c.add(t2);
		c.add(b1);
		c.add(b2);
		c.add(b3);
		jf.setBounds(50, 100, 400, 400);
		jf.setVisible(true);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}

	public static void main(String[] args) {
		new UserLogin();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) 
		{

			String name = t1.getText();
			String pass = t2.getText();
			
			String ip=JOptionPane.showInputDialog("Enter Cloud server Ip Address");

			try {
				Socket s=new Socket(ip,4006);
				DataOutputStream dos=new DataOutputStream(s.getOutputStream());
				dos.writeUTF(name);
				dos.writeUTF(pass);
	
				System.out.println("Req sent to cloud server");
				
				DataInputStream dis=new DataInputStream(s.getInputStream());
				String msg=dis.readUTF();
				if(msg.equals("success"))
				{ 
					 JOptionPane.showMessageDialog(null,"You are Successfully Login");
					new Enduser(name);
					
					 
				}else
					if(msg.equals("fail"))
					{
						 JOptionPane.showMessageDialog(null,"Please enter valid Username and password");
					}

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		if (e.getSource() == b2) {
			
			 t1.setText("");
			 t2.setText("");

		}
		if (e.getSource() == b3) 
		{

		UserRegistration user=new UserRegistration();

		}

	}

}
