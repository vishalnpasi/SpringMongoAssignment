package com.Assignment.SpringIntegretionTest;
import com.Assignment.models.UserModel;
import com.Assignment.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import static org.junit.Assert.assertEquals;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.util.List;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringIntegrationTest {
    @LocalServerPort
    private int port;
    @Autowired
//    private WebTestClient webTestClient;
    private WebClient.Builder webTestClient;
    @MockBean
    private UserRepository repository;
    private UserModel user = new UserModel("123","demo"," vishal pasi", "vishal@gmail",
            "9191919191","Albnero");
    @Test
    public void exampleTest() {
        ResponseEntity<?> response = webTestClient
                .baseUrl("http://localhost:"+port)
                .build()
                .get()
                .uri("/demo")
                .retrieve().toEntity(String.class).block();
        System.out.println(response.getBody());
    }
    @Test
    public void createUserTest(){
        when(repository.save(user)).thenReturn(user);

        ResponseEntity<UserModel> response = webTestClient
                .baseUrl("http://localhost:"+port)
                .build()
                .post()
                .uri("/user")
                .bodyValue(user)
                .retrieve().toEntity(UserModel.class).block();
        assertEquals(response.getBody().getFullName(),user.getFullName());
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }
    @Test
    public void getUsersTest(){
        when(repository.findAll()).thenReturn(List.of(user,user,user));

        ResponseEntity<List<UserModel>> response = webTestClient
                .baseUrl("http://localhost:"+port)
                .build()
                .get()
                .uri("/user")
                .retrieve().toEntityList(UserModel.class).block();
        assertEquals(3,response.getBody().size());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void updateUserTest(){
        String userName="vishal";

        when(repository.findByUserName(userName)).thenReturn(List.of(user));
        when(repository.save(user)).thenReturn(user);

        ResponseEntity<String> response = webTestClient
                .baseUrl("http://localhost:"+port)
                .build()
                .put()
                .uri("/user/{userName}",userName)
                .retrieve().toEntity(String.class).block();

        assertEquals(response.getBody(),"Updated Successfully");
        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }
    @Test
    public void deleteUserTest(){
        String userName="vishal";

        when(repository.findByUserName(userName)).thenReturn(List.of(user));
        when(repository.save(user)).thenReturn(user);

        ResponseEntity<String> response = webTestClient
                .baseUrl("http://localhost:"+port)
                .build()
                .delete()
                .uri("/user/{userName}",userName)
                .retrieve().toEntity(String.class).block();

        assertEquals(response.getBody(),"Deleted Successfully");
        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }
}




//            webTestClient.get()
//                .uri("/demo")
//                .exchange().expectStatus().isOk()
//                .expectBody(String.class)
//                .isEqualTo("hello");
//
//        WebClient webClient = WebClient.create();
//        String responseJson = webClient.get()
//                .uri("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
//                .exchange()
//                .block()
//                .bodyToMono(String.class)
//                .block();

//        webTestClient.post()
//                .uri("/demo")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(new LoginDetails(username, password))
//                .exchange()
//                .expectHeader()
//                .exists(HttpHeaders.AUTHORIZATION)
//                .returnResult(Map.class);



//        webTestClient.post()
//                .uri("/user")
//                .bodyValue(user)
//                .exchange()
////                        .returnResult(UserModel.class)
//                .expectStatus().isCreated()
//                .expectBody(ResponseEntity.class);
