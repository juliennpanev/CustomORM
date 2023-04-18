package org.example.entity;

import ormFramework.annotation.Column;
import ormFramework.annotation.Entity;
import ormFramework.annotation.Id;

@Entity(tableName = "employees")
public class Employee {
    @Id
    private int id;

    private String name;
}
