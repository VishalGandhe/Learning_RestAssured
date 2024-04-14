package com.myprojectstructure.JsonToPojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myprojectstructure.Pojo.User;

import java.io.File;
import java.io.IOException;

public class JsonToPojoUser {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper= new ObjectMapper();
        User user= objectMapper.readValue(new File("C:\\Users\\Shreeya\\IdeaProjects\\Learning_RestAssured\\src\\test\\java\\com\\myprojectstructure\\DynamicPaylodHandle\\user.json"), User.class);

      //  System.out.println(user.getId());
        user.setId(5);
        user.setFirstName("Vishal");

    }

}
