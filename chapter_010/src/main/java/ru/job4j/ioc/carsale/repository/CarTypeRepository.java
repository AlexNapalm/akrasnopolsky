package ru.job4j.ioc.carsale.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.ioc.models.CarType;

@Repository
public interface CarTypeRepository extends CrudRepository<CarType, Integer> {
}
