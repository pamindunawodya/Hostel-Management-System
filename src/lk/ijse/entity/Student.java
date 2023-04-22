package lk.ijse.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student implements SuperEntity {
@Id
    private String id;
    private String name;
    private String address;
    private String contact;
    private String gender;

    @OneToMany(mappedBy = "student")
    private List<Rservations>reservationList=new ArrayList<>();

    public Student() {
    }

    public Student(String id, String name, String address, String contact, String gender) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
