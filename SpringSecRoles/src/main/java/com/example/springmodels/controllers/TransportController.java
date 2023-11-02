package com.example.springmodels.controllers;


import com.example.springmodels.models.Location;
import com.example.springmodels.models.Person;
import com.example.springmodels.models.Transport;
import com.example.springmodels.repos.LocationRepo;
import com.example.springmodels.repos.PersonRepo;
import com.example.springmodels.repos.TransportRepo;
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
public class TransportController {

    private TransportRepo us;
    private final PersonRepo pr;
    private final LocationRepo lr;
    public List<Transport> transp = new ArrayList<Transport>();
    public List<Location> locat = new ArrayList<Location>();


    public List<Person> per = new ArrayList<>();


    public TransportController(TransportRepo us, PersonRepo pr, LocationRepo lr) {
        this.us = us;
        this.pr=pr;
        this.lr=lr;
    }

    @GetMapping("/Transports")
    public String Transports(Model model){
        transp = us.findAll();
        model.addAttribute("transp", transp);
        per = pr.findAll();
        model.addAttribute("per", per);
        locat = lr.findAll();
        model.addAttribute("locat", locat);
        return "Transports";
    }

    @GetMapping("/deleteTransport/{transpId}")
    public String DeleteTransport(@PathVariable("transpId") int id) {

        us.deleteById(id);
        return "redirect:/Transports";
    }

    @GetMapping("/UpdateTransport/{transpId}")
    public String UpdateLocation(@PathVariable("transpId") Integer id, Model model) {
        for (int i = 0; i < transp.size(); i++) {
            if (transp.get(i).getId() == id) {
                model.addAttribute("name", transp.get(i).getName());
                model.addAttribute("id", transp.get(i).getId());
                model.addAttribute("number", transp.get(i).getNumber());
                model.addAttribute("description", transp.get(i).getDescription());
                model.addAttribute("driver", per.get(i).getSecondName());
                model.addAttribute("location", locat.get(i).getName());
            }
        }
        return "Transport";
    }

    @GetMapping("/AddTransport")
    public String AddTransport(@ModelAttribute("name") String name, @ModelAttribute("number") String number, @ModelAttribute("description") String description, @ModelAttribute("driver") int driver, @ModelAttribute("location") int location){
        Person pers = pr.findById(driver).orElseThrow();
        Location loc = lr.findById(location).orElseThrow();
            us.save(new Transport(name, number, description, pers, loc));

        return "redirect:/Transports";
    }

    @PostMapping("/updateTransport")
    public String UpdateTransport(@ModelAttribute("name") String name, @ModelAttribute("number") String number, @ModelAttribute("description") String description, @ModelAttribute("driver") int driver, @ModelAttribute("location") int location, @ModelAttribute("id") Integer id){
        Person pers = pr.findById(driver).orElseThrow();
        Location loc = lr.findById(location).orElseThrow();

        us.save(new Transport(name, number, description, pers, loc, id));
        return "redirect:/Transports";
    }

    @GetMapping("/GetTransport")
    public String UpdateLocation(@ModelAttribute("numberTr") String numberTr, Model model) {
        Transport transport;
        transport = us.findTransportByNumber(numberTr.trim());

        model.addAttribute("name", transport.getName());
        model.addAttribute("id", transport.getId());
        model.addAttribute("number", transport.getNumber());
        model.addAttribute("description", transport.getDescription());
        model.addAttribute("driver", transport.getDriver().getSecondName());
        model.addAttribute("location", transport.getLocation().getName());

        return "Transport";
    }

    @GetMapping("/goI")
    public String Index() {

        return "index";
    }
}
