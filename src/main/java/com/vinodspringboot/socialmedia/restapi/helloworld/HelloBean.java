package com.vinodspringboot.socialmedia.restapi.helloworld;

public class HelloBean {
    String message = null;

    public HelloBean(String helloWorldBean) {
        message = helloWorldBean;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloBean{" +
                "message='" + message + '\'' +
                '}';
    }
}
