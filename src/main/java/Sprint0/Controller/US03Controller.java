package Sprint0.Controller;

import Sprint0.Model.GeographicArea;
import Sprint0.Model.GeographicAreaList;
import Sprint0.Model.Local;
import Sprint0.Model.TypeArea;


/**
 * User Story 3
 * As a System Administrator I want to Create a new Geographic Area
 * <p>
 * US 03 controller class. Provides methods to create a new GA and add it to a given list.
 */
public class US03Controller {

    /**
     * Method to add a new geographic area to a list of geographic areas
     *
     * @param newGeoList geographic area list to add the new geographic area
     * @param newName    input string for geographic area name
     * @param typeArea   input string for type area
     * @param latitude   input number for latitude
     * @param longitude  input number for longitude
     * @return success if a new GA is added, false otherwise
     */
    public boolean addNewGeoArea(GeographicAreaList newGeoList, String newName, String typeArea, double latitude, double longitude) {
        if (newGeoList == null) {
            return false;
        }
        GeographicArea geoToAdd = new GeographicArea(newName, new TypeArea(typeArea), new Local(latitude, longitude));
        return newGeoList.addGeographicAreaToGeographicAreaList(geoToAdd);
    }
}