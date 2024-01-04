package fr.snowsdy.controleaccesfx.controllers;

import fr.snowsdy.controleaccesfx.entities.Admin;
import fr.snowsdy.controleaccesfx.services.UserService;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class LoginController {

    private final UserService service;

    @Value("classpath:/pages/home/home.fxml")
    private Resource resource;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    public LoginController(UserService service) {
        this.service = service;
    }

    @FXML
    void login(Event event) {
        Admin admin;

        String password = passwordField.getText();

        Optional<Admin> optionalUser = service.findByPassword(password);

        if (optionalUser.isPresent()) {
            admin = optionalUser.get();
            System.out.println("You're logged in ! " + admin.getName());

            try {
                FXMLLoader loader = new FXMLLoader(resource.getURL());
                Scene scene = loginField.getScene();
                scene.setRoot(loader.load());
                // Set its new dimensions
                scene.getWindow().setHeight(500);
                scene.getWindow().setWidth(800);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        // Clearing fields
        loginField.clear();
        passwordField.clear();

        event.consume();
    }

    @FXML
    void enterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login(event);
        }
    }
}
