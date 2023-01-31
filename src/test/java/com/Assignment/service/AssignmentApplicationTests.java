package com.Assignment.service;

import com.Assignment.models.UserModel;
import com.Assignment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentApplicationTests {

	private String userName = "vishal";
	private UserModel user = new UserModel("1","vishal"," vishal pasi", "vishal@gmail",
			"9191919191","Albnero");
	@Autowired
	private UserService service;
	@MockBean
	private UserRepository repository;
	@BeforeEach
	public  void MockFun(){
		when(repository.findAll()).thenReturn(List.of(user,user));
		when(repository.save(user)).thenReturn(user);
		when(repository.findByUserName(userName)).thenReturn((List.of(user)));
	}
	@Test
	public void createUserTest(){
		assertEquals(user,service.createUser(user));
	}
	@Test
	public void getUsersTest(){
		assertEquals(2,service.getUsers().size());
	}
	@Test
	public void updateUserTest() throws Exception {
		assertEquals("Updated Successfully",service.updateUser(userName));
	}
	@Test
	public void deleteUser() throws Exception {
		service.deleteByUserName(userName);
		verify(repository,times(1)).deleteById("1");
	}
}





//package com.SpringIntegrationTest;
//
//import com.Assignment.controller.UserController;
//import com.Assignment.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientExcludeFilter;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import java.util.stream.Stream;
//
//import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
//@RunWith(SpringRunner.class)
//@SpringBootConfiguration
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestConfiguration
//public class SpringIntegretionTest {
//
//
//    @Autowired
//    private WebTestClient webTestClient;
////    @MockBean
////    private UserRepository repository;
//
//    @Test
//    public void exampleTest() {
//            webTestClient.get()
//                .uri("/demo")
//                .exchange().expectStatus().isOk();
////                .expectBody(String.class)
////                .isEqualTo("hello");
//
////        webTestClient.post()
////                .uri("/demo")
////                .contentType(MediaType.APPLICATION_JSON)
////                .bodyValue(new LoginDetails(username, password))
////                .exchange()
////                .expectHeader()
////                .exists(HttpHeaders.AUTHORIZATION)
////                .returnResult(Map.class);
//    }
////    @Test
////    void shouldReturnNotFoundForUnknownUserId() {
////        this.webTestClient
////                .get()
////                .uri("/users", 42)
////                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
////                .exchange()
////                .expectStatus()
////                .isEqualTo(NOT_FOUND);
////    }
//}





//package com.Assignment;
//
//import com.Assignment.models.UserModel;
//import com.Assignment.repository.UserRepository;
//import com.Assignment.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@SpringBootConfiguration
//class SprintIntegrationTest {
//
//    private String userName = "vishal";
//    private UserModel user = new UserModel("1","vishal"," vishal pasi", "vishal@gmail",
//            "9191919191","Albnero");
//    @Autowired
//    private UserService service;
//    //    @Autowired
////    private WebTestClient webTestClient;
//    @MockBean
//    private UserRepository repository;
//
//    @Test
//    public void createUserTest(){
//        when(repository.save(user)).thenReturn(user);
//        assertEquals(user,service.createUser(user));
//
////        webTestClient.get()
////                .uri("/demo")
////                .exchange().expectStatus().isOk()
////                .expectBody(String.class)
////                .isEqualTo("hello");
//    }
//    @Test
//    public void getUsersTest(){
//        when(repository.findAll()).thenReturn(List.of(user,user));
//        assertEquals(2,service.getUsers().size());
//    }
//    @Test
//    public void updateUserTest() throws Exception {
//        when(repository.findByUserName(userName)).thenReturn((List.of(user)));
//        when(repository.save(user)).thenReturn((user));
//        assertEquals("Updated Successfully",service.updateUser(userName));
//    }
//    @Test
//    public void deleteUser() throws Exception {
//        when(repository.findByUserName(userName)).thenReturn(List.of(user));
//        service.deleteByUserName(userName);
//        verify(repository,times(1)).deleteById("1");
//    }
//}
//
//
