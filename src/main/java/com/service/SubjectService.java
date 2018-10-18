package com.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.BookRepository;
import com.dao.SubjectRepository;
import com.pojo.Book;
import com.pojo.Subject;
import com.pojo.SubjectOps;

@Service
public class SubjectService {
	
	@Autowired(required=true)
	SubjectRepository<Subject> subjectRepository;
	
	@Autowired(required = true)
	BookRepository<Book> bookRepository;

	
	public List<Subject> listAllSubjects(){
		System.out.println(">>>>>>>>>>>>>In sujects");
		//List<Subject> lstSubjects = subjectdao.getSubjectList();
		Iterable<Subject> lstItr = subjectRepository.findAll();
		List<Subject> lstSubjects = new ArrayList<>();
		lstItr.forEach(lstSubjects::add);
		Set<Book> bookset = lstSubjects.get(0).getReferences();
		System.out.println(">>>>>>>>>>>>>"+bookset.size());
		return lstSubjects;
	}

	
	public void addSubject(Subject subject){
		Set<Book> bookSet = new HashSet<>();
		try {
			long bookId = subject.getRefBookId();
			//Book book = bookdao.readBookObject(bookId);
			//Optional<Book> bookOpt = bookRepository.findById(bookId);
			Book book = bookRepository.findOne(bookId);
			bookSet.add(book);
			subject.setReferences(bookSet);
			
			//subjectdao.writeSubjectObject(subject);
			subjectRepository.save(subject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delteSubject( SubjectOps subjectOps){
		System.out.println(subjectOps.getDelSubjIds());
		try {
			long subjectId = subjectOps.getDelSubjIds()[0];
			//subjectRepository.deleteById(subjectId);
			subjectRepository.delete(subjectId);
			//this.subjectdao.deleteSubjectObject(subjectId);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Subject> searchSubject(SubjectOps subjectOps){
		List<Subject> lstSubjects = new ArrayList<>();
		try {
			long subjectId = 0;
			if(subjectOps.getSearchSubjId()!=null && subjectOps.getSearchSubjId()!="")
				subjectId =Long.parseLong(subjectOps.getSearchSubjId());
		//Optional<Subject> subjectOpt = subjectRepository.findOne(subjectId); 
			Subject subject = null;
			if(subjectId!=0) {
				subject = subjectRepository.findOne(subjectId);
			}else {
				subject = subjectRepository.searchSubjectByDuration(subjectOps.getSearchDuration()).get(0);
			}
			//subjectdao.readSubjectObject(Long.parseLong(subjectOps.getSearchSubjId()));
		lstSubjects.add(subject);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lstSubjects;
	}

}
