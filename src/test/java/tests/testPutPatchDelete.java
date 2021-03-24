package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class testPutPatchDelete {
//    @BeforeClass
//    public void setup(){
//        baseURI = "https://jsonplaceholder.typicode.com";
//    }
private String baseURI = "https://jsonplaceholder.typicode.com";

    /**
     * PUT, PATCH and DELETE requests are not allowed for the given end points
     * **/

    @Test
    public void testPatch(){
        //Using Map
        Map<String, Object> patchMap = new HashMap<>();
//        patchMap.put("userId",1);
//        patchMap.put("id",1);
        patchMap.put("title","Nope!");
//        patchMap.put("completed","true");

        //Sending request body with updated value, and content type header
        given().contentType(ContentType.JSON)
                .and().pathParam("id",1)
                .and().body(patchMap)
                .when().patch(baseURI+ "/posts{id}")
                .then().assertThat().statusCode(204);
    }

    @Test
    public void testDelete(){
        given().pathParam("id",100)
                .when().delete(baseURI+ "/posts{id}")
                .then().assertThat().statusCode(204);
    }

    // Delete one of the comments
    @Test
    public void test10() {

        Response response = when().delete(baseURI+"/posts/1");

        response.prettyPeek();
    }

}
