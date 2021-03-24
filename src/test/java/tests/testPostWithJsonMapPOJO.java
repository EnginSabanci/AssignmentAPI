package tests;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class testPostWithJsonMapPOJO {
    private String baseURI = "https://jsonplaceholder.typicode.com";

    @Test
    public void PostWithMap(){
        //create a map to be used as a body for post request
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("title","Just say Hi");
        requestMap.put("body","Hello There!");
        requestMap.put("userId","5");

        Response response = given().
                accept(ContentType.JSON).
                and().
                contentType(ContentType.JSON).
                body(requestMap).
                when().post(baseURI+"/posts");
        //verify status code is 201
        assertEquals(response.statusCode(),201);
        response.prettyPrint();
        //verify request body
        JsonPath json = response.jsonPath();
        assertEquals(json.getString("title"),"Just say Hi");
        assertEquals(json.getString("body"),"Hello There!");
        assertEquals(json.getString("userId"),"5");
    }

    @Test
    public void postWithGson() {
        SortedMap<String, String> post = new TreeMap();
        post.put("title", "Test RestAssured post");
        post.put("body", "RestAssured post body test content");
        post.put("userId", "7");

        //Sending JSon body
        Gson gson = new Gson();
        Type gsonType = new TypeToken<HashMap>(){}.getType();
        String gsonString = gson.toJson(post,gsonType);
        Response response = given().
                accept(ContentType.JSON).
                and().
                contentType(ContentType.JSON).
                body(gsonString).
                when().
                post(baseURI+"/posts");

        //verify status code is 201
        assertEquals(response.statusCode(),201);
        assertEquals(response.path("body"),"RestAssured post body test content");
        System.out.println(response.prettyPrint());

        //verify request body by reading value with JsonPath
        JsonPath json = response.jsonPath();
        assertEquals(json.getString("title"),"Test RestAssured post");
        assertEquals(json.getString("userId"),"7");
    }

    @Test
    public void PostThenGet(){
    /**
     * When I send Post request, I get following Json response. Since theres is 200 todos. If I send a new one, the id number of new todo
     * is 201 which I did not set in my post request. API automatically assigns 201 to new Post.
     *
     * {
     *      "userId": 8,
     *      "id": 201,
     *      "title": "Clean the back yard",
     *      "completed": "false"
     * }
     *
     * However, the new post is not actually creates a new todo.
     * I understand that when I send GET request for new todo
     * Response object for Get request is as follows:
     * todos{userId=0, id=0, title='null', completed='null'}
     **/

        //create a Todos class object and used as a body for post request
        Todos todo = new Todos();
        todo.setCompleted("false");
        todo.setTitle("Clean the back yard");
        todo.setUserId(8);

        //I pass my object to my request
        //my object will be automatically converts into Json object
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(todo)
                .when().post(baseURI + "/todos");
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(), "application/json; charset=utf-8");

        response.prettyPrint();

        //If I send a GET request, I get the above information and save it to a Todos object.
        //The object keeps the all information related with above created object
        //========================================GET REQUEST=====================================

        Response response2 = given().accept(ContentType.JSON)
                .pathParam("id",201)
                .and().when().get(baseURI+"/todos{id}");

        Todos todos = response2.body().as(Todos.class);
        System.out.println(todos.toString());

    }

}
