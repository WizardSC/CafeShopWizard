package Service;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.io.IOException;

public class InHoaDonService {
    private static PdfFont cambriaFont;
    private String path;
    private String cambriaPath = "./src/resources/fonts/Cambria_0.ttf";
    public void ExportPDF() {
        path = "./reports/invoice2.pdf";
        try {
            cambriaFont = PdfFontFactory.createFont(cambriaPath, PdfEncodings.IDENTITY_H,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = new PdfWriter(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        Paragraph paragraph1 = new Paragraph("HÓA ĐƠN BÁN HÀNG ");
        paragraph1.addStyle(new Style().setFont(cambriaFont));
        document.add(paragraph1);
        document.close();

    }
}
