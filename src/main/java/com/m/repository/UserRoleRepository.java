package com.m.repository;

import com.m.entity.Role;
import com.m.entity.UserRole;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findByRoleAndApprovalState(Role role, Integer state);
}
