package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;
import lk.ijse.util.Navigation;
import lk.ijse.util.Route;


import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ManageStudentsFormController implements Serializable {
    public JFXButton btnBack;
    public Button btnAddStudent;
    public Button btnUpdateDelete;
    public TableView<StudentDTO> tblStudent;
    public TextField txtSearchStudents;
    public StudentBO studentBO;
    public TableColumn colStudentid;
    public TableColumn calName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colGender;
    List<StudentDTO>studentDTOList;
    private String searchText;



    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {

    }
    public  void initialize(){
        studentDTOList = new ArrayList<>();
        this. studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
      //  btnUpdateDelete.setDisable(true);
//        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
//        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
//        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
//        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
//        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("gender"));

        searchText="";

        colStudentid.setCellValueFactory(new PropertyValueFactory<>("id"));
        calName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        searchStudent(searchText);


    }

    public void btnAddStudentOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/view/AddStudentForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
      AddStudentFormController AddStudentController = fxmlLoader.getController();
      AddStudentController.init(tblStudent,this);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New User Registration Form");
        stage.centerOnScreen();
        stage.show();

    }
    private void loadAllStudents(){



    }

    public void btnUpdateDeleteOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/lk/ijse/view/UpdateStudentForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent load = fxmlLoader.load();
       UpdateStudentFormController controller = fxmlLoader.getController();
        controller.init(studentDTOList.get(tblStudent.getSelectionModel().getSelectedIndex()),this);
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

    public void btnAddRoomOnAction(ActionEvent actionEvent) {

    }


    public void searchStudent(String searchText){

                tblStudent.getItems().clear();
                studentDTOList.clear();
                ObservableList<StudentDTO> studentDTOObservableList = FXCollections.observableArrayList();

                studentDTOList = studentBO.searchStudentByText(searchText);

                studentDTOObservableList.addAll(studentDTOList);

                tblStudent.setItems(studentDTOObservableList);

            }

    public void txtSearchStudentoHostelOnAction(KeyEvent keyEvent) {
        searchStudent(txtSearchStudents.getText());
    }
}


