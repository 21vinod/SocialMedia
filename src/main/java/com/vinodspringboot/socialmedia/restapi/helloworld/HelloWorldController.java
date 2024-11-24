package com.vinodspringboot.socialmedia.restapi.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {
    private MessageSource   messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @GetMapping(path="/hello-internationalized")
    public String sayHelloInternationalization(){

        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"default message",locale);

    }
}
