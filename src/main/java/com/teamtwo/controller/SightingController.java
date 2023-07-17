package com.teamtwo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.stream.Collectors;
import java.sql.Date;



import javax.validation.Valid;

import com.teamtwo.dto.entity.Hero;
import com.teamtwo.dto.entity.Sighting;
import com.teamtwo.model.service.HeroService;
import com.teamtwo.model.service.SightingService;

@Controller
public class SightingController {

    @Autowired
    private SightingService sightingService;
    
    @Autowired
    private HeroService heroService;
  
    
    

    @ModelAttribute("sightingIds")
    public List<Integer> getSightingIDs() {
        return sightingService.getAllSightings()
                .stream()
                .map(sighting -> sighting.getSightingId())
                .collect(Collectors.toList());
    }

    @RequestMapping("/SightingIndex")
    public ModelAndView SightingIndexPageController() {
        return new ModelAndView("SightingIndex");
    }

    @RequestMapping("/InputSightingIdPage")
    public ModelAndView InputSightingIdPageController() {
        ModelAndView modelAndView = new ModelAndView("InputSightingIdPage");
        Sighting sighting = new Sighting();
        sighting.setSightingId(0); // set initial value
        modelAndView.addObject("sighting", sighting);
        return modelAndView;
    }

    @RequestMapping("/searchSightingById")
    public ModelAndView searchSightingByIdController(@RequestParam("sightingId") int sightingId) {
        ModelAndView modelAndView = new ModelAndView();
        Sighting sighting = sightingService.searchSightingById(sightingId);
        if (sighting != null) {
            Hero hero = heroService.searchHeroById(sighting.getHeroId());
            modelAndView.addObject("sighting", sighting);
            modelAndView.addObject("hero", hero);
            modelAndView.setViewName("ShowSighting");
        } else {
            modelAndView.addObject("message", "Sighting with ID " + sightingId + " does not exist");
            modelAndView.setViewName("SightingOutput");
        }
        return modelAndView;
    }


    @RequestMapping("/showAllSightings")
    public ModelAndView showAllSightingsController() {
        ModelAndView modelAndView = new ModelAndView();
        List<Sighting> sightingList = sightingService.getAllSightings();
        modelAndView.addObject("sightingList", sightingList);
        modelAndView.setViewName("DisplayAllSightings");
        return modelAndView;
    }

    @RequestMapping("/InputSightingDetails")
    public ModelAndView InputSightingDetailsPageController() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sighting", new Sighting());
        modelAndView.setViewName("InputSightingDetails");
        return modelAndView;
    }

    @RequestMapping("/saveSighting")
    public ModelAndView saveSightingController(@Valid @ModelAttribute("sighting") Sighting sighting, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("InputSightingDetails");
        }

        ModelAndView modelAndView = new ModelAndView();
        String message = null;
        if (sightingService.insertSighting(sighting))
            message = "Sighting Added";
        else
            message = "Sighting Not Added";

        modelAndView.addObject("message", message);
        modelAndView.setViewName("SightingOutput");

        return modelAndView;
 
    
    }
    
    
    
    @RequestMapping(value = "/ShowSightingsByDate", method = RequestMethod.GET)
    public ModelAndView showSightingsByDateInputPageController() {
        return new ModelAndView("EnterDate");
    }

    @RequestMapping(value = "/ShowSightingsByDate", method = RequestMethod.POST)
    public ModelAndView searchSightingsByDateController(@RequestParam("locationDate") Date locationDate) {
        ModelAndView modelAndView = new ModelAndView();
        List<Sighting> sightings = sightingService.getSightingByDate(locationDate);
        if (sightings != null) {
            modelAndView.addObject("sightings", sightings);
            modelAndView.setViewName("ShowSightingsByDate");
        } else {
            modelAndView.addObject("message", "No sightings on date " + locationDate);
            modelAndView.setViewName("SightingOutput");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/ShowSightingsByLocation", method = RequestMethod.GET)
    public ModelAndView showSightingsByLocationInputPageController() {
        return new ModelAndView("EnterLocation");
    }

    @RequestMapping(value = "/ShowSightingsByLocation", method = RequestMethod.POST)
    public ModelAndView searchSightingsByLocationController(@RequestParam("locationId") int locationId) {
        ModelAndView modelAndView = new ModelAndView();
        List<Sighting> sightings = sightingService.getSightingByLocationId(locationId);
        if (sightings != null) {
            modelAndView.addObject("sightings", sightings);
            modelAndView.setViewName("ShowSightingsByLocation");
        } else {
            modelAndView.addObject("message", "No sightings at location with ID " + locationId);
            modelAndView.setViewName("SightingOutput");
        }
        return modelAndView;
    }






}
