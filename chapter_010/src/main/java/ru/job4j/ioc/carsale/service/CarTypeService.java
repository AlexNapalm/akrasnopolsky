package ru.job4j.ioc.carsale.service;

import ru.job4j.ioc.models.CarType;

import java.util.List;

public interface CarTypeService {

    CarType getById(int id);

    List<CarType> getAll();

    void create(CarType carType);

    void update(CarType carType);

    void delete(CarType carType);
}
