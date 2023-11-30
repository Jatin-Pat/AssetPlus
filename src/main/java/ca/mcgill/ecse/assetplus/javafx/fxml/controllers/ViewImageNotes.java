package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet5Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet7Controller;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceNote;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewImageNotes {

    private MainPage mainPage;

    @FXML
    private Button addImage;

    @FXML
    private Button addNote;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteImage;

    @FXML
    private Button deleteNote;

    @FXML
    private TableView<String> imagesView;

    @FXML
    private TableView<TOMaintenanceNote> notesView;

    @FXML
    private Label title;

    @FXML
    private Button updateNote;

    public static String selectedURL = null;
    public static TOMaintenanceNote selectedNote = null;
    public static int selectedTicket = ViewTicketsPage.getTicketID();

    public void initialize(){
      title.setText(title.getText()+": Ticket ID#"+selectedTicket);

      imagesView.setPlaceholder(new Label("No Image URLs Found"));
      notesView.setPlaceholder(new Label("No notes found"));

      TableColumn<TOMaintenanceNote, String> noteWriter = new TableColumn<TOMaintenanceNote, String>("Written by");
      noteWriter.setCellValueFactory(new PropertyValueFactory<TOMaintenanceNote, String>("noteTakerEmail"));
      
      TableColumn<TOMaintenanceNote, Date> dateRaised = new TableColumn<TOMaintenanceNote, Date>("Raised On");
      dateRaised.setCellValueFactory(new PropertyValueFactory<TOMaintenanceNote, Date>("date"));
      
      TableColumn<TOMaintenanceNote, String> description = new TableColumn<TOMaintenanceNote, String>("Note Description");
      description.setCellValueFactory(new PropertyValueFactory<TOMaintenanceNote, String>("description"));
      
      notesView.getColumns().add(dateRaised);
      notesView.getColumns().add(noteWriter);
      notesView.getColumns().add(description);


      TableColumn<String, String> imageURLS = new TableColumn<String, String>("Image URL");
      imageURLS.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue()));
      imageURLS.setMaxWidth(600);
      imagesView.getColumns().add(imageURLS);

      setImageNotes();
    }

    TOMaintenanceTicket getTicket(){
      for (TOMaintenanceTicket ticket : AssetPlusFeatureSet6Controller.getTickets()) {
        if(ticket.getId()==selectedTicket){
          return ticket;
        }
      }
      ViewUtils.showError("Could not find ticket (unexpected)");
      return null;

    }
    
    void setImageNotes(){
      TOMaintenanceTicket ticket = getTicket();
      notesView.setItems(FXCollections.observableList(ticket.getNotes()));
      imagesView.setItems(FXCollections.observableList(ticket.getImageURLs()));
    }

    @FXML
    void addImage(ActionEvent event) {
      //Open add image page
    }

    @FXML
    void addNote(ActionEvent event) {
      //Open add note page
    }

    @FXML
    void deleteImage(ActionEvent event) {
      if(selectedURL!=null){
        AssetPlusFeatureSet5Controller.deleteImageFromMaintenanceTicket(selectedURL, selectedTicket);
      }
      else{
        ViewUtils.showError("Select a URL first");
      }
      setImageNotes();
    }

    @FXML
    void deleteNote(ActionEvent event) {
      int noteIndex=-1;
      if(selectedNote!=null){
        List<TOMaintenanceNote> allNotes = getTicket().getNotes();
        for (TOMaintenanceNote note : allNotes) {
          if(note.getDescription().equals(selectedNote.getDescription())&&
              note.getNoteTakerEmail().equals(selectedNote.getNoteTakerEmail())&&
              note.getDate().equals(selectedNote.getDate())){
                noteIndex = allNotes.indexOf(note);
              }
        }
        AssetPlusFeatureSet7Controller.deleteMaintenanceNote(selectedTicket,noteIndex);
      
      
      }
      else{
        ViewUtils.showError("Select a note first");
      }
      setImageNotes();
    }

    
    @FXML
    void goBack(ActionEvent event) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainPage.fxml"));
        Parent root = loader.load();

        // Get the current Stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new root for the current Scene
        stage.getScene().setRoot(root);
        mainPage = loader.getController();

        mainPage.selectTab(1);
        
    
      } catch (IOException e) {
        e.printStackTrace();
        ViewUtils.showError("Error Changing Page\n");
      }
      
    }

    @FXML
    void selectRow(MouseEvent event) {
      String attemptedSelURL = imagesView.getSelectionModel().getSelectedItem();
      TOMaintenanceNote attemptedSelNote = notesView.getSelectionModel().getSelectedItem();

      if(attemptedSelNote!=null){
        System.out.println("Selected note");
        selectedNote = attemptedSelNote;
      }
      if(attemptedSelURL!=null){
        System.out.println("Selected url");
        selectedURL = attemptedSelURL;
      }
    }

    @FXML
    void updateNote(ActionEvent event) {
      //Open update Note page
    }

}
