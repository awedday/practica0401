package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "[А-Я][0-9][0-9][0-9][А-Я][А-Я][0-9][0-9][0-9]")
    @Column(unique = true)
    private String number;

    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @NotBlank
    private Person driver;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Transport(String name, String number, String description, Person driver, Location location, int num) {
        this.setID(num);
        this.name = name;
        this.number = number;
        this.description = description;
        this.driver = driver;
        this.location = location;
    }

    public Transport(String name, String number, String description, Person driver, Location location) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.driver = driver;
        this.location = location;
    }

    public Transport() {

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getDriver() {
        return driver;
    }

    public void setDriver(Person driver) {
        this.driver = driver;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
