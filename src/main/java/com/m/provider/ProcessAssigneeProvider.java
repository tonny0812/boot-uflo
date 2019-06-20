package com.m.provider;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.assign.AssigneeProvider;
import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;

import java.util.Collection;

public class ProcessAssigneeProvider implements AssigneeProvider {

    @Override
    public boolean isTree() {
        return false;
    }

    @Override
    public String getName() {
        return "平台角色";
    }

    @Override
    public void queryEntities(PageQuery<Entity> pageQuery, String str) {

    }

    @Override
    public Collection<String> getUsers(String str, Context context, ProcessInstance processInstance) {
        return null;
    }

    @Override
    public boolean disable() {
        return false;
    }
}
