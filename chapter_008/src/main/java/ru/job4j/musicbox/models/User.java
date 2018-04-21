package ru.job4j.musicbox.models;

import java.util.List;

public class User {
    private int id;
    private String login;
    private String password;
    private String email;
    private Role role;
    private Address address;
    private List<MusicType> musicTypes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<MusicType> getMusicTypes() {
        return musicTypes;
    }

    public void setMusicTypes(List<MusicType> musicTypes) {
        this.musicTypes = musicTypes;
    }
}
