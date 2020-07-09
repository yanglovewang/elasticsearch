package org.spring.springboot.controller;


import org.spring.springboot.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/api/search2", method = RequestMethod.GET)
    public String search2Controller() throws IOException {
        System.out.println("controller");
        searchService.search2();
        return "true";
    }
}
