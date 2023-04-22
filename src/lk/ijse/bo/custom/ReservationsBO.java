package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBo;
import lk.ijse.dto.ReservationsDTO;
import lk.ijse.dto.StudentDTO;

import java.util.List;

public interface ReservationsBO extends SuperBo {
    boolean saveReservation(ReservationsDTO reservationsDTO);

    List<ReservationsDTO> viewAllReservations();

    List<ReservationsDTO> viewActiveReservations();

    List<ReservationsDTO> viewNotpaidReservations();

    boolean update(ReservationsDTO reservationsDTO);

    List<StudentDTO> searchStudentByText(String text);
}
