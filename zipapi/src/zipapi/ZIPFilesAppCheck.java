package zipapi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ZIPFilesAppCheck {

	
	/*
	 * This App creates a ZIP file for some content file-wise
	 * 
	 */
	
	
	
	
	public static void main(String[] args) {

		// Demonstration of writing a file and zipping 
		
		// This content can be fetched from DB or rest API or some file.
		String fileContent = "This data comes from eirther DB or some \nservices which needs to be stored in some file and then \nzipped it and store it to some location";

		// key : file name in which you want to write the above content
		// Value is content to be written in the filename
		
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

		// Extension of files can be anything except .exe
		
		map.put("file1.txt", "File 1: Content ==>"+ fileContent);
		map.put("file2.txt", "File 2: Content ==>"+ fileContent);

		// Put the ZIP file name 
		
		// Below extensions can only be accepted.
		// rar or .rar
		// zip or .zip
		
		Zipper zipper = new Zipper("myzip",".RAR", map);
		
		// You can also provide the path name where zip should be created, like below. Constructor is overloaded.
		
		//Zipper zipper = new Zipper("myzip", "rar", "/home/diwakar/", map);
		
		boolean status = zipper.zipFiles(zipper);

		System.out.println("Zip status ="+ status);
		
		
		// Now, un-zipping the file
		
		// way-1 : you can provide the path
		//String path = "/home/diwakar/";
		// ConcurrentHashMap<String, String> filesContent = new UnZipper().getContentMap("ch123.rar", path);
		
		//way-2
		// Default path would be the same where this app is getting executed
		
		ConcurrentHashMap<String, String> filesContent = new UnZipper().getContentMap("myzip.rar");
		
		
		if(filesContent!=null)
		{
		for(Map.Entry<String,String> entry : filesContent.entrySet())
		{
			System.out.println("File Name: "+ entry.getKey() + " File Content "+ entry.getValue());
			
		}
		}
		else
		{
			System.out.println("Some issue Occured!!");
		}
		
		

	}



}
