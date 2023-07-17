package com.teamtwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.teamtwo.dto.entity.Hero;
import com.teamtwo.model.service.HeroService;

import java.nio.file.*;

@Controller
public class IMageUpdateController {

    @Autowired
    private HeroService heroService; 

    @PostMapping("/uploadHeroImage")
    public String handleImagePost(@RequestParam("heroId") String heroId, @RequestParam("file") MultipartFile file) {
        try {
            Path path = Paths.get("uploads/");
            if (!Files.exists(path)) {
                Files.createDirectories(path); 
            }

            byte[] bytes = file.getBytes();
            Path completePath = Paths.get("uploads/" + heroId + ".jpg");
            Files.write(completePath, bytes);

            // Fetch the hero
            Hero hero = heroService.searchHeroById(Integer.parseInt(heroId));
            
            // If the hero exists, update the image URL
            if(hero != null) {
                String imageUrl = "/uploads/" + heroId + ".jpg";
                hero.setImagePath(imageUrl);
                heroService.updateHero(hero);
            }

            return "redirect:/"; 

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/uploadError";
        }
    }
}
