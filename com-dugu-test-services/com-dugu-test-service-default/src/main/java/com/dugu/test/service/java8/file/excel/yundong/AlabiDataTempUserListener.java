package com.dugu.test.service.java8.file.excel.yundong;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dugu.test.service.java8.file.excel.ExcelUtil;
import com.dugu.test.service.java8.file.excel.yundong.model.InvitedDemoModel;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Optional;

/**
 * @author cihun
 * @date 2023/1/11 17:29
 */
@Slf4j
public class AlabiDataTempUserListener extends AnalysisEventListener<InvitedDemoModel> {
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
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ExcelUtil.YUNDONG_DOC_USER_PATH), "UTF-8"), 1024);

            for (InvitedDemoModel e : invitedModelList) {
                if (e.getUserId().equals("userId")) {
                    continue;
                }



                StringBuilder sb = new StringBuilder();
                sb.append(StringUtils.trim(e.getUserId())).append(",")
                        .append(StringUtils.trim(e.getUserName())).append(",")
                        .append(StringUtils.trim(e.getPosition())).append(",")
                        .append(StringUtils.trim(e.getDepartment()));
                bw.write(sb.toString());
                bw.newLine();
                System.out.println("--------" + e.getUserName());
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (bw != null) {
                try {
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }


        }

    }
}
