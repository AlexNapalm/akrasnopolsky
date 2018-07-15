package ru.job4j.ioc.carsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.ioc.carsale.repository.AdRepository;
import ru.job4j.ioc.carsale.repository.CarBrandRepository;
import ru.job4j.ioc.carsale.domain.Ad;

@Controller
public class List {

    @Autowired
    private AdRepository ads;

    @Autowired
    private CarBrandRepository carbrands;

    @GetMapping("/")
    public String startPage() {
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String getAll(Model model, @RequestParam(required = false) String today, @RequestParam(required = false) String brand) {
        model.addAttribute("brands", carbrands.findAll());

        java.util.List<Ad> list = ads.findByCarbrandNameAndCreated(today, brand);
        model.addAttribute("ads", list);
        return "list";
    }

}
