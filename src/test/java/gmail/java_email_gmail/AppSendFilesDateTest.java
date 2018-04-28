package gmail.java_email_gmail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.javautils.email.Gmail;
import com.javautils.files.FileUtils;

public class AppSendFilesDateTest {

	@Test
	public void test() {
		testFilesByDate();
	}
	
	public void testFilesByDate(){
	    System.out.println ("**** Enter class AppSendFilesByDateTest");
        Date date = new Date();
	    try
	    {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   	
	        SimpleDateFormat sdfh = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String dateStr = sdf.format(date) + " 00:00:00";
	        String dateEnd = sdfh.format(date);
	        String sourceDirectory = "C:\\directory\\";
	        String targetDirectory = "C:\\directory_history\\";
	        
	        FileUtils fu = new FileUtils();
	        List<String> attach = fu.listFiles(sourceDirectory, dateStr, dateEnd);

	        if(attach != null && attach.size()>0) {
	        	Gmail email = new Gmail();
		        email.sendMail("angelricardo.uthh@gmail.com", "asunto", "Hola", attach, sourceDirectory);
		        
		        fu.moveAndDeleteFiles(attach, sourceDirectory, targetDirectory);	        
	        }
	        System.out.println("**** Exit from class AppSendFilesByDateTest - no errors");
	    }catch(Exception ex){
	    	System.err.println("Exception information " + ex);
	    }
	}

}

