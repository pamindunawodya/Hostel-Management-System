package lk.ijse.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Navigation {
    public static AnchorPane pneContainer;
    public static void init(AnchorPane pneContainer){
        Navigation.pneContainer=pneContainer;
    }

    public static void navigate(Route route) throws IOException {
        pneContainer.getChildren().clear();
        Stage container = (Stage) pneContainer.getScene().getWindow();
        URL resource = null;
        switch (route) {
            case LOGIN:
                resource = Navigation.class.getResource("/lk/ijse/view/LoginWindow.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;

            case SELECT:
                resource = Navigation.class.getResource("/lk/ijse/view/SelectWindow.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;

            case MENU:
                resource = Navigation.class.getResource("/lk/ijse/view/MenuWindow.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;

            case STUDENT:
                resource = Navigation.class.getResource("/lk/ijse/view/ManageStudentsForm.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;


            case ADDSTUDENT:
                resource = Navigation.class.getResource("/lk/ijse/view/AddStudentForm.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;

            case ROOM:
                resource = Navigation.class.getResource("/lk/ijse/view/ManageRoomForm.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;

            case RESERVATION:
                resource = Navigation.class.getResource("/lk/ijse/view/ReservationForm.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;

            case RESERVATION_DETAILS:
                resource = Navigation.class.getResource("/lk/ijse/view/Reservation_Details.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;

        }

        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane load = fxmlLoader.load();
        pneContainer.getChildren().addAll(load);
    }
}