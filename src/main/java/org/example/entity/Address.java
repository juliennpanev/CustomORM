package org.example.entity;

import ormFramework.annotation.Column;
import ormFramework.annotation.Entity;
import ormFramework.annotation.Id;

@Entity(tableName = "addresses", definition = "VARCHAR(250)")
public class Address {

    @Id
    private int id;
    @Column(name = "street", columnDefinition = "VARCHAR(20)")
    private String street;
    @Column(name = "street_number", columnDefinition = "INT")

    private String streetNumber;
    @Column(name = "people_count", columnDefinition = "INT")
    private int peopleCount;

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

}



