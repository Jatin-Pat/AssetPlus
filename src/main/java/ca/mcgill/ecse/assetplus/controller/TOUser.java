/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.assetplus.controller;
import java.util.*;
import java.sql.Date;

// line 47 "../../../../../../AssetPlusPersistence.ump"
// line 16 "../../../../../../AssetPlus.ump"
public abstract class TOUser
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByEmail = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String email;
  private String name;
  private String password;
  private String phoneNumber;

  //User Associations
  private List<TOMaintenanceTicket> raisedTickets;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TOUser(String aEmail, String aName, String aPassword, String aPhoneNumber)
  {
    name = aName;
    password = aPassword;
    phoneNumber = aPhoneNumber;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    raisedTickets = new ArrayList<TOMaintenanceTicket>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static TOUser getWithEmail(String aEmail)
  {
    return usersByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getName()
  {
    return name;
  }

  public String getPassword()
  {
    return password;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }
}