package com.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.dao.RoleRepository;
import com.dao.UserRepository;
import com.pojo.RegisterForm;
import com.pojo.Role;
import com.pojo.User;

@Service
public class UserService {
	
	@Autowired(required=true)
	UserRepository<User> userRepository;
	
	@Autowired(required=true)
	RoleRepository<Role> roleRepository;

	public void registerUser(RegisterForm registerForm) {
		  User user = new User();
		  Role role = new Role();
		  user.setUsername(registerForm.getUserName());
		  user.setPassword(registerForm.getPassword());
		  user.setEnabled(true);
		  role.setUsername(registerForm.getUserName());
		  role.setRole(registerForm.getRole());
		  userRepository.save(user);
		  roleRepository.save(role);

		}
	


}
