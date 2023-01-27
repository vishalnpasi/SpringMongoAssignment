package com.Assignment.controller;

import com.Assignment.models.StringModel;
import com.Assignment.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class StringController {
    @Autowired
    private StringService myService;
    @GetMapping("/getVowels")
//    public ResponseEntity<Map<Character,Integer>> CountVowels(@PathVariable String str){
    public String CountVowels( @RequestBody StringModel str){
        return myService.getVowels(str);
    }
}
