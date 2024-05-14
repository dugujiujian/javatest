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
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.io.IOException;

/**
 * @author cihun
 * @date 2024/5/13 20:48
 */


public class PdfWaterMarker implements IEventHandler {

    private PdfFont font;
    private String waterContent;

    public PdfWaterMarker(String waterContent) {
        this.waterContent = waterContent;
        try {
            this.font = createDefaultFont();
        } catch (IOException e) {
            //LOGGER.error("PDF Header设置中文字体失败", e);
        }
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        Rectangle pageSize = page.getPageSize();

        PdfCanvas pdfCanvas = new PdfCanvas(page.getLastContentStream(), page.getResources(), pdfDoc);
        Canvas canvas = new Canvas(pdfCanvas, pageSize);

        //水印旋转的角度
        float angle = (float) Math.toRadians(35);

        // 设置水印文字内容
        Paragraph waterMarker = new Paragraph(waterContent)
                .setFont(font)
                .setOpacity(0.15f)// 设置透明度
                .setFontSize(16);// 文字大小

            /*for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    canvas.showTextAligned(waterMarker, (150 + i * 300), (160 + j * 150), pdfDoc.getNumberOfPages(),
                            TextAlignment.CENTER, VerticalAlignment.MIDDLE, 0.3f);
                }
            }*/

        // 计算水印的起始位置和间隔
        float xStart = 1;
        float yStart = 1;
        float stepX = 150;
        float stepY = 150;

        // 在页面上循环绘制水印文字
        for (float x = xStart; x < pageSize.getWidth(); x += stepX) {
            for (float y = yStart; y < pageSize.getHeight(); y += stepY) {
                // 绘制水印文字
                canvas.showTextAligned(waterMarker, x, y, pdfDoc.getNumberOfPages(),
                        TextAlignment.LEFT, VerticalAlignment.MIDDLE, 0.6f);
            }
        }
        // 关闭流
        canvas.close();
    }

    private static PdfFont createDefaultFont() throws IOException {
        return PdfFontFactory.createFont();
    }


}