package org.example;
import ormFramework.annotation.Entity;
import ormFramework.core.EntityManager;
import ormFramework.core.EntityManagerFactory;

import java.net.URISyntaxException;
import java.sql.*;

public class ApplicationStarter {
    public static void main(String[] args) throws SQLException, URISyntaxException, ClassNotFoundException {
        EntityManager entityManager = EntityManagerFactory.createConnection("jdbc",
                "mysql",
                "localhost",
                3306, "test_orm",
                ApplicationStarter.class);


    }
}