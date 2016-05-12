package com.springtemplate.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
