package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;

public class ExtraFeaturesController {

    public static List<TOUser> getUsers(){
      List<TOUser> users = new ArrayList<TOUser>();
      
      users.addAll(getGuests());
      users.addAll(getEmployees());
      users.addAll(getManager());

      return users;

    }

    public static List<TOUser> getGuests(){
      List<TOUser> users = new ArrayList<TOUser>();
      
      List<Guest> guests = AssetPlusApplication.getAssetPlus().getGuests();
      for (Guest guest : guests) {
        String name = guest.getName();
        String email = guest.getEmail();
        String phoneNum = guest.getPhoneNumber();
        String password = guest.getPassword();
        String userType = "Guest";
        users.add(new TOUser(userType, name, phoneNum, email, password));
      }
      return users;
    }

    public static List<TOUser> getEmployees(){
      List<TOUser> users = new ArrayList<TOUser>();
      
      List<Employee> employees = AssetPlusApplication.getAssetPlus().getEmployees();
      for (Employee employee : employees) {
        String name = employee.getName();
        String email = employee.getEmail();
        String phoneNum = employee.getPhoneNumber();
        String password = employee.getPassword();
        String userType = "Employee";
        users.add(new TOUser(userType, name, phoneNum, email, password));
    
      }
      return users;
    }
    
    public static List<TOUser> getManager(){
      List<TOUser> users = new ArrayList<TOUser>();
      Manager manager = AssetPlusApplication.getAssetPlus().getManager();
      users.add(new TOUser("Manager", manager.getName(), manager.getPhoneNumber(), manager.getEmail(), manager.getPassword()));

      return users;
    }

    public static List<TOAsset> getAssetByNumber(int number){
      List<SpecificAsset> listOfAsset = new ArrayList<SpecificAsset>();
      if(SpecificAsset.hasWithAssetNumber(number)){
        listOfAsset.add(SpecificAsset.getWithAssetNumber(number));
      }

      return convertToTransferObject(listOfAsset);
    }
    
    public static List<TOAsset> getAssetsByFloor(int floorNumber){
      List<SpecificAsset> allAssets = AssetPlusApplication.getAssetPlus().getSpecificAssets();
      List<SpecificAsset> filteredAsset = new ArrayList<SpecificAsset>();
      for (SpecificAsset asset : allAssets) {
        if(asset.getFloorNumber() == floorNumber){
          filteredAsset.add(asset);
        }
      }
      return convertToTransferObject(filteredAsset);
    }

    public static List<TOAsset> getAssetsByType(String assetType){
      List<SpecificAsset> allAssets = AssetPlusApplication.getAssetPlus().getSpecificAssets();
      List<SpecificAsset> filteredAsset = new ArrayList<SpecificAsset>();
      
      if(AssetType.hasWithName(assetType)){
        for (SpecificAsset asset : allAssets) {
          if(asset.getAssetType() == AssetType.getWithName(assetType)){
            filteredAsset.add(asset);
          }
        }
      }

      return convertToTransferObject(filteredAsset);
    }

    public static List<TOAsset> getAllAssets(){
      return convertToTransferObject(AssetPlusApplication.getAssetPlus().getSpecificAssets());
    }

    public static List<TOAsset> convertToTransferObject(List<SpecificAsset> listToConvert){
      List<TOAsset> convertedList = new ArrayList<TOAsset>();
      for (SpecificAsset specificAsset : listToConvert) {
        int floorNumber = specificAsset.getFloorNumber();
        int roomNumber = specificAsset.getRoomNumber();
        int id = specificAsset.getAssetNumber();
        Date purchaseDate = specificAsset.getPurchaseDate();
        String type = specificAsset.getAssetType().toString();

        convertedList.add(new TOAsset(id, floorNumber, roomNumber, purchaseDate,type));
      }

      return convertedList;

    }
  }
