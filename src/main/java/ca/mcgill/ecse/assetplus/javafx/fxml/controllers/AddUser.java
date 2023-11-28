package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet1Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class AddUser {

    @FXML
    private MenuItem addEmployeeClicked;

    @FXML
    private MenuItem addGuestClicked;

    @FXML
    private Button goBack;

    @FXML
    private TextField userEmailTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField userPasswordTextField;

    @FXML
    private TextField userPhoneNumberTextField;

    @FXML
    void addEmployeeClicked(ActionEvent event) {
      String name = userNameTextField.getText();
      String email = userEmailTextField.getText();
      String phoneNumber = userPhoneNumberTextField.getText();
      String password = userPasswordTextField.getText();

      if (ViewUtils.successful(AssetPlusFeatureSet1Controller.addEmployeeOrGuest(email,password, name, phoneNumber, true))){
        userNameTextField.setText("");
        userEmailTextField.setText("");
        userPhoneNumberTextField.setText("");
        userPasswordTextField.setText("");
      }

    }

    @FXML
    void addGuestClicked(ActionEvent event) {
      String name = userNameTextField.getText();
      String email = userEmailTextField.getText();
      String phoneNumber = userPhoneNumberTextField.getText();
      String password = userPasswordTextField.getText();

      if (ViewUtils.successful(AssetPlusFeatureSet1Controller.addEmployeeOrGuest(email,password, name, phoneNumber, false))){
        userNameTextField.setText("");
        userEmailTextField.setText("");
        userPhoneNumberTextField.setText("");
        userPasswordTextField.setText("");
      }

    }
    //TODO
    @FXML
    void goBack(ActionEvent event) {
       try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
      Parent root = loader.load();

      // Get the current Stage
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      // Set the new root for the current Scene
      stage.getScene().setRoot(root);
      
    } catch (IOException e) {
      e.printStackTrace();
      ViewUtils.showError("Error opening image upload page\n");
    }

    }

}