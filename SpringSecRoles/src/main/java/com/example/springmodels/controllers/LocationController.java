package com.example.springmodels.controllers;


import com.example.springmodels.models.Cell;
import com.example.springmodels.models.Location;
import com.example.springmodels.models.Person;
import com.example.springmodels.models.Product;
import com.example.springmodels.repos.CellRepo;
import com.example.springmodels.repos.LocationRepo;
import com.example.springmodels.repos.PersonRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('USER, DIRECTOR')")
public class LocationController {

    private LocationRepo us;

    private final CellRepo cr;
    private final PersonRepo pr;
    public List<Location> locs = new ArrayList<>();

    public List<Cell> cro = new ArrayList<>();
    public List<Person> per = new ArrayList<>();

    public LocationController( LocationRepo us, CellRepo cr, PersonRepo pr) {
        this.cr = cr;
        this.us = us;
        this.pr = pr;
    }

    @GetMapping("/Locations")
    public String Locations(Model model){
        locs = us.findAll();
        model.addAttribute("locs", locs);
        cro = cr.findAll();
        model.addAttribute("cro", cro);
        per = pr.findAll();
        model.addAttribute("per", per);
        return "Locations";
    }

    @GetMapping("/deleteLocation/{locId}")
    public String DeleteLocation(@PathVariable("locId") int id) {

        us.deleteById(id);

        return "redirect:/Locations";
    }

    @GetMapping("/UpdateLocation/{locId}")
    public String UpdateLocation(@PathVariable("locId") Integer id, Model model) {
        for (int i = 0; i < locs.size(); i++) {
            if (locs.get(i).getId() == id) {
                model.addAttribute("name", locs.get(i).getName());
                model.addAttribute("id", locs.get(i).getId());
                model.addAttribute("address", locs.get(i).getAddress());
                model.addAttribute("description", locs.get(i).getDescription());
                model.addAttribute("countCells",  cro.get(i).getName());
                model.addAttribute("mainPerson", per.get(i).getSecondName());
            }
        }
        return "Location";
    }

    @GetMapping("/AddLocation")
    public String AddLocation(@ModelAttribute("name") String name, @ModelAttribute("address") String address, @ModelAttribute("description") String description, @ModelAttribute("countCells") int countCells, @ModelAttribute("mainPerson") int mainPerson){
        Cell cell = cr.findById(countCells).orElseThrow();
        Person pers = pr.findById(mainPerson).orElseThrow();
        us.save(new Location(name, address, description, cell, pers));
        return "redirect:/Locations";
    }

    @PostMapping("/updateLocation")
    public String UpdatePerson(@ModelAttribute("name") String name, @ModelAttribute("address") String address, @ModelAttribute("description") String description, @ModelAttribute("countCells") int countCells, @ModelAttribute("mainPerson") int mainPerson, @ModelAttribute("id") Integer id){
        Cell cell = cr.findById(countCells).orElseThrow();
        Person pers = pr.findById(mainPerson).orElseThrow();
        us.save(new Location(name, address, description, cell, pers, id));
        return "redirect:/Locations";
    }

    @GetMapping("/GetLocation")
    public String UpdateLocation(@ModelAttribute("address") String address, Model model) {
        Location location;
        location = us.findLocationByAddress(address.trim());

        model.addAttribute("name", location.getName());
        model.addAttribute("id", location.getId());
        model.addAttribute("address", location.getAddress());
        model.addAttribute("description", location.getDescription());
        model.addAttribute("countCells", location.getCountCell().getName());
        model.addAttribute("mainPerson", location.getMainPerson().getSecondName());

        return "Location";
    }

    @GetMapping("/goIn")
    public String Index() {

        return "index";
    }
}
