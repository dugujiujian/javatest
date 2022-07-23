package com.dugu.test.service.performance.inf;

import com.dugu.test.service.performance.domain.DocProcessResponse;
import com.dugu.test.service.performance.domain.ProcessCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @author cihun
 * @date 2022-07-23 10:58 上午
 */
@Component
public class DocObjectEntryProcessor extends AbsDocProcessProcessor {

    @Override
    public boolean handle(DocProcessResponse response) {
        System.out.println(getCurProcessCode() + ":biz handle");
        // 其他业务逻辑处理
        // 更新文档状态
        return false;
    }

    @Override
    public boolean after(DocProcessResponse response) {
        // 清理待办
        // 发送消息
        System.out.println(getCurProcessCode() + ":biz handle");
        return false;
    }

    @Override
    public ProcessCodeEnum getCurProcessCode() {
        return ProcessCodeEnum.objectives_entry;
    }
}
