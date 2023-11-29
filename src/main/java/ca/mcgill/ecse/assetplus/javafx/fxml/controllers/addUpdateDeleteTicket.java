package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ca.mcgill.ecse.assetplus.controller.*;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class addUpdateDeleteTicket {
    private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus(); // can I do this?

    private MainPage mainPage;

    @FXML
    public Button goBack;

    @FXML
    private TextField assetIdTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private DatePicker ticketDateDatePicker;

    @FXML
    private TextField ticketIdTextField;

    @FXML
    public void initialize() {
    // Set the value of userEmailTextField from ViewUserPage.currentUser
    if (ViewTicketsPage.getTicketID() != -1) {
        assetIdTextField.setText(String.valueOf(ViewTicketsPage.getTicketID()));
    } else {
        assetIdTextField.setText("");
    }
    }

    @FXML
    void addTicket(ActionEvent event) {
        int ticketId = assetPlus.numberOfMaintenanceTickets();
        Date raisedOn =  Date.valueOf(ticketDateDatePicker.getValue());
        String description = descriptionTextField.getText();
        String email = emailTextField.getText();
        int assetId = -1;
        String assetIdString = assetIdTextField.getText();
        if (!assetIdString.isEmpty()) {
            assetId = Integer.parseInt(assetIdString);
            if (assetId < 0)
                ViewUtils.showError("Please enter a valid asset ID\n");
        }
        if (ViewUtils.successful(
                AssetPlusFeatureSet4Controller.addMaintenanceTicket(ticketId, raisedOn, description, email, assetId))) {
            assetIdTextField.setText("");
            emailTextField.setText("");
            descriptionTextField.setText("");
            ticketDateDatePicker.setValue(null);
        }
        for (MaintenanceTicket ticket : AssetPlusApplication.getAssetPlus().getMaintenanceTickets()) {
            System.out.println(ticket);
        }
        
    }

    @FXML
    void updateTicket(ActionEvent event) {
        int ticketId = Integer.parseInt(ticketIdTextField.getText());
        Date raisedOn =  Date.valueOf(ticketDateDatePicker.getValue());
        String description = descriptionTextField.getText();
        String email = emailTextField.getText();
        int assetId = -1;
        String assetIdString = assetIdTextField.getText();
        if (!assetIdString.isEmpty()) {
            assetId = Integer.parseInt(assetIdString);
            if (assetId < 0)
                ViewUtils.showError("Please enter a valid asset ID");
        }
        if (ViewUtils.successful(AssetPlusFeatureSet4Controller.updateMaintenanceTicket(ticketId, raisedOn, description,
                email, assetId))) {
            assetIdTextField.setText("");
            emailTextField.setText("");
            descriptionTextField.setText("");
            ticketDateDatePicker.setValue(null);
        }
    }

    @FXML
    void deleteTicket(ActionEvent actionEvent) { //useless (see ViewTicketsPage controller)
        int ticketId = Integer.parseInt(ticketIdTextField.getText());
        AssetPlusFeatureSet4Controller.deleteMaintenanceTicket(ticketId);
        ticketIdTextField.setText("");
    }

    @FXML
    void openAddImagePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/addDeleteImage.fxml"));
            Parent root = loader.load();

            // Get the current Stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new root for the current Scene
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();
            ViewUtils.showError("Error opening image upload page\n");
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
            Parent root = loader.load();

            // Get the current Stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new root for the current Scene
            stage.getScene().setRoot(root);
            mainPage = loader.getController();

            mainPage.selectTab(1);


        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.showError("Error Changing Page\n");
        }
    }
}