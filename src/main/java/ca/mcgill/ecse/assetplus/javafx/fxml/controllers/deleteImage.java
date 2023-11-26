package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.*;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class deleteImage {
    @FXML
    private TextField URL;
    @FXML
    private TextField ticketID;
    @FXML
    private Button Cancel;
    @FXML
    private Button Remove;

    // Event Listener on Button[#Add].onAction
    @FXML
    public void deleteImages(ActionEvent event) {
        String imageUrl = URL.getText();
        String ticketId = ticketID.getText();
        int ID = Integer.parseInt(ticketId);
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            ViewUtils.showError("Please input a valid image URL");
        } else if (ID < 0) {
            ViewUtils.showError("Please input a valid ticketID");
        } else {
            // adds the imahe to the ticket
            AssetPlusFeatureSet5Controller.deleteImageFromMaintenanceTicket(imageUrl, ID);
        }
    }
}