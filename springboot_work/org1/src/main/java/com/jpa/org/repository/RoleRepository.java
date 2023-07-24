package com.jpa.org.repository;

import com.jpa.org.entity.Member;
import com.jpa.org.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
