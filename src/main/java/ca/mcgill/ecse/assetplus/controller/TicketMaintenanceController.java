package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.*;

import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;

public class TicketMaintenanceController {

  // for each method, transform variables into proper form and use model to apply
  // changes (validate inputs)
  public static String assignStaffToTicket(String ticketID, String staffEmail, String timeEstimate,
      String priorityLevel, String approvalRequired) {
    // to implement
    return "";
  }

  public static String beginTicketWork(String ticketID) {
    int intTicketID = Integer.parseInt(ticketID);
    MaintenanceTicket aTicket = MaintenanceTicket.getWithId(intTicketID);
    aTicket.beginWork();
    return "";
  }

  public static String completeTicketWork(String ticketID) {
    // to implement
    int intTicketID = Integer.parseInt(ticketID);
    MaintenanceTicket aTicket = MaintenanceTicket.getWithId(intTicketID);
    aTicket.completeWork();
    return "";
  }
  public static String approveTicketWork(String ticketID){
    //Input validation
    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
    boolean ticketExists = MaintenanceTicket.hasWithId(Integer.parseInt(ticketID));
    if(!ticketExists){
      return "Maintenance ticket does not exist";
    }

    //to implement
    return "";
  }
  public static String disapproveTicketWork(String ticketID, String date, String reason){
    AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
    //Input validation
    boolean ticketExists = MaintenanceTicket.hasWithId(Integer.parseInt(ticketID));
    if(!ticketExists){
      return "Maintenance ticket does not exist";
    }


    //to implement
    return "";
  }

}
