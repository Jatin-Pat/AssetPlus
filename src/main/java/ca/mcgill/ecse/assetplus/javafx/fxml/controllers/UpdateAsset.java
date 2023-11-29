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

public class UpdateAsset {

    private MainPage mainPage;

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
    private Button updateAsset;

    @FXML
    public void initialize() {
      Integer assetID = ViewAssetsPage.getAssetID();
      if (assetID != -1) {
          assetNumberTextField.setText(String.valueOf(ViewAssetsPage.getAssetID()));
      } else {
          assetNumberTextField.setText("");
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

        mainPage.selectTab(3);
        
    
      } catch (IOException e) {
        e.printStackTrace();
        ViewUtils.showError("Error Changing Page\n");
      }
    }

    @FXML
    void updateAsset(ActionEvent event) {
      int assetNumber = Integer.parseInt(assetNumberTextField.getText());
      int newFloorNumber = Integer.parseInt(floorNumberTextField.getText());
      int newRoomNumber = Integer.parseInt(roomNumberTextField.getText());
      Date newPurchaseDate = Date.valueOf(purchaseDateDatePicker.getValue());
      String newAssetType = assetTypeTextField.getText();

      if (ViewUtils.successful(AssetPlusFeatureSet3Controller.updateSpecificAsset(assetNumber, newFloorNumber, newRoomNumber, newPurchaseDate, newAssetType))){
        assetNumberTextField.setText("");
        floorNumberTextField.setText("");
        roomNumberTextField.setText("");
        purchaseDateDatePicker.setValue(null);
        assetTypeTextField.setText("");
      }
    }

}
