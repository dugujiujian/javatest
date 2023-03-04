package com.dugu.test.service.java8.file.excel.complex;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.dugu.test.service.java8.file.excel.complex.model.DocDetailExcelModel;
import com.dugu.test.service.java8.file.excel.complex.model.ExcelHeaderModel;
import com.dugu.test.service.java8.file.excel.complex.model.UserScoreHeadModel;
import com.dugu.test.service.java8.file.excel.complex.strategy.BudgetDeclareSheetWriteHandler;
import com.dugu.test.service.java8.file.excel.complex.strategy.CustomMergeStrategy;
import com.dugu.test.service.performance.domain.UserSimpleDTO;
import junit.framework.TestCase;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cihun
 * @date 2023-02-19 1:07 下午
 */
public class ComplexWriteExcelTest extends TestCase {


    private static List<List<String>> head() {
        List<List<String>> list = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add("维度");
        list.add(head0);
        List<String> head1 = new ArrayList<>();
        head1.add("目标");
        list.add(head1);
        List<String> head2 = new ArrayList<>();
        head2.add("权重");
        list.add(head2);
        Map<String, List<String>> map = getHeader();
        map.forEach((k, v) -> {
            String deviceCategory = k;
            List<String> ls = v;
            ls.forEach(e -> {
                List<String> head = new ArrayList<>();
                head.add(deviceCategory);
                head.add(e);
                list.add(head);
            });

        });

        return list;
    }

    /**
     * 下载模板的自定义表头第二行
     *
     * @return
     */
    private static Map<String, List<String>> getHeader() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> aList = new ArrayList<>();
        List<String> sList = new ArrayList<>();
        List<String> subList = new ArrayList<>();
        aList.add("刺魂");
        sList.add("刺魂(10%)");
        sList.add("99(90%)");
        map.put("自评（10%）", aList);
        map.put("上级评分(90%)", sList);
        return map;
    }

    @Test
    public void test() {
        List<List<String>> header = head();
        String fileName = "//Users/zhaohaihua/Documents/complex.xlsx";
        EasyExcel.write(fileName)
                .head(header)
                .sheet("文档").doWrite(Collections.EMPTY_LIST);
    }

    /**
     * 思路:
     * 动态表格构建思路，表头可以认为是一个二维数组，按下面的列的数字去构建内存的数组，内层的数组元素的个数就是表头列的合并单元格后的行数
     * <pre>
     *     List<List<Object>> 二维数组
     *     List<Object> 内层数组
     * </pre>
     *
     * @throws FileNotFoundException
     */
    @Test
    public void test2() throws FileNotFoundException {
        String fileName = "//Users/zhaohaihua/Documents/complex.xlsx";


        //创建表格对象
        WriteTable table = new WriteTable();
        //设置第N个表格
        table.setTableNo(1);
        //创建表头集合
        List<List<String>> headList = Lists.newArrayList();

        //===============自评==================
        String fixHeaderStr = "维度,目标,描述,标准,完成说明,完成度,完成值,单位,权重";
        List<ExcelHeaderModel> modelList = Arrays.stream(fixHeaderStr.split(",")).map(e -> {
            ExcelHeaderModel excelHeaderModel = new ExcelHeaderModel();
            excelHeaderModel.setLabel(e);
            excelHeaderModel.setFieldName(ExcelFieldUtil.getNameFieldByKey(e));
            return excelHeaderModel;
        }).collect(Collectors.toList());
        fixHeader(headList, modelList);
        //===============自评==================
        boolean employeeHidden = false;
        employeeScoreHeader(headList, "10%", employeeHidden, false, false);
        //===============同事互评==================
        List<UserScoreHeadModel> inviteUserList = Lists.newArrayList();
        UserScoreHeadModel userScoreHeadModel = new UserScoreHeadModel();
        userScoreHeadModel.setLabel("刺魂");
        userScoreHeadModel.setWeight("10%");
        inviteUserList.add(userScoreHeadModel);
        userScoreHeadModel = new UserScoreHeadModel();
        userScoreHeadModel.setLabel("99");
        userScoreHeadModel.setWeight("20%");
        inviteUserList.add(userScoreHeadModel);
        userScoreHeadModel = new UserScoreHeadModel();
        userScoreHeadModel.setLabel("蛇哥");
        userScoreHeadModel.setWeight("70%");
        inviteUserList.add(userScoreHeadModel);
        inviteScoreHeader(headList, inviteUserList, "20%", true, true);
        //===============上级评分==================
        List<UserScoreHeadModel> leaderScoreList = Lists.newArrayList();
        UserScoreHeadModel leaderModel = new UserScoreHeadModel();
        leaderModel.setLabel("刺魂");
        leaderModel.setWeight("30%");
        leaderScoreList.add(leaderModel);
        leaderModel = new UserScoreHeadModel();
        leaderModel.setLabel("99");
        leaderModel.setWeight("30%");
        leaderScoreList.add(leaderModel);
        leaderModel = new UserScoreHeadModel();
        leaderModel.setLabel("蛇哥");
        leaderModel.setWeight("40%");
        leaderScoreList.add(leaderModel);
        leaderScoreHeader(headList, leaderScoreList, "20%", true, true);

        headList.add(Arrays.asList());


        table.setHead(headList);
        table.setRelativeHeadRowIndex(2);


        UserSimpleDTO leader = new UserSimpleDTO();
        leader.setLabel("003");
        UserSimpleDTO user = new UserSimpleDTO();
        user.setLabel("刺魂");
        DocDetailExcelModel docDetailExcelModel = new DocDetailExcelModel();
        docDetailExcelModel.setPlanName("2023年Q1等级制只有价值观");
        docDetailExcelModel.setPosition("开发工程师");
        docDetailExcelModel.setDepartmentPath("技术中心/技术部");
        docDetailExcelModel.setCellCount(headList.size());
        docDetailExcelModel.setPlanCycle("2023第二季度");
        docDetailExcelModel.setLeader(leader);
        docDetailExcelModel.setUser(user);

        docDetailExcelModel.setTotalScore("87.5");
        docDetailExcelModel.setTotalValueScore("A");
        docDetailExcelModel.setGrade("优秀");
        //创建ExcelWriter写入对象k
        ExcelWriter excelWriter = EasyExcel.write(new FileOutputStream(fileName)).build();
        WriteSheet sheet = EasyExcel.writerSheet(docDetailExcelModel.getPlanName())
                .sheetNo(1)
                .registerWriteHandler(new CustomMergeStrategy(mergeLine(), 0))
                .registerWriteHandler(new BudgetDeclareSheetWriteHandler(docDetailExcelModel))
                .relativeHeadRowIndex(2)
                .build();
        sheet.setHead(headList);
        excelWriter.write(getData(), sheet);
        //  释放资源
        excelWriter.finish();
    }

    private void fixHeader(List<List<String>> headerList, List<ExcelHeaderModel> modelList) {
        modelList.forEach(h -> {
            List<String> header = Lists.newArrayList();
            header.add(h.getLabel());
            headerList.add(header);
        });
    }

    private void employeeScoreHeader(List<List<String>> headerList, String weight, boolean hiddenEmployeeScore, boolean showScore, boolean showComment) {

        if (hiddenEmployeeScore && showScore) {
            List<String> scoreHeader = Lists.newArrayList();
            scoreHeader.add("自评（" + weight + "）");
            scoreHeader.add("评分");
            headerList.add(scoreHeader);
        }
        if (hiddenEmployeeScore && showComment) {
            List<String> commentHeader = Lists.newArrayList();
            commentHeader.add("自评（" + weight + "）");
            commentHeader.add("评语");
            headerList.add(commentHeader);
        }

    }

    private void inviteScoreHeader(List<List<String>> headerList, List<UserScoreHeadModel> userScoreHeadModelList,
                                   String weight, boolean showScore, boolean showComment) {
        userScoreHeadModelList.forEach(e -> {
            List<String> scoreHeader = Lists.newArrayList();
            scoreHeader.add("同事互评（" + weight + "）");
            scoreHeader.add(e.getLabel() + "（" + e.getWeight() + ")");
            scoreHeader.add("评分");
            List<String> commentHeader = Lists.newArrayList();
            commentHeader.add("同事互评（" + weight + "）");
            commentHeader.add(e.getLabel() + "（" + e.getWeight() + ")");
            commentHeader.add("评语");
            headerList.add(scoreHeader);
            headerList.add(commentHeader);
        });
    }

    private void leaderScoreHeader(List<List<String>> headerList, List<UserScoreHeadModel> userScoreHeadModelList,
                                   String weight, boolean showScore, boolean showComment) {
        userScoreHeadModelList.forEach(e -> {
            List<String> scoreHeader = Lists.newArrayList();
            scoreHeader.add("上级评分（" + weight + "）");
            scoreHeader.add(e.getLabel() + "（" + e.getWeight() + ")");
            scoreHeader.add("评分");
            List<String> commentHeader = Lists.newArrayList();
            commentHeader.add("上级评分（" + weight + "）");
            commentHeader.add(e.getLabel() + "（" + e.getWeight() + ")");
            commentHeader.add("评语");
            headerList.add(scoreHeader);
            headerList.add(commentHeader);
        });
    }

    private List<String> mergeLine() {
        return getData().stream().map(s -> s.get(0)).collect(Collectors.toList());
    }


    private List<List<String>> getData() {
        List<List<String>> list = new ArrayList<>();
        List<String> line1 = Lists.newArrayList();
        line1.add("普通业务");
        line1.add("测试目标");
        line1.add("20");
        line1.add("测试目标描述");
        line1.add("测试目标标准");
        line1.add("测试目标完成度");
        line1.add("测试目标完成值");
        line1.add("次");
        line1.add("测试目标完成说明");
        list.add(line1);
        List<String> line2 = Lists.newArrayList();
        line2.add("普通业务");
        line2.add("测试目标2");
        line2.add("30");
        line2.add("测试目标描述2");
        line2.add("测试目标标准2");
        line2.add("测试目标完成度2");
        line2.add("测试目标完成值2");
        line2.add("次");
        line2.add("测试目标完成说明2");
        list.add(line2);
        return list;
    }

    public void testbb() {

//        public void exportSpUser(SpUserExportRequest request, HttpServletResponse response) {
//            List<SpUserExportDto> spUserExportDtos = spUserService.selectSpUseExportByIds(request);
//
//            // 采用动态 表头设计
//            List<List<String>> heads = new ArrayList<>(3);
//            // 1、常用信息
//            List<String> names = Arrays.stream(SpUserExportDto.class.getDeclaredFields())
//                    .filter(field -> field.isAnnotationPresent(ExcelProperty.class))
//                    .map(field -> {
//                        ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
//                        return annotation.value()[annotation.value().length - 1];
//                    })
//                    .collect(Collectors.toList());
//            for (String name : names) {
//                heads.add(Arrays.asList("基本信息",name));
//            }
//            // 2、指标信息
//            Map<String, String> userParamsValue2Name = getUserParamsValue2Name();
//            for (String name : userParamsValue2Name.values()) {
//                heads.add(Arrays.asList("指标信息",name));
//            }
//
//            // 数据需要重新计算
//            List<List<String>> dataList = new ArrayList<>(spUserExportDtos.size());
//            for (SpUserExportDto spUserExportDto : spUserExportDtos) {
//                Field[] declaredFields = spUserExportDto.getClass().getDeclaredFields();
//                List<String> datas = new ArrayList<>();
//                for (Field declaredField : declaredFields) {
//                    // userParams 特殊处理
//                    if (!declaredField.isAnnotationPresent(ExcelProperty.class) && !declaredField.getName().equals("userParams")){
//                        continue;
//                    }
//                    boolean accessible = declaredField.isAccessible();
//                    declaredField.setAccessible(true);
//                    try {
//
//                        // 数据 需要头对应上，所有空的时候赋值 空字符串
//                        Object o = declaredField.get(spUserExportDto);
//                        if (o == null) {
//                            datas.add("");
//                            continue;
//                        }
//                        if (Map.class.isAssignableFrom(o.getClass())) {
//                            Map<String, String> map = (Map<String, String>) o;
//                            for (String paramsName : userParamsValue2Name.keySet()) {
//                                datas.add(map.getOrDefault(paramsName, ""));
//                            }
//                            continue;
//                        }
//                        datas.add(String.valueOf(o));
//                    } catch (IllegalAccessException e) {
//                        throw new ServiceException("志愿者信息导出失败", e);
//                    }
//                    declaredField.setAccessible(accessible);
//                }
//                dataList.add(datas);
//            }
//
//
//            ExcelUtil<SpUserExportDto> excelUtil = new ExcelUtil<>(SpUserExportDto.class);
//            try {
//                ExcelWriterBuilder write = EasyExcelFactory.write(response.getOutputStream());
//                write.registerWriteHandler(new SpUserInfoSheetWriteHandler());
//                write.registerWriteHandler(new LongestMatchColumnWidthStyleStrategy());
//                write.head(heads);
//
//
//                excelUtil.exportEasyExcelFromBuilder(response,write, dataList, "志愿者信息");
//            } catch (IOException e) {
//                throw new ServiceException("志愿者信息导出失败", e);
//            }
//
//        }

    }
}