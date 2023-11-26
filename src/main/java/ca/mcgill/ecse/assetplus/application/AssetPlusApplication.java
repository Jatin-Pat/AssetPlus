package ca.mcgill.ecse.assetplus.application;

import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;
import javafx.application.Application;
import ca.mcgill.ecse.assetplus.javafx.fxml.AssetPlusFxmlView;


public class AssetPlusApplication {

  private static AssetPlus assetPlus;

  public static void main(String[] args) {

      AssetPlus assetPlus = getAssetPlus();

      

      Application.launch(AssetPlusFxmlView.class, args);
      Manager manager = new Manager("manager@ap.com", null, "manager", null, assetPlus);
      assetPlus.setManager(manager);
      
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
