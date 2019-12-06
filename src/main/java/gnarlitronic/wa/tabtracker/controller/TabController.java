/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gnarlitronic.wa.tabtracker.controller;

import gnarlitronic.wa.tabtracker.dto.Tab;
import gnarlitronic.wa.tabtracker.service.TabService;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Elnic
 */
@Controller
public class TabController {

    @Inject
    TabService tServ;

    
    //Loads all entries from database
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loadPage(HttpServletRequest request, Model model) {
        model.addAttribute("tabList", tServ.loadTabs());
        model.addAttribute("tab", new Tab());
        return "/index";
    }
    
    //Adds new tab entry to database
    @RequestMapping(value="/addTab", method = RequestMethod.POST)
    public String addTab(@ModelAttribute("tab") Tab tab, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/index";
        } else {
            tServ.addTab(tab);
            model.addAttribute("tabList", tServ.loadTabs());
            return "/index";
        }
    }
    
    //Takes in search term and category to search by and returns all entries that match
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public String search(@ModelAttribute("tab") Tab tab, HttpServletRequest request, Model model) {
        model.addAttribute("tabList", tServ.searchTabs(request.getParameter("searchTerm"), request.getParameter("category")));
        return "/index";
    }
    
    //removes entry from database
    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public String deleteTab(@ModelAttribute("tab") Tab tab, HttpServletRequest request, Model model) {
        tServ.deleteTab(request.getParameter("id"));
        model.addAttribute("tabList", tServ.loadTabs());
        return "/index";
    }

}
