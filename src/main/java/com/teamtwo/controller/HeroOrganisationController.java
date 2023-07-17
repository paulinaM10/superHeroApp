package com.teamtwo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.teamtwo.dto.entity.HeroOrganisation;

import com.teamtwo.model.service.HeroOrganisationService;
import com.teamtwo.model.service.HeroService;
import com.teamtwo.model.service.OrganisationService;


@Controller
public class HeroOrganisationController {
	
	@Autowired
	private HeroOrganisationService heroOrganisationService;


	
	@RequestMapping("/HeroOrganisationIndex")
	public ModelAndView welcomePageController() {
		return new ModelAndView("HeroOrganisationIndex");
	}
	
	@RequestMapping("/InputOrganisationIDForMembers")
	public ModelAndView InputOrgIdPageController() {
		return new ModelAndView("InputOrganisationIDForMembers");
	}
	
	
	
	
    @RequestMapping("/membersByOrganisation")
    public ModelAndView getAllMembersByOrganisation(@RequestParam("orgId") int orgId) {
        ModelAndView modelAndView = new ModelAndView();
        List<HeroOrganisation> members = heroOrganisationService.getAllMembersByOrganisation(orgId);
        if (members != null) {
        modelAndView.addObject("members", members);
        modelAndView.setViewName("ShowMembers");
        }else {
        modelAndView.addObject("message", "organisation with ID " + orgId + " does not exist");
		modelAndView.setViewName("Output");
        	
        }
        return modelAndView;
    }
    
    @RequestMapping("/InputHeroIdForOrg")
	public ModelAndView InputHeroIdForOrgPageController() {
		return new ModelAndView("InputHeroIdForOrg");
	}
    
 
    @RequestMapping("/organisationBySuperHero")
    public ModelAndView getOrganisationBySuperHero(@RequestParam("heroId") int heroId) {
        ModelAndView modelAndView = new ModelAndView();
        List<HeroOrganisation> organisations = heroOrganisationService.getOrganisationBySuperHero(heroId);
        if (organisations != null) {
            modelAndView.addObject("organisations", organisations);
            modelAndView.setViewName("ShowOrganisationByHero");
        } else {
            modelAndView.addObject("message", "No organisations found for hero with ID " + heroId);
            modelAndView.setViewName("Output");
        }
        return modelAndView;
    }

	
	

}
