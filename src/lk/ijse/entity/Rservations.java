package lk.ijse.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Rservations implements SuperEntity {
    @Id
    private String res_id;

    private LocalDate date;

@ManyToOne(targetEntity = Room.class,fetch = FetchType.EAGER)
@JoinColumn(
        name="room_id",referencedColumnName = "room_id"
)
    private Room room;

@ManyToOne(targetEntity = Student.class,fetch = FetchType.EAGER)
@JoinColumn(
        name="id",referencedColumnName = "id"
)
    private Student student;

    private String status;

    public Rservations() {
    }

    public Rservations(String res_id, LocalDate date, Room room, Student student, String status) {
        this.res_id = res_id;
        this.date = date;
        this.room = room;
        this.student = student;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Rservations{" +
                "res_id='" + res_id + '\'' +
                ", date=" + date +
                ", room=" + room +
                ", student=" + student +
                ", status='" + status + '\'' +
                '}';
    }
}

