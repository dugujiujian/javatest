package com.dugu.test.service.performance.doc.domain;

import com.dugu.test.service.performance.domain.PfmPlanDTO;
import com.dugu.test.service.performance.message.domain.PfmTemplateInfoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2022/12/11 11:43
 */
@Getter
@Setter
@ToString
public class DocProcessResponse implements Serializable {
    private static final long serialVersionUID = -1430051869849099884L;
    private PfmPlanDTO plan;
    private PfmTemplateInfoDTO template;
}
