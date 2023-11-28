package ca.mcgill.ecse.assetplus.controller;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.model.TicketImage;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;
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
}
