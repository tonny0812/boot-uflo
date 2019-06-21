package com.m.provider;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.assign.AssigneeProvider;
import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;
import com.m.entity.Role;
import com.m.entity.UserRole;
import com.m.repository.RoleRepository;
import com.m.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 获取平台用户角色
 */
@Component
public class ProcessAssigneeProvider implements AssigneeProvider {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

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
        Pageable pageable = PageRequest.of(pageQuery.getPageIndex() - 1,
                pageQuery.getPageSize(), Sort.Direction.DESC, "createdTime");
        Specification<Role> specification = new Specification<Role>() {

            @Override
            public Predicate toPredicate(Root<Role> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.notEqual(
                        root.get("status").as(Integer.TYPE), -1));
                predicates.add(cb.equal(
                        root.get("type").as(Integer.TYPE), 1));
                return cb.and(predicates.toArray(new Predicate[predicates
                        .size()]));
            }

        };
        Page<Role> pages = roleRepository.findAll(specification, pageable);
        List<Entity> entityList=new ArrayList<>();
        for (Role role : pages.getContent()) {
            Entity entity=new Entity(role.getId().toString(),role.getName());
            entityList.add(entity);
            pageQuery.setResult(entityList);
        }
        pageQuery.setRecordCount(Integer.parseInt(pages.getTotalElements()+""));
    }

    @Override
    public Collection<String> getUsers(String entityId, Context context, ProcessInstance processInstance) {
        Optional<Role> roleOptional = roleRepository.findById(Integer.valueOf(entityId));
        Role role = roleOptional.get();
        List<UserRole> userRoles=userRoleRepository.findByRoleAndApprovalStatus(role,1);
        Collection<String> users=new ArrayList<String>();
        for(UserRole userRole:userRoles){
            users.add(userRole.getUser().getName());
        }
        return users;
    }

    @Override
    public boolean disable() {
        return false;
    }
}
