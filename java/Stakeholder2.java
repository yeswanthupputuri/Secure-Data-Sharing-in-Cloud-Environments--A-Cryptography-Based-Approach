import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ReadOnlyBufferException;
import java.security.DigestInputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.swing.*;
import javax.swing.border.Border;
public class Stakeholder2 implements ActionListener
{
  JFrame f;
  JPanel p;
  JButton b1,b2,b3;
  ImageIcon ic;
  
  
  ImageIcon ic1,ic2,ic3,ic4,ic5,ic6,ic7,ic8,ic9,ic11,ic22,ic33,ic44,ic55,ic66,ic77,ic88,ic99,icc1,icc2,icc3,icc4,icc5,lab1,lab2,lab3,lab4;
  JLabel op,op1,l1,l2,l3,l4,l5,l6,l7,l8,l9,l11,l22,l33,l44,l55,l66,l77,l88,l99,lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb9;
  JLabel label1,label2,label3,label4,label5,label6,label7;
  
  JLabel lbb1,lbb2,lbb3,lbb4,lbb5,lbb6,lbb7,lbb8;
  
  
  public JTextArea tf = new JTextArea();
	public JTextField fname = new JTextField();
	public JScrollPane pane1 = new JScrollPane();
	
	
	Cipher encoder = null;
	Key prKey;
	public static Key pubKey;
	
	String Selectfname="";
	String data="";
  
  public Stakeholder2() 
  {
	
	  f=new JFrame("Stakeholder-->2::A Blockchain Approach to Ensuring Provenance to Outsourced Cloud Data in a Sharing Ecosystem");
	  p=new JPanel();
	  p.setBackground(new Color(220, 220, 188));
	  
	  b1=new JButton("View Files");
	  b1.setBounds(70, 180, 150, 30);
	  p.add(b1);
	  
	  
	   ImageIcon banner = new ImageIcon(this.getClass().getResource("NormalPeer.png"));
		JLabel title=new JLabel();
		title.setIcon(banner);
		title.setBounds(0, 0, 800, 95);
	  
	  
	  
	  b2=new JButton("View File Content");
	  b2.setBounds(70, 255, 150, 30);
	  p.add(b2);
	  
	  b3=new JButton("Modify File");
	  b3.setBounds(70, 325, 150, 30);
	  p.add(b3);
	  
	  
	    Border b = BorderFactory.createLineBorder(Color.black, 3);
		op = new JLabel();
		op.setBorder(b);
		op.setBounds(60, 110, 650, 350);
		p.add(op);
	  
	 
	  
	  f.setSize(810, 550);
	  f.setVisible(true);
	  p.setLayout(null);
	  p.add(title);
	  f.add(p);
	  
	    tf.setColumns(200);
		tf.setRows(100);
		tf.setName("tf");
		pane1.setName("pane");
		pane1.setViewportView(tf);
		pane1.setBounds(340, 170, 300, 200);
		p.add(pane1);
		
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	  
	  int[] port = new int[] { 401, 1007,201,2323,5005,5008,6001,6767,3005,2229};

		for (int i = 0; i < 10; i++) {
			Thread th = new Thread(new PortListener(port[i]));
			th.start();
		}
	  
	  
}
  public static void main(String[] args) 
  {
	new Stakeholder2();
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
      if(this.port==1007)
      {
			try 
			{
				AES enc = new AES();
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(1007);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				 DataInputStream dis=new DataInputStream(ss1.getInputStream());
	             String user=dis.readUTF();
	             String content=dis.readUTF();
	             String fname=dis.readUTF();
	             String mac=dis.readUTF();
	             String pk=dis.readUTF();
	             String sk=dis.readUTF();
	            
				System.out.println("data recived");
				
				Statement stmt1 = con.createStatement();
				String sql2="insert into Normalpeer2 values('"+user+"','"+fname+"','"+mac+"','"+pk+"','"+sk+"')";
				stmt1.executeUpdate(sql2);
				System.out.println("Data inserted into Stakeholder2 db");
				
				FileOutputStream fos = new FileOutputStream("server/" + fname);
				fos.write(content.getBytes());
				fos.close();
				
				
				FileOutputStream fos1 = new FileOutputStream("peer2/" + fname);
				fos1.write(content.getBytes());
				fos1.close();
				
				
				
				Socket ssx=new Socket("localhost",6777);
				DataOutputStream dos=new DataOutputStream(ssx.getOutputStream());
				dos.writeUTF(fname);
				dos.writeUTF(mac);
				dos.writeUTF(pk);
				dos.writeUTF(sk);
				dos.writeUTF("Stakeholder2");
				
				DataOutputStream dors=new DataOutputStream(ss1.getOutputStream());
				dors.writeUTF("taken");
				System.out.println("reach msg send to server");
				
				System.out.println("alll data send to bootstrap");
				
				
				
				
				
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		
      }
      
      if(this.port==6001)
      {
			try 
			{
				AES enc = new AES();
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(6001);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
	             DataInputStream dis=new DataInputStream(ss1.getInputStream());
	             String username=dis.readUTF();
	             String fname=dis.readUTF();
	             
	           
	          
				System.out.println("data recived");
				
				Socket sc=new Socket("localhost",7878);
				DataOutputStream dose=new DataOutputStream(sc.getOutputStream());
				dose.writeUTF("sk req");
				System.out.println("view send to engine");
				
				DataInputStream diis=new DataInputStream(sc.getInputStream());
				String msg=diis.readUTF();
				
				Statement stmt1 = con.createStatement();
				String sql2="select * from metadata where fname='"+fname+"'";
			    ResultSet rs=stmt1.executeQuery(sql2);
			    
			    String sk="";
			    if(rs.next()==true)
			    {
			    	 sk=rs.getString(5);
			    	 
			    	    DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
						dos.writeUTF("found");
						dos.writeUTF(sk);
						System.out.println("sk send to engine");
			    }else
			    {
			    	DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
			    	dos.writeUTF("not found");
			    	dos.writeUTF("not found");
					System.out.println("sk send to engine");
			    }
				
				
				
				
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		
      }
      
      if(this.port==5008)
      {

			try 
			{
				AES enc = new AES();
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(5008);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				
				String msg=dis.readUTF();
				String fname=dis.readUTF();
				String fakem=dis.readUTF();
				String date=dis.readUTF();
	
				System.out.println("data recived");
				
				Statement stmt1 = con.createStatement();
				String sql2="insert into Attacker values('"+fname+"','"+fakem+"','"+date+"')";
				stmt1.executeUpdate(sql2);
				System.out.println("Data inserted into manager db");
				
				
				DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
				dos.writeUTF("taken");
				System.out.println("reach msg send to pattern");
				
				
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		
  
    
      }
     
      if(this.port==2323)
      {
			try 
			{
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(2323);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				
				String fake=dis.readUTF();
				System.out.println("fake data recived");
				
				
				Statement stmt1 = con.createStatement();
				String sql2="update patternmanager set fakedata='"+fake+"'";
				stmt1.executeUpdate(sql2);
				System.out.println("Data inserted into manager db");
				
				
				DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
				dos.writeUTF("reach");
				System.out.println("reach msg send to pattern");
				
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		
    
      }
      
    	  if(this.port==6767)
          {
    			try 
    			{
    				 DBCon db=new DBCon();
    	        	 Connection con=db.getConnection();
    				
    			   ServerSocket s1=new ServerSocket(6767);
    			   while(true)
    			   {
    				Socket ss1=s1.accept();
    				
    				DataInputStream dis=new DataInputStream(ss1.getInputStream());
    				
    				String fname=dis.readUTF();
    				String vmac=dis.readUTF();
    				
    				System.out.println("fake data recived");
    				
    				
    				Statement stmt1 = con.createStatement();
    				String sql="select * from metadata where fname='"+fname+"'";
    				ResultSet rs=stmt1.executeQuery(sql);
    				if(rs.next()==true)
    				{
    					System.out.println("File found");
    					String mac=rs.getString(4);
    					
    					if(mac.equals(vmac))
    					{
    						DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
    						dos.writeUTF("safe");
    					
    					}else
    					{
    						DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
        					dos.writeUTF("notsafe");
    					}
    					
    				}else
    				{
    					
    					
    				}
    				
    			   }
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
      
        
          }
    	  
    	  if(this.port==3005)
    	  {
    		 
  			try 
  			{
  				 DBCon db=new DBCon();
  	        	 Connection con=db.getConnection();
  				
  			   ServerSocket s1=new ServerSocket(3005);
  			   while(true)
  			   {
  				Socket ss1=s1.accept();
  				
  				DataInputStream dis=new DataInputStream(ss1.getInputStream());
  				
  				String fname=dis.readUTF();
  				String key=dis.readUTF();
  				String name=dis.readUTF();
  				
  				System.out.println(" data recived");
  				
  				
  				Statement stmt1 = con.createStatement();
  				String sql2="select * from Normalpeer2 where fname='"+fname+"' and sk='"+key+"'";
  				ResultSet rss=stmt1.executeQuery(sql2);
  				
  				String content="";
  				if(rss.next()==true)
  				{
  					FileInputStream fis = new FileInputStream("peer2/" + fname);
					byte b[] = new byte[fis.available()];
					fis.read(b);
					content = new String(b);
					
					    DataOutputStream dos1=new DataOutputStream(ss1.getOutputStream());
		  				dos1.writeUTF("");
		  				System.out.println("msg send to cloud cluster");
					
					
					Socket ss=new Socket("localhost",5554);
					DataOutputStream dos=new DataOutputStream(ss.getOutputStream());
					dos.writeUTF("success");
	  				dos.writeUTF(content);
	  				System.out.println("data send to users");
	  				
  				}else
  					if(rss.next()==false)
  					{
  						
  						DataOutputStream dos1=new DataOutputStream(ss1.getOutputStream());
  		  				dos1.writeUTF("wrong");
  		  				System.out.println("msg send to cloud cluster");
  						
  						
  						Socket sss=new Socket("localhost",5554);
  						DataOutputStream dos=new DataOutputStream(sss.getOutputStream());
  		  				dos.writeUTF("fail");
  		  			    dos.writeUTF("fail");
  		  				System.out.println("data send to users");
  		  				
  		  				
  		  			    Socket socket=new Socket("localhost",7657);
		  				DataOutputStream dosto=new DataOutputStream(socket.getOutputStream());
		  				dosto.writeUTF(name);
		  			    dosto.writeUTF(fname);
		  		        dosto.writeUTF(key);
		  		        dosto.writeUTF("Stakeholder2");
		  		        System.out.println("Attacker details send to Authorized Authority");
  					}
  				
  			   }
  			} catch (Exception e) {
  				// TODO: handle exception
  			}
  		
  		
      
        
    		  
    	  }
    	  
    	  if(this.port==2229)	  
    	  {
    		  
    		     try 
    			{
    				 DBCon db=new DBCon();
    	        	 Connection con=db.getConnection();
    				
    			   ServerSocket s1=new ServerSocket(2229);
    			   while(true)
    			   {
    				Socket ss1=s1.accept();
    				
    				DataInputStream dis=new DataInputStream(ss1.getInputStream());
    				
    				String msg=dis.readUTF();
    				String omac=dis.readUTF();
    				String file=dis.readUTF();
    				System.out.println(" data recived");
    				
    			
    				Statement stmt1 = con.createStatement();
    				String sql="select * from Normalpeer2 where fname='"+file+"'";
    				ResultSet rs=stmt1.executeQuery(sql);
    				if(rs.next()==true)
    				{
    					System.out.println("File found");
    					String vmac=rs.getString(3);
    					
    					
    					System.out.println("O MAC       "+omac);
    					System.out.println("Vary MAC    "+vmac);
    					
    					if(omac.equals(vmac))
    					{
    						
    						Socket sc=new Socket("localhost",3332);
    						DataOutputStream dos=new DataOutputStream(sc.getOutputStream());
    						dos.writeUTF("safe");
    						System.out.println("Safe sent");
    					
    					}else
    						if(!omac.equals(vmac))
    				       	{
    						
    						Socket sck=new Socket("localhost",3332);
    						DataOutputStream dos=new DataOutputStream(sck.getOutputStream());
        					dos.writeUTF("notsafe");
        					System.out.println("Not Safe sent");
        					
    					     }
    					
    				}else
    				{
    					
    					
    				}
    				
    			   }
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
      
        
          
      		  
      	  
    	  }
      
      
      
		}
  
  }

@Override
public void actionPerformed(ActionEvent ae) 
     {
	
	if(ae.getSource()==b1)
	{
		Viewpeer2files file=new Viewpeer2files();
		
	}
	
	if(ae.getSource()==b2)
	{
		 Selectfname = JOptionPane.showInputDialog(null,
				"Enter the file name to view");

		try {

			KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
			encoder = Cipher.getInstance("RSA");
			KeyPair kp = kg.generateKeyPair();
			prKey = kp.getPrivate();

			pubKey = kp.getPublic();
			byte[] pub = pubKey.getEncoded();
			byte[] priv = prKey.getEncoded();

			File f = new File("peer2/" + Selectfname);
			boolean success = f.isFile();

			if (success) 
			{
				int choice = JOptionPane.showConfirmDialog(null,
						"File Available!!! Do u want to Open it");
				
				

				if (choice == 0) 
				{
					FileInputStream fstream = new FileInputStream(f);
					DataInputStream in = new DataInputStream(fstream);
					BufferedReader br = new BufferedReader(
							new InputStreamReader(in));
					String strLine;
					StringBuffer buffer = new StringBuffer();

					// Read File Line By Line
					while ((strLine = br.readLine()) != null) {

						buffer.append(strLine + " ");
						buffer.append("\n");

					}

					String data = buffer.toString();

//					System.out.println("Data is:  "+ new AES().encrypt(data, keyWord));

					String content1="";
					content1 = tf.getText();
//					String decData = new AES().decrypt(data, keyWord);

//					System.out.println("decdata		:" + decData);

					tf.setText(data);

					// Close the input stream
					in.close();

				}

				if (choice == 1) {

					System.out.println("No");

					fname.setText(" ");

				}

			}

		} catch (Exception e) {

		}
	
	
		
	
	}
	if(ae.getSource()==b3)
	{
		


		
		System.out.println("Updated File name   :" + Selectfname);

		String content2 = tf.getText();

		if (content2.equals(data)) 
		{
			System.out.println("File is not modified");
			
		} else {
			try {

//				content2 = new AES().encrypt(tf.getText(), keyWord);

//				String nm = fntf.getText();

				FileOutputStream fos = new FileOutputStream("modify/"+ Selectfname);
				fos.write(content2.getBytes());
				fos.close();

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
				String sql = "update Normalpeer2 set mac='" + mac
						+ "' where fname='" + Selectfname + "'";
				stmt.executeUpdate(sql);

				JOptionPane.showMessageDialog(null, Selectfname
						+ "is Modified  ", "",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				System.out.println(e);
			}

		}
	
		
	
		
	
		
	}
	
	
	
	
     }
}
