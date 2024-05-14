package com.dugu.test.service.pdf;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.BackgroundImage;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author cihun
 * @date 2024/5/13 20:21
 */
public class PdfCreateTest {


    @Test
    public void createPdf() throws IOException {
        String dest = "/Users/cihun/duguddd/helloworld.pdf";
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        //Add paragraph to the document
        document.add(new Paragraph("Hello World!"));

        //Close document
        document.close();
    }

    @Test
    public void createPdfList() throws IOException {
        String dest = "/Users/cihun/duguddd/list.pdf";
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
// Create a PdfFont
        PdfFont font = PdfFontFactory.createFont();
// Add a Paragraph
        document.add(new Paragraph("iText is:").setFont(font));
// Create a List
        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(font);
// Add ListItem objects
        list.add(new ListItem("Never gonna give you up"))
                .add(new ListItem("Never gonna let you down"))
                .add(new ListItem("Never gonna run around and desert you"))
                .add(new ListItem("Never gonna make you cry"))
                .add(new ListItem("Never gonna say goodbye"))
                .add(new ListItem("Never gonna tell a lie and hurt you"));
// Add the list
        document.add(list);
        document.close();
    }

    @Test
    public void createPdfTable() throws IOException {
        String dest = "/Users/cihun/duguddd/table.pdf";
        PdfCreate pdfCreate = new PdfCreate();
        java.util.List<String> headerList = new ArrayList<>();
        headerList.add("head1");
        headerList.add("head2");
        headerList.add("head3");
        headerList.add("head4");
        headerList.add("head5");
        headerList.add("head6");
        headerList.add("head7");
        headerList.add("head8");
        headerList.add("head9");
        java.util.List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        dataList.add(map1);
        map1.put("data11", "data11");
        map1.put("data12", "data12");
        map1.put("data13", "data13");
        map1.put("data14", "data14");
        map1.put("data15", "data15");
        map1.put("data16", "data16");
        map1.put("data17", "data17");
        map1.put("data18", "data18");
        map1.put("data19", "data19");
        pdfCreate.createPdf(headerList, dataList, dest);
    }
    @Test
    public void createPdfTable2() throws IOException {
        String dest = "/Users/cihun/duguddd/table2.pdf";
        // 创建一个要生成的PDF文件对象File
        File file = new File(dest);
        // 创建PDF输出流
        PdfWriter pdfWriter = new PdfWriter(file);
        // 创建文档对象
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        // 创建字体
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        // 创建背景图片
//        ImageData imageData = ImageDataFactory.create(IMG_PATH);
//        PdfImageXObject pdfImageXObject = new PdfImageXObject(imageData);
//        BackgroundImage.Builder backgroundImageBuilder = new BackgroundImage.Builder();
//        backgroundImageBuilder.setImage(pdfImageXObject);
//        BackgroundImage backgroundImage = backgroundImageBuilder.build();
        // 创建table
        Table table = new Table(UnitValue.createPercentArray(8)).useAllAvailableWidth();
        for (int i = 0; i < 16; i++) {
            Cell cell = new Cell();
            // 设置段落，设置段落的字体和字体颜色
            Paragraph paragraph = new Paragraph("hi" + i).setFont(font).setFontColor(ColorConstants.WHITE);
            cell.add(paragraph);
            // 设置背景颜色
            // cell.setBackgroundColor(ColorConstants.RED);
            // 设置边框样式
            SolidBorder solidBorder = new SolidBorder(ColorConstants.BLACK, 1);
            cell.setBorder(solidBorder);
            // 设置文本对齐方式
            cell.setTextAlignment(TextAlignment.CENTER);
            table.addCell(cell);
        }
        // 设置表格背景图片
        //table.setBackgroundImage(backgroundImage);
        // 添加表格
        document.add(table);
        document.close();
    }

}