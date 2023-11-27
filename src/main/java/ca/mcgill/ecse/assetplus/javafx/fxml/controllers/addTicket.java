package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

        import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.TextField;
        import ca.mcgill.ecse.assetplus.controller.*;
        import ca.mcgill.ecse.assetplus.model.AssetPlus;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.sql.Date;
        import java.time.LocalDate;

public class addTicket {
    private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus(); // can I do this?

    @FXML
    private TextField assetIdTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    void addTicket(ActionEvent event) {
        int ticketId = assetPlus.numberOfMaintenanceTickets();
        Date raisedOn = Date.valueOf(LocalDate.now());
        String description = descriptionTextField.getText();
        String email = emailTextField.getText();
        int assetId = -1;
        String assetIdString = assetIdTextField.getText();
        if (!assetIdString.isEmpty()) {
            assetId = Integer.parseInt(assetIdString);
            if (assetId < 0) ViewUtils.showError("Please enter a valid asset ID\n");
        }
        if (ViewUtils.successful(AssetPlusFeatureSet4Controller.addMaintenanceTicket(ticketId, raisedOn, description, email, assetId))){
            assetIdTextField.setText("");
            emailTextField.setText("");
            descriptionTextField.setText("");
            locationTextField.setText("");
        }
    }

    @FXML
    void openAddImagePage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addDeleteImage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Image Page");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.showError("Error opening image upload page\n");
        }
    }

}

