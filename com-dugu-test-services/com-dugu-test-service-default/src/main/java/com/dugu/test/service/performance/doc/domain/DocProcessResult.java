package com.dugu.test.service.performance.doc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2022/12/11 11:34
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocProcessResult implements Serializable {

    private static final long serialVersionUID = 8592155929245405192L;
    private Boolean success;
    private String message;
}
