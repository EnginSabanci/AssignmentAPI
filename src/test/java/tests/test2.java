package tests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.is;
public class test2 {

    private String baseURI = "https://jsonplaceholder.typicode.com";


    //Get the item which has userID equals 2 and id equals 20- use query parameters
    @Test
    public void test1() {
        Response response = given().
                header("Accept","application/json"). //Set the header accepting body in Json format
                baseUri(baseURI + "/posts").
                queryParam("userId","2").
                queryParam("id", "20").
                get();
        assertEquals(200, response.getStatusCode());
        //Verify data data is in Json format
        assertEquals("application/json; charset=utf-8",response.getContentType());
        System.out.println(response.prettyPrint());

    }

    //Get all items list as json format
    @Test
    public void test2() {
        Response response = given().
                header("Accept","application/json").
                baseUri(baseURI + "/posts").
                get();
        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    //Get id=50 info by using rote/end point and make assertion using Hamcrest Matcher
    @Test
    public void test3() {
        //I don't create response object. Instead, I use chaining method
       given().accept(ContentType.JSON).
               pathParam("id",50).
               when().get(baseURI + "/posts/{id}").
               then().assertThat().statusCode(200).
               and().assertThat().contentType("application/json; charset=utf-8").
               and().assertThat().body("userId", Matchers.equalTo(5)); //Hamcrest matcher allows us to verify json key value inside this body
    }

    //Get comments which belongs to postid=5 by using rote/end point
    @Test
    public void test4() {
        Response response = given().
                baseUri(baseURI + "/posts/5/comments").
                get();
        assertEquals(200, response.getStatusCode());
        response.prettyPrint();
    }

    //Verify that size is 100"
    @Test

    public void test5() {
        //By using Hamcrest library, I chain my request. I continue with my response and get verification with my response.
        given().
                get(baseURI+"/posts").
                then().statusCode(200).and().
                assertThat().
                body("size()", is(100));
    }

    //Get employee with id 50 and verify that response returns status code 200 also , print body"
    @Test
    public void test6() {

        Response response = given().
                header("accept", "application/json").get(baseURI + "/posts/50");
        int actualStatusCode = response.getStatusCode();
        response.prettyPrint();
        assertEquals(200, actualStatusCode);

        System.out.println( "The content type is : "+response.getHeader("Content-Type"));
    }

}
