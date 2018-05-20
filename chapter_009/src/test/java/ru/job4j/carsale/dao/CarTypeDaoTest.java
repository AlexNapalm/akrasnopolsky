package ru.job4j.carsale.dao;

import org.junit.Test;
import ru.job4j.models.CarType;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CarTypeDaoTest {
    private final CarTypeDao carcases = new CarTypeDao();

    @Test
    public void createAndGetByIdTest() {
        CarType carType = new CarType();
        carType.setType("test");
        this.carcases.create(carType);
        CarType result = this.carcases.getById(carType.getId());
        assertThat(carType, is(result));
    }

    @Test
    public void getAllTest() {
        CarType carcase = new CarType();
        carcase.setType("test");
        this.carcases.create(carcase);
        assertTrue(this.carcases.getAll().contains(carcase));
    }

}