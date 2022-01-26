package pdfPasswordEncription;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import com.codoid.products.exception.FilloException;

import Utility.Util;

public class PDFPasswordEncryption {

	public static void main(String[] args) throws Exception {
		encryptPDF();
	}
	
	//to encrypt pdf
	public static void encryptPDF() throws Exception {
		Util util = new Util();
		ArrayList<String> totalNumber = new ArrayList<String>();
		try {
			totalNumber = util.getTestData_SeqNos("passwordEncryption");
		} catch (FilloException e) {
			e.printStackTrace();
			throw new Exception();
		}
		 for(String seq:totalNumber) {
        	 try {
        		 Hashtable<String,String> data = new Hashtable<String,String>();
            	 data = util.get_Data( "passwordEncryption", seq);
            	 System.out.println(data.get("PDFName"));
            	 String pdfName = data.get("PDFName");
            	 String password = data.get("Password");
            	// step 1. Loading the pdf file
                 File f = new File(".\\PDFPasswordEncryption\\unprotectedFiles\\"+pdfName+".pdf");
                 PDDocument pdd = PDDocument.load(f);
                 // step 2.Creating instance of AccessPermission
                 // class
                 AccessPermission ap = new AccessPermission();
                 // step 3. Creating instance of
                 // StandardProtectionPolicy
                 StandardProtectionPolicy stpp
                     = new StandardProtectionPolicy(password, password, ap);
                 // step 4. Setting the length of Encryption key
                 stpp.setEncryptionKeyLength(128);
                 // step 5. Setting the permission
                 stpp.setPermissions(ap);
                 // step 6. Protecting the PDF file
                 pdd.protect(stpp);
                 // step 7. Saving and closing the the PDF Document
                 pdd.save(".\\PDFPasswordEncryption\\encryptedFiles\\"+pdfName+".pdf");
                 pdd.close();
                 System.out.println("PDF Encrypted successfully...");
            }catch (Exception e) {
     			e.printStackTrace();
    			throw new Exception();
    		}
        	 
		 }
	}

}
