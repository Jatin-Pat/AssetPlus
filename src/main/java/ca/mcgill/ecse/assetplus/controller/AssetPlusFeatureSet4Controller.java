package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import ca.mcgill.ecse.assetplus.model.*;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;

/**
 * @author Pei Yan Geng
 * The AssetPlusFeatureSet4Controller class provides methods for managing maintenance tickets
 * and their associated data (ticket ID, date, email and asset number), including the addition,
 * update, and deletion of maintenance tickets.
 * This controller class interacts with the AssetPlus model.
 */

public class AssetPlusFeatureSet4Controller {

  private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  /**
   * Checks if an email address is valid.
   *
   * @param email The email address to validate.
   * @return True if the email is valid; false otherwise.
   */
  private static boolean isValidEmail(String email) {
    String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9]+(\\.[A-Za-z]+)*$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  /**
   * Checks if a ticket ID is valid.
   *
   * @param id The ID to validate.
   * @return True if the ID is valid; false otherwise.
   */
  private static boolean isValidID(int id) {
    return !(MaintenanceTicket.hasWithId(id));
  }

  /**
   * Checks if a date is valid. A date can't be set in the past.
   *
   * @param raisedOnDate The date to validate.
   * @return True if the date is valid; false otherwise.
   */
  private static boolean isValidDate(Date raisedOnDate) {
    Date todayDate = Date.valueOf(LocalDate.now());
    int comparisonResult = raisedOnDate.compareTo(todayDate);
    return comparisonResult >= 0;
  }
  private static boolean isValidAssetNumber(int assetNumber) {
    return SpecificAsset.getWithAssetNumber(assetNumber) != null;
  }

  /**
   * Adds a maintenance ticket to AssetPlus.
   *
   * @param id The ID of the maintenance ticket.
   * @param raisedOnDate The date when the ticket was raised.
   * @param description The description of the ticket.
   * @param email The email of the user associated with the ticket.
   * @param assetNumber The asset number associated with the ticket, or -1 if none.
   * @return A string containing any error messages, if applicable, or a success message.
   */
  public static String addMaintenanceTicket(int id, Date raisedOnDate, String description, String email, int assetNumber) {
    StringBuilder errorMessage = new StringBuilder();
    if (id < 0) {
      errorMessage.append("Invalid ID \n");
    }
    if (description.isEmpty()) {
      errorMessage.append("Invalid description \n");
    }
    if (!isValidDate(raisedOnDate)) {
      errorMessage.append("Invalid date \n");
    }
    if (!isValidEmail(email)) {
      errorMessage.append("Invalid email \n");
    }
    if (!isValidAssetNumber(assetNumber)) {
      errorMessage.append("Invalid asset number \n");
    }
    if (errorMessage.length() > 0) {
      System.out.println(errorMessage);
      return errorMessage.toString();
    }
    try {
      User aUser = User.getWithEmail(email);
      MaintenanceTicket aTicket = assetPlus.addMaintenanceTicket(id, raisedOnDate, description, aUser);

      assetPlus.addMaintenanceTicket(aTicket);

      if (assetNumber != -1) {
        SpecificAsset aAsset = SpecificAsset.getWithAssetNumber(assetNumber);
        aTicket.setAsset(aAsset);
      }
      else {
        aTicket.setAsset(null);
      }
    }
    catch (Exception e) {
      String eString = e.getMessage();
      if (eString.startsWith("Cannot create due to duplicate id.")) {
        errorMessage.append("Ticket with specified ID already exists \n");
      }
    }
    if (errorMessage.length() == 0) {
      errorMessage.append("Ticket added successfully \n");
    }
    System.out.println(errorMessage);
    return errorMessage.toString();
  }

  /**
   * Updates an existing maintenance ticket.
   *
   * @param id The ID of the ticket to update.
   * @param newRaisedOnDate The new date when the ticket was raised.
   * @param newDescription The new description of the ticket.
   * @param newEmail The new email of the user associated with the ticket.
   * @param newAssetNumber The new asset number associated with the ticket, or -1 if none.
   * @return A string containing any error messages, if applicable, or a success message.
   */
  public static String updateMaintenanceTicket(int id, Date newRaisedOnDate, String newDescription, String newEmail, int newAssetNumber) {
    StringBuilder errorMessage = new StringBuilder();
    if (id < 0) {
      errorMessage.append("Invalid ID \n");
    }
    if (newDescription.isEmpty()) {
      errorMessage.append("Invalid description \n");
    }
    if (!isValidDate(newRaisedOnDate)) {
      errorMessage.append("Invalid date \n");
    }
    if (!isValidEmail(newEmail)) {
      errorMessage.append("Invalid email \n");
    }
    if (!isValidAssetNumber(newAssetNumber)) {
      errorMessage.append("Invalid asset number \n");
    }
    if (errorMessage.length() > 0) {
      System.out.println(errorMessage);
      return errorMessage.toString();
    }
    try {
      List<MaintenanceTicket> maintenanceTickets = assetPlus.getMaintenanceTickets();
      MaintenanceTicket aTicket = maintenanceTickets.stream().filter(ticket -> ticket.getId() == id).findFirst().orElse(null);
      if (aTicket != null) {
        aTicket.setRaisedOnDate(newRaisedOnDate);
        aTicket.setDescription(newDescription);
        User aUser = User.getWithEmail(newEmail);
        aTicket.setTicketRaiser(aUser);
        if (newAssetNumber != -1) {
          SpecificAsset aAsset = SpecificAsset.getWithAssetNumber(newAssetNumber);
          aTicket.setAsset(aAsset);
        }
        //else: leave asset number unmodified
      }
      else {
        errorMessage.append("Ticket not found \n");
      }
    }
    catch (Exception e) {
      errorMessage.append(e.getMessage()).append("\n");
    }
    if (errorMessage.length() == 0) {
      errorMessage.append("Ticket modified successfully \n");
    }
    System.out.println(errorMessage);
    return errorMessage.toString();
  }


  /**
   * Deletes a maintenance ticket from the system.
   *
   * @param id The ID of the ticket to delete.
   */
  public static void deleteMaintenanceTicket(int id) {
    try {
      List<MaintenanceTicket> maintenanceTickets = assetPlus.getMaintenanceTickets();
      MaintenanceTicket ticketToDelete = maintenanceTickets.stream().filter(ticket -> ticket.getId() == id).findFirst().orElse(null);
      if (ticketToDelete != null) {
        ticketToDelete.delete();
        System.out.println("Ticket deleted successfully \n");
      }
      else {
        System.out.println("Ticket not found \n");
      }
    }
    catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
  }
}
