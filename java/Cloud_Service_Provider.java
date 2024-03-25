import java.awt.Color;
import java.awt.Font;
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
import java.io.ObjectOutputStream;
import java.io.PrintStream;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.swing.*;
import javax.swing.border.Border;
public class Cloud_Service_Provider implements ActionListener
{
  JFrame f;
  JPanel p;
  JButton b1,b2,b3;
  ImageIcon ic;
  
  JMenuBar mbr;
	JMenu m1;
	JMenuItem mi1, mi2,mi3;
	String content1="";
	String Selectfname="";
  
  ImageIcon ic1,ic2,ic3,ic4,ic5,ic6,ic7,ic8,ic9,ic11,iccc1,ic22,ic33,ic44,ic55,ic66,ic77,ic88,ic99,icc1,icc2,icc3,icc4,icc5,icc6,icc7,icc8,icc9,icc10,icc12,icc13,icc14,icc15,lab1,lab2,lab3,lab4;
  JLabel op,op2,l1,l2,l3,l4,l5,l6,l7,l8,l9,l11,l22,l33,l44,l55,l66,l77,l88,l99,lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb9;
  JLabel label1,label2,label3,label4,label5,label6,label7;
  
  JLabel lbb1,lbb2,lbb3,lbb4,lbb5,lbb6,lbb7,lbb8,lbb9,lbb10,lbb11,lbb22,lbb33,lbb44;
  String keyWord = "ef50a0ef2c3e3a5fdf803ae9752c8c66";
  
  ImageIcon iss1,iss2,iss3,iss4,iss5,iss6,iss7,iss8;
  JLabel lk1,lk2,lk3,lk4,lk5,lk11,lk22,lk33;
  
  public Font f1 = new Font("Times new Roman", Font.BOLD, 17);
  public Font f3 = new Font("Times new Roman", Font.BOLD, 14);
  
  public JTextArea tf = new JTextArea();
	public JTextField fname = new JTextField();
	public JScrollPane pane1 = new JScrollPane();
	
	Cipher encoder = null;
	Key prKey;
	public static Key pubKey;
  
  public Cloud_Service_Provider() 
  {
	
	  f=new JFrame("CCloud Service Provider::A Blockchain Approach to Ensuring Provenance to Outsourced Cloud Data in a Sharing Ecosystem");
	  p=new JPanel();
	  
      p.setBackground(Color.white);
	 
	  
	  b1=new JButton("View Files");
	  b1.setBounds(60, 180, 150, 30);
//	  p.add(b1);
	  
	  b2=new JButton("View File Content");
	  b2.setBounds(60, 260, 150, 30);
//	  p.add(b2);
	  
	  b3=new JButton("Modify");
	  b3.setBounds(60, 330, 150, 30);
//	  p.add(b3);
	  
	     tf.setColumns(200);
		tf.setRows(100);
		tf.setName("tf");
		pane1.setName("pane");
		pane1.setViewportView(tf);
		pane1.setBounds(350, 150, 340, 230);
//		p.add(pane1);
		
		Border bb = BorderFactory.createLineBorder(Color.black, 5);
		op2 = new JLabel();
		op2.setBorder(bb);
		op2.setBounds(20, 60, 830, 450);
		p.add(op2);
		
		Border bb1 = BorderFactory.createLineBorder(Color.black, 2);
		op = new JLabel();
		op.setBorder(bb1);
		op.setBounds(200, 330, 210, 77);
//		p.add(op);
		
		lk11=new JLabel("AA::~ Authorized Authority");
		lk11.setBounds(36, -20, 250, 250);
		p.add(lk11);
		
		lk22=new JLabel("DPS::~ Data Provenance System");
		lk22.setBounds(36, 10, 250, 250);
		p.add(lk22);
		
		
		lbb1=new JLabel("Dataowner");
		lbb1.setBounds(36, 220, 250, 250);
		lbb1.setFont(f1);
		p.add(lbb1);
		
		lbb2=new JLabel("AA1");
		lbb2.setBounds(200, 177, 150, 150);
		lbb2.setFont(f3);
		p.add(lbb2);
		
		lbb3=new JLabel("AA2");
		lbb3.setBounds(620, 170, 150, 150);
		lbb3.setFont(f3);
		p.add(lbb3);
	  
		
		lbb4=new JLabel("CSP");
		lbb4.setBounds(495, 70, 150, 150);
		lbb4.setFont(f3);
		p.add(lbb4);
		
		lbb5=new JLabel("Stakeholder1");
		lbb5.setBounds(190,300, 150, 150);
		lbb5.setFont(f3);
		p.add(lbb5);
		

		lbb6=new JLabel("Stakeholder2");
		lbb6.setBounds(320,300, 150, 150);
		lbb6.setFont(f3);
		p.add(lbb6);
		
		lbb7=new JLabel("DPS");
		lbb7.setBounds(600,300, 150, 150);
		lbb7.setFont(f3);
		p.add(lbb7);
		
		lbb8=new JLabel("End User");
		lbb8.setBounds(760, 230, 250, 250);
		lbb8.setFont(f1);
		p.add(lbb8);
		
	
		    mbr = new JMenuBar();
			f.setJMenuBar(mbr);
			m1 = new JMenu("File");
			mi1 = new JMenuItem("Exit");
			
			mbr.add(m1);
			m1.add(mi1);
			
			
			
			iss1=new ImageIcon(this.getClass().getResource("NPP.png"));
			lbb11=new JLabel();
			lbb11.setIcon(iss1);
			lbb11.setBounds(36, 330, 250, 250);
//			p.add(lbb11);
			
			iss2=new ImageIcon(this.getClass().getResource("BSP.png"));
			lbb22=new JLabel();
			lbb22.setIcon(iss2);
			lbb22.setBounds(36, 360, 250, 250);
//			p.add(lbb22);
			
			iss4=new ImageIcon(this.getClass().getResource("Bigcross1.png"));
			lk1=new JLabel();
			lk1.setIcon(iss4);
			lk1.setBounds(80, 60, 250, 250);
			p.add(lk1);
			
			iss5=new ImageIcon(this.getClass().getResource("Bigcross2.png"));
			lk2=new JLabel();
			lk2.setIcon(iss5);
			lk2.setBounds(720, 60, 250, 250);
			p.add(lk2);
			
			
			//all images
			ic1=new ImageIcon(this.getClass().getResource("ca4.jpg"));
			l1=new JLabel();
			l1.setIcon(ic1);
			l1.setBounds(30, 150, 250, 250);
			p.add(l1);
			
			ic2=new ImageIcon(this.getClass().getResource("lady1.jpg"));
			l2=new JLabel();
			l2.setIcon(ic2);
			l2.setBounds(750, 150, 250, 250);
			p.add(l2);
			
			
			
			ic4=new ImageIcon(this.getClass().getResource("cloud.jpg"));
			l4=new JLabel();
			l4.setIcon(ic4);
			l4.setBounds(385, 70, 150, 150);
			p.add(l4);
			
			
			
			
			ic5=new ImageIcon(this.getClass().getResource("/fire.jpg"));
			l5=new JLabel();
			l5.setIcon(ic5);
			l5.setBounds(270, 170, 150, 150);
			p.add(l5);
			
			ic6=new ImageIcon(this.getClass().getResource("/fire.jpg"));
			l6=new JLabel();
			l6.setIcon(ic6);
			l6.setBounds(550, 170, 150, 150);
			p.add(l6);
			
			ic7=new ImageIcon(this.getClass().getResource("/firewall.png"));
			l7=new JLabel();
			l7.setIcon(ic7);
			l7.setBounds(220,296, 150, 150);
			p.add(l7);
			
			ic8=new ImageIcon(this.getClass().getResource("/firewall.png"));
			l8=new JLabel();
			l8.setIcon(ic8);
			l8.setBounds(350,296, 150, 150);
			p.add(l8);
			
			ic9=new ImageIcon(this.getClass().getResource("/bootstrap.png"));
			l9=new JLabel();
			l9.setIcon(ic9);
			l9.setBounds(530,300, 150, 150);
			p.add(l9);
			
			ic11=new ImageIcon(this.getClass().getResource("/fireline1.png"));
			l11=new JLabel();
			l11.setIcon(ic11);
			l11.setBounds(330,118, 150, 150);
			p.add(l11);
			
			ic22=new ImageIcon(this.getClass().getResource("/fireline2.png"));
			l22=new JLabel();
			l22.setIcon(ic22);
			l22.setBounds(500,118, 150, 150);
			p.add(l22);
			
			ic33=new ImageIcon(this.getClass().getResource("/Blackcross1.png"));
			l33=new JLabel();
			l33.setIcon(ic33);
			l33.setBounds(240,228, 150, 150);
			p.add(l33);
			
			ic44=new ImageIcon(this.getClass().getResource("/Blackcross2.png"));
			l44=new JLabel();
			l44.setIcon(ic44);
			l44.setBounds(324,228, 150, 150);
			p.add(l44);
			
			ic55=new ImageIcon(this.getClass().getResource("/Blacksrt.png"));
			l55=new JLabel();
			l55.setIcon(ic55);
			l55.setBounds(444,300, 150, 150);
			p.add(l55);
			
			
			ic3=new ImageIcon(this.getClass().getResource("/cluster1.jpg"));
			l3=new JLabel();
			l3.setIcon(ic3);
			l3.setBounds(160, 60, 590, 450);
			p.add(l3);
			
			
			  
	  f.setSize(890, 640);
	  f.setVisible(true);
	  p.setLayout(null);
	  
	  f.add(p);
	  
	    
		

	  
	  int[] port = new int[] { 401, 2001,201,5005,6005,3008,5666,9989,8989,7776,7778};

		for (int i = 0; i < 11; i++) {
			Thread th = new Thread(new PortListener(port[i]));
			th.start();
		}
	  
	  
}
  public static void main(String[] args) 
  {
	new Cloud_Service_Provider();
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
      if(this.port==2001)
      {
			try 
			{
				AES enc = new AES();
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(2001);
			   while(true)
			   {
				Socket ss1=s1.accept();
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				
				String sh=dis.readUTF();
				String fname=dis.readUTF();
				String mac=dis.readUTF();
				String pk=dis.readUTF();
				String sk=dis.readUTF();
				String content=dis.readUTF();
				String user=dis.readUTF();
				
				System.out.println("peer name    "+ sh);
				
				System.out.println("data recived into server");
				
				
				//lines for data upload
				
				Thread.sleep(2000);
				l1.setVisible(false);
				Thread.sleep(1000);
				l1.setVisible(true);  //owner
				Thread.sleep(1000);
				l1.setVisible(false);
				Thread.sleep(1000);
				l1.setVisible(true);
				
				
				Thread.sleep(2000);
				iss6=new ImageIcon(this.getClass().getResource("BigGreen1.png"));
				lk1.setIcon(iss6);
				
				
				Thread.sleep(2000);
				l4.setVisible(false);
				Thread.sleep(1000);
				l4.setVisible(true);  //cloud
				Thread.sleep(1000);
				l4.setVisible(false);
				Thread.sleep(1000);
				l4.setVisible(true);
				
				
				Thread.sleep(2000);
				icc1=new ImageIcon(this.getClass().getResource("greenfire1.png"));
				l11.setIcon(icc1);
		
				
				Thread.sleep(2000);
				l5.setVisible(false);
				Thread.sleep(1000);
				l5.setVisible(true);  //fire1
				Thread.sleep(1000);
				l5.setVisible(false);
				Thread.sleep(1000);
				l5.setVisible(true);
				
				
				
							if(sh.equals("Stakeholder1"))
							{
								
								Thread.sleep(2000);
								icc3=new ImageIcon(this.getClass().getResource("Greencross1.png"));
								l33.setIcon(icc3);
								
								
								Thread.sleep(2000);
								l7.setVisible(false);
								Thread.sleep(1000);
								l7.setVisible(true);  //NP1
								Thread.sleep(1000);
								l7.setVisible(false);
								Thread.sleep(1000);
								l7.setVisible(true);
								
								Thread.sleep(2000);
								icc2=new ImageIcon(this.getClass().getResource("Greenstr.png"));
								l55.setIcon(icc2);
								
								Thread.sleep(2000);
								l9.setVisible(false);
								Thread.sleep(1000);
								l9.setVisible(true);  //BootStrap
								Thread.sleep(1000);
								l9.setVisible(false);
								Thread.sleep(1000);
								l9.setVisible(true);
								
								
								
								
								
								Socket ss11=new Socket("localhost",10002);
								DataOutputStream doos=new DataOutputStream(ss11.getOutputStream());
								
								doos.writeUTF(user);
								doos.writeUTF(content);
								doos.writeUTF(fname);
								doos.writeUTF(mac);
								doos.writeUTF(pk);
								doos.writeUTF(sk);
						
								System.out.println("data send to sh1");
								
								Statement stmt1 = con.createStatement();
								String sql2="insert into Cloudcluster values('"+fname+"','"+pk+"','"+mac+"','"+sk+"','"+sh+"')";
								stmt1.executeUpdate(sql2);
								System.out.println("Data inserted into cluster db");
								
								DataInputStream dos=new DataInputStream(ss11.getInputStream());
								String msg=dos.readUTF();
				
								DataOutputStream doos1=new DataOutputStream(ss1.getOutputStream());
								doos1.writeUTF("valid");
								
								
							}else 
								if(sh.equals("Stakeholder2"))
								{
									
									
									Thread.sleep(2000);
									icc13=new ImageIcon(this.getClass().getResource("Greencross2.png"));
									l44.setIcon(icc13);
									
									Thread.sleep(2000);
									l8.setVisible(false);
									Thread.sleep(1000);
									l8.setVisible(true);  //NP2
									Thread.sleep(1000);
									l8.setVisible(false);
									Thread.sleep(1000);
									l8.setVisible(true);
									
									
									Thread.sleep(2000);
									icc2=new ImageIcon(this.getClass().getResource("Greenstr.png"));
									l55.setIcon(icc2);
									
									Thread.sleep(2000);
									l9.setVisible(false);
									Thread.sleep(1000);
									l9.setVisible(true);  //BootStrap
									Thread.sleep(1000);
									l9.setVisible(false);
									Thread.sleep(1000);
									l9.setVisible(true);
								
									Socket soc=new Socket("localhost",1007);
									DataOutputStream doos=new DataOutputStream(soc.getOutputStream());
									
									doos.writeUTF(user);
									doos.writeUTF(content);
									doos.writeUTF(fname);
									doos.writeUTF(mac);
									doos.writeUTF(pk);
									doos.writeUTF(sk);
							
									System.out.println("data send to Stakeholder 2");
									
									Statement stmt1 = con.createStatement();
									String sql2="insert into Cloudcluster values('"+fname+"','"+pk+"','"+mac+"','"+sk+"','"+sh+"')";
									stmt1.executeUpdate(sql2);
									System.out.println("Data inserted into cluster db");
									
									DataInputStream dos=new DataInputStream(soc.getInputStream());
									String msg=dos.readUTF();
				
									DataOutputStream doos1=new DataOutputStream(ss1.getOutputStream());
									doos1.writeUTF("valid");
								}
							
							
							//all clear lines 
							Thread.sleep(3000);
							l1.setIcon(ic1);
							l2.setIcon(ic2);
							l4.setIcon(ic4);
							l5.setIcon(ic5);
							l6.setIcon(ic6);
							l7.setIcon(ic7);
							l8.setIcon(ic8);
							l9.setIcon(ic9);
							l11.setIcon(ic11);
							l22.setIcon(ic22);
							l33.setIcon(ic33);
							l44.setIcon(ic44);
							l55.setIcon(ic55);
							lk1.setIcon(iss4);

				
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		
      }
      
      if(this.port==5005)
      {
    	  
      }
      
//      if(this.port==7001)
//      {
//
//			try 
//			{
//				AES enc = new AES();
//				 DBCon db=new DBCon();
//	        	 Connection con=db.getConnection();
//				
//			   ServerSocket s1=new ServerSocket(7001);
//			   while(true)
//			   {
//				Socket ss1=s1.accept();
//				
//				DataInputStream dis=new DataInputStream(ss1.getInputStream());
//				
//				String name=dis.readUTF();
//				String pwd=dis.readUTF();
//				String add=dis.readUTF();
//				String city=dis.readUTF();
//				String Email=dis.readUTF();
//				String mobile=dis.readUTF();
//	
//				System.out.println("data recived  from user register");
//				
//				Statement st=con.createStatement();
//				String sql="insert into Register values ('"+name+"','"+pwd+"','"+add+"','"+city+"','"+Email+"','"+mobile+"')";
//				st.executeUpdate(sql);
//				System.out.println("data insetred into db");
//				
//				
//				DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
//				dos.writeUTF("taken");
//				System.out.println("reach msg send to pattern");
//				
//				
//			   }
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		
//		
//  
//    
//      }
     
     
      if(this.port==6005)
      {

			try 
			{
				 AES enc = new AES();
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(6005);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				String msg=dis.readUTF();
				

				Vector vv=new Vector();
			
				
				
				Statement stmt1 = con.createStatement();
				String sql2="select * from Cloudcluster";
				ResultSet rs=stmt1.executeQuery(sql2);
				System.out.println("Query checked");
	
		        while(rs.next()==true)
	             {
		       
		        	String fname=rs.getString(1);
		        	System.out.println("  "+fname);
		        	
		        	
		              	vv.add(fname);
		              	
	             }  
		        ObjectOutputStream obj=new ObjectOutputStream(ss1.getOutputStream());
 		        obj.writeObject(vv);
 		        System.out.println("All list semd to user");
		      
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}
      }
      
      if(this.port==3008)
      {
			try 
			{
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(3008);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				String name=dis.readUTF();
				String msg=dis.readUTF();
				System.out.println("Req recived from owner");
				
				 Vector data=new Vector();
				
				Statement stmt1 = con.createStatement();
				String sql2="select * from transaction where username='"+name+"'";
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
				
                  ObjectOutputStream obj=new ObjectOutputStream(ss1.getOutputStream());
                  obj.writeObject(data);
                  System.out.println("vector sent");
                  
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}
    
    	  
    	  
      }
      
 
      if(this.port==5666)
      {

			try 
			{
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(5666);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				
				String name=dis.readUTF();
				String fname=dis.readUTF();
				String sk=dis.readUTF();
				String msg=dis.readUTF();
				
				System.out.println("All  data recived");
				
				if(msg.equals("Request"))
				{
					
					l2.setVisible(false);
					Thread.sleep(1000);
					l2.setVisible(true);  //EndUser
					Thread.sleep(1000);
					l2.setVisible(false);
					Thread.sleep(1000);
					l2.setVisible(true);
					
					
					Thread.sleep(2000);
					iss7=new ImageIcon(this.getClass().getResource("Biggreen2.png"));
					lk2.setIcon(iss7);
					
					Socket ss=new Socket("localhost",1110);
					DataOutputStream dos=new DataOutputStream(ss.getOutputStream());
					dos.writeUTF(name);
					System.out.println("username send to fire wall2 ");
					
					
					DataInputStream dip=new DataInputStream(ss.getInputStream());
					String status=dip.readUTF();
					
					if(status.equals("success"))
					{
						
						Thread.sleep(2000);
						l4.setVisible(false);
						Thread.sleep(1000);
						l4.setVisible(true);  //cloud
						Thread.sleep(1000);
						l4.setVisible(false);
						Thread.sleep(1000);
						l4.setVisible(true);
						
						Thread.sleep(2000);
						ic66=new ImageIcon(this.getClass().getResource("greenfire2.png"));
						l22.setIcon(ic66);
						
						Thread.sleep(2000);
						l6.setVisible(false);
						Thread.sleep(1000);
						l6.setVisible(true);  //fire2
						Thread.sleep(1000);
						l6.setVisible(false);
						Thread.sleep(1000);
						l6.setVisible(true);
						
						
						Socket socket=new Socket("localhost",4324);
						DataOutputStream out=new DataOutputStream(socket.getOutputStream());
						out.writeUTF(name);
						System.out.println("username validation for attacker list send to firewall 1");
						
						DataInputStream inp=new DataInputStream(socket.getInputStream());
						String message=inp.readUTF();
						System.out.println("message recived        "+message);
						
						
						if(message.equals("ok"))
						{
						
							
							Statement stmt1 = con.createStatement();
							String sql2="select * from Cloudcluster where fname='"+fname+"'";
							ResultSet rs=stmt1.executeQuery(sql2);
							System.out.println("Query exe");
							
							String peername="";
							
							if(rs.next()==true)
							{
								peername=rs.getString(5);
								System.out.println("Stakeholder name is "+peername);
								
								
							}else
								
								if(rs.next()==false)
								{
									Thread.sleep(2000);
									l4.setVisible(false);
									Thread.sleep(1000);
									l4.setVisible(true);  //cloud
									Thread.sleep(1000);
									l4.setVisible(false);
									Thread.sleep(1000);
									l4.setVisible(true);
									
									Thread.sleep(2000);
									icc1=new ImageIcon(this.getClass().getResource("greenfire1.png"));
									l11.setIcon(icc1);
									
									
									Thread.sleep(2000);
									l5.setVisible(false);
									Thread.sleep(1000);
									l5.setVisible(true);  //fire1
									Thread.sleep(1000);
									l5.setVisible(false);
									Thread.sleep(1000);
									l5.setVisible(true);
									
									Thread.sleep(2000);
									icc6=new ImageIcon(this.getClass().getResource("red fire.jpg"));             
									l5.setIcon(icc6);
									
									
									Thread.sleep(2000);
									icc7=new ImageIcon(this.getClass().getResource("Redfireline1.png"));
									l11.setIcon(icc7);
									
									
									Thread.sleep(2000);
									ic99=new ImageIcon(this.getClass().getResource("Redcloud.jpg"));				
									l4.setIcon(ic99);
									
									
//									Thread.sleep(2000);
//									iss8=new ImageIcon(this.getClass().getResource("BigRed2.png"));
//									lk2.setIcon(iss8);
									
									    Socket socket1=new Socket("localhost",7657);
				  		  				DataOutputStream dosto=new DataOutputStream(socket1.getOutputStream());
				  		  				dosto.writeUTF(name);
				  		  			    dosto.writeUTF(fname);
				  		  		        dosto.writeUTF(sk);
				  		  		        dosto.writeUTF("Stakeholder1");
				  		  		        System.out.println("Attacker details send ");
									
									Socket ssk=new Socket("localhost",5554);
			  						DataOutputStream dus=new DataOutputStream(ssk.getOutputStream());
			  						dus.writeUTF("fail");
			  						dus.writeUTF("fail");
			  						System.out.println("fail msg send to end user");
									
								}
							
							if(peername.equals("Stakeholder1"))
							{
								//File req send to peer1
								
								Thread.sleep(2000);
								l4.setVisible(false);
								Thread.sleep(1000);
								l4.setVisible(true);  //cloud
								Thread.sleep(1000);
								l4.setVisible(false);
								Thread.sleep(1000);
								l4.setVisible(true);
								
								
								Thread.sleep(2000);
								icc1=new ImageIcon(this.getClass().getResource("greenfire1.png"));
								l11.setIcon(icc1);
								
								
								Thread.sleep(2000);
								l5.setVisible(false);
								Thread.sleep(1000);
								l5.setVisible(true);  //fire1
								Thread.sleep(1000);
								l5.setVisible(false);
								Thread.sleep(1000);
								l5.setVisible(true);
								
								Thread.sleep(2000);
								icc3=new ImageIcon(this.getClass().getResource("Greencross1.png"));
								l33.setIcon(icc3);
								
								
								Thread.sleep(2000);
								l7.setVisible(false);
								Thread.sleep(1000);
								l7.setVisible(true);  //NP1
								Thread.sleep(1000);
								l7.setVisible(false);
								Thread.sleep(1000);
								l7.setVisible(true);
								
								
								
								
								
								Socket sc=new Socket("localhost",3003);
								System.out.println("inside port no 3003");
								DataOutputStream dost=new DataOutputStream(sc.getOutputStream());
								dost.writeUTF(fname);
								dost.writeUTF(sk);
								dost.writeUTF(name);
								System.out.println("File req send to Stakeholder1 ");
								
								DataInputStream diis=new DataInputStream(sc.getInputStream());
								String msg1=diis.readUTF();
								System.out.println("message   "+msg1);
								if(msg1.equals("wrong"))
								{
									//for wrong sk 
									
								
									Thread.sleep(2000);
									icc8=new ImageIcon(this.getClass().getResource("RedNP.png"));
									l7.setIcon(icc8);
									
									Thread.sleep(2000);
									icc9=new ImageIcon(this.getClass().getResource("Redcross1.png"));
									l33.setIcon(icc9);
									
									
									Thread.sleep(2000);
									icc6=new ImageIcon(this.getClass().getResource("red fire.jpg"));             
									l5.setIcon(icc6);
									
									Thread.sleep(2000);
									icc7=new ImageIcon(this.getClass().getResource("Redfireline1.png"));
									l11.setIcon(icc7);
									
									Thread.sleep(2000);
									ic99=new ImageIcon(this.getClass().getResource("Redcloud.jpg"));				
									l4.setIcon(ic99);
								}
								
								
							}else
								if(peername.equals("Stakeholder2"))
								{
									
									Thread.sleep(2000);
									l4.setVisible(false);
									Thread.sleep(1000);
									l4.setVisible(true);  //cloud
									Thread.sleep(1000);
									l4.setVisible(false);
									Thread.sleep(1000);
									l4.setVisible(true);
									
									
								
									Thread.sleep(2000);
									icc1=new ImageIcon(this.getClass().getResource("greenfire1.png"));
									l11.setIcon(icc1);;
									
									
									Thread.sleep(2000);
									l5.setVisible(false);
									Thread.sleep(1000);
									l5.setVisible(true);  //fire1
									Thread.sleep(1000);
									l5.setVisible(false);
									Thread.sleep(1000);
									l5.setVisible(true);
									
									Thread.sleep(2000);
									icc13=new ImageIcon(this.getClass().getResource("Greencross2.png"));
									l44.setIcon(icc13);
									
									Thread.sleep(2000);
									l8.setVisible(false);
									Thread.sleep(1000);
									l8.setVisible(true);  //NP2
									Thread.sleep(1000);
									l8.setVisible(false);
									Thread.sleep(1000);
									l8.setVisible(true);
									
									
									//File req send to peer2
									Socket sc=new Socket("localhost",3005);
									System.out.println("inside port no 3005");
									
									
									DataOutputStream dost=new DataOutputStream(sc.getOutputStream());
									dost.writeUTF(fname);
									dost.writeUTF(sk);
									dost.writeUTF(name);
									
									System.out.println("File req send to Stakeholder2 ");
									
									
									DataInputStream diis=new DataInputStream(sc.getInputStream());
									String msg1=diis.readUTF();
									System.out.println("message   "+msg1);
									
									if(msg1.equals("wrong"))
									{
										
										
										
										Thread.sleep(2000);
										icc10=new ImageIcon(this.getClass().getResource("RedNP.png"));
										l8.setIcon(icc10);
										
										Thread.sleep(2000);
										icc12=new ImageIcon(this.getClass().getResource("Redcross2.png"));
										l44.setIcon(icc12);
										
										Thread.sleep(2000);
										icc6=new ImageIcon(this.getClass().getResource("red fire.jpg"));             
										l5.setIcon(icc6);
										
										Thread.sleep(2000);
										icc7=new ImageIcon(this.getClass().getResource("Redfireline1.png"));
										l11.setIcon(icc7);
										
										
										Thread.sleep(2000);
										ic99=new ImageIcon(this.getClass().getResource("Redcloud.jpg"));				
										l4.setIcon(ic99);
									}
			
								}
							
						}else
							if(message.equals("not ok"))
							{
								
		
								Thread.sleep(2000);
								l4.setVisible(false);
								Thread.sleep(1000);
								l4.setVisible(true);  //cloud
								Thread.sleep(1000);
								l4.setVisible(false);
								Thread.sleep(1000);
								l4.setVisible(true);
//								
//							
								Thread.sleep(2000);
								icc1=new ImageIcon(this.getClass().getResource("greenfire1.png"));
								l11.setIcon(icc1);
								
							
								Thread.sleep(2000);
								l5.setVisible(false);
								Thread.sleep(1000);
								l5.setVisible(true);  //fire2
								Thread.sleep(1000);
								l5.setVisible(false);
								Thread.sleep(1000);
								l5.setVisible(true);
								
								
								Thread.sleep(2000);
								icc6=new ImageIcon(this.getClass().getResource("red fire.jpg"));             
								l5.setIcon(icc6);
								
								
								
								Thread.sleep(2000);
								icc7=new ImageIcon(this.getClass().getResource("Redfireline1.png"));
								l11.setIcon(icc7);
								
								
								Thread.sleep(2000);
								ic99=new ImageIcon(this.getClass().getResource("Redcloud.jpg"));				
								l4.setIcon(ic99);
								
								
								Socket ssk=new Socket("localhost",5554);
		  						DataOutputStream dus=new DataOutputStream(ssk.getOutputStream());
		  		  				dus.writeUTF("not ok");
		  		  			    dus.writeUTF("not ok");
		  		  				System.out.println("data send to users");
							}
						

					}else
						
						if(status.equals("fail"))
						{
				
							
							l2.setVisible(false);
							Thread.sleep(1000);
							l2.setVisible(true);  //EndUser
							Thread.sleep(1000);
							l2.setVisible(false);
							Thread.sleep(1000);
							l2.setVisible(true);
							
							
							
							Thread.sleep(2000);
							l4.setVisible(false);
							Thread.sleep(1000);
							l4.setVisible(true);  //cloud
							Thread.sleep(1000);
							l4.setVisible(false);
							Thread.sleep(1000);
							l4.setVisible(true);
							
							
							Thread.sleep(2000);
							ic66=new ImageIcon(this.getClass().getResource("greenfire2.png"));
							l22.setIcon(ic66);
							
						
							Thread.sleep(2000);
							l6.setVisible(false);
							Thread.sleep(1000);
							l6.setVisible(true);  //fire2
							Thread.sleep(1000);
							l6.setVisible(false);
							Thread.sleep(1000);
							l6.setVisible(true);
							
							
							Thread.sleep(2000);
							ic77=new ImageIcon(this.getClass().getResource("red fire.jpg"));             
							l6.setIcon(ic77);
							
							
							Thread.sleep(2000);
							ic88=new ImageIcon(this.getClass().getResource("Redfireline2.png"));
							l22.setIcon(ic88);
							
							
							Thread.sleep(2000);
							ic99=new ImageIcon(this.getClass().getResource("Redcloud.jpg"));				
							l4.setIcon(ic99);					
					
		
							Socket ssk=new Socket("localhost",5554);
	  						DataOutputStream dus=new DataOutputStream(ssk.getOutputStream());
	  		  				dus.writeUTF("failure");
	  		  			    dus.writeUTF("failure");
	  		  				System.out.println("data send to users");
						}
				}
				
				//all clear lines 
				Thread.sleep(3000);
				l1.setIcon(ic1);
				l2.setIcon(ic2);
			
				l4.setIcon(ic4);
				l5.setIcon(ic5);
				l6.setIcon(ic6);
				l7.setIcon(ic7);
				l8.setIcon(ic8);
				l9.setIcon(ic9);
				l11.setIcon(ic11);
				l22.setIcon(ic22);
				l33.setIcon(ic33);
				l44.setIcon(ic44);
				l55.setIcon(ic55);
				lk2.setIcon(iss5);
		
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}
    
      }
      
      if(this.port==9989)
      {

			try 
			{
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(9989);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				String msg=dis.readUTF();
				System.out.println("Req recived from owner");
				
				if(msg.equals("success"))
				{
					//lines for owner login
					
					l1.setVisible(false);
					Thread.sleep(1000);
					l1.setVisible(true);  //owner
					Thread.sleep(1000);
					l1.setVisible(false);
					Thread.sleep(1000);
					l1.setVisible(true);
					
					Thread.sleep(2000);
					iss6=new ImageIcon(this.getClass().getResource("BigGreen1.png"));
					lk1.setIcon(iss6);
					
					
					
					Thread.sleep(2000);
					l4.setVisible(false);
					Thread.sleep(1000);
					l4.setVisible(true);  //cloud
					Thread.sleep(1000);
					l4.setVisible(false);
					Thread.sleep(1000);
					l4.setVisible(true);
					
					
					Thread.sleep(2000);
					ic66=new ImageIcon(this.getClass().getResource("greenfire2.png"));
					l22.setIcon(ic66);
					
				
					Thread.sleep(2000);
					l6.setVisible(false);
					Thread.sleep(1000);
					l6.setVisible(true);  //fire2
					Thread.sleep(1000);
					l6.setVisible(false);
					Thread.sleep(1000);
					l6.setVisible(true);
					
					DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
					dos.writeUTF("ok");
					
					
					
		
				}
				
				
				//all clear lines 
				Thread.sleep(3000);
				l1.setIcon(ic1);
				l2.setIcon(ic2);
			
				l4.setIcon(ic4);
				l5.setIcon(ic5);
				l6.setIcon(ic6);
				l7.setIcon(ic7);
				l8.setIcon(ic8);
				l9.setIcon(ic9);
				l11.setIcon(ic11);
				l22.setIcon(ic22);
				l33.setIcon(ic33);
				l44.setIcon(ic44);
				l55.setIcon(ic55);
				lk1.setIcon(iss4);
    
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}
  
      }
      
      if(this.port==8989)
      {

			try 
			{
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(8989);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				String msg=dis.readUTF();
				System.out.println("Req recived from owner");
				
				if(msg.equals("failure"))
				{
					//lines for owner login
					
					l1.setVisible(false);
					Thread.sleep(1000);
					l1.setVisible(true);  //owner
					Thread.sleep(1000);
					l1.setVisible(false);
					Thread.sleep(1000);
					l1.setVisible(true);
					
					
					
					Thread.sleep(2000);
					l4.setVisible(false);
					Thread.sleep(1000);
					l4.setVisible(true);  //cloud
					Thread.sleep(1000);
					l4.setVisible(false);
					Thread.sleep(1000);
					l4.setVisible(true);
					
					
					Thread.sleep(2000);
					ic66=new ImageIcon(this.getClass().getResource("greenfire2.png"));
					l22.setIcon(ic66);
					
				
					Thread.sleep(2000);
					l6.setVisible(false);
					Thread.sleep(1000);
					l6.setVisible(true);  //fire2
					Thread.sleep(1000);
					l6.setVisible(false);
					Thread.sleep(1000);
					l6.setVisible(true);
					
					
					Thread.sleep(2000);
					ic77=new ImageIcon(this.getClass().getResource("red fire.jpg"));             
					l6.setIcon(ic77);
					
					
					Thread.sleep(2000);
					ic88=new ImageIcon(this.getClass().getResource("Redfireline2.png"));
					l22.setIcon(ic88);
					
					
					Thread.sleep(2000);
					ic99=new ImageIcon(this.getClass().getResource("Redcloud.jpg"));				
					l4.setIcon(ic99);					
			
					
					DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
					dos.writeUTF("not ok");
		
				}
				
				//all clear lines 
				Thread.sleep(3000);
				l1.setIcon(ic1);
				l2.setIcon(ic2);
			
				l4.setIcon(ic4);
				l5.setIcon(ic5);
				l6.setIcon(ic6);
				l7.setIcon(ic7);
				l8.setIcon(ic8);
				l9.setIcon(ic9);
				l11.setIcon(ic11);
				l22.setIcon(ic22);
				l33.setIcon(ic33);
				l44.setIcon(ic44);
				l55.setIcon(ic55);
  
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}

	  
	  
  
    
      }
      
      if(this.port==7776)	  
      {
			try 
			{
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(7776);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				String msg=dis.readUTF();
				System.out.println("Req recived from owner");
				
				if(msg.equals("success"))
				{
					//lines for owner login
					
					l2.setVisible(false);
					Thread.sleep(1000);
					l2.setVisible(true);  //EndUser
					Thread.sleep(1000);
					l2.setVisible(false);
					Thread.sleep(1000);
					l2.setVisible(true);
					
					Thread.sleep(2000);
					iss7=new ImageIcon(this.getClass().getResource("Biggreen2.png"));
					lk2.setIcon(iss7);
					
					
					
					Thread.sleep(2000);
					l4.setVisible(false);
					Thread.sleep(1000);
					l4.setVisible(true);  //cloud
					Thread.sleep(1000);
					l4.setVisible(false);
					Thread.sleep(1000);
					l4.setVisible(true);
					
					
					Thread.sleep(2000);
					ic66=new ImageIcon(this.getClass().getResource("greenfire2.png"));
					l22.setIcon(ic66);
					
				
					Thread.sleep(2000);
					l6.setVisible(false);
					Thread.sleep(1000);
					l6.setVisible(true);  //fire2
					Thread.sleep(1000);
					l6.setVisible(false);
					Thread.sleep(1000);
					l6.setVisible(true);
					
					DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
					dos.writeUTF("ok");
					
					
					
		
				}
				
				
				//all clear lines 
				Thread.sleep(3000);
				l1.setIcon(ic1);
				l2.setIcon(ic2);
			
				l4.setIcon(ic4);
				l5.setIcon(ic5);
				l6.setIcon(ic6);
				l7.setIcon(ic7);
				l8.setIcon(ic8);
				l9.setIcon(ic9);
				l11.setIcon(ic11);
				l22.setIcon(ic22);
				l33.setIcon(ic33);
				l44.setIcon(ic44);
				l55.setIcon(ic55);
				lk2.setIcon(iss5);
  
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}

    
      }
      
      if(this.port==7778)
      {
			try 
			{
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(7778);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				String msg=dis.readUTF();
				System.out.println("Req recived from owner");
				
				if(msg.equals("failure"))
				{
					//lines for owner login
					
					l2.setVisible(false);
					Thread.sleep(1000);
					l2.setVisible(true);  //EndUser
					Thread.sleep(1000);
					l2.setVisible(false);
					Thread.sleep(1000);
					l2.setVisible(true);
					
					
					
					Thread.sleep(2000);
					l4.setVisible(false);
					Thread.sleep(1000);
					l4.setVisible(true);  //cloud
					Thread.sleep(1000);
					l4.setVisible(false);
					Thread.sleep(1000);
					l4.setVisible(true);
					
					
					Thread.sleep(2000);
					ic66=new ImageIcon(this.getClass().getResource("greenfire2.png"));
					l22.setIcon(ic66);
					
				
					Thread.sleep(2000);
					l6.setVisible(false);
					Thread.sleep(1000);
					l6.setVisible(true);  //fire2
					Thread.sleep(1000);
					l6.setVisible(false);
					Thread.sleep(1000);
					l6.setVisible(true);
					
					
					Thread.sleep(2000);
					ic77=new ImageIcon(this.getClass().getResource("red fire.jpg"));             
					l6.setIcon(ic77);
					
					
					Thread.sleep(2000);
					ic88=new ImageIcon(this.getClass().getResource("Redfireline2.png"));
					l22.setIcon(ic88);
					
					
					Thread.sleep(2000);
					ic99=new ImageIcon(this.getClass().getResource("Redcloud.jpg"));				
					l4.setIcon(ic99);					
			
					
					DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
					dos.writeUTF("not ok");
		
				}
				
				//all clear lines 
				Thread.sleep(3000);
				l1.setIcon(ic1);
				l2.setIcon(ic2);
			
				l4.setIcon(ic4);
				l5.setIcon(ic5);
				l6.setIcon(ic6);
				l7.setIcon(ic7);
				l8.setIcon(ic8);
				l9.setIcon(ic9);
				l11.setIcon(ic11);
				l22.setIcon(ic22);
				l33.setIcon(ic33);
				l44.setIcon(ic44);
				l55.setIcon(ic55);

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
//		ViewFiles view=new ViewFiles();
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

			File f = new File("cloud/" + Selectfname);
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

					System.out.println("Data is:  "+ new AES().encrypt(data, keyWord));

					
					content1 = tf.getText();
					String decData = new AES().decrypt(data, keyWord);

					System.out.println("decdata		:" + decData);

					tf.setText(decData);

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

		if (content2.equals(content1)) 
		{
			System.out.println("File is not modified");
			
		} else {
			try {

				content2 = new AES().encrypt(tf.getText(), keyWord);

//				String nm = fntf.getText();

				FileOutputStream fos = new FileOutputStream("cloud/"+ Selectfname);
				fos.write(content2.getBytes());
				fos.close();

				MessageDigest md = MessageDigest.getInstance("SHA1");
				FileInputStream in1 = new FileInputStream("cloud\\"
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
				String sql = "update cloudserver set mac='" + mac
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
	
	if(ae.getSource()==mi1)
	{
		  String name = JOptionPane.showInputDialog("Enter owner name");
		  String size = JOptionPane.showInputDialog("Enter size");
		  String cost = JOptionPane.showInputDialog("Enter cost");
		  
		  DBCon db=new DBCon();
     	  Connection con=db.getConnection();
     	  
     	 try 
     	 {
			Statement st=con.createStatement();
			String sql="insert into Account values ('"+name+"','"+size+"','"+cost+"')";
			st.executeUpdate(sql);
			System.out.println("data inserterd");
			
			JOptionPane.showMessageDialog(null,"Owner Created Successfully");
     		 
		} catch (Exception e) {
			// TODO: handle exception
		}
     	  
		  
	}
	
	if(ae.getSource()==mi2)
	{
//		Viewowner owner=new Viewowner();
	}
	
	
	
	
     }
}
