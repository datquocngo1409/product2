package com.codegym.product2.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Size(min = 10, max = 10)
    private String code;

    @Size(min = 1, max = 255)
    private String name;

    @Min(0)
    private double price;

    @Min(0)
    @Max(255)
    private int quantity;

    @Size(min = 1,max = 255)
    private String description;

    @Min(0)
    private double discount;

    private String color;

    @Min(1)
    @Max(3)
    private int status;

    private String distributor;

    public Product() {
    }

    public Product(Category category, @Size(min = 10, max = 10) String code, @Size(min = 1, max = 255) String name, @Min(0) double price, @Min(0) @Max(255) int quantity, @Size(min = 1, max = 255) String description, @Min(0) double discount, String color, @Min(1) @Max(3) int status, String distributor) {
        this.category = category;
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.discount = discount;
        this.color = color;
        this.status = status;
        this.distributor = distributor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }
}
