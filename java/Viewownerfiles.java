import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import java.io.*;
import java.util.*;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;

import java.net.*;

public class Viewownerfiles extends JFrame implements ActionListener
{
 JButton property,Reset;
 JPanel panel;
 JLabel label1;
  JTextField  text1;
  Vector data;
Vector heading;

JButton view;
JScrollPane pane;
JTable table;
int v,h;
String s,d,call,dt;
Container c;

JLabel imglabel;
Viewownerfiles(Vector data2) 
 {

setTitle("Owners Files::A Blockchain Approach to Ensuring Provenance to Outsourced Cloud Data in a Sharing Ecosystem");
c= getContentPane();
c.setLayout (new FlowLayout());


label1 = new JLabel();
label1.setText("Enter Username");

 text1 = new JTextField(20);
   property=new JButton("View Property");

   property.addActionListener(this);


//c.add(label1);
//c.add(text1);
//c.add(property);



c.setBackground(Color.ORANGE);
setSize(550,500);
setVisible(true);

try {
	 Vector heading = new Vector();
	 
	 
	 

	 heading.addElement("Username");
	 heading.addElement("fname");
	 heading.addElement("MAC");
	 heading.addElement("Privk");
	 heading.addElement("Sk");
	 heading.addElement("Stakeholder");
	 

			  
			JTable table = new JTable(data2,heading);
			  
			  pane = new JScrollPane(table);
			 
			  pane.setBounds(100,50,750,260);
			  c.add(pane);
			 // c.add(image);
	 } 
	 catch(Exception ex) {
		 ex.printStackTrace();
		 } 

  }


public void actionPerformed(ActionEvent ae)
{

Object o=ae.getSource();

if(o==property)
{
		
}

}

}