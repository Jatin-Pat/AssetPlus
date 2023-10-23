package ca.mcgill.ecse.assetplus.controller;

import java.util.List;

public class AssetPlusFeatureSet6Controller {

  public static void deleteEmployeeOrGuest(String email) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
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
        String assetName = null;
        int lifeSpan = -1;
        Date purchaseDate = null;
        int aFloorNumber = -1;
        int aRoomNumber = -1;
        if (ticket.hasAsset()){
          SpecificAsset asset = ticket.getAsset();
          
           assetName = asset.getAssetType().getName();
           lifeSpan = asset.getAssetType().getExpectedLifeSpan();
           purchaseDate = asset.getPurchaseDate();
           aFloorNumber = asset.getFloorNumber();
           aRoomNumber = asset.getRoomNumber();
          }
        
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
