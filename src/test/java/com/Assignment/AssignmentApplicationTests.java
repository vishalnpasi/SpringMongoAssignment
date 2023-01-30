package com.Assignment;

import com.Assignment.models.UserModel;
import com.Assignment.repository.UserRepository;
import com.Assignment.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

//import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentApplicationTests {

	@Autowired
	private UserService service;
	@MockBean
	private UserRepository repository;

	@Test
	public void createUserTest(){
		UserModel user = new UserModel("1","vishal"," vishal pasi", "vishal@gmail",
						"9191919191","Albnero");

		when(repository.save(user)).thenReturn(user);
		assertEquals(user,service.createUser(user).getBody());
		assertEquals(HttpStatus.CREATED,service.createUser(user).getStatusCode());
	}
	@Test
	public void getUsersTest(){
		when(repository.findAll()).thenReturn(List.of(new UserModel("1","vishal"," vishal pasi",
				"vishal@gmail","9191919191","Albnero"),new UserModel("2","pasi",
				"pasi pasi","pasi@gmail","9090909090","Albnero")));

		assertEquals(2,service.getUsers().getBody().size());
		assertEquals(HttpStatus.OK,service.getUsers().getStatusCode());
	}
	@Test
	public void updateUserTest() throws Exception {
		String userName = "visha";
		UserModel user = new UserModel("1","vishal"," vishal pasi",
				"vishal@gmail","9191919191","Albnero");
//		when(repository.findUser(userName)).thenReturn(List.of(user));
//		when(repository.save(user)).thenReturn(user);
//		UserModel obj = service.updateUser(userName);
//		System.out.print(obj.getFullName());
//		assertEquals(user,service.updateUser(userName));
//		assertEquals(HttpStatus.OK , service.updateUser(userName).getStatusCode());

		when(repository.findByUserName(userName)).thenReturn((List.of(user)));
		when(repository.save(user)).thenReturn((user));

		ResponseEntity<String> str = service.updateUser(userName);
		assertEquals("Updated Successfully",str.getBody());
		assertEquals(HttpStatus.OK,str.getStatusCode());
	}
	@Test
	public void deleteUser() throws Exception {
		String userName = "vishal";
		UserModel user = new UserModel("1","vishal"," vishal pasi", "vishal@gmail",
				"9191919191","Albnero");

		when(repository.findByUserName(userName)).thenReturn(List.of(user));
		service.deleteByUserName(userName);
		verify(repository,times(1)).deleteById("1");
		assertEquals(HttpStatus.OK,service.updateUser(userName).getStatusCode());
	}
}
