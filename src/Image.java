/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;

// line 79 "model.ump"
// line 147 "model.ump"
public class Image
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Image Attributes
  private Date date;
  private String url;

  //Image Associations
  private Ticket ticket;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Image(Date aDate, String aUrl, Ticket aTicket)
  {
    date = aDate;
    url = aUrl;
    boolean didAddTicket = setTicket(aTicket);
    if (!didAddTicket)
    {
      throw new RuntimeException("Unable to create image due to ticket. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setUrl(String aUrl)
  {
    boolean wasSet = false;
    url = aUrl;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public String getUrl()
  {
    return url;
  }
  /* Code from template association_GetOne */
  public Ticket getTicket()
  {
    return ticket;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTicket(Ticket aTicket)
  {
    boolean wasSet = false;
    if (aTicket == null)
    {
      return wasSet;
    }

    Ticket existingTicket = ticket;
    ticket = aTicket;
    if (existingTicket != null && !existingTicket.equals(aTicket))
    {
      existingTicket.removeImage(this);
    }
    ticket.addImage(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Ticket placeholderTicket = ticket;
    this.ticket = null;
    if(placeholderTicket != null)
    {
      placeholderTicket.removeImage(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "url" + ":" + getUrl()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "ticket = "+(getTicket()!=null?Integer.toHexString(System.identityHashCode(getTicket())):"null");
  }
}