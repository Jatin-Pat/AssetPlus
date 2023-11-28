package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet1Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddUser {

    @FXML
    private MenuItem addEmployeeClicked;

    @FXML
    private MenuItem addGuestClicked;

    @FXML
    private Button cancle;

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

    }

}