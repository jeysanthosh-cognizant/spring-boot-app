package com.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pojo.Role;
@Repository
public interface RoleRepository<P> extends CrudRepository<Role,String> {

}
