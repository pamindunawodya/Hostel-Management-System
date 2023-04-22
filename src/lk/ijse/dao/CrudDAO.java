package lk.ijse.dao;


import lk.ijse.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {

    List<String> getAll(Session session);

    boolean add(T entity);

    boolean update(T entity);

    List<Student> exist(String id);

    String generateNewID();

    boolean delete(String id);

    T search(String id);

    T findByPk(String pk, Session session);
}

