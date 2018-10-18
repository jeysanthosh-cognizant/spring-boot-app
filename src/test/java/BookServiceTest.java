import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.WebApplication;
import com.dao.BookRepository;
import com.pojo.Book;
import com.pojo.BookOps;
import com.service.BookService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class BookServiceTest {
	
	@Autowired
	BookService bookService;
	
	@MockBean
	BookRepository<Book> bookRepository; 
	
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
		
		List<Book> lstBooks= new ArrayList<>();
		lstBooks.add(book1);
		lstBooks.add(book2);
		
		Mockito.when(bookRepository.findAll())
	      .thenReturn(lstBooks);
		
		Mockito.when(bookRepository.searchBookByTitle("SpringTest"))
	      .thenReturn(lstBooks);
		
		Mockito.when(bookRepository.findOne(121211L))
	      .thenReturn(book1);
	}
	
	
	@Test
	public void whenListAllBooks_thenReturnBooks() {
	    Iterable<Book> bookItr = bookService.listAllBooks();
	    List<Book> lstBooks = new ArrayList<>();
	    bookItr.forEach(lstBooks::add);
	    System.out.println(lstBooks);
	    boolean lstSucccess = false;
	    for(Book bookDB:lstBooks) {
	    	if(bookDB.getTitle().equals("SpringTest")) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Book listing is not correct", lstSucccess);
	}

	boolean saveCalled = false;
	
	@Test
	public void whenAddBook_thenAddBook() {
		Book book1 = new Book();
		book1.setBookId(121211);
		book1.setPrice(123123);
		book1.setPublishDate(new Date());
		book1.setTitle("SpringTest");
		book1.setVolume(12312);
		bookService.addBook(book1);
		ArgumentCaptor<Book> valueCapture = ArgumentCaptor.forClass(Book.class);
		//Mockito.when(bookrepo.save(book1)).save( valueCapture.capture());
		Mockito.verify(bookRepository).save(valueCapture.capture());
	    assertEquals("AddBook error", valueCapture.getValue().getBookId(), 121211L);
	}
	
	@Test
	public void whenDeleteBook_thenDeleteBook() {
		BookOps bookOps = new BookOps();
		long[] delBookIds = {121211L};
		bookOps.setDelBookIds(delBookIds);
		bookService.deleteBook(bookOps);
		ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
		Mockito.verify(bookRepository).delete( valueCapture.capture());
	    System.out.println("dsadsada>>>>>>"+valueCapture.getValue());
	    assertEquals("DeleteBook error", valueCapture.getValue().longValue(), 121211L);
	}
	
	@Test
	public void whenSearchBook_thenSearchBookByBookId() {
		BookOps bookOps = new BookOps();
		bookOps.setSearchBookId("121211");
		List<Book> lstBooks = bookService.searchBook(bookOps);
	    boolean lstSucccess = false;
	    for(Book bookDB:lstBooks) {
	    	if(bookDB.getBookId()==121211L) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Book listing is not correct", lstSucccess);
	}
	
	@Test
	public void whenSearchBook_thenSearchBookByBookTitle() {
		BookOps bookOps = new BookOps();
		bookOps.setSearchBookId("0");
		bookOps.setSearchBookTitle("SpringTest");
		List<Book> lstBooks = bookService.searchBook(bookOps);
	    boolean lstSucccess = false;
	    for(Book bookDB:lstBooks) {
	    	if(bookDB.getTitle().equals("SpringTest")) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Book listing is not correct", lstSucccess);
	}


}
