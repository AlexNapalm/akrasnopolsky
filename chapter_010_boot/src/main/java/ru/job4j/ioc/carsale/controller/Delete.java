package ru.job4j.ioc.carsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.ioc.carsale.repository.AdRepository;
import ru.job4j.ioc.carsale.domain.Ad;

@Controller
public class Delete {

    @Autowired
    private AdRepository ads;

    @GetMapping
    @RequestMapping(value = "/delete/{id}")
    public String deleteAdvertisement(@PathVariable("id") int id) {
        ads.delete(new Ad(id));
        return "redirect:/list";
    }
}
