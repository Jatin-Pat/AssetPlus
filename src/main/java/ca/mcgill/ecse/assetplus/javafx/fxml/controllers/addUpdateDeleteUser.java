package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ca.mcgill.ecse.assetplus.controller.*;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;


public class addUpdateDeleteUser {

    @FXML
    private MenuItem addEmployeeClicked;

    @FXML
    private MenuItem addGuestClicked;

    @FXML
    private MenuButton addUser;

    @FXML
    private Button deleteUser;

    @FXML
    private Button modifyUser;

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

    @FXML
    void deleteEmployeeOrGuestClicked(ActionEvent event) {
      //TODO ERROR MESSAGE?? 
      String name = userNameTextField.getText();
      String email = userEmailTextField.getText();
      String phoneNumber = userPhoneNumberTextField.getText();
      String password = userPasswordTextField.getText();

  
      AssetPlusFeatureSet6Controller.deleteEmployeeOrGuest(email);
      userNameTextField.setText("");
      userEmailTextField.setText("");
      userPhoneNumberTextField.setText("");
      userPasswordTextField.setText("");


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
    }}

