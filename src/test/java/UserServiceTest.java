import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.WebApplication;
import com.dao.RoleRepository;
import com.dao.UserRepository;
import com.pojo.RegisterForm;
import com.pojo.Role;
import com.pojo.User;
import com.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class UserServiceTest {
	

	@Autowired
	UserService userService;
	
	@MockBean
	UserRepository<User> userRepository; 

	@MockBean
	RoleRepository<Role> roleRepository; 
	
	@Test
	public void whenRegisterUser_thenRegisterUser() {
		RegisterForm registerForm = new RegisterForm();
		registerForm.setUserName("Test");
		registerForm.setPassword("Test");
		registerForm.setRole("ROLE_PRINCIPAL");
		userService.registerUser(registerForm);
		ArgumentCaptor<User> valueCapture = ArgumentCaptor.forClass(User.class);
		//Mockito.when(bookrepo.save(book1)).save( valueCapture.capture());
		Mockito.verify(userRepository).save(valueCapture.capture());
	    assertEquals("Register user error", valueCapture.getValue().getUsername(), "Test");
	    ArgumentCaptor<Role> valueCaptureRole = ArgumentCaptor.forClass(Role.class);
		//Mockito.when(bookrepo.save(book1)).save( valueCapture.capture());
		Mockito.verify(roleRepository).save(valueCaptureRole.capture());
	    assertEquals("Register role error", valueCapture.getValue().getUsername(), "Test");
	    
	}
	
}
