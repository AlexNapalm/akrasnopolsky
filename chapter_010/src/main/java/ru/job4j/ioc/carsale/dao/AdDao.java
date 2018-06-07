package ru.job4j.ioc.carsale.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.ioc.carsale.repository.AdDataRepository;
import ru.job4j.ioc.models.Ad;

import java.util.List;

public class AdDao implements IDao<Ad> {

    @Autowired
    AdDataRepository repository;

    @Override
    public Ad getById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Ad> getAll() {
        return (List<Ad>) this.repository.findAll();
    }

    @Override
    public void create(Ad ad) {
        this.repository.save(ad);
    }

    @Override
    public void update(Ad ad) {
        this.repository.save(ad);
    }

    @Override
    public void delete(Ad ad) {
        this.repository.delete(ad);
    }

    public List<Ad> getAllFiltered(String today, String carbrand) {
        List<Ad> result;

        if (today == null && carbrand == null) {
            result = (List<Ad>) this.repository.findAll();
        } else {
            result = this.repository.findByCarbrandNameAndCreated(carbrand, today);
        }
        return result;
    }

    @Override
    public void close() {
    }
}
