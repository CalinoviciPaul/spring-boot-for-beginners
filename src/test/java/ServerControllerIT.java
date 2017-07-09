import com.in28minutes.springboot.Application;
import com.in28minutes.springboot.jpa.User;
import com.in28minutes.springboot.model.Question;
import com.in28minutes.springboot.model.Survey;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IrianLaptop on 7/8/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerControllerIT {

    @LocalServerPort
    private int port;

    HttpHeaders headers = new HttpHeaders();

    private TestRestTemplate template = new TestRestTemplate();

    @Before
    public void setupJSONAcceptType() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }


    @Test
    public void test(){
        String url = createUrl("/surveys/Survey1/questions/Question1");
        String url2 = createUrl("/users");
        TestRestTemplate restTemplate = new TestRestTemplate();
       // String output = restTemplate.getForObject(url, String.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Question> response = restTemplate.exchange(url, HttpMethod.GET,entity,Question.class);
        ResponseEntity<String> response2 = restTemplate.exchange(url2, HttpMethod.GET, null, String.class);

        System.out.println("Port: " + port);
        System.out.println(response.getBody());

       /* String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";
        JSONAssert.assertEquals(expected, response.getBody(), false);*/
    }

    @Test
    public void test2(){
        String url = createUrl("/surveys/Survey1/questions");

        String output = template.getForObject(url, String.class);
        ResponseEntity<List<Question>> response = template.exchange(
                url,
                HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER",
                        headers), new ParameterizedTypeReference<List<Question>>() {
                });


       /* String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";
        JSONAssert.assertEquals(expected, response.getBody(), false);*/
    }


    //this works with property spring.data.rest.defaultMediaType=application/json
    @Test
    public void test3(){
        String url = createUrl("/users");

        String output = template.getForObject(url, String.class);
        ResponseEntity<Resources<User>> response = template.exchange(
                url,
                HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER",
                        headers), new ParameterizedTypeReference<Resources<User>>(){
                });


       /* String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";
        JSONAssert.assertEquals(expected, response.getBody(), false);*/
    }

    @Test
    public void test4(){
        String url = createUrl("/users/1");

        String output = template.getForObject(url, String.class);
        ResponseEntity<Resource<User>> response = template.exchange(
                url,
                HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER",
                        headers), new ParameterizedTypeReference<Resource<User>>() {
                });


       /* String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia}";
        JSONAssert.assertEquals(expected, response.getBody(), false);*/
    }


    private String createUrl(String uri) {
        return "http://localhost:" + port + uri;
    }


    @Test
    public void retrieveSurveyQuestion() throws Exception {

        String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia,options:[India,Russia,United States,China]}";

        ResponseEntity<String> response = template.exchange(
                createUrl("/surveys/Survey1/questions/Question1"),
                HttpMethod.GET, new HttpEntity<String>("DUMMY_DOESNT_MATTER",
                        headers), String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}
