package lk.ijse.dto;

import lk.ijse.entity.Room;
import lk.ijse.entity.Student;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class ReservationsDTO {
    private String res_id;

    private LocalDate date;

    private String studentID;

    private String roomID;

    private String status;

    public ReservationsDTO() {
    }

    public ReservationsDTO(String res_id, LocalDate date, String studentID, String roomID, String status) {
        this.res_id = res_id;
        this.date = date;
        this.studentID = studentID;
        this.roomID = roomID;
        this.status = status;
    }

    public String getRes_id() {
        return res_id;
    }

    public void setRes_id(String res_id) {
        this.res_id = res_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReservationsDTO{" +
                "res_id='" + res_id + '\'' +
                ", date=" + date +
                ", studentID='" + studentID + '\'' +
                ", roomID='" + roomID + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
