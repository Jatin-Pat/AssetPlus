package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.ArrayList;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceNote;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;

public class AssetPlusFeatureSet6Controller {

  public static void deleteEmployeeOrGuest(String email) {

    //Checking if email is manager's email
    if(email.equals("manager@ap.com")){
      throw new IllegalArgumentException("Cannot delete Manager");
    }
    
    //Checking if employee with given email exists
    if(Employee.hasWithEmail(email)){
      Employee.getWithEmail(email).delete();
    }
    //Checking if guest with given email exists
    else if(Guest.hasWithEmail(email)){
      Guest.getWithEmail(email).delete();
    }
    //Throw error since no user with given email exists
    else{
        throw new IllegalArgumentException("No user with given email");
    }
  }

  // returns all tickets
  public static List<TOMaintenanceTicket> getTickets() {
      AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
      List<TOMaintenanceTicket> listOfTickets = new ArrayList<TOMaintenanceTicket>(); //Return value
      List<MaintenanceTicket> maintenanceTickets = assetPlus.getMaintenanceTickets();
      
      for (MaintenanceTicket ticket : maintenanceTickets) {
        
        //Getting Primitive Ticket attributes
        int aID = ticket.getId();
        Date aRaisedOnDate = ticket.getRaisedOnDate();
        String aDescription = ticket.getDescription();
        String emailRaiser = ticket.getTicketRaiser().getEmail();
        
        //Getting SpecificAsset Information
        SpecificAsset asset = ticket.getAsset();
        
        String assetName = asset.getAssetType().getName();
        int lifeSpan = asset.getAssetType().getExpectedLifeSpan();
        Date purchaseDate = asset.getPurchaseDate();
        int aFloorNumber = asset.getFloorNumber();
        int aRoomNumber = asset.getRoomNumber();
        
        //Getting Ticket Image URLS
        List<String> imageURLS = new ArrayList<String>();
        List<TicketImage> imageList = ticket.getTicketImages();
        for (TicketImage TicketImage : imageList) {
          imageURLS.add(TicketImage.getImageURL());
        }
        
        //Getting Ticket Notes
        TOMaintenanceNote[] allNotes = new TOMaintenanceNote[ticket.numberOfTicketNotes()];
        int i=0;
        //Converting from List type to array
        for (MaintenanceNote note : ticket.getTicketNotes()) {
          Date noteDate = note.getDate();
          String noteDescription = note.getDescription();
          String noteTaker = note.getNoteTaker().getEmail();
          TOMaintenanceNote MaintenanceNote = new TOMaintenanceNote(noteDate, noteDescription, noteTaker);
          allNotes[i] = MaintenanceNote;
          i++;
        }
        //Constructing TO of current Ticket
        TOMaintenanceTicket TOTicket = new TOMaintenanceTicket( aID,  aRaisedOnDate,  aDescription, emailRaiser , assetName , lifeSpan,  purchaseDate , aFloorNumber, aRoomNumber, imageURLS, allNotes);
        listOfTickets.add(TOTicket);
      }
    return listOfTickets;
  }

}
