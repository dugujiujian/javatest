package com.dugu.test.service.pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.extgstate.PdfExtGState;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

/**
 * @author cihun
 * @date 2024/5/13 21:43
 */
public class WaterMarkImage implements IEventHandler {
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
        PdfDocument document = documentEvent.getDocument();
        PdfPage page = documentEvent.getPage();
        Rectangle pageSize = page.getPageSize();

        ImageData imageData = null;
        try {
            imageData = ImageDataFactory.create(ResourceUtils.getFile("classpath:pdf/WaterMark.png").getPath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 两个for循环 是多个水印 去掉之后就只有一个水印
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Image image = new Image(imageData);

                // 水印位置
                image.setFixedPosition((50.5f + i * 150f), (40.0f + j * 250f));
                // 水印倾斜度
                image.setRotationAngle(170.5f);

                //  setFillOpacity 透明度
                PdfExtGState gs = new PdfExtGState();
                gs.setFillOpacity(0.1f);
                PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), document);
                canvas.setExtGState(gs);

                new Canvas(canvas, pageSize, true)
                        .add(image);
            }
        }
    }
}