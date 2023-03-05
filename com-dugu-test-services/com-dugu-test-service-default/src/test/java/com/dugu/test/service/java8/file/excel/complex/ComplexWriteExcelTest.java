package com.dugu.test.service.java8.file.excel.complex;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.dugu.test.service.java8.file.excel.complex.model.DocDetailDataModel;
import com.dugu.test.service.java8.file.excel.complex.model.DocDetailExcelModel;
import com.dugu.test.service.java8.file.excel.complex.model.ExcelHeaderModel;
import com.dugu.test.service.java8.file.excel.complex.model.UserScoreHeadModel;
import com.dugu.test.service.java8.file.excel.complex.strategy.BudgetDeclareSheetWriteHandler;
import com.dugu.test.service.java8.file.excel.complex.strategy.CustomHorizontalCellStyleStrategy;
import com.dugu.test.service.java8.file.excel.complex.strategy.CustomMergeStrategy;
import com.dugu.test.service.performance.domain.UserSimpleDTO;
import junit.framework.TestCase;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
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
    public void testDynamicHeader() throws FileNotFoundException {
        String fileName = "//Users/zhaohaihua/Documents/complex.xlsx";


        //创建表格对象
        WriteTable table = new WriteTable();
        //设置第N个表格
        table.setTableNo(1);

        //------
        UserSimpleDTO leader = new UserSimpleDTO();
        leader.setLabel("003");
        UserSimpleDTO user = new UserSimpleDTO();
        user.setLabel("刺魂");
        //
        List<ExcelHeaderModel> headModelList = Lists.newArrayList();
        // 计划维度数据
        DocDetailExcelModel docDetailExcelModel = buildDocDetailExcelModel(leader, user);
        // 文档数据
        List<DocDetailDataModel> docDetailDataModelList = buildDocDetailDataModelList();
        // 创建表头集合
        List<List<String>> headList = Lists.newArrayList();
        buildFixHead(config(), headList, headModelList);
        buildDynamicHead(config(), docDetailExcelModel, headModelList, docDetailDataModelList);
//
//        //===============自评==================
//        employeeScoreHeader(headList, "10%", Boolean.TRUE, Boolean.TRUE, false);
//        //===============同事互评==================
//        List<UserScoreHeadModel> inviteUserList = Lists.newArrayList();
//        UserScoreHeadModel userScoreHeadModel = new UserScoreHeadModel();
//        userScoreHeadModel.setLabel("刺魂");
//        userScoreHeadModel.setWeight("10%");
//        inviteUserList.add(userScoreHeadModel);
//        userScoreHeadModel = new UserScoreHeadModel();
//        userScoreHeadModel.setLabel("99");
//        userScoreHeadModel.setWeight("20%");
//        inviteUserList.add(userScoreHeadModel);
//        userScoreHeadModel = new UserScoreHeadModel();
//        userScoreHeadModel.setLabel("蛇哥");
//        userScoreHeadModel.setWeight("70%");
//        inviteUserList.add(userScoreHeadModel);
//        inviteScoreHeader(headList, inviteUserList, "20%", true, true);
//        //===============上级评分==================
//        List<UserScoreHeadModel> leaderScoreList = Lists.newArrayList();
//        UserScoreHeadModel leaderModel = new UserScoreHeadModel();
//        leaderModel.setLabel("刺魂");
//        leaderModel.setWeight("30%");
//        leaderScoreList.add(leaderModel);
//        leaderModel = new UserScoreHeadModel();
//        leaderModel.setLabel("99");
//        leaderModel.setWeight("30%");
//        leaderScoreList.add(leaderModel);
//        leaderModel = new UserScoreHeadModel();
//        leaderModel.setLabel("蛇哥");
//        leaderModel.setWeight("40%");
//        leaderScoreList.add(leaderModel);
//        leaderScoreHeader(headList, leaderScoreList, "20%", true, true);


        docDetailExcelModel.setCellCount(headList.size());

        table.setHead(headList);
        table.setRelativeHeadRowIndex(2);

        List<List<Object>> data = getData(docDetailDataModelList, headModelList);

        //创建ExcelWriter写入对象k
        ExcelWriter excelWriter = EasyExcel.write(new FileOutputStream(fileName)).build();
        WriteSheet sheet = EasyExcel.writerSheet(docDetailExcelModel.getPlanName())
                .sheetNo(1)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .registerWriteHandler(new CustomMergeStrategy(mergeLine(data), 0))
                .registerWriteHandler(new BudgetDeclareSheetWriteHandler(docDetailExcelModel))
                .registerWriteHandler(CustomHorizontalCellStyleStrategy.getHorizontalCellStyleStrategy())
                .relativeHeadRowIndex(2)
                .build();
        sheet.setHead(headList);
        excelWriter.write(data, sheet);
        //  释放资源
        excelWriter.finish();
    }

    private DocDetailExcelModel buildDocDetailExcelModel(UserSimpleDTO leader, UserSimpleDTO user) {
        DocDetailExcelModel docDetailExcelModel = new DocDetailExcelModel();
        docDetailExcelModel.setPlanName("2023年Q1等级制只有价值观");
        docDetailExcelModel.setPosition("开发工程师");
        docDetailExcelModel.setDepartmentPath("技术中心/技术部");
        docDetailExcelModel.setPlanCycle("2023第二季度");
        docDetailExcelModel.setLeader(leader);
        docDetailExcelModel.setUser(user);

        docDetailExcelModel.setTotalScore("87.5");
        docDetailExcelModel.setTotalValueScore("A");
        docDetailExcelModel.setGrade("优秀");
        docDetailExcelModel.setEmployeeWeight("10");
        docDetailExcelModel.setInviteWeight("20");
        docDetailExcelModel.setLeaderScoreWeight("70");
        return docDetailExcelModel;
    }

    private List<DocDetailDataModel> buildDocDetailDataModelList() {
        List<DocDetailDataModel> docDetailDataModelList = Lists.newArrayList();
        DocDetailDataModel docDetailDataModel1 = new DocDetailDataModel();
        docDetailDataModel1.setDimensionName("业务维度");
        docDetailDataModel1.setDimensionWeight("25");
        docDetailDataModel1.setUpperLimit("100");
        docDetailDataModel1.setObjectName("技术产品化讨论结果");
        docDetailDataModel1.setDesc("每月需要低技术方能提出一定的建议");
        docDetailDataModel1.setStandard("1.每月产出2片文章\r\n2.1次技术研讨会");
        docDetailDataModel1.setWeight("20");
        docDetailDataModel1.setTargetValue("2");
        docDetailDataModel1.setChargeValue("4");

        docDetailDataModelList.add(docDetailDataModel1);

        return docDetailDataModelList;
    }


    private void employeeScoreHeader(List<List<String>> headerList, String weight, boolean showEmployeeScore, boolean showScore, boolean showComment) {

        if (showEmployeeScore && showScore) {
            List<String> scoreHeader = Lists.newArrayList();
            scoreHeader.add("自评（" + weight + "）");
            scoreHeader.add("评分");
            headerList.add(scoreHeader);
        }
        if (showEmployeeScore && showComment) {
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

    private List<String> mergeLine(List<List<Object>> data) {
        return data.stream().map(s -> s.get(0).toString()).collect(Collectors.toList());
    }


    private List<List<Object>> getData(List<DocDetailDataModel> docDetailDataModelList, List<ExcelHeaderModel> headerModelList) {
        List<List<Object>> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(docDetailDataModelList)) {
            getExportListData(list, docDetailDataModelList, headerModelList);
        }
        return list;
    }


    public void getExportListData(List<List<Object>> listData, List<DocDetailDataModel> list, List<ExcelHeaderModel> headerModelList) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        try {
            for (DocDetailDataModel t : list) {
                List<Object> rowLine = new ArrayList<>();
                for (ExcelHeaderModel s : headerModelList) {
                    String getMethodName = "get" + s.getFieldName().substring(0, 1).toUpperCase() + s.getFieldName().substring(1);
                    Class clazz = t.getClass();
                    Method getMethod;
                    getMethod = clazz.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    rowLine.add(value);
                }
                listData.add(rowLine);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 固定部分
     * <pre>
     *     fixedField 必须在固定的map内
     * </pre>
     *
     * @param config        配置信息
     * @param headModelList 头部模型
     */
    private void buildFixHead(DocDetailExcelConfig config, List<List<String>> headList, List<ExcelHeaderModel> headModelList) {
        // 信息部分，维度权重和上下限制，写到维度里
        String fixedField;
        if (config == null || StringUtils.isEmpty(config.getHead())) {
            fixedField = "完成说明,完成度,完成值,单位,权重";
        } else {
            fixedField = config.getHead();
        }
        fixedField = "维度,目标,描述,标准," + fixedField;
        List<ExcelHeaderModel> modelList = Arrays.stream(fixedField.split(",")).map(e -> {
            ExcelHeaderModel excelHeaderModel = new ExcelHeaderModel();
            excelHeaderModel.setLabel(e);
            excelHeaderModel.setFieldName(ExcelFieldUtil.getNameFieldByName(e));
            excelHeaderModel.setFixed(Boolean.TRUE);
            return excelHeaderModel;
        }).collect(Collectors.toList());

        Arrays.stream(fixedField.split(",")).forEach(e -> {
            List<String> head = Lists.newArrayList();
            head.add(e);
            headList.add(head);
        });
        // head 上级会写
        headModelList.addAll(modelList);
    }

    private void buildDynamicHead(DocDetailExcelConfig config,
                                  DocDetailExcelModel docDetailExcelModel,
                                  List<ExcelHeaderModel> headModelList,
                                  List<DocDetailDataModel> docDetailDataModelList) {

    }

    private DocDetailExcelConfig config() {
        DocDetailExcelConfig config = new DocDetailExcelConfig();
        config.setHead("目标值,完成值,权重");
        config.setShowComment(false);
        return config;
    }

}