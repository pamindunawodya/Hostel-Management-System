package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.text;

public class RoomDAOimpl implements RoomDAO {
    Session session = FactoryConfiguration.getInstance().getSession();
    Transaction transaction = session.beginTransaction();


    @Override
    public List<String> getAll(Session session) {
        List<String> idList = new ArrayList<>();
        String hql = "SELECT s.room_id FROM Room s";
        Query query = this.session.createQuery(hql);
        idList = query.list();
        return idList;
    }

    @Override
    public boolean add(Room entity) {
        System.out.println(entity.toString());
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) {
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
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        Room room=session.get(Room.class,id);
        session.delete(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room search(String id) {
        return null;
    }

    @Override
    public Room findByPk(String pk, Session session) {
        try {
            return session.get(Room.class,pk);

        } catch (HibernateException e) {
            return  null;
        }
    }


    @Override
    public List<Room> searchRoomByText(String text) {
            Session session= FactoryConfiguration.getInstance().getSession();
            List<Room> roomList = new ArrayList<>();

            String hql = "FROM Room E WHERE E.id like '%"+text+"%'";

            Query query = session.createQuery(hql);
            roomList=query.list();
            return roomList;
    }
}
