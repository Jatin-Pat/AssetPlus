package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import java.io.IOException;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet5Controller;
import ca.mcgill.ecse.assetplus.controller.TicketMaintenanceController;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class startCompleteWorkTicket {
  private MainPage mainPage;
  @FXML
  private TextField ticketID;
  @FXML
  private Button startWork;
  @FXML
  private Button completeWork;
  @FXML
  private Button cancel;

  @FXML
  public void initialize() {
    int ticketId = ViewTicketsPage.getTicketID();
    if (ticketId != -1) {
        ticketID.setText(String.valueOf(ViewTicketsPage.getTicketID()));
    }
  }
  


  // Even listner when Start Work button is pressed
  @FXML
  public void startWork(ActionEvent event) {
    String ticketId = ticketID.getText();

    if (ticketId == null || ticketId.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid ticketID");
    }
    else if (MaintenanceTicket.getWithId(Integer.parseInt(ticketId)).getTicketStatus() == MaintenanceTicket.TicketStatus.Open) {
      ViewUtils.showError("Please assign an user to this ticket first"); //this error message replaces "Cannot start a maintenance ticket which is open"
    }
    else if (ViewUtils.successful(TicketMaintenanceController.beginTicketWork(ticketId))) {

      ticketID.setText("");
    }
  }

  // Even listner when Complete Work button is pressed
  @FXML
  public void completeWork(ActionEvent event) {
    String ticketId = ticketID.getText();

    if (ticketId == null || ticketId.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid ticketID");
    }
    else if (MaintenanceTicket.getWithId(Integer.parseInt(ticketId)).getTicketStatus() == MaintenanceTicket.TicketStatus.Assigned) {
      ViewUtils.showError("Please start this work first"); //this error message replaces "Cannot start a maintenance ticket which is assigned"
    }
    else if (ViewUtils.successful(TicketMaintenanceController.completeTicketWork(ticketId))) {

      ticketID.setText("");
    }
  }

  @FXML
  public void cancel(ActionEvent event) {
    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
      Parent root = loader.load();

      // Get the current Stage
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      // Set the new root for the current Scene
      stage.getScene().setRoot(root);
      mainPage = loader.getController();

      mainPage.selectTab(1);

    } catch (IOException e) {
      e.printStackTrace();
      ViewUtils.showError("Error Changing Page\n");
    }
  }
}
