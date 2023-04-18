package ormFramework.core;

import ormFramework.annotation.Column;
import ormFramework.annotation.Entity;
import ormFramework.annotation.Id;

import java.lang.reflect.Field;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class EntityManagerFactory implements EntityManager {

    private static Connection connection;
    private static String connectionString = "";

    public static EntityManager createConnection(String connectionDriver, String dbType, String host, int port, String dbName, Class<?> mainClass) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "C1c4da_33O1");

        connectionString = String.format("%s:%S://%s:%d/%s", connectionDriver, dbType, host, port, dbName);

        connection = DriverManager.getConnection(connectionString);

        System.out.println(Arrays.toString(mainClass.getFields()));

        return null;

    }

    private static void createTables(Connection connection, List<Class<?>> classes) {
        for (Class classInfo : classes) {
            Entity entityInfo = (Entity) classInfo.getAnnotation(Entity.class);

            String sql = "CREATE TABLE ";
            String tableName = entityInfo.annotationType().getName();
            System.out.println(tableName);

            sql += " (\n";
            String primaryKeyDefinition = "";

            for (Field field : classInfo.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    sql += "  " + field.getName() + " int auto_increment, \n";
                    primaryKeyDefinition = "constraint " + tableName + "_pk primery key (" + field.getName() + "\n)";
                } else if (field.isAnnotationPresent(Column.class)) {
                    Column columnInfo = field.getAnnotation(Column.class);
                    sql = " " + columnInfo.name() + " " + columnInfo.columnDefinition() + ",\n";
                }
            }

            sql += " " + primaryKeyDefinition + "\n;";

            System.out.println(sql);
        }
    }





    @Override
    public <T> T findById(int id, Class<T> type) {
        return null;
    }
}
