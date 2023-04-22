package lk.ijse.controller;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReservationsBO;
import lk.ijse.dto.ReservationsDTO;

import java.util.ArrayList;
import java.util.List;

public class ReservationDetailsController {



   
    public JFXTextField txtfldKeymoney;
    public JFXDatePicker txtfldperiodDate;
    public JFXButton btnUpdateReservation;
    public TableView tblviewallReservationsnotpaidedit;
    public TableColumn colReservationID;
    public TableColumn colRoomTypeID;
    public TableColumn colStudentID;
    public TableColumn colResStatusAndCreatedDate;
    public TableColumn colReserveExpireDate;
    ReservationsBO reservationsBO;
    List<ReservationsDTO>reserveDTOListForEdit;

    public void initialize(){
        reserveDTOListForEdit=new ArrayList<>();
        reservationsBO = (ReservationsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);


        colReservationID.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        colReserveExpireDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colResStatusAndCreatedDate.setCellValueFactory(new PropertyValueFactory<>("status"));


        loadTable();
    }

    private void loadTable(){
        reserveDTOListForEdit.clear();
       tblviewallReservationsnotpaidedit.getItems().clear();
        ObservableList<ReservationsDTO>reserveDTOObservableList= FXCollections.observableArrayList();
        reserveDTOListForEdit=reservationsBO.viewActiveReservations();
        reserveDTOObservableList.setAll(reserveDTOListForEdit);
        tblviewallReservationsnotpaidedit.setItems(reserveDTOObservableList);
    }


    public void clickedActionEditReservationtbl(MouseEvent mouseEvent) {
        txtfldKeymoney.clear();
        txtfldperiodDate.getEditor().clear();
        txtfldKeymoney.setText(reserveDTOListForEdit.get(tblviewallReservationsnotpaidedit.getSelectionModel().getSelectedIndex()).getStatus());
        txtfldperiodDate.setValue(reserveDTOListForEdit.get(tblviewallReservationsnotpaidedit.getSelectionModel().getSelectedIndex()).getDate());



    }

    public void clickedbtnupdateReservation(ActionEvent actionEvent) {
        Alert alert1=new Alert(Alert.AlertType.INFORMATION);
        alert1.setHeaderText("Reservation Updating Information");
        alert1.setContentText("This reservation updated Ssuccessfully.");

        Alert alert2=new Alert(Alert.AlertType.ERROR);
        alert2.setHeaderText("Reservation Updating Information");
        alert2.setContentText("This reservation Updated not successfully!");


        if(true){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Reservation Updating Confirmation");
            alert.setContentText("Are you sure to want you to update this Reservation?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    ReservationsDTO reserveDTO=new ReservationsDTO(
                            reserveDTOListForEdit.get(tblviewallReservationsnotpaidedit.getSelectionModel().getSelectedIndex()).getRes_id(),txtfldperiodDate.getValue(),
                            reserveDTOListForEdit.get(tblviewallReservationsnotpaidedit.getSelectionModel().getSelectedIndex()).getStudentID(),
                            reserveDTOListForEdit.get(tblviewallReservationsnotpaidedit.getSelectionModel().getSelectedIndex()).getRoomID(),
                            txtfldKeymoney.getText());
                    if(reservationsBO.update(reserveDTO)){
                        loadTable();
                        Platform.runLater(()->
                                alert1.show());

                    }else {
                        Platform.runLater(()->
                                alert2.show());
                    }


                }
            });
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid data entered!");
            alert.setContentText("You have entered invalid data.Please retype and try again. ");
            alert.show();
        }



    }
}
