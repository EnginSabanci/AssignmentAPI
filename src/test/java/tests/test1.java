package tests;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class test1 {
    private String baseURI = "https://jsonplaceholder.typicode.com/";

    /**
     * Start from given() then specify type of request get()
     * enter resource location as parameter
     * put the response into response object
     * retrieve header, body, status code from response object
     * verify that status code is 200
     * */

    @Test
    public void test1(){
        Response response = given().
                get(baseURI + "/posts/1/comments");
        //System.out.println(response.getBody().asString());
        System.out.println(response.prettyPrint());
        assertEquals(200, response.getStatusCode());
    }

    //Get todos for userID = 1 , verify that response returns status code 200, and print body in Json format
    @Test
    public void test2(){
        //specify that webservice returns json as a response type
        Response response = given().
                header("Accept","application/json").
                get(baseURI +"/users/1/todos");
        System.out.println(response.prettyPrint());
        assertEquals(200, response.getStatusCode());
    }

    //Perform GET request to  all headers.

    @Test
    public void testGetHeaders(){
        Response response = given().
                get(baseURI);
        assertEquals(200, response.getStatusCode());
        // I get specific header getHeaders() method returns all possible Headers that response delivering and I return specific headers
        Header header = response.getHeaders().get("Content-Type");
        System.out.println(header);
        //Print all headers one by one. I read metadata by retrieving information from header object
        for(Header h: response.getHeaders()){
            System.out.println(h);
        }
    }

    @Test
    public void testWithPath(){
        Response response = given().accept(ContentType.JSON).
                pathParam("id",100).
                when().get(baseURI + "posts/{id}");

        //verify status code
        assertEquals(200, response.getStatusCode());

        //verify content type
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        //assign values of json key to variables
        int userId = response.body().path("userId");
        String title = response.body().path("title");
        String body = response.body().path("body").toString();

        //verify json keys and values
        assertEquals(userId, 10);
        assertEquals(title,"at nam consequatur ea labore ea harum");
        assertEquals(body, "cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut");

    }
}
