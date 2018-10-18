package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.BookRepository;
import com.pojo.Book;
import com.pojo.BookOps;

@Service
public class BookService {
	
	@Autowired(required = true)
	BookRepository<Book> bookRepository;

	public List<Book> listAllBooks(){
		Iterable<Book> lstItr = bookRepository.findAll();
		List<Book> lstBooks = new ArrayList<>();
		lstItr.forEach(lstBooks::add);
		return lstBooks;
	}

	public void addBook(Book book){
		try {
			//bookdao.writeBookObject(book);
			bookRepository.save(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteBook(BookOps bookOps){
		System.out.println(bookOps.getDelBookIds());
		try {
			long bookId = bookOps.getDelBookIds()[0];
			//this.bookdao.deleteBookObject(bookId);
			bookRepository.delete(bookId);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Book> searchBook(BookOps bookOps){
		List<Book> lstBooks = new ArrayList<>();
		try {
			long bookId = 0;
			if(bookOps.getSearchBookId()!=null && bookOps.getSearchBookId()!="")
			 bookId = Long.parseLong(bookOps.getSearchBookId());
			//Optional<Book> bookOpt = bookRepository.findById(bookId);
			Book book = null;
			if(bookId!=0) {
			   book =bookRepository.findOne(bookId);
			}else {
				List<Book> lstSearchBooks =bookRepository.searchBookByTitle(bookOps.getSearchBookTitle());
				book =lstSearchBooks.get(0);
			}
			lstBooks.add(book);
		}catch(Exception e) {
			e.printStackTrace();
		}

		return lstBooks;
	}
	


}
