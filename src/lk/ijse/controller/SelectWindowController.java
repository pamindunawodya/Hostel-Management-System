package lk.ijse.controller;


import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.util.Navigation;
import lk.ijse.util.Route;

import java.io.IOException;

public class SelectWindowController {
    public AnchorPane pneContainer;


    public void BacktoLoginButton(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Route.LOGIN);
    }
}
