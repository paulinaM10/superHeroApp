package com.teamtwo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.teamtwo.dto.entity.Sighting;
import com.teamtwo.model.service.SightingService;

@Controller
public class HomePageController {
    
	
	
	@Autowired
    private SightingService sightingService;

    
	@RequestMapping("/")
    public String homePage(Model model) {
        List<Sighting> latestSightings = sightingService.findLatestSightings();
        model.addAttribute("latestSightings", latestSightings);
        return "HomePage";
    }
   
}


