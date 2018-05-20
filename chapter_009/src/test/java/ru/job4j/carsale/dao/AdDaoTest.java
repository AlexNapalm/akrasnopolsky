package ru.job4j.carsale.dao;

import org.junit.Test;
import ru.job4j.models.Ad;
import ru.job4j.models.CarBrand;
import ru.job4j.models.CarType;
import ru.job4j.models.User;

import java.sql.Timestamp;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class AdDaoTest {

    private final AdDao adDao = new AdDao();
    private final CarBrandDao carBrandDao = new CarBrandDao();
    private final CarTypeDao carTypeDao = new CarTypeDao();
    private final UserDao userDao = new UserDao();

    @Test
    public void getByIdTest() {
        Ad ad = new Ad();
        this.adDao.create(ad);
        Ad result = this.adDao.getById(ad.getId());
        assertThat(ad, is(result));
    }

    @Test
    public void getAllTest() {
        Ad ad = new Ad();
        this.adDao.create(ad);
        assertTrue(this.adDao.getAll().contains(ad));
    }

    @Test
    public void createTest() {
        Ad ad = new Ad();
        CarBrand carBrand = new CarBrand();
        CarType carType = new CarType();
        User user = new User();
        this.carBrandDao.create(carBrand);
        this.carTypeDao.create(carType);
        this.userDao.create(user);
        ad.setCarbrand(carBrand);
        ad.setModel("Focus");
        ad.setDescription("your best choice");
        ad.setYear(2018);
        ad.setCartype(carType);
        ad.setPrice(1000);
        ad.setCreated(new Timestamp(System.currentTimeMillis()));
        ad.setSold(false);
        ad.setUser(user);
        this.adDao.create(ad);
        Ad result = this.adDao.getById(ad.getId());
        assertThat(ad, is(result));
    }

    @Test
    public void updateTest() {
        Ad ad = new Ad();
        CarBrand carBrand = new CarBrand();
        CarType carType = new CarType();
        User user = new User();
        this.carBrandDao.create(carBrand);
        this.carTypeDao.create(carType);
        this.userDao.create(user);
        ad.setCarbrand(carBrand);
        ad.setModel("Focus");
        ad.setDescription("your best choice");
        ad.setYear(2018);
        ad.setCartype(carType);
        ad.setPrice(1000);
        ad.setCreated(new Timestamp(System.currentTimeMillis()));
        ad.setSold(false);
        ad.setUser(user);
        this.adDao.create(ad);
        assertThat(ad, is(this.adDao.getById(ad.getId())));

        CarBrand carBrand2 = new CarBrand();
        CarType carcase2 = new CarType();
        User user2 = new User();
        this.carBrandDao.create(carBrand2);
        this.carTypeDao.create(carcase2);
        this.userDao.create(user2);
        ad.setCarbrand(carBrand2);
        ad.setModel("Fiesta");
        ad.setDescription("updated description");
        ad.setYear(2017);
        ad.setCartype(carcase2);
        ad.setPrice(20000);
        ad.setCreated(new Timestamp(System.currentTimeMillis()));
        ad.setSold(true);
        ad.setUser(user2);
        assertThat(ad, not(this.adDao.getById(ad.getId())));
        this.adDao.update(ad);
        assertThat(ad, is(this.adDao.getById(ad.getId())));
    }

    @Test
    public void deleteTest() {
        Ad ad = new Ad();
        this.adDao.create(ad);
        assertTrue(this.adDao.getAll().contains(ad));
        this.adDao.delete(ad);
        assertFalse(this.adDao.getAll().contains(ad));
    }

    @Test
    public void getAllFilteredByBrandTest() {
        Ad ad = new Ad();
        CarBrand brand = new CarBrand();
        this.carBrandDao.create(brand);
        ad.setCarbrand(brand);
        this.adDao.create(ad);
        String brandId = String.valueOf(brand.getId());
        boolean result = this.adDao.getAllFiltered(null, brandId).contains(ad);
        assertTrue(result);
    }

    @Test
    public void getAllFilteredForTodayTest() {
        Ad ad = new Ad();
        ad.setCreated(new Timestamp(System.currentTimeMillis()));
        this.adDao.create(ad);
        boolean result = this.adDao.getAllFiltered("on", null).contains(ad);
        assertTrue(result);
    }
}