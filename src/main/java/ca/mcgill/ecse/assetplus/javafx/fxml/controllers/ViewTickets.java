package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Date;
import java.time.LocalDate;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;


public class ViewTickets {

    @FXML
    private DatePicker dateFilter;

    @FXML
    private TextField employeeFilter;

    @FXML
    private Button refreshTickets;

    @FXML
    private TableView<TOMaintenanceTicket> TicketsView;


    public void initialize(){
      
      refreshTickets.setOnAction(new EventHandler<ActionEvent>(){
        @Override public void handle(ActionEvent e){
          TicketsView.setItems(getMaintenanceTickets());
        }
      });


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
      TicketsView.getItems().add(new TOMaintenanceTicket(1, Date.valueOf("2020-02-02"), "null", "null", "null", "null", "null", "null", false, "null", 0, Date.valueOf("2020-03-03"), 0, 0, imageULRS ));


      TicketsView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


      TicketsView.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> TicketsView.setItems(getMaintenanceTickets()));

    }

    public ObservableList<TOMaintenanceTicket> getMaintenanceTickets(){
      LocalDate selectedDate = dateFilter.getValue();
      String employeeName = employeeFilter.getText();

      List<TOMaintenanceTicket> tickets = null;
      
      if(selectedDate == null && employeeName == null){
        tickets = AssetPlusFeatureSet6Controller.getTickets();
      }
      else if(selectedDate != null && employeeName != null){
        AssetPlusFeatureSet6Controller.filterByBoth(Date.valueOf(selectedDate), employeeName);
      }
      else if(selectedDate != null){
        tickets = AssetPlusFeatureSet6Controller.filterTicketsByDate(Date.valueOf(selectedDate));
      }
      else if(employeeName != null){
        tickets = AssetPlusFeatureSet6Controller.filterTicketsByEmployee(employeeName);
      }
      
      return FXCollections.observableList(tickets);
      
    }
}
