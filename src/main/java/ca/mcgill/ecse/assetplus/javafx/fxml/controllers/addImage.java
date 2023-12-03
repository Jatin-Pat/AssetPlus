package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import java.io.IOException;

import ca.mcgill.ecse.assetplus.controller.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/*
 * @author: Dmytro Martyuniuk
 * Adds image to a maitencnace ticket
 */

public class addImage {

  @FXML
  public Button cancel;
  @FXML
  private TextField imageURL;
  @FXML
  private TextField id;
  @FXML
  private Button submit;
  @FXML
  private ImageView image;

  @FXML
  public void initialize() {
  // Set the value of userEmailTextField from ViewUserPage.currentUser
  if(id!=null){
  if (ViewTicketsPage.getTicketID() != -1) {
      id.setText(String.valueOf(ViewTicketsPage.getTicketID()));
  } else {
      id.setText("");
  }}
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
    if (url == null || url.trim().isEmpty()) {
        ViewUtils.showError("Please input a valid image URL");
    } else if (ID < 1) {
        ViewUtils.showError("Please input a valid ticketID");
    } else if (ViewUtils.successful(AssetPlusFeatureSet5Controller.addImageToMaintenanceTicket(url, ID))) {
        // Adds the image to the ticket
        // Resets the fields
        image.setImage(new Image(imageURL.getText()));
        imageURL.setText("");        
    }
  }

  // Event Listener on Button[#remove_selected].onAction
  @FXML
  public void cancel(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/ViewImageNotes.fxml"));
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
