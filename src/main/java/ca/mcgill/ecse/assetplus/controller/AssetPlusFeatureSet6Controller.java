package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import java.util.List;
import java.util.TooManyListenersException;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.*;



public class AssetPlusFeatureSet6Controller {

  public static void deleteEmployeeOrGuest(String email) {
    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

    List<Guest> guests = assetPlus.getGuests();
    List<Employee> employees = assetPlus.getEmployees();

    //Checking if email is manager's email
    if(email.equals("manager@ap.com")){
      throw new IllegalArgumentException("Cannot delete Manager");
    }

    //Checking if employee with given email exists
    if(employees[0].hasWithEmail(email)){
      Employee target = employees[0].getWithEmail(email);
    }
    //Checking if guest with given email exists
    else if(guests[0].hasWithEmail(email)){
      Guest target = guests[0].getWithEmail(email);
    }
    //Throw error since no user with given email exists
    else{
        throw new IllegalArgumentException("No user with given email");
    }
    target.delete();
  }

  // returns all tickets
  public static List<TOMaintenanceTicket> getTickets() {
      AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
      List<TOMaintenanceTicket> listOfTickets;
      List<MaintenanceTicket> maintenanceTickets = assetPlus.getMaintenanceTickets();
      
      for (MaintenanceTicket ticket : maintenanceTickets) {
        List<TOMaintenanceNote> allNotes;
        
        //Getting Primitive Ticket attributes
        int aID = ticket.getId();
        Date aRaisedOnDate = ticket.getRaisedOnDate();
        String aDescription = ticket.getDescription();
        String aRaiser = ticket.getTicketRaiser().getEmail();
        
        //Getting SpecificAsset Information
        SpecificAsset asset = ticket.getAsset();
        String aAssetName = asset.getAssetType().getName();
        int lifeSpan = asset.getAssetType().getExpectedLifeSpan();
        Date purchaseDate = asset.getPurchaseDate();
        int aFloorNumber = asset.getFloorNumber();
        int aRoomNumber = asset.getRoomNumber();
        
        //Getting Ticket Image URLS
        List<String> imageURLS;
        List<TicketImage> imageList = ticket.getTicketImages();
        for (TicketImage TicketImage : imageList) {
          imageURLS.add(TicketImage.getImageURL());
        }
        
        //Getting Ticket Notes
        for (MaintenanceNote note : ticket.getTicketNotes()) {
          Date noteDate = note.getDate();
          String noteDescription = note.getDescription();
          String noteTaker = note.getNoteTaker();
          TOMaintenanceNote MaintenanceNote = new TOMaintenanceNote(noteDate, noteDescription, noteTaker);
          allNotes.add(MaintenanceNote);
        }

        TOMaintenanceTicket TOMaintenanceTicket = new TOMaintenanceTicket(aID, aRaisedOnDate, aDescription, aRaiser, aAssetName, lifeSpan, purchaseDate, aFloorNumber, aRoomNumber, imageURLS, allNotes);
        listOfTickets.add(TOMaintenanceTicket);
      }
    return listOfTickets;
  }

}
