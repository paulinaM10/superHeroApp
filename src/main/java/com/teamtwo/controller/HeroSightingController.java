package com.teamtwo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.teamtwo.dto.entity.Hero;
import com.teamtwo.dto.entity.Sighting;
import com.teamtwo.model.service.HeroService;
import com.teamtwo.model.service.SightingService;

@Controller
public class HeroSightingController {
	
	@Autowired
	private HeroService heroService;
	
	@Autowired
	private SightingService sightingService;

	
	@RequestMapping("/UploadHeroImagePage")
    public ModelAndView uploadHeroImagePage() {
		return new ModelAndView("UploadHeroImage");
    }
	  
	@PostMapping("/UploadHeroImage")
	public ModelAndView uploadHeroImage(@RequestParam("heroId") int heroId, @RequestParam("file") MultipartFile file) {
	    String fileName = generateUniqueFileName(file.getOriginalFilename());

	    if (!file.isEmpty()) {
	        try {
	            byte[] bytes = file.getBytes();
	            Path path = Paths.get("src/main/resources/static/" + fileName);
	            Files.write(path, bytes);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    ModelAndView modelAndView = new ModelAndView("UploadSuccess");
	    modelAndView.addObject("message", "SuperHero image uploaded");
	    modelAndView.addObject("fileName", fileName);
	    return modelAndView;
	}


	private String generateUniqueFileName(String originalFilename) {
	    String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
	    return UUID.randomUUID().toString() + extension;
	}


	    @RequestMapping("/AddHeroImage")
	    public ModelAndView addHeroImage(@PathVariable int heroId, @RequestParam("file") MultipartFile file) {
	        ModelAndView modelAndView = new ModelAndView("redirect:/showHeroImage/{heroId}");
	        if (!file.isEmpty()) {
	            String fileName = file.getOriginalFilename();

	    }
        return modelAndView;
    }

    }
