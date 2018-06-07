package ru.job4j.ioc.carsale.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.ioc.models.User;

public interface UserDataRepository extends CrudRepository<User, Integer> {
    User findByLoginAndPassword(String login, String password);
}
