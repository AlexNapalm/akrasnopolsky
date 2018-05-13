package ru.job4j.models;

import java.sql.Timestamp;

public class Ad {
    private  int id;
    private CarBrand carbrand;
    private String model;
    private String description;
    private int year;
    private CarType cartype;
    private int price;
    private Timestamp created;
    private boolean sold;
    private User user;

    public Ad() {
    }

    public Ad(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CarBrand getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(CarBrand carbrand) {
        this.carbrand = carbrand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public CarType getCartype() {
        return cartype;
    }

    public void setCartype(CarType cartype) {
        this.cartype = cartype;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ad{");
        sb.append("id=").append(id);
        sb.append(", carbrand=").append(carbrand);
        sb.append(", model='").append(model).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", year=").append(year);
        sb.append(", cartype=").append(cartype);
        sb.append(", price=").append(price);
        sb.append(", created=").append(created);
        sb.append(", sold=").append(sold);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
