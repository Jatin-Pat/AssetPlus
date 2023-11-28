package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import javafx.stage.Stage;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.ExtraFeaturesController;
import ca.mcgill.ecse.assetplus.controller.TOUser;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ViewUserPage {

    private static String currrentUser;
    
    @FXML
    private Button openAddUser;

    @FXML
    private Button openUpdateUser;

    @FXML
    private Button deleteUser;

    @FXML
    private Button refreshUser;

    @FXML
    private CheckBox showEmployees;

    @FXML
    private CheckBox showManager;

    @FXML
    private CheckBox showUsers;

    @FXML
    private TableView<TOUser> UserView;

    public static String getUserEmail(){
        return currrentUser;
    }


    @FXML
    void refreshUser(ActionEvent event){
      System.out.println("buttonClicked");
      List<TOUser> users = getFilteredUsers();
      
      UserView.setItems(FXCollections.observableList(users));

      AssetPlusFxmlView.getInstance().refresh();
    }

    public void initialize(){
      System.out.println("initialized");

      
      UserView.setPlaceholder(new Label("No users found"));

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



      UserView.getColumns().add(userRole);
      UserView.getColumns().add(userName);
      UserView.getColumns().add(userEmail);
      UserView.getColumns().add(userPhoneNumber);
      UserView.getColumns().add(userPassword);
      
      UserView.getItems().add(new TOUser("userEmail","userName","userPassword","userPassword","Guest"));

      UserView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }


    public List<TOUser> getFilteredUsers(){
      List<TOUser> filteredUsers = new ArrayList<TOUser>();

      boolean employees_shown = showEmployees.isSelected();
      boolean users_shown = showUsers.isSelected();
      boolean manager_shown = showManager.isSelected();

      if((!employees_shown && !users_shown && !manager_shown)|| (employees_shown && users_shown && manager_shown)){
        return ExtraFeaturesController.getUsers();
      }
      if(manager_shown){
        filteredUsers.addAll(ExtraFeaturesController.getManager());
      }
      if(employees_shown){
        filteredUsers.addAll(ExtraFeaturesController.getEmployees());
      }
      if(users_shown){
        filteredUsers.addAll(ExtraFeaturesController.getGuests());
      }
      return filteredUsers;
    }


  

    @FXML
    void openUpdateUser(ActionEvent event) {
      try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/updateUser.fxml"));
      Parent root = loader.load();

      // Get the current Stage
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      // Set the new root for the current Scene
      stage.getScene().setRoot(root);
    } catch (IOException e) {
      e.printStackTrace();
      ViewUtils.showError("Error opening image upload page\n");
    }


    }
    @FXML
    void openAddUser(ActionEvent event) {
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/addUser.fxml"));
            Parent addUserRoot = loader.load();

            // Get the current Stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Create a new BorderPane for the main scene
            BorderPane mainPane = new BorderPane();
            
            mainPane.setCenter(addUserRoot);

            // Set the new root for the current Scene
            stage.setScene(new Scene(mainPane));

        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.showError("Error opening Add User page");
        }


    }

    @FXML
    void selectUser(MouseEvent event) {
      if (event.getClickCount() == 1) {
        // Get the selected item
        TOUser selectedUser = UserView.getSelectionModel().getSelectedItem();
        currrentUser = selectedUser.getEmail();
        if (currrentUser != null) {
            // Debugging output
            System.out.println("Selected User Email: " + selectedUser.getEmail());
        }
    }
  }

    @FXML
    void deleteUser(ActionEvent event) {
      //TOUser selectedUser = UserView.getSelectionModel().getSelectedItem();
      System.out.println("Selected User Email: " + currrentUser);
      if (currrentUser != null) {
        if (!currrentUser.equals("manager@ap.com")) {
            AssetPlusFeatureSet6Controller.deleteEmployeeOrGuest(currrentUser);
            // Additional code after deletion if needed
        } else {
            // Handle the case where the user is the manager
            ViewUtils.showError("Cannot delete the manager");
        }
    }
  }

}
