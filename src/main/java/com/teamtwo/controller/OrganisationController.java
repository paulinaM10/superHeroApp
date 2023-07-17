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


import com.teamtwo.dto.entity.Organisation;

import com.teamtwo.model.service.OrganisationService;

@Controller
public class OrganisationController {
	
	
	@Autowired
	private OrganisationService organisationService;
	
	
	@ModelAttribute("orgIds")
	public List<Integer> getOrgIds(){
		return organisationService.getAllOrganisations()
				.stream()
				.map(org->org.getOrgId())
				.collect(Collectors.toList());
	}
	
	
	@RequestMapping("/OrganisationIndex")
	public ModelAndView welcomePageController() {
		return new ModelAndView("OrganisationIndex");
	}
	
	@RequestMapping("/InputOrgIdPage")
	public ModelAndView InputOrgIdPageController() {
		return new ModelAndView("InputOrganisationID");
	}
	
	@RequestMapping("/searchOrgById")
	public ModelAndView searchEmployeeByIdController(@RequestParam("orgId") int orgId) {
		ModelAndView modelAndView = new ModelAndView();
		Organisation org = organisationService.searchOrganisationById(orgId);
		if (org != null) {
			modelAndView.addObject("organisation", org);
			modelAndView.setViewName("ShowOrganisation");
		} else {
			modelAndView.addObject("message", "organisation with ID " + orgId + " does not exist");
			modelAndView.setViewName("Output");

		}

		return modelAndView;
	}

	@RequestMapping("/InputOrganisationDetails")
	public ModelAndView InputOrganisationDetailsPageController() {
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.addObject("org", new Organisation());
		modelAndView.setViewName("InputOrganisationDetails");
		return modelAndView;
//		return new ModelAndView("InputorganisationDetails");
	}
	
	@RequestMapping("/showAllOrganisations")
	public ModelAndView showAllOrganisationController() {
		ModelAndView modelAndView = new ModelAndView();

		List<Organisation> orgList= organisationService.getAllOrganisations();
		modelAndView.addObject("organisationList", orgList);
		modelAndView.setViewName("DisplayAllOrganisations");
		return modelAndView;
	}
	
	
	  @RequestMapping("/saveOrganisation")
	    public ModelAndView saveOrganisationController(@Valid @ModelAttribute("org") Organisation organisation, BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            return new ModelAndView("InputOrganisationDetails");
	        }

	        ModelAndView modelAndView = new ModelAndView();
	        String message = null;
	        if (organisationService.insertOrganisation(organisation))
	            message = "Organisation Added";
	        else
	            message = "Organisation Not Added";

	        modelAndView.addObject("message", message);
	        modelAndView.setViewName("OrganisationOutput");

	        return modelAndView;
	 
	    
	    }
	    
	
	
	@RequestMapping("/InputOrgIdPageForDelete")
	public ModelAndView inputOrgIdPageForDeleteController() {
		return new ModelAndView("InputOrgIdForDelete","org",new Organisation());
	}
	

	
	
	@RequestMapping("/deleteOrg")
	public ModelAndView deleteOrganisationController(@ModelAttribute("org") Organisation organisation) {
		ModelAndView modelAndView = new ModelAndView();
		String message = null;
		int oId=organisation.getOrgId();
		if (organisationService.deleteOrganisation(oId) != null) {
			message = "Org with id" + oId+ " deleted !";
		} else {
			message = "Org with id " + oId + " not deleted !";
		}
		modelAndView.addObject("message", message);
		modelAndView.setViewName("OrgDeleteOutput");
		
		return modelAndView;
	}
	

	
	
	@RequestMapping("/InputOrgDetailsPageForUpdate")
	public ModelAndView InputOrgDetailsPageForUpdateController(){
		return new ModelAndView("InputOrgDetailsForUpdate");
	}
	
	
	@RequestMapping("/updateOrg")
	public ModelAndView updateOrganisationController(@ModelAttribute("org") Organisation organisation) {
	    ModelAndView modelAndView = new ModelAndView();
	    String message = null;
	    int orgId = organisation.getOrgId();

	    if (organisationService.updateOrganisation(organisation) != null) {
	        message = "Details updated for organisation ID: " + orgId;
	    } else {
	        message = "Details not updated for organisation ID: " + orgId;
	    }

	    modelAndView.addObject("message", message);
	    modelAndView.setViewName("OrgUpdateOutput");

	    return modelAndView;
	}
	

	



}
