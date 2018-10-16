package com.executor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.BookRepository;
import com.dao.SubjectRepository;
import com.pojo.Book;
import com.pojo.BookOps;
import com.pojo.Subject;
import com.pojo.SubjectOps;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/*@Autowired(required = false)
	SerializeUtil serializeUtil;*/

	/*@Autowired(required = true)
	BookDaoImpl bookdao;*/
	
	@Autowired(required = true)
	BookRepository<Book> bookRepository;
	
	@Autowired(required=true)
	SubjectRepository<Subject> subjectRepository;

/*	@Autowired(required = true)
	SubjectDaoImpl subjectdao;
*/
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("in a mvc controller>>>>>>>>>");
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String listAllBooks(Locale locale, Model model){
		Iterable<Book> lstItr = bookRepository.findAll();
		List<Book> lstBooks = new ArrayList<>();
		lstItr.forEach(lstBooks::add);
		model.addAttribute("lstBooks", lstBooks );
		model.addAttribute("bookOps", new BookOps() );
		return "books";
	}

	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	public String listAllSubjects(Locale locale, Model model){
		System.out.println(">>>>>>>>>>>>>In sujects");
		//List<Subject> lstSubjects = subjectdao.getSubjectList();
		Iterable<Subject> lstItr = subjectRepository.findAll();
		List<Subject> lstSubjects = new ArrayList<>();
		lstItr.forEach(lstSubjects::add);
		Set<Book> bookset = lstSubjects.get(0).getReferences();
		System.out.println(">>>>>>>>>>>>>"+bookset.size());
		model.addAttribute("lstSubjects", lstSubjects );
		model.addAttribute("subjectOps", new SubjectOps() );
		return "subjects";
	}

	@RequestMapping(value = "/addbookform", method = RequestMethod.GET)
	public String bookForm(Locale locale, Model model,@ModelAttribute("book")Book book){
		return "bookForm";
	}

	@RequestMapping(value = "/addSubjectform", method = RequestMethod.GET)
	public String subjectForm(Locale locale, Model model,@ModelAttribute("subject")Subject subject){
		return "subjectForm";
	}

	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("book")Book book,Model modelMap){
		try {
			//bookdao.writeBookObject(book);
			bookRepository.save(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//List<Book> lstBooks = bookdao.getBookList();
		Iterable<Book> lstItr = bookRepository.findAll();
		List<Book> lstBooks = new ArrayList<>();
		lstItr.forEach(lstBooks::add);
		modelMap.addAttribute("lstBooks", lstBooks );
		modelMap.addAttribute("bookOps", new BookOps() );
		return "books";
	}

	@RequestMapping(value = "/addSubject", method = RequestMethod.POST)
	public String addSubject(@ModelAttribute("subject")Subject subject,Model modelMap){
		try {
			long bookId = subject.getRefBookId();
			//Book book = bookdao.readBookObject(bookId);
			//Optional<Book> bookOpt = bookRepository.findById(bookId);
			Book book = bookRepository.findOne(bookId);
			Set<Book> bookSet = new HashSet<>();
			bookSet.add(book);
			subject.setReferences(bookSet);
			
			//subjectdao.writeSubjectObject(subject);
			subjectRepository.save(subject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/subjects";
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
	public String deleteBook(Locale locale, Model model,@ModelAttribute("bookOps") BookOps bookOps){
		System.out.println(bookOps.getDelBookIds());
		try {
			long bookId = bookOps.getDelBookIds()[0];
			//this.bookdao.deleteBookObject(bookId);
			bookRepository.delete(bookId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/books";
	}

	@RequestMapping(value = "/deleteSubject", method = RequestMethod.POST)
	public String delteSubject(Locale locale, Model model,@ModelAttribute("subjectOps") SubjectOps subjectOps){
		System.out.println(subjectOps.getDelSubjIds());
		try {
			long subjectId = subjectOps.getDelSubjIds()[0];
			//subjectRepository.deleteById(subjectId);
			subjectRepository.delete(subjectId);
			//this.subjectdao.deleteSubjectObject(subjectId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/subjects";
	}

	@RequestMapping(value = "/searchBook", method = RequestMethod.POST)
	public String searchBook(Locale locale, Model model,@ModelAttribute("bookOps") BookOps bookOps){
		List<Book> lstBooks = new ArrayList<>();
		try {
			long bookId = Long.parseLong(bookOps.getSearchBookId());
			//Optional<Book> bookOpt = bookRepository.findById(bookId);
			Book book =bookRepository.findOne(bookId);
			lstBooks.add(book);
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("lstBooks", lstBooks );
		model.addAttribute("bookOps", new BookOps() );
		return "books";
	}

	@RequestMapping(value = "/searchSubject", method = RequestMethod.POST)
	public String searchSubject(Locale locale, Model model,@ModelAttribute("subjectOps") SubjectOps subjectOps){
		List<Subject> lstSubjects = new ArrayList<>();
		try {
			long subjectId =Long.parseLong(subjectOps.getSearchSubjId());
		//Optional<Subject> subjectOpt = subjectRepository.findOne(subjectId); 
		Subject subject = subjectRepository.findOne(subjectId);
			//subjectdao.readSubjectObject(Long.parseLong(subjectOps.getSearchSubjId()));
		lstSubjects.add(subject);
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("lstSubjects", lstSubjects );
		model.addAttribute("subjectOps", new SubjectOps() );
		return "subjects";
	}

	@InitBinder("book")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,"publishDate", new CustomDateEditor(dateFormat, true));
	}

}
