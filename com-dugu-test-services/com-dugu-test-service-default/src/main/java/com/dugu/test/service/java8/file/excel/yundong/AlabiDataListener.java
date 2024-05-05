package com.dugu.test.service.java8.file.excel.yundong;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dugu.test.service.java8.file.excel.fixedtitle.ExcelHandlerImpl;
import com.dugu.test.service.java8.file.excel.ExcelUtil;
import com.dugu.test.service.java8.file.excel.domain.ExcelRequest;
import com.dugu.test.service.java8.file.excel.yundong.model.AlbUserInfo;
import com.dugu.test.service.java8.file.excel.yundong.model.InvitedDemoModel;
import com.dugu.test.service.java8.file.excel.yundong.model.InvitedModel;
import com.dugu.test.service.java8.file.excel.yundong.model.InvitedYundongModel;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 监听数据记录读取
 * @Author by cihun
 * @Date 2020/5/23 4:33 PM
 */
@Slf4j
public class AlabiDataListener extends AnalysisEventListener<AlbUserInfo> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5000;

    private Map<String, InvitedYundongModel> dataMap = Maps.newHashMap();
    private Map<String, String> curMap = Maps.newHashMap();

    /**
     * 重名数量累计
     */
    private Map<String, Integer> userNameDupMap = Maps.newHashMap();

    private final String MAP_KEY_CUR_USER_NAME = "cur_user_name";

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(AlbUserInfo data, AnalysisContext context) {
        if (data == null) {
            System.out.println("数据不能为空");
            return;
        }
        // 考核用户名字
        String userName = StringUtils.trim(data.getUserName());
        String pm = StringUtils.trim(data.getPm());
        String pmPhone = StringUtils.trim(data.getPmPhone());
        String weight = StringUtils.trim(data.getWeight());
        if (StringUtils.isEmpty(userName)
                || StringUtils.isEmpty(pmPhone)
                || StringUtils.isEmpty(weight)) {
            System.out.println("参数无效:" + data);
            return;
        }
        String newWeight = StringUtils.replace(weight, "%", "");
        if (!NumberUtils.isDigits(newWeight)) {
            System.out.println("权重必须是数字:" + data);
            return;
        }
        // 是否新用户
        boolean newData = userName.equals(curMap.get(MAP_KEY_CUR_USER_NAME));
        if (!newData) {
            // 新用户处理
            // ----当前用户---
            curMap.put(MAP_KEY_CUR_USER_NAME, userName);
            // ---重复数量---
            Integer count = userNameDupMap.get(userName);
            InvitedYundongModel invitedYundongModel = getInvitedYundongModel(userName, pm, pmPhone, newWeight);
            if (count == null) {
                dataMap.put(userName, invitedYundongModel);
                userNameDupMap.put(userName, 0);
            } else {
                dataMap.put(userName + "_" + (count + 1), invitedYundongModel);
                userNameDupMap.put(userName, 1);
            }
        } else {
            InvitedYundongModel invitedYundongModel;
            Integer count = userNameDupMap.get(userName);
            if (count == 0) {
                invitedYundongModel = dataMap.get(userName);
            } else {
                invitedYundongModel = dataMap.get(userName + "_" + 1);
            }
            invitedYundongModel.getInvitedModelList().add(buildInviteModel(pm, pmPhone, newWeight));
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        List<InvitedDemoModel> list = new ArrayList<>();

        AtomicInteger i = new AtomicInteger();
        dataMap.forEach((k, v) -> {
            InvitedDemoModel invitedDemoModel = new InvitedDemoModel();
            invitedDemoModel.setUserName(v.getUserName());
            List<InvitedModel> invitedModelList = mergeDuplicateUserWeight(v.getInvitedModelList());
            fillInvited(invitedModelList, invitedDemoModel);
            list.add(invitedDemoModel);
        });


        ExcelHandlerImpl excelHandler = new ExcelHandlerImpl();
        ExcelRequest<InvitedDemoModel> dataExcelRequest = new ExcelRequest<>();
        dataExcelRequest.setDataList(list);
        dataExcelRequest.setFilePath(ExcelUtil.FINAL_EXCEL_PATH);
        dataExcelRequest.setFileName(ExcelUtil.TEMP_FILE_NAME);
        dataExcelRequest.setHead(new InvitedDemoModel());
        excelHandler.exportToExcel(dataExcelRequest);
    }

    private static void fillInvited(List<InvitedModel> invitedModelList, InvitedDemoModel invitedDemoModel) {
        if (CollectionUtils.isNotEmpty(invitedModelList)) {
            if (invitedModelList.size() > 10) {
                System.out.println("邀请人超出10人了,名字="+invitedDemoModel.getUserName());
                return;
            }
            int j = 1;
            for (InvitedModel it : invitedModelList) {
                if (j == 1) {
                    invitedDemoModel.setInvitedUserName1(it.getInvitedUserName());
                    invitedDemoModel.setInvitedPhone1(it.getInvitedPhone());
                    invitedDemoModel.setInvitedWeight1(it.getInvitedWeight());
                } else if (j == 2) {
                    invitedDemoModel.setInvitedUserName2(it.getInvitedUserName());
                    invitedDemoModel.setInvitedPhone2(it.getInvitedPhone());
                    invitedDemoModel.setInvitedWeight2(it.getInvitedWeight());
                } else if (j == 3) {
                    invitedDemoModel.setInvitedUserName3(it.getInvitedUserName());
                    invitedDemoModel.setInvitedPhone3(it.getInvitedPhone());
                    invitedDemoModel.setInvitedWeight3(it.getInvitedWeight());
                } else if (j == 4) {
                    invitedDemoModel.setInvitedUserName4(it.getInvitedUserName());
                    invitedDemoModel.setInvitedPhone4(it.getInvitedPhone());
                    invitedDemoModel.setInvitedWeight4(it.getInvitedWeight());
                } else if (j == 5) {
                    invitedDemoModel.setInvitedUserName5(it.getInvitedUserName());
                    invitedDemoModel.setInvitedPhone5(it.getInvitedPhone());
                    invitedDemoModel.setInvitedWeight5(it.getInvitedWeight());
                } else if (j == 6) {
                    invitedDemoModel.setInvitedUserName6(it.getInvitedUserName());
                    invitedDemoModel.setInvitedPhone6(it.getInvitedPhone());
                    invitedDemoModel.setInvitedWeight6(it.getInvitedWeight());
                } else if (j == 7) {
                    invitedDemoModel.setInvitedUserName7(it.getInvitedUserName());
                    invitedDemoModel.setInvitedPhone7(it.getInvitedPhone());
                    invitedDemoModel.setInvitedWeight7(it.getInvitedWeight());
                } else if (j == 8) {
                    invitedDemoModel.setInvitedUserName8(it.getInvitedUserName());
                    invitedDemoModel.setInvitedPhone8(it.getInvitedPhone());
                    invitedDemoModel.setInvitedWeight8(it.getInvitedWeight());
                } else if (j == 9) {
                    invitedDemoModel.setInvitedUserName9(it.getInvitedUserName());
                    invitedDemoModel.setInvitedPhone9(it.getInvitedPhone());
                    invitedDemoModel.setInvitedWeight9(it.getInvitedWeight());
                } else if (j == 10) {
                    invitedDemoModel.setInvitedUserName10(it.getInvitedUserName());
                    invitedDemoModel.setInvitedPhone10(it.getInvitedPhone());
                    invitedDemoModel.setInvitedWeight10(it.getInvitedWeight());
                }
                j++;
            }
        }
    }

    private static List<InvitedModel> mergeDuplicateUserWeight(List<InvitedModel> userList) {
        List<InvitedModel> invitedModelList = Lists.newArrayList();
        Map<String, String> phoneWeightMap = Maps.newHashMap();
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(userList)) {
            userList.forEach(e -> {
                String phone = e.getInvitedPhone();
                String sourceWeight = phoneWeightMap.get(e.getInvitedPhone());
                String curWeight = e.getInvitedWeight();
                if (StringUtils.isEmpty(sourceWeight)) {
                    phoneWeightMap.put(phone, curWeight);
                    InvitedModel invitedModel = new InvitedModel();
                    invitedModel.setInvitedPhone(e.getInvitedPhone());
                    invitedModel.setInvitedUserName(e.getInvitedUserName());
                    invitedModel.setInvitedWeight(curWeight);
                    invitedModelList.add(invitedModel);
                } else {
                    String newWeight = addExistUserWeight(curWeight, sourceWeight);
                    phoneWeightMap.put(phone, newWeight);
                }

            });
        }
        // 通过用户聚合权重
        invitedModelList.forEach(e -> {
            String weight = phoneWeightMap.get(e.getInvitedPhone());
            e.setInvitedWeight(weight);
        });
        return invitedModelList;
    }

    private static String addExistUserWeight(String weight, String sourceWeight) {
        if (StringUtils.isNotEmpty(weight) && StringUtils.isNotEmpty(sourceWeight)) {
            return BigDecimal.valueOf(Integer.parseInt(weight))
                    .add(BigDecimal.valueOf(Integer.parseInt((sourceWeight))))
                    .intValue() + "";
        }
        return null;
    }


    private static InvitedYundongModel getInvitedYundongModel(String userName, String pm, String pmPhone, String newWeight) {
        InvitedYundongModel invitedYundongModel = new InvitedYundongModel();
        invitedYundongModel.setUserName(userName);
        List<InvitedModel> invitedModelList = getInvitedModelList(pm, pmPhone, newWeight);
        invitedYundongModel.setInvitedModelList(invitedModelList);
        return invitedYundongModel;
    }

    private static List<InvitedModel> getInvitedModelList(String pm, String pmPhone, String newWeight) {
        List<InvitedModel> invitedModelList = Lists.newArrayList();
        invitedModelList.add(buildInviteModel(pm, pmPhone, newWeight));
        return invitedModelList;
    }

    private static InvitedModel buildInviteModel(String pm, String pmPhone, String newWeight) {
        InvitedModel invitedMode = new InvitedModel();
        invitedMode.setInvitedUserName(pm);
        invitedMode.setInvitedPhone(pmPhone);
        invitedMode.setInvitedWeight(newWeight);
        return invitedMode;
    }


}