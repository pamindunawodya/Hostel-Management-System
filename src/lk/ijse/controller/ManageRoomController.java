package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RoomBO;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.util.Navigation;
import lk.ijse.util.Route;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ManageRoomController {
    public Button btnAddRoom;
    public TableView<RoomDTO>tblRooms;
    public TextField txtSearchStudents;
    public Button btnUpdateDelete;
    public JFXButton btnBack;
    public TableColumn colID;
    public TableColumn ColType;
    public TableColumn cloKeyMoney;
    public TableColumn colQty;
    List<RoomDTO> roomDTOList;
    public RoomBO roomBO;
    private String searchText;



    public  void initialize(){
        roomDTOList = new ArrayList<>();
        roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

        searchText="";

        colID.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        cloKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keymoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));



       searchRoom( searchText);

    }

    public void btnAddRoomOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/view/AddRoomForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        AddRoomFormController AddRoomController = fxmlLoader.getController();
        AddRoomController.init(tblRooms,this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New User Registration Form");
        stage.centerOnScreen();
        stage.show();
    }

    public void btnUpdateDeleteOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/view/UpdateRoomForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
        UpdateRoomFormController updateRoomController = fxmlLoader.getController();
        updateRoomController.init(roomDTOList.get(tblRooms.getSelectionModel().getSelectedIndex()), this);
        Stage stage = new Stage();
        stage.setTitle("Update/Delete MemberDTO details");
        stage.setScene(new Scene(load));
        stage.centerOnScreen();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }


    public void btnBackToOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MENU);
    }

    public void searchRoom(String searchText){

        tblRooms.getItems().clear();
        roomDTOList.clear();
        ObservableList<RoomDTO> roomDTOObservableList = FXCollections.observableArrayList();

        roomDTOList = roomBO.searchRoomByText(searchText);

        roomDTOObservableList.addAll(roomDTOList);

        tblRooms.setItems(roomDTOObservableList);

    }


    public void txtSearchRoomOnAction(KeyEvent keyEvent) {
        searchRoom(txtSearchStudents.getText());
    }
}
