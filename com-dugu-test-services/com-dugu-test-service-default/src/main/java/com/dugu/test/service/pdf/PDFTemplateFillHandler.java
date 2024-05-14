package com.dugu.test.service.pdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfWidgetAnnotation;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author cihun
 * @date 2024/5/13 22:26
 */

@Slf4j
public class PDFTemplateFillHandler {
    /**
     * 待填充的PDF模板文档
     */
//    private final transient PdfDocument templateDocument;
//
//    /**
//     * 填充后PDF文档数据
//     */
//    private final transient ByteArrayOutputStream destByteOutStream;
//
//    /**
//     * 字体
//     */
//    private transient PdfFont font;
//
//    private PDFTemplateFillHandler(PdfDocument templateDocument, ByteArrayOutputStream destByteOutStream) {
//        this.templateDocument = templateDocument;
//        this.destByteOutStream = destByteOutStream;
//        try {
//            this.font = loadFont();
//        } catch (IOException e) {
//            log.warn("加载指定字体异常：{}", e.getMessage());
//        }
//    }
//
//    public static PDFTemplateFillHandler loadTemplate(InputStream templateFileStream) {
//        try {
//            ByteArrayOutputStream dest = new ByteArrayOutputStream();
//            PdfDocument pdfDocument = new PdfDocument(new PdfReader(templateFileStream), new PdfWriter(dest));
//            return new PDFTemplateFillHandler(pdfDocument, dest);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 填充及返回填充后的数据
//     *
//     * @param fillData - 待填充数据
//     * @return - 填充后bytes数据
//     */
//    public byte[] fill(Map<String, Object> fillData) {
//        try {
//            PdfAcroForm form = PdfAcroForm.getAcroForm(templateDocument, true);
//            for (Map.Entry<String, Object> entry : fillData.entrySet()) {
//                String keyword = entry.getKey();
//                Object value = entry.getValue();
//                Optional.ofNullable(form.getField(keyword)).ifPresent(templateFormField -> {
//                    if (value instanceof byte[]) {
//                        PdfArray pos = templateFormField.getWidgets().get(0).getRectangle();
//                        float x = pos.getAsNumber(0).floatValue();
//                        float y = pos.getAsNumber(1).floatValue();
//                        float width = pos.getAsNumber(2).floatValue() - x;
//                        float height = pos.getAsNumber(3).floatValue() - y;
//                        Rectangle rectangle = new Rectangle(x, y, width, height);
//                        PdfWidgetAnnotation widget = new PdfWidgetAnnotation(rectangle);
//                        PdfFormField formField = PdfFormCreator.createFormField(widget, templateDocument);
//                        PdfPage annotationPage = findAnnotationPage(keyword);
//                        if (annotationPage != null) {
//                            doFillFieldImage(annotationPage, formField, (byte[]) value);
//                        }
//                    } else {
//                        templateFormField.setValue(String.valueOf(value));
//                        if (font != null) {
//                            templateFormField.setFont(templateFormField.getFont());
//                        }
//                    }
//                    form.partialFormFlattening(keyword);
//                });
//            }
//            form.flattenFields();
//        } finally {
//            if (templateDocument != null) {
//                templateDocument.close();
//            }
//        }
//        return destByteOutStream.toByteArray();
//    }
//
//    /**
//     * 图片填充
//     *
//     * @param newPage   - 当前页
//     * @param formField - 表单文本域
//     * @param imgBytes  - 图片文件字节数组
//     */
////    private void doFillFieldImage(PdfPage newPage, PdfFormField formField, byte[] imgBytes) {
////        Rectangle rtl = formField.getWidgets().get(0).getRectangle().toRectangle(); // 获取表单域的xy坐标
////        PdfCanvas canvas = new PdfCanvas(newPage);
////        ImageData img = ImageDataFactory.create(imgBytes);
////        if (Float.compare(img.getWidth(), rtl.getWidth()) <= 0 && Float.compare(img.getHeight(), rtl.getHeight()) <= 0) {// 不处理
////            canvas.addImageAt(img, rtl.getX(), rtl.getY(), true);
////        } else {
////            // 压缩图片。计算得到图片放缩的最大比例
////            float scale = Math.max(img.getWidth() / rtl.getWidth(), img.getHeight() / rtl.getHeight());
////            int imgWidth = Math.round(img.getWidth() / scale);
////            int imgHeight = Math.round(img.getHeight() / scale);
////            // 压缩图片
////            byte[] compressImgBytes;
////            try {
////                compressImgBytes = ImageCompressUtils.resizeByThumbnails(imgBytes, imgWidth, imgHeight);
////            } catch (IOException e) {
////                throw new RuntimeException(e);
////            }
////            img = ImageDataFactory.create(compressImgBytes);
////            canvas.addImageAt(img, rtl.getX(), rtl.getY(), true);
////        }
////    }
//
//    /**
//     * 根据表单域关键字查找当前关键字所在页对象（PdfPage）
//     *
//     * @param keyword - 关键字
//     * @return - page object
//     */
//    private PdfPage findAnnotationPage(String keyword) {
//        int pages = templateDocument.getNumberOfPages();
//        for (int index = 1; index <= pages; index++) {
//            PdfPage page = templateDocument.getPage(index);
//            for (PdfAnnotation annotation : page.getAnnotations()) {
//                PdfString title = annotation.getPdfObject().getAsString(PdfName.T);
//                if (title != null && keyword.equals(String.valueOf(title))) {
//                    return page;
//                }
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 获取模板文件表单域关键字位置信息
//     */
//    private Map<Integer, Map<String, float[]>> getFormKeywordsPos() {
//        int pages = templateDocument.getNumberOfPages();
//        Map<Integer, Map<String, float[]>> maps = new HashMap<>(pages);
//        for (int index = 1; index <= pages; index++) {
//            maps.putIfAbsent(index, new HashMap<>());
//            PdfPage page = templateDocument.getPage(index);
//            // 获取当前页的表单域
//            int finalIndex = index;
//            page.getAnnotations().forEach(anno -> {
//                PdfString title = anno.getTitle();
//                PdfArray rectangle = anno.getRectangle();
//                float x = rectangle.getAsNumber(0).floatValue();
//                float y = rectangle.getAsNumber(1).floatValue();
//                float width = rectangle.getAsNumber(2).floatValue() - x;
//                float height = rectangle.getAsNumber(3).floatValue() - y;
//                maps.get(finalIndex).put(title.getValue(), new float[]{x, y, width, height});
//            });
//        }
//        return maps;
//    }
//
//    /**
//     * 加载字体
//     */
//    private PdfFont loadFont() throws IOException {
//        Resource[] resources = new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:/font/*.ttc");
//        if (resources.length == 0) {
//            return null;
//        }
//        PdfFontFactory.register(resources[0].getURL().getPath(), "SimSun");
//        return PdfFontFactory.createRegisteredFont("SimSun", PdfEncodings.IDENTITY_H,
//                PdfFontFactory.EmbeddingStrategy.PREFER_NOT_EMBEDDED);
//    }

}
