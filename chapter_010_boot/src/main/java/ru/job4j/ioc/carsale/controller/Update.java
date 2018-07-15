package ru.job4j.ioc.carsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.ioc.carsale.repository.AdRepository;
import ru.job4j.ioc.carsale.repository.CarBrandRepository;
import ru.job4j.ioc.carsale.repository.CarTypeRepository;
import ru.job4j.ioc.carsale.domain.Ad;
import ru.job4j.ioc.carsale.domain.CarBrand;
import ru.job4j.ioc.carsale.domain.CarType;
import ru.job4j.ioc.carsale.domain.User;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(value = "/update")
public class Update {

    @Autowired
    private AdRepository ads;

    @Autowired
    private CarBrandRepository carbrands;

    @Autowired
    private CarTypeRepository cartypes;

    @Autowired
    private ServletContext servletContext;

    @GetMapping
    @RequestMapping(value = "/{id}")
    public String getUpdateForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("brands", carbrands.findAll());
        model.addAttribute("carcases", cartypes.findAll());
        model.addAttribute("ad", ads.findById(id));
        return "update";
    }

    @PostMapping
    public String updateAdvertisement(@Validated Ad ad,
                                      @RequestParam("brand_id") int brandId,
                                      @RequestParam("carcase_id") int carcaseId,
                                      @RequestParam("user_id") int userId,
                                      @RequestParam("picture") MultipartFile file) {
        ad.setCarbrand(new CarBrand(brandId));
        ad.setCartype(new CarType(carcaseId));
        ad.setUser(new User(userId));
        ad.setCreated(new Timestamp(new Date().getTime()));
        ads.save(ad);

        //обновление изображения.
        if (!file.isEmpty()) {
            try {
                String filename = file.getOriginalFilename();
                if (filename.lastIndexOf(".") != -1 && filename.lastIndexOf(".") != 0) {
                    String ext =  filename.substring(filename.lastIndexOf("."));
                    File dir = new File(servletContext.getRealPath("") + File.separator + "pic");
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File uploadedFile = new File(dir.getAbsolutePath() + File.separator + ad.getId() + ext);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                    byte[] bytes = file.getBytes();
                    stream.write(bytes);
                    stream.flush();
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/list";
    }
}
