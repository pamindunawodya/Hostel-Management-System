package lk.ijse.dao;

import lk.ijse.dao.custom.LoginDetailDAO;
import lk.ijse.dao.custom.impl.LoginDetailDAOimpl;
import lk.ijse.dao.custom.impl.ReservationsDAOimpl;
import lk.ijse.dao.custom.impl.RoomDAOimpl;
import lk.ijse.dao.custom.impl.StudentDAOimpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
       STUDENT,ROOM,RESERVATION,LOGINDETAIL
    }

    public <T> T getDAO(DAOTypes types){
        switch (types) {
            case STUDENT:
                return (T) new StudentDAOimpl();
            case ROOM:
                return (T) new RoomDAOimpl();
            case RESERVATION:
                return (T) new ReservationsDAOimpl();
            case LOGINDETAIL:
                return (T) new LoginDetailDAOimpl();
            default:
                return null;
        }
    }


}
