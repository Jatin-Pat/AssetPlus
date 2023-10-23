package ca.mcgill.ecse.assetplus.controller;

public class AssetPlusFeatureSet1Controller {

/**
  * The controller for the Update Manager Password & Add and Update Employee/Guest
  * @since 1.0
  * @author Marc-Antoine Nadeau - Student ID: 261114549
  */
public class AssetPlusFeatureSet1Controller { 
  
  //get the single instance of the AssetPlus class
  private static AssetPlus assetplus = AssetPlusApplication.getAssetPlus();
  /**
   * Updates the password of the Manager and perfome Input Validation on the password.
   * @param password a string with at least one 3 character long containing at least one of #!$, both uppercase and lowercase letters. 
   * @return An empty string when the password has been succesfully updated, or a String with all the error messages combined in case of failure.
   * @since 1.0 
   */
  public static String updateManager(String password) {  
    //Input Validation
    var error = "";
    if (password.length() <= 3){
      error = "Password must be at least four characters long";
    } if( ! (password.contains("!") || password.contains("#") || password.contains("$"))){
       error += "Password must contain one character out of !#$";
    } if(password.isBlank()){
      error += "Password cannot be empty";
    }if( ! (password.matches(".*[a-z].*"))){
      error += "Password must contain one lower-case character";
    }if( ! (password.matches(".*[A-Z].*"))){
      error += "Password must contain one upper-case character";
    } if(!assetplus.hasManager()){
      error += "A Manager should exist";
    } 
    if (!error.isEmpty()) {
      return error.trim();
    }
    //Call Model
    Manager manager = assetplus.getManager();
    manager.setPassword(password);
    return error; 
  }

  public static String addEmployeeOrGuest(String email, String password, String name, String phoneNumber,
        boolean isEmployee) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

  public static String updateEmployeeOrGuest(String email, String newPassword, String newName, String newPhoneNumber) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

}