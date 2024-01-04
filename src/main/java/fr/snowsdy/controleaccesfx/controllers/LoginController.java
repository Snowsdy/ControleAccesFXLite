package fr.snowsdy.controleaccesfx.controllers;

import fr.snowsdy.controleaccesfx.entities.User;
import fr.snowsdy.controleaccesfx.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    private final UserService service;

    @FXML
    private Button userTest;

    public LoginController(UserService service) {
        this.service = service;
    }

    @FXML
    public void addTest(ActionEvent event) {
        service.save(
                new User("Oui-Oui")
        );
        System.out.println("User added !!!");
        event.consume();
    }
}
