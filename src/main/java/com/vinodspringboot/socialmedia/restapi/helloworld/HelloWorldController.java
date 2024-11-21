package com.vinodspringboot.socialmedia.restapi.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    @GetMapping(path="/hello")
    public String sayHello(){
        return "Hello world!";
    }

    @GetMapping(path="/hello-bean")
    public HelloBean helloBean(){
        return new HelloBean("Hello World Bean");
    }

    @GetMapping(path="/hello-bean/{name}")
    public HelloBean helloBean(@PathVariable String name){
        return new HelloBean(String.format("Hello World Bean %s",name));
    }

}
