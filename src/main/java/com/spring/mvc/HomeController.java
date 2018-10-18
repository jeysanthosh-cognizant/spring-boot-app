package com.spring.mvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pojo.Book;
import com.pojo.BookOps;
import com.pojo.RegisterForm;
import com.pojo.Subject;
import com.pojo.SubjectOps;
import com.service.BookService;
import com.service.SubjectService;
import com.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

/*	@Autowired(required = true)
	BookRepository<Book> bookRepository;
	
	@Autowired(required=true)
	SubjectRepository<Subject> subjectRepository;
	
	@Autowired(required=true)
	UserRepository<User> userRepository;
	
	@Autowired(required=true)
	RoleRepository<Role> roleRepository;
*/	
	@Autowired(required=true)
	BookService bookService;
	
	@Autowired(required=true)
	SubjectService subjectService;
	
	@Autowired(required=true)
	UserService userService;
	
	
	

/*	@Autowired(required = true)
	SubjectDaoImpl subjectdao;
*/
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale,Authentication authentication) {
		System.out.println("in a mvc controller>>>>>>>>>");
		//logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		if(authentication!=null){
			Collection<?extends GrantedAuthority> granted =authentication.getAuthorities();

			for(int i=0;i<granted.size();i++){
				String role = granted.toArray()[i] + "";
				System.out.println(role);

			}
		}

		//model.addAttribute("serverTime", formattedDate );

		return "home";
	}

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String listAllBooks(Locale locale, Model model){
		/*Iterable<Book> lstItr = bookRepository.findAll();
		List<Book> lstBooks = new ArrayList<>();
		lstItr.forEach(lstBooks::add);*/
		List<Book> lstBooks = bookService.listAllBooks();
		model.addAttribute("lstBooks", lstBooks );
		model.addAttribute("bookOps", new BookOps() );
		return "books";
	}

	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	public String listAllSubjects(Locale locale, Model model){
		System.out.println(">>>>>>>>>>>>>In sujects");
		//List<Subject> lstSubjects = subjectdao.getSubjectList();
/*		Iterable<Subject> lstItr = subjectRepository.findAll();
		List<Subject> lstSubjects = new ArrayList<>();
		lstItr.forEach(lstSubjects::add);
		Set<Book> bookset = lstSubjects.get(0).getReferences();
		System.out.println(">>>>>>>>>>>>>"+bookset.size());
*/		List<Subject> lstSubjects = subjectService.listAllSubjects();
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
		/*try {
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
*/		bookService.addBook(book);
		List<Book> lstBooks = bookService.listAllBooks();
		modelMap.addAttribute("lstBooks", lstBooks );
		modelMap.addAttribute("bookOps", new BookOps() );
		return "books";
	}

	@RequestMapping(value = "/addSubject", method = RequestMethod.POST)
	public String addSubject(@ModelAttribute("subject")Subject subject,Model modelMap){
		/*try {
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
		}*/
		subjectService.addSubject(subject);
		return "redirect:/subjects";
	}

	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
	public String deleteBook(Locale locale, Model model,@ModelAttribute("bookOps") BookOps bookOps){
		System.out.println(bookOps.getDelBookIds());
		/*try {
			long bookId = bookOps.getDelBookIds()[0];
			//this.bookdao.deleteBookObject(bookId);
			bookRepository.delete(bookId);
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		bookService.deleteBook(bookOps);
		return "redirect:/books";
	}

	@RequestMapping(value = "/deleteSubject", method = RequestMethod.POST)
	public String delteSubject(Locale locale, Model model,@ModelAttribute("subjectOps") SubjectOps subjectOps){
		System.out.println(subjectOps.getDelSubjIds());
		/*try {
			long subjectId = subjectOps.getDelSubjIds()[0];
			//subjectRepository.deleteById(subjectId);
			subjectRepository.delete(subjectId);
			//this.subjectdao.deleteSubjectObject(subjectId);
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		subjectService.delteSubject(subjectOps);
		return "redirect:/subjects";
	}

	@RequestMapping(value = "/searchBook", method = RequestMethod.POST)
	public String searchBook(Locale locale, Model model,@ModelAttribute("bookOps") BookOps bookOps){
		List<Book> lstBooks = new ArrayList<>();
		/*try {
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
		}*/
		lstBooks = bookService.searchBook(bookOps);
		model.addAttribute("lstBooks", lstBooks );
		model.addAttribute("bookOps", new BookOps() );
		return "books";
	}

	@RequestMapping(value = "/searchSubject", method = RequestMethod.POST)
	public String searchSubject(Locale locale, Model model,@ModelAttribute("subjectOps") SubjectOps subjectOps){
		List<Subject> lstSubjects = new ArrayList<>();
		/*try {
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
		}*/
		lstSubjects = subjectService.searchSubject(subjectOps);
		model.addAttribute("lstSubjects", lstSubjects );
		model.addAttribute("subjectOps", new SubjectOps() );
		return "subjects";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

	  ModelAndView model = new ModelAndView();
	  if (error != null) {
		model.addObject("error", "Invalid username and password!");
	  }

	  if (logout != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }
	  model.setViewName("login");

	  return model;

	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

	  ModelAndView model = new ModelAndView();
		
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
		
	  model.setViewName("403");
	  return model;

	}
	
	@RequestMapping(value = "/registerForm", method = RequestMethod.GET)
	public ModelAndView registerForm(Locale locale) {

		  ModelAndView model = new ModelAndView();
		  model.addObject("registerForm", new RegisterForm());
		  model.setViewName("registerForm");
		  return model;

		}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(Locale locale,@ModelAttribute("registerForm") RegisterForm registerForm) {

		  ModelAndView model = new ModelAndView();
/*		  User user = new User();
		  Role role = new Role();
		  user.setUsername(registerForm.getUserName());
		  user.setPassword(registerForm.getPassword());
		  user.setEnabled(true);
		  role.setUsername(registerForm.getUserName());
		  role.setRole(registerForm.getRole());
		  userRepository.save(user);
		  roleRepository.save(role);
*/		  userService.registerUser(registerForm);
		  model.setViewName("registerSuccess");
		  return model;

		}
	

	@InitBinder("book")
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class,"publishDate", new CustomDateEditor(dateFormat, true));
	}

}
