package com.dugu.test.service.performance.doc.domain;

import com.dugu.test.service.performance.domain.PfmDocDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2022/12/11 11:42
 */
@Data
public class DocProcessBatchRequest extends DocProcessRequest{
    private static final long serialVersionUID = 5486433242070719878L;
    private String planId;
    private List<PfmDocDTO> docList;
}
