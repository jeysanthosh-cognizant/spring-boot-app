import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.dao.SubjectRepository;
import com.pojo.Book;
import com.pojo.Subject;
import com.pojo.SubjectOps;
import com.service.SubjectService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class SubjectServiceTest {
	@Autowired
	SubjectService subjecService;
	
	@MockBean
	SubjectRepository<Subject> subjRepository; 
	
	
	@Before
	public void setUp() {
		
		List<Subject> lstSubj = new ArrayList<>();
		List<Subject> lstSearchSubj = new ArrayList<>();

		Subject subject = new Subject();
		subject.setSubjectId(121211);
		subject.setDurationInHours(30);
		subject.setSubtitle("SpringTest");

		
		Subject subj1 = new Subject();
		subj1.setSubjectId(3432432);
		subj1.setDurationInHours(10);
		subj1.setSubtitle("SpringTest1");

		
		Set<Book> bookSet = new HashSet<>(); 
		
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
		
		bookSet.add(book1);
		bookSet.add(book2);
		
		subject.setReferences(bookSet);
		subj1.setReferences(bookSet);
		
		lstSubj.add(subject);
		lstSubj.add(subj1);
		
		lstSearchSubj.add(subj1);
		
		Mockito.when(subjRepository.findAll())
	      .thenReturn(lstSubj);
		
		Mockito.when(subjRepository.searchSubjectByDuration(10))
	      .thenReturn(lstSearchSubj);
		
		Mockito.when(subjRepository.findOne(121211L))
	      .thenReturn(subject);
	}
	
	
	@Test
	public void whenListAllSubject_thenListSubjects() {
	    Iterable<Subject> subItr = subjecService.listAllSubjects();
	    List<Subject> lstSubject = new ArrayList<>();
	    subItr.forEach(lstSubject::add);
	    System.out.println(lstSubject);
	    boolean lstSucccess = false;
	    for(Subject subjectDB:lstSubject) {
	    	if(subjectDB.getSubtitle().equals("SpringTest")) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Subject listing is not correct", lstSucccess);
	}

	
	@Test
	public void whenAddBook_thenAddBook() {
		Subject subject = new Subject();
		subject.setSubjectId(121211);
		subject.setDurationInHours(30);
		subject.setSubtitle("SpringTest");

		Book book1 = new Book();
		book1.setBookId(121211);
		book1.setPrice(123123);
		book1.setPublishDate(new Date());
		book1.setTitle("SpringTest");
		book1.setVolume(12312);
		Set<Book> bookSet = new HashSet<>(); 
		
		bookSet.add(book1);
		subject.setReferences(bookSet);
		subjecService.addSubject(subject);
		
		ArgumentCaptor<Subject> valueCapture = ArgumentCaptor.forClass(Subject.class);
		//Mockito.when(bookrepo.save(book1)).save( valueCapture.capture());
		Mockito.verify(subjRepository).save(valueCapture.capture());
	    assertEquals("Add Subject error", valueCapture.getValue().getSubjectId(), 121211L);
	}
	
	@Test
	public void whenDeleteSubject_thenDeleteSubject() {
		long[] delSubjIds = {121211L};
		SubjectOps subjOps = new SubjectOps();
		subjOps.setDelSubjIds(delSubjIds);
		subjecService.delteSubject(subjOps);
		ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
		Mockito.verify(subjRepository).delete( valueCapture.capture());
	    System.out.println("dsadsada>>>>>>"+valueCapture.getValue());
	    assertEquals("Delete Subject error", valueCapture.getValue().longValue(), 121211L);
	}
	
	@Test
	public void whenSearchSubject_thenSearchSubjectById() {
		SubjectOps subjOps = new SubjectOps();
		subjOps.setSearchSubjId("121211");
		List<Subject> lstSubj = subjecService.searchSubject(subjOps);
	    boolean lstSucccess = false;
	    for(Subject subjectDB:lstSubj) {
	    	if(subjectDB.getSubjectId()==121211L) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Subject listing is not correct", lstSucccess);
	}
	
	@Test
	public void whenSearchBook_thenSearchBookByDuration() {
		SubjectOps subjOps = new SubjectOps();
		subjOps.setSearchDuration(10);
		List<Subject> lstSubj = subjecService.searchSubject(subjOps);
	    boolean lstSucccess = false;
	    for(Subject subjectDB:lstSubj) {
	    	if(subjectDB.getSubjectId()==3432432L) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Book listing is not correct", lstSucccess);
	}

}
