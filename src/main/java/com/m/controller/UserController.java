package com.m.controller;

import com.bstek.uflo.model.ProcessDefinition;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.model.task.Task;
import com.bstek.uflo.model.task.TaskState;
import com.bstek.uflo.query.ProcessQuery;
import com.bstek.uflo.query.TaskQuery;
import com.bstek.uflo.service.ProcessService;
import com.bstek.uflo.service.StartProcessInfo;
import com.bstek.uflo.service.TaskService;
import com.m.dto.UserModel;
import com.m.entity.User;
import com.m.service.UserService;
import com.m.utils.CodeMsg;
import com.m.utils.MD5Util;
import com.m.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    protected Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    ProcessService processService;
    @Autowired
    TaskService taskService;

    /**
     * 创建用户
     *
     * @param userModel
     * @return
     */
    @PostMapping("/create")
    public ResultUtil<String> createUser(UserModel userModel) {
        logger.info("userModel name: " + userModel.getName());
        int result = userService.createUser(userModel);
        if (result > 0) {
            return ResultUtil.success("创建成功");
        }
        return ResultUtil.error(CodeMsg.USER_CREATE_ERROR);
    }

    /**
     * 用户登录
     *
     * @param userModel
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResultUtil<String> loginUser(UserModel userModel,
                                        HttpServletRequest request) {
        User user = userService.getUserByName(userModel.getName());
//         登录成功将name值保存session
        if (user != null && user.getPassword().equals(MD5Util.GetMD5Code(userModel.getPassword()))) {
            request.getSession().setAttribute("user", user.getName());
            return ResultUtil.success("登陆成功");
        }
        return ResultUtil.error(CodeMsg.COOKIE_ERROR);
    }

    /**
     * 发起审批流
     * @param request
     * @return
     */
    @GetMapping("/start")
    public ResultUtil<String> startProcess(HttpServletRequest request){
        String userName = (String) request.getSession().getAttribute("user");
        ProcessDefinition processDefinition = processService
                .getProcessByName("test");
        StartProcessInfo info = new StartProcessInfo(userName);
        info.setCompleteStartTask(true);
        ProcessInstance processInstance = processService.startProcessById(
                processDefinition.getId(), info);
        return ResultUtil.success("审批流发起成功");
    }


    @GetMapping("myprocess")
    public ResultUtil<List<Task>> findMyProcess(HttpServletRequest request) {
        List<Task> taskList = new ArrayList<>();
        String userName = (String) request.getSession().getAttribute("user");
        TaskQuery query = taskService.createTaskQuery();
        query.addTaskState(TaskState.Created);
        query.addTaskState(TaskState.InProgress);
        query.addTaskState(TaskState.Ready);
        query.addTaskState(TaskState.Suspended);
        query.addTaskState(TaskState.Reserved);
        ProcessQuery processQuery = processService.createProcessQuery();
        processQuery.nameLike("test");
        List<ProcessDefinition> processDefinitions = processQuery.list();
        for (ProcessDefinition processDefinition : processDefinitions) {
            query.processId(processDefinition.getId());
        }

        List<Task> tasks = query.list();
        for (Task task : tasks) {
            Task entity = new Task();
            entity.setBusinessId(task.getBusinessId());
            ProcessDefinition pd = processService.getProcessById(task
                    .getProcessId());
            ProcessInstance processInstance = processService
                    .getProcessInstanceById(task.getRootProcessInstanceId());
            if (processInstance != null) {
                entity.setOwner(processInstance.getPromoter());
            }
            entity.setSubject(task.getSubject());
            entity.setTaskName(task.getNodeName());
            taskList.add(entity);
        }
        return ResultUtil.success(taskList);
    }

    @GetMapping("/approve")
    public ResultUtil<String> approve() {
        return ResultUtil.success("审批成功");
    }
}
