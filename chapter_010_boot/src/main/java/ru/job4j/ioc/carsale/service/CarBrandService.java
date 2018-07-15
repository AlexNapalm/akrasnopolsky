package ru.job4j.ioc.carsale.service;

import ru.job4j.ioc.carsale.domain.CarBrand;

import java.util.List;

public interface CarBrandService {

    CarBrand getById(int id);

    List<CarBrand> getAll();

    void create(CarBrand carBrand);

    void update(CarBrand carBrand);

    void delete(CarBrand carBrand);
}
