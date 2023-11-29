package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;
import ca.mcgill.ecse.assetplus.controller.TicketMaintenanceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ApproveDisapproveWork implements Initializable {

    private MainPage mainPage;

    @FXML
    private Button cancel;

    @FXML
    private ChoiceBox<String> approval;

    private String status[] = {"approved", "rejected"};

    @FXML
    private TextField id;

    @FXML
    private DatePicker date;

    @FXML
    private TextField reason;

    @FXML
    private Button submit;

    public void setApprovalStatus(ActionEvent event) {
        String status = approval.getValue();
        String ticketID = id.getText();
        String rejectionReason = reason.getText();
        String rejectionDate;
        if (ticketID.isEmpty() || status == null) {
            ViewUtils.showError("Please select the ticket ID and approval status.");
        }
        else if (status.equals("approved")) {
            if (ViewUtils.successful(TicketMaintenanceController.approveTicketWork(ticketID))){
                approval.setValue(null);
                id.setText("");
                reason.setText("");
                date.setValue(null);
            }
        }
        else {
            if (date.getValue() == null || rejectionReason.isEmpty()) {
                ViewUtils.showError("Please enter the rejection reason and rejection date.");
            } else {
                rejectionDate = date.getValue().toString();
                String result = TicketMaintenanceController.disapproveTicketWork(ticketID, rejectionDate, rejectionReason);
                if (result.equals("")){
                    approval.setValue(null);
                    id.setText("");
                    reason.setText("");
                    date.setValue(null);
                }
                else {
                    ViewUtils.showError(result);
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        approval.getItems().addAll(status);
    }

    public void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
            mainPage = loader.getController();
            mainPage.selectTab(1);
        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.showError("Error Changing Page\n");
        }
    }
}