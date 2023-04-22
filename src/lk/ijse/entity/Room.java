package lk.ijse.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @Id
    private String room_id;
    private String type;
    private Double keymoney;
    int qty;

    public Room() {
    }
    @OneToMany(mappedBy = "room")
    private List<Rservations>reservationList=new ArrayList<>();

    public Room(String room_id, String type, Double keymoney, int qty) {
        this.room_id = room_id;
        this.type = type;
        this.keymoney = keymoney;
        this.qty = qty;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getKeymoney() {
        return keymoney;
    }

    public void setKeymoney(Double keymoney) {
        this.keymoney = keymoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id='" + room_id + '\'' +
                ", type='" + type + '\'' +
                ", keymoney=" + keymoney +
                ", qty=" + qty +
                '}';
    }
}
