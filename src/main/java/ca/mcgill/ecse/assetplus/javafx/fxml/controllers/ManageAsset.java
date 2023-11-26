package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.*;
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

public class ManageAsset {

    @FXML
    private Label AssetNumberLabel;

    @FXML
    private TextField AssetNumberTextField;

    @FXML
    private TextField AssetTypeTextField;

    @FXML
    private Label ExpectedLifespanLabel;

    @FXML
    private TextField ExpectedLifespanTextField;

    @FXML
    private Label NewAssetTypeLabel;

    @FXML
    private Label NewExpectedLifespanLabel;

    @FXML
    private MenuItem addAssetClicked;

    @FXML
    private MenuItem addAssetTypeClicked;

    @FXML
    private SplitMenuButton addMenuButton;

    @FXML
    private Label assetTypeLabel;

    @FXML
    private MenuItem deleteAssetClicked;

    @FXML
    private MenuItem deleteAssetTypeClicked;

    @FXML
    private SplitMenuButton deleteMenuButton;

    @FXML
    private Label floorNumberLabel;

    @FXML
    private TextField floorNumberTextField;

    @FXML
    private MenuItem modifyAssetClicked;

    @FXML
    private MenuItem modifyAssetTypeClicked;

    @FXML
    private SplitMenuButton modifyMenuButton;

    @FXML
    private TextField newAssetTypeTextField;

    @FXML
    private TextField newExpectedLifespanTextField;

    @FXML
    private DatePicker purchaseDateDatePicker;

    @FXML
    private Label purchaseDateLabel;

    @FXML
    private Label roomNumberLabel;

    @FXML
    private TextField roomNumberTextField;

    @FXML
    void addAssetClicked(ActionEvent event) {
      int assetNumber = Integer.parseInt(AssetNumberTextField.getText());
      int floorNumber = Integer.parseInt(floorNumberTextField.getText());
      int roomNumber = Integer.parseInt(roomNumberTextField.getText());
      Date purchaseDate = Date.valueOf(purchaseDateDatePicker.getValue());
      String assetTypeName = AssetTypeTextField.getText();

      if (ViewUtils.successful(AssetPlusFeatureSet3Controller.addSpecificAsset(assetNumber, floorNumber, roomNumber, purchaseDate, assetTypeName))){
        AssetNumberTextField.setText("");
        floorNumberTextField.setText("");
        roomNumberTextField.setText("");
        AssetTypeTextField.setText("");
      }
    }

    @FXML
    void addAssetTypeClicked(ActionEvent event) {
      String assetTypeName = newAssetTypeTextField.getText();
      int expectedLifespan = Integer.parseInt(newExpectedLifespanTextField.getText());

      if (ViewUtils.successful(AssetPlusFeatureSet2Controller.addAssetType(assetTypeName, expectedLifespan))){
        newAssetTypeTextField.setText("");
        newExpectedLifespanTextField.setText("");
      }
    }

    @FXML
    void deleteAssetClicked(ActionEvent event) {
      int assetNumber = Integer.parseInt(AssetNumberTextField.getText());

      AssetPlusFeatureSet3Controller.deleteSpecificAsset(assetNumber);
      AssetNumberTextField.setText("");
    }

    @FXML
    void deleteAssetTypeClicked(ActionEvent event) {
      String assetTypeName = AssetTypeTextField.getText();

      AssetPlusFeatureSet2Controller.deleteAssetType(assetTypeName);
      AssetTypeTextField.setText("");
    }

    @FXML
    void modifyAssetClicked(ActionEvent event) {
      int assetNumber = Integer.parseInt(AssetNumberTextField.getText());
      int floorNumber = Integer.parseInt(floorNumberTextField.getText());
      int roomNumber = Integer.parseInt(roomNumberTextField.getText());
      Date purchaseDate = Date.valueOf(purchaseDateDatePicker.getValue());
      String assetTypeName = AssetTypeTextField.getText();

      if (ViewUtils.successful(AssetPlusFeatureSet3Controller.updateSpecificAsset(assetNumber, floorNumber, roomNumber, purchaseDate, assetTypeName))){
        AssetNumberTextField.setText("");
        floorNumberTextField.setText("");
        roomNumberTextField.setText("");
        AssetTypeTextField.setText("");
      }
    }

    @FXML
    void modifyAssetTypeClicked(ActionEvent event) {
      String assetTypeName = AssetTypeTextField.getText();
      int newExpectedLifespan = Integer.parseInt(newExpectedLifespanTextField.getText());
      String newAssetType = newAssetTypeTextField.getText();

      if (ViewUtils.successful(AssetPlusFeatureSet2Controller.updateAssetType(assetTypeName, newAssetType, newExpectedLifespan))){
        AssetTypeTextField.setText("");
        ExpectedLifespanTextField.setText("");
      }
    }
}
