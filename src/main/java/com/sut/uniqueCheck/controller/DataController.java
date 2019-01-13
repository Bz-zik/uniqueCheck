package com.sut.uniqueCheck.controller;

import com.sut.uniqueCheck.service.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @Autowired
    private DataServiceImpl dataService;


    /**
     * User should send new text and count of words for selection in algorithm in request parameters <br>
     * Sample POST request: http://localhost:8080/add?text=some string for unique checking&count=2 <br>
     *
     * @param text  new text
     * @param count count of words for selection
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public void addValue(@RequestParam String text, @RequestParam int count) {
        dataService.add(text, count);
    }

}
