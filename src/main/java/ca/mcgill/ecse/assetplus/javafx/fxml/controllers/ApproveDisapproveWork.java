package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;
import ca.mcgill.ecse.assetplus.controller.TicketMaintenanceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ApproveDisapproveWork implements Initializable {

    @FXML
    private ChoiceBox<String> approvalStatus;

    private String status[] = {"approved", "rejected"};

    @FXML
    private TextField id;

    @FXML
    private DatePicker date;

    @FXML
    private Button logout;

    @FXML
    private TextField reason;

    @FXML
    private Button submit;

    public void setApprovalStatus(ActionEvent event) {
        String status = approvalStatus.getValue();
        String ticketID = id.getText();
        String rejectionReason = reason.getText();
        String rejectionDate = date.getValue().toString();
        if (Integer.parseInt(ticketID) < 0 || ticketID.isEmpty()) {
            ViewUtils.showError("Please input a valid ticket id.");
        }

        if (status.isEmpty()) {
            ViewUtils.showError("Please select approval status.");
        }
        else if (status.equals("approved")) {
            TicketMaintenanceController.approveTicketWork(ticketID);
        }
        else {
            if (rejectionDate.isEmpty() || rejectionReason.isEmpty()) {
                ViewUtils.showError("Please enter rejection date and reason.");
            } else {
                TicketMaintenanceController.disapproveTicketWork(ticketID, rejectionDate, rejectionReason);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        approvalStatus.getItems().addAll(status);
    }
}
