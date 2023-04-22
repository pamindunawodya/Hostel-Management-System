package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.LoginDetailBO;
import lk.ijse.dto.LoginDetailDTO;
import lk.ijse.util.Navigation;
import lk.ijse.util.Route;

import java.io.IOException;
import java.util.ArrayList;

public class LoginWindowController {
    public TextField txtfldUsername;

    private final String UserID = "user1"; //this is user row id
    public TextField pswrdhint;
    public CheckBox radioBtn;
    public PasswordField pswrdfldPassword;
    LoginDetailBO loginDetailBO;

    public void initialize(){
        pswrdhint.setVisible(false);
        pswrdfldPassword.setVisible(true);
        loginDetailBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LoginDetail);
        ArrayList<String> wordArrayList;
        ArrayList<String> dotArrayList;
    }
    public void loginButtonOnAction(ActionEvent actionEvent) throws IOException {
        LoginDetailDTO loginDetailDTO=loginDetailBO.findByPk(UserID);

        if(txtfldUsername.getText().equals(loginDetailDTO.getUsername()) & pswrdfldPassword.getText().equals(loginDetailDTO.getPassword())){
            Navigation.navigate(Route.MENU);
        }else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Username or Password mismatched!");
            alert.setContentText("You have entered invalid username or password.Please retype and try again. ");
            alert.show();
        }
    }

    public void ActionTxtUsername(ActionEvent actionEvent) {

    }

    public void keyReleaseActionTxtfldpswrd(KeyEvent keyEvent) {
    }

    public void actionRadioBtnShowPswrd(ActionEvent actionEvent) {
        pswrdhint.setText(pswrdfldPassword.getText());
        if(radioBtn.isSelected()){
            pswrdhint.setVisible(true);
            pswrdfldPassword.setVisible(false);
        }else{
            pswrdhint.setVisible(false);
            pswrdfldPassword.setVisible(true);
        }
    }


    public void ActionTxthin(MouseEvent mouseEvent) {
        radioBtn.selectedProperty().setValue(false);
        pswrdfldPassword.setVisible(true);
        pswrdhint.setVisible(false);
    }
}
