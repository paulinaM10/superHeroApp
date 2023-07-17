package com.teamtwo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.teamtwo.dto.entity.Hero;
import com.teamtwo.model.service.HeroService;

@Controller
public class HeroController {
	
	@Autowired
	private HeroService heroService;
	
	
	@ModelAttribute("heroIds")
	public List<Integer> getHeroIDs(){
		return heroService.getAllHeroes()
				.stream()
				.map(hero->hero.getHeroId())
				.collect(Collectors.toList());
	}
	
	@RequestMapping("/HeroIndex")
	public ModelAndView welcomeHeroPageController() {
		return new ModelAndView("HeroIndex");
	}

	
	@RequestMapping("/InputHeroIdPage")
	public ModelAndView includeHeroIdPageController() {
		return new ModelAndView("InputHeroId");
	}
	
	@RequestMapping("/searchHeroById") 
	public ModelAndView searchHeroByIdController (@RequestParam("heroId") int heroId) {
		ModelAndView modelAndView = new ModelAndView();
		
		Hero hero = heroService.searchHeroById(heroId);
		
		if (hero !=null) {
			modelAndView.addObject("hero", hero);
			modelAndView.setViewName("ShowHero");
		} else {
			modelAndView.addObject("message", "SuperHero with ID "+heroId+ "does not exist");
			modelAndView.setViewName("HeroOutput");
		
	}
		return modelAndView;
	}
	@RequestMapping("/InputHeroDetails")
	public ModelAndView InputHeroDetailsPageController() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("hero", new Hero());
		modelAndView.setViewName("InputHeroDetails");
		return modelAndView;
	}
	@RequestMapping("/saveHero") 
	public ModelAndView saveHeroController(@Valid @ModelAttribute("hero") Hero hero, BindingResult results) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		if (results.hasErrors()) {
			return new ModelAndView("InputHeroDetails", "hero", hero);
		}
		
		String message = null;
			
			if (heroService.addHero(hero))
				message= "Hero added";
			else 
				message= "Hero not added";
			
			modelAndView.addObject("message", message);
			modelAndView.setViewName("HeroOutput");
			
			return modelAndView;

	}
		
	@RequestMapping("/showAllHeroes")
	public ModelAndView showAllHeroesController() {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Hero> heroList = heroService.getAllHeroes();
		modelAndView.addObject("heroList", heroList);
		modelAndView.setViewName("DisplayAllHeroes");
		return modelAndView;
	}
	
	@RequestMapping("/InputHeroIDPageForDelete")
	public ModelAndView inputHeroIdPageForDeleteController() {
		return new ModelAndView("InputHeroIdForDelete","hero",new Hero());
	}
	
	@RequestMapping("/deleteHero")
	public ModelAndView deleteHeroController(@ModelAttribute("hero") Hero hero) {
		ModelAndView modelAndView = new ModelAndView();
		String message = null;
		int heroId=hero.getHeroId();
		if (heroService.deleteHeroById(heroId)) {
			message = "Hero with id " + heroId + " deleted !";
		} else {
			message = "Hero with id " + heroId + " not deleted !";
		}
		modelAndView.addObject("message", message);
		modelAndView.setViewName("HeroOutput");
		
		return modelAndView;
	}
	@RequestMapping("/InputHeroDetailsPageForUpdate")
	public ModelAndView inputHeroDetailsForUpdateController() {
		ModelAndView modelAndView = new ModelAndView();
	    
	    List<Hero> heroes = heroService.getAllHeroes(); 
	    
	    modelAndView.addObject("heroes", heroes);
	    modelAndView.setViewName("InputHeroDetailsForUpdate");

	    return modelAndView;
	}
	@RequestMapping("/updateHero")
	public ModelAndView updateHeroController(@RequestParam("heroId") int heroId, @RequestParam("heroName") String heroName,
	                                         @RequestParam("heroDesc") String heroDesc,
	                                         @RequestParam("heroSuperPower") String heroSuperPower) {
	    ModelAndView modelAndView = new ModelAndView();

	    Hero updatedHero = heroService.editHero(heroId, heroName, heroDesc, heroSuperPower);

	    if (updatedHero != null) {
	        modelAndView.addObject("hero", updatedHero);
	        modelAndView.addObject("message", "SuperHero has been updated");
	    } else {
	        modelAndView.addObject("message", "Superhero has not been updated");
	    }

	    modelAndView.setViewName("HeroOutput");
	    
	    return modelAndView;
	}


	
}


