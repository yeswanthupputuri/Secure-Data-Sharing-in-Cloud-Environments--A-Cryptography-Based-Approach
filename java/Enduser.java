import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.Thread.State;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ReadOnlyBufferException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.swing.*;
public class Enduser implements ActionListener {
	JFrame f;
	JFrame ff;


	JPanel p;
	JPanel pp;

	JButton b1, b2, b3, b4;
	JTextField t1, t2, t3, t4;


	JPanel panel;
	Vector data;
	Vector heading;

	ImageIcon ic;
	JScrollPane pane;

	ImageIcon ic1, ic2, ic3, ic4, ic5, ic6, ic7, ic8, ic9, ic11, ic22, ic33, ic44, ic55, ic66, ic77, ic88, ic99, icc1, icc2, icc3, icc4, icc5, lab1, lab2, lab3, lab4;
	JLabel op, op1, l1, l2, l3, l4, l5, l6, l7, l8, l9, l11, l22, l33, l44, l55, l66, l77, l88, l99, lb1, lb2, lb3, lb4, lb5, lb6, lb7, lb8, lb9;
	JLabel label1, label2, label3, label4, label5, label6, label7;

	JLabel lbb1, lbb2, lbb3, lbb4, lbb5, lbb6, lbb7, lbb8;

	String Data = "";

	public Font f1 = new Font("Times new Roman", Font.BOLD, 17);
	public JTextArea tf = new JTextArea();
	public JTextField fname = new JTextField();
	public JScrollPane pane1 = new JScrollPane();

	public Enduser(String name) {

		f = new JFrame("EndUser::A Blockchain Approach to Ensuring Provenance to Outsourced Cloud Data in a Sharing Ecosystem");
		p = new JPanel();

		ImageIcon banner = new ImageIcon(this.getClass().getResource("titl.jpeg"));
		JLabel title = new JLabel();
		title.setIcon(banner);
		title.setBounds(0, 0, 800, 95);


		ff = new JFrame();

		pp = new JPanel();

		p.setBackground(new Color(128, 128, 128));


		b1 = new JButton("Download");
		b1.setBounds(60, 460, 150, 30);
		b1.setFont(f1);
		p.add(b1);

		b2 = new JButton("Request Sk");
		b2.setBounds(240, 460, 150, 30);
		b2.setFont(f1);
//	  p.add(b2);

		b3 = new JButton("View Files");
		b3.setBounds(240, 460, 150, 30);
		b3.setFont(f1);
		p.add(b3);

		b4 = new JButton("Modify Files");
		b4.setBounds(420, 460, 150, 30);
		b4.setFont(f1);
		p.add(b4);

		lbb1 = new JLabel("Username");
		lbb1.setBounds(450, 70, 150, 150);
		lbb1.setFont(f1);
		p.add(lbb1);

		lbb2 = new JLabel("Filename");
		lbb2.setBounds(450, 130, 150, 150);
		lbb2.setFont(f1);
		p.add(lbb2);

		lbb3 = new JLabel("ScretKey");
		lbb3.setBounds(450, 190, 150, 150);
		lbb3.setFont(f1);
		p.add(lbb3);

		t1 = new JTextField();
		t1.setBounds(540, 130, 150, 30);
		t1.setText(name);
		t1.setFont(f1);
//	  t1.setEditable(false);
		p.add(t1);

		t2 = new JTextField();
		t2.setBounds(540, 190, 150, 30);
		t2.setFont(f1);
		p.add(t2);

		t3 = new JTextField();
		t3.setBounds(540, 250, 150, 30);
		t3.setFont(f1);
		p.add(t3);


		ic1 = new ImageIcon(this.getClass().getResource("systems.jpg"));
		lb1 = new JLabel();
		lb1.setIcon(ic1);
		lb1.setBounds(10, 50, 440, 450);
//	  p.add(lb1);


		tf.setColumns(200);
		tf.setRows(100);
		tf.setName("tf");
		pane1.setName("pane");
		pane1.setViewportView(tf);
		pane1.setBounds(60, 110, 340, 240);
		p.add(pane1);

		f.setSize(800, 570);
		f.setVisible(true);
		p.setLayout(null);
		p.add(title);
		f.add(p);


		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		int[] port = new int[]{401, 1001, 201, 2323, 5005, 5008, 5554};

		for (int i = 0; i < 7; i++) {
			Thread th = new Thread(new PortListener(port[i]));
			th.start();
		}


	}

	public static void main(String[] args) {
//	new Enduser("adas");
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

		public void run() {
			if (this.port == 1001) {
				try {
					AES enc = new AES();
					DBCon db = new DBCon();
					Connection con = db.getConnection();

					ServerSocket s1 = new ServerSocket(1001);
					while (true) {
						Socket ss1 = s1.accept();

						DataInputStream dis = new DataInputStream(ss1.getInputStream());
						String fname = dis.readUTF();
						String key = dis.readUTF();
						String pk = dis.readUTF();
						String mac = dis.readUTF();
						String sk = dis.readUTF();

						System.out.println("data recived");

						Statement stmt1 = con.createStatement();
						String sql2 = "insert into metadata values('" + fname + "','" + key + "','" + pk + "','" + mac + "','" + sk + "')";
						stmt1.executeUpdate(sql2);
						System.out.println("Data inserted into manager db");


						DataOutputStream dos = new DataOutputStream(ss1.getOutputStream());
						dos.writeUTF("reach");
						System.out.println("reach msg send to engine");


					}
				} catch (Exception e) {
					// TODO: handle exception
				}


			}

			if (this.port == 5005) {


			}

			if (this.port == 5008) {

				try {
					AES enc = new AES();
					DBCon db = new DBCon();
					Connection con = db.getConnection();

					ServerSocket s1 = new ServerSocket(5008);
					while (true) {
						Socket ss1 = s1.accept();

						DataInputStream dis = new DataInputStream(ss1.getInputStream());

						String msg = dis.readUTF();
						String fname = dis.readUTF();
						String fakem = dis.readUTF();
						String date = dis.readUTF();

						System.out.println("data recived");

						Statement stmt1 = con.createStatement();
						String sql2 = "insert into Attacker values('" + fname + "','" + fakem + "','" + date + "')";
						stmt1.executeUpdate(sql2);
						System.out.println("Data inserted into manager db");


						DataOutputStream dos = new DataOutputStream(ss1.getOutputStream());
						dos.writeUTF("taken");
						System.out.println("reach msg send to pattern");


					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

			if (this.port == 5554) {
				try {
					DBCon db = new DBCon();
					Connection con = db.getConnection();

					ServerSocket s1 = new ServerSocket(5554);
					while (true) {
						Socket ss1 = s1.accept();

						DataInputStream dis = new DataInputStream(ss1.getInputStream());

						String msg = dis.readUTF();
						String content = dis.readUTF();

						System.out.println("");

						if (msg.equals("success")) {
							JOptionPane.showMessageDialog(null, "File Recived Successfully");
							System.out.println("content   " + content);
							tf.setText(content);
							Data = content;
						} else if (msg.equals("fail")) {
							JOptionPane.showMessageDialog(null, "Wrong File name And Sk you are Blocked");
						} else if (msg.equals("failure")) {
							JOptionPane.showMessageDialog(null, "Sorry You Are Not a Valid User");
						} else if (msg.equals("not ok")) {
							JOptionPane.showMessageDialog(null, "Sorry You Are Currently Blocked By Server");
						}


					}
				} catch (Exception e) {
					// TODO: handle exception
				}


			}


		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == b1) {
			String filename = t2.getText();
			if (filename.isEmpty()) {
				JOptionPane.showMessageDialog(f, "Please enter a filename to download.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			AzureConnector azureConnector = new AzureConnector();

			String fileContent = azureConnector.getFileContent(filename);

			if (fileContent != null) {
				tf.setText(fileContent);
				System.out.println("data received from azure cloud");
			} else {
				JOptionPane.showMessageDialog(f, "File not found in Azure Blob Storage.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (ae.getSource() == b2) {

			String fname = JOptionPane.showInputDialog("Enter File name");
			String ip = JOptionPane.showInputDialog("Enter Metadata server IP Address");

			try {
				Socket s = new Socket(ip, 6001);
				DataOutputStream dos1 = new DataOutputStream(s.getOutputStream());
				dos1.writeUTF(t1.getText());
				dos1.writeUTF(fname);

				System.out.println("Req send to Metadata server");

				DataInputStream dis = new DataInputStream(s.getInputStream());
				String msg = dis.readUTF();
				String sk = dis.readUTF();

				if (msg.equals("found")) {
					JOptionPane.showMessageDialog(null, "Sk Recived Successfully");
					t3.setText(sk);
				} else if (msg.equals("notfound")) {
					JOptionPane.showMessageDialog(null, "File Not Found");
				}


			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		if (ae.getSource() == b3) {

			String ip1 = JOptionPane.showInputDialog("Enter Cloud Cluster IP Address");


			try {
				DBCon db = new DBCon();
				Connection con = db.getConnection();

				Socket s = new Socket(ip1, 6005);
				DataOutputStream dos1 = new DataOutputStream(s.getOutputStream());
				dos1.writeUTF("Req");

				System.out.println("Req send to  server");

				ObjectInputStream obi = new ObjectInputStream(s.getInputStream());
				Vector files = (Vector) obi.readObject();


				System.out.println(" All Cloud Files     " + files);

				Viewcloudfiles file = new Viewcloudfiles(files);


			} catch (Exception e) {
				// TODO: handle exception
			}


		}
		if(ae.getSource() == b4){
			String Selectfname = t2.getText();
			System.out.println("Updated File name   :" + Selectfname);
			String content2 = tf.getText();
			System.out.println(content2);
			String username = t1.getText();
			System.out.println(username);

			if (content2.equals(Data))
			{
				System.out.println("File is not modified");

			} else {
				try {

					String userEmailAddress = getEmailForUser(username);
					String privateKey = getpk(Selectfname);
					System.out.println(userEmailAddress);

					if (userEmailAddress != null && privateKey != null) {
						// Assuming EmailSender.sendEmail is your method to send an email
						String content = "Here is your private key: " + privateKey;
						String subject = "Private key for " + Selectfname ;
						EmailSender e = new EmailSender();
						e.sendEmail(userEmailAddress, content,subject);

						// Prompt user to enter the private key
						String enteredKey = JOptionPane.showInputDialog(f, "Enter the Private Key sent to your email:", "Verify Private Key", JOptionPane.QUESTION_MESSAGE);

						if (enteredKey != null && enteredKey.equals(privateKey)) {

							FileOutputStream fos = new FileOutputStream("modify/"+ Selectfname);
							fos.write(content2.getBytes());
							fos.close();

							AzureConnector azureConnector = new AzureConnector("modify");
							azureConnector.uploadFile("modify/" + Selectfname);

							MessageDigest md = MessageDigest.getInstance("SHA1");
							FileInputStream in1 = new FileInputStream("modify\\"
									+ Selectfname);
							DigestInputStream dis2 = new DigestInputStream(in1, md);
							BufferedInputStream bd = new BufferedInputStream(dis2);

							while (true) {
								int b2 = bd.read();
								if (b2 == -1)
									break;
							}


							String mac="";
							BigInteger bi2 = new BigInteger(md.digest());
							mac = bi2.toString(16);
							System.out.println("The updated mac is:" + mac);


							DBCon db=new DBCon();
							Connection con=db.getConnection();

							Statement stmt = con.createStatement();
							String sql = "update Normalpeer1 set mac='" + mac
									+ "' where fname='" + Selectfname + "'";
							stmt.executeUpdate(sql);

							JOptionPane.showMessageDialog(null, Selectfname
											+ "is Modified  ", "",
									JOptionPane.INFORMATION_MESSAGE);
							String uname = t1.getText();
							String filename = Selectfname;
							String sk = getsk(Selectfname);
							String peername = getpeer(sk);
							LocalDateTime now = LocalDateTime.now();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							String formattedDate = now.format(formatter);

							sql = "INSERT INTO attacker (username, fname, sk, peer, dt) VALUES (?, ?, ?, ?, ?)";
							PreparedStatement pstmt = null;
							pstmt = con.prepareStatement(sql);
							pstmt.setString(1, username);
							pstmt.setString(2, filename);
							pstmt.setString(3, sk);
							pstmt.setString(4, peername);
							pstmt.setString(5, formattedDate);

							int insertedRows = pstmt.executeUpdate();
							if (insertedRows > 0) {
								System.out.println("Successfully inserted the record into the attacker table.");
							} else {
								System.out.println("The record was not inserted.");
							}


							JOptionPane.showMessageDialog(f, "The file has been successfully modified and uploaded.", "Success", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(f, "The entered Private Key is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}



				} catch (Exception e) {
					System.out.println(e);
				}

			}

		}

	}

	private String getEmailForUser(String username) {
		DBCon db = new DBCon();
		Connection con = db.getConnection();
		try {
			String sql = "SELECT email FROM userregister WHERE username = ?";
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1, username);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						return rs.getString("email");
					}
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error accessing database: " + e.getMessage());
		}
		return username;
	}
	public static String getpk(String filename) throws SQLException {
		DBCon db=new DBCon();
		Connection con=db.getConnection();
		String sql = "select privk from owner where fname = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, filename);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("privk");
				}
			}
		}
		return null;
	}

	public static String getsk(String filename) throws SQLException {
		DBCon db=new DBCon();
		Connection con=db.getConnection();
		String sql = "select sk from owner where fname = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, filename);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("sk");
				}
			}
		}
		return null;
	}

	public static String getpeer(String sk) throws SQLException {
		DBCon db=new DBCon();
		Connection con=db.getConnection();
		String sql = "select peername from owner where sk = ?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, sk);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("peername");
				}
			}
		}
		return null;
	}

}