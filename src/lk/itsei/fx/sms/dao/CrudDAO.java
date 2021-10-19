package lk.itsei.fx.sms.dao;

import java.util.List;

public interface CrudDAO<T, ID> extends SuperDAO {
    boolean add(T entity) throws Exception;
    T find(ID key) throws Exception;
    List<T> findAll() throws Exception;
    boolean update(T entity) throws Exception;
    boolean delete(String key) throws Exception;
}
