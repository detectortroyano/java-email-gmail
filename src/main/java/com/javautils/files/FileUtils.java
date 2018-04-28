package com.javautils.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils{
	
	public List<String> listFiles(String sourceDirectory, String dateStr, String dateEnd) {
		File folder =  new File(sourceDirectory);
		File[] files = null;
		if(dateStr == null || dateStr.equals("") || dateEnd == null || dateEnd.equals("") ) {
	        files = folder.listFiles();
        }else {
			FileFilterDateIntervalUtils filter = 
		            new FileFilterDateIntervalUtils(dateStr, dateEnd);        
	        files = folder.listFiles(filter);
        }
        List<String> filesName = new ArrayList<String>();
        for (File f : files) {
        	filesName.add(f.getName());
        }
        return filesName;
	}

    public void moveAndDeleteFile(String source, String target){
    	InputStream inStream = null;
    	OutputStream outStream = null;		
    	try{
    	    File afile =new File(source);
    	    File bfile =new File(target);
    		
    	    inStream = new FileInputStream(afile);
    	    outStream = new FileOutputStream(bfile);
        	
    	    byte[] buffer = new byte[1024];
    		
    	    int length;
    	    //copy the file content in bytes 
    	    while ((length = inStream.read(buffer)) > 0){    	  
    	    	outStream.write(buffer, 0, length);    	 
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	    
    	    //delete the original file
    	    afile.delete();    	    
    	    //System.out.println("File is move and delete successful!");    	    
    	}catch(IOException e){
    	    System.out.println("Error "+e);
    	}
    }
    
    public void moveAndDeleteFiles(List<String> files, String sourceDirectory,
    		String targetDirectory) {
        for (String f : files) {
            FileUtils fu = new FileUtils();
            String ds = sourceDirectory + f;
            String df = targetDirectory + f;
            fu.moveAndDeleteFile(ds, df);
        }
        System.out.println("Files is move and deleted successful!"); 
    }

}