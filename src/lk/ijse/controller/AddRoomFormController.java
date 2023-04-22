package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.bo.exception.DuplicateException;
import lk.ijse.bo.exception.NotFoundException;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Room;
import lk.ijse.util.FactoryConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddRoomFormController {


    public TextField txtID;
    public TextField txtRoomID;
    public TextField txtRoomType;
    public TextField txtRoomKeyMoney;
    public TextField txtRoomQty;

    public RoomBO roomBO;
    public TableView<RoomDTO> tblRoom;
    public ManageRoomController manageRoomController;


    public void init(TableView <RoomDTO>tblRoom,ManageRoomController manageRoomController) {
        this. roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
        this.manageRoomController=manageRoomController;
        this.tblRoom=tblRoom;
    }




    public void btnRegisterOnAction(ActionEvent actionEvent) {

        if (txtRoomID.getText().isEmpty() || !txtRoomID.getText().matches("^R\\d{3}$")) {
            new Alert(Alert.AlertType.ERROR, "RoomID empty or invalid").show();
            txtRoomID.selectAll();
            txtRoomID.requestFocus();
            return;
        } else if (txtRoomType.getText().isEmpty() || !txtRoomType.getText().matches("^(AC|non-AC)$")) {
            new Alert(Alert.AlertType.ERROR, " Room Type cannot be empty ").show();
            txtRoomType.selectAll();
            txtRoomType.requestFocus();
            return;
        } else if (txtRoomKeyMoney.getText().isEmpty() || !txtRoomKeyMoney.getText().matches("^[1-9]\\d*(\\.\\d+)?$")) {
            new Alert(Alert.AlertType.ERROR, "RoomKey Money cannot be empty").show();
            txtRoomKeyMoney.selectAll();
            txtRoomKeyMoney.requestFocus();
            return;
        } else if (txtRoomQty.getText().isEmpty() || !txtRoomQty.getText().matches("^[1-9]\\d*$")) {
            new Alert(Alert.AlertType.ERROR, "Room Qty cannot be empty").show();
            txtRoomQty.selectAll();
            txtRoomQty.requestFocus();
            return;
        }
        RoomDTO room = new RoomDTO(txtRoomID.getText(), txtRoomType.getText(), Double.parseDouble(txtRoomKeyMoney.getText()), Integer.parseInt(txtRoomQty.getText()));

        try {
            boolean isAdded=roomBO.addRoom(room);
                if (isAdded){
                    new Alert(Alert.AlertType.CONFIRMATION, "New Room Added!").show();
                }
            }catch(HibernateException e){
            new Alert((Alert.AlertType.ERROR), "RoomDTO Data Not Added!"+e.getMessage()).show();
        }

        manageRoomController.tblRooms.getItems().add(new RoomDTO(room.getRoom_id(), room.getType(), room.getKeymoney(), room.getQty()));
    }


}




