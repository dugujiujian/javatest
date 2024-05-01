package com.dugu.test.service.performance.doc;

import com.dugu.test.service.performance.doc.DocProcessService;
import com.dugu.test.service.performance.doc.domain.DocProcessBatchRequest;
import com.dugu.test.service.performance.doc.domain.DocProcessRequest;
import com.dugu.test.service.performance.doc.domain.DocProcessResponse;
import com.dugu.test.service.performance.doc.domain.DocProcessResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2022/12/11 11:49
 */
@Service
public class DocProcessServiceImpl implements DocProcessService {
    @Override
    public DocProcessResult process(DocProcessRequest request, DocProcessResponse response) {
        DocProcessResult result = DocProcessResult.builder().success(Boolean.FALSE).build();
        // 基础参数校验
        checkParam(request, result);
        // 数据初始化
        init(request, response, result);
        // 单文档处理
        if (!(request instanceof DocProcessBatchRequest)) {
            // 加锁
        } else {

        }
        result.setSuccess(Boolean.TRUE);
        return result;
    }

    private void checkParam(DocProcessRequest request, DocProcessResult result) {
        if (request == null) {
            result.setMessage("参数不能为空");
            return;
        }
        if (request.getContext() == null || StringUtils.isEmpty(request.getAppId())) {
            result.setMessage("context 参数不能为空");
            return;
        }
        if (request.getFrom() == null || request.getTo() == null) {
            result.setMessage("状态不能为空");
            return;
        }
        // 单文档处理
        if (!(request instanceof DocProcessBatchRequest)) {
            if (StringUtils.isEmpty(request.getDocId()) || request.getDoc() == null) {
                result.setMessage("文档信息不能为空");
                return;
            }
        }
    }

    private void init(DocProcessRequest request, DocProcessResponse response, DocProcessResult result) {
        // 计划信息获取
        // 模版信息获取
        // 流程节点信息获取
        // 单文档文档需要查询
    }

    /**
     * 是否进入下个状态
     *
     * @return
     */
    private boolean pushToNextState() {

    }

    private void fromHandler(DocProcessRequest request, DocProcessResponse response, DocProcessResult result) {

    }

    private void toHandler(DocProcessRequest request, DocProcessResponse response, DocProcessResult result) {

    }
}
