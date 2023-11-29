package ca.mcgill.ecse.assetplus.application;

import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;
import javafx.application.Application;
import java.sql.Date;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;


public class AssetPlusApplication {

  private static AssetPlus assetPlus;

  public static void main(String[] args) {

      AssetPlus assetPlus = getAssetPlus();      
      
      if (!assetPlus.hasManager()){
        Manager manager = new Manager("manager@ap.com", null, "manager", null, assetPlus);
        assetPlus.setManager(manager);
      }
      if(assetPlus.getMaintenanceTickets().isEmpty()){
        assetPlus.addMaintenanceTicket(new MaintenanceTicket(0, Date.valueOf("2023-11-11"),"Dummy ticket", assetPlus,assetPlus.getManager() ));
      }

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
