package com.dugu.test.service.performance.doc.domain;

import com.dugu.test.service.performance.domain.PfmDocDTO;
import com.dugu.test.service.performance.domain.model.Context;
import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2022/12/11 11:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocProcessRequest implements Serializable {
    private static final long serialVersionUID = 1818103198985025523L;
    private Context context;
    private String appId;
    private String docId;
    private ProcessCodeEnum from;
    private ProcessCodeEnum to;
    private PfmDocDTO doc;
}
