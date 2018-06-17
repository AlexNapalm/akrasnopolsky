package ru.job4j.ioc.carsale.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.ioc.models.CarBrand;

@Repository
public interface CarBrandRepository extends CrudRepository<CarBrand, Integer> {
}
