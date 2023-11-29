package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet5Controller;
import ca.mcgill.ecse.assetplus.controller.TicketMaintenanceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class startCompleteWorkTicket {
    
  @FXML
  private TextField ticketID;
  @FXML
  private Button startWork;
  @FXML
  private Button completeWork;


//Even listner when Start Work button is pressed  
@FXML
public void startWork(ActionEvent event) {
    String ticketId = ticketID.getText();


    if (ticketId == null || ticketId.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid ticketID"); 
    } else if (ViewUtils.successful(TicketMaintenanceController.beginTicketWork(ticketId))){
        
        ticketID.setText("");
        }
    }

//Even listner when Complete Work button is pressed  
@FXML
public void completeWork(ActionEvent event) {
    String ticketId = ticketID.getText();

    if (ticketId == null || ticketId.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid ticketID"); 
    } else if (ViewUtils.successful(TicketMaintenanceController.completeTicketWork(ticketId))){
        
        ticketID.setText("");
        }
    }    
}
