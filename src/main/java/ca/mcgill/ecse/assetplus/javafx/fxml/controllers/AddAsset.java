package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet3Controller;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class AddAsset {

  private MainPage mainPage;

    @FXML
    private Button addAsset;

    @FXML
    private TextField assetNumberTextField;

    @FXML
    private TextField assetTypeTextField;

    @FXML
    private TextField floorNumberTextField;

    @FXML
    private Button goBack;

    @FXML
    private DatePicker purchaseDateDatePicker;

    @FXML
    private TextField roomNumberTextField;

    @FXML
    void addAsset(ActionEvent event) {
      int assetNumber = Integer.parseInt(assetNumberTextField.getText());
      int floorNumber = Integer.parseInt(floorNumberTextField.getText());
      int roomNumber = Integer.parseInt(roomNumberTextField.getText());
      Date purchaseDate = Date.valueOf(purchaseDateDatePicker.getValue());
      String assetTypeName = assetTypeTextField.getText();

      if (ViewUtils.successful(AssetPlusFeatureSet3Controller.addSpecificAsset(assetNumber, floorNumber, roomNumber, purchaseDate, assetTypeName))){
        assetNumberTextField.setText("");
        floorNumberTextField.setText("");
        roomNumberTextField.setText("");
        purchaseDateDatePicker.setValue(null);
        assetTypeTextField.setText("");
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

        mainPage.selectTab(2);
        
    
      } catch (IOException e) {
        e.printStackTrace();
        ViewUtils.showError("Error Changing Page\n");
      }
    }

}

