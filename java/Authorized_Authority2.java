import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ReadOnlyBufferException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
public class Authorized_Authority2 implements ActionListener
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
  
  public Authorized_Authority2() 
  {
	
	  f=new JFrame("Authorized_Authority2::A Blockchain Approach to Ensuring Provenance to Outsourced Cloud Data in a Sharing Ecosystem");
	  p=new JPanel();
	  p.setBackground(new Color(190, 220, 188));
	  
//	 p.setBackground(Color.white);
	 
	  
	  b1=new JButton("Registered Owners");
	  b1.setBounds(160, 380, 150, 30);
	  p.add(b1);
	  
	  b2=new JButton("Registered Users");
	  b2.setBounds(370, 380, 160, 30);
	  p.add(b2);
	  
	  b3=new JButton("Biometric Report");
	  b3.setBounds(450, 385, 150, 30);
//	  p.add(b3);
	 
	  ic1=new ImageIcon(this.getClass().getResource("wallpaper2.jpg"));
	  l1=new JLabel();
	  l1.setIcon(ic1);
	  l1.setBounds(0, 0, 700, 500);
	  p.add(l1);
	  
	  f.setSize(700, 500);
	  f.setVisible(true);
	  p.setLayout(null);
	  
	  f.add(p);
	  
	    tf.setColumns(200);
		tf.setRows(100);
		tf.setName("tf");
		pane1.setName("pane");
		pane1.setViewportView(tf);
		pane1.setBounds(450, 250, 300, 200);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	  
	  int[] port = new int[] { 401,201,7002,5005,7001,4007,4006,1110};

		for (int i = 0; i < 8; i++) {
			Thread th = new Thread(new PortListener(port[i]));
			th.start();
		}
	  
	  
}
  public static void main(String[] args) 
  {
	new Authorized_Authority2();
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
      if(this.port==7888)
      {
    	  
    	  
      }
      
      if(this.port==6001)
      {
    	  
    	  
      }
      
      if(this.port==7001)
      {

			try 
			{
				AES enc = new AES();
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(7001);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				
				
				String name=dis.readUTF();
				String pass=dis.readUTF();
				String address=dis.readUTF();
				String city=dis.readUTF();
				String email=dis.readUTF();
				String mobile=dis.readUTF();
				
	
				System.out.println("data recived");
				
				Statement stmt1 = con.createStatement();
				String sql2="insert into FireRegister values('"+name+"','"+pass+"','"+address+"','"+city+"','"+email+"','"+mobile+"')";
				stmt1.executeUpdate(sql2);
				System.out.println("Data inserted into Firewall db");
				
				
				DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
				dos.writeUTF("taken");
				System.out.println("register msg send to user");
				
				
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}
      }
     
      if(this.port==7002)  
      {
			try 
			{
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(7002);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				
				String name=dis.readUTF();
				String pass=dis.readUTF();
				
				System.out.println("data recived");
				
				
				
				
				Statement stmt1 = con.createStatement();
				String sql2="select * from FireRegister where username='"+name+"' and pass='"+pass+"'";
				ResultSet rs=stmt1.executeQuery(sql2);
				if(rs.next()==true)
				{
					
					Socket ss=new Socket("localhost",9989);
					DataOutputStream out=new DataOutputStream(ss.getOutputStream());
					out.writeUTF("success");
					System.out.println("view send to cloud cluster");
					
					DataInputStream inp=new DataInputStream(ss.getInputStream());
					String msg=inp.readUTF();
					
					
					
					DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
					dos.writeUTF("success");
					
				}else
					if(rs.next()==false)
					{
						
						Socket ss=new Socket("localhost",8989);
						DataOutputStream out=new DataOutputStream(ss.getOutputStream());
						out.writeUTF("failure");
						System.out.println("view send to cloud cluster");
						
						DataInputStream inp=new DataInputStream(ss.getInputStream());
						String msg=inp.readUTF();
						
						
						
						DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
						dos.writeUTF("fail");
					}
			
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		
    
      }
      
    	  if(this.port==4007)
          {

  			try 
  			{
  				AES enc = new AES();
  				 DBCon db=new DBCon();
  	        	 Connection con=db.getConnection();
  				
  			   ServerSocket s1=new ServerSocket(4007);
  			   
  			   System.out.println("inside 4007 port no");
  			   while(true)
  			   {
  				Socket ss1=s1.accept();
  				
  				DataInputStream dis=new DataInputStream(ss1.getInputStream());
  				
  				
  				String name=dis.readUTF();
  				String pass=dis.readUTF();
  				String address=dis.readUTF();
  				String city=dis.readUTF();
  				String email=dis.readUTF();
  				String mobile=dis.readUTF();
  				
  	
  				System.out.println("data recived");
  				
  				Statement stmt1 = con.createStatement();
  				String sql2="insert into UserRegister values('"+name+"','"+pass+"','"+address+"','"+city+"','"+email+"','"+mobile+"')";
  				stmt1.executeUpdate(sql2);
  				System.out.println("Data inserted into userregister db");
  				
  				
  				DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
  				dos.writeUTF("taken");
  				System.out.println("register msg send to user");
  				
  				
  			   }
  			} catch (Exception e) {
  				// TODO: handle exception
  			}
   
          }
    	  
    	  if(this.port==4006)   
          {
    			try 
    			{
    				 DBCon db=new DBCon();
    	        	 Connection con=db.getConnection();
    				
    			   ServerSocket s1=new ServerSocket(4006);
    			   while(true)
    			   {
    				Socket ss1=s1.accept();
    				
    				DataInputStream dis=new DataInputStream(ss1.getInputStream());
    				
    				String name=dis.readUTF();
    				String pass=dis.readUTF();
    				
    				System.out.println("data recived");
    				
    				
    				Statement stmt1 = con.createStatement();
    				String sql2="select * from UserRegister where username='"+name+"' and pass='"+pass+"'";
    				ResultSet rs=stmt1.executeQuery(sql2);
    				if(rs.next()==true)
    				{
    					
    					Socket ss=new Socket("localhost",7776);
    					DataOutputStream out=new DataOutputStream(ss.getOutputStream());
    					out.writeUTF("success");
    					System.out.println("view send to cloud cluster");
    					
    					DataInputStream inp=new DataInputStream(ss.getInputStream());
    					String msg=inp.readUTF();
    					
    					
    					DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
    					dos.writeUTF("success");
    					
    				}else
    					if(rs.next()==false)
    					{
    						
    						Socket ss=new Socket("localhost",7778);
    						DataOutputStream out=new DataOutputStream(ss.getOutputStream());
    						out.writeUTF("failure");
    						System.out.println("view send to cloud cluster");
    						
    						DataInputStream inp=new DataInputStream(ss.getInputStream());
    						String msg=inp.readUTF();
    						
    						
    						
    						DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
    						dos.writeUTF("fail");
    					}
    			
    			   }
    			} catch (Exception e) {
    				// TODO: handle exception
    			}
    		
    		
        
          }
    	  
    	  if(this.port==1110)
    	  {
    		  

  			try 
  			{
  				 DBCon db=new DBCon();
  	        	 Connection con=db.getConnection();
  				
  			   ServerSocket s1=new ServerSocket(1110);
  			   while(true)
  			   {
  				Socket ss1=s1.accept();
  				
  				DataInputStream dis=new DataInputStream(ss1.getInputStream());
  				
  				String name=dis.readUTF();
  				
  				
  				System.out.println("data recived from cloud");
  				
  				
  				Statement stmt1 = con.createStatement();
  				String sql2="select * from UserRegister where username='"+name+"'";
  				ResultSet rs=stmt1.executeQuery(sql2);
  				if(rs.next()==true)
  				{
  					
  					DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
  					dos.writeUTF("success");
  					
  				}else
  					if(rs.next()==false)
  					{
  						
  						DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
  						dos.writeUTF("fail");

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
		
//		new ViewOwners();
		

		try {

			Vector heading = new Vector();

			 heading.addElement("Ownername");
			 heading.addElement("Password");
			 heading.addElement("Address");
			 heading.addElement("City");
			 heading.addElement("Email");
			 heading.addElement("Mobile");

			 Vector data=new Vector();
			DBCon db = new DBCon();
			Connection con = db.getConnection();
			Statement stmt = con.createStatement();
             
             
             String query = "SELECT * from FireRegister";
             ResultSet rs = stmt.executeQuery(query);

			ResultSetMetaData rsm = rs.getMetaData();
			int col = rsm.getColumnCount();

			while (rs.next()) {
				Vector row = new Vector();
				for (int i = 1; i <= col; i++) {
					row.addElement(rs.getObject(i));

				}
				data.addElement(row);
			}

			JTable table = new JTable(data, heading);

			pane1 = new JScrollPane(table);

			pane1.setBounds(100, 100, 490, 240);
			f.add(pane1);
			// f.add(image);

		} catch (Exception e) {

		}
	
		
	
	
	
		
	
	}
	
	if(ae.getSource()==b2)
	{
//		new ViewEndusers();
		

		
//		new ViewOwners();
		

		try {

			Vector heading = new Vector();

			 heading.addElement("Username");
			 heading.addElement("Password");
			 heading.addElement("Address");
			 heading.addElement("City");
			 heading.addElement("Email");
			 heading.addElement("Mobile");

			 Vector data=new Vector();
			DBCon db = new DBCon();
			Connection con = db.getConnection();
             Statement stmt = con.createStatement();
             
             
             String query = "SELECT * from UserRegister";
             ResultSet rs = stmt.executeQuery(query);

			ResultSetMetaData rsm = rs.getMetaData();
			int col = rsm.getColumnCount();

			while (rs.next()) {
				Vector row = new Vector();
				for (int i = 1; i <= col; i++) {
					row.addElement(rs.getObject(i));

				}
				data.addElement(row);
			}

			JTable table = new JTable(data, heading);

			pane1 = new JScrollPane(table);

			pane1.setBounds(100, 100, 490, 240);
			f.add(pane1);
			// f.add(image);

		} catch (Exception e) {

		}
	
		
	
	
	
		
	
	
		
	}
	if(ae.getSource()==b3)
	{
		
		
	}
	
	
	
	
     }
}
