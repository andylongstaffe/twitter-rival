package com.hollywood.twitterrival.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/value")
public class ValueDemoController {

    @Value("string value")
    private String stringValue;

    @Value("${value.from.file}")
    private String fileValue;

    @Value("${system.value}")
    private String systemValue;

    @GetMapping("/demo1")
    public String demo1() {
        return stringValue;
    }

    @GetMapping("/demo2")
    public String demo2() {
        return fileValue;
    }

    @GetMapping("/demo3")
    public String demo3() {
        return systemValue;
    }
}
