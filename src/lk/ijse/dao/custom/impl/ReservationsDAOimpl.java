package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ReservationsDAO;
import lk.ijse.entity.Rservations;
import lk.ijse.entity.Student;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ReservationsDAOimpl implements ReservationsDAO {

    @Override
    public List<String> getAll(Session session) {
        return null;
    }

    @Override
    public boolean add(Rservations entity) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Rservations entity) {

        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Student> exist(String id) {
        return null;
    }

    @Override
    public String generateNewID() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Rservations search(String id) {
        return null;
    }

    @Override
    public Rservations findByPk(String pk, Session session) {
        return null;
    }

    @Override
    public List<Rservations> viewAllReservations(Session session) {
        List<Rservations>  reservations= new ArrayList<>();
        String hql = "FROM Rservations ";
        Query query = session.createQuery(hql);
        reservations=query.list();
        return reservations;
    }

    @Override
    public List<Rservations> viewActiveReservations(Session session) {
        List<Rservations>reservations = new ArrayList<>();
        String hql = "SELECT res FROM Rservations res WHERE res.date >= CURRENT_DATE";
        Query query = session.createQuery(hql);
        reservations = query.list();
        return reservations;
    }

    @Override
    public List<Rservations> viewNotpaidReservations(Session session) {
        List<Rservations> reservations = new ArrayList<>();
        String hql = "FROM Rservations res WHERE res.status LIKE '%Status:not%'";
        Query query = session.createQuery(hql);
        reservations = query.list();
        return reservations;
    }
}
