package store.dao;
import store.DAOException;

import java.util.List;

/**
 * This interface declares a DAO patter design
 * @author Mariana Ramirez
 * @version 1.0
 */

public interface DAO<T, K> {
    void create(T a) throws DAOException;
    void update(T a) throws DAOException;
    void delete(T a) throws DAOException;
    List<T> getAll() throws DAOException;
    T get(K id) throws DAOException;
}
