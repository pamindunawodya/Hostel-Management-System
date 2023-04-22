package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ReservationsBO;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.ReservationsDTO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.util.Navigation;
import lk.ijse.util.Route;
import lombok.SneakyThrows;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ReservationFormController {
    public DatePicker cmbDate;
    public ComboBox cmbStudentID;
    public ComboBox cmbRoomID;
    public JFXButton btnBack;

    public TableView tblReservation;
    public TableColumn colRoomID;


    public TextField txtRID;
    public DatePicker txtDate;
    public TextField txtKeyMoney;
    public TableColumn colKeyMoney;
    public TableColumn colAvailableRoom;
    public TableColumn colType;

    public Text resStdID;
    public Text resStdName;
    public Text resStdAddress;
    public Text resStContact;
    public Text resStdGender;
    ArrayList<String> studentIDlist;
    ArrayList<String> roomIDlist;
    ArrayList<ReservationsDTO>reserveDTOArrayList = new ArrayList<>();

    StudentBO studentBO;
    RoomBO roomBO;
    ReservationsBO reservationsBO;
    StudentDTO studentDTO;
    RoomDTO roomDTO;

    public  void initialize(){
        studentDTO=null;

        reservationsBO = (ReservationsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);


        studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

        roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

        //add data into combobox
        ObservableList<String>stringObservableList= FXCollections.observableArrayList();
        studentIDlist= (ArrayList<String>) studentBO.getAllStudents();
        stringObservableList.addAll(studentIDlist);
        cmbStudentID.setItems(stringObservableList);

        //add data into combobox
        ObservableList<String>stringObservableList1= FXCollections.observableArrayList();
        roomIDlist= (ArrayList<String>)roomBO.getAllRooms();
        stringObservableList1.addAll(roomIDlist);
        cmbRoomID.setItems(stringObservableList1);

      colRoomID.setCellValueFactory(new PropertyValueFactory<>("room_id"));
      colType.setCellValueFactory(new PropertyValueFactory<>("type"));
      colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keymoney"));
      colAvailableRoom.setCellValueFactory(new PropertyValueFactory<>("qty"));




    }
    public void btnBackToOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MENU);
    }

    public void cmbDateOnAction(ActionEvent actionEvent) {
    }

    public void cmbStudentIDOnAction(ActionEvent actionEvent) {
        //cmbBoxStudent Id select data  return label
     studentDTO=studentBO.findByPk(studentIDlist.get(cmbStudentID.getSelectionModel().getSelectedIndex()));
     resStdID.setText(studentDTO.getId());
    resStdName.setText(studentDTO.getName());
     resStdAddress.setText(studentDTO.getAddress());
     resStContact.setText(studentDTO.getContact());
     resStdGender.setText(studentDTO.getGender());


    }

    public void cmbRoomIDOnAction(ActionEvent actionEvent) {
//roomDTO=roomBO.findByPk(roomIDlist.get(cmbRoomID.getSelectionModel().getSelectedIndex()));
//colRoomID.setText(roomDTO.getRoom_id());
//colType.setText(roomDTO.getType());
//colKeyMoney.setText(String.valueOf(roomDTO.getKeymoney()));
//colAvailableRoom.setText(String.valueOf(roomDTO.getQty()));
//        roomDTO=roomBO.findByPk(roomIDlist.get(cmbRoomID.getSelectionModel().getSelectedIndex()));
//        colRoomID.setText(roomIDlist.get(cmbRoomID.getSelectionModel().getSelectedIndex()));
//        colType.setText(roomDTO.getType());
        ObservableList<RoomDTO>obl=FXCollections.observableArrayList();
        List<RoomDTO>  roomDTOS = new ArrayList<>();
        RoomDTO roomDTO=roomBO.findByPk(roomIDlist.get(cmbRoomID.getSelectionModel().getSelectedIndex()));
        roomDTOS.add(roomDTO);
        obl.setAll(roomDTOS);
        tblReservation.setItems(obl);


    }


    public void createReservationONAction(ActionEvent actionEvent) {

        Alert alert1=new Alert(Alert.AlertType.INFORMATION);
        alert1.setHeaderText("Reservation Adding Information");
        alert1.setContentText("This reservation created Ssuccessfully.");

        Alert alert2=new Alert(Alert.AlertType.ERROR);
        alert2.setHeaderText("Reservation Adding Information");
        alert2.setContentText("This reservation created not successfully!");

        //persist data using thread
        Thread threadAdd =  new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {

                LocalDate localDate = LocalDate.now();
                ReservationsDTO reserveDTO = new ReservationsDTO(txtRID.getText(), txtDate.getValue(), studentIDlist.get(cmbStudentID.getSelectionModel().getSelectedIndex()),roomIDlist.get(cmbRoomID.getSelectionModel().getSelectedIndex()),
                        "Status:"+txtKeyMoney.getText()+",date:"+localDate
                );
                System.out.println("new"+reserveDTO.toString());
                reservationsBO.saveReservation(reserveDTO);
            }
        });
        if(true){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Reservation Adding Confirmation");
            alert.setContentText("Are you sure to want you to add this Reserve?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    threadAdd.start();  //start thread
                }
            });
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid data entered!");
            alert.setContentText("You have entered invalid data.Please retype and try again. ");

        }
    }

    public void tblReservationOnAction(SortEvent<TableView> tableViewSortEvent) {

    }
}
