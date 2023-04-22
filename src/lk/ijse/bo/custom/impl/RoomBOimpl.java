package lk.ijse.bo.custom.impl;

import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.RoomDAO;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Room;
import lk.ijse.entity.Student;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class RoomBOimpl implements RoomBO {
    private Session session;
    private Transaction transaction;
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);


    @Override
    public List<String> getAllRooms() {
        return roomDAO.getAll(session);

    }

    @Override
    public boolean addRoom(RoomDTO dto) {
       // System.out.println( roomDAO.add(new Room(dto.getRoom_id(),dto.getType(),dto.getKeymoney(),dto.getQty())));
          return roomDAO.add(new Room(dto.getRoom_id(),dto.getType(),dto.getKeymoney(),dto.getQty()));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) {
        return roomDAO.update(new Room(dto.getRoom_id(),dto.getType(),dto.getKeymoney(),dto.getQty()));

    }

    @Override
    public List<Student> existRoom(String id) {
        return null;
    }

    @Override
    public boolean deleteRoom(String id) {
        return roomDAO.delete(id);
    }

    @Override
    public String generateNewRoomID() {
        return null;
    }

    @Override
    public List<RoomDTO> searchRoomByText(String text) {
        List<RoomDTO> roomList = new ArrayList<>();
        for(Room room:roomDAO.searchRoomByText(text)){
            roomList.add(new RoomDTO(room.getRoom_id(),room.getType(),room.getKeymoney(),room.getQty()));
        }
        return roomList;
    }

    @Override
    public RoomDTO findByPk(String pk) {
        RoomDTO roomDTO = null;
        session=null;
        transaction=null;
        session= FactoryConfiguration.getInstance().getSession();
        transaction=session.beginTransaction();

        try{
            // System.out.println(studentDAO.findByPk(pk,session));

            //conver Room to RoomDTO
           Room room = roomDAO.findByPk(pk,session);
            roomDTO = new RoomDTO(room.getRoom_id(),room.getType(),room.getKeymoney(),room.getQty());
            return roomDTO;

        }catch (HibernateException e){
            if(session!=null) {
                transaction.rollback();
            }
            return  roomDTO;
        }finally {
            session.close();
        }
    }

}
