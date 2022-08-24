package com.techblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techblog.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
