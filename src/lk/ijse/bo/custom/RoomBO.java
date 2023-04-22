package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;

import java.util.ArrayList;
import java.util.List;

public interface RoomBO extends SuperBo {
    List<String> getAllRooms();

    boolean addRoom(RoomDTO dto)  ;

    boolean updateRoom(RoomDTO dto) ;

    List<Student> existRoom(String id) ;

    boolean deleteRoom(String id) ;

    String generateNewRoomID() ;

    List<RoomDTO> searchRoomByText(String text);

     RoomDTO findByPk(String pk) ;

}
