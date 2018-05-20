package ru.job4j.carsale.dao;

import org.junit.Test;
import ru.job4j.models.CarBrand;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarBrandDaoTest {
    private final CarBrandDao carBrandDao = new CarBrandDao();

    @Test
    public void createAndGetByIdTest() {
        CarBrand carBrand = new CarBrand();
        carBrand.setName("test");
        this.carBrandDao.create(carBrand);
        CarBrand result = this.carBrandDao.getById(carBrand.getId());
        assertThat(carBrand, is(result));
    }

    @Test
    public void getAllTest() {
        CarBrand carBrand = new CarBrand();
        carBrand.setName("test");
        this.carBrandDao.create(carBrand);
        assertTrue(this.carBrandDao.getAll().contains(carBrand));
    }

}