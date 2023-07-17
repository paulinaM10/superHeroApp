package com.teamtwo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.teamtwo.dto.entity.SuperPower;
import com.teamtwo.model.service.SuperPowerService;

@Controller
public class SuperPowerController {
	
	
	@Autowired
	private SuperPowerService superPowerService;
	
	@ModelAttribute("superPowerIds")
	public List<Integer> getSuperPowerIDs(){
		return superPowerService.getAllSuperPowers()
				.stream()
				.map(superPower -> superPower.getSuperPowerId())
				.collect(Collectors.toList());
	}

	
	
	@RequestMapping("/SuperPowerIndex")
	public ModelAndView welcomePageController() {
		return new ModelAndView("SuperPowerIndex");
	}

	
	@RequestMapping("/InputSuperPowerIdPage")
	public ModelAndView InputPowerIdPageController() {
		ModelAndView modelAndView = new ModelAndView("InputSuperPowerIdPage");
		SuperPower superPower = new SuperPower();
		superPower.setSuperPowerId(0); // set initial value
		modelAndView.addObject("superPower", superPower);
		return modelAndView;
	}

	
	
	@RequestMapping("/searchSuperPowerById")
	public ModelAndView searchSuperPowerByIdController(@RequestParam("superPowerId") int superPowerId) {
		ModelAndView modelAndView = new ModelAndView();
		SuperPower superPower = superPowerService.getSuperPowerById(superPowerId);
		if (superPower != null) {
			modelAndView.addObject("superPower", superPower);
			modelAndView.setViewName("ShowSuperPower");
		} else {
			modelAndView.addObject("message", "SuperPower with ID " + superPowerId + " does not exist");
			modelAndView.setViewName("SuperPowerOutput");

		}

		return modelAndView;
	}
	
	
	@RequestMapping("/showAllSuperPowers")
	public ModelAndView showAllSuperPowersController() {
		ModelAndView modelAndView = new ModelAndView();

		List<SuperPower> spList = superPowerService.getAllSuperPowers();
		modelAndView.addObject("spList", spList);
		modelAndView.setViewName("DisplayAllSuperPowers");
		return modelAndView;
	}

	
	@RequestMapping("/InputSuperPowerDetails")
	public ModelAndView InputSuperPowerDetailsPageController() {
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.addObject("sp", new SuperPower());
		modelAndView.setViewName("InputSuperPowerDetails");
		return modelAndView;
	}

	

	@RequestMapping("/saveSuperPower")
	public ModelAndView saveSuperPowerController(@Valid @ModelAttribute("sp") SuperPower superPower, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        return new ModelAndView("InputSuperPowerDetails");
	    }

	    ModelAndView modelAndView = new ModelAndView();

	    String message = null;
	    if (superPowerService.addSuperPower(superPower))
	        message = "Super Power Added";
	    else
	        message = "Super Power Not Added";

	    modelAndView.addObject("message", message);
	    modelAndView.setViewName("SuperPowerOutput");

	    return modelAndView;
	}

	
	
	
	@RequestMapping("/InputSuperPowerIdForDelete")
	public ModelAndView inputSuperPowerIdPageForDeleteController() {
	    ModelAndView modelAndView = new ModelAndView("InputSuperPowerIdForDelete");
	    modelAndView.addObject("sp", new SuperPower());
	    modelAndView.addObject("superPowerIds", getSuperPowerIDs());
	    return modelAndView;
	}

	@RequestMapping("/deleteSuperPower")
	public ModelAndView deleteSuperPowerController(@ModelAttribute("sp") SuperPower superPower) {
	    ModelAndView modelAndView = new ModelAndView();
	    int id = superPower.getSuperPowerId();
	    String message = null;

	    if (superPowerService.deleteSuperPower(id)) {
	        message = "Super Power with id " + id + " deleted !";
	    } else {
	        message = "Super Power with id " + id + " not deleted !";
	    }

	    modelAndView.addObject("message", message);
	    modelAndView.setViewName("SuperPowerOutput");
	    
	    return modelAndView;
	}

	
	
	
	
	
	@RequestMapping("/InputSuperPowerDetailsPageForUpdate")
	public ModelAndView InputSuperPowerDetailsPageForUpdateController(){
	    ModelAndView modelAndView = new ModelAndView("InputSuperPowerDetailsPageForUpdate");
	    modelAndView.addObject("sp", new SuperPower());
	    modelAndView.addObject("superPowerIds", getSuperPowerIDs());
	    return modelAndView;
	}
	
	
	@RequestMapping("/updateSuperPower")
	public ModelAndView updateSuperPowerController(@ModelAttribute("sp") SuperPower superPower) {
	    ModelAndView modelAndView = new ModelAndView();
	    String message = null;

	    if (superPowerService.editSuperPower(superPower)) {
	        message = "Super Power with id " + superPower.getSuperPowerId() + " updated!";
	    } else {
	        message = "Super Power with id " + superPower.getSuperPowerId() + " not updated!";
	    }

	    modelAndView.addObject("message", message);
	    modelAndView.setViewName("SuperPowerOutput");
	    
	    return modelAndView;
	}

	
}
