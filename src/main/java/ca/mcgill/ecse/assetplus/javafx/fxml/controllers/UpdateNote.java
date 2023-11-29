package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet3Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet7Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class UpdateNote {

    @FXML
    private TextField ticketIdTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField indexTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    void updateNote(ActionEvent event) {
      int id = Integer.parseInt(ticketIdTextField.getText());
      int ind = Integer.parseInt(indexTextField.getText());
      String email = emailTextField.getText();
      Date date = Date.valueOf(datePicker.getValue());
      String description = descriptionTextField.getText();

      if (ViewUtils.successful(AssetPlusFeatureSet7Controller.updateMaintenanceNote(id, ind, date, description, email))){
        indexTextField.setText("");
        descriptionTextField.setText("");
        emailTextField.setText("");
        datePicker.setValue(null);
        ticketIdTextField.setText("");
      }
    }

}
