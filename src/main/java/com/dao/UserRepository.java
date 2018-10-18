package com.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pojo.User;

@Repository
public interface UserRepository<P> extends CrudRepository<User,String> {

}
