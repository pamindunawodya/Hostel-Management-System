package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.entity.Student;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOimpl implements StudentDAO {
    Session session = FactoryConfiguration.getInstance().getSession();
    Transaction transaction = session.beginTransaction();

    @Override
    public List<String> getAll(Session session) {
        List<String> idList = new ArrayList<>();
        String hql = "SELECT s.id FROM Student s";
        Query query = this.session.createQuery(hql);
        idList = query.list();
        return idList;

    }

    @Override
    public boolean add(Student entity) {
        System.out.println(entity.toString());
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Student> exist(String id) {
        String hql = "FROM Student E WHERE E.id = id";
        Query query = session.createQuery(hql);
        List<Student> results = new ArrayList<>();
        results=query.getResultList();
        for (Student c :results){
            System.out.println(c.getName());

        }



        transaction.commit();
        session.close();
        return results;

    }

    @Override
    public String generateNewID() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        Student student=session.get(Student.class,id);
        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String id) {
       return null;

    }

    @Override
    public List<Student> searchRoomByText(String text) {
        Session session= FactoryConfiguration.getInstance().getSession();
        List<Student> studentList = new ArrayList<>();

        String hql = "FROM Student E WHERE E.id like '%"+text+"%'";

        Query query = session.createQuery(hql);
        studentList=query.list();
        return studentList;
    }

    @Override
    public Student findByPk(String pk, Session session) {
        try {
            return session.get(Student.class,pk);

        } catch (HibernateException e) {
            return  null;
        }
    }
}
