import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailSender {
    private final Session session;
    private String fromEmail;
    private final String password;

    public EmailSender() {
        Properties properties = new Properties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        System.setProperty("https.protocols", "TLSv1.2");

        String host = "smtp.gmail.com";
        properties.put("mail.smtp.host", host);

        // Sender credentials
        this.fromEmail = "random33501006@gmail.com";
        this.password = "ycsg fpsc kkir lusx";
        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        session.setDebug(true);
    }

    public void sendEmail(String recipientEmail, String emailBody, String subject) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
        message.setSubject(subject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/html");
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(bodyPart);
        message.setContent(multiPart);

        Transport.send(message); // Send the email
        System.out.println("Email successfully sent!");
    }

}