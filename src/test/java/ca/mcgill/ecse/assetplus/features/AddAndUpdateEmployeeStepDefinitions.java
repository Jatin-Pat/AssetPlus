package ca.mcgill.ecse.assetplus.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import ca.mcgill.ecse.assetplus.model.*;

public class AddAndUpdateEmployeeStepDefinitions {
  //team1
  @Given("the following employees exist in the system \\(p11)")
  public void the_following_employees_exist_in_the_system_p11(
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

  @Given("the following manager exists in the system \\(p11)")
  public void the_following_manager_exists_in_the_system_p11(
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

  @When("a new employee attempts to register with {string}, {string}, {string}, and {string} \\(p11)")
  public void a_new_employee_attempts_to_register_with_and_p11(String string, String string2,
      String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  //team2
  @When("the employee with {string} attempts to update their account information to {string}, {string}, and {string} \\(p11)")
  public void the_employee_with_attempts_to_update_their_account_information_to_and_p11(
      String string, String string2, String string3, String string4) {
    // string = email; string2 = newPassword; string3 = newName; string4 = newPhoneNumber;
    //checks if the email and password fields are non-null
    String[] strings = {string, string2};
    for (String aString : strings) {
      assertNotNull(aString);
    }
    assertTrue(Employee.hasWithEmail(string));
    Employee employeeToBeUpdated = (Employee) User.getWithEmail(string);
    employeeToBeUpdated.setPassword(string2);
    if (string3 != null) {
      employeeToBeUpdated.setName(string3);
    }
    if (string4 != null) {
      employeeToBeUpdated.setPhoneNumber(string4);
    }
    assertEquals(employeeToBeUpdated.getPassword(), string2);
    assertEquals(employeeToBeUpdated.getName(), string3);
    assertEquals(employeeToBeUpdated.getPhoneNumber(), string4);
    throw new io.cucumber.java.PendingException();
  }

  @Then("the following {string} shall be raised \\(p11)")
  public void the_following_shall_be_raised_p11(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of employees in the system shall be {string} \\(p11)")
  public void the_number_of_employees_in_the_system_shall_be_p11(String string) {
    // Write code here that turns the phrase above into concrete actions

    // I don't know if this is right 
    List <Employee> employees = AssetPlus.getEmployees();
    
    assertEquals(AssetPlus.numberOfEmployees(), employees.size());
    throw new io.cucumber.java.PendingException();
  }

  //team3
  @Then("a new employee account shall exist with {string}, {string}, {string}, and {string} \\(p11)")
  public void a_new_employee_account_shall_exist_with_and_p11(String string, String string2,
      String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions
    
    //Checks an employee with given email exists
    assertTrue(Employee.hasWithEmail(string));
    //Checks other employee attributes
    Employee existingEmployee = (Employee) User.getWithEmail(string);
    assertEquals(string2, existingEmployee.getName());
    assertEquals(string3, existingEmployee.getPassword());
    assertEquals(string4,existingEmployee.getPhoneNumber());
    
    throw new io.cucumber.java.PendingException();
  }

  @Then("their employee account information will be updated and is now {string}, {string}, {string}, and {string} \\(p11)")
  public void their_employee_account_information_will_be_updated_and_is_now_and_p11(String string,
      String string2, String string3, String string4) {
    //Not sure about this one, I will name an Employee variable, assuming
    //it is instantiated in the related @When section above
    Employee updatedEmployee;
    assertEquals(string, updatedEmployee.getEmail());
    assertEquals(string2, updatedEmployee.getName());
    assertEquals(string3, updatedEmployee.getPassword());
    assertEquals(string4, updatedEmployee.getPhoneNumber());
    
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the following employees shall exist in the system \\(p11)")
  public void the_following_employees_shall_exist_in_the_system_p11(
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
}