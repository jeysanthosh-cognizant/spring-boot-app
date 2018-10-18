package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pojo.Subject;

@Repository
public interface SubjectRepository<P> extends JpaRepository<Subject,Long> {
	@Query("select s from Subject s where s.durationInHours=?1")
	public List<Subject> searchSubjectByDuration(int durationInHours);


}
