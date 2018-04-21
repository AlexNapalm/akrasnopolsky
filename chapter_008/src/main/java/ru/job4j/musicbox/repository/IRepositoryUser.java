package ru.job4j.musicbox.repository;

import ru.job4j.musicbox.models.Role;
import ru.job4j.musicbox.models.User;

import java.util.List;

/**
 * Интерфейс репозитория для пользователя.
 */
public interface IRepositoryUser {

    //поиск пользователей по адресу
    List<User> getUsersByAddress(String address);

    //поиск пользователей по роли
    List<User> getUsersByRole(Role role);
}
