package com.example.LibraryManagement.com.LibraryManagement.Api;

import com.example.LibraryManagement.com.LibraryManagement.Models.ModelClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {


    ModelClass modelClass = new ModelClass("Sunil");
    //http:8080/hello
    @GetMapping("/hello")
    public ModelClass helloWorld() {
        return modelClass;
    }

}
