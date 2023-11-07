package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.*;

import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;

public class TicketMaintenanceController {

  // for each method, transform variables into proper form and use model to apply
  // changes (validate inputs)
  public static String assignStaffToTicket(String ticketID, String staffEmail, String timeEstimate,
      String priorityLevel, String approvalRequired) {
    if(!MaintenanceTicket.hasWithId(Integer.parseInt(ticketID))){
      return "Maintenance ticket does not exist.";
    }
    if(!HotelStaff.hasWithEmail(staffEmail)){
      return "Staff to assign does not exist.";
    }

    MaintenanceTicket ticket = MaintenanceTicket.getWithId(Integer.parseInt(ticketID));
    try{
      ticket.assignStaff(staffEmail, MaintenanceTicket.PriorityLevel.valueOf(priorityLevel), MaintenanceTicket.TimeEstimate.valueOf(timeEstimate), Boolean.parseBoolean(approvalRequired));
    }catch(Exception e){
      return e.getMessage();
    }
    return "";
  }

  public static String beginTicketWork(String ticketID) {
    int intTicketID = Integer.parseInt(ticketID);
    //Input validation
    if(!MaintenanceTicket.hasWithId(intTicketID)){
      return "Maintenance ticket does not exist.";
    }

    MaintenanceTicket aTicket = MaintenanceTicket.getWithId(intTicketID);
    try {
      aTicket.beginWork();
    } catch (Exception e) {
      return e.getMessage();
    }
    return "";
  }

  public static String completeTicketWork(String ticketID) {
    int intTicketID = Integer.parseInt(ticketID);
    //Input Validation
    if(!MaintenanceTicket.hasWithId(intTicketID)){
      return "Maintenance ticket does not exist.";
    }
    
    MaintenanceTicket aTicket = MaintenanceTicket.getWithId(intTicketID);
    try {
      aTicket.completeWork();
    } catch (Exception e) {
      return e.getMessage();
    }
    return "";
  }
  public static String approveTicketWork(String ticketID){
    //Input validation
    if(!MaintenanceTicket.hasWithId(Integer.parseInt(ticketID))){
      return "Maintenance ticket does not exist.";
    }
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(Integer.parseInt(ticketID));

    try {
      ticket.approve();
    } catch (Exception e) {
      return e.getMessage();
    }
    return "";
  }
  public static String disapproveTicketWork(String ticketID, String date, String reason){
    //Input validation
    if(!MaintenanceTicket.hasWithId(Integer.parseInt(ticketID))){
      return "Maintenance ticket does not exist.";
    }
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(Integer.parseInt(ticketID));

    //to implement
    try{ ticket.disapprove(ticketID, date, reason);
    }catch(Exception e){
      return e.getMessage();
    }
    return "";
  }

}
