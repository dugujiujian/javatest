package com.dugu.test.service.java8.file.excel.complex;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.dugu.test.service.java8.file.excel.complex.model.UserScoreHeadModel;
import com.dugu.test.service.java8.file.excel.complex.strategy.CustomMergeStrategy;
import junit.framework.TestCase;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
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
        //创建ExcelWriter写入对象k
        ExcelWriter excelWriter = EasyExcel.write(new FileOutputStream(fileName)).build();
        WriteSheet sheet = EasyExcel.writerSheet("文档")
                .sheetNo(1)
                .registerWriteHandler(new CustomMergeStrategy(mergeLine(), 0))
                .build();

        //创建表格对象
        WriteTable table = new WriteTable();
        //设置第N个表格
        table.setTableNo(1);
        //创建表头集合
        List<List<String>> headList = Lists.newArrayList();

        //===============自评==================

        fixHeader(headList);
        //===============自评==================
        UserScoreHeadModel employee = new UserScoreHeadModel();
        employee.setLabel("刺魂");
        employeeScoreHeader(headList, employee, "10%", true, true);
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

        table.setHead(headList);
        excelWriter.write(getData(), sheet, table);
        //  释放资源
        excelWriter.finish();
    }

    private void fixHeader(List<List<String>> headerList) {
        String fixHeader = "维度,目标,权重,描述,标准,完成度,完成值,单位,完成说明";
        Arrays.stream(StringUtils.split(fixHeader, ","))
                .forEach(h -> {
                    List<String> header = Lists.newArrayList();
                    header.add(h);
                    headerList.add(header);
                });
    }

    private void employeeScoreHeader(List<List<String>> headerList, UserScoreHeadModel userScoreHeadModel,
                                     String weight, boolean showScore, boolean showComment) {
        List<String> scoreHeader = Lists.newArrayList();
        scoreHeader.add("自评（" + weight + "）");
        scoreHeader.add(userScoreHeadModel.getLabel());
        scoreHeader.add("评分");
        List<String> commentHeader = Lists.newArrayList();
        commentHeader.add("自评（" + weight + "）");
        commentHeader.add(userScoreHeadModel.getLabel());
        commentHeader.add("评语");
        headerList.add(scoreHeader);
        headerList.add(commentHeader);
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
}