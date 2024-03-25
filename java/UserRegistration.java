import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserRegistration implements ActionListener
{

	JFrame jf;
	Container c;
	JLabel l1, l2, l3, l4, l5,l6;
	JTextField t1,t3, t4,t7,t5,t6;
	JPasswordField t2;
	JLabel lab1,lab2;
	JButton b1, b2;

	JComboBox node1 ;
	public Font f3 = new Font("Times new Roman", Font.BOLD, 18);
	
	UserRegistration() 
	{
		jf = new JFrame("Registeration::A Blockchain Approach to Ensuring Provenance to Outsourced Cloud Data in a Sharing Ecosystem");
		c = jf.getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(216, 196, 198));
		
		lab1=new JLabel("User Registration Form");
		lab1.setBounds(50, 50, 300, 25);
		lab1.setFont(f3);
	    c.add(lab1);

		l1 = new JLabel("Username");
		l2 = new JLabel("Password");
		l3 = new JLabel("Address");
		l4 = new JLabel("City");
		l5 = new JLabel("Email");
		l6 = new JLabel("Mobile");
		 

		t1 = new JTextField();
		t2 = new JPasswordField(15);
		t2.setEchoChar('*');
		t3 = new JTextField();
		t4 = new JTextField();
		t5 = new JTextField();
		t6 = new JTextField();

		b1 = new JButton("Submit");
		b2 = new JButton("Reset");

		l1.setBounds(50, 130, 100, 25);
		l2.setBounds(50, 180, 100, 25);
		l3.setBounds(50, 230, 100, 25);
		l4.setBounds(50, 280, 190, 25);
		l5.setBounds(50, 330, 190, 25);
		l6.setBounds(50, 380, 190, 25);
		c.add(l6);
		c.add(l5);

		t1.setBounds(150, 130, 150, 25);
		t2.setBounds(150, 180, 150, 25);
		t3.setBounds(150, 230, 150, 25);
		t4.setBounds(150, 280, 150, 25);
		t5.setBounds(150, 330, 150, 25);
		t6.setBounds(150, 380, 150, 25);
		c.add(t6);
		c.add(t5);

		b1.setBounds(70, 450, 100, 30);
		b2.setBounds(200, 450, 100, 30);

		c.add(l1);
		c.add(l2);
		c.add(l3);
		c.add(l4);
		
		c.add(t1);
		c.add(t2);
		c.add(t3);
		c.add(t4);
		
		c.add(b1);
		c.add(b2);

		jf.setBounds(100, 100, 470, 600);
		jf.show();

		b1.addActionListener(this);
		b2.addActionListener(this);
//		node1.addActionListener(this);
	}

	public static void main(String[] args) {
		new UserRegistration();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b1) 
		{

			try {
				
				String name=t1.getText();
				String pwd=t2.getText();
				String add=t3.getText();
				String city=t4.getText();
				String email=t5.getText();
				String mobile=t6.getText();
				
				String ip1 = JOptionPane.showInputDialog(null,
				"Enter Cloud server Ip Address");
			
				Socket sc=new Socket(ip1,4007);
				DataOutputStream dos=new DataOutputStream(sc.getOutputStream());
				dos.writeUTF(name);
				dos.writeUTF(pwd);
				dos.writeUTF(add);
				dos.writeUTF(city);
				dos.writeUTF(email);
				dos.writeUTF(mobile);
				
				System.out.println("   "+name);
				System.out.println("   "+pwd);
				System.out.println("   "+add);
				System.out.println("   "+city);
				System.out.println("   "+email);
				System.out.println("   "+mobile);
				

				System.out.println("All data sent to Cloud server");
				
				DataInputStream dis=new DataInputStream(sc.getInputStream());
				String msg=dis.readUTF();
				if(msg.equals("taken"))
				{
					JOptionPane.showMessageDialog(null, "Registration Completed");
				}
				
				
			} catch (Exception ex) {

			}
		}
		if (e.getSource() == b2) 
		{

			System.exit(0);
			
		}
		
		if(e.getSource()==node1)
		{
			String perm=node1.getSelectedItem().toString();
			if(perm.equals("Group1"))
			{
				t7.setText(".java");
			}else
				if(perm.equals("Group2"))
				{
					t7.setText(".txt");
				}else
					if(perm.equals("Group3"))
					{
						t7.setText(".log");
					}
			
		}
	
	}
}
