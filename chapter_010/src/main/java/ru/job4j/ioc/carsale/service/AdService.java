package ru.job4j.ioc.carsale.service;

import ru.job4j.ioc.models.Ad;

import java.util.List;

public interface AdService {

    Ad getById(int id);

    List<Ad> getAll();

    void create(Ad ad);

    void update(Ad ad);

    void delete(Ad ad);

    List<Ad> getAllFiltered(String today, String carbrand);

}
