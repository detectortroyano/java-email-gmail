package gmail.java_email_gmail;

import java.io.UnsupportedEncodingException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.mail.MessagingException;

import org.junit.Test;

import com.javautils.email.Gmail;

public class AppSendMailTest {

	@Test
	public void test() {
	    Logger logger = Logger.getLogger("log");  
	    FileHandler fh;		
		try {
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("C:/logs/log_listener.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formater = new SimpleFormatter();
	        fh.setFormatter(formater);

			Gmail gmail = new Gmail();
	        gmail.sendMail("angelricardo.uthh@gmail.com", "asunto", "Hola", null, null);
			//gmail.sendMailSession("angelricardo.uthh@gmail.com", "asunto", "Hola", null, null);
		}catch (UnsupportedEncodingException e) {
			System.out.println("Error => " + e);
			logger.info("Error => " + e);
		}catch (MessagingException e) {
			System.out.println("Error => " + e);
			logger.info("Error => " + e);
		}catch (Exception e) {
			System.out.println("Error => " + e);
			logger.info("Error => " + e);
		}
	}
}

