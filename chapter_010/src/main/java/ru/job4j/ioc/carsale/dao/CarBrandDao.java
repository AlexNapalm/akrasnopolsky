package ru.job4j.ioc.carsale.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.ioc.carsale.repository.CarBrandDataRepository;
import ru.job4j.ioc.models.CarBrand;

import java.util.List;

public class CarBrandDao implements IDao<CarBrand> {
    @Autowired
    CarBrandDataRepository repository;

    @Override
    public CarBrand getById(int id) {
        return this.repository.findById(id).get();
    }

    @Override
    public List<CarBrand> getAll() {
        return (List<CarBrand>) this.repository.findAll();
    }

    @Override
    public void create(CarBrand carBrand) {
        this.repository.save(carBrand);
    }

    @Override
    public void update(CarBrand carBrand) {
        this.repository.save(carBrand);
    }

    @Override
    public void delete(CarBrand carBrand) {
        this.repository.delete(carBrand);
    }

    @Override
    public void close() {
    }
}
