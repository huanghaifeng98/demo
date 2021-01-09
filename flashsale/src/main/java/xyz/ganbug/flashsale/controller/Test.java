package xyz.ganbug.flashsale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.ganbug.flashsale.entity.Student;

@RestController
public class Test {

    public Student test(){
        return new Student("gdut");
    }
}
