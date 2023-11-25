package ca.mcgill.ecse.assetplus.application;

import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;
import javafx.application.Application;
import java.sql.Date;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;


public class AssetPlusApplication {

  private static AssetPlus assetPlus;

  public static void main(String[] args) {
    AssetPlus assetPlus = getAssetPlus();

    Employee testEmployee = new Employee("email", "name", "password", "number", assetPlus);
    MaintenanceTicket testTicket = new MaintenanceTicket(34, Date.valueOf("2002-03-03"), "shits broken", assetPlus, testEmployee);

    Application.launch(AssetPlusFxmlView.class, args);
    // TODO Start the application user interface here
  }

  public static AssetPlus getAssetPlus() {
    if (assetPlus == null) {
      // these attributes are default, you should set them later with the setters
      // assetPlus = new AssetPlus();
      assetPlus = AssetPlusPersistence.load();

    }
    return assetPlus;
  }

}
