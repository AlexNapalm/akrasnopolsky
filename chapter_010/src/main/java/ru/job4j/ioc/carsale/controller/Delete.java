package ru.job4j.ioc.carsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.ioc.carsale.service.AdService;
import ru.job4j.ioc.models.Ad;

@Controller
public class Delete {

    @Autowired
    AdService adService;

    @GetMapping
    @RequestMapping(value = "delete/{id}")
    public String deleteAdvertisement(@PathVariable("id") int id) {
        adService.delete(new Ad(id));
        return "redirect:list";
    }
}
