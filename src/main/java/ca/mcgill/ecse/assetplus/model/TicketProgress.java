/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/



// line 2 "model.ump"
// line 26 "model.ump"
public class TicketProgress
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TicketProgress State Machines
  public enum TicketStatus { Raised, Assigned, InProgress, Resolved, Closed }
  private TicketStatus ticketStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TicketProgress()
  {
    setTicketStatus(TicketStatus.Raised);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getTicketStatusFullName()
  {
    String answer = ticketStatus.toString();
    return answer;
  }

  public TicketStatus getTicketStatus()
  {
    return ticketStatus;
  }

  public boolean assignStaff()
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Raised:
        setTicketStatus(TicketStatus.Assigned);
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
      case Assigned:
        setTicketStatus(TicketStatus.InProgress);
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
      case InProgress:
        if (approvalRequired())
        {
          setTicketStatus(TicketStatus.Resolved);
          wasEventProcessed = true;
          break;
        }
        if (!(approvalRequired()))
        {
          setTicketStatus(TicketStatus.Closed);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean approved()
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Resolved:
        setTicketStatus(TicketStatus.Closed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean disapproved()
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Resolved:
        setTicketStatus(TicketStatus.InProgress);
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

  public void delete()
  {}

}