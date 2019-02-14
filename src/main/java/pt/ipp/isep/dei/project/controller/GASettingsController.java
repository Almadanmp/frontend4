package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.model.*;

import java.util.List;

/**
 * Controller class for Geographical Area Settings UI
 */

public class GASettingsController {

    //GEOGRAPHIC AREA SETTINGS CONTROLLER  - SHARED METHODS//

    /**
     * @param input        is the name of the TypeArea we want to search the list for.
     * @param typeAreaList is the list where we're going to search for areas of the given name.
     * @return is a list of integers with all the indexes, in the parameter list, where
     * typeAreas with the name given in the first parameter are contained.
     */

    List<Integer> matchTypeAreaIndexByString(String input, TypeAreaList typeAreaList) {
        return typeAreaList.matchGeographicAreaTypeIndexByString(input);
    }

    /**
     * @param listOfIndexesTypeGeographicAreas is a list of integers containing the indexes of objects we want
     *                                         to access.
     * @param typeAreaList                     is the list where we're going to look for said objects.
     * @return builds a string of all the objects contained in the indexes of the provided list.
     */

    String buildTypeAreaElementsByIndexString(List<Integer> listOfIndexesTypeGeographicAreas, TypeAreaList typeAreaList) {
        return typeAreaList.buildGATypeElementsByIndexString(listOfIndexesTypeGeographicAreas);
    }

    /**
     * @param typeArea is the type of area to print.
     * @return builds a string with the given type of area.
     */

    public String buildTypeAreaString(TypeArea typeArea) {
        return typeArea.buildTypeGeographicAreaString();
    }

    /**
     * @param typeAreaList is the list of Geographic Area Types we want to print.
     * @return builds a string with each individual member of the given list.
     */

    public String buildGATypeListString(TypeAreaList typeAreaList) {
        return typeAreaList.buildGATypeWholeListString(typeAreaList);
    }

    /**
     * @param geoAreaList is the list of Geographic Areas we want to print.
     * @return builds a string with each individual member of the given list.
     */

    public String buildGAListString(GeographicAreaList geoAreaList) {
        return geoAreaList.buildGaWholeListString(geoAreaList);
    }



    /*
     * User Story 01
     * As a system administrator, I wish to define a new type of geographic area, so that later I can classify said geographic area.
     */

    /**
     * This method creates a new Type of Geographic Area and adds it to a List.
     *
     * @param input - the String name of the Type of Geographic Area.
     * @return true - the Type of Geographic Area was successfully created and added to a list;
     * false - the name is null.
     */

    public boolean createAndAddTypeAreaToList(String input, TypeAreaList typeAreaList) {
        return typeAreaList.newTAG(input);
    }

    /* User Story 02
     As a System Administrator I want to receive a list of all the previously stated Types of area.
     */

    public String getTypeAreaList(TypeAreaList typeAreaList) {
        return typeAreaList.buildGATypeWholeListString(typeAreaList);
    }

    /* User Story - 03 As a System Administrator I want to Create a new Geographic Area */

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

    public boolean addNewGeoAreaToList(GeographicAreaList newGeoList, String newName, TypeArea typeArea, double latitude, double longitude, double altitude, double length, double width) {
        GeographicArea geoToAdd = new GeographicArea(newName, new TypeArea(typeArea.getTypeOfGeographicArea()), length, width, new Local(latitude, longitude, altitude));
        return newGeoList.addGeographicAreaToGeographicAreaList(geoToAdd);
    }

    /* USER STORY 04 -  As an Administrator, I want to get a list of existing geographical areas of a given type. */

    /**
     * @param geographicAreaList is the Geographic Area List where we want to search for objects with a given type.
     * @param typeArea           is the type that we want to look for.
     * @return is a list of all the objects in the original list with a type that matches the given type.
     */

    public GeographicAreaList matchGAByTypeArea(GeographicAreaList geographicAreaList, TypeArea typeArea) {
        String typeAreaName = typeArea.getTypeOfGeographicArea();
        return geographicAreaList.matchGeographicAreaWithInputType(typeAreaName);
    }

    /**
     * @param typeArea is the Type of Area we want to get the name of.
     * @return is the name of the given type of area.
     */

    public String getTypeAreaName(TypeArea typeArea) {
        return typeArea.getTypeOfGeographicArea();
    }

    /*USER STORY 07 - As an Administrator, I want to add an existing geographical area to another one (e.g. add city of
    Porto to the district of Porto). */


    /**
     * Method that gets a Geographic Area and returns its Id.
     *
     * @param geographicArea that method will use
     * @return geographic area id as a string
     */
    public String getGeographicAreaId(GeographicArea geographicArea) {
        return geographicArea.getId();
    }

    /**
     * @param daughterArea is the area that is contained in another.
     * @param motherArea   is the area that contains another.
     */
    public boolean setMotherArea(GeographicArea daughterArea, GeographicArea motherArea) {
        return daughterArea.setMotherArea(motherArea);
    }

    /*USER STORY 08 - As an Administrator, I want to find out if a geographical area is included, directly
    or indirectly, in another one. */

    /**
     * @return returns true if one area is contained in the other, false if it isn't.
     */

    public boolean seeIfItsContained(GeographicArea motherGA, GeographicArea daughterGA) {
        return daughterGA.checkIfAreaIsContained(daughterGA, motherGA);
    }

}
