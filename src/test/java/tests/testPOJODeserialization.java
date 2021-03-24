package tests;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class testPOJODeserialization {
    @BeforeClass
    public void setup(){
        baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void test1(){
        Response response =given().accept(ContentType.JSON)
                .pathParam("id",1)
        .when().get("/todos/{id}");
        //This is what I get from API call
        System.out.println("This is what I get from API call");
        response.prettyPrint();

        //How to convert JSON response to my todos class
        //Here, I convert the JSON to Todos.class here with as method.
        //In test3, I use as method to convert my JSON object to Map.
        Todos todos1 = response.body().as(Todos.class);

        //If I print the following object, I assume that it will get all information from API and map to my object.
        System.out.println("This is what I put into Todos class object");
        System.out.println(todos1.toString());

        //For assertions, we have getter methods in Todos class so we use them.
        Assertions.assertEquals(todos1.getUserId(),1);
        Assertions.assertEquals(todos1.getId(),1);
        Assertions.assertEquals(todos1.getTitle(),"delectus aut autem");
        Assertions.assertEquals(todos1.getCompleted(),"false");
    }

    @Test
    public void Test2(){
        Gson gson = new Gson();
        //I have constructor with 4 parameters in Todos class. This is my Java object to keep my information I want to just create and send
        Todos todos2 = new Todos(10,201,"Go shopping","false");

        //I want to convert above object into Json format which means converting custom class to Json (serialization)
        String jsonbody = gson.toJson(todos2);
        System.out.println(jsonbody);
    }
}
