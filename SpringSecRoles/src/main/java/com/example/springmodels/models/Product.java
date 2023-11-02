package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 9, max = 9)
    @Pattern(regexp = "[A-Z][A-Z][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")
    @Column(unique = true)
    private String article;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person Owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<Cell> yuo;

    private Integer Quantity;

    private Integer Price;

    public Product(String name, String article, Person owner, Integer quantity, Integer price, Integer num) {
        this.setID(num);
        this.name = name;
        this.article = article;
        this.Owner = owner;
        this.Quantity = quantity;
        this.Price = price;
    }

    public Product(String name, String article, Person owner, Integer quantity, Integer price) {
        this.name = name;
        this.article = article;
        this.Owner = owner;
        this.Quantity = quantity;
        this.Price = price;
    }

    public Product() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Person getOwner() {
        return Owner;
    }

    public void setOwner(Person owner) {
        Owner = owner;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public List<Cell> getYuo() {
        return yuo;
    }

    public void setYuo(List<Cell> yuo) {
        this.yuo = yuo;
    }
}
