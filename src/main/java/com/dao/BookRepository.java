package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pojo.Book;
@Repository
public interface BookRepository<P> extends CrudRepository<Book,Long> {
	
@Query("select b from Book b where b.title=?1")
public List<Book> searchBookByTitle(String title);
}
