package ormFramework.core;

import ormFramework.annotation.Column;
import ormFramework.annotation.Entity;
import ormFramework.annotation.Id;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class EntityManagerFactory {


    private static Connection connection;
    private static String connectionString = "";

    public static EntityManager createConnection(String connectionDriver, String dbType, String host, int port, String dbName, Class<?> mainClass) throws SQLException, URISyntaxException, ClassNotFoundException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "C1c4da_33O1");

        connectionString = String.format("%s:%S://%s:%d/%s", connectionDriver, dbType, host, port, dbName);

        connection = DriverManager.getConnection(connectionString, props);
        List<Class<?>> classes = getEntities(mainClass);
        System.out.println(Arrays.toString(mainClass.getFields()));

        createTables(connection, classes);

        return new EntityManagerImpl(connection);

    }

    private static void createTables(Connection connection, List<Class<?>> classes) throws SQLException {
        for (Class classInfo : classes) {
            Entity entityInfo = (Entity) classInfo.getAnnotation(Entity.class);

            String sql = "CREATE TABLE IF NOT EXISTS ";
            String tableName = entityInfo.tableName();
            System.out.println(tableName);
            sql += tableName;
            sql += " (\n";
            String primaryKeyDefinition = "";

            for (Field field : classInfo.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    sql += "  " + field.getName() + " int auto_increment, \n";
                    primaryKeyDefinition = "constraint " + tableName + "_pk primary key (" + field.getName() + "))";
                } else if (field.isAnnotationPresent(Column.class)) {
                    Column columnInfo = field.getAnnotation(Column.class);
                    sql += " " + columnInfo.name() + " " + columnInfo.columnDefinition() + ",\n";
                }
            }

            sql += " " + primaryKeyDefinition + "\n;";

            System.out.println(sql);

            connection.createStatement().execute(sql);
        }
    }

    private static List<Class<?>> getEntities(Class<?> mainClass) throws URISyntaxException, ClassNotFoundException, URISyntaxException {
        String path = mainClass.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        String packageName = mainClass.getPackageName();


        File rootDir = new File(path + packageName.replace(".", "/"));
        List<Class<?>> classes = new ArrayList<>();

        scanEntities(
                rootDir,
                packageName,
                classes
        );
        return classes;
    }


    private static List<Class<?>> scanEntities(File dir, String packageName, List<Class<?>> classes) throws ClassNotFoundException {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                System.out.println(file.getName());
                scanEntities(file, packageName + "." + file.getName(), classes);
            } else if (file.getName().endsWith(".class")) {
                Class<?> classInfo = Class.forName(packageName + "." + file
                        .getName().replace(".class", ""));
                if (classInfo.isAnnotationPresent(Entity.class)) {
                    classes.add(classInfo);
                }
                System.out.println();
            }
        }
        return classes;
    }

}
