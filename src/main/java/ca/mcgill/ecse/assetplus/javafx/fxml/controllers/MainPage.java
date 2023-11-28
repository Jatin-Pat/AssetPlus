package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class MainPage {

  @FXML
  private TabPane tabPane;

  public void selectTab(int index) {
    tabPane.getSelectionModel().select(index);
  }

  
}
