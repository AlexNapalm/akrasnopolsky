package ru.job4j.ioc.carsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.ioc.carsale.repository.AdRepository;
import ru.job4j.ioc.models.Ad;

import java.util.List;
@Component
public class AdServiceImpl implements AdService {
    @Autowired
    private AdRepository adRepository;

    @Override
    @Transactional
    public Ad getById(int id) {
        return this.adRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<Ad> getAll() {
        return (List<Ad>) this.adRepository.findAll();
    }

    @Override
    @Transactional
    public void create(Ad ad) {
        this.adRepository.save(ad);
    }

    @Override
    @Transactional
    public void update(Ad ad) {
        this.adRepository.save(ad);
    }

    @Override
    @Transactional
    public void delete(Ad ad) {
        this.adRepository.delete(ad);
    }

    @Override
    @Transactional
    public List<Ad> getAllFiltered(String today, String carbrand) {
        List<Ad> result;

        if (today == null && carbrand == null) {
            result = (List<Ad>) this.adRepository.findAll();
        } else {
            result = this.adRepository.findByCarbrandNameAndCreated(carbrand, today);
        }
        return result;
    }
}
