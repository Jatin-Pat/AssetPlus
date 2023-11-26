package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/*
 * @author: Dmytro Martyuniuk
 * Adds or deletes image to a maitencnace ticket
 */

public class addDeleteImage {
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

  // Event Listener on Button[#Add].onAction
  @FXML
  public void add_selected(ActionEvent event) {
    String url = imageURL.getText();
    String ticketId = id.getText();
    int ID = Integer.parseInt(ticketId);
    if (url == null || url.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid image URL");
    } else if (ID < 0) {
      ViewUtils.showError("Please input a valid ticketID");
    } else if (ViewUtils.successful(AssetPlusFeatureSet5Controller.addImageToMaintenanceTicket(url, ID))) {
      // Adds the image to the ticket
      // Resets the fields
      imageURL.setText("");
      id.setText("");
    }
  }

  @FXML
  public void remove_selected(ActionEvent event) {
    String url = imageURL.getText();
    String ticketId = id.getText();
    int ID = Integer.parseInt(ticketId);
    if (url == null || url.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid image URL");
    } else if (ID < 0) {
      ViewUtils.showError("Please input a valid ticketID");
    } else {
      // Removes the image from the ticket
      AssetPlusFeatureSet5Controller.deleteImageFromMaintenanceTicket(url, ID);

      // Resets the text fields
      imageURL.setText("");
      id.setText("");
    }
  }
}
