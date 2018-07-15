package ru.job4j.ioc.carsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.ioc.carsale.repository.CarTypeRepository;
import ru.job4j.ioc.carsale.domain.CarType;

import java.util.List;
@Service
public class CarTypeServiceImpl implements CarTypeService {
    @Autowired
    private CarTypeRepository carTypeRepository;

    @Override
    @Transactional
    public CarType getById(int id) {
        return this.carTypeRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<CarType> getAll() {
        return (List<CarType>) this.carTypeRepository.findAll();
    }

    @Override
    @Transactional
    public void create(CarType carType) {
        this.carTypeRepository.save(carType);
    }

    @Override
    @Transactional
    public void update(CarType carType) {
        this.carTypeRepository.save(carType);
    }

    @Override
    @Transactional
    public void delete(CarType carType) {
        this.carTypeRepository.delete(carType);
    }
}
