package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.*;
import java.io.IOException;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addUpdateDeleteAssetType {

  private MainPage mainPage;

  @FXML
  private Button addAssetType;

  @FXML
  private TextField assetTypeNameTextField;

  @FXML
  private Button deleteAssetType;

  @FXML
  private ComboBox<String> existingAsset;

  @FXML
  private TextField expectedLifespanTextField;

  @FXML
  private Button updateAssetType;


  public void refreshTypes(){
    existingAsset.setItems(FXCollections.observableArrayList(ExtraFeaturesController.getAllAssetTypes()));
  }
  @FXML
  public void initialize(){
    refreshTypes();
  }

  @FXML
  void addAssetTypeClicked(ActionEvent event) {
    String assetTypeName = assetTypeNameTextField.getText();

    int expectedLifespan=-1; 
    if(expectedLifespanTextField.getText().equals("")){
      ViewUtils.showError("Enter a valid integer lifespan");
      return;
    }
    try{
      expectedLifespan = Integer.parseInt(expectedLifespanTextField.getText());
    }catch(NumberFormatException e){
      ViewUtils.showError("Enter a valid integer lifespan");
      return;
    }
    if(expectedLifespan<1){
      ViewUtils.showError("Life span must be larger than 0");
      return;
    }

    String result = AssetPlusFeatureSet2Controller.addAssetType(assetTypeName, expectedLifespan);
    if(!result.isBlank()){
      ViewUtils.showError(result);
    }else{
      assetTypeNameTextField.setText("");
      expectedLifespanTextField.setText("");
    }
    refreshTypes();
  }

    @FXML
    void deleteAssetTypeClicked(ActionEvent event) {
      String assetTypeName = existingAsset.getValue();
      if(assetTypeName==null || assetTypeName.equals("")){
        ViewUtils.showError("Select an asset to delete from the drop down menu");
        return;
      }

      AssetPlusFeatureSet2Controller.deleteAssetType(assetTypeName);
      assetTypeNameTextField.setText("");
      existingAsset.setValue(null);
      refreshTypes();
    }

    @FXML
    void updateAssetTypeClicked(ActionEvent event) {
      String assetToUpdate = existingAsset.getValue();
      if(assetToUpdate==null||assetToUpdate.equals("")){
        ViewUtils.showError("Select an asset to update from the drop down menu");
        return;
      }

      String newName = assetTypeNameTextField.getText();
      if(newName==""){
        ViewUtils.showError("Enter a new name for the asset");
        return;
      }

      if(expectedLifespanTextField.getText().equals("")){
        ViewUtils.showError("Write asset type's lifespan in days");
        return;
      }
      int newLifeSpan = -1;
      try{
        newLifeSpan = Integer.parseInt(expectedLifespanTextField.getText());
      }catch(NumberFormatException e){
        ViewUtils.showError("Invalid integer lifespan");
        return;
      }

      String result = AssetPlusFeatureSet2Controller.updateAssetType(assetToUpdate, newName, newLifeSpan);
      if (result.isBlank()){
        assetTypeNameTextField.setText("");
        expectedLifespanTextField.setText("");
        existingAsset.setValue(null);
      }else{
        ViewUtils.showError(result);
      }
      refreshTypes();
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

}