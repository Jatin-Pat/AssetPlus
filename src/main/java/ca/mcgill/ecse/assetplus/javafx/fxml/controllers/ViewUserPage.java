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
    void refreshUser(ActionEvent event){
      System.out.println("buttonClicked");
      //TODO - Way yo get employee
      //TicketsView.setItems(getEmployees());
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

      TableColumn<TOUser, Integer> userRole = new TableColumn<TOUser, Integer>("Role");
      userRole.setCellValueFactory(new PropertyValueFactory<TOUser, Integer>("type"));
      
      TableColumn<TOUser, Date> userName = new TableColumn<TOUser, Date>("Name");
      userName.setCellValueFactory(new PropertyValueFactory<TOUser, Date>("name"));

      TableColumn<TOUser, String> userEmail = new TableColumn<TOUser, String>("Email");
      userEmail.setCellValueFactory(new PropertyValueFactory<TOUser, String>("email"));

      TableColumn<TOUser, String> userPhoneNumber = new TableColumn<TOUser, String>("Phone Number");
      userPhoneNumber.setCellValueFactory(new PropertyValueFactory<TOUser, String>("phone"));

      TableColumn<TOUser, String> userPassword = new TableColumn<TOUser, String>("Password");
      userPassword.setCellValueFactory(new PropertyValueFactory<TOUser, String>("password"));

      
      //TableColumn<TOMaintenanceTicket, String> statusColumn = new TableColumn<TOMaintenanceTicket, String>("Status");
      //statusColumn.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("status"));

      TicketsView.getColumns().add(userRole);
      TicketsView.getColumns().add(userName);
      TicketsView.getColumns().add(userEmail);
      TicketsView.getColumns().add(userPhoneNumber);
      TicketsView.getColumns().add(userPassword);
      //TicketsView.getColumns().add(statusColumn);

      
      TicketsView.getItems().add(new TOUser(userEmail,userName,userPassword,userPassword,userRole));

      TicketsView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      //TicketsView.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT, e -> TicketsView.setItems(getUser()));

    }
  }

