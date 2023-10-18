package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;

public class AssetPlusFeatureSet2Controller {

  // Get the single instance of the AssetPlus class
  private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  // This class is not meant to be instantiated
  private AssetPlusFeatureSet2Controller(){}

  /**
   * Check if the name is valid
   * @param name the name of asset type
   * @author Jatin Patel
   */
  private static boolean checkName(String name) {
    if (name == null || name.trim().length() == 0) {
      return false;
    }
    return true;
  }

  /**
   * Check if the expected life span is valid
   * @param expectedLifeSpanInDays the expected life span of asset type
   * @author Jatin Patel
   */
  private static boolean checkExpectedLifeSpan(int expectedLifeSpanInDays) {
    if (expectedLifeSpanInDays <= 0) {
      return false;
    }
    return true;
  }


  /**
   * Add an asset type to the asset plus
   * @param name the name of asset type
   * @param expectedLifeSpanInDays the expected life span of asset type
   * @author Jatin Patel
   */
  public static String addAssetType(String name, int expectedLifeSpanInDays) {
    StringBuilder errorMessage = new StringBuilder();

    if (!checkName(name)) {
      errorMessage.append("The name must not be empty\n");
    }

    if (!checkExpectedLifeSpan(expectedLifeSpanInDays)) {
      errorMessage.append("The expected life span must be greater than 0 days\n");
    }

    if (errorMessage.length() > 0) {
      return errorMessage.toString();
    }

    try {
      assetPlus.addAssetType(name.toLowerCase(), expectedLifeSpanInDays);
    } catch (RuntimeException e) {
      String error = e.getMessage();
      if (error.startsWith("Cannot create due to duplicate name.")) {
        errorMessage.append("The asset type already exists\n");
      } else if (error.startsWith("Unable to create assetType due to assetPlus.")) {
        errorMessage.append("The asset plus does not exist\n");
      }
    }
    return errorMessage.toString();
  }

  /**
   * Update an asset type with new name and new expected life span
   * @param oldName the old name of asset type
   * @param newName  the new name of asset type
   * @param newExpectedLifeSpanInDays the new expected life span of asset type
   * @author Jatin Patel
   */
  public static String updateAssetType(String oldName, String newName, int newExpectedLifeSpanInDays) {
    StringBuilder errorMessage = new StringBuilder();
    if (!checkName(oldName) || !checkName(newName)) {
      errorMessage.append("The name must not be empty\n");
    }

    if (!checkExpectedLifeSpan(newExpectedLifeSpanInDays)) {
      errorMessage.append("The expected life span must be greater than 0 days\n");
    }

    if (errorMessage.length() > 0) {
      return errorMessage.toString();
    }

    if (AssetType.hasWithName(newName.toLowerCase())) {
        errorMessage.append("The asset type already exists\n");
    }

    if (!AssetType.hasWithName(oldName.toLowerCase())) {
        errorMessage.append("The asset type with name " + oldName + " does not exist\n");
    } else {
        AssetType assetType = AssetType.getWithName(oldName.toLowerCase());
        assetType.setName(newName.toLowerCase());
        assetType.setExpectedLifeSpan(newExpectedLifeSpanInDays);
    }

    return errorMessage.toString();
  }

  /**
   * Delete an asset type
   * @param name the name of asset type to be deleted
   * @author Jatin Patel
   */
  public static void deleteAssetType(String name) {
    try {
        AssetType assetTypeToDelete = AssetType.getWithName(name.toLowerCase());
        assetTypeToDelete.delete();
    } catch (RuntimeException e) {
    }
  }
}
