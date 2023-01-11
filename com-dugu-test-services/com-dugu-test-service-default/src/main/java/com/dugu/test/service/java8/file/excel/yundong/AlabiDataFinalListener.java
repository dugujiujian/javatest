package com.dugu.test.service.java8.file.excel.yundong;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dugu.test.service.java8.file.excel.ExcelHandlerImpl;
import com.dugu.test.service.java8.file.excel.ExcelUtil;
import com.dugu.test.service.java8.file.excel.domain.ExcelRequest;
import com.dugu.test.service.java8.file.excel.yundong.model.AlbUserInfo;
import com.dugu.test.service.java8.file.excel.yundong.model.AlbUserInfoTemplate;
import com.dugu.test.service.java8.file.excel.yundong.model.InvitedDemoModel;
import com.dugu.test.service.java8.file.excel.yundong.model.InvitedModel;
import com.dugu.test.service.java8.file.excel.yundong.model.InvitedYundongModel;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Description 监听数据记录读取
 * @Author by cihun
 * @Date 2020/5/23 4:33 PM
 */
@Slf4j
public class AlabiDataFinalListener extends AnalysisEventListener<InvitedDemoModel> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5000;

    private List<InvitedDemoModel> invitedModelList = Lists.newArrayList();

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(InvitedDemoModel data, AnalysisContext context) {
        if (data != null) {
            invitedModelList.add(data);
        }

    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        Map<String, AlbUserInfoTemplate> map = dataFromFile();
        InvitedDemoModel fields = builderHead();


        invitedModelList.add(0, fields);
        invitedModelList.forEach(e -> {
            AlbUserInfoTemplate v = map.get(e.getUserName());
            if (v != null) {
                e.setPosition(v.getPosition());
                e.setUserId(v.getUserId());
                e.setDepartment(v.getDepartment());
            }
        });


        ExcelHandlerImpl excelHandler = new ExcelHandlerImpl();
        ExcelRequest<InvitedDemoModel> dataExcelRequest = new ExcelRequest<>();
        dataExcelRequest.setDataList(invitedModelList);
        String filePath = ExcelUtil.FINAL_EXCEL_PATH;
        String fileName = ExcelUtil.FINAL_FILE_NAME;
        dataExcelRequest.setFilePath(filePath);
        dataExcelRequest.setFileName(fileName);
        dataExcelRequest.setHead(new InvitedDemoModel());
        excelHandler.exportToExcel(dataExcelRequest);
    }

    private static InvitedDemoModel builderHead() {
        InvitedDemoModel fields = new InvitedDemoModel();
        fields.setUserId("userId");
        fields.setUserName("userName");
        fields.setPosition("position");
        fields.setDepartment("department");

        fields.setInvitedUserName1("invitedUserName1");
        fields.setInvitedPhone1("invitedPhone1");
        fields.setInvitedWeight1("invitedWeight1");

        fields.setInvitedUserName2("invitedUserName2");
        fields.setInvitedPhone2("invitedPhone2");
        fields.setInvitedWeight2("invitedWeight2");

        fields.setInvitedUserName3("invitedUserName3");
        fields.setInvitedPhone3("invitedPhone3");
        fields.setInvitedWeight3("invitedWeight3");

        fields.setInvitedUserName4("invitedUserName4");
        fields.setInvitedPhone4("invitedPhone4");
        fields.setInvitedWeight4("invitedWeight4");

        fields.setInvitedUserName5("invitedUserName5");
        fields.setInvitedPhone5("invitedPhone5");
        fields.setInvitedWeight5("invitedWeight5");

        fields.setInvitedUserName6("invitedUserName6");
        fields.setInvitedPhone6("invitedPhone6");
        fields.setInvitedWeight6("invitedWeight6");


        fields.setInvitedUserName7("invitedUserName7");
        fields.setInvitedPhone7("invitedPhone7");
        fields.setInvitedWeight7("invitedWeight7");

        fields.setInvitedUserName8("invitedUserName8");
        fields.setInvitedPhone8("invitedPhone8");
        fields.setInvitedWeight8("invitedWeight8");

        fields.setInvitedUserName9("invitedUserName9");
        fields.setInvitedPhone9("invitedPhone9");
        fields.setInvitedWeight9("invitedWeight9");

        fields.setInvitedUserName10("invitedUserName10");
        fields.setInvitedPhone10("invitedPhone10");
        fields.setInvitedWeight10("invitedWeight10");
        return fields;
    }

    private Map<String, AlbUserInfoTemplate> dataFromFile() {
        // Java 8例子
        Map<String, AlbUserInfoTemplate> map = Maps.newHashMap();
        try {
            List<AlbUserInfoTemplate> list = Files.readAllLines(Paths.get(ExcelUtil.YUNDONG_DOC_USER_PATH), StandardCharsets.UTF_8).stream()
                    .map(item -> StringUtils.split(item, ","))
                    .map(this::buildAlbUserInfoTemplate)
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(list)) {
                list.forEach(e -> map.put(e.getUserName(), e));
            }
            return map;
        } catch (IOException e) {
            System.out.println(e);
        }
        return Maps.newHashMap();
    }

    private AlbUserInfoTemplate buildAlbUserInfoTemplate(String[] arr) {
        AlbUserInfoTemplate data = new AlbUserInfoTemplate();
        if (arr.length >= 3) {
            data.setUserId((StringUtils.trim(arr[0])));
            data.setUserName(getName(arr[1]));
            data.setPosition(StringUtils.trim(arr[2]));
            data.setDepartment(StringUtils.trim(arr[3]));
        } else {
            System.out.println(arr[0]);
        }
        return data;
    }

    private String getName(String name) {
        if (StringUtils.isNotEmpty(name)) {
            if (!name.contains("(")) {
                return StringUtils.trim(name);
            }
            return StringUtils.trim(name.substring(0, name.indexOf("(")));
        }
        return null;
    }

}