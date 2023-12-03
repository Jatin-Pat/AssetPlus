package ca.mcgill.ecse.assetplus.javafx.fxml.controllers;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet7Controller;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceNote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateNote {

    
    @FXML
    private Button cancel;

    @FXML
    private DatePicker noteDate;

    @FXML
    private TextField noteDescription;

    @FXML
    private TextField noteTaker;

    @FXML
    private Label title;

    private static TOMaintenanceNote selectedNote = ViewImageNotes.selectedNote;

    @FXML
    public void initialize(){
      title.setText(title.getText()+" #"+String.valueOf(ViewImageNotes.selectedNoteIndex+1));
      
      noteDate.setValue(LocalDate.now());
      
      if(ViewImageNotes.getSelectedNote() !=null){
        TOMaintenanceNote thisNote = ViewImageNotes.getSelectedNote();
        noteDescription.setText(thisNote.getDescription());
        noteTaker.setText(thisNote.getNoteTakerEmail());
        System.out.println("changing fields");
      }
    }
    @FXML
    public void cancel(ActionEvent event) {
      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/ViewImageNotes.fxml"));
        Parent root = loader.load();

        // Get the current Stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new root for the current Scene
        stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            ViewUtils.showError("Error viewing images and notes");
        }
      }

    public boolean isNumeric(String input){
      try{
        Integer.parseInt(input);
        return true;
      }catch(NumberFormatException e){
        return false;
      }
    }
    @FXML
    void submit(ActionEvent event) {
      
      if(noteDescription.getText()==""){
        ViewUtils.showError("A description must be provided");
      }
      else if(noteDate.getValue()==null){
        ViewUtils.showError("A date must be provided");
      }
      else if(noteTaker.getText().equals("")){
        ViewUtils.showError("A note taker must be provided");
      }
      
      Date newDate = Date.valueOf(noteDate.getValue());
      String newDescription = noteDescription.getText();
      String newTaker = noteTaker.getText();
      //String result = AssetPlusFeatureSet7Controller.updateMaintenanceNote(ViewImageNotes.selectedTicket,ViewImageNotes.selectedNoteIndex , newDate, newDescription, newTaker);
      if(ViewUtils.successful(AssetPlusFeatureSet7Controller.updateMaintenanceNote(ViewImageNotes.selectedTicket,ViewImageNotes.selectedNoteIndex , newDate, newDescription, newTaker))){
        noteDescription.setText("");
        noteTaker.setText("");
        noteDate.setValue(null);
      }
    }

}
