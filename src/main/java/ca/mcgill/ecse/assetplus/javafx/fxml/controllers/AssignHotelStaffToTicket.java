package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet1Controller;
import ca.mcgill.ecse.assetplus.controller.TicketMaintenanceController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.util.HashMap;
import java.util.Map;

public class AssignHotelStaffToTicket implements Initializable {

    private MainPage mainPage;

    @FXML
    private Button cancel;

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

    private String timeChoice[] = {"< day", "1 - 3 days", "3 - 7 days", "1 - 3 weeks", "> 3 weeks"};

    private static Map<String, String> timeChoiceMap;

    static {
        String[] originalChoices = {"LessThanADay", "OneToThreeDays", "ThreeToSevenDays", "OneToThreeWeeks", "ThreeOrMoreWeeks"};
        timeChoiceMap = new HashMap<>();
        String[] uiChoices = {"< day", "1 - 3 days", "3 - 7 days", "1 - 3 weeks", "> 3 weeks"};
        for (int i = 0; i < originalChoices.length; i++) {
            timeChoiceMap.put(uiChoices[i], originalChoices[i]);
        }
    }

    public static String getTimeChoiceFromUi(String uiChoice) {
        return timeChoiceMap.get(uiChoice);
    }

    public void assignTicket(ActionEvent event) {
        String ticketID = id.getText();
        String staffEmail = email.getText();
        String timeEstimate = getTimeChoiceFromUi(time.getValue());
        String priorityLevel = priority.getValue();
        String approvalRequired = String.valueOf(approval.isSelected());
        if (ticketID.isEmpty() || staffEmail.isEmpty() || timeEstimate == null || priorityLevel == null || approvalRequired.isEmpty()) {
            ViewUtils.showError("Please fill in the listed fields.");
        } else {
            String result = TicketMaintenanceController.assignStaffToTicket(ticketID, staffEmail, timeEstimate, priorityLevel, approvalRequired);
            if (result.equals("")) {
                id.setText("");
                email.setText("");
                time.setValue(null);
                priority.setValue(null);
                approval.setSelected(false);
            }
            else {
                ViewUtils.showError(result);
            }
        }
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        priority.getItems().addAll(priorityChoice);
        time.getItems().addAll(timeChoice);

        if(ViewTicketsPage.getTicketID() != -1){
            id.setText(String.valueOf(ViewTicketsPage.getTicketID()));}    }

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