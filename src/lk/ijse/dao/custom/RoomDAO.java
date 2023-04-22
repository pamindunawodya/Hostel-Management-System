package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface RoomDAO extends CrudDAO<Room> {
    public List<Room> searchRoomByText(String text);

}
