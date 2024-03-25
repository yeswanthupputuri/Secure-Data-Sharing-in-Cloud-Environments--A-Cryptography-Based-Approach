import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.DigestInputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JPasswordField;



public class Dataowner implements ActionListener {

	Container c;
	JLabel l1, l2, l3, l4, img, op, op2;
	JButton b1, b2, b3, b4,b5;
	String filename;
	JTextField t1,t2;
	JTextArea ta;

	JPasswordField pf;
	JScrollPane sp;
	JFrame jf;
	Border b, bb;
	File path;
	int y = 0;
	String keyWord = "ef50a0ef2c3e3a5fdf803ae9752c8c66";
	String path1;
	String REQ;
	Cipher encoder;
	public static Key prKey;
	public static Key pubKey;

	public Font f1 = new Font("Times new Roman", Font.BOLD, 17);
	public Font f2 = new Font("Times new Roman", Font.BOLD, 15);
	String mac;
	// String secretkey;
	// String pk;
	String fname;
	File field;
	String SReciever = "";
	String receiver = "";
	String d = "";
	JMenuBar mbr;
	JMenu m1;
	JMenuItem mi1, mi2,mi3;

	static String secretkey;
	static String pk;

	Dataowner(String name) {

		jf = new JFrame("Dataowner::A Blockchain Approach to Ensuring Provenance to Outsourced Cloud Data in a Sharing Ecosystem");
		c = jf.getContentPane();
		c.setLayout(null);
//		jf.setResizable(false);

		c.setBackground(new Color(182, 222, 162));


		ImageIcon banner = new ImageIcon(this.getClass().getResource("title.jpeg"));
		JLabel title=new JLabel();
		title.setIcon(banner);
		title.setBounds(0, 0, 800, 95);


		ta = new JTextArea();
		ta.setColumns(100);
		ta.setRows(100);
		ta.setFont(f2);

		sp = new JScrollPane();
		sp.setViewportView(ta);
		sp.setBounds(360, 150, 320, 220);

		Border b = BorderFactory.createLineBorder(Color.gray, 5);
		op = new JLabel();
		op.setBorder(b);
		op.setBounds(80, 110, 680, 400);

		Border bb = BorderFactory.createLineBorder(new Color(255, 105, 180), 3);
		op2 = new JLabel();
		op2.setBorder(bb);
		op2.setBounds(100, 410,620, 70);

		mbr = new JMenuBar();
		jf.setJMenuBar(mbr);
		m1 = new JMenu("File");
		mi1 = new JMenuItem("View File");
		mi2 = new JMenuItem("Exit");

		mbr.add(m1);
		m1.add(mi1);
		m1.add(mi2);


		l2 = new JLabel("FileName :");
		l2.setFont(f1);
		l2.setBounds(100, 150, 130, 30);

		l3 = new JLabel("username :");
		l3.setFont(f1);
		l3.setBounds(100, 190, 130, 30);
		c.add(l3);

		l4 = new JLabel("Password :");
		l4.setFont(f1);
		l4.setBounds(100, 230, 130, 30);
		c.add(l4);




		t1 = new JTextField();
		t1.setBounds(190, 150, 120, 25);


		t2 = new JTextField();
		t2.setText(name);
		t2.setBounds(190, 190, 120, 25);
		t2.setEditable(false);
		c.add(t2);

		pf = new JPasswordField();
		pf.setBounds(190, 230, 120, 25);
		c.add(pf);

		b1 = new JButton("Submit");

		b1.setForeground(Color.red);

		b2 = new JButton("Send Data ");

		b2.setForeground(Color.red);

		b3 = new JButton("Browse");

		b3.setForeground(Color.red);

		b5=new JButton("Check Data Integrity");

		b5.setForeground(Color.red);

		b1.setBounds(340, 430, 150, 30);
		b1.setFont(f1);

		b3.setBounds(120, 430, 150, 30);
		b3.setFont(f1);

		b5.setBounds(550, 430, 200, 30);
		b5.setFont(f1);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b5.addActionListener(this);
		mi1.addActionListener(this);
		mi2.addActionListener(this);


		c.add(l2);
		c.add(t1);
		c.add(b1);
//		c.add(b2);
		c.add(b3);
		c.add(b5);
		c.add(sp);
		c.add(op);
		c.add(op2);
		c.add(title);

		jf.show();
		jf.setSize(830, 600);


		int[] port = new int[] { 401, 3332};

		for (int i = 0; i < 2; i++) {
			Thread th = new Thread(new PortListener(port[i]));
			th.start();
		}



	}

	public static void main(String[] args) {

//		new Dataowner("sangu");
	}



	class PortListener implements Runnable {
		DataOutputStream dos = null;
		DataInputStream in = null;

		ServerSocket server;
		Socket connection;
		int i;
		String fileid;
		Connection con;
		Statement stmt;
		int port;

		public PortListener(int port) {
			this.port = port;
		}

		public void run()
		{

			if (this.port == 3332)
			{
				try
				{
					ServerSocket s1=new ServerSocket(3332);
					while(true)
					{
						Socket ss1=s1.accept();

						DataInputStream dis=new DataInputStream(ss1.getInputStream());
						String msg=dis.readUTF();

						System.out.println("data recived");

						if(msg.equals("safe"))

						{
							JOptionPane.showMessageDialog(null,"File is Safe");
						}else
						if(msg.equals("notsafe"))
						{
							JOptionPane.showMessageDialog(null,"File is Modified");

						}


						DBCon db=new DBCon();
						Connection con=db.getConnection();

						Statement stmt1 = con.createStatement();
						String sql2="select * from owner where fname='"+fname+"'";
						ResultSet rs=stmt1.executeQuery(sql2);
						String sk="";

						if(rs.next()==true)
						{
							sk=rs.getString(5);
						}

						DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());

						dos.writeUTF(sk);


					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}


		}


	}


	public void actionPerformed(ActionEvent ae) {

		String strLine;
		if (ae.getSource() == b3) {
			JFileChooser chooser = new JFileChooser();

			try {

				File f = new File(new File("filename.txt").getCanonicalPath());

				chooser.setSelectedFile(f);
			} catch (IOException e1) {
			}

			int retval = chooser.showOpenDialog(b3);
			if (retval == JFileChooser.APPROVE_OPTION) {
				File field = chooser.getSelectedFile();

				filename = field.getName();

				t1.setText(filename);

			}

			File curFile = chooser.getSelectedFile();

			try {

				FileInputStream fstream = new FileInputStream(curFile);

				DataInputStream ins = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						ins));

				StringBuffer buffer = new StringBuffer();
				while ((strLine = br.readLine()) != null) {

					buffer.append(strLine + "\n");

				}
				ta.setText(buffer.toString());
				String content = ta.getText();

				FileOutputStream fos = new FileOutputStream("owner/" + filename);
				fos.write(content.getBytes());
				fos.close();

			} catch (Exception e1) {
				System.err.println("Error: " + e1.getMessage());
			}

		}

		if (ae.getSource() == b1)
		{

			String[] sh = { "Select Stakeholder", "Stakeholder1", "Stakeholder2"};
			String shs = (String) JOptionPane.showInputDialog(null,
					"Select peer", "Select", JOptionPane.QUESTION_MESSAGE,
					null, sh, sh[0]);


			String ip = JOptionPane.showInputDialog("Enter Cloud Server IP Address");
			try {
				AES enc = new AES();

				KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
				encoder = Cipher.getInstance("RSA");
				KeyPair kp = kg.generateKeyPair();

				prKey = kp.getPrivate();
				pubKey = kp.getPublic();

				byte[] pub = pubKey.getEncoded();
				System.out.println("PUBLIC KEY" + pub);

				pk = String.valueOf(pub);
				System.out.println("Private key" + pk);

				KeyGenerator2 k = new KeyGenerator2();
				secretkey = String.valueOf(k.getKeys());
				System.out.println("Secretkey" + secretkey);


				String fname1 = ta.getText();
				System.out.println("File Name" + filename);
				MessageDigest md = MessageDigest.getInstance("SHA1");

				FileInputStream in1 = new FileInputStream("owner\\" + filename);
				DigestInputStream dis2 = new DigestInputStream(in1, md);
				BufferedInputStream bd = new BufferedInputStream(dis2);

				while (true) {
					int b2 = bd.read();
					if (b2 == -1)
						break;
				}
				String content = ta.getText();

				BigInteger bi2 = new BigInteger(md.digest());
				mac = bi2.toString(16);
				System.out.println("The generated macis:" + mac);

				String uname=t2.getText();
				String fname=t1.getText();
				String password = new String(pf.getPassword());

				DBCon db=new DBCon();
				Connection con=db.getConnection();
				try {
					Statement st = con.createStatement();
					String sql = "INSERT INTO owner VALUES('" + uname + "','" + fname + "','" + mac + "','" + pk + "','" + secretkey + "','" + shs + "')";
					int result = st.executeUpdate(sql);

					if(result > 0) {
						System.out.println("Record inserted successfully.");
						String sqlFilePasswords = "INSERT INTO file_passwords (filename, password_hash) VALUES('" + fname + "', '" + password + "')";
						try {
							int filePasswordResult = st.executeUpdate(sqlFilePasswords);
							if (filePasswordResult > 0) {
								System.out.println("Filename and password stored successfully.");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("Record not inserted.");
					}
				} catch(SQLException e) {
					System.out.println("SQLException: " + e.getMessage());
				}

				Socket ss=new Socket("localhost",2001);
				DataOutputStream dos=new DataOutputStream(ss.getOutputStream());
				dos.writeUTF(shs);
				dos.writeUTF(fname);
				dos.writeUTF(mac);
				dos.writeUTF(pk);
				dos.writeUTF(secretkey);
				dos.writeUTF(content);
				dos.writeUTF(uname);
				System.out.println("All data send to server");

				DataInputStream dii=new DataInputStream(ss.getInputStream());
				String msg=dii.readUTF();
				if(msg.equals("valid"))
				{
					JOptionPane.showMessageDialog(null, "File Uploaded Successfully");
				}

				// Reset fields after operation
				t1.setText(""); // Clear filename field
				pf.setText(""); // Clear password field

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				// Assuming you've saved the file in the "owner" folder and filename is the name of the file.
				String localFilePath = "owner/" + filename;
				System.out.println(localFilePath);
				FileOutputStream fos = new FileOutputStream(localFilePath);
				String content = ta.getText(); // Assuming 'ta' is your JTextArea where file content is written.
				fos.write(content.getBytes());
				fos.close();

				// Now upload to Azure Blob Storage.
				// Determine the container name based on the selected stakeholder.
				String containerName = ""; // Initialize container name.
				if ("Stakeholder1".equals(shs)) {
					containerName = "peer1";
				} else if ("Stakeholder2".equals(shs)) {
					containerName = "peer2";
				}

				// Initialize the AzureConnector with your container name.
				AzureConnector azureConnector = new AzureConnector(containerName);

				// Upload the file to Azure Blob Storage.
				azureConnector.uploadFile(localFilePath);

				// Show confirmation message.
				JOptionPane.showMessageDialog(null, "File uploaded to Azure Blob Storage successfully!");

				// After uploading, clear the fields or perform any other final actions.
				t1.setText(""); // Clear filename field.
				pf.setText(""); // Clear password field.
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error during file upload: " + ex.getMessage());
			}
		}

		if (ae.getSource() == b2)
		{

			String fname = JOptionPane.showInputDialog(null, "Enter File Name");

			String csname = JOptionPane
					.showInputDialog("Enter cloud Server name");

			String ip = JOptionPane
					.showInputDialog("Enter Audit cloud IP Address");

			try {
				Socket s = new Socket(ip, 1999);

				DataOutputStream dos1 = new DataOutputStream(s
						.getOutputStream());

				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con = DriverManager.getConnection("jdbc:odbc:Audit");
				Statement stmt = con.createStatement();

				String sql = " select * from ownerfiles where Filename='"
						+ fname + "' and CS = '" + csname + "'  ";

				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next() == true) {

					filename = fname;
					mac = rs.getString(5);
					pk = rs.getString(3);
					secretkey = rs.getString(4);

					System.out.println("inside 1006");

					dos1.writeUTF(csname);
					dos1.writeUTF(filename);
					dos1.writeUTF(mac);
					dos1.writeUTF(secretkey);
					dos1.writeUTF(pk);

					System.out.println("Data sent to Auditcloud");

					DataInputStream dis = new DataInputStream(s
							.getInputStream());
					String msg = dis.readUTF();
					if (msg.equals("success")) {
						JOptionPane.showMessageDialog(null,
								"Data sent to Audit cloud");
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (ae.getSource() == mi1)
		{

			String name=t2.getText();

			DBCon db=new DBCon();
			Connection con=db.getConnection();

			Vector data=new Vector();

			try
			{
				Statement stmt1 = con.createStatement();
				String sql2="select * from owner where username='"+name+"'";
				ResultSet rs=stmt1.executeQuery(sql2);
				System.out.println("Query exe");

				ResultSetMetaData rsm=rs.getMetaData();
				int col=rsm.getColumnCount();

				while(rs.next()==true)
				{
					Vector row = new Vector();
					for(int i = 1; i <=col; i++){
						row.addElement(rs.getObject(i));

					}

					data.addElement(row);

				}

			} catch (Exception e) {
				// TODO: handle exception
			}

			Viewownerfiles tr=new Viewownerfiles(data);







		}

		if(ae.getSource()==mi2)
		{

			String name = JOptionPane.showInputDialog("Enter Username To UnBlock");
			String ip = JOptionPane.showInputDialog("Enter LBS server IP Address");

			try
			{
				Socket ss=new Socket(ip,1002);
				DataOutputStream dos=new DataOutputStream(ss.getOutputStream());
				dos.writeUTF(name);
				System.out.println("name send to server");


				DataInputStream diis=new DataInputStream(ss.getInputStream());
				String msg=diis.readUTF();

				JOptionPane.showMessageDialog(null,"User Removed from Atttacker List");


			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		if(ae.getSource()==b5)
		{
			String fname = JOptionPane.showInputDialog(null,"Please Enter the File Name");

			String name = JOptionPane.showInputDialog(null,"Please Enter the Stakeholder Name(Stakeholder1,Stakeholder2)");

			String ip = JOptionPane.showInputDialog(null,"Please Enter the Data Provenance System server IP Address");


			try {
				Socket soc1 = new Socket(ip, 6767);
				System.out.println("Inside 6767");

				DBCon db=new DBCon();
				Connection con=db.getConnection();

				DataOutputStream dos2 = new DataOutputStream(soc1.getOutputStream());
				dos2.writeUTF(fname);
				dos2.writeUTF(name);

				System.out.println("Varify msg send to Data Provenance System");

			} catch (Exception e2) {

			}

		}

	}
}





