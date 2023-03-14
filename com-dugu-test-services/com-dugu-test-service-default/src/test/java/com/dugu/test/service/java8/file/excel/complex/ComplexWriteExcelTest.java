package com.dugu.test.service.java8.file.excel.complex;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.dugu.test.service.java8.file.excel.complex.model.DocDetailDataModel;
import com.dugu.test.service.java8.file.excel.complex.model.DocDetailExcelModel;
import com.dugu.test.service.java8.file.excel.complex.model.ExcelHeaderModel;
import com.dugu.test.service.java8.file.excel.complex.model.UserScoreValueModel;
import com.dugu.test.service.java8.file.excel.complex.strategy.TitleSheetWriteHandler;
import com.dugu.test.service.java8.file.excel.complex.strategy.CustomMergeStrategy;
import com.dugu.test.service.java8.file.excel.complex.strategy.SummarySheetWriteHandler;
import com.dugu.test.service.performance.domain.UserSimpleDTO;
import com.google.common.collect.Maps;
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.dugu.test.service.java8.file.excel.complex.strategy.CustomHorizontalCellStyleStrategy.getHorizontalCellStyleStrategy;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

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
        String fileName = "//Users/cihun/Documents/complex.xlsx";

        //创建表格对象
        WriteTable table = new WriteTable();
        //设置第N个表格
        table.setTableNo(1);
//        table.setNeedHead(false);
        //------ 原文档主管及其自己
        UserSimpleDTO leader = new UserSimpleDTO();
        leader.setLabel("003");
        UserSimpleDTO user = new UserSimpleDTO();
        user.setLabel("刺魂");
        DocDetailExcelConfig config = config();
        //头部字段数据映射模型
        List<ExcelHeaderModel> headModelList = Lists.newArrayList();
        // 文档数据
        List<DocDetailDataModel> docDetailDataModelList = buildDocDetailDataModelList();
        // 计划维度数据
        DocDetailExcelModel docDetailExcelModel = buildDocDetailExcelModel(leader, user);
        docDetailExcelModel.setLeaderScoreList(docDetailDataModelList.get(0).getLeaderScoreList());
        // 创建表头集合
        List<List<String>> headList = Lists.newArrayList();
        //固定头部
        buildFixHead(config, headList, headModelList);
        // 动态头部
        buildDynamicHead(config, docDetailExcelModel, headList, headModelList, docDetailDataModelList);
        // head列数
        docDetailExcelModel.setCellCount(headList.size());
        // head新消息
        table.setHead(headList);
        // 从第几行开始写列表数据
        table.setRelativeHeadRowIndex(2);
        // 获取导出数据
        List<List<Object>> data = getData(config(), docDetailDataModelList, headModelList);
        docDetailExcelModel.setDataSize(data.size());
        //创建ExcelWriter写入对象k
        ExcelWriter excelWriter = EasyExcel.write(new FileOutputStream(fileName)).build();
        WriteSheet sheet = EasyExcel.writerSheet(docDetailExcelModel.getPlanName())
                .sheetNo(1)
                .head(headList)
                .relativeHeadRowIndex(2)
                .registerWriteHandler(getHorizontalCellStyleStrategy())
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .registerWriteHandler(new CustomMergeStrategy(mergeLine(data), 0))
                .registerWriteHandler(new CustomMergeStrategy(mergeLine(data), 1))
                .registerWriteHandler(new CustomMergeStrategy(mergeLine(data), 2))
                .registerWriteHandler(new TitleSheetWriteHandler(docDetailExcelModel))
                .build();
        excelWriter.write(data, sheet);

        WriteTable tableSummary = new WriteTable();
        //设置第N个表格
        tableSummary.setTableNo(2);
        tableSummary.setNeedHead(false);
        tableSummary.setCustomWriteHandlerList(Collections.singletonList(new SummarySheetWriteHandler(docDetailExcelModel)));
        excelWriter.write(Lists.newArrayList(), sheet, tableSummary);
        //  释放资源
        excelWriter.finish();
    }


    private List<String> mergeLine(List<List<Object>> data) {
        return data.stream().map(s -> s.get(0).toString()).collect(Collectors.toList());
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
            fixedField = "完成说明,完成度,完成值,单位,权重(%)";
        } else {
            fixedField = config.getHead();
        }
        fixedField = "维度,维度权重(%),分数上限,目标,描述,标准," + fixedField;
        List<ExcelHeaderModel> modelList = Arrays.stream(fixedField.split(",")).map(e -> {
            ExcelHeaderModel excelHeaderModel = new ExcelHeaderModel();
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
                                  List<List<String>> headerList,
                                  List<ExcelHeaderModel> headModelList,
                                  List<DocDetailDataModel> docDetailDataModelList) {
        // 自评
        if (docDetailExcelModel.getEmployeeScore() != null) {
            employeeScoreHeader(config, headModelList, headerList, docDetailExcelModel.getEmployeeScore().getWeight());
        }
        // 同事互评评分
        if (StringUtils.isNotEmpty(docDetailExcelModel.getInviteWeight())) {
            inviteScoreHeader(config, docDetailDataModelList, headModelList, headerList, docDetailExcelModel.getInviteWeight());
        }
        // 上级评分
        if (StringUtils.isNotEmpty(docDetailExcelModel.getLeaderScoreWeight())) {
            leaderScoreHeader(config, docDetailDataModelList, headModelList, headerList, docDetailExcelModel.getLeaderScoreWeight());
        }
        if (config.getShowTotal() == null || config.getShowTotal()) {
            // 目标合计
            List<String> objectTotalHeader = Lists.newArrayList();
            objectTotalHeader.add(ExcelFieldUtil.NAME_OBJECT_TOTAL_SCORE);
            headerList.add(objectTotalHeader);
            ExcelHeaderModel excelHeaderModel = new ExcelHeaderModel();
            excelHeaderModel.setFixed(Boolean.FALSE);
            excelHeaderModel.setFieldName(ExcelFieldUtil.VALUE_OBJECT_TOTAL_SCORE);
            headModelList.add(excelHeaderModel);
        }
    }

    private void employeeScoreHeader(DocDetailExcelConfig config,
                                     List<ExcelHeaderModel> headModelList,
                                     List<List<String>> headerList,
                                     String weight) {

        ExcelHeaderModel excelHeaderModel = new ExcelHeaderModel();
        excelHeaderModel.setFixed(Boolean.FALSE);
        excelHeaderModel.setFieldName(ExcelFieldUtil.VALUE_EMPLOYEE_SCORE);
        headModelList.add(excelHeaderModel);

        List<String> scoreHeader = Lists.newArrayList();
        scoreHeader.add("自评（" + weight + "%）");
        scoreHeader.add(ExcelFieldUtil.NAME_SCORE);
        headerList.add(scoreHeader);

        if (config.getShowComment() == null || config.getShowComment()) {
            List<String> commentHeader = Lists.newArrayList();
            commentHeader.add("自评（" + weight + "%）");
            commentHeader.add(ExcelFieldUtil.NAME_COMMENT);
            headerList.add(commentHeader);
        }
        if (config.getShowTotal() == null || config.getShowTotal()) {
            List<String> commentHeader = Lists.newArrayList();
            commentHeader.add(ExcelFieldUtil.NAME_OBJECT_EMPLOYEE_TOTAL);
            headerList.add(commentHeader);
            excelHeaderModel = new ExcelHeaderModel();
            excelHeaderModel.setFixed(Boolean.FALSE);
            excelHeaderModel.setFieldName(ExcelFieldUtil.VALUE_OBJECT_EMPLOYEE_TOTAL);
            headModelList.add(excelHeaderModel);
        }
    }

    private void inviteScoreHeader(DocDetailExcelConfig config,
                                   List<DocDetailDataModel> docDetailDataModelList,
                                   List<ExcelHeaderModel> headModelList,
                                   List<List<String>> headerList,
                                   String weight) {
        if (CollectionUtils.isNotEmpty(docDetailDataModelList)) {
            ExcelHeaderModel excelHeaderModel = new ExcelHeaderModel();
            excelHeaderModel.setFixed(Boolean.FALSE);
            excelHeaderModel.setFieldName(ExcelFieldUtil.VALUE_INVITE_SCORE);
            headModelList.add(excelHeaderModel);


            List<UserScoreValueModel> newUserScoreValueModels = getUserScoreValueModels(docDetailDataModelList);

            if (CollectionUtils.isNotEmpty(newUserScoreValueModels)) {
                newUserScoreValueModels.forEach(e -> {
                    // 权重
                    List<String> weightHeader = Lists.newArrayList();
                    weightHeader.add("同事互评评分（" + weight + "%）");
                    weightHeader.add(e.getUser().getLabel());
                    weightHeader.add(ExcelFieldUtil.NAME_WEIGHT);
                    headerList.add(weightHeader);
                    //分数
                    List<String> scoreHeader = Lists.newArrayList();
                    scoreHeader.add("同事互评评分（" + weight + "%）");
                    scoreHeader.add(e.getUser().getLabel());
                    scoreHeader.add(ExcelFieldUtil.NAME_SCORE);
                    headerList.add(scoreHeader);
                    if (config.getShowComment() == null || config.getShowComment()) {
                        List<String> commentHeader = Lists.newArrayList();
                        commentHeader.add("同事互评评分（" + weight + "%）");
                        commentHeader.add(e.getUser().getLabel());
                        commentHeader.add(ExcelFieldUtil.NAME_COMMENT);
                        headerList.add(commentHeader);
                    }
                });
                if (config.getShowTotal() == null || config.getShowTotal()) {
                    List<String> commentHeader = Lists.newArrayList();
                    commentHeader.add(ExcelFieldUtil.NAME_OBJECT_INVITE_SCORE);
                    headerList.add(commentHeader);
                    excelHeaderModel = new ExcelHeaderModel();
                    excelHeaderModel.setFixed(Boolean.FALSE);
                    excelHeaderModel.setFieldName(ExcelFieldUtil.VALUE_OBJECT_INVITE_SCORE);
                    headModelList.add(excelHeaderModel);
                }

            }
        }
    }

    private List<UserScoreValueModel> getUserScoreValueModels(List<DocDetailDataModel> docDetailDataModelList) {
        if (CollectionUtils.isNotEmpty(docDetailDataModelList)) {
            return docDetailDataModelList.stream().filter(Objects::nonNull)
                    .flatMap(e -> e.getInviteScoreList().stream().filter(Objects::nonNull))
                    .collect(Collectors.toList()).stream().
                    collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getUser().getId()))), ArrayList::new));
        }
        return Lists.newArrayList();
    }

    private void leaderScoreHeader(DocDetailExcelConfig config,
                                   List<DocDetailDataModel> docDetailDataModelList,
                                   List<ExcelHeaderModel> headModelList,
                                   List<List<String>> headerList, String weight) {
        if (CollectionUtils.isNotEmpty(docDetailDataModelList)) {
            ExcelHeaderModel excelHeaderModel = new ExcelHeaderModel();
            excelHeaderModel.setFixed(Boolean.FALSE);
            excelHeaderModel.setFieldName(ExcelFieldUtil.VALUE_LEADER_SCORE);
            headModelList.add(excelHeaderModel);
            DocDetailDataModel docDetailDataModel = docDetailDataModelList.get(0);
            List<UserScoreValueModel> userScoreValueModels = docDetailDataModel.getLeaderScoreList();
            if (CollectionUtils.isNotEmpty(userScoreValueModels)) {
                userScoreValueModels.forEach(e -> {
                    List<String> scoreHeader = Lists.newArrayList();
                    scoreHeader.add("上级评分（" + weight + "%）");
                    scoreHeader.add(e.getUser().getLabel() + "（" + e.getWeight() + "%)");
                    scoreHeader.add(ExcelFieldUtil.NAME_SCORE);
                    headerList.add(scoreHeader);
                    if (config.getShowComment() == null || config.getShowComment()) {
                        List<String> commentHeader = Lists.newArrayList();
                        commentHeader.add("上级评分（" + weight + "%）");
                        commentHeader.add(e.getUser().getLabel() + "（" + e.getWeight() + "%)");
                        commentHeader.add(ExcelFieldUtil.NAME_COMMENT);
                        headerList.add(commentHeader);
                    }
                });
                if (config.getShowTotal() == null || config.getShowTotal()) {
                    List<String> commentHeader = Lists.newArrayList();
                    commentHeader.add(ExcelFieldUtil.NAME_OBJECT_LEADER_SCORE);
                    headerList.add(commentHeader);
                    excelHeaderModel = new ExcelHeaderModel();
                    excelHeaderModel.setFixed(Boolean.FALSE);
                    excelHeaderModel.setFieldName(ExcelFieldUtil.VALUE_OBJECT_LEADER_SCORE);
                    headModelList.add(excelHeaderModel);
                }

            }
        }

    }


    private List<List<Object>> getData(DocDetailExcelConfig config,
                                       List<DocDetailDataModel> docDetailDataModelList,
                                       List<ExcelHeaderModel> headerModelList) {
        List<List<Object>> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(docDetailDataModelList)) {
            getExportListData(config, list, docDetailDataModelList, headerModelList);
        }
        return list;
    }


    public void getExportListData(DocDetailExcelConfig config,
                                  List<List<Object>> listData,
                                  List<DocDetailDataModel> list,
                                  List<ExcelHeaderModel> headerModelList) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        List<UserScoreValueModel> inviteList = getUserScoreValueModels(list);
        try {
            for (DocDetailDataModel t : list) {
                List<Object> rowLine = new ArrayList<>();
                for (ExcelHeaderModel s : headerModelList) {
                    String getMethodName = "get" + s.getFieldName().substring(0, 1).toUpperCase() + s.getFieldName().substring(1);
                    Class clazz = t.getClass();
                    Method getMethod;
                    getMethod = clazz.getMethod(getMethodName, new Class[]{});
                    if (ExcelFieldUtil.VALUE_EMPLOYEE_SCORE.equals(s.getFieldName())) {
                        UserScoreValueModel value = (UserScoreValueModel) getMethod.invoke(t, new Object[]{});
                        if (value != null) {
                            rowLine.add(value.getScore());
                            if (config.getShowComment() != null && config.getShowComment()) {
                                rowLine.add(value.getComment());
                            }
                        }
                    } else if (ExcelFieldUtil.VALUE_LEADER_SCORE.equals(s.getFieldName())) {
                        List<UserScoreValueModel> value = (List<UserScoreValueModel>) getMethod.invoke(t, new Object[]{});
                        if (CollectionUtils.isNotEmpty(value)) {
                            value.forEach(e -> {
                                rowLine.add(e.getScore());
                                if (config.getShowComment() != null && config.getShowComment()) {
                                    rowLine.add(e.getComment());
                                }
                            });
                        }
                    } else if (ExcelFieldUtil.VALUE_INVITE_SCORE.equals(s.getFieldName())) {
                        List<UserScoreValueModel> value = (List<UserScoreValueModel>) getMethod.invoke(t, new Object[]{});
                        if (CollectionUtils.isNotEmpty(value) && CollectionUtils.isNotEmpty(inviteList)) {
                            Map<String, UserScoreValueModel> valueModelMap = value.stream().collect(Collectors.toMap(e -> e.getUser().getId(), Function.identity(), (k1, k2) -> k1));
                            inviteList.forEach(e -> {
                                UserScoreValueModel u = valueModelMap.get(e.getUser().getId());
                                if (u != null) {
                                    if (StringUtils.isNotEmpty(e.getWeight())) {
                                        rowLine.add(e.getWeight() + "%");
                                    } else {
                                        rowLine.add("");
                                    }
                                    rowLine.add(e.getScore());
                                    if (config.getShowComment() != null && config.getShowComment()) {
                                        rowLine.add(e.getComment());
                                    }
                                } else {
                                    rowLine.add("");
                                    rowLine.add("");
                                    if (config.getShowComment() != null && config.getShowComment()) {
                                        rowLine.add("");
                                    }
                                }
                            });
                        }
                    } else {
                        Object value = getMethod.invoke(t, new Object[]{});
                        rowLine.add(value);
                    }


                }
                listData.add(rowLine);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
        docDetailExcelModel.setInviteWeight("20");
        docDetailExcelModel.setLeaderScoreWeight("70");
        //
        docDetailExcelModel.setLeaderScore("88.1");
        docDetailExcelModel.setLeaderValueScore("A");


        UserScoreValueModel employeeScoreTotal = new UserScoreValueModel();
        employeeScoreTotal.setComment("what your problem");
        employeeScoreTotal.setWeight("10");
        employeeScoreTotal.setScoreValue("B");
        employeeScoreTotal.setScore("99.1");

        docDetailExcelModel.setEmployeeScore(employeeScoreTotal);

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
        docDetailDataModel1.setObjectEmployeeTotal("10.1");

        //自评
        UserScoreValueModel employeeScore = new UserScoreValueModel();
        employeeScore.setScore("10.1");
        employeeScore.setComment("我自我感觉良好");
        docDetailDataModel1.setEmployeeScore(employeeScore);
        // 邀请评分
        List<UserScoreValueModel> inviteScoreList = Lists.newArrayList();

        UserSimpleDTO inviteUser1 = new UserSimpleDTO();
        inviteUser1.setLabel("005");
        inviteUser1.setId("1");
        UserSimpleDTO inviteUser2 = new UserSimpleDTO();
        inviteUser2.setLabel("008");
        inviteUser2.setId("2");


        UserScoreValueModel invite1 = new UserScoreValueModel();
        invite1.setScore("92");
        invite1.setComment("邀请评分1");
        invite1.setUser(inviteUser1);
        invite1.setWeight("20");
        inviteScoreList.add(invite1);
        UserScoreValueModel invite2 = new UserScoreValueModel();
        invite2.setScore("89");
        invite2.setComment("邀请评分2");
        invite2.setUser(inviteUser2);
        invite2.setWeight("80");
        inviteScoreList.add(invite2);
        docDetailDataModel1.setInviteScoreList(inviteScoreList);


        // 上级评分
        List<UserScoreValueModel> leaderScoreList = Lists.newArrayList();
        UserSimpleDTO leader = new UserSimpleDTO();
        leader.setLabel("003");
        UserSimpleDTO leader2 = new UserSimpleDTO();
        leader2.setLabel("99");
        UserScoreValueModel leaderScore = new UserScoreValueModel();
        leaderScore.setScore("92");
        leaderScore.setComment("自定义主管评分3");
        leaderScore.setUser(leader);
        leaderScore.setWeight("20");
        leaderScoreList.add(leaderScore);

        leaderScore = new UserScoreValueModel();
        leaderScore.setScore("99");
        leaderScore.setComment("自定义主管评分4");
        leaderScore.setUser(leader2);
        leaderScore.setWeight("80");
        leaderScoreList.add(leaderScore);

        docDetailDataModel1.setLeaderScoreList(leaderScoreList);
        docDetailDataModel1.setObjectLeaderTotal("93.2");
        docDetailDataModel1.setObjectTotalScore("62");

        docDetailDataModelList.add(docDetailDataModel1);


        // 目标2

        DocDetailDataModel docDetailDataModel2 = new DocDetailDataModel();
        docDetailDataModel2.setDimensionName("业务维度");
        docDetailDataModel2.setDimensionWeight("25");
        docDetailDataModel2.setUpperLimit("100");
        docDetailDataModel2.setObjectName("提高技术生产力建议");
        docDetailDataModel2.setDesc("在一定时间提升声称里效率");
        docDetailDataModel2.setStandard(null);
        docDetailDataModel2.setWeight("80");
        docDetailDataModel2.setTargetValue("10");
        docDetailDataModel2.setChargeValue("10");
        docDetailDataModel2.setUnit("万元");
        docDetailDataModel2.setObjectEmployeeTotal("10.1");

        //自评
        UserScoreValueModel employeeScore2 = new UserScoreValueModel();
        employeeScore2.setScore("92.1");
        employeeScore2.setComment("");
        docDetailDataModel2.setEmployeeScore(employeeScore2);

        // 邀请评分
        List<UserScoreValueModel> inviteScoreList2 = Lists.newArrayList();

        UserSimpleDTO inviteUser3 = new UserSimpleDTO();
        inviteUser3.setId("3");
        inviteUser3.setLabel("99");
        UserSimpleDTO inviteUser4 = new UserSimpleDTO();
        inviteUser4.setLabel("001");
        inviteUser4.setId("4");


        UserScoreValueModel invite3 = new UserScoreValueModel();
        invite3.setScore("93.1");
        invite3.setComment("邀请评分1");
        invite3.setUser(inviteUser3);
        inviteScoreList2.add(invite3);
        UserScoreValueModel invite4 = new UserScoreValueModel();
        invite4.setScore("92.0");
        invite4.setComment("邀请评分2");
        invite4.setUser(inviteUser4);
        inviteScoreList2.add(invite4);
        docDetailDataModel2.setInviteScoreList(inviteScoreList2);

        docDetailDataModel2.setObjectInviteTotal("23.2");

        // 上级评分
        List<UserScoreValueModel> leaderScoreList2 = Lists.newArrayList();
        UserScoreValueModel leaderScore2 = new UserScoreValueModel();
        leaderScore2.setScore("19");
        leaderScore2.setComment("自定义主管评分11");
        leaderScore2.setUser(leader);
        leaderScore2.setWeight("20");
        leaderScoreList2.add(leaderScore2);

        leaderScore2 = new UserScoreValueModel();
        leaderScore2.setScore("98");
        leaderScore2.setComment("自定义主管评分21");
        leaderScore2.setUser(leader2);
        leaderScore2.setWeight("80");
        leaderScoreList2.add(leaderScore2);

        docDetailDataModel2.setLeaderScoreList(leaderScoreList2);
        docDetailDataModel2.setObjectLeaderTotal("82.00");
        docDetailDataModel2.setObjectTotalScore("92.00");

        docDetailDataModelList.add(docDetailDataModel2);


        return docDetailDataModelList;
    }


    private DocDetailExcelConfig config() {
        DocDetailExcelConfig config = new DocDetailExcelConfig();
        // config.setHead("目标值,完成值,权重(%)");
        config.setShowComment(false);
        config.setShowTotal(true);
        return config;
    }


}