package ru.job4j.ioc.carsale.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.ioc.models.CarBrand;

public interface CarBrandDataRepository extends CrudRepository<CarBrand, Integer> {
}
