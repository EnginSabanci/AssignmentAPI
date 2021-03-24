package tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class testPostWithString {
    @BeforeClass
    public void setup(){
        baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void postWithString(){
        //I set the header and the JSON body. Send Json body as string
        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"userId\": 10,\n" +
//                        "  \"id\": 201,\n" +
                        "  \"title\": \"I just added another todo\",\n" +
                        "  \"completed\": false\n" +
                        "}")
                .when().post("/todos/");

        response.prettyPrint();
        //validations
        //verify status code is 201
        Assertions.assertEquals(response.statusCode(),201);
        //verify that the title is in the data base
        Assertions.assertEquals(response.path("title"),"I just added another todo");
        Assertions.assertEquals(response.contentType(),"application/json; charset=utf-8");

        //verify request body
        JsonPath json = response.jsonPath();
        Assertions.assertEquals(json.getString("completed"),"false");
        Assertions.assertEquals(json.getString("title"),"I just added another todo");

    }
}
