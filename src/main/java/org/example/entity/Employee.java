package org.example.entity;

import ormFramework.annotation.Column;
import ormFramework.annotation.Entity;
import ormFramework.annotation.Id;

@Entity(tableName = "employees", definition = "VARCHAR(250)")
public class Employee {
    @Id
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(250)")
    private String name;

    public Employee() {}
}
