package com.hackjana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewController {
	
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public String init() {
		return "index";
    }
	
	@RequestMapping(value = "/page1.htm", method = RequestMethod.GET)
    public String goToPage1() {
		return "page1";
    }

	@RequestMapping(value = "/page2.htm", method = RequestMethod.GET)
    public String goToPage2(Model model) {
		return "page2";
    }
}
