import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
import com.dao.SubjectRepository;
import com.pojo.Subject;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = WebApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SubjectRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private SubjectRepository<Subject> subjectRepo;
	
	@Test
	public void whenListAllSubject_thenReturnSubjects() {
		Subject subject = new Subject();
		subject.setSubjectId(121211);
		subject.setDurationInHours(10);
		subject.setSubtitle("SpringTest");
/*		Book book = new Book();
		book.setBookId(121211);
		book.setPrice(123123);
		book.setPublishDate(new Date());
		book.setTitle("SpringTest");
		book.setVolume(12312);
*/		entityManager.persist(subject);
	    entityManager.flush();
	    Iterable<Subject> subjItr = subjectRepo.findAll();
	    List<Subject> lstSubjects = new ArrayList<>();
	    subjItr.forEach(lstSubjects::add);
	    System.out.println(lstSubjects);
	    boolean lstSucccess = false;
	    for(Subject subjectDB:lstSubjects) {
	    	if(subjectDB.getSubtitle().equals("SpringTest")) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Subject listing is not correct", lstSucccess);
	}
	
	
	@Test
	public void whenDeleteSubject_thenDeleteSubject() {
		Subject subject = new Subject();
		subject.setSubjectId(121211);
		subject.setDurationInHours(10);
		subject.setSubtitle("SpringTest");
		entityManager.persist(subject);
	    entityManager.flush();
		long subjId = 121211;
	    subjectRepo.delete(subjId);
	    Iterable<Subject> subjItr = subjectRepo.findAll();
	    List<Subject> lstSubjects = new ArrayList<>();
	    subjItr.forEach(lstSubjects::add);
	    System.out.println(lstSubjects);
	    boolean lstSucccess = false;
	    for(Subject subjectDB:lstSubjects) {
	    	if(subjectDB.getSubtitle().equals("SpringTest")) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertFalse("Subject not deleted", lstSucccess);
	}

	
	@Test
	public void whenAddSubject_thenSubject() {
		Subject subject = new Subject();
		subject.setSubjectId(121211);
		subject.setDurationInHours(10);
		subject.setSubtitle("SpringTest");
		entityManager.persist(subject);
	    entityManager.flush();
	    Iterable<Subject> subjItr = subjectRepo.findAll();
	    List<Subject> lstSubjects = new ArrayList<>();
	    subjItr.forEach(lstSubjects::add);
	    System.out.println(lstSubjects);
	    boolean lstSucccess = false;
	    for(Subject subjectDB:lstSubjects) {
	    	if(subjectDB.getSubtitle().equals("SpringTest")) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Subject not added", lstSucccess);
	}
	
	
	
	@Test
	public void whenSearchSubject_thenSearchSubject() {
		Subject subject = new Subject();
		subject.setSubjectId(121211);
		subject.setDurationInHours(10);
		subject.setSubtitle("SpringTest");
		entityManager.persist(subject);
	    entityManager.flush();
		long subjId = 121211;
		Subject subjSearch = subjectRepo.findOne(subjId);
	    boolean lstSucccess = false;
	    if(subjSearch.getSubtitle().equals("SpringTest")) {
	    	lstSucccess = true;
	    }
	    assertTrue("Subject not found", lstSucccess);
	}
	
	
}
