package com.jibro.fulfill.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.fulfill.dto.ProductOrderDto;
import com.jibro.fulfill.service.IncomingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productOrder")
public class IncomingController {
    private IncomingService incomingServic;
    @Autowired
    public IncomingController(IncomingService incomingService){
        this.incomingServic = incomingService;
    }

    @PostMapping(value = "/form")
    public ProductOrderDto insert(){

        return null;
    }
    @GetMapping("incoming/list")
    public ModelAndView incomingList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("incoming/list");
        return mav;
    }

}
