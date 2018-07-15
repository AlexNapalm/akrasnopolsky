package ru.job4j.ioc.carsale.domain;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "ads_cs")
public class Ad {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private  int id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "carbrand_id")
    private CarBrand carbrand;

    @Column(name = "model")
    private String model;

    @Column(name = "description")
    private String description;

    @Column(name = "manufacture_year")
    private int year;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "cartype_id")
    private CarType cartype;

    @Column(name = "price")
    private int price;

    @Column(name = "publish_date")
    private Timestamp created;

    @Column(name = "sold")
    private boolean sold;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ad ad = (Ad) o;
        return id == ad.id
                && year == ad.year
                && price == ad.price
                && sold == ad.sold
                && Objects.equals(carbrand, ad.carbrand)
                && Objects.equals(model, ad.model)
                && Objects.equals(description, ad.description)
                && Objects.equals(cartype, ad.cartype)
                && Objects.equals(created, ad.created)
                && Objects.equals(user, ad.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carbrand, model, description, year, cartype, price, created, sold, user);
    }
}
