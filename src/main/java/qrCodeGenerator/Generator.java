package qrCodeGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import com.codoid.products.exception.FilloException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import Utility.Util;

public class Generator {
		
	public static void main(String[] args) throws Exception{
		writeQRCode();
	}
	
	
	public static void writeQRCodeToBlankPDF()  throws Exception{
		Document document = new Document();
		
        PdfWriter.getInstance(document, new FileOutputStream(".//generatedPDF//barcode.pdf"));
        document.open();
        //PdfContentByte cb = writer.getDirectContent();
 		String data = "Name-Umakanta Pattanaik,\r\n"
				+ "Employee ID-10200,\r\n"
				+ "From-10-06-2021,\r\n"
				+ "To-10-06-2021,\r\n"
				+ "Conduct-Good";
        BarcodeQRCode barcodeQRCode = new BarcodeQRCode(data, 1000, 1000, null);
        Image codeQrImage = barcodeQRCode.getImage();
        codeQrImage.scaleAbsolute(100, 100);
        document.add(codeQrImage);
        document.close();      
	}
	
	
	public static void writeQRCode() throws Exception{
		Util util = new Util();
		ArrayList<String> totalNumber = new ArrayList<String>();
		try {
			totalNumber = util.getTestData_SeqNos("qrCode");
		} catch (FilloException e) {
			e.printStackTrace();
			throw new Exception();
		}
		 for(String seq:totalNumber) {
        	 try {
        		 Hashtable<String,String> data = new Hashtable<String,String>();
            	 data = util.get_Data( "qrCode", seq);
            	 System.out.println(data.get("QR Code"));
            	 String qrCode = data.get("QR Code");
            	 String empID = data.get("Employee ID");
            	 PdfReader reader = new PdfReader(".//generatedPDF//BlankInputLetter//"+empID+".pdf");
                 PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(".//generatedPDF//letterWithQRCode//"+empID+".pdf"));
                 BarcodeQRCode barcodeQRCode = new BarcodeQRCode(qrCode, 1000, 1000, null);
                 Image image = barcodeQRCode.getImage();
                 image.scaleAbsolute(100, 100);
                 PdfImage stream = new PdfImage(image, "", null);
                 stream.put(new PdfName("ITXT_SpecialId"), new PdfName("123456789"));
                 PdfIndirectObject ref = stamper.getWriter().addToBody(stream);
                 image.setDirectReference(ref.getIndirectReference());
                 image.setAbsolutePosition(80, 150);
                 PdfContentByte over = stamper.getOverContent(1);
                 over.addImage(image);
                 stamper.close();
                 reader.close();
        	 }catch (Exception e) {
     			e.printStackTrace();
    			throw new Exception();
    		}
        	 
		 }
		
    }
	
	public static void writeTextToPDF()  throws Exception{
		 String SRC = ".//generatedPDF//create.pdf";
		 String value = "Ref: AFPL/HR/BBSR/2021- 34567\r\n" + 
		 		"Ankita Naik\r\n" + 
		 		"Assistant Vice President -HR";
		 Document document = new Document();
		 try {
			 PdfWriter.getInstance(document, new FileOutputStream(new File(SRC)));
			 document.open();
			 Paragraph p = new Paragraph();
	         p.add("This is my paragraph 1");
	         p.setAlignment(Element.ALIGN_CENTER);
			 document.add(p);
			 Paragraph p2 = new Paragraph();
			 p2.add("This is my paragraph 2"); //no alignment
	         document.add(p2);
	         Font f = new Font();
	         f.setStyle(Font.BOLD);
	         f.setSize(8);
	         document.add(new Paragraph(value));
	         document.close();
	         System.out.println("Done");
		 }catch(Exception e) {
	     }
	}
		 
	
}
