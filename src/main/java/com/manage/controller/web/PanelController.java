package com.manage.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/panel")
public class PanelController {
    @GetMapping
    public String index(){
        return "panel_tow";
//        return "panel";
    }
}
