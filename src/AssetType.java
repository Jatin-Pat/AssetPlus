/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;
import java.sql.Date;

// line 38 "model.ump"
// line 125 "model.ump"
public class AssetType
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, AssetType> assettypesByType = new HashMap<String, AssetType>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AssetType Attributes
  private String type;
  private int count;

  //AssetType Associations
  private AssetPlus assetPlus;
  private List<Asset> assets;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AssetType(String aType, int aCount, AssetPlus aAssetPlus)
  {
    count = aCount;
    if (!setType(aType))
    {
      throw new RuntimeException("Cannot create due to duplicate type. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create assetType due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assets = new ArrayList<Asset>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(String aType)
  {
    boolean wasSet = false;
    String anOldType = getType();
    if (anOldType != null && anOldType.equals(aType)) {
      return true;
    }
    if (hasWithType(aType)) {
      return wasSet;
    }
    type = aType;
    wasSet = true;
    if (anOldType != null) {
      assettypesByType.remove(anOldType);
    }
    assettypesByType.put(aType, this);
    return wasSet;
  }

  public boolean setCount(int aCount)
  {
    boolean wasSet = false;
    count = aCount;
    wasSet = true;
    return wasSet;
  }

  public String getType()
  {
    return type;
  }
  /* Code from template attribute_GetUnique */
  public static AssetType getWithType(String aType)
  {
    return assettypesByType.get(aType);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithType(String aType)
  {
    return getWithType(aType) != null;
  }

  public int getCount()
  {
    return count;
  }
  /* Code from template association_GetOne */
  public AssetPlus getAssetPlus()
  {
    return assetPlus;
  }
  /* Code from template association_GetMany */
  public Asset getAsset(int index)
  {
    Asset aAsset = assets.get(index);
    return aAsset;
  }

  public List<Asset> getAssets()
  {
    List<Asset> newAssets = Collections.unmodifiableList(assets);
    return newAssets;
  }

  public int numberOfAssets()
  {
    int number = assets.size();
    return number;
  }

  public boolean hasAssets()
  {
    boolean has = assets.size() > 0;
    return has;
  }

  public int indexOfAsset(Asset aAsset)
  {
    int index = assets.indexOf(aAsset);
    return index;
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
      existingAssetPlus.removeAssetType(this);
    }
    assetPlus.addAssetType(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssets()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Asset addAsset(Date aPurchaseDate, String aExpectedLifespan, Location aLocation, AssetPlus aAssetPlus, Manager aManager)
  {
    return new Asset(aPurchaseDate, aExpectedLifespan, this, aLocation, aAssetPlus, aManager);
  }

  public boolean addAsset(Asset aAsset)
  {
    boolean wasAdded = false;
    if (assets.contains(aAsset)) { return false; }
    AssetType existingAssetType = aAsset.getAssetType();
    boolean isNewAssetType = existingAssetType != null && !this.equals(existingAssetType);
    if (isNewAssetType)
    {
      aAsset.setAssetType(this);
    }
    else
    {
      assets.add(aAsset);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAsset(Asset aAsset)
  {
    boolean wasRemoved = false;
    //Unable to remove aAsset, as it must always have a assetType
    if (!this.equals(aAsset.getAssetType()))
    {
      assets.remove(aAsset);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssetAt(Asset aAsset, int index)
  {  
    boolean wasAdded = false;
    if(addAsset(aAsset))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssets()) { index = numberOfAssets() - 1; }
      assets.remove(aAsset);
      assets.add(index, aAsset);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssetAt(Asset aAsset, int index)
  {
    boolean wasAdded = false;
    if(assets.contains(aAsset))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssets()) { index = numberOfAssets() - 1; }
      assets.remove(aAsset);
      assets.add(index, aAsset);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssetAt(aAsset, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    assettypesByType.remove(getType());
    AssetPlus placeholderAssetPlus = assetPlus;
    this.assetPlus = null;
    if(placeholderAssetPlus != null)
    {
      placeholderAssetPlus.removeAssetType(this);
    }
    for(int i=assets.size(); i > 0; i--)
    {
      Asset aAsset = assets.get(i - 1);
      aAsset.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "type" + ":" + getType()+ "," +
            "count" + ":" + getCount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "assetPlus = "+(getAssetPlus()!=null?Integer.toHexString(System.identityHashCode(getAssetPlus())):"null");
  }
}