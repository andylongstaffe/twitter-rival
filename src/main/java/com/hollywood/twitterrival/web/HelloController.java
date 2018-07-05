package com.hollywood.twitterrival.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    class StatusResponse {
        private String text;
        private String source;

        public StatusResponse(String text) {
            this.text = text;
            this.source = "Hollywood.com";
        }

        public String getText() {
            return text;
        }

        public String getSource() {
            return source;
        }
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String sayHello() {
        return "hiya!";
    }

    @RequestMapping("/status")
    public StatusResponse status() {
        return new StatusResponse("OK");
    }

    @RequestMapping("/status/alt")
    public ResponseEntity altStatus() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/status/bad")
    public ResponseEntity badStatus() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
