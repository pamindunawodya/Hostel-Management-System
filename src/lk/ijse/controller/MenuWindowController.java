package lk.ijse.controller;

import javafx.event.ActionEvent;
import lk.ijse.util.Navigation;
import lk.ijse.util.Route;

import java.io.IOException;

public class MenuWindowController {

    public void btnStudentsFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.STUDENT);
    }

    public void btnAddRoomFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.ROOM);
    }

    public void reservationFormOnAction(ActionEvent actionEvent) throws IOException {
         Navigation.navigate(Route.RESERVATION);
    }

    public void btnReservationDetailsFormOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.RESERVATION_DETAILS);
    }
}
