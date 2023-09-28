/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;
import java.util.*;

// line 61 "model.ump"
// line 149 "model.ump"
public class Ticket
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PriorityLevel { Urgent, Normal, Low }
  public enum TimeEstimate { Day, From1to3Days, From3to7Days, From1to3Weeks, From3Weeks }
  public enum Status { InProgress, Resolved, Approved, Closed }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ticket Attributes
  private Date date;
  private String description;
  private int assetID;
  private String assignedTo;
  private boolean approvalRequired;
  private PriorityLevel priorityLevel;
  private TimeEstimate timeEstimate;
  private Status status;

  //Autounique Attributes
  private int id;

  //Ticket Associations
  private List<Image> images;
  private List<Note> notes;
  private User user;
  private Asset assets;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ticket(Date aDate, String aDescription, int aAssetID, PriorityLevel aPriorityLevel, TimeEstimate aTimeEstimate, Status aStatus, User aUser)
  {
    date = aDate;
    description = aDescription;
    assetID = aAssetID;
    assignedTo = null;
    approvalRequired = false;
    priorityLevel = aPriorityLevel;
    timeEstimate = aTimeEstimate;
    status = aStatus;
    id = nextId++;
    images = new ArrayList<Image>();
    notes = new ArrayList<Note>();
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create ticket due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setAssetID(int aAssetID)
  {
    boolean wasSet = false;
    assetID = aAssetID;
    wasSet = true;
    return wasSet;
  }

  public boolean setAssignedTo(String aAssignedTo)
  {
    boolean wasSet = false;
    assignedTo = aAssignedTo;
    wasSet = true;
    return wasSet;
  }

  public boolean setApprovalRequired(boolean aApprovalRequired)
  {
    boolean wasSet = false;
    approvalRequired = aApprovalRequired;
    wasSet = true;
    return wasSet;
  }

  public boolean setPriorityLevel(PriorityLevel aPriorityLevel)
  {
    boolean wasSet = false;
    priorityLevel = aPriorityLevel;
    wasSet = true;
    return wasSet;
  }

  public boolean setTimeEstimate(TimeEstimate aTimeEstimate)
  {
    boolean wasSet = false;
    timeEstimate = aTimeEstimate;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(Status aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public String getDescription()
  {
    return description;
  }

  public int getAssetID()
  {
    return assetID;
  }

  public String getAssignedTo()
  {
    return assignedTo;
  }

  public boolean getApprovalRequired()
  {
    return approvalRequired;
  }

  public PriorityLevel getPriorityLevel()
  {
    return priorityLevel;
  }

  public TimeEstimate getTimeEstimate()
  {
    return timeEstimate;
  }

  public Status getStatus()
  {
    return status;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isApprovalRequired()
  {
    return approvalRequired;
  }
  /* Code from template association_GetMany */
  public Image getImage(int index)
  {
    Image aImage = images.get(index);
    return aImage;
  }

  public List<Image> getImages()
  {
    List<Image> newImages = Collections.unmodifiableList(images);
    return newImages;
  }

  public int numberOfImages()
  {
    int number = images.size();
    return number;
  }

  public boolean hasImages()
  {
    boolean has = images.size() > 0;
    return has;
  }

  public int indexOfImage(Image aImage)
  {
    int index = images.indexOf(aImage);
    return index;
  }
  /* Code from template association_GetMany */
  public Note getNote(int index)
  {
    Note aNote = notes.get(index);
    return aNote;
  }

  public List<Note> getNotes()
  {
    List<Note> newNotes = Collections.unmodifiableList(notes);
    return newNotes;
  }

  public int numberOfNotes()
  {
    int number = notes.size();
    return number;
  }

  public boolean hasNotes()
  {
    boolean has = notes.size() > 0;
    return has;
  }

  public int indexOfNote(Note aNote)
  {
    int index = notes.indexOf(aNote);
    return index;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_GetOne */
  public Asset getAssets()
  {
    return assets;
  }

  public boolean hasAssets()
  {
    boolean has = assets != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfImages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Image addImage(Date aDate, String aUrl)
  {
    return new Image(aDate, aUrl, this);
  }

  public boolean addImage(Image aImage)
  {
    boolean wasAdded = false;
    if (images.contains(aImage)) { return false; }
    Ticket existingTicket = aImage.getTicket();
    boolean isNewTicket = existingTicket != null && !this.equals(existingTicket);
    if (isNewTicket)
    {
      aImage.setTicket(this);
    }
    else
    {
      images.add(aImage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeImage(Image aImage)
  {
    boolean wasRemoved = false;
    //Unable to remove aImage, as it must always have a ticket
    if (!this.equals(aImage.getTicket()))
    {
      images.remove(aImage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addImageAt(Image aImage, int index)
  {  
    boolean wasAdded = false;
    if(addImage(aImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfImages()) { index = numberOfImages() - 1; }
      images.remove(aImage);
      images.add(index, aImage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveImageAt(Image aImage, int index)
  {
    boolean wasAdded = false;
    if(images.contains(aImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfImages()) { index = numberOfImages() - 1; }
      images.remove(aImage);
      images.add(index, aImage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addImageAt(aImage, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfNotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Note addNote(Date aDate, String aContent)
  {
    return new Note(aDate, aContent, this);
  }

  public boolean addNote(Note aNote)
  {
    boolean wasAdded = false;
    if (notes.contains(aNote)) { return false; }
    Ticket existingTicket = aNote.getTicket();
    boolean isNewTicket = existingTicket != null && !this.equals(existingTicket);
    if (isNewTicket)
    {
      aNote.setTicket(this);
    }
    else
    {
      notes.add(aNote);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeNote(Note aNote)
  {
    boolean wasRemoved = false;
    //Unable to remove aNote, as it must always have a ticket
    if (!this.equals(aNote.getTicket()))
    {
      notes.remove(aNote);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addNoteAt(Note aNote, int index)
  {  
    boolean wasAdded = false;
    if(addNote(aNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotes()) { index = numberOfNotes() - 1; }
      notes.remove(aNote);
      notes.add(index, aNote);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveNoteAt(Note aNote, int index)
  {
    boolean wasAdded = false;
    if(notes.contains(aNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotes()) { index = numberOfNotes() - 1; }
      notes.remove(aNote);
      notes.add(index, aNote);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addNoteAt(aNote, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }

    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      existingUser.removeTicket(this);
    }
    user.addTicket(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setAssets(Asset aAssets)
  {
    boolean wasSet = false;
    Asset existingAssets = assets;
    assets = aAssets;
    if (existingAssets != null && !existingAssets.equals(aAssets))
    {
      existingAssets.removeTicket(this);
    }
    if (aAssets != null)
    {
      aAssets.addTicket(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (images.size() > 0)
    {
      Image aImage = images.get(images.size() - 1);
      aImage.delete();
      images.remove(aImage);
    }
    
    while (notes.size() > 0)
    {
      Note aNote = notes.get(notes.size() - 1);
      aNote.delete();
      notes.remove(aNote);
    }
    
    User placeholderUser = user;
    this.user = null;
    if(placeholderUser != null)
    {
      placeholderUser.removeTicket(this);
    }
    if (assets != null)
    {
      Asset placeholderAssets = assets;
      this.assets = null;
      placeholderAssets.removeTicket(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "description" + ":" + getDescription()+ "," +
            "assetID" + ":" + getAssetID()+ "," +
            "assignedTo" + ":" + getAssignedTo()+ "," +
            "approvalRequired" + ":" + getApprovalRequired()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "priorityLevel" + "=" + (getPriorityLevel() != null ? !getPriorityLevel().equals(this)  ? getPriorityLevel().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "timeEstimate" + "=" + (getTimeEstimate() != null ? !getTimeEstimate().equals(this)  ? getTimeEstimate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assets = "+(getAssets()!=null?Integer.toHexString(System.identityHashCode(getAssets())):"null");
  }
}