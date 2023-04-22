package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ReservationsBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ReservationsDAO;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.ReservationsDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Room;
import lk.ijse.entity.Rservations;
import lk.ijse.entity.Student;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;


public class ReservationsBOimpl implements ReservationsBO {

    private Session session;
    private Transaction transaction;

    ReservationsDAO reservationsDAO= (ReservationsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);




    @Override
    public boolean saveReservation(ReservationsDTO reservationsDTO) {
        session=null;
        transaction=null;
        session= FactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        Student student=new Student();
        Room room=new Room();
        student=studentDAO.findByPk(reservationsDTO.getStudentID(),session);
        room=roomDAO.findByPk(reservationsDTO.getRoomID(),session);
        Rservations rservations=new Rservations(
                reservationsDTO.getRes_id(),
                reservationsDTO.getDate(),
                room,
                student,
                reservationsDTO.getStatus()
        );
        try{
            reservationsDAO.add(rservations);
            transaction.commit();
            return true;
        }catch (HibernateException e){
            if(session!=null) {
                transaction.rollback();
            }
            return false;
        }finally {
            session.close();
        }


    }

    @Override
    public List<ReservationsDTO> viewAllReservations() {
        List<ReservationsDTO> reserveDTOS=new ArrayList<>();
        session=null;
        transaction=null;
        session= FactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        try{
            for(Rservations reservation:reservationsDAO.viewActiveReservations(session))
                reserveDTOS.add(new ReservationsDTO(reservation.getRes_id(),reservation.getDate(),reservation.getStudent().getId(),reservation.getRoom().getRoom_id(),reservation.getStatus()));
            transaction.commit();

        }catch (HibernateException e){
            if(session!=null) {
                transaction.rollback();
            }
            return reserveDTOS;
        }finally {
            session.close();
            return reserveDTOS;
        }
    }

    @Override
    public List<ReservationsDTO> viewActiveReservations() {

        List<ReservationsDTO> reserveDTOS=new ArrayList<>();
        List<Rservations> reservationList=new ArrayList<>();
        session=null;
        transaction=null;
        session= FactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        try{
            reservationList=reservationsDAO.viewActiveReservations(session);
            transaction.commit();
            for(Rservations reservation:reservationList){
                ReservationsDTO reservationsDTO = new ReservationsDTO();
                reservationsDTO.setRes_id(reservation .getRes_id());
                reservationsDTO.setDate(reservation .getDate());
                reservationsDTO.setStudentID(reservation .getStudent().getId());
                reservationsDTO.setRoomID((reservation.getRoom().getRoom_id()));
                reservationsDTO.setStatus((reservation.getStatus()));
                reserveDTOS.add(reservationsDTO);
            }
        }catch (HibernateException e){
            if(session!=null) {
                transaction.rollback();
            }
        }finally {
            session.close();
            return reserveDTOS;

        }
    }

    @Override
    public List<ReservationsDTO> viewNotpaidReservations() {
        List<ReservationsDTO> reserveDTOS=new ArrayList<>();
        session=null;
        transaction=null;
        session= FactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        try{
            for(Rservations reservation:reservationsDAO.viewAllReservations(session))
                reserveDTOS.add(new ReservationsDTO(reservation.getRes_id(),reservation.getDate(),reservation.getStudent().getId(),reservation.getRoom().getRoom_id(),reservation.getStatus()));
            transaction.commit();
            return reserveDTOS;
        }catch (HibernateException e){
            if(session!=null) {
                transaction.rollback();
            }
            return reserveDTOS;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(ReservationsDTO reservationsDTO) {
        session=null;
        transaction=null;
        session= FactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();
        Student student=new Student();
        Room room=new Room();
        student=studentDAO.findByPk(reservationsDTO.getStudentID(),session);
        room=roomDAO.findByPk(reservationsDTO.getRoomID(),session);
        Rservations rservations=new Rservations(
                reservationsDTO.getRes_id(),
                reservationsDTO.getDate(),
                room,
                student,
                reservationsDTO.getStatus()
        );
        try{
            reservationsDAO.update(rservations);
            System.out.println(reservationsDAO.update(rservations));
            transaction.commit();
            return true;
        }catch (HibernateException e){
            if(session!=null) {
                transaction.rollback();
            }
            return false;
        }finally {
            session.close();
        }

    }

    @Override
    public List<StudentDTO> searchStudentByText(String text) {
        return null;
    }
}
