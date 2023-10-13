package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.*;


public class AssetPlusFeatureSet6Controller {

  public static void deleteEmployeeOrGuest(String email) {
    
    User queriedUser = User.getWithEmail(email);
    if(queriedUser instanceof Guest || queriedUser instanceof Employee){
      queriedUser.delete();
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
