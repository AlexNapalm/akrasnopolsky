package ru.job4j.ioc.carsale.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.ioc.models.Ad;

import java.util.List;

@Repository
public interface AdRepository extends CrudRepository<Ad, Integer> {
    List<Ad> findByCarbrandNameAndCreated(String carbrandName, String created);
}
