package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;


    @NotBlank
    @Column(unique = true)
    private String address;

    @ManyToOne
    @JoinColumn(name = "cell_id")
    private Cell CountCell;

    @ManyToOne
    @JoinColumn(name = "persons_id")
    private Person MainPerson;




    public Location(String name, String description, String address, Cell countCell, Person mainPerson, int num) {
        this.setID(num);
        this.name = name;
        this.description = description;
        this.address = address;
        CountCell = countCell;
        MainPerson = mainPerson;
    }

    public Location(String name, String address, String description, Cell countCells, Person mainPerson) {
        this.name = name;
        this.description = description;
        this.address = address;
        CountCell = countCells;
        MainPerson = mainPerson;
    }

    public Location() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cell getCountCell() {
        return CountCell;
    }

    public void setCountCell(Cell countCell) {
        CountCell = countCell;
    }

    public Person getMainPerson() {
        return MainPerson;
    }

    public void setMainPerson(Person mainPerson) {
        MainPerson = mainPerson;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
