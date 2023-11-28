/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import ca.mcgill.ecse.assetplus.model.Employee;
import javafx.scene.control.TableColumn;

// line 47 "../../../../../../AssetPlusPersistence.ump"
// line 16 "../../../../../../AssetPlus.ump"
public class TOUser
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String email;
  private String name;
  private String password;
  private String phoneNumber;
  private String userType;
  private List<Employee> employees;

  //User Associations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOUser(String aEmail, String aName, String aPassword, String aPhoneNumber, String aUserType)
  {
    name = aName;
    password = aPassword;
    phoneNumber = aPhoneNumber;
    email = aEmail;
    userType = aUserType;
  }

  //------------------------
  // INTERFACE
  //------------------------

  

  public TOUser(TableColumn<TOUser, String> userEmail, TableColumn<TOUser, Date> userName,
      TableColumn<TOUser, String> userPassword, TableColumn<TOUser, String> userPassword2,
      TableColumn<TOUser, Integer> userRole) {}

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  
  /* Code from template attribute_HasUnique */
  

  public String getName()
  {
    return name;
  }

  public String getType()
  {
    return userType;
  }

  public String getPassword()
  {
    return password;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public List<Employee> getEmployees()
  {
    List<Employee> newEmployees = Collections.unmodifiableList(employees);
    return newEmployees;
  }

  

  public String toString()
  {
    return super.toString() + "["+
            "type" + ":" + getType()+ "," +
            "email" + ":" + getEmail()+ "," +
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }
}

