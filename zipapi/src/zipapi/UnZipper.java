package zipapi;

/*
 * @author
 * Diwakar Choudhary
 * 
 * 
 * Description
 * This Class is for decompressing the zip file  into files and read the content of the file
 */


import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipper {
	
	public ConcurrentHashMap<String, String> getContentMap(String zipFileName, String path)
	{
		return  generateMap(path+"/"+zipFileName);
	}

	public ConcurrentHashMap<String, String> getContentMap(String zipFileName)
	{
        return  generateMap(zipFileName);
	}
	
	public ConcurrentHashMap<String, String> generateMap(String fileName)
	{
		
		System.out.println("File Name is "+ fileName);
		
		ConcurrentHashMap<String, String> fileContentMap = new ConcurrentHashMap<String, String>();

		try {

			// Open the ZIP file
			ZipInputStream in = new ZipInputStream(new FileInputStream(fileName));

			// While we have other entry
			ZipEntry entry = in.getNextEntry();

			while(entry != null){

				System.out.println("Reading: " + entry.getName());

				byte[] buf = new byte[1024];

				int len = in.read(buf);
				String fileContent = new String(buf, 0, len);

				fileContentMap.put(entry.getName(),fileContent);

				entry = in.getNextEntry();
			} 
			// Close the ZIP file
			in.close();
		} catch (IOException e) {
			System.out.println("Exception while reading back the ZIP file: " + e);
			return null;
		}
		
		return fileContentMap;
		
	}
}
