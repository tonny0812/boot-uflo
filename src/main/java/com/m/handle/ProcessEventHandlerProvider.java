package com.m.handle;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessDefinition;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.ProcessEventHandler;
import com.bstek.uflo.service.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取流程开始结束函数,进行相应处理
 */
@Component
public class ProcessEventHandlerProvider implements ProcessEventHandler {
    protected Logger logger = LoggerFactory.getLogger(ProcessEventHandlerProvider.class);

    @Autowired
    ProcessService processService;

    @Override
    public void start(ProcessInstance processInstance, Context context) {

    }

    @Override
    public void end(ProcessInstance processInstance, Context context) {
        ProcessDefinition processDefinition = processService.getProcessById(processInstance.getProcessId());

        logger.info("process " + processDefinition.getName() + " end.");
        logger.info("processInstance.getCurrentTask():" + processInstance.getCurrentTask());
    }
}
