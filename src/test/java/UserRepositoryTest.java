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
import com.dao.UserRepository;
import com.pojo.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = WebApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository<User> userRepo;
	


	
	@Test
	public void whenAddSubject_thenSubject() {
		User user = new User();
		user.setUsername("Test");
		user.setPassword("Test");
		user.setEnabled(true);
		//entityManager.persist(user);
	    //entityManager.flush();
	    userRepo.save(user);
	    Iterable<User> userItr = userRepo.findAll();
	    List<User> lstUsers = new ArrayList<>();
	    userItr.forEach(lstUsers::add);
	    System.out.println(lstUsers);
	    boolean lstSucccess = false;
	    for(User userDB:lstUsers) {
	    	if(userDB.getUsername().equals("Test")) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("User not added", lstSucccess);
	}
	
	
	
	
}
