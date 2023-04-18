package org.example;
import ormFramework.core.EntityManager;
import ormFramework.core.EntityManagerFactory;

import java.sql.*;
import java.util.Scanner;

public class ApplicationStarter {
    public static void main(String[] args) throws SQLException {
        EntityManager entityManager = EntityManagerFactory.createConnection("jdbc", "mysql", "localhost", 3306, "soft_uni", ApplicationStarter.class);
    }
}