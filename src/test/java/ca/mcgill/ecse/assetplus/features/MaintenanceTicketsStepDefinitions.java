package ca.mcgill.ecse.assetplus.features;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.HotelStaff;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.TicketImage;
import ca.mcgill.ecse.assetplus.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MaintenanceTicketsStepDefinitions {
   private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();
  private String error = "";

  @Given("the following employees exist in the system")
  public void the_following_employees_exist_in_the_system_p11(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> employeesToAdd = dataTable.asMaps();
    for (Map<String, String> employee : employeesToAdd) {
      String email = employee.get("email");
      String name = employee.get("name");
      String password = employee.get("password");
      String phoneNumber = employee.get("phoneNumber");
      assetPlus.addEmployee(email, name, password, phoneNumber);
    }
  }

  @Given("the following manager exists in the system")
  public void the_following_manager_exists_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> managerToAdd = dataTable.asMaps();
    for (Map<String, String> manager : managerToAdd) {
      String email = manager.get("email");
      String name = manager.get("name");
      String password = manager.get("password");
      String phoneNumber = manager.get("phoneNumber");
      new Manager(email, name, password, phoneNumber, assetPlus);
    }
  }
  @Given("the following asset types exist in the system")
  public void the_following_asset_types_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> assetTypeToAdd = dataTable.asMaps();
      for (Map<String,String> assetType : assetTypeToAdd) {
        String name = assetType.get("name");
        int lifeSpan = Integer.parseInt(assetType.get("expectedLifeSpan"));
        assetPlus.addAssetType(name, lifeSpan);
        }
  }

  @Given("the following assets exist in the system")
  public void the_following_assets_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> assetToAdd = dataTable.asMaps();
    for (Map<String, String> asset : assetToAdd) {
      int assetNumber = Integer.parseInt(asset.get("assetNumber"));
      AssetType assetType = AssetType.getWithName(asset.get("type"));
      Date purchaseDate = Date.valueOf(asset.get("purchaseDate"));
      int floorNumber = Integer.parseInt(asset.get("floorNumber"));
      int roomNumber = Integer.parseInt(asset.get("roomNumber"));
      assetPlus.addSpecificAsset(assetNumber, floorNumber, roomNumber, purchaseDate, assetType);
    }
  }

  @Given("the following tickets exist in the system")
  public void the_following_tickets_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> ticketToAdd = dataTable.asMaps();
    for (Map<String,String> ticket : ticketToAdd) {
      int id = Integer.parseInt(ticket.get("id"));
      User ticketRaiser = User.getWithEmail(ticket.get("ticketRaiser"));
      Date raisedOnDate = Date.valueOf(ticket.get("raisedOnDate"));
      String description = ticket.get("description");
      int assetNumber = Integer.parseInt(ticket.get("assetNumber"));
      MaintenanceTicket maintenanceTicket = new MaintenanceTicket(id, raisedOnDate, description, assetPlus, ticketRaiser);
      maintenanceTicket.setAsset(SpecificAsset.getWithAssetNumber(assetNumber));
    }
  }

  @Given("the following notes exist in the system")
  public void the_following_notes_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> noteToAdd = dataTable.asMaps();
    for (Map<String, String> note : noteToAdd) {
      MaintenanceTicket maintenanceTicket = MaintenanceTicket.getWithId(Integer.parseInt(note.get("ticketId")));
      maintenanceTicket.addTicketNote(Date.valueOf(note.get("addedOnDate")), note.get("description"), (HotelStaff) HotelStaff.getWithEmail(note.get("noteTaker")));
    }
  }

  @Given("the following ticket images exist in the system")
  public void the_following_ticket_images_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> ticketImageToAdd= dataTable.asMaps();
    for (var ticketImage : ticketImageToAdd) {
      String imageUrl = ticketImage.get("imageUrl");
      int ticketId = Integer.parseInt(ticketImage.get("ticketId"));
      new TicketImage(imageUrl, MaintenanceTicket.getWithId(ticketId));
    }
  }

  @Given("ticket {string} is marked as {string} with requires approval {string}")
  public void ticket_is_marked_as_with_requires_approval(String ticketId, String initialState, String requiresApproval) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("ticket {string} is marked as {string}")
  public void ticket_is_marked_as(String ticketId, String state) {
    
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the manager attempts to view all maintenance tickets in the system")
  public void the_manager_attempts_to_view_all_maintenance_tickets_in_the_system() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the manager attempts to assign the ticket {string} to {string} with estimated time {string}, priority {string}, and requires approval {string}")
  public void the_manager_attempts_to_assign_the_ticket_to_with_estimated_time_priority_and_requires_approval(
      String string, String string2, String string3, String string4, String string5) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the hotel staff attempts to start the ticket {string}")
  public void the_hotel_staff_attempts_to_start_the_ticket(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the manager attempts to approve the ticket {string}")
  public void the_manager_attempts_to_approve_the_ticket(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the hotel staff attempts to complete the ticket {string}")
  public void the_hotel_staff_attempts_to_complete_the_ticket(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the manager attempts to disapprove the ticket {string} on date {string} and with reason {string}")
  public void the_manager_attempts_to_disapprove_the_ticket_on_date_and_with_reason(String string,
      String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the ticket {string} shall be marked as {string}")
  public void the_ticket_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the ticket {string} shall not exist in the system")
  public void the_ticket_shall_not_exist_in_the_system(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the ticket {string} shall have estimated time {string}, priority {string}, and requires approval {string}")
  public void the_ticket_shall_have_estimated_time_priority_and_requires_approval(String string,
      String string2, String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the ticket {string} shall be assigned to {string}")
  public void the_ticket_shall_be_assigned_to(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of tickets in the system shall be {string}")
  public void the_number_of_tickets_in_the_system_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the following maintenance tickets shall be presented")
  public void the_following_maintenance_tickets_shall_be_presented(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Then("the ticket with id {string} shall have the following notes")
  public void the_ticket_with_id_shall_have_the_following_notes(String string,
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Then("the ticket with id {string} shall have no notes")
  public void the_ticket_with_id_shall_have_no_notes(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the ticket with id {string} shall have the following images")
  public void the_ticket_with_id_shall_have_the_following_images(String string,
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Then("the ticket with id {string} shall have no images")
  public void the_ticket_with_id_shall_have_no_images(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
