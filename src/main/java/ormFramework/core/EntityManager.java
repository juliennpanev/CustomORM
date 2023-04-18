package ormFramework.core;

import java.sql.Connection;

public interface EntityManager {
    <T> T findById(int id, Class<T> type);

}
