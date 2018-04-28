package com.javautils.email;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.smtp.SMTPTransport;

public class Gmail {
	
	private static String FROM_USER_EMAIL = "email@gmail.com";
	private static String SMTP_HOST = "smtp.gmail.com";
	private static String FROM_FULL_USERNAME = "Angel Ricardo Vega Hernandez";
	private static String FROM_PASSWORD_EMAIL = "password";
	
	private Properties getProperties(String tipo, String host, String port) {
        Properties props = System.getProperties();        
        if(tipo.equals("TTLS")) {
    		props.put("mail.smtp.auth", "true");
    		props.put("mail.smtp.starttls.enable", "true");      	
        }else if (tipo.equals("SSL")) {        
	        props.put("mail.smtp.socketFactory.port", port);
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			//props.put("mail.smtp.sasl.mechanisms", "XOAUTH2");
        }
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
        return props;
	}

    public void sendMail(String toEmail, String subject, String body, List<String> attachFiles, String directoryAttach) throws UnsupportedEncodingException, MessagingException {
        final String smtpUserName = FROM_USER_EMAIL;
        String smtpServerHost = SMTP_HOST;
        String fromUserFullName = FROM_FULL_USERNAME;
        final String fromUserEmail = FROM_USER_EMAIL;
        final String password = FROM_PASSWORD_EMAIL;
        //Properties props = this.getProperties("TTLS", smtpServerHost, "587");
        Properties props = this.getProperties("SSL", smtpServerHost, "465");

		Session session = Session.getDefaultInstance(props);

	    session.setDebug(true);
	
	    // creates a new e-mail message
	    Message msg = new MimeMessage(session);
	    msg.setFrom(new InternetAddress(fromUserEmail, fromUserFullName));
	    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	    msg.setSubject(subject);
	    
	    // creates message part
	    MimeBodyPart messageBodyPart = new MimeBodyPart();
	    messageBodyPart.setContent(body, "text/html");            
	    
	    // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        String directory = directoryAttach == null ? null : directoryAttach;
        if (attachFiles != null && attachFiles.size() > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart(); 
                try {
                    attachPart.attachFile(directory + filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } 
                multipart.addBodyPart(attachPart);
            }
        }
	 
	    // sets the multi-part as e-mail's content
	    msg.setContent(multipart);

	    SMTPTransport transport = new SMTPTransport(session, null);
	    transport.connect(smtpServerHost, smtpUserName, password);
	    transport.sendMessage(msg, msg.getAllRecipients());
    }
    
    public void sendMailSession(String toEmail, String subject, String body, List<String> attachFiles, String directoryAttach) throws UnsupportedEncodingException, MessagingException {
        final String smtpUserName = FROM_USER_EMAIL;
        String smtpServerHost = SMTP_HOST;
        String fromUserFullName = FROM_FULL_USERNAME;
        final String fromUserEmail = FROM_USER_EMAIL;
        final String password = FROM_PASSWORD_EMAIL;
        //Properties props = this.getProperties("TTLS", smtpServerHost, "587");
        Properties props = this.getProperties("SSL", smtpServerHost, "465");

        javax.mail.Authenticator auth = new javax.mail.Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUserName, password);
            }
        };
        Session session = Session.getInstance(props, auth);

	    session.setDebug(true);
	
	    // creates a new e-mail message
	    Message msg = new MimeMessage(session);
	    msg.setFrom(new InternetAddress(fromUserEmail, fromUserFullName));
	    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	    msg.setSubject(subject);
	    
	    // creates message part
	    MimeBodyPart messageBodyPart = new MimeBodyPart();
	    messageBodyPart.setContent(body, "text/html");            
	    
	    // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        String directory = directoryAttach == null ? null : directoryAttach;
        if (attachFiles != null && attachFiles.size() > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart(); 
                try {
                    attachPart.attachFile(directory + filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } 
                multipart.addBodyPart(attachPart);
            }
        }
	 
	    // sets the multi-part as e-mail's content
	    msg.setContent(multipart);

	    Transport.send(msg);
    }    
}