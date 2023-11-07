/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/

package ca.mcgill.ecse.assetplus.model;
import java.util.*;
import java.sql.Date;

// line 43 "../../../../../../AssetPlus.ump"
// line 1 "../../../../../../AssetPlusStates.ump"
public class MaintenanceTicket
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TimeEstimate { LessThanADay, OneToThreeDays, ThreeToSevenDays, OneToThreeWeeks, ThreeOrMoreWeeks }
  public enum PriorityLevel { Urgent, Normal, Low }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, MaintenanceTicket> maintenanceticketsById = new HashMap<Integer, MaintenanceTicket>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MaintenanceTicket Attributes
  private int id;
  private Date raisedOnDate;
  private String description;
  private TimeEstimate timeToResolve;
  private PriorityLevel priority;

  //MaintenanceTicket State Machines
  public enum TicketStatus { Open, Assigned, InProgress, Resolved, Closed }
  private TicketStatus ticketStatus;

  //MaintenanceTicket Associations
  private List<MaintenanceNote> ticketNotes;
  private List<TicketImage> ticketImages;
  private AssetPlus assetPlus;
  private User ticketRaiser;
  private HotelStaff ticketFixer;
  private SpecificAsset asset;
  private Manager fixApprover;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MaintenanceTicket(int aId, Date aRaisedOnDate, String aDescription, AssetPlus aAssetPlus, User aTicketRaiser)
  {
    raisedOnDate = aRaisedOnDate;
    description = aDescription;
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    ticketNotes = new ArrayList<MaintenanceNote>();
    ticketImages = new ArrayList<TicketImage>();
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create maintenanceTicket due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddTicketRaiser = setTicketRaiser(aTicketRaiser);
    if (!didAddTicketRaiser)
    {
      throw new RuntimeException("Unable to create raisedTicket due to ticketRaiser. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setTicketStatus(TicketStatus.Open);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    Integer anOldId = getId();
    if (anOldId != null && anOldId.equals(aId)) {
      return true;
    }
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      maintenanceticketsById.remove(anOldId);
    }
    maintenanceticketsById.put(aId, this);
    return wasSet;
  }

  public boolean setRaisedOnDate(Date aRaisedOnDate)
  {
    boolean wasSet = false;
    raisedOnDate = aRaisedOnDate;
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

  public boolean setTimeToResolve(TimeEstimate aTimeToResolve)
  {
    boolean wasSet = false;
    timeToResolve = aTimeToResolve;
    wasSet = true;
    return wasSet;
  }

  public boolean setPriority(PriorityLevel aPriority)
  {
    boolean wasSet = false;
    priority = aPriority;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template attribute_GetUnique */
  public static MaintenanceTicket getWithId(int aId)
  {
    return maintenanceticketsById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }

  public Date getRaisedOnDate()
  {
    return raisedOnDate;
  }

  public String getDescription()
  {
    return description;
  }

  public TimeEstimate getTimeToResolve()
  {
    return timeToResolve;
  }

  public PriorityLevel getPriority()
  {
    return priority;
  }

  public String getTicketStatusFullName()
  {
    String answer = ticketStatus.toString();
    return answer;
  }

  public TicketStatus getTicketStatus()
  {
    return ticketStatus;
  }

  public boolean assignStaff(Employee email,PriorityLevel level,TimeEstimate estimate,boolean approval)
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Open:
        // line 5 "../../../../../../AssetPlusStates.ump"
        doAssign(email, level, estimate, approval);
        setTicketStatus(TicketStatus.Assigned);
        wasEventProcessed = true;
        break;
      case Assigned:
        // line 20 "../../../../../../AssetPlusStates.ump"
        rejectAssign("assigned");
        setTicketStatus(TicketStatus.Assigned);
        wasEventProcessed = true;
        break;
      case InProgress:
        // line 33 "../../../../../../AssetPlusStates.ump"
        rejectAssign("in progress");
        setTicketStatus(TicketStatus.InProgress);
        wasEventProcessed = true;
        break;
      case Resolved:
        // line 48 "../../../../../../AssetPlusStates.ump"
        rejectAssign("resolved");
        setTicketStatus(TicketStatus.Resolved);
        wasEventProcessed = true;
        break;
      case Closed:
        // line 53 "../../../../../../AssetPlusStates.ump"
        rejectAssign("closed");
        setTicketStatus(TicketStatus.Closed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean approve(MaintenanceTicket ticket)
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Open:
        // line 9 "../../../../../../AssetPlusStates.ump"
        rejectApprove(ticket);
        setTicketStatus(TicketStatus.Open);
        wasEventProcessed = true;
        break;
      case Assigned:
        // line 22 "../../../../../../AssetPlusStates.ump"
        rejectApprove(ticket);
        setTicketStatus(TicketStatus.Assigned);
        wasEventProcessed = true;
        break;
      case InProgress:
        // line 35 "../../../../../../AssetPlusStates.ump"
        rejectApprove("in progress");
        setTicketStatus(TicketStatus.InProgress);
        wasEventProcessed = true;
        break;
      case Resolved:
        // line 42 "../../../../../../AssetPlusStates.ump"
        doApprove(ticket);
        setTicketStatus(TicketStatus.Closed);
        wasEventProcessed = true;
        break;
      case Closed:
        // line 56 "../../../../../../AssetPlusStates.ump"
        rejectApprove("closed");
        setTicketStatus(TicketStatus.Closed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean disapprove(MaintenanceTicket ticket)
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Open:
        // line 11 "../../../../../../AssetPlusStates.ump"
        rejectDisapprove("open");
        setTicketStatus(TicketStatus.Open);
        wasEventProcessed = true;
        break;
      case Assigned:
        // line 24 "../../../../../../AssetPlusStates.ump"
        rejectDisapprove("assigned");
        setTicketStatus(TicketStatus.Assigned);
        wasEventProcessed = true;
        break;
      case InProgress:
        // line 37 "../../../../../../AssetPlusStates.ump"
        rejectDisapprove("in progress");
        setTicketStatus(TicketStatus.InProgress);
        wasEventProcessed = true;
        break;
      case Resolved:
        // line 44 "../../../../../../AssetPlusStates.ump"
        doDisapprove(ticket);
        setTicketStatus(TicketStatus.InProgress);
        wasEventProcessed = true;
        break;
      case Closed:
        // line 57 "../../../../../../AssetPlusStates.ump"
        rejectDisapprove("closed");
        setTicketStatus(TicketStatus.Closed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean completeWork()
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Open:
        // line 13 "../../../../../../AssetPlusStates.ump"
        rejectComplete("open")
        setTicketStatus(TicketStatus.Open);
        wasEventProcessed = true;
        break;
      case Assigned:
        // line 21 "../../../../../../AssetPlusStates.ump"
        rejectComplete("assigned")
        setTicketStatus(TicketStatus.Assigned);
        wasEventProcessed = true;
        break;
      case InProgress:
        if (getApprovalRequired())
        {
          setTicketStatus(TicketStatus.Resolved);
          wasEventProcessed = true;
          break;
        }
        if (!(getApprovalRequired()))
        {
          setTicketStatus(TicketStatus.Closed);
          wasEventProcessed = true;
          break;
        }
        break;
      case Resolved:
        // line 46 "../../../../../../AssetPlusStates.ump"
        rejectComplete("resolved")
        setTicketStatus(TicketStatus.Resolved);
        wasEventProcessed = true;
        break;
      case Closed:
        // line 52 "../../../../../../AssetPlusStates.ump"
        rejectComplete("closed")
        setTicketStatus(TicketStatus.Closed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean beginWork()
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Open:
        // line 14 "../../../../../../AssetPlusStates.ump"
        rejectBegin("open");
        setTicketStatus(TicketStatus.Open);
        wasEventProcessed = true;
        break;
      case Assigned:
        setTicketStatus(TicketStatus.InProgress);
        wasEventProcessed = true;
        break;
      case InProgress:
        // line 32 "../../../../../../AssetPlusStates.ump"
        rejectBegin("in progress");
        setTicketStatus(TicketStatus.InProgress);
        wasEventProcessed = true;
        break;
      case Resolved:
        // line 47 "../../../../../../AssetPlusStates.ump"
        rejectBegin("resolved");
        setTicketStatus(TicketStatus.Resolved);
        wasEventProcessed = true;
        break;
      case Closed:
        // line 58 "../../../../../../AssetPlusStates.ump"
        rejectBegin("closed");
        setTicketStatus(TicketStatus.Closed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setTicketStatus(TicketStatus aTicketStatus)
  {
    ticketStatus = aTicketStatus;
  }
  /* Code from template association_GetMany */
  public MaintenanceNote getTicketNote(int index)
  {
    MaintenanceNote aTicketNote = ticketNotes.get(index);
    return aTicketNote;
  }

  public List<MaintenanceNote> getTicketNotes()
  {
    List<MaintenanceNote> newTicketNotes = Collections.unmodifiableList(ticketNotes);
    return newTicketNotes;
  }

  public int numberOfTicketNotes()
  {
    int number = ticketNotes.size();
    return number;
  }

  public boolean hasTicketNotes()
  {
    boolean has = ticketNotes.size() > 0;
    return has;
  }

  public int indexOfTicketNote(MaintenanceNote aTicketNote)
  {
    int index = ticketNotes.indexOf(aTicketNote);
    return index;
  }
  /* Code from template association_GetMany */
  public TicketImage getTicketImage(int index)
  {
    TicketImage aTicketImage = ticketImages.get(index);
    return aTicketImage;
  }

  public List<TicketImage> getTicketImages()
  {
    List<TicketImage> newTicketImages = Collections.unmodifiableList(ticketImages);
    return newTicketImages;
  }

  public int numberOfTicketImages()
  {
    int number = ticketImages.size();
    return number;
  }

  public boolean hasTicketImages()
  {
    boolean has = ticketImages.size() > 0;
    return has;
  }

  public int indexOfTicketImage(TicketImage aTicketImage)
  {
    int index = ticketImages.indexOf(aTicketImage);
    return index;
  }
  /* Code from template association_GetOne */
  public AssetPlus getAssetPlus()
  {
    return assetPlus;
  }
  /* Code from template association_GetOne */
  public User getTicketRaiser()
  {
    return ticketRaiser;
  }
  /* Code from template association_GetOne */
  public HotelStaff getTicketFixer()
  {
    return ticketFixer;
  }

  public boolean hasTicketFixer()
  {
    boolean has = ticketFixer != null;
    return has;
  }
  /* Code from template association_GetOne */
  public SpecificAsset getAsset()
  {
    return asset;
  }

  public boolean hasAsset()
  {
    boolean has = asset != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Manager getFixApprover()
  {
    return fixApprover;
  }

  public boolean hasFixApprover()
  {
    boolean has = fixApprover != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTicketNotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceNote addTicketNote(Date aDate, String aDescription, HotelStaff aNoteTaker)
  {
    return new MaintenanceNote(aDate, aDescription, this, aNoteTaker);
  }

  public boolean addTicketNote(MaintenanceNote aTicketNote)
  {
    boolean wasAdded = false;
    if (ticketNotes.contains(aTicketNote)) { return false; }
    MaintenanceTicket existingTicket = aTicketNote.getTicket();
    boolean isNewTicket = existingTicket != null && !this.equals(existingTicket);
    if (isNewTicket)
    {
      aTicketNote.setTicket(this);
    }
    else
    {
      ticketNotes.add(aTicketNote);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTicketNote(MaintenanceNote aTicketNote)
  {
    boolean wasRemoved = false;
    //Unable to remove aTicketNote, as it must always have a ticket
    if (!this.equals(aTicketNote.getTicket()))
    {
      ticketNotes.remove(aTicketNote);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTicketNoteAt(MaintenanceNote aTicketNote, int index)
  {  
    boolean wasAdded = false;
    if(addTicketNote(aTicketNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTicketNotes()) { index = numberOfTicketNotes() - 1; }
      ticketNotes.remove(aTicketNote);
      ticketNotes.add(index, aTicketNote);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTicketNoteAt(MaintenanceNote aTicketNote, int index)
  {
    boolean wasAdded = false;
    if(ticketNotes.contains(aTicketNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTicketNotes()) { index = numberOfTicketNotes() - 1; }
      ticketNotes.remove(aTicketNote);
      ticketNotes.add(index, aTicketNote);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTicketNoteAt(aTicketNote, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTicketImages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TicketImage addTicketImage(String aImageURL)
  {
    return new TicketImage(aImageURL, this);
  }

  public boolean addTicketImage(TicketImage aTicketImage)
  {
    boolean wasAdded = false;
    if (ticketImages.contains(aTicketImage)) { return false; }
    MaintenanceTicket existingTicket = aTicketImage.getTicket();
    boolean isNewTicket = existingTicket != null && !this.equals(existingTicket);
    if (isNewTicket)
    {
      aTicketImage.setTicket(this);
    }
    else
    {
      ticketImages.add(aTicketImage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTicketImage(TicketImage aTicketImage)
  {
    boolean wasRemoved = false;
    //Unable to remove aTicketImage, as it must always have a ticket
    if (!this.equals(aTicketImage.getTicket()))
    {
      ticketImages.remove(aTicketImage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTicketImageAt(TicketImage aTicketImage, int index)
  {  
    boolean wasAdded = false;
    if(addTicketImage(aTicketImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTicketImages()) { index = numberOfTicketImages() - 1; }
      ticketImages.remove(aTicketImage);
      ticketImages.add(index, aTicketImage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTicketImageAt(TicketImage aTicketImage, int index)
  {
    boolean wasAdded = false;
    if(ticketImages.contains(aTicketImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTicketImages()) { index = numberOfTicketImages() - 1; }
      ticketImages.remove(aTicketImage);
      ticketImages.add(index, aTicketImage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTicketImageAt(aTicketImage, index);
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
      existingAssetPlus.removeMaintenanceTicket(this);
    }
    assetPlus.addMaintenanceTicket(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTicketRaiser(User aTicketRaiser)
  {
    boolean wasSet = false;
    if (aTicketRaiser == null)
    {
      return wasSet;
    }

    User existingTicketRaiser = ticketRaiser;
    ticketRaiser = aTicketRaiser;
    if (existingTicketRaiser != null && !existingTicketRaiser.equals(aTicketRaiser))
    {
      existingTicketRaiser.removeRaisedTicket(this);
    }
    ticketRaiser.addRaisedTicket(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setTicketFixer(HotelStaff aTicketFixer)
  {
    boolean wasSet = false;
    HotelStaff existingTicketFixer = ticketFixer;
    ticketFixer = aTicketFixer;
    if (existingTicketFixer != null && !existingTicketFixer.equals(aTicketFixer))
    {
      existingTicketFixer.removeMaintenanceTask(this);
    }
    if (aTicketFixer != null)
    {
      aTicketFixer.addMaintenanceTask(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setAsset(SpecificAsset aAsset)
  {
    boolean wasSet = false;
    SpecificAsset existingAsset = asset;
    asset = aAsset;
    if (existingAsset != null && !existingAsset.equals(aAsset))
    {
      existingAsset.removeMaintenanceTicket(this);
    }
    if (aAsset != null)
    {
      aAsset.addMaintenanceTicket(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setFixApprover(Manager aFixApprover)
  {
    boolean wasSet = false;
    Manager existingFixApprover = fixApprover;
    fixApprover = aFixApprover;
    if (existingFixApprover != null && !existingFixApprover.equals(aFixApprover))
    {
      existingFixApprover.removeTicketsForApproval(this);
    }
    if (aFixApprover != null)
    {
      aFixApprover.addTicketsForApproval(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    maintenanceticketsById.remove(getId());
    while (ticketNotes.size() > 0)
    {
      MaintenanceNote aTicketNote = ticketNotes.get(ticketNotes.size() - 1);
      aTicketNote.delete();
      ticketNotes.remove(aTicketNote);
    }
    
    while (ticketImages.size() > 0)
    {
      TicketImage aTicketImage = ticketImages.get(ticketImages.size() - 1);
      aTicketImage.delete();
      ticketImages.remove(aTicketImage);
    }
    
    AssetPlus placeholderAssetPlus = assetPlus;
    this.assetPlus = null;
    if(placeholderAssetPlus != null)
    {
      placeholderAssetPlus.removeMaintenanceTicket(this);
    }
    User placeholderTicketRaiser = ticketRaiser;
    this.ticketRaiser = null;
    if(placeholderTicketRaiser != null)
    {
      placeholderTicketRaiser.removeRaisedTicket(this);
    }
    if (ticketFixer != null)
    {
      HotelStaff placeholderTicketFixer = ticketFixer;
      this.ticketFixer = null;
      placeholderTicketFixer.removeMaintenanceTask(this);
    }
    if (asset != null)
    {
      SpecificAsset placeholderAsset = asset;
      this.asset = null;
      placeholderAsset.removeMaintenanceTicket(this);
    }
    if (fixApprover != null)
    {
      Manager placeholderFixApprover = fixApprover;
      this.fixApprover = null;
      placeholderFixApprover.removeTicketsForApproval(this);
    }
  }

  // line 64 "../../../../../../AssetPlusStates.ump"
   private void doAssign(Employee email, PriorityLevel level, TimeEstimate estimate, approvalRequired approval){
    //Implement this
      //Have this call the assign controller method
  }


  /**
   * Should it be int or String instead of MaintenanceTicket
   */
  // line 70 "../../../../../../AssetPlusStates.ump"
   private void doApprove(MaintenanceTicket id){
    //have this call the approve controller method



      MaintenanceTickett ticket = getMaintenanceTicket(parse.Integer(id));
      if (ticket == null){
        throw new RuntimeException("Maintenance ticket does not exist.");
      }
      //if (ticket.getTicketStatus() == "Resolved"){ //Don't think that's necessary
      //  ticket.approve();
      //}
  }


  /**
   * Should it be int or String instead of MaintenanceTicket
   */
  // line 85 "../../../../../../AssetPlusStates.ump"
   private void doDisapprove(MaintenanceTicket id, Date date, Description description, Manager email){
    //have this call the controller method
      //dont do it in here
      
      MaintenanceTicket ticket = getMaintenanceTicket(parse.Integer(id));
      //Are they strings?
      if(ticket == null){
        throw new RuntimeException("Maintenance ticket does not exist.");
      }

      if (ticket.getTicketStatus() == Resolved){ //Don't think that's necessary
        if(email = "manager@ap.com"){ //Don't think that's necessary
          Manager manager = getAssetPlus.getManager();
          ticketNote = addTicketNote(date, description, manager);
      }
      }
  }

  // line 103 "../../../../../../AssetPlusStates.ump"
   private void doBeginWork(){
    //Implement this
  }


  /**
   * I wrote a different version of these below
   * private void rejectApprove(MaintenanceTicket id){
   * //i wrote a different version of this below -behrad
   * MaintenanceTicket ticket = getMaintenanceTicket(parse.Integer(id));
   * if (ticket == null){ // necessary ??
   * throw new RuntimeException("Maintenance ticket does not exist");
   * }
   * 
   * String ticketStatus =  ticket.getTicketStatus();
   * //are they string? 
   * if (ticketStatus == Closed){
   * throw new RuntimeException("The maintenance ticket is already closed.");
   * }
   * throw new RuntimeException("Cannot approve a maintenance ticket which is "+ ticketStatus.toLowerCase() +".");
   * }
   * 
   * private void rejectDisapprove(MaintenanceTicket id){
   * //I wrote a different version of this below -behrad
   * 
   * MaintenanceTicket ticket = getMaintenanceTicket(parse.Integer(id));
   * if (ticket == null){ // necessary ??
   * throw new RuntimeException("Maintenance ticket does not exist");
   * }
   * String ticketStatus =  ticket.getTicketStatus();
   * throw new RuntimeException("Cannot disapprove a maintenance ticket which is "+ ticketStatus.toLowerCase() +".");
   * }
   * ALL REJECT METHODS DUE TO WRONG STATE
   * Behrad
   */
  // line 140 "../../../../../../AssetPlusStates.ump"
   private void rejectBegin(String status){
    if(status.contains("progress")){
        throw new RuntimeException("The maintenance ticket is already in progress.");
      }
      throw new RuntimeException("Cannot start a maintenance ticket which is "+status+".");
  }


  /**
   * Behrad
   */
  // line 147 "../../../../../../AssetPlusStates.ump"
   private void rejectComplete(String status){
    if(status.contains("complete") || status.contains("resolved")){
        throw new RuntimeException("The maintenance ticket is already "+status);
      }
      throw new RuntimeException("Cannot complete a maintenance ticket which is "+status+".");
  }


  /**
   * Behrad
   */
  // line 154 "../../../../../../AssetPlusStates.ump"
   private void rejectAssign(String status){
    //Case where ticket cannot be assigned due to its status
      if (status.contains("assigned")){
        throw new RuntimeException("The maintenance ticket is already assigned.");
     }
      throw new RuntimeException("Cannot assign a ticket which is "+status+".");
  }


  /**
   * HAVE TO DISCUSS THE FOLLOWING TWO WITH TEAM
   * Behrad & Marc-Antoine
   */
  // line 164 "../../../../../../AssetPlusStates.ump"
   private void rejectApprove(String status){
    if (status.contains("closed")){
        throw new RuntimeException("The maintenance ticket is already closed.");
      }
      throw new RuntimeException("Cannot approve a maintenance ticket which is "+status+".");
  }


  /**
   * Behrad & Marc-Antoine
   */
  // line 171 "../../../../../../AssetPlusStates.ump"
   private void rejectDisapprove(String status){
    throw new RuntimeException("Cannot approve a maintenance ticket which is "+status+".")
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "description" + ":" + getDescription()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "raisedOnDate" + "=" + (getRaisedOnDate() != null ? !getRaisedOnDate().equals(this)  ? getRaisedOnDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "timeToResolve" + "=" + (getTimeToResolve() != null ? !getTimeToResolve().equals(this)  ? getTimeToResolve().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "priority" + "=" + (getPriority() != null ? !getPriority().equals(this)  ? getPriority().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "assetPlus = "+(getAssetPlus()!=null?Integer.toHexString(System.identityHashCode(getAssetPlus())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ticketRaiser = "+(getTicketRaiser()!=null?Integer.toHexString(System.identityHashCode(getTicketRaiser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ticketFixer = "+(getTicketFixer()!=null?Integer.toHexString(System.identityHashCode(getTicketFixer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "asset = "+(getAsset()!=null?Integer.toHexString(System.identityHashCode(getAsset())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "fixApprover = "+(getFixApprover()!=null?Integer.toHexString(System.identityHashCode(getFixApprover())):"null");
  }
}