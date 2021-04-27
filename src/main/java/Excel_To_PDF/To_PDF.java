package Excel_To_PDF;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;

public class To_PDF {

	
	public static void main(String[] args) throws Exception {
		
		
		Document pdfDocument = new Document();
        Reader htmlreader = new BufferedReader(new InputStreamReader(new FileInputStream("C:/shib/PHP SQL UNIX INTERVIEW HELPER.html")) );
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(pdfDocument, baos);
        pdfDocument.open();
        StyleSheet styles = new StyleSheet();
        styles.loadTagStyle("body", "font", "Bitstream Vera Sans");
        List<Element> arrayElementList = HTMLWorker.parseToList(htmlreader, styles);
        for (int i = 0; i < arrayElementList.size(); ++i) {
            Element e = (Element) arrayElementList.get(i);
            pdfDocument.add(e);
        }
        pdfDocument.close();
        byte[] bs = baos.toByteArray();
        String pdfBase64 = Base64.encodeBytes(bs); //output
        File pdfFile = new File("C:/shib/PHP SQL UNIX INTERVIEW.pdf");
        FileOutputStream out = new FileOutputStream(pdfFile);
        out.write(bs);
        out.close();
	}
}
