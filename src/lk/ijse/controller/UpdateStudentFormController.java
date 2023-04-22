package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.bo.exception.NotFoundException;
import lk.ijse.dto.StudentDTO;

import java.util.Optional;


public class UpdateStudentFormController {

    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContact;
    public TextField txtGender;
    public Button btnUpdate;
    public Button btnDelete;
    public Label lblId;
    public StudentDTO studentDTO;
    public ManageStudentsFormController manageStudentsController;
    public StudentBO studentBO;



    public void init(StudentDTO studentDTO, ManageStudentsFormController manageStudentsFormController) {
        this. studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
        this.studentDTO=studentDTO;
        this.manageStudentsController =manageStudentsFormController;
        fillAllFields(studentDTO);
    }
    private void fillAllFields(StudentDTO studentDTO){
lblId.setText(studentDTO.getId());
txtName.setText(studentDTO.getName());
txtAddress.setText(studentDTO.getAddress());
txtContact.setText(studentDTO.getContact());
txtGender.setText(studentDTO.getGender());
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {

            Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure to delete the student", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                studentBO.deleteCustomer(studentDTO.getId());
                new Alert(Alert.AlertType.INFORMATION, "Student delete successful").show();
                manageStudentsController.tblStudent.getItems().
                        removeAll(manageStudentsController.tblStudent.getSelectionModel().getSelectedItem());
                btnDelete.getScene().getWindow().hide();

            }

        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            txtContact.getScene().getWindow().hide();
            return;


        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
//        if (txtName.getText().isEmpty() || !txtName.getText().matches("^[A-Za-z ]+$")){
//            new Alert(Alert.AlertType.ERROR,"Name cannot be empty").show();
//            txtName.selectAll();
//            txtName.requestFocus();
//            return;
//        }else if (txtAddress.getText().isEmpty() || !txtAddress.getText().matches("^[A-Za-z ]+$")){
//            new Alert(Alert.AlertType.ERROR,"Address cannot be empty").show();
//            txtAddress.selectAll();
//            txtAddress.requestFocus();
//            return;
//        }else if (txtContact.getText().isEmpty() || !txtContact.getText().matches("^\\d{3}-\\d{7}$")){
//            new Alert(Alert.AlertType.ERROR,"Contact cannot be empty").show();
//            txtContact.selectAll();
//            txtContact.requestFocus();
//            return;
//        }
        StudentDTO updateStudent = new StudentDTO(lblId.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),txtGender.getText());

        try {
            studentBO.updateStudent(updateStudent);
            int selectedIndex = manageStudentsController.tblStudent.getSelectionModel().getSelectedIndex();
           manageStudentsController.tblStudent.getItems().add(selectedIndex,new StudentDTO(updateStudent.getId(), updateStudent.getName(), updateStudent.getAddress(), updateStudent.getContact(),updateStudent.getGender()));
           manageStudentsController.tblStudent.getItems().remove(selectedIndex+1);
            new Alert(Alert.AlertType.INFORMATION,"StudentDTO has been successfully updated!").show();


        }catch (NotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
}
