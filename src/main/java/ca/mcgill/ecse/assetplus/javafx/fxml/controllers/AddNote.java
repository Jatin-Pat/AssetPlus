package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.*;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import javafx.event.ActionEvent;
import java.sql.Date;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddImage {
    @FXML private TextField description;
    @FXML private TextField ticketID;
    @FXML private Textfield date;
    @FXML private TextField email;
    @FXML private Button Cancel;
    @FXML private Button Add;
    
    @FXML
    public void addNotes(ActionEvent event) {
      String text = description.getText(); 
      String ticket = ticketID.getText();
      String user = email.getText();
      int ID = Integer.parseInt(ticket);
      if ( text == null || text == "") {
        ViewUtils.showError("Please input a valid description");
      }
      else if(ID < 0){
        ViewUtils.showError("Please input a valid ticketID");
      } else{
        AssetPlusFeatureSet7Controller.addMaintenance(date, text, ticketID, user);
      }
    }
}
