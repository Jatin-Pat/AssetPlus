package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet3Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet7Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DeleteNote {

    @FXML
    private TextField ticketIdTextField;

    @FXML
    private TextField indexTextField;

    @FXML
    void deleteNote(ActionEvent event) {
      int id = Integer.parseInt(ticketIdTextField.getText());
      int ind = Integer.parseInt(indexTextField.getText());

      AssetPlusFeatureSet7Controller.deleteMaintenanceNote(id, ind);
      ticketIdTextField.setText("");
      indexTextField.setText("");
    }
}
