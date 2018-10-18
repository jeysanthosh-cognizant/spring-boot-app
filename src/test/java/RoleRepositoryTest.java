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
import com.dao.RoleRepository;
import com.dao.SubjectRepository;
import com.dao.UserRepository;
import com.pojo.Role;
import com.pojo.Subject;
import com.pojo.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = WebApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RoleRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private RoleRepository<Role> roleRepo;
	


	
	@Test
	public void whenAddSubject_thenSubject() {
		Role role = new Role();
		role.setUsername("Test");
		role.setRole("ROLE_PRINCIPAL");
		//entityManager.persist(user);
	    //entityManager.flush();
	    roleRepo.save(role);
	    Iterable<Role> roleItr = roleRepo.findAll();
	    List<Role> lstRoles = new ArrayList<>();
	    roleItr.forEach(lstRoles::add);
	    System.out.println(lstRoles);
	    boolean lstSucccess = false;
	    for(Role roleDB:lstRoles) {
	    	if(roleDB.getUsername().equals("Test")) {
	    		lstSucccess = true;
	    		break;
	    	}
	    }
	    assertTrue("Role not added", lstSucccess);
	}
	
	
	
	
}
