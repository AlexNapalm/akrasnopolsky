package ru.job4j.ioc.carsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.ioc.carsale.repository.CarBrandRepository;
import ru.job4j.ioc.carsale.domain.CarBrand;

import java.util.List;
@Service
public class CarBrandServiceImpl implements CarBrandService {
    @Autowired
    private CarBrandRepository carBrandRepository;

    @Override
    @Transactional
    public CarBrand getById(int id) {
        return this.carBrandRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<CarBrand> getAll() {
        return (List<CarBrand>) this.carBrandRepository.findAll();
    }

    @Override
    @Transactional
    public void create(CarBrand carBrand) {
        this.carBrandRepository.save(carBrand);
    }

    @Override
    @Transactional
    public void update(CarBrand carBrand) {
        this.carBrandRepository.save(carBrand);
    }

    @Override
    @Transactional
    public void delete(CarBrand carBrand) {
        this.carBrandRepository.delete(carBrand);
    }
}
