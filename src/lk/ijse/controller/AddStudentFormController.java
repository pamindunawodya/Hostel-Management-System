package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.bo.exception.DuplicateException;
import lk.ijse.dto.RoomDTO;
import lk.ijse.dto.StudentDTO;

public class AddStudentFormController {


    public TextField txtAddress;
    public TextField txtID;
    public TextField txtName;
    public TextField txtContact;
    public TextField txtGender;
    public Button btnRegister;
    public TableView<StudentDTO> tblStudent;

    public StudentBO studentBO;
     public TableView<StudentDTO> tblStudents;
     public ManageStudentsFormController manageStudentsFormController;



    public void init(TableView<StudentDTO> tblStudents , ManageStudentsFormController manageStudentsFormController) {
            this. studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
            this.manageStudentsFormController=manageStudentsFormController;
            this.tblStudents=tblStudents;



    }
    public void initialize() {
//        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
//        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
//        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
//        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
//        tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("gender"));
//
//          initUI();

    }

    private void initUI() {
        txtID.clear();
    }

    public void btnRegisterStudentOnAction(ActionEvent actionEvent) {



        if (txtID.getText().isEmpty() || !txtID.getText().matches("S\\d{3}")) {
            new Alert(Alert.AlertType.ERROR,"StudentDTO ID empty or invalid").show();
            txtID.selectAll();
            txtID.requestFocus();
            return;
        }
        else if (txtName.getText().isEmpty() || !txtName.getText().matches("^[A-Za-z ]+$")){
            new Alert(Alert.AlertType.ERROR,"Name cannot be empty").show();
            txtName.selectAll();
            txtName.requestFocus();
            return;
        }else if (txtAddress.getText().isEmpty() || !txtAddress.getText().matches("^[A-Za-z ]+$")){
            new Alert(Alert.AlertType.ERROR,"Address cannot be empty").show();
            txtAddress.selectAll();
            txtAddress.requestFocus();
            return;
        }else if (txtContact.getText().isEmpty() || !txtContact.getText().matches("^\\d{3}-\\d{7}$")) {
            new Alert(Alert.AlertType.ERROR, "Contact cannot be empty").show();
            txtContact.selectAll();
            txtContact.requestFocus();
            return;
        } else if (txtGender.getText().isEmpty() || !txtGender.getText().matches("^(?i)(male|female)$")){
                new Alert(Alert.AlertType.ERROR,"Gender cannot be empty").show();
                txtContact.selectAll();
                txtContact.requestFocus();
                return;
        }

        StudentDTO student=new StudentDTO(txtID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),txtGender.getText());
        try {
            boolean isAdded =studentBO.addStudent(student);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Registered !").show();
            }
            txtID.clear();
            txtName.clear();
            txtAddress.clear();
            txtContact.clear();
            txtContact.clear();
            txtID.requestFocus();
            manageStudentsFormController.tblStudent.getItems().add(new StudentDTO(student.getId(), student.getName(), student.getAddress(), student.getContact(),student.getGender()));


        }catch (DuplicateException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}

