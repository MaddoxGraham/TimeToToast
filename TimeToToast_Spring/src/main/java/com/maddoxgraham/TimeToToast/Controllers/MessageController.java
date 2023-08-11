package com.maddoxgraham.TimeToToast.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MessageController {

    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages(){
        return ResponseEntity.ok(Arrays.asList("first","second"));
    }

}
