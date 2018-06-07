package ru.job4j.ioc.carsale.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.ioc.carsale.repository.CarTypeDataRepository;
import ru.job4j.ioc.models.CarType;

import java.util.List;

public class CarTypeDao implements IDao<CarType> {
    @Autowired
    CarTypeDataRepository repository;

    @Override
    public CarType getById(int id) {
        return this.repository.findById(id).get();
    }

    @Override
    public List<CarType> getAll() {
        return (List<CarType>) this.repository.findAll();
    }

    @Override
    public void create(CarType carType) {
        this.repository.save(carType);
    }

    @Override
    public void update(CarType carType) {
        this.repository.save(carType);
    }

    @Override
    public void delete(CarType carType) {
        this.repository.delete(carType);
    }

    @Override
    public void close() {
    }
}
