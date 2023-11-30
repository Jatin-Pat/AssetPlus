package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import java.io.IOException;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.controller.*;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 * @author: Dmytro Martyuniuk
 * Adds or deletes image to a maitencnace ticket
 */

public class addImage {

  private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus(); // can I do this?
  @FXML
  private TextField imageURL;
  @FXML
  private TextField id;
  @FXML
  private CheckBox add_selected;
  @FXML
  private CheckBox remove_selected;
  @FXML
  private Button logout;
  @FXML
  private Button submit;
  @FXML
  private Button cancel;

  @FXML
  public void initialize() {
  // Set the value of userEmailTextField from ViewUserPage.currentUser
    int ticketId = assetPlus.numberOfMaintenanceTickets();
    String number = ""+ticketId;
    id.setText(number);

  }
  
  // Event Listener on Button[#add_selected].onAction
  @FXML
  public void submit(ActionEvent event) {

    String url = imageURL.getText();
    String ticketId = id.getText();
    int ID;

    if (!(ticketId == null || ticketId == "")) {
      ID = Integer.parseInt(ticketId);
    } else {
      ID = -1;// ID is empty
    }

    if (add_selected.isSelected() && !remove_selected.isSelected()) {
      if (url == null || url.trim().isEmpty()) {
        ViewUtils.showError("Please input a valid image URL");
      } else if (ID < 0) {
        ViewUtils.showError("Please input a valid ticketID");
      } else if (ViewUtils.successful(AssetPlusFeatureSet5Controller.addImageToMaintenanceTicket(url, ID))) {
        // Adds the image to the ticket
        // Resets the fields
        imageURL.setText("");
        id.setText("");
        add_selected.setSelected(false);
      }
    } else if (remove_selected.isSelected() && !add_selected.isSelected()) {
      if (url == null || url.trim().isEmpty()) {
        ViewUtils.showError("Please input a valid image URL");
      } else if (ID < 0) {
        ViewUtils.showError("Please input a valid ticketID");
      } else {
        // Removes the iamge
        AssetPlusFeatureSet5Controller.deleteImageFromMaintenanceTicket(url, ID);
        // Resets the text fields
        imageURL.setText("");
        id.setText("");
        remove_selected.setSelected(false);
      }
    } else if (add_selected.isSelected() && remove_selected.isSelected()) {
      ViewUtils.showError("Please select only one option");
    } else {
      ViewUtils.showError("Please select either 'Add Image' or 'Remove Image'");
    }
  }

  // Event Listener on Button[#remove_selected].onAction
  @FXML
  public void cancel(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/AddTicket.fxml"));
      Parent root = loader.load();

      // Get the current Stage
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      // Set the new root for the current Scene
      stage.getScene().setRoot(root);
      } catch (IOException e) {
          e.printStackTrace();
          ViewUtils.showError("Error opening Add User page");
      }
  }
}
