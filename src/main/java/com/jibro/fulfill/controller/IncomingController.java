/*
package com.jibro.fulfill.controller;
import com.jibro.fulfill.entity.Incoming;
import com.jibro.fulfill.service.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.jibro.fulfill.dto.ProductOrderDto;
import com.jibro.fulfill.service.IncomingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IncomingController {
    private IncomingService incomingService;
    private ApiService apiService;

    @Autowired
    public IncomingController(IncomingService incomingService, ApiService apiService){
        this.incomingService = incomingService;
        this.apiService = apiService;
    }

    @PostMapping(value = "/incoming/form")
    public ModelAndView insert(ProductOrderDto productOrderDto) throws Exception {
        String incoming_id = incomingService.insert(productOrderDto);
        System.out.println(productOrderDto.getProduct_id());
        System.out.println(incoming_id);
        apiService.productOrder(incoming_id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/incoming/list");
        return mav;
    }
    @GetMapping(value = "/incoming/form")
    public ModelAndView incomingForm(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/incoming/incoming");
        return mav;
    }
    @GetMapping("incoming/list")
    public ModelAndView incomingList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("incoming/list");
        return mav;
    }

}
*/
