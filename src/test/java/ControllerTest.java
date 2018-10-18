import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import com.WebApplication;
import com.executor.WebSecurityConfig;
import com.pojo.Book;
import com.pojo.BookOps;
import com.pojo.RegisterForm;
import com.pojo.Subject;
import com.pojo.SubjectOps;
import com.service.BookService;
import com.service.SubjectService;
import com.service.UserService;
import com.spring.mvc.HomeController;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {HomeController.class}, secure=false)
@ContextConfiguration(classes = WebApplication.class)
public class ControllerTest {
	
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private BookService bookService;
    

    @MockBean
    private SubjectService subjectService;
    
    @MockBean
    private UserService userService;
    
    List<Book> lstBooks= new ArrayList<>();
    
    @Before
	public void setUp() {
    	Book book1 = new Book();
		book1.setBookId(121211);
		book1.setPrice(123123);
		book1.setPublishDate(new Date());
		book1.setTitle("SpringTest");
		book1.setVolume(12312);
	 
		Book book2 = new Book();
		book2.setBookId(131311);
		book2.setPrice(133133);
		book2.setPublishDate(new Date());
		book2.setTitle("SpringTest3");
		book2.setVolume(10);
		
		List<Book> lstBooks1= new ArrayList<>();
		lstBooks1.add(book1);
		
		List<Book> lstBooks2= new ArrayList<>();
		lstBooks2.add(book2);
		
		//List<Book> lstBooks= new ArrayList<>();
		lstBooks.add(book1);
		lstBooks.add(book2);
		
		List<Subject> lstSubj = new ArrayList<>();

		Subject subject = new Subject();
		subject.setSubjectId(121211);
		subject.setDurationInHours(30);
		subject.setSubtitle("SpringTest");

		
		Subject subj1 = new Subject();
		subj1.setSubjectId(3432432);
		subj1.setDurationInHours(10);
		subj1.setSubtitle("SpringTest1");

		Set<Book> bookSet = new HashSet<>(); 
		bookSet.add(book1);
		bookSet.add(book2);
		
		subject.setReferences(bookSet);
		subj1.setReferences(bookSet);
		
		lstSubj.add(subject);
		lstSubj.add(subj1);


     	BDDMockito.given(bookService.listAllBooks()).willReturn(lstBooks);
     	BDDMockito.given(subjectService.listAllSubjects()).willReturn(lstSubj);
     	 
    }
    
    @Test
    public void testlistAllBooks() throws Exception{
    	
      	MvcResult result = mvc.perform(get("/books")
      		      .contentType(MediaType.APPLICATION_JSON))
      		      .andExpect(status().isOk()).andReturn();
      		     // .andExpect(jsonPath("$[0].title", is("SpringTest")));
      	List<Book> resultBooks = (List<Book>)result.getModelAndView().getModel().get("lstBooks");
      	
	    boolean lstSucccess = false;
	    for(Book bookDB:resultBooks) {
	    	if(bookDB.getTitle().equals("SpringTest")) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Book listing is not correct", lstSucccess);
	    assertTrue("Book listing is not correct",result.getModelAndView().getViewName().equals("books"));

        
    }
    
    @Test
    public void testAddBook() throws Exception{
     	Book book1 = new Book();
    		book1.setBookId(121211);
    		book1.setPrice(123123);
    		book1.setPublishDate(new Date());
    		book1.setTitle("SpringTest");
    		book1.setVolume(12312);
    		
      		 RequestBuilder request = post("/addBook")
 			        .param("bookId", book1.getBookId()+"")
 			        .param("price", book1.getPrice()+"")
 			        .param("publishDate", "10/10/2018")
 			       .param("title",book1.getTitle())
 			       .param("volume",book1.getVolume()+"").
 			       contentType(MediaType.APPLICATION_JSON);

    		
      	MvcResult result = mvc.perform(request)
      		      .andExpect(status().isOk()).andReturn();
      		     // .andExpect(jsonPath("$[0].title", is("SpringTest")));
      	List<Book> resultBooks = (List<Book>)result.getModelAndView().getModel().get("lstBooks");
      	
	    boolean lstSucccess = false;
	    for(Book bookDB:resultBooks) {
	    	if(bookDB.getTitle().equals("SpringTest")) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Book add is not correct", lstSucccess);
	    assertTrue("Book add is not correct",result.getModelAndView().getViewName().equals("books"));

        
    }
    
    @Test
    public void testDeleteBook() throws Exception{
     	Book book1 = new Book();
    		book1.setBookId(121211);
    		book1.setPrice(123123);
    		book1.setPublishDate(new Date());
    		book1.setTitle("SpringTest");
    		book1.setVolume(12312);
    		
    		 RequestBuilder request = post("/deleteBook")
    			        .param("bookId", book1.getBookId()+"")
    			        .param("delBookIds", book1.getBookId()+"");
    		 		
    	//		        .with(csrf());
    			
    		
      	MvcResult result = mvc.perform(request)
      		     // .contentType(MediaType.APPLICATION_JSON))
      		      .andExpect(status().is3xxRedirection()).andReturn();
      		     // .andExpect(jsonPath("$[0].title", is("SpringTest")));
      	
      	ArgumentCaptor<BookOps> valueCapture = ArgumentCaptor.forClass(BookOps.class);
		Mockito.verify(bookService).deleteBook(valueCapture.capture());
		 assertEquals("AddBook error", valueCapture.getValue().getDelBookIds()[0], 121211L);

    }
    

    
    @Test
    public void testlistAllSubject() throws Exception{
    	
      	MvcResult result = mvc.perform(get("/subjects")
      		      .contentType(MediaType.APPLICATION_JSON))
      		      .andExpect(status().isOk()).andReturn();
      		     // .andExpect(jsonPath("$[0].title", is("SpringTest")));
      	List<Subject> resultSubject = (List<Subject>)result.getModelAndView().getModel().get("lstSubjects");
      	
	    boolean lstSucccess = false;
	    for(Subject subjectDB:resultSubject) {
	    	if(subjectDB.getSubtitle().equals("SpringTest")) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Subject listing is not correct", lstSucccess);
	    assertTrue("Subject listing is not correct",result.getModelAndView().getViewName().equals("subjects"));

        
    }
    
    @Test
    public void testAddSubject() throws Exception{
		Subject subject = new Subject();
		subject.setSubjectId(121211);
		subject.setDurationInHours(30);
		subject.setSubtitle("SpringTest");
    		
      		 RequestBuilder request = post("/addSubject")
 			        .param("setSubjectId", subject.getSubjectId()+"")
 			        .param("setDurationInHours", subject.getDurationInHours()+"")
 			        .param("setSubtitle", subject.getSubtitle())
 			         .contentType(MediaType.APPLICATION_JSON);

    		
      	MvcResult result = mvc.perform(request)
      		      .andExpect(status().is3xxRedirection()).andReturn();
        
    }
    
    @Test
    public void testDeleteSubject() throws Exception{
    		SubjectOps subjectOps = new SubjectOps();
    		subjectOps.setDelSubjIds(new long[] {121211L});
    		 RequestBuilder request = post("/deleteSubject")
    			        .param("delSubjIds", subjectOps.getDelSubjIds()[0]+"");
    			
    		
      	MvcResult result = mvc.perform(request)
      		     // .contentType(MediaType.APPLICATION_JSON))
      		      .andExpect(status().is3xxRedirection()).andReturn();
      		     // .andExpect(jsonPath("$[0].title", is("SpringTest")));
      	
      	ArgumentCaptor<SubjectOps> valueCapture = ArgumentCaptor.forClass(SubjectOps.class);
		Mockito.verify(subjectService).delteSubject(valueCapture.capture());
		 assertEquals("Delete Subject error", valueCapture.getValue().getDelSubjIds()[0], 121211L);

    }
    
    @Test
    public void testRegisterUser() throws Exception{
		RegisterForm registerForm = new RegisterForm();
		registerForm.setUserName("Test");
		registerForm.setPassword("Test");
		registerForm.setRole("ROLE_PRINCIPAL");
    		
      		 RequestBuilder request = post("/register")
 			        .param("userName", registerForm.getUserName())
 			        .param("password", registerForm.getPassword())
 			        .param("role", registerForm.getRole())
 			         .contentType(MediaType.APPLICATION_JSON);

    		
      	MvcResult result = mvc.perform(request)
      		      .andExpect(status().isOk()).andReturn();
      	assertEquals("registeration failed", result.getModelAndView().getViewName(),"registerSuccess");
        
    }
    
//    @Test
//    public void testLogin() throws Exception{
//    	RequestBuilder requestBuilder = formLogin().user("Cyril").password("123");
//    	mvc.perform(requestBuilder)
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(cookie().exists("JSESSIONID"));
//    }

}
