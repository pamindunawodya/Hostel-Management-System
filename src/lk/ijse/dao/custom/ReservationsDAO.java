package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Rservations;
import org.hibernate.Session;

import java.util.List;

public interface ReservationsDAO extends CrudDAO<Rservations> {

    List<Rservations> viewAllReservations(Session session);

    List<Rservations> viewActiveReservations(Session session);

    List<Rservations>viewNotpaidReservations(Session session);
}
