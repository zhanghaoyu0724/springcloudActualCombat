package study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/echo")
    public String hello(){
        return  "hello! I`m provider2020";
    }
}
