package com.dugu.test.service.pdf;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.IOException;


/**
 * @author cihun
 * @date 2024/5/13 20:52
 */
public class Footer implements IEventHandler {

    // 写总页码的占位符
    protected PdfFormXObject placeholder;

    // 页脚占位符大小
    private float side = 36;

    // 页脚占位符位置向右调整移动1，向下调整移动3
    private float space = 1;
    private float descent = 3;

    private PdfFont font;

    public Footer() {
        this.placeholder = new PdfFormXObject(new Rectangle(0, 0, side, side));
        try {
            this.font = createDefaultFont();
        } catch (IOException e) {
            //LOGGER.error("PDF Footer设置中文字体失败", e);
        }
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdf = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        int pageNumber = pdf.getPageNumber(page);
        Rectangle pageSize = page.getPageSize();

       // LOGGER.debug(String.format("当前处理第[%d]页", pageNumber));

        Document doc = new Document(pdf);
        float leftMargin = doc.getLeftMargin();
        float rightMargin = doc.getRightMargin();
        float bottomMargin = doc.getBottomMargin();
        float height = page.getPageSize().getHeight();
        float width = page.getPageSize().getWidth();

        // 页脚的位置
        float x = width / 2;
        float y = bottomMargin / 2;

        // Creates drawing canvas
        PdfCanvas pdfCanvas = new PdfCanvas(page);
        Canvas canvas = new Canvas(pdfCanvas, pageSize);

        if (font != null) {
            // 设置支持中文
            canvas.setFont(this.font);
        }
        canvas.setFontSize(8);

        // 设置支持横线
        // canvas.setStrokeWidth(0.1f);
        pdfCanvas.setLineWidth(0.1f);
        pdfCanvas.moveTo(leftMargin, bottomMargin - 3).lineTo(width - rightMargin, bottomMargin - 3).stroke();

        // 设置支持页码
        Paragraph p = new Paragraph().add(String.format("第[%d]页", pageNumber));
        canvas.showTextAligned(p, x, y, TextAlignment.RIGHT);
        canvas.close();

        // 添加占位符，用于写入总页码
        pdfCanvas.addXObjectAt(placeholder, x + space, y - descent);
        pdfCanvas.release();

    }

    private static PdfFont createDefaultFont() throws java.io.IOException {
        return PdfFontFactory.createFont("STSong-Light", "UniGB-UCS2-H");
    }
}
