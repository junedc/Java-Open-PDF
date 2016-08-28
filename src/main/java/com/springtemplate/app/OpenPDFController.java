package com.springtemplate.app;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Handles requests for the application welcome page.
 */
@Controller

public class OpenPDFController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(OpenPDFController.class);


	@RequestMapping("/welcome1")
	public String welcome() {
		return "openpdffile";
	}
	
	
	@RequestMapping("/itextheresaveas")
	public void itextheresaveas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/pdf");

		// This line show the save as pdf. Because of "attachement" keyword
		response.setHeader("Content-Disposition", "attachment;filename=sample"
				+ ".pdf");

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream()); 
			document.open();

			PdfPTable table = new PdfPTable(2);
			table.addCell("1");
			table.addCell("2");
			table.addCell("3");
			table.addCell("4");
			table.addCell("5");
			table.addCell("6");

			document.add(table);
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/itextshowinbrowser")
	public   void  itextshowinbrowser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		response.setContentType("application/pdf");	
		response.setHeader("Content-Disposition", "inline;filename=sample.pdf");
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream()); 
			document.open();

			PdfPTable table = new PdfPTable(2);
			table.addCell("1");
			table.addCell("2");
			table.addCell("3");
			table.addCell("4");
			table.addCell("5");
			table.addCell("6");


			document.add(table);
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/itextshowinbrowserwithfilename")
	public   void  itextshowinbrowserwithfilename(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

       //HttpHeaders headers = new HttpHeaders();
       //headers.setContentType(MediaType.parseMediaType("application/pdf"));
       //headers.add("content-disposition", "inline;filename=myfile.pdf");
		response.setHeader("Content-Disposition", "attachment;filename=sample"
				+ ".pdf");
		response.setContentType("application/pdf");	
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream()); 
			document.open();

			PdfPTable table = new PdfPTable(2);
			table.addCell("1");
			table.addCell("2");
			table.addCell("3");
			table.addCell("4");
			table.addCell("5");
			table.addCell("6");


			document.add(table);
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/openapdfinurl")
	public  void generatePdf( HttpServletRequest request, HttpServletResponse response) {
		String url = "https://www.immigration.govt.nz/documents/forms-and-guides/acceptableformslist.pdf";
		

	
		try {

			HttpUriRequest urirequest;
			urirequest = new HttpGet(url.trim());
			HttpClient client = new DefaultHttpClient();

			urirequest.addHeader("content-type", "application/pdf");			
			HttpResponse httpresponse =	client.execute(urirequest);	
			byte[] data = EntityUtils.toByteArray(httpresponse.getEntity());


			if (data != null){
				sendBytes(response, data, "application/pdf", data.length, "Invoice_111.pdf");
			}

		} catch (Exception e) {		
			logger.error ("generatePDF " + e.getMessage());		
		}

		

	}

	public static void sendBytes(HttpServletResponse resp, byte[] in, String mimeType, int length, String fileName) throws FileNotFoundException, IOException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(in); 
		sendStream(resp, inputStream, mimeType, length, fileName);
	}

	public static void sendStream(HttpServletResponse resp, InputStream in, String mimeType, int length, String fileName) throws FileNotFoundException, IOException {
		OutputStream out = null;
		try {
			if (in != null) {
				
		        // Set content type
		        resp.setContentType(mimeType);
		        // Set content size
		        resp.setContentLength(length);
		        // Set the file name
		        if(fileName!=null){
		        	resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		        }
		        // Open the file and output streams
		        out = resp.getOutputStream();
		    
		        // Copy the contents of the file to the output stream
		        byte[] buf = new byte[1024];
		        int count = 0;
		        while ((count = in.read(buf)) >= 0) {
		            out.write(buf, 0, count);
		        }
		        out.flush();
		        out.close();
			}
		}
		finally {
			try {in.close();}catch (Exception ex) { }
			try {out.close();}catch (Exception ex) { }
		}	
	}


}
