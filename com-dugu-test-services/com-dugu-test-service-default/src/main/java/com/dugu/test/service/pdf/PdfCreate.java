package com.dugu.test.service.pdf;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author cihun
 * @date 2024/5/13 20:35
 */
public class PdfCreate {

    public void createPdf(List<String> headerList, List<Map<String, Object>> dataList, String dest) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);


        // 添加水印
        PdfWaterMarker pdfWaterMarker = new PdfWaterMarker("水印内容");
        pdf.addEventHandler(PdfDocumentEvent.INSERT_PAGE, pdfWaterMarker);

        // 添加表头

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1, 3, 4, 3, 3, 3, 3, 1}))
                .useAllAvailableWidth();
        header(table, bold, headerList);
        data(table, font, dataList);
        document.add(table);

        // flush触发写操作，此时才会触发已经注册的事件处理器
        document.flush();
        //Close document
        document.close();
    }

    public void header(Table table, PdfFont font, List<String> headerList) {
        if (CollectionUtils.isNotEmpty(headerList)) {
            for (String header : headerList) {
                table.addHeaderCell(new Cell().add(new Paragraph(header).setFont(font)));
            }
        }
    }

    public void data(Table table, PdfFont font, List<Map<String, Object>> dataList) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            for (Map<String, Object> data : dataList) {
                for (Map.Entry<String, Object> m : data.entrySet()) {
                    table.addCell(new Cell().add(new Paragraph((String) m.getValue()).setFont(font)));
                }
            }
        }
    }


}
