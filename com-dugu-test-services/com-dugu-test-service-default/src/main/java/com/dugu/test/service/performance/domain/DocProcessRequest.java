package com.dugu.test.service.performance.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author cihun
 * @date 2022-07-23 10:11 上午
 */
@Getter
@Setter
@Builder
public class DocProcessRequest implements Serializable {
    /**
     * 文档信息
     */
    private PfmDocDTO docInfo;
}
