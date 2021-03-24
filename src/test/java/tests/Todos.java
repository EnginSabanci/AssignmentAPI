package tests;

import java.util.HashMap;
import java.util.Map;

public class Todos {
    private int userId;
    private int id;
    private String title;
    private String completed;

    //My class is to create object from JSON response
    //String method to use what I have inside the object
    @Override
    public String toString() {
        return "todos{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed='" + completed + '\'' +
                '}';
    }

    public Todos() {

    }
   //constructor with parameters
    public Todos(int userId, int id, String title, String completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    //I have getter and setter for my class
    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompleted() {
        return completed;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }
}
