package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.*;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.time.LocalDate;

public class addTicket {
    @FXML private Label assetPlusLabel;

    @FXML private Label maintenanceTicketLabel;

    @FXML private Label usernameLabel;

    @FXML private Button logoutButton;

    @FXML private TextField assetTypeTextField;

    @FXML private TextField assetIdTextField;

    @FXML private TextField locationTextField;

    @FXML private TextField descriptionTextField;

    @FXML private Button addImageButton;

    @FXML private Button submitButton;

    @FXML public void addTicket(ActionEvent event) {
        int generatedID = 0; //get next integer in list of tickets
        Date todayDate = Date.valueOf(LocalDate.now());
        String description = descriptionTextField.getText();
        int assetId = Integer.parseInt(assetIdTextField.getText());
        String userEmail = ""; //get current user's email
        AssetPlusFeatureSet4Controller.addMaintenanceTicket(generatedID, todayDate, description, userEmail, assetId);
    }
}
