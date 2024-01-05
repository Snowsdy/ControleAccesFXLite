package fr.snowsdy.controleaccesfx.controllers;

import fr.snowsdy.controleaccesfx.entities.AccessCard;
import fr.snowsdy.controleaccesfx.entities.Admin;
import fr.snowsdy.controleaccesfx.entities.Attribution;
import fr.snowsdy.controleaccesfx.entities.User;
import fr.snowsdy.controleaccesfx.services.AccessCardService;
import fr.snowsdy.controleaccesfx.services.AttributionService;
import fr.snowsdy.controleaccesfx.services.UserService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
public class HomeController implements Initializable {

    // Entities management

    private User selectedUser;

    private AccessCard selectedAccessCard;

    private Attribution selectedAttribution;

    // SpringBoot DI

    @Value("classpath:/pages/login/login.fxml")
    private Resource resource;

    private final ApplicationContext applicationContext;

    private final UserService userService;

    private final AccessCardService accessCardService;

    private final AttributionService attributionService;

    // FXML Controls

    @FXML
    private Button btnAccessCards;

    @FXML
    private Button btnAttributions;

    @FXML
    private Button btnUsers;

    @FXML
    private CheckBox isAdminCheckBox;

    // Labels

    @FXML
    private Label homeLabel;

    @FXML
    private Label welcomeLabel;

    // Text fields

    @FXML
    private TextField loginField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField serialNumberField;

    @FXML
    private PasswordField passwordField;

    // Stack pane

    @FXML
    private StackPane stackPane;

    // List items

    @FXML
    private ListView<User> usersListView;

    @FXML
    private ListView<AccessCard> accessCardsListView;

    @FXML
    private ListView<Attribution> attributionsListView;

    // Combo Boxes

    @FXML
    private ComboBox<AccessCard> accessCardsComboBox;

    @FXML
    private ComboBox<User> usersComboBox;

    // Views

    @FXML
    private HBox usersView;

    @FXML
    private HBox accessCardsView;

    @FXML
    private HBox attributionsView;

    public HomeController(
            ApplicationContext applicationContext,
            UserService userService,
            AccessCardService accessCardService,
            AttributionService attributionService
    ) {
        this.applicationContext = applicationContext;
        this.userService = userService;
        this.accessCardService = accessCardService;
        this.attributionService = attributionService;
    }

    public void setWelcomeLabel(String label) {
        welcomeLabel.setText(label);
    }

    @FXML
    void logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(resource.getURL());
            loader.setControllerFactory(applicationContext::getBean);
            Scene scene = stackPane.getScene();
            scene.setRoot(loader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void activateAdminMode(ActionEvent event) {
        loginField.setDisable(!loginField.isDisable());
        passwordField.setDisable(!passwordField.isDisable());
        event.consume();
    }

    @FXML
    void saveUser(ActionEvent event) {
        User user;

        if (selectedUser != null) {
            user = selectedUser;

            if (user instanceof Admin) {
                ((Admin) user).setLogin(loginField.getText());
                ((Admin) user).setPassword(passwordField.getText());
            }
        } else {
            if (isAdminCheckBox.isSelected()) {
                user = new Admin();
                ((Admin) user).setLogin(loginField.getText());
                ((Admin) user).setPassword(passwordField.getText());
            } else {
                user = new User();
            }
        }

        user.setName(nameField.getText());

        userService.save(user);

        updateUsersList();

        clearUserForm();

        event.consume();
    }

    @FXML
    void saveAccesCard(ActionEvent event) {
        AccessCard card;

        card = Objects.requireNonNullElseGet(selectedAccessCard, AccessCard::new);

        card.setSerialNumber(serialNumberField.getText());

        accessCardService.save(card);

        updateAccessCardsList();

        clearAccessCardForm();

        event.consume();
    }

    @FXML
    void saveAttribution(ActionEvent event) {
        Attribution attribution;

        attribution = Objects.requireNonNullElseGet(selectedAttribution, Attribution::new);

        attribution.setAccessCard(accessCardsComboBox.getValue());
        attribution.setUser(usersComboBox.getValue());

        attributionService.save(attribution);

        updateAttributionsList();

        clearAttributionForm();

        event.consume();
    }

    private void updateUsersList() {
        usersListView.setItems(FXCollections.observableList(userService.findAll()));
        selectedUser = null;
    }

    private void updateAccessCardsList() {
        accessCardsListView.setItems(FXCollections.observableList(accessCardService.findAll()));
        selectedAccessCard = null;
    }

    private void updateAttributionsList() {
        attributionsListView.setItems(FXCollections.observableList(attributionService.findAll()));
        usersComboBox.setItems(FXCollections.observableList(userService.findAll()));
        accessCardsComboBox.setItems(FXCollections.observableList(accessCardService.findAll()));
        selectedAttribution = null;
    }

    @FXML
    void deleteUser(ActionEvent event) {
        if (selectedUser != null) {
            userService.delete(selectedUser);
            updateUsersList();
        }
        event.consume();
    }

    @FXML
    void deleteAccessCard(ActionEvent event) {
        if (selectedAccessCard != null) {
            accessCardService.delete(selectedAccessCard);
            updateAccessCardsList();
        }
        event.consume();
    }

    @FXML
    void deleteAttribution(ActionEvent event) {
        if (selectedAttribution != null) {
            attributionService.delete(selectedAttribution);
            updateAttributionsList();
        }
        event.consume();
    }

    @FXML
    void handleNavigation(ActionEvent event) {
        if (event.getSource() == btnUsers) {
            homeLabel.setText("Users");
            usersView.toFront();
            updateUsersList();
        } else if (event.getSource() == btnAccessCards) {
            homeLabel.setText("Access Cards");
            accessCardsView.toFront();
            updateAccessCardsList();
        } else if (event.getSource() == btnAttributions) {
            homeLabel.setText("Attributions");
            attributionsView.toFront();
            updateAttributionsList();
        }

        event.consume();
    }

    @FXML
    void resetSelectedValues(ActionEvent event) {
        selectedUser = null;
        selectedAccessCard = null;
        selectedAttribution = null;

        clearUserForm();
        clearAccessCardForm();

        usersListView.getSelectionModel().clearSelection();
        accessCardsListView.getSelectionModel().clearSelection();

        event.consume();
    }

    private void initSelectedUserItemListener() {
        usersListView.getSelectionModel().selectedItemProperty().addListener((observableValue, user, t1) -> {
            if (observableValue.getValue() != null) {
                populateUserForm(t1);
                selectedUser = t1;
            }
        });
    }

    private void initSelectedAccessCardItemListener() {
        accessCardsListView.getSelectionModel().selectedItemProperty().addListener((observableValue, accessCard, t1) -> {
            if (observableValue.getValue() != null) {
                populateAccessCardForm(t1);
                selectedAccessCard = t1;
            }
        });
    }

    private void initSelectedAttributionItemListener() {
        attributionsListView.getSelectionModel().selectedItemProperty().addListener((observableValue, attribution, t1) -> {
            if (observableValue.getValue() != null) {
                populateAttributionForm(t1);
                selectedAttribution = t1;
            }
        });
    }

    private void populateAttributionForm(Attribution attribution) {
        clearAttributionForm();
        usersComboBox.getSelectionModel().select(attribution.getUser());
        accessCardsComboBox.getSelectionModel().select(attribution.getAccessCard());
    }

    private void populateAccessCardForm(AccessCard card) {
        clearAccessCardForm();
        serialNumberField.setText(card.getSerialNumber());
    }

    private void populateUserForm(User user) {
        clearUserForm();
        nameField.setText(user.getName());
        if (user instanceof Admin) {
            loginField.setDisable(false);
            passwordField.setDisable(false);
            isAdminCheckBox.setSelected(true);

            loginField.setText(((Admin) user).getLogin());
            passwordField.setText(((Admin) user).getPassword());
        }
    }

    private void clearAttributionForm() {
        usersComboBox.getSelectionModel().clearSelection();
        accessCardsComboBox.getSelectionModel().clearSelection();
        selectedAttribution = null;
    }

    private void clearAccessCardForm() {
        serialNumberField.clear();
        selectedAccessCard = null;
    }

    private void clearUserForm() {
        loginField.setDisable(true);
        passwordField.setDisable(true);
        isAdminCheckBox.setSelected(false);

        nameField.clear();
        loginField.clear();
        passwordField.clear();

        selectedUser = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initUsersList();
        initAccessCardsList();
        initAttributionsList();

        initSelectedUserItemListener();
        initSelectedAccessCardItemListener();
        initSelectedAttributionItemListener();

        usersView.toFront();
    }

    private void initUsersList() {
        List<User> users = userService.findAll();
        usersListView.setItems(FXCollections.observableList(users));
    }

    private void initAccessCardsList() {
        List<AccessCard> accessCards = accessCardService.findAll();
        accessCardsListView.setItems(FXCollections.observableList(accessCards));
    }

    private void initAttributionsList() {
        List<Attribution> attributions = attributionService.findAll();
        attributionsListView.setItems(FXCollections.observableList(attributions));

        List<AccessCard> accessCards = accessCardService.findAll();
        List<User> users = userService.findAll();

        usersComboBox.setItems(FXCollections.observableList(users));
        accessCardsComboBox.setItems(FXCollections.observableList(accessCards));
    }
}
