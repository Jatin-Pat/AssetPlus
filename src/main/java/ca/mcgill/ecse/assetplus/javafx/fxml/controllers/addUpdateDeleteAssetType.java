package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.*;
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class addUpdateDeleteAssetType {

    @FXML
    private Button addAssetType;

    @FXML
    private TextField assetTypeNameTextField;

    @FXML
    private Button deleteAssetType;

    @FXML
    private TextField expectedLifespanTextField;

    @FXML
    private Button logout;

    @FXML
    private TextField newAssetTypeNameTextField;

    @FXML
    private TextField newExpectedLifespanTextField;

    @FXML
    private Button updateAssetType;

    @FXML
    void addAssetTypeClicked(ActionEvent event) {
      String assetTypeName = assetTypeNameTextField.getText();
      int expectedLifespan = Integer.parseInt(expectedLifespanTextField.getText());

      if (ViewUtils.successful(AssetPlusFeatureSet2Controller.addAssetType(assetTypeName, expectedLifespan))){
        assetTypeNameTextField.setText("");
        expectedLifespanTextField.setText("");
      }
    }

    @FXML
    void deleteAssetTypeClicked(ActionEvent event) {
      String assetTypeName = assetTypeNameTextField.getText();

      AssetPlusFeatureSet2Controller.deleteAssetType(assetTypeName);
      assetTypeNameTextField.setText("");
    }

    @FXML
    void updateAssetTypeClicked(ActionEvent event) {
      String assetTypeName = assetTypeNameTextField.getText();
      int newExpectedLifespan = Integer.parseInt(newExpectedLifespanTextField.getText());
      String newAssetType = newAssetTypeNameTextField.getText();

      if (ViewUtils.successful(AssetPlusFeatureSet2Controller.updateAssetType(assetTypeName, newAssetType, newExpectedLifespan))){
        assetTypeNameTextField.setText("");
        expectedLifespanTextField.setText("");
        newAssetTypeNameTextField.setText("");
      }
    }

}