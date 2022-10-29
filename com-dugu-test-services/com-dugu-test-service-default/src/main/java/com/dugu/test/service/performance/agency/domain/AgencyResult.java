package com.dugu.test.service.performance.agency.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2022/10/29 18:46
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgencyResult implements Serializable {
    private static final long serialVersionUID = 5929309089015160737L;
    private Boolean success;
    private String msg;
    /**
     * 是否需要push到下一个状态
     */
    private Boolean pushStatus;

}
