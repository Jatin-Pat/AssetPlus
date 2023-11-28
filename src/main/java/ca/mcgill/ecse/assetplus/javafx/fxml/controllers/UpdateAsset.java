package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet3Controller;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class UpdateAsset {

    @FXML
    private TextField assetNumberTextField;

    @FXML
    private TextField assetTypeTextField;

    @FXML
    private TextField floorNumberTextField;

    @FXML
    private Button goBack;

    @FXML
    private DatePicker purchaseDateDatePicker;

    @FXML
    private TextField roomNumberTextField;

    @FXML
    private Button updateAsset;

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void updateAsset(ActionEvent event) {

    }

}
