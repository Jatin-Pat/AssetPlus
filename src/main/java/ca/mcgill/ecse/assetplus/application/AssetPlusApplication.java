package ca.mcgill.ecse.assetplus.application;

import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.User;

public class AssetPlusApplication {

  private static AssetPlus assetPlus;

  public static void main(String[] args) {
    // TODO Start the application user interface here

    AssetPlus assetPlus = new AssetPlus();
    String val = "VALUES";
    Employee one = new Employee(val,val,val,val, assetPlus);
    Employee two = new Employee(val,val,val,val, assetPlus);

  }

  public static AssetPlus getAssetPlus() {
    if (assetPlus == null) {
      // these attributes are default, you should set them later with the setters
      assetPlus = new AssetPlus();
    }
    return assetPlus;
  }

}
