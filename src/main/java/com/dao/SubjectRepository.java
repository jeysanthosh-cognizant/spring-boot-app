package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pojo.Subject;


public interface SubjectRepository<P> extends JpaRepository<Subject,Long> {
	

}
