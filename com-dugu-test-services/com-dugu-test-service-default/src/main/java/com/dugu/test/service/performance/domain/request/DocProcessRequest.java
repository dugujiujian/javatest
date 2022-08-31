package com.dugu.test.service.performance.domain.request;

import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author cihun
 * @date 2022-07-23 10:11 上午
 */
@Getter
@Setter
@ToString
public class DocProcessRequest implements Serializable {

    /**
     * 文档Id
     */
    private String docId;

    private ProcessCodeEnum nextNode;


}
