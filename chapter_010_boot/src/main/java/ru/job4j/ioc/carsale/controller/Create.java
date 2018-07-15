package ru.job4j.ioc.carsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping(value = "/create")
public class Create {

    @Autowired
    private AdRepository ads;

    @Autowired
    private CarBrandRepository carbrands;

    @Autowired
    private CarTypeRepository cartypes;

    @Autowired
    private ServletContext servletContext;

    @GetMapping
    public String getCreateForm(Model model) {
        model.addAttribute("brands", carbrands.findAll());
        model.addAttribute("carcases", cartypes.findAll());
        return "create";
    }

    @PostMapping
    public String createAdvertisement(@Validated Ad ad,
                                      @RequestParam("brand_id") int brandId,
                                      @RequestParam("carcase_id") int carcaseId,
                                      @RequestParam("user_id") int userId,
                                      @RequestParam("picture") MultipartFile file) {

        ad.setCarbrand(new CarBrand(brandId));
        ad.setCartype(new CarType(carcaseId));
        ad.setUser(new User(userId));
        ad.setCreated(new Timestamp(new Date().getTime()));
        ad.setSold(false);
        ads.save(ad);

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
