package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "cell")
public class Cell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    private Integer level;

    @NotBlank
    @Column(unique = true)
    private String hash;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location loc;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Cell(String name, int level, String hash, Location loc, Product product, int num) {
        this.setID(num);
        this.name = name;
        this.level = level;
        this.hash = hash;
        this.loc = loc;
        this.product = product;
    }

    public Cell(String name, Integer level, String hash, Location loc, Product product) {

        this.name = name;
        this.level = level;
        this.hash = hash;
        this.loc = loc;
        this.product = product;
    }

    public Cell() {

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
