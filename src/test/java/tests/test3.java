package tests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
public class test3 {
    private String baseURI = "https://jsonplaceholder.typicode.com";

    //"Save payload into java collection"
    @Test
    public void test1() {
        Response response = given().
                contentType(ContentType.JSON).
                when().
                get(baseURI+"/posts");

        List<Map<String, ?>> collection = response.jsonPath().get();

        for (Map<String, ?> map : collection) {
            System.out.println(map);
        }
    }
   //Save a single post into java collection and make assertion"
    @Test
    public void test2() {
        Response response = given().
                contentType(ContentType.JSON).
                when().
                get(baseURI+"/posts/50");
        //convert Json response to Java collections (Map). It is de-serialization
        Map<String, Object> collection = response.body().as(Map.class);

        //one example of verification one sided map/expected value
        assertEquals(5.00, collection.get("userId"));
    }

    //"Save the users into list of map"
    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .when().get(baseURI +"/users");
        //convert full json body into list of maps
        List<Map<String ,Object>> listOfUsers = response.body().as(List.class);

        //print all data of first user
        System.out.println(listOfUsers.get(0));
        Map<String, Object> firstUser = listOfUsers.get(0);

        //make one assertion
        String expectedFirstUserEmail = "Sincere@april.biz";
        String actualFirstUserEmail = firstUser.get("email").toString();
        System.out.println("The email of firstUser is " + actualFirstUserEmail);
        assertEquals(expectedFirstUserEmail, actualFirstUserEmail);
    }


}
