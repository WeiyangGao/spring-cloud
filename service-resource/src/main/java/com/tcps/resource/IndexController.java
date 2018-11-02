package com.tcps.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "this is Index Page";
    }

    @GetMapping("/user")
    public String user() {
        return "this is User Page";
    }

}
