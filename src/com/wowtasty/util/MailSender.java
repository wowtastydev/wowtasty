package com.wowtasty.util;

import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class MailSender {
	
	/** Logger */	
	private static Logger logger = Logger.getLogger(MailSender.class);
	
    /**
     The SMTP Server to send the emails and the user name and password to
     authenticate with the server
     */
	private String smtpServer = "";
	private String userName = ""; 
	private String password = "";
	private String infoEmail = "";
	private String infoEmailName = "";
        
    /**
    * Contructor : Set up SMTP information from config.properties
    */        
	public MailSender() {
    	Properties config = (Properties)SessionUtil.getInstance().getApplicationAttribute(Constants.KEY_SESSION_CONFIG_PROPERTIES);
    	smtpServer = config.getProperty("smtpServer");
    	userName = config.getProperty("userName");
    	password = config.getProperty("password");
    	infoEmail = config.getProperty("infoEmail");
    	infoEmailName = config.getProperty("infoEmailName");
    }
       
    /**
     *
     * @param to Cannot be Null
     * @param subject Cannot be Null
     * @param mailText
     */
    public synchronized boolean sendEmail(String to, String subject, String mailText) throws Exception {
        if((to == null)  && (subject == null)) {
        	return false;
        }
                
        if(mailText == null) {
        	mailText = "";
        }
                
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.host", smtpServer);
            props.put("mail.user", userName);
            props.put("mail.password", password);
            props.put("mail.port", "587");
            //SMTP server authentication is set to false, by default. Setting it to true as shown below
            props.put( "mail.smtp.auth", "true" ) ;
 
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
 
            //Setting the 'from', 'to', 'cc' addresses and the 'subject'
            message.setFrom(new InternetAddress(infoEmail, infoEmailName));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
 
            //Making the mail body as inline and of html type
            MimeMultipart mp = new MimeMultipart();
            MimeBodyPart text = new MimeBodyPart();
            text.setDisposition(Part.INLINE);
            text.setContent(mailText, "text/html");
            mp.addBodyPart(text);
            message.setContent(mp);
 
            //SMTP authentication
            Transport transport = session.getTransport ("smtp") ;
            transport.connect (smtpServer, userName, password) ;
            message.saveChanges();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception e){
        	logger.error("!!!!!FileUtil downloadOrderList occurs error:" + e);
        	throw new RuntimeException(e);
        }
    }
}
