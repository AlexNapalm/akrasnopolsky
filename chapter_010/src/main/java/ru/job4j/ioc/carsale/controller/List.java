package ru.job4j.ioc.carsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.ioc.carsale.dao.AdDao;
import ru.job4j.ioc.carsale.dao.CarBrandDao;
import ru.job4j.ioc.models.Ad;

@Controller
@RequestMapping(value = "list")
public class List {

    @Autowired
    private AdDao adDao;

    @Autowired
    private CarBrandDao carBrandDao;

    @GetMapping
    public String getAll(Model model,
                         @RequestParam(required = false) String today,
                         @RequestParam(required = false) String brand) {
        model.addAttribute("brands", carBrandDao.getAll());
        java.util.List<Ad> list = adDao.getAllFiltered(today, brand);
        model.addAttribute("ads", list);
        return "list";
    }
}
