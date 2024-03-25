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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;
public class Authorized_Authority1 implements ActionListener
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
	
	JMenuBar mbr;
	JMenu m1;
	JMenuItem mi1, mi2,mi3;
  
  public Authorized_Authority1() 
  {
	
	  f=new JFrame("Authorized_Authority1::A Blockchain Approach to Ensuring Provenance to Outsourced Cloud Data in a Sharing Ecosystem");
	  p=new JPanel();
	  p.setBackground(new Color(170, 130, 188));
	  
//	 p.setBackground(Color.white);
	 
	  
	  b1=new JButton("View Attacker");
	  b1.setBounds(150, 400, 150, 30);
	  p.add(b1);
	  
	  
	  
	  b2=new JButton("UnBlock User");
	  b2.setBounds(350, 400, 150, 30);
	  p.add(b2);
	  
	  b3=new JButton("Biometric Report");
	  b3.setBounds(450, 585, 150, 30);
//	  p.add(b3);
	  
	  ic1=new ImageIcon(this.getClass().getResource("wallpaper1.jpg"));
	  l1=new JLabel();
	  l1.setIcon(ic1);
	  l1.setBounds(0, 0, 700, 500);
	  p.add(l1);
	  
	  
	    mbr = new JMenuBar();
//		f.setJMenuBar(mbr);
		m1 = new JMenu("File");
		mi1 = new JMenuItem("View Attacker");
		mi2 = new JMenuItem("UnBlock User");
		mi3 = new JMenuItem("Exit");
		
		
		mbr.add(m1);
		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
	  
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
		mi3.addActionListener(this);
	  
	  int[] port = new int[] { 401,7657,201,5005,4324,6767};

		for (int i = 0; i < 6; i++) {
			Thread th = new Thread(new PortListener(port[i]));
			th.start();
		}
	  
	  
}
  public static void main(String[] args) 
  {
	new Authorized_Authority1();
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
      if(this.port==7657)
      {

			try 
			{
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(7657);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				
				String username=dis.readUTF();
				String fname=dis.readUTF();
				String key=dis.readUTF();
				String peer=dis.readUTF();
				
				System.out.println(" data recived");
				
				
				SimpleDateFormat sdfDate = new SimpleDateFormat(
				"dd/MM/yyyy");
		        SimpleDateFormat sdfTime = new SimpleDateFormat(
				"HH:mm:ss");

		          Date now = new Date();
		          String strDate = sdfDate.format(now);
		          String strTime = sdfTime.format(now);
		          String dt = strDate + "   " + strTime;
				
				Statement stmt1 = con.createStatement();
				String sql2="insert into Attacker values('"+username+"','"+fname+"','"+key+"','"+peer+"','"+dt+"')";
				stmt1.executeUpdate(sql2);
				System.out.println("Data inserted into Attacker  db");
				
				
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}

  
    
    	  
      }
      
      if(this.port==4324)
      {

			try 
			{
				 DBCon db=new DBCon();
	        	 Connection con=db.getConnection();
				
			   ServerSocket s1=new ServerSocket(4324);
			   while(true)
			   {
				Socket ss1=s1.accept();
				
				DataInputStream dis=new DataInputStream(ss1.getInputStream());
				
				String username=dis.readUTF();
				
				
				System.out.println(" data recived");
				
				
				Statement stmt1 = con.createStatement();
				String sql="select * from Attacker where username='"+username+"'";
				ResultSet rs=stmt1.executeQuery(sql);
				if(rs.next()==true)
				{
						DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
						dos.writeUTF("not ok");
						
				}else
					if(rs.next()==false)
				{
						DataOutputStream dos=new DataOutputStream(ss1.getOutputStream());
						dos.writeUTF("ok");
					
				}
				
			   }
			} catch (Exception e) {
				// TODO: handle exception
			}

  
    
    	  
      }
      
    	/*  if(this.port==6767)
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
      
        
          }*/
      
      
      
		}
  
  }

@Override
public void actionPerformed(ActionEvent ae) 
     {
	
	if(ae.getSource()==b1)
	{
		try {

			Vector heading = new Vector();

			 heading.addElement("Username");
			 heading.addElement("Filename");
			 heading.addElement("Sk");
			 heading.addElement("Peer name");
			 heading.addElement("Date and Time");

			 Vector data=new Vector();
			DBCon db = new DBCon();
			Connection con = db.getConnection();
             Statement stmt = con.createStatement();
             
             
             String query = "SELECT * from Attacker";
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
		//UnBlock Users


		 DBCon db=new DBCon();
  	    Connection con=db.getConnection();
		
		String name = JOptionPane.showInputDialog("Enter Username To UnBlock");
		
		try 
		{
			Statement stmt19 = con.createStatement();
			String sql16 = "delete * from Attacker where Username='"+name+"'";
			stmt19.executeUpdate(sql16);
			System.out.println("user deleted successfully");
			JOptionPane.showMessageDialog(null,"User UnBlocked Successfully");
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
	
	
	
		
	}
	
	if(ae.getSource()==mi1)
	{
		
	}
	if(ae.getSource()==mi2)
	{}
	
	
	
	
     }
}
