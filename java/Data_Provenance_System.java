import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Vector;
import javax.mail.*;

import javax.mail.MessagingException;
import javax.swing.*;

public class Data_Provenance_System implements ActionListener {
    JFrame f;
    JPanel p;
    JButton b1, b2, b3, showFilenamesButton;
    JScrollPane pane1 = new JScrollPane();

    public Data_Provenance_System() {
        f = new JFrame("Data Provenance System::A Blockchain Approach to Ensuring Provenance to Outsourced Cloud Data in a Sharing Ecosystem");
        p = new JPanel();
        p.setBackground(new Color(230, 220, 188));

        b1 = new JButton("View Metadata");
        b1.setBounds(250, 380, 150, 30);
        p.add(b1);

        showFilenamesButton = new JButton("Show Filenames");
        showFilenamesButton.setBounds(410, 380, 150, 30);
        p.add(showFilenamesButton);

        b2 = new JButton("Attacker Details");
        b2.setBounds(240, 385, 150, 30);
        // p.add(b2); // Initially commented out in your code

        b3 = new JButton("Biometric Report");
        b3.setBounds(450, 385, 150, 30);
        // p.add(b3); // Initially commented out in your code

        f.setSize(700, 500);
        f.setVisible(true);
        p.setLayout(null);
        f.add(p);

        b1.addActionListener(this);
        showFilenamesButton.addActionListener(this);
        // b2 and b3 actions are not defined in the provided code snippet

        int[] port = new int[]{401, 6777, 201, 2323, 5005, 5008, 6001, 6767};
        for (int i = 0; i < 8; i++) {
            Thread th = new Thread(new PortListener(port[i]));
            th.start();
        }
    }

    public static void main(String[] args) {
        new Data_Provenance_System();
    }

    private String promptForFilename() {
        String filename = JOptionPane.showInputDialog(f, "Enter the filename to view metadata:");
        return filename;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { // "View Metadata"
            // Request username and password in a single prompt
            JLabel usernameLabel = new JLabel("Username:");
            JTextField usernameField = new JTextField();
            JLabel passwordLabel = new JLabel("Password:");
            JPasswordField passwordField = new JPasswordField();

            Object[] loginFields = {
                    usernameLabel, usernameField,
                    passwordLabel, passwordField
            };

            int result = JOptionPane.showConfirmDialog(null, loginFields, "Authentication", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticateUser(username, password)) {
                    JOptionPane.showMessageDialog(f, "Authentication Successful.");
                    JLabel Filename = new JLabel("Enter File name :");
                    JTextField Filelabel = new JTextField();
                    int output = JOptionPane.showConfirmDialog(null, Filelabel, "Request Access", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    String file = Filelabel.getText();
                    if(output == JOptionPane.OK_OPTION){
                        String email = getEmailForUser(username);
                        System.out.println(email);
                        String content = getPasswordForFile(file);
                        System.out.println(file);
                        EmailSender emailsend = new EmailSender();
                        try {
                            emailsend.sendEmail(email,content,file);
                            JLabel pass = new JLabel("Password for the file:");
                            JTextField passField = new JPasswordField();
                            int passout = JOptionPane.showConfirmDialog(null, passField, "Password of file", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            if(passout == JOptionPane.OK_OPTION){
                                String entered = passField.getText();
                                System.out.println(entered);
                                if(entered.equals(content)){
                                    viewMetadata(file);
                                }
                            }

                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(f, "Authentication Failed.", "Access Denied", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (ae.getSource() == showFilenamesButton) {
            showAllFilenames();
        }
    }



    private String getPasswordForFile(String filename) {
        DBCon db = new DBCon();
        Connection con = db.getConnection();
        try {
            String sql = "SELECT password_hash FROM file_passwords WHERE filename = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, filename);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("password_hash");
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error accessing database: " + e.getMessage());
        }

        return null; // Password hash not found
    }
    // Helper method to get the email from the database
    private String getEmailForUser(String username) {
        DBCon db = new DBCon();
        Connection con = db.getConnection();
        try  {
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

        return null; // Email not found
    }

    private void showAllFilenames() {
        try {
            Vector<String> heading = new Vector<>();
            heading.addElement("File name");

            Vector<Vector<String>> data = new Vector<>();

            // Using DBCon class to get connection
            DBCon db = new DBCon();
            Connection connect = db.getConnection();

            Statement stmt = connect.createStatement();
            String query = "SELECT fname FROM Bootstrap";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.addElement(rs.getString("fname"));  // Retrieve values by column name
                data.addElement(row);
            }

            // Create and display the JFrame as before
            JFrame filenamesFrame = new JFrame("All Filenames");
            JPanel filenamesPanel = new JPanel();
            filenamesPanel.setLayout(new BoxLayout(filenamesPanel, BoxLayout.Y_AXIS));

            JTable table = new JTable(data, heading);
            JScrollPane scrollPane = new JScrollPane(table);

            filenamesPanel.add(scrollPane);
            filenamesFrame.add(filenamesPanel);
            filenamesFrame.pack();
            filenamesFrame.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database: " + e.getMessage());
        }
    }
    private boolean authenticateUser(String username, String password) {
        DBCon db=new DBCon();
        Connection con=db.getConnection();
        try  {
            String sql = "SELECT pass FROM userregister WHERE username = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("pass");
                        return password.equals(storedPassword);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error accessing database: " + e.getMessage());
            return false;
        }

        return false; // No matching username found
    }

    private void viewMetadata(String filename) {
        try {
            Vector<String> heading = new Vector<>();
            heading.addElement("File name");
            heading.addElement("Sk");

            Vector<Vector<String>> data = new Vector();

            DBCon db = new DBCon();
            Connection conn = db.getConnection();

            String query = "SELECT fname, sk FROM owner WHERE fname LIKE ?"; // Parameterized query

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, filename + "%"); // Set the filename with wildcard
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Vector<String> row = new Vector<>();
                        row.addElement(rs.getString("fname"));
                        row.addElement(rs.getString("sk"));
                        data.addElement(row);
                    }
                }
            }

            JTable table = new JTable(data, heading);
            pane1 = new JScrollPane(table);
            pane1.setBounds(100, 100, 600, 300);
            p.add(pane1);
            f.validate();
            f.repaint();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error accessing Database: " + e.getMessage());
            e.printStackTrace();
        }
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
            if (this.port == 6777) {
                try {
                    AES enc = new AES();
                    DBCon db = new DBCon();
                    Connection con = db.getConnection();

                    ServerSocket ss1 = new ServerSocket(6777);
                    System.out.println("inside port 6777");
                    while (true) {
                        Socket ss = ss1.accept();

                        DataInputStream dis = new DataInputStream(ss.getInputStream());
                        String fname = dis.readUTF();
                        String mac = dis.readUTF();
                        String pk = dis.readUTF();
                        String sk = dis.readUTF();
                        String sh = dis.readUTF();

                        System.out.println("peer name   " + sh);

                        System.out.println("data received");

                        Statement stmt1 = con.createStatement();
                        String sql2 = "insert into Bootstrap values('" + fname + "','" + pk + "','" + mac + "','" + sk + "','" + sh + "')";
                        stmt1.executeUpdate(sql2);
                        System.out.println("Data inserted into manager db");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (this.port == 6001) {
                try {
                    AES enc = new AES();
                    DBCon db = new DBCon();
                    Connection con = db.getConnection();

                    ServerSocket s1 = new ServerSocket(6001);
                    while (true) {
                        Socket ss1 = s1.accept();

                        DataInputStream dis = new DataInputStream(ss1.getInputStream());
                        String username = dis.readUTF();
                        String fname = dis.readUTF();

                        System.out.println("data received");

                        Socket sc = new Socket("localhost", 7878);
                        DataOutputStream dose = new DataOutputStream(sc.getOutputStream());
                        dose.writeUTF("sk req");
                        System.out.println("view send to engine");

                        DataInputStream diis = new DataInputStream(sc.getInputStream());
                        String msg = diis.readUTF();

                        Statement stmt1 = con.createStatement();
                        String sql2 = "select * from metadata where fname='" + fname + "'";
                        ResultSet rs = stmt1.executeQuery(sql2);

                        String sk = "";
                        if (rs.next() == true) {
                            sk = rs.getString(5);

                            DataOutputStream dos = new DataOutputStream(ss1.getOutputStream());
                            dos.writeUTF("found");
                            dos.writeUTF(sk);
                            System.out.println("sk send to engine");
                        } else {
                            DataOutputStream dos = new DataOutputStream(ss1.getOutputStream());
                            dos.writeUTF("not found");
                            dos.writeUTF("not found");
                            System.out.println("sk send to engine");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (this.port == 5008) {
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

                        System.out.println("data received");

                        Statement stmt1 = con.createStatement();
                        String sql2 = "insert into Attacker values('" + fname + "','" + fakem + "','" + date + "')";
                        stmt1.executeUpdate(sql2);
                        System.out.println("Data inserted into manager db");

                        DataOutputStream dos = new DataOutputStream(ss1.getOutputStream());
                        dos.writeUTF("taken");
                        System.out.println("reach msg send to pattern");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (this.port == 2323) {
                try {
                    DBCon db = new DBCon();
                    Connection con = db.getConnection();

                    ServerSocket s1 = new ServerSocket(2323);
                    while (true) {
                        Socket ss1 = s1.accept();

                        DataInputStream dis = new DataInputStream(ss1.getInputStream());

                        String fake = dis.readUTF();
                        System.out.println("fake data received");

                        Statement stmt1 = con.createStatement();
                        String sql2 = "update patternmanager set fakedata='" + fake + "'";
                        stmt1.executeUpdate(sql2);
                        System.out.println("Data inserted into manager db");

                        DataOutputStream dos = new DataOutputStream(ss1.getOutputStream());
                        dos.writeUTF("reach");
                        System.out.println("reach msg send to pattern");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (this.port == 6767) {
                try {
                    DBCon db = new DBCon();
                    Connection con = db.getConnection();

                    ServerSocket s1 = new ServerSocket(6767);
                    while (true) {
                        Socket ss1 = s1.accept();

                        DataInputStream dis = new DataInputStream(ss1.getInputStream());

                        String fname = dis.readUTF();
                        String shname = dis.readUTF();

                        System.out.println("fake data received");

                        String mac = "";

                        Statement stmt1 = con.createStatement();
                        String sql = "select * from Bootstrap where fname='" + fname + "' and peer='" + shname + "'";
                        ResultSet rss = stmt1.executeQuery(sql);
                        System.out.println("Query Executed");
                        if (rss.next() == true) {
                            System.out.println("File found");
                            mac = rss.getString(3);
                            System.out.println("OMAC       " + mac);
                        } else {
                        }

                        if (shname.equals("Stakeholder1")) {
                            Socket sck = new Socket("localhost", 2227);
                            DataOutputStream dos = new DataOutputStream(sck.getOutputStream());
                            dos.writeUTF("check");
                            dos.writeUTF(mac);
                            dos.writeUTF(fname);
                            System.out.println("mac send to server from Stakeholder1");
                        } else if (shname.equals("Stakeholder2")) {
                            Socket sck = new Socket("localhost", 2229);
                            DataOutputStream dos = new DataOutputStream(sck.getOutputStream());
                            dos.writeUTF("check");
                            dos.writeUTF(mac);
                            dos.writeUTF(fname);
                            System.out.println("mac send to server from Stakeholder2");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}