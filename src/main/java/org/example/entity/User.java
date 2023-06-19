package org.example.entity;

import ormFramework.annotation.Column;
import ormFramework.annotation.Entity;
import ormFramework.annotation.Id;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

@Entity(tableName = "users", definition = "VARCHAR(250)")
public class User {

    @Id
    private long id;

    @Column(name = "username", columnDefinition = "VARCHAR(10)")
    private String username;

    @Column(name = "age", columnDefinition = "INT")
    private int age;

    @Column(name = "registration_date", columnDefinition = "DATE")
    private LocalDate registrationDate;

    public User(){}
    public User(String username, int age, LocalDate registrationDate) {
        this.age = age;
        this.username = username;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
