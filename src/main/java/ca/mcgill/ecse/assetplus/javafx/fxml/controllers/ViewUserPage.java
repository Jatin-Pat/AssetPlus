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
import ca.mcgill.ecse.assetplus.controller.ExtraFeaturesController;
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
      List<TOUser> users = ExtraFeaturesController.getUsers();
      
      TicketsView.setItems(FXCollections.observableList(users));

      AssetPlusFxmlView.getInstance().refresh();
    }
    @FXML
    void clearDate(MouseEvent event){
      dateFilter.setValue(null);
    }
   
    public void initialize(){
      System.out.println("initialized");
      dateFilter.setEditable(false);
      
      TicketsView.setPlaceholder(new Label("No users found"));

      TableColumn<TOUser, String> userRole = new TableColumn<TOUser, String>("Role");
      userRole.setCellValueFactory(new PropertyValueFactory<TOUser, String>("userType"));
      
      TableColumn<TOUser, String> userName = new TableColumn<TOUser, String>("Name");
      userName.setCellValueFactory(new PropertyValueFactory<TOUser, String>("name"));

      TableColumn<TOUser, String> userEmail = new TableColumn<TOUser, String>("Email");
      userEmail.setCellValueFactory(new PropertyValueFactory<TOUser, String>("email"));

      TableColumn<TOUser, String> userPhoneNumber = new TableColumn<TOUser, String>("Phone Number");
      userPhoneNumber.setCellValueFactory(new PropertyValueFactory<TOUser, String>("phoneNumber"));

      TableColumn<TOUser, String> userPassword = new TableColumn<TOUser, String>("Password");
      userPassword.setCellValueFactory(new PropertyValueFactory<TOUser, String>("password"));



      TicketsView.getColumns().add(userRole);
      TicketsView.getColumns().add(userName);
      TicketsView.getColumns().add(userEmail);
      TicketsView.getColumns().add(userPhoneNumber);
      TicketsView.getColumns().add(userPassword);
      
      TicketsView.getItems().add(new TOUser("userEmail","userName","userPassword","userPassword","Guest"));

      TicketsView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }
  }

