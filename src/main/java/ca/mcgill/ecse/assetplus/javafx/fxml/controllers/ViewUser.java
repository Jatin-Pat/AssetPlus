package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.TOUser;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Syntax;

public class ViewUserPage {

    @FXML
    private DatePicker dateFilter;

    @FXML
    private TextField employeeFilter;

    @FXML
    private Button refreshTickets;

    @FXML
    private TableView<TOUser> TicketsView;

    @FXML
    void refreshTickets(ActionEvent event){
      System.out.println("buttonClicked");
      //TODO
      TicketsView.setItems(getUsers());
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

      TableColumn<TOUser, Integer> ticketIDColumn = new TableColumn<TOUser, Integer>("Role");
      ticketIDColumn.setCellValueFactory(new PropertyValueFactory<TOUser, Integer>("type"));
      
      TableColumn<TOUser, Date> dateColumn = new TableColumn<TOUser, Date>("Name");
      dateColumn.setCellValueFactory(new PropertyValueFactory<TOUser, Date>("name"));

      TableColumn<TOUser, String> assignedToColumn = new TableColumn<TOUser, String>("Email");
      assignedToColumn.setCellValueFactory(new PropertyValueFactory<TOUser, String>("email"));

      TableColumn<TOUser, String> timeToResolveColumn = new TableColumn<TOUser, String>("Phone Number");
      timeToResolveColumn.setCellValueFactory(new PropertyValueFactory<TOUser, String>("phone"));

      TableColumn<TOUser, String> priorityColumn = new TableColumn<TOUser, String>("Password");
      priorityColumn.setCellValueFactory(new PropertyValueFactory<TOUser, String>("password"));

      
      //TableColumn<TOMaintenanceTicket, String> statusColumn = new TableColumn<TOMaintenanceTicket, String>("Status");
      //statusColumn.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("status"));

      TicketsView.getColumns().add(ticketIDColumn);
      TicketsView.getColumns().add(dateColumn);
      TicketsView.getColumns().add(assignedToColumn);
      TicketsView.getColumns().add(timeToResolveColumn);
      TicketsView.getColumns().add(priorityColumn);
      //TicketsView.getColumns().add(statusColumn);

      List<String> imageULRS = new ArrayList<String>();
      TicketsView.getItems().add(new TOUser(1, Date.valueOf("2020-02-02"), "null", "null", "null", "null", "null", "null", false, "null", 0, Date.valueOf("2020-03-03"), 0, 0, imageULRS ));

      TicketsView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      TicketsView.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> TicketsView.setItems(getUser()));

    }
  }

