package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import ca.mcgill.ecse.assetplus.controller.*;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Syntax;
import com.google.common.collect.Table;


public class ViewTicketsPage {
    private static int currentTicket;

    @FXML
    private Button openAssignTicket;

    @FXML
    private Button openApproveTicket;

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
    private TextField nameFilter;

    @FXML
    private TextField emailFilter;

    @FXML
    private Button refreshTickets;

    @FXML
    private TableView<TOMaintenanceTicket> TicketsView;

    @FXML
    private Button viewImageNote;


    public static int getTicketID(){
      return currentTicket;
    }
    private static void setCurrentIDToNull() {
        //setting it to -1
        currentTicket = -1;
    }

    @FXML
    void selectTicket(MouseEvent event) {
      System.out.println("clicked");
      if (event.getClickCount() == 1) {
        // Get the selected item
        TOMaintenanceTicket selectedTicket = TicketsView.getSelectionModel().getSelectedItem();
        if(selectedTicket==null){
          return;
        }
        currentTicket = selectedTicket.getId();
        if (currentTicket != -1) {
            // Debugging output
            System.out.println("Selected Ticket ID: " + selectedTicket.getId());
        }
    }
  }
    @FXML
    void refreshTickets(ActionEvent event){
      System.out.println("buttonClicked");
      TicketsView.setItems(getMaintenanceTickets());
      AssetPlusFxmlView.getInstance().refresh();
    }
    
    @FXML
    void refreshEmployees(KeyEvent event) {
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

      TableColumn<TOMaintenanceTicket, String> descriptionColumn = new TableColumn<TOMaintenanceTicket, String>("Description");
      descriptionColumn.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("description"));
      
      TableColumn<TOMaintenanceTicket, String> raiserColumn = new TableColumn<TOMaintenanceTicket, String>("Raised by");
      raiserColumn.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("raisedByEmail"));


      TicketsView.getColumns().add(ticketIDColumn);
      TicketsView.getColumns().add(dateColumn);
      TicketsView.getColumns().add(raiserColumn);
      TicketsView.getColumns().add(assignedToColumn);
      TicketsView.getColumns().add(timeToResolveColumn);
      TicketsView.getColumns().add(priorityColumn);
      TicketsView.getColumns().add(statusColumn);
      TicketsView.getColumns().add(descriptionColumn);


      TicketsView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
      TicketsView.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> TicketsView.setItems(getMaintenanceTickets()));

    }

    public ObservableList<TOMaintenanceTicket> getMaintenanceTickets(){
      LocalDate selectedDate = dateFilter.getValue();
      String employeeName = nameFilter.getText();
      String employeeEmail = emailFilter.getText();
      
      System.out.println(selectedDate + " 1");
      System.out.println(employeeName + " 2");

      System.out.println("GETTING TICKETS AGAIN");
      List<TOMaintenanceTicket> tickets = new ArrayList<TOMaintenanceTicket>();
      
      if(selectedDate == null && employeeName == ""){
        System.out.println("BOTH NULL");
        tickets = AssetPlusFeatureSet6Controller.getTickets();
      }
      else if(selectedDate != null && employeeName != ""){
        System.out.println("Filter by both");
        tickets = AssetPlusFeatureSet6Controller.filterByBoth(Date.valueOf(selectedDate), employeeName);
      }
      else if(selectedDate != null){
        System.out.println("Getting Tickets by Date only");
        tickets = AssetPlusFeatureSet6Controller.filterTicketsByDate(Date.valueOf(selectedDate));
      }
      else if(employeeName != ""){
        System.out.println("Filter by employee");
        tickets = AssetPlusFeatureSet6Controller.filterTicketsByEmployee(employeeName);
      }
      
      List<TOMaintenanceTicket> emailFilteredTickets = new ArrayList<TOMaintenanceTicket>();
      if(employeeEmail!=""){
        for (TOMaintenanceTicket ticket : tickets) {
          if(ticket.getRaisedByEmail()!=null && ticket.getRaisedByEmail().contains(employeeEmail)){
            emailFilteredTickets.add(ticket);
          }
        }
        return FXCollections.observableList(emailFilteredTickets);
      }
      return FXCollections.observableList(tickets);
      
    }
    @FXML
    void deleteTicket(ActionEvent event) {
        System.out.println("Selected Ticket ID: " + currentTicket);
        if (currentTicket != -1) {

            AssetPlusFeatureSet4Controller.deleteMaintenanceTicket(currentTicket);
            refreshTickets(new ActionEvent());
            currentTicket = -1;

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

    TOMaintenanceTicket getTicket(){
      List<TOMaintenanceTicket> allTickets = AssetPlusFeatureSet6Controller.getTickets();
      for (TOMaintenanceTicket ticket : allTickets) {
        if(ticket.getId()==getTicketID()){
          return ticket;
        }
      }
      return null;
    } 
    
    @FXML
    void openUpdateTicket(ActionEvent event) {
        try {
          if(getTicketID()!=-1){
            if (getTicket().getStatus().equals("Closed")) {
                ViewUtils.showError("Cannot update a closed ticket");
                return;
            }
          }
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

    @FXML
    void openAssignTicket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/AssignHotelStaffToTicket.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.showError("Error opening Assign Ticket page\n");
        }
    }

    @FXML
    void openApproveTicket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/ApproveDisapproveWork.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.showError("Error opening Approve Ticket page\n");
        }
    }
    
    @FXML
    void viewImageNote(ActionEvent event) {
      if(currentTicket==-1){
        ViewUtils.showError("Select a ticket first");
      }
      else{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/ViewImageNotes.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.showError("Error opening images and notes\n");
        }
      }
    }

}
