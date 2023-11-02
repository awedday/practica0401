package com.example.springmodels.controllers;

import com.example.springmodels.models.Cell;
import com.example.springmodels.models.Location;
import com.example.springmodels.models.Product;
import com.example.springmodels.repos.CellRepo;
import com.example.springmodels.repos.LocationRepo;
import com.example.springmodels.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN, USER')")
public class CellController {
    private final CellRepo us;
    private final LocationRepo lr;

    private final ProductRepo pr;
    private Cell updateableCell;
    public List<Cell> cells = new ArrayList<>();
    public List<Location> locat = new ArrayList<Location>();
    public List<Product> pro = new ArrayList<Product>();

    @Autowired
    public CellController(CellRepo us, LocationRepo lr, ProductRepo pr){
        updateableCell = new Cell();
        this.lr = lr;
        this.us = us;
        this.pr = pr;
    }

    @GetMapping("/Cells")
    public String Cells(Model model){
        cells = us.findAll();

        model.addAttribute("cells", cells);
        locat = lr.findAll();
        model.addAttribute("locat", locat);
        pro = pr.findAll();
        model.addAttribute("pro", pro);
        return "Cells";
    }

    @GetMapping("/deleteCell/{cellId}")
    public String DeleteCell(@PathVariable("cellId") int id) {

        us.deleteById(id);
        return "redirect:/Cells";
    }

    @GetMapping("/UpdateCell/{cellId}")
    public String UpdateCell(@PathVariable("cellId") Integer id, Model model) {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getId() == id) {

                model.addAttribute("name", cells.get(i).getName());
                model.addAttribute("id", cells.get(i).getId());
                model.addAttribute("level", cells.get(i).getLevel());
                model.addAttribute("hash", cells.get(i).getHash());
                model.addAttribute("loc", locat.get(i).getName());
                model.addAttribute("product",pro.get(i).getArticle());
            }
        }
        return "Cell";
    }

    @PostMapping("/AddCell")
    public String AddCell(@ModelAttribute("name") String name, @ModelAttribute("level") Integer level, @ModelAttribute("hash") String hash, @ModelAttribute("loc") int loc, @ModelAttribute("product") int product){
        Location location = lr.findById(loc).orElseThrow();
        Product pro = pr.findById(product).orElseThrow();
        us.save(new Cell(name, level, hash, location, pro));
        return "redirect:/Cells";
    }

    @PostMapping("/updateCell")
    public String UpdateCell(@ModelAttribute("name") String name, @ModelAttribute("level") Integer level, @ModelAttribute("hash") String hash, @ModelAttribute("loc") int loc, @ModelAttribute("product") int product, @ModelAttribute("id") Integer id){
        Location location = lr.findById(loc).orElseThrow();
        Product pro = pr.findById(product).orElseThrow();
        us.delete(updateableCell);
        us.save(new Cell(name, level, hash, location, pro, id));
        return "redirect:/Cells";
    }

    @GetMapping("/Cells/{id}")
    public String GetByProduct(@PathVariable("id") int id, @ModelAttribute("article") String art, Model model) {

        updateableCell = us.findById(id).orElse(null);

        if(updateableCell == null){
            return "redirect:/Cells";
        }

        model.addAttribute("name", updateableCell.getName());
        model.addAttribute("id", updateableCell.getId());
        model.addAttribute("level", updateableCell.getLevel());
        model.addAttribute("hash", updateableCell.getHash());
        model.addAttribute("loc", updateableCell.getLoc().getAddress());
        model.addAttribute("product", updateableCell.getProduct().getName());

        return "Cell";
    }

    @GetMapping("/goInd")
    public String Index() {

        return "index";
    }
}
