package com.dugu.test.service.pdf;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;

/**
 * @author cihun
 * @date 2024/5/13 21:31
 */
public class PdfUtil {

    /**
     * 创建字体对象
     * @return
     * @throws IOException
     */
    public static PdfFont createPdfFont() throws IOException {
        // 使用 PdfFontFactory 创建字体
        // 使用下面字体可以处理中文不显示的问题
        return PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H");
    }
    /**
     * 创建 Table 对象
     * @return
     */
    public static Table createTable() throws IOException {
        // 创建几列的表格对象
        Table table = new Table(4);
        // 设置table表格宽度
        table.setWidth(UnitValue.POINT).setWidth(520);
        for (int i = 0; i < 2; i++) {
            if (i == 0){
                // 第一行数据，创建 Cell 对象，默认一行一列
                Cell cell00 = new Cell();
                cell00.add(new Paragraph("姓名").setFont(createPdfFont()));
                table.addCell(cell00);
                table.addCell(new Cell().add(new Paragraph("李白").setFont(createPdfFont()).setFontColor(ColorConstants.BLACK)));
                table.addCell(new Cell().add(new Paragraph("性别").setFont(createPdfFont())).setFontSize(24));
                table.addCell(new Cell().add(new Paragraph("男").setFont(createPdfFont())));
            }else if (i == 1){
                // 第二行数据
                table.addCell(new Cell().add(new Paragraph("代表作").setFont(createPdfFont())));
                // 第二行数据，创建 Cell 对象，默认一行三列
                table.addCell(new Cell(1, 3).add(new Paragraph("《将进酒》《蜀道难》").setFont(createPdfFont())));
            }
        }
        return table;
    }
    /**
     * 创建段落
     * Paragraph 和 Text 关系，
     *  同一个设置如果 Text 存在，则以 Text 设置为显示方式
     *  如果 Text 没有设置，以 Paragraph 设置为显示方式
     *  对齐模式以 Paragraph 对齐模式设置为显示方式
     * @return
     * @throws IOException
     */
    public static Paragraph createParagraph() throws IOException {
        // 可以通过构造方法添加问题
        Paragraph paragraph = new Paragraph("段落内容");
        // 也可以通过添加 Text 对象添加文字
        paragraph.add(createText());
        // 段落设置字体
        paragraph.setFont(createPdfFont());
        // 段落加粗
        paragraph.setBold();
        // 段落设置字体大佬
        paragraph.setFontSize(24);
        // 段落设置颜色
        paragraph.setFontColor(ColorConstants.RED);
        // 段落设置下划
        paragraph.setUnderline();
        // 段落首行缩进
        paragraph.setFirstLineIndent(40);
        // 设置段落对齐模式，对齐模式以段落对齐模式设置而显示
        paragraph.setTextAlignment(TextAlignment.CENTER);
        return paragraph;
    }
    /**
     * 创建文本对象
     *
     * 注意要点：文本对象不能直接添加到document
     *
     * Paragraph 和 Text 关系，
     *  同一个设置如果 Text 存在，则以 Text 设置为显示方式
     *  如果 Text 没有设置，以 Paragraph 设置为显示方式
     * @return
     */
    public static Text createText() throws IOException {
        Text text = new Text("将进酒");
        // 字体
        text.setFont(createPdfFont());
        // 字体加粗
        text.setBold();
        // 字体颜色
        text.setFontColor(ColorConstants.BLACK);
        // 字体大小
        text.setFontSize(24);
        // 字体添加下划线
        text.setUnderline();
        // 字体设置文本对齐模式
        text.setTextAlignment(TextAlignment.LEFT);
        return text;
    }
    /**
     * 获取临时文件路径
     * @return
     */
    private static String getPdfFileName(){
        String userDir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        return userDir + separator + "learn.pdf";
    }
}
