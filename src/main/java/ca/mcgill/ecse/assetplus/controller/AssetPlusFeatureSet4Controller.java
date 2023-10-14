package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
public class AssetPlusFeatureSet4Controller {

  private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  private static boolean isValidEmail(String email) {
    String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.find();
  }
  private static boolean isValidID(int id) {
    return id >= 0;
  }
  private static boolean isExistingID(int id) {
    List<MaintenanceTicket> ticketList = assetPlus.getMaintenanceTickets();
    for (int i = 0; i < ticketList.size(); i++) {
      if (id == ticketList.get(i).getId()) {
        return true;
      }
    }
    return false;
  }
  private static boolean isValidDate(Date raisedOnDate) {
    Date todayDate = Date.valueOf(LocalDate.now());
    int comparisonResult = raisedOnDate.compareTo(todayDate);
    return comparisonResult >= 0;
  }
  // assetNumber -1 means that no asset is specified
  public static String addMaintenanceTicket(int id, Date raisedOnDate, String description,
      String email, int assetNumber) {
    if (! isValidEmail(email)) {
      throw new IllegalArgumentException("Invalid email");
    }
    if (! isValidID(id)) {
      throw new IllegalArgumentException("Invalid ID");
    }
    if (! isValidDate(raisedOnDate)) {
      throw new IllegalArgumentException("Invalid date");
    }
    User aUser = User.getWithEmail(email);
    MaintenanceTicket aTicket = assetPlus.addMaintenanceTicket(id, raisedOnDate, description, aUser);

    assetPlus.addMaintenanceTicket(aTicket);

    if (assetNumber != -1) {
      if (SpecificAsset.getWithAssetNumber(assetNumber) == null) {
        throw new IllegalArgumentException("Invalid asset number");
      }
      SpecificAsset aAsset = SpecificAsset.getWithAssetNumber(assetNumber);
      aTicket.setAsset(aAsset);
    }

    return "Ticket " + id + " added successfully";
  }

  // newAssetNumber -1 means that no asset is specified
  public static String updateMaintenanceTicket(int id, Date newRaisedOnDate, String newDescription,
      String newEmail, int newAssetNumber) {
    MaintenanceTicket aTicket = assetPlus.getMaintenanceTicket(id);
    if (! isValidID(id)) {
      throw new IllegalArgumentException("Invalid ID");
    }
    if (! isExistingID(id)) {
      throw new IllegalArgumentException("Ticket ID does not exist");
    }
    if (! isValidDate(newRaisedOnDate)) {
      throw new IllegalArgumentException("Invalid date");
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
      throw new IllegalArgumentException("Ticket ID not found");
    }
    return "Ticket " + id + " modified successfully";
  }

  public static void deleteMaintenanceTicket(int id) {
    // Remove this exception when you implement this method
    assetPlus.getMaintenanceTicket(id).delete();
  }
}
