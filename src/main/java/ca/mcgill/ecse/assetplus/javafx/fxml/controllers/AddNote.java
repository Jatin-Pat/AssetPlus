package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet7Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddNote {

    @FXML
    private TextField ticketIdTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private DatePicker datePicker;

    @FXML
    void addNote(ActionEvent event) {
      int id = Integer.parseInt(ticketIdTextField.getText());
      String email = emailTextField.getText();
      Date date = Date.valueOf(datePicker.getValue());
      String description = descriptionTextField.getText();

      if (ViewUtils.successful(AssetPlusFeatureSet7Controller.addMaintenanceNote(date, description, id, email))){
        descriptionTextField.setText("");
        emailTextField.setText("");
        datePicker.setValue(null);
        ticketIdTextField.setText("");
      }
    }

}
