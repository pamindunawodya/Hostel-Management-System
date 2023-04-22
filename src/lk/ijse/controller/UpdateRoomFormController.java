package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.bo.exception.NotFoundException;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;

import java.util.Optional;

public class UpdateRoomFormController {

    public TextField txtName;
    public TextField txtType;
    public TextField txtKeyMoney;
    public TextField txtQty;
    public Label lblRoomId;
    public RoomDTO roomDTO;
    public RoomBO roomBO;
    public TableView<RoomDTO> tblRoom;
    public ManageRoomController manageRoomController;
    public Button btnDelete;


    public void init(RoomDTO roomDTO, ManageRoomController manageRoomController) {
        this.roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
        this.manageRoomController = manageRoomController;
        this.tblRoom = tblRoom;
        fillAllFields(roomDTO);

    }

    private void fillAllFields(RoomDTO roomDTO) {
        lblRoomId.setText(roomDTO.getRoom_id());
        txtType.setText(roomDTO.getType());
        txtKeyMoney.setText(String.valueOf((roomDTO.getKeymoney())));
        txtQty.setText(String.valueOf(roomDTO.getQty()));

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        RoomDTO update = new RoomDTO(lblRoomId.getText(), txtType.getText(), Double.parseDouble(txtKeyMoney.getText()), Integer.parseInt(txtQty.getText()));

        try {
            roomBO.updateRoom(update);
            int selectedIndex = manageRoomController.tblRooms.getSelectionModel().getSelectedIndex();
            manageRoomController.tblRooms.getItems().add(selectedIndex, new RoomDTO(update.getRoom_id(), update.getType(), update.getKeymoney(), update.getQty()));
            manageRoomController.tblRooms.getItems().remove(selectedIndex+1);
            new Alert(Alert.AlertType.INFORMATION, "RoomDTO has been successfully updated!").show();

        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {

            Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure to delete the student", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                roomBO.deleteRoom(roomDTO.getRoom_id());
                new Alert(Alert.AlertType.INFORMATION, "Room delete successful").show();
                manageRoomController.tblRooms.getItems().
                        removeAll(manageRoomController.tblRooms.getSelectionModel().getSelectedItem());
                btnDelete.getScene().getWindow().hide();

            }

        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            return;
        }


    }
}