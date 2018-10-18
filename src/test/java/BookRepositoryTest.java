import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.WebApplication;
import com.dao.BookRepository;
import com.pojo.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = WebApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BookRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
	@Autowired
	private BookRepository<Book> bookrepo;
	
	
	@Test
	public void whenListAllBooks_thenReturnBooks() {
		Book book = new Book();
		book.setBookId(121211);
		book.setPrice(123123);
		book.setPublishDate(new Date());
		book.setTitle("SpringTest");
		book.setVolume(12312);
		entityManager.persist(book);
	    entityManager.flush();
	    Iterable<Book> bookItr = bookrepo.findAll();
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
	
	
	@Test
	public void whenDeleteBooks_thenDeleteBook() {
		Book book = new Book();
		book.setBookId(121211);
		book.setPrice(123123);
		book.setPublishDate(new Date());
		book.setTitle("SpringTest");
		book.setVolume(12312);
		entityManager.persist(book);
	    entityManager.flush();
		long bookId = 121211;
	    bookrepo.delete(bookId);
	    Iterable<Book> bookItr = bookrepo.findAll();
	    List<Book> lstBooks = new ArrayList<>();
	    bookItr.forEach(lstBooks::add);
	    System.out.println(lstBooks);
	    boolean lstSucccess = false;
	    for(Book bookDB:lstBooks) {
	    	if(bookDB.getBookId()==bookId) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertFalse("Book not deleted", lstSucccess);
	}

	
	@Test
	public void whenAddBooks_thenAddBooks() {
		Book book = new Book();
		book.setBookId(121211);
		book.setPrice(123123);
		book.setPublishDate(new Date());
		book.setTitle("SpringTest");
		book.setVolume(12312);
		entityManager.persist(book);
	    entityManager.flush();
	    Iterable<Book> bookItr = bookrepo.findAll();
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
	    assertTrue("Book not added", lstSucccess);
	}
	
	
	
	@Test
	public void whenSearchBook_thenSearchBook() {
		Book book = new Book();
		book.setBookId(121211);
		book.setPrice(123123);
		book.setPublishDate(new Date());
		book.setTitle("SpringTest");
		book.setVolume(12312);
		entityManager.persist(book);
	    entityManager.flush();
	    long bookId =121211;
	    Book bookSearch = bookrepo.findOne(bookId);
	    boolean lstSucccess = false;
	    if(bookSearch.getTitle().equals("SpringTest")) {
	    	lstSucccess = true;
	    }
	    assertTrue("Book not found", lstSucccess);
	}
	
	
}
