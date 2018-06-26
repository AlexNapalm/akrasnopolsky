package ru.job4j.ioc.carsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.ioc.carsale.repository.UserRepository;
import ru.job4j.ioc.models.User;

import java.util.List;
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User getById(int id) {
        return this.userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    @Transactional
    public void create(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));

        this.userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        this.userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    @Override
    @Transactional
    public User getByLogin(String login) {
        return this.userRepository.findByLogin(login);
    }
}
