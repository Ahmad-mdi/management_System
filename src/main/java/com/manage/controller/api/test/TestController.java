package com.manage.controller.api.test;

import com.manage.service.test.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/text")
@AllArgsConstructor
public class TestController {
    private final TestService testService;
    @GetMapping
    public void getAll(){
        testService.getText();
    }
}
