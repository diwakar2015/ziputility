package com.zip.common.util;

import java.io.BufferedInputStream;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 
 * @author: Diwakar Choudhary
 */

@WebServlet("/ZipDownloadServlet")
public class ZipDownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String ZIP_FILE_NAME ="content.zip";
	private static final String FILE_NAME ="exportedfile.txt";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userContent =  request.getParameter("content");
		
		 try {
	            //
	            // The path below is the root directory of data to be
	            // compressed.
	            //
	            String path = getServletContext().getRealPath("/WEB-INF/datafiles");
	            System.out.println("path is: "+ path);
	 
	            File directory = new File(path);
	            String[] files = directory.list();
	            
	            System.out.println("files ==>");
	            for(String fileName : files)
	            {
	            	System.out.println("File found :"+ fileName);
	            }
	 
	            //
	            // Checks to see if the directory contains some files.
	            //
	            if (files != null && files.length > 0) {
	 
	                //
	                // Call the zipFiles method for creating a zip stream.
	                //
	                byte[] zip = zipFiles(directory, files);
	                
	                byte[] zippedData = zipFilesForContent(userContent);
	 
	                //
	                // Sends the response back to the user / browser. The
	                // content for zip file type is "application/zip". We
	                // also set the content disposition as attachment for
	                // the browser to show a dialog that will let user 
	                // choose what action will he do to the sent content.
	                //
	                ServletOutputStream sos = response.getOutputStream();
	                response.setContentType("application/zip");
	          
	                response.setHeader("Content-Disposition","attachment;filename=\"" + ZIP_FILE_NAME + "\""); 
	 
	                sos.write(zippedData);
	                sos.flush();
	            }
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	
	
	
	
	
	/**
     * Compress the given directory with all its files.
     */
    private byte[] zipFiles(File directory, String[] files) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        byte bytes[] = new byte[2048];
 
        for (String fileName : files) {
            FileInputStream fis = new FileInputStream(directory.getPath() + 
                ZipDownloadServlet.FILE_SEPARATOR + fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);
 
            zos.putNextEntry(new ZipEntry(fileName));
 
            int bytesRead;
            while ((bytesRead = bis.read(bytes)) != -1) {
                zos.write(bytes, 0, bytesRead);
            }
            zos.closeEntry();
            bis.close();
            fis.close();
        }
        zos.flush();
        baos.flush();
        zos.close();
        baos.close();
 
        return baos.toByteArray();
    }
    
    
    private byte[] zipFilesForContent(String content) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
       
            zos.putNextEntry(new ZipEntry(FILE_NAME));
 
            zos.write(content.getBytes());
            zos.closeEntry();
           
      
        zos.flush();
        baos.flush();
        zos.close();
        baos.close();
 
        return baos.toByteArray();
    }

    

}
