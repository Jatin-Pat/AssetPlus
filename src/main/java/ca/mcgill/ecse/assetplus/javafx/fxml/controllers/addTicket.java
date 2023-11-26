package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.*;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class addTicket {
    @FXML private TextField ticketIdTextField; //how do I get the next available int in the list?

    @FXML private TextField usernameTextField; //how do I get these?

    @FXML private Button logoutButton;

    @FXML private TextField assetTypeTextField;

    @FXML private TextField assetIdTextField;

    @FXML private TextField locationTextField;

    @FXML private TextField descriptionTextField;

    @FXML private Button addImageButton;

    @FXML private Button submitButton;

    @FXML public void addTicket(ActionEvent event) {
        int generatedID = Integer.parseInt(ticketIdTextField.getText()); //get next integer in list of tickets
        Date todayDate = Date.valueOf(LocalDate.now());

        String description = descriptionTextField.getText();
        if (description == null) ViewUtils.showError("Description field must not be empty\n"); //unsure about the newline

        int assetId = Integer.parseInt(assetIdTextField.getText());
        if (assetId < 0) ViewUtils.showError("Please enter a valid asset ID\n");

        String userEmail = usernameTextField.getText(); //get current user's email
        if (assetId == 0) assetId = -1; //set to -1 if there is no specified assetId
        AssetPlusFeatureSet4Controller.addMaintenanceTicket(generatedID, todayDate, description, userEmail, assetId);
    }
    public void openAddImagePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddImagePage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Image Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
}
