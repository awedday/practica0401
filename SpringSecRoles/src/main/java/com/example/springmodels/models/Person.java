package com.example.springmodels.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    int id;

    @NotBlank
    private String name;

    @NotBlank
    private String secondName;

    private int age;

    @NotBlank
    private String family;



    public Person(String name, String secondName, int age, String family, Integer num) {
        this.setID(num);
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.family = family;
    }

    public Person(String name, String secondname, Integer age, String family) {
        this.name = name;
        this.secondName = secondname;
        this.age = age;
        this.family = family;
    }

    public Person() {

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

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }



}
