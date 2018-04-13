package ru.job4j.crud;

public class User {
    private String login;
    private String password;
    private String email;
    private String createDate;
    private Role role = new Role();

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getRole() {
        return role.getId();
    }

    public void setRole(int roleId) {
        this.role.setId(roleId);
    }
}
