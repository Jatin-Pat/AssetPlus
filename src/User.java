/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;
import java.sql.Date;

// line 10 "model.ump"
// line 103 "model.ump"
public abstract class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByAccountName = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String accountName;
  private String password;
  private String name;
  private String phoneNumber;

  //User Associations
  private List<Ticket> tickets;
  private AssetPlus assetPlus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aAccountName, String aPassword, AssetPlus aAssetPlus)
  {
    password = aPassword;
    name = null;
    phoneNumber = null;
    if (!setAccountName(aAccountName))
    {
      throw new RuntimeException("Cannot create due to duplicate accountName. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    tickets = new ArrayList<Ticket>();
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create user due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAccountName(String aAccountName)
  {
    boolean wasSet = false;
    String anOldAccountName = getAccountName();
    if (anOldAccountName != null && anOldAccountName.equals(aAccountName)) {
      return true;
    }
    if (hasWithAccountName(aAccountName)) {
      return wasSet;
    }
    accountName = aAccountName;
    wasSet = true;
    if (anOldAccountName != null) {
      usersByAccountName.remove(anOldAccountName);
    }
    usersByAccountName.put(aAccountName, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getAccountName()
  {
    return accountName;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithAccountName(String aAccountName)
  {
    return usersByAccountName.get(aAccountName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithAccountName(String aAccountName)
  {
    return getWithAccountName(aAccountName) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public String getName()
  {
    return name;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  /* Code from template association_GetMany */
  public Ticket getTicket(int index)
  {
    Ticket aTicket = tickets.get(index);
    return aTicket;
  }

  public List<Ticket> getTickets()
  {
    List<Ticket> newTickets = Collections.unmodifiableList(tickets);
    return newTickets;
  }

  public int numberOfTickets()
  {
    int number = tickets.size();
    return number;
  }

  public boolean hasTickets()
  {
    boolean has = tickets.size() > 0;
    return has;
  }

  public int indexOfTicket(Ticket aTicket)
  {
    int index = tickets.indexOf(aTicket);
    return index;
  }
  /* Code from template association_GetOne */
  public AssetPlus getAssetPlus()
  {
    return assetPlus;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTickets()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Ticket addTicket(Date aDate, String aDescription, int aAssetID, Ticket.PriorityLevel aPriorityLevel, Ticket.TimeEstimate aTimeEstimate, Ticket.Status aStatus)
  {
    return new Ticket(aDate, aDescription, aAssetID, aPriorityLevel, aTimeEstimate, aStatus, this);
  }

  public boolean addTicket(Ticket aTicket)
  {
    boolean wasAdded = false;
    if (tickets.contains(aTicket)) { return false; }
    User existingUser = aTicket.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aTicket.setUser(this);
    }
    else
    {
      tickets.add(aTicket);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTicket(Ticket aTicket)
  {
    boolean wasRemoved = false;
    //Unable to remove aTicket, as it must always have a user
    if (!this.equals(aTicket.getUser()))
    {
      tickets.remove(aTicket);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTicketAt(Ticket aTicket, int index)
  {  
    boolean wasAdded = false;
    if(addTicket(aTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTickets()) { index = numberOfTickets() - 1; }
      tickets.remove(aTicket);
      tickets.add(index, aTicket);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTicketAt(Ticket aTicket, int index)
  {
    boolean wasAdded = false;
    if(tickets.contains(aTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTickets()) { index = numberOfTickets() - 1; }
      tickets.remove(aTicket);
      tickets.add(index, aTicket);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTicketAt(aTicket, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAssetPlus(AssetPlus aAssetPlus)
  {
    boolean wasSet = false;
    if (aAssetPlus == null)
    {
      return wasSet;
    }

    AssetPlus existingAssetPlus = assetPlus;
    assetPlus = aAssetPlus;
    if (existingAssetPlus != null && !existingAssetPlus.equals(aAssetPlus))
    {
      existingAssetPlus.removeUser(this);
    }
    assetPlus.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByAccountName.remove(getAccountName());
    for(int i=tickets.size(); i > 0; i--)
    {
      Ticket aTicket = tickets.get(i - 1);
      aTicket.delete();
    }
    AssetPlus placeholderAssetPlus = assetPlus;
    this.assetPlus = null;
    if(placeholderAssetPlus != null)
    {
      placeholderAssetPlus.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "accountName" + ":" + getAccountName()+ "," +
            "password" + ":" + getPassword()+ "," +
            "name" + ":" + getName()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "assetPlus = "+(getAssetPlus()!=null?Integer.toHexString(System.identityHashCode(getAssetPlus())):"null");
  }
}