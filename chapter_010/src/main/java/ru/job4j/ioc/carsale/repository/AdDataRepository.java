package ru.job4j.ioc.carsale.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.ioc.models.Ad;

import java.util.List;

public interface AdDataRepository extends CrudRepository<Ad, Integer> {
    List<Ad> findByCarbrandNameAndCreated(String carbrandName, String created);
}
