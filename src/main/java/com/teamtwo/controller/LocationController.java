package com.teamtwo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import com.teamtwo.dto.entity.Location;
import com.teamtwo.model.service.LocationService;


import java.util.List;
import java.util.stream.Collectors;

@Controller

public class LocationController {
	
   
    @Autowired
    private LocationService locationService;
   
    @ModelAttribute("locationIds")
	public List<Integer> getLocationId(){
		return locationService.getAllLocations()
				.stream()
				.map(loc->loc.getLocationId())
				.collect(Collectors.toList());
    }
    
    	
    @RequestMapping("/location-index")
	public ModelAndView welcomePageController() {
		return new ModelAndView("location-index");
	}

    
    @RequestMapping("/InputLocationId")
	public ModelAndView InputLocationIdPageController() {
    	ModelAndView modelAndView = new ModelAndView("InputLocationId");
		Location location = new Location();
		location.setLocationId(0);
		modelAndView.addObject("location", location);
		return modelAndView;
	}
    
    @RequestMapping("/searchLocationById")
	public ModelAndView searchLocationByIdController(@RequestParam("locationId") int locationId) {
		ModelAndView modelAndView = new ModelAndView();
		Location location = locationService.searchLocationById(locationId);
		if (location != null) {
			modelAndView.addObject("location", location);
			modelAndView.setViewName("ShowLocation");
		} else {
			modelAndView.addObject("message", "Location with ID " + locationId + " does not exist");
			modelAndView.setViewName("Output");

		}

		return modelAndView;
	}
    
    
    @RequestMapping("/showAllLocations")
	public ModelAndView showAllLocationsController() {
		ModelAndView modelAndView = new ModelAndView();

		List<Location> locList = locationService.getAllLocations();
		modelAndView.addObject("locList", locList);
		modelAndView.setViewName("DisplayAllLocations");
		return modelAndView;
	}
    
    @RequestMapping("/InputLocationDetails")
  	public ModelAndView InputLocationDetailsPageController() {
  		ModelAndView modelAndView=new ModelAndView();
  		
  		modelAndView.addObject("location", new Location());
  		modelAndView.setViewName("InputLocationDetails");
  		return modelAndView;
  	}
    
	@RequestMapping("/saveLocation")
	public ModelAndView saveLocationsController(@ModelAttribute("location") Location location) {
		ModelAndView modelAndView = new ModelAndView();
	
		String message = null;
		if (locationService.insertLocation(location))
			message = "Location Added";
		else
			message = "Location Not Added";

		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");

		return modelAndView;
	}
    
	@RequestMapping("/InputLocationIdPageForDelete")
	public ModelAndView inputLocationIdPageForDeleteController() {
		
		ModelAndView modelAndView = new ModelAndView("InputLocationIdForDelete");
	    modelAndView.addObject("loc", new Location());
	    modelAndView.addObject("locationId", getLocationId());
	    return modelAndView;
		
	}
	
	
	@RequestMapping("/deleteLocation")
	public ModelAndView deleteLocationController(@ModelAttribute("loc") Location location) {
		ModelAndView modelAndView = new ModelAndView();
		String message = null;
		int id =location.getLocationId();
		if (locationService.deleteLocation(id) != null) {
			message = "Location with id " + id + " deleted !";
		} else {
			message = "Location with id " + id + " not deleted !";
		}
		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");
		
		return modelAndView;
	}
	

	@RequestMapping("/InputLocationDetailsPageForUpdate")
	public ModelAndView InputSuperPowerDetailsPageForUpdateController(){
	    ModelAndView modelAndView = new ModelAndView("InputLocationDetailsPageForUpdate");
	    modelAndView.addObject("loc", new Location());
	    modelAndView.addObject("location", getLocationId());
	    return modelAndView;
	}
	
	
	@RequestMapping("/updateLocation")
	public ModelAndView updateSuperPowerController(@ModelAttribute("location") Location location) {
	    ModelAndView modelAndView = new ModelAndView();
	    String message = null;
	    int locationId = location.getLocationId();
	    if (locationService.updateLocation(location) != null) {
	        message = "Location with id " + location.getLocationId() + " updated!";
	    } else {
	        message = "Location with id " + location.getLocationId() + " not updated!";
	    }

	    modelAndView.addObject("message", message);
	    modelAndView.setViewName("Output");
	    
	    return modelAndView;
	}
}
