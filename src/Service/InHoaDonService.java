package Service;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.io.IOException;

public class InHoaDonService {
    private static PdfFont cambriaFont;
    private String path;
    private String cambriaPath = "./src/resources/fonts/Cambria_0.ttf";
    private Color horizontalLineColor;
    private String ngayLap;

    public InHoaDonService(String NgayLap){
        this.ngayLap = NgayLap;
    }
    public void ExportPDF() throws IOException {

        path = "./reports/invoice3.pdf";

        cambriaFont = PdfFontFactory.createFont(cambriaPath, PdfEncodings.IDENTITY_H, true);
        PdfWriter pdfWriter = null;
        pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdfDocument);

        float twocol = 285f;
        float twocol150 = twocol + 150f;
        float twocolumnWidth[] = {twocol150, twocol};
        float fullWidth[] = {570f};


        //header
        Table tblHeader = new Table(twocolumnWidth);
        Cell leftCellHeader = new Cell().add(new Paragraph("HÓA ĐƠN BÁN HÀNG"));
        leftCellHeader.setBorder(Border.NO_BORDER);
        leftCellHeader.addStyle(new Style()
                .setFont(cambriaFont)
                .setBold()
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER));
        tblHeader.addCell(leftCellHeader);

        Table tblnestedRightTableHeader = new Table(new float[]{twocol / 2, twocol / 2});

        tblnestedRightTableHeader.addCell(getHeaderTextCell("Mã hóa đơn"));
        tblnestedRightTableHeader.addCell(getHeaderTextCellValue("HD001"));
        tblnestedRightTableHeader.addCell(getHeaderTextCell("Ngày lập"));
        tblnestedRightTableHeader.addCell(getHeaderTextCellValue(ngayLap));
        tblHeader.addCell(new Cell().add(tblnestedRightTableHeader).setBorder(Border.NO_BORDER));

        //Đường kẻ chia header và content

        horizontalLineColor = new DeviceRgb(105,105,105);
        Border bdrhorizontalLine = new SolidBorder(horizontalLineColor,1f/2f);
        Table dividerHorizontalLine = new Table(fullWidth);
        dividerHorizontalLine.setBorder(bdrhorizontalLine).addStyle(new Style().setMarginTop(20));

        //Content


        document.add(tblHeader);
        document.add(dividerHorizontalLine);
        document.close();


    }

    static Cell getHeaderTextCell(String textValue) {
        return new Cell().addStyle(new Style()
                .setFontSize(13f)
                .setBold()
                .setBorder(Border.NO_BORDER)
                .setFont(cambriaFont))
                .add(new Paragraph(textValue));
    }

    static Cell getHeaderTextCellValue(String textValue) {
        return new Cell().addStyle(new Style()
                .setFont(cambriaFont)
                .setFontSize(13f)
                .setBorder(Border.NO_BORDER))
                .add(new Paragraph(textValue));
    }
}
