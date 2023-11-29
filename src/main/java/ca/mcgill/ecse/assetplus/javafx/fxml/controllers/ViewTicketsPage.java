package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Syntax;


public class ViewTicketsPage {
    private static int currentTicket;

    @FXML
    public Button deleteTicket;
    
    @FXML
    public Button setProgress;

    @FXML
    public Button openAddTicket;

    @FXML
    public Button openUpdateTicket;

    @FXML
    private DatePicker dateFilter;

    @FXML
    private TextField employeeFilter;

    @FXML
    private Button refreshTickets;

    @FXML
    private TableView<TOMaintenanceTicket> TicketsView;

  
    public static int getTicektID(){
      return currentTicket;
    }
    private static void setCurrentIDToNull() {
        //setting it to -1
        currentTicket = -1;
    }

    @FXML
    void selectTicket(MouseEvent event) {
        TOMaintenanceTicket selectedTicket = TicketsView.getSelectionModel().getSelectedItem();
        currentTicket = selectedTicket.getId();
    }

    @FXML
    void refreshTickets(ActionEvent event){
      System.out.println("buttonClicked");
      TicketsView.setItems(getMaintenanceTickets());
      AssetPlusFxmlView.getInstance().refresh();
    }
    @FXML
    void clearDate(MouseEvent event){
      dateFilter.setValue(null);
    }
   
    public void initialize(){
      System.out.println("initialized");
      dateFilter.setEditable(false);
      
      
      TicketsView.setPlaceholder(new Label("No tickets found"));
      
      refreshTickets(new ActionEvent());
      setCurrentIDToNull();

      TableColumn<TOMaintenanceTicket, Integer> ticketIDColumn = new TableColumn<TOMaintenanceTicket, Integer>("Ticket ID");
      ticketIDColumn.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, Integer>("id"));
      
      TableColumn<TOMaintenanceTicket, Date> dateColumn = new TableColumn<TOMaintenanceTicket, Date>("Date");
      dateColumn.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, Date>("raisedOnDate"));

      TableColumn<TOMaintenanceTicket, String> assignedToColumn = new TableColumn<TOMaintenanceTicket, String>("Assigned To");
      assignedToColumn.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("fixedByEmail"));

      TableColumn<TOMaintenanceTicket, String> timeToResolveColumn = new TableColumn<TOMaintenanceTicket, String>("Time To Resolve");
      timeToResolveColumn.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("timeToResolve"));

      TableColumn<TOMaintenanceTicket, String> priorityColumn = new TableColumn<TOMaintenanceTicket, String>("Priority");
      priorityColumn.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("priority"));
      
      TableColumn<TOMaintenanceTicket, String> statusColumn = new TableColumn<TOMaintenanceTicket, String>("Status");
      statusColumn.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("status"));

      TicketsView.getColumns().add(ticketIDColumn);
      TicketsView.getColumns().add(dateColumn);
      TicketsView.getColumns().add(assignedToColumn);
      TicketsView.getColumns().add(timeToResolveColumn);
      TicketsView.getColumns().add(priorityColumn);
      TicketsView.getColumns().add(statusColumn);


      List<String> imageULRS = new ArrayList<String>();

      TicketsView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
      TicketsView.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> TicketsView.setItems(getMaintenanceTickets()));

    }

    public ObservableList<TOMaintenanceTicket> getMaintenanceTickets(){
      LocalDate selectedDate = dateFilter.getValue();
      String employeeName = employeeFilter.getText();
      
      System.out.println(selectedDate + " 1");
      System.out.println(employeeName + " 2");

      System.out.println("GETTING TICKETS AGAIN");
      List<TOMaintenanceTicket> tickets = null;
      
      if(selectedDate == null && employeeName == ""){
        System.out.println("BOTH NULL");
        tickets = AssetPlusFeatureSet6Controller.getTickets();
      }
      else if(selectedDate != null && employeeName != ""){
        System.out.println("Filter by both");
        AssetPlusFeatureSet6Controller.filterByBoth(Date.valueOf(selectedDate), employeeName);
      }
      else if(selectedDate != null){
        System.out.println("Getting Tickets by Date only");
        tickets = AssetPlusFeatureSet6Controller.filterTicketsByDate(Date.valueOf(selectedDate));
      }
      else if(employeeName != ""){
        System.out.println("Filter by employee");
        tickets = AssetPlusFeatureSet6Controller.filterTicketsByEmployee(employeeName);
      }
      
      return FXCollections.observableList(tickets);
      
    }
    @FXML
    void deleteTicket(ActionEvent event) {
        System.out.println("Selected User Email: " + currentTicket);
        if (currentTicket != 0) {

            AssetPlusFeatureSet4Controller.deleteMaintenanceTicket(currentTicket);
            refreshTickets(new ActionEvent());
            currentTicket = 0;

        } else {
            ViewUtils.showError("Select Ticket to Delete");
        }

    }
    @FXML
    void setProgress(ActionEvent event) {
        
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/startCompleteWorkTicket.fxml"));
        Parent root = loader.load();

        // Get the current Stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new root for the current Scene
        stage.getScene().setRoot(root);
    } catch (IOException e) {
        System.err.println(e);
        e.printStackTrace();
        ViewUtils.showError("Error opening image upload page\n");
    }

    }


    @FXML
    void openAddTicket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/AddTicket.fxml"));
            Parent root = loader.load();

            // Get the current Stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new root for the current Scene
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.showError("Error opening Add Ticket page");
        }
    }

    @FXML
    void openUpdateTicket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/UpdateTicket.fxml"));
            Parent root = loader.load();

            // Get the current Stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new root for the current Scene
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.showError("Error opening Update Ticket page\n");
        }
    }
}
