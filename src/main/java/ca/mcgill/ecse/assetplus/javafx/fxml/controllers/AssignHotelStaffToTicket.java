package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.TicketMaintenanceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AssignHotelStaffToTicket implements Initializable {

    @FXML
    private CheckBox approval;

    @FXML
    private TextField email;

    @FXML
    private TextField id;

    @FXML
    private ChoiceBox<String> priority;

    private String priorityChoice[] = {"Urgent", "Normal", "Low"};

    @FXML
    private Button submit;

    @FXML
    private ChoiceBox<String> time;

    private String timeChoice[] = {"LessThanADay", "OneToThreeDays", "ThreeToSevenDays", "OneToThreeWeeks", "ThreeOrMoreWeeks"};

    public void assignStaff(ActionEvent event) {
        String ticketID = id.getText();
        String staffEmail = email.getText();
        String timeEstimate = time.getValue();
        String priorityLevel = priority.getValue();
        String approvalRequired = String.valueOf(approval.isSelected());
        if (Integer.parseInt(ticketID) < 0 || ticketID.isEmpty()) {
            ViewUtils.showError("Please input a valid ticket id.");
        }
        else if (staffEmail.isEmpty() || timeEstimate.isEmpty() || priorityLevel.isEmpty() || approvalRequired.isEmpty()) {
            ViewUtils.showError("Please fill in the listed fields.");
        } else {
            TicketMaintenanceController.assignStaffToTicket(ticketID, staffEmail, timeEstimate, priorityLevel, approvalRequired);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priority.getItems().addAll(priorityChoice);
        time.getItems().addAll(timeChoice);
    }
}
