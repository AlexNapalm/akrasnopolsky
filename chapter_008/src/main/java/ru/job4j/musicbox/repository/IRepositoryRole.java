package ru.job4j.musicbox.repository;

import ru.job4j.musicbox.models.Role;

import java.util.List;

/**
 * Интерфейс репозитория для роли.
 */
public interface IRepositoryRole {

    //поиск всех связанных с ролью сущностей
    List getRoleRelatedUsers(Role role);
}
