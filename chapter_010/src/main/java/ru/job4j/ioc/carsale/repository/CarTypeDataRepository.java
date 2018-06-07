package ru.job4j.ioc.carsale.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.ioc.models.CarType;

public interface CarTypeDataRepository extends CrudRepository<CarType, Integer> {
}
