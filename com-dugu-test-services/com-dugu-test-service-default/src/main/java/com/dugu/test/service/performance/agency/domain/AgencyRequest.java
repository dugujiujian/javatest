package com.dugu.test.service.performance.agency.domain;

import com.dugu.test.service.performance.agency.enums.TaskActionTypeEnum;
import com.dugu.test.service.performance.domain.model.Context;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2022/10/29 19:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgencyRequest implements Serializable {
    private static final long serialVersionUID = 3707597560484620227L;
    private String planId;
    private String docId;
    private String receiveUserId;
    private Context context;
    private String appId;
    private TaskActionTypeEnum taskActionTypeEnum;
}
