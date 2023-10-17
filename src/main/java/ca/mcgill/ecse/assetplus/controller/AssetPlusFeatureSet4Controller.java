package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
/**
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
    String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.find();
  }
  /**
   * Checks if a ticket ID is valid.
   *
   * @param id The ID to validate.
   * @return True if the ID is valid; false otherwise.
   */
  private static boolean isValidID(int id) {
    return id >= 0;
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
  /**
   * Adds a maintenance ticket to AssetPlus.
   *
   * @param id The ID of the maintenance ticket.
   * @param raisedOnDate The date when the ticket was raised.
   * @param description The description of the ticket.
   * @param email The email of the user associated with the ticket.
   * @param assetNumber The asset number associated with the ticket, or -1 if none.
   * @return A string containing any error messages, if applicable.
   */
  public static String addMaintenanceTicket(int id, Date raisedOnDate, String description,
      String email, int assetNumber) {
    StringBuilder errorMessage = new StringBuilder();
    if (! isValidEmail(email)) {
      errorMessage.append("Invalid email \n");
    }
    if (! isValidID(id)) {
      errorMessage.append("Invalid ID \n");
    }
    if (! isValidDate(raisedOnDate)) {
      errorMessage.append("Invalid date \n");
    }
    User aUser = User.getWithEmail(email);
    MaintenanceTicket aTicket = assetPlus.addMaintenanceTicket(id, raisedOnDate, description, aUser);

    assetPlus.addMaintenanceTicket(aTicket);

    if (assetNumber != -1) {
      if (SpecificAsset.getWithAssetNumber(assetNumber) == null) {
        errorMessage.append("Invalid asset number \n");
      }
      SpecificAsset aAsset = SpecificAsset.getWithAssetNumber(assetNumber);
      aTicket.setAsset(aAsset);
    }
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
   * @return A string containing any error messages, if applicable.
   */
  public static String updateMaintenanceTicket(int id, Date newRaisedOnDate, String newDescription,
      String newEmail, int newAssetNumber) {
    StringBuilder errorMessage = new StringBuilder();
    MaintenanceTicket aTicket = assetPlus.getMaintenanceTicket(id);
    if (! isValidID(id)) {
      errorMessage.append("Invalid ID \n");
    }
    if (! MaintenanceTicket.hasWithId(id)) {
      errorMessage.append("Ticket ID does not exist \n");
    }
    if (! isValidDate(newRaisedOnDate)) {
      errorMessage.append("Invalid date \n");
    }
    if (aTicket != null) {
      aTicket.setRaisedOnDate(newRaisedOnDate);
      aTicket.setDescription(newDescription);
      User aUser = User.getWithEmail(newEmail);
      aTicket.setTicketRaiser(aUser);
      if (newAssetNumber != -1) {
        SpecificAsset aAsset = SpecificAsset.getWithAssetNumber(newAssetNumber);
        aTicket.setAsset(aAsset);
      }
    }
    else {
      errorMessage.append("Ticket ID not found \n");
    }
    return errorMessage.toString();
  }

  /**
   * Deletes a maintenance ticket from the system.
   *
   * @param id The ID of the ticket to delete.
   */
  public static void deleteMaintenanceTicket(int id) {
    assetPlus.getMaintenanceTicket(id).delete();
  }
}
