package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet1Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class UpdateUser {

    private MainPage mainPage;



    @FXML
    private Button cancel;

    @FXML
    private TextField userEmailTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField userPasswordTextField;

    @FXML
    private TextField userPhoneNumberTextField;

    @FXML
    public void initialize() {
      if (ViewUserPage.getUserEmail() != null) {
        userEmailTextField.setText(ViewUserPage.getUserEmail());
      } else {
        userEmailTextField.setText("");
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

    @FXML
    void updateEmployeeOrGuestClicked(ActionEvent event) {
      String name = userNameTextField.getText();
      String email = userEmailTextField.getText();
      String phoneNumber = userPhoneNumberTextField.getText();
      String password = userPasswordTextField.getText();
      if (ViewUtils.successful(AssetPlusFeatureSet1Controller.updateEmployeeOrGuest(email,password,name,phoneNumber))){
        userNameTextField.setText("");
        userEmailTextField.setText("");
        userPhoneNumberTextField.setText("");
        userPasswordTextField.setText("");
      }

    }

    @FXML
    void updateManagerClicked(ActionEvent event) {
      String name = userNameTextField.getText();
      String email = userEmailTextField.getText();
      String phoneNumber = userPhoneNumberTextField.getText();
      String password = userPasswordTextField.getText();
      if (ViewUtils.successful(AssetPlusFeatureSet1Controller.updateManager(password))){
        userNameTextField.setText("");
        userEmailTextField.setText("");
        userPhoneNumberTextField.setText("");
        userPasswordTextField.setText("");
      }
      
    }

}