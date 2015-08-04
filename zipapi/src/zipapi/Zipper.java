package zipapi;

/*
 * @author
 * Diwakar Choudhary
 * 
 * 
 * Description
 * This Class is for writing into files and compress it to a zip file.
 */

import java.io.FileOutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {

	private String zippedFileName;
	private String zipFileExtension;
	private ConcurrentHashMap<String, String> contentMap;
	private String zipFilePath;

	public Zipper(String zippedFileName, String zipFileExtension,String zipFilePath, ConcurrentHashMap<String, String> contentMap) {

		this.zippedFileName = zippedFileName;
		this.zipFileExtension = zipFileExtension;
		this.zipFilePath = zipFilePath;
		this.contentMap = contentMap;	
	}

	public Zipper(String zippedFileName, String zipFileExtension, ConcurrentHashMap<String, String> contentMap) {

		this.zippedFileName = zippedFileName;
		this.zipFileExtension = zipFileExtension;
		this.contentMap = contentMap;	
	}

	public String getZippedFileName() {
		return zippedFileName;
	}

	public void setZippedFileName(String zippedFileName) {
		this.zippedFileName = zippedFileName;
	}


	public String getZipFileExtension() {
		return zipFileExtension;
	}

	public void setZipFileExtension(String zipFileExtension) {
		this.zipFileExtension = zipFileExtension;
	}

	public String getZipFilePath() {
		return zipFilePath;
	}

	public void setZipFilePath(String zipFilePath) {
		this.zipFilePath = zipFilePath;
	}

	public ConcurrentHashMap<String, String> getContentMap() {
		return contentMap;
	}

	public void setContentMap(ConcurrentHashMap<String, String> contentMap) {
		this.contentMap = contentMap;
	}


	public  boolean zipFiles(Zipper zipper)
	{
		boolean isZipped = false;
		ZipOutputStream out= null;

		System.out.println("ZIP File "+zipper.getZippedFileName() +" is being created...");

		try {

			//Validate the filename and extension

			String zipFileName  = getValidFileName(zipper.getZippedFileName(), zipper.getZipFileExtension());

			if(zipFileName!=null && zipFileName.length()<=255)
			{
				if(zipper.getZipFilePath()!=null)
				{
					zipFileName = zipper.getZipFilePath()+"/"+ zipFileName;
				}

				System.out.println("ZIP file name "+ zipFileName);
				out = new ZipOutputStream(new FileOutputStream(zipFileName));
			}
			else
			{
				System.out.println("Exception : Invalid file name of extension.");
				return isZipped;

			}


			for(Map.Entry<String, String> entry : zipper.getContentMap().entrySet())
			{

				// Add ZIP entry (files to the zip file) to output stream.
				out.putNextEntry(new ZipEntry(entry.getKey()));

				// Write content into the file
				out.write(entry.getValue().getBytes());

				// Close the file
				out.closeEntry();
			}

		}
		catch (Exception e) {
			System.out.println("Exception caught while creating zip file :"+ e);
			return isZipped;
		}

		finally
		{
			try {
				out.close();
			} catch (Exception e) {
				System.out.println("Exception occured while closing the zipwriter");
				return isZipped;
			}

		}

		System.out.println("ZIP file "+ zipper.getZippedFileName() + " is created successfully!! ");
		return true;


	}




	public String getValidFileName(String zippedFileName, String zipFileExtension)
	{

		String validZipFileName =null;

		if(!(zipFileExtension.equalsIgnoreCase("rar") || zipFileExtension.equalsIgnoreCase(".rar") || zipFileExtension.equalsIgnoreCase("zip") ||  zipFileExtension.equalsIgnoreCase(".zip")))
		{
			return null;
		}
		zipFileExtension = zipFileExtension.toLowerCase();

		//int count = zipFileExtension.length() - zipFileExtension.replace(".", "").length();

		if(zipFileExtension.contains("."))
		{
			validZipFileName = zippedFileName+zipFileExtension;
		}

		else
		{
			validZipFileName = zippedFileName+"."+zipFileExtension;
		}

		return validZipFileName;

	}

	public String toString()
	{
		return "ZipFile Name "+ zippedFileName + " Ext : "+ zipFileExtension + " Map "+ contentMap.toString();

	}

}
