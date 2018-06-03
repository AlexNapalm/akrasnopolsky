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
import ru.job4j.ioc.carsale.dao.AdDao;
import ru.job4j.ioc.carsale.dao.CarBrandDao;
import ru.job4j.ioc.carsale.dao.CarTypeDao;
import ru.job4j.ioc.models.Ad;
import ru.job4j.ioc.models.CarBrand;
import ru.job4j.ioc.models.CarType;
import ru.job4j.ioc.models.User;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;


@Controller
@RequestMapping(value = "create")
public class Create {

    @Autowired
    private AdDao adDao;

    @Autowired
    private CarBrandDao carBrandDao;

    @Autowired
    private CarTypeDao carTypeDao;

    @Autowired
    private ServletContext servletContext;

    @GetMapping
    public String getCreateForm(Model model) {
        model.addAttribute("brands", carBrandDao.getAll());
        model.addAttribute("carcases", carTypeDao.getAll());
        return "create";
    }

    @PostMapping
    public String createAdvertisement(@Validated Ad ad,
                                      @RequestParam("carbrand") int carBrandId,
                                      @RequestParam("cartype") int carTypeId,
                                      @RequestParam("user") int userId,
                                      @RequestParam("picture") MultipartFile file) {

        ad.setCarbrand(new CarBrand(carBrandId));
        ad.setCartype(new CarType(carTypeId));
        ad.setUser(new User(userId));
        ad.setCreated(new Timestamp(new Date().getTime()));
        ad.setSold(false);
        adDao.create(ad);

        if (!file.isEmpty()) {
            try {
                String filename = file.getOriginalFilename();
                if (filename.lastIndexOf(".") != -1 && filename.lastIndexOf(".") != 0) {
                    String ext =  filename.substring(filename.lastIndexOf("."));
                    File dir = new File(servletContext.getRealPath("") + File.separator + "uploads");
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
        return "redirect:list";
    }
}
