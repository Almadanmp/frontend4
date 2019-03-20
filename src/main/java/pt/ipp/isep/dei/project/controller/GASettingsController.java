package pt.ipp.isep.dei.project.controller;

import pt.ipp.isep.dei.project.dto.*;
import pt.ipp.isep.dei.project.io.ui.utils.InputUtils;
import pt.ipp.isep.dei.project.model.*;

/**
 * Controller class for Geographical Area Settings UI
 */

public class GASettingsController {

    //GEOGRAPHIC AREA SETTINGS CONTROLLER  - SHARED METHODS//

    /**
     * @param typeAreaList is the list of Geographic Area Types we want to print.
     * @return builds a string with each individual member of the given list.
     */

    public String buildGATypeListString(TypeAreaList typeAreaList) {
        return typeAreaList.buildString();
    }

    /**
     * @param geoAreaList is the list of Geographic Areas we want to print.
     * @return builds a string with each individual member of the given list.
     */

    public String buildGAListString(GeographicAreaList geoAreaList) {
        return geoAreaList.buildString();
    }



    /*
     * User Story 01
     * As a system administrator, I wish to define a new type of geographic area, so that later I can classify said geographic area.
     */

    /**
     * This method creates a new Type of Geographic Area and adds it to a List.
     *
     * @param typeAreaList - the list of types of areas
     * @param input        - the String name of the Type of Geographic Area.
     * @return true - the Type of Geographic Area was successfully created and added to a list or false if the name is
     * null.
     */
    public boolean createAndAddTypeAreaToList(String input, TypeAreaList typeAreaList) {
        return typeAreaList.createTypeArea(input);
    }

    /* User Story 02
     As a System Administrator I want to receive a list of all the previously stated Types of area.
     */

    public String getTypeAreaList(TypeAreaList typeAreaList) {
        return typeAreaList.buildString();
    }

    /* User Story - 03 As a System Administrator I want to Create a new Geographic Area */

    /**
     * Method to add a new geographic area to a list of geographic areas
     *
     * @param newGeoList geographic area list to add the new geographic area
     * @param localDTO   the latitude, longitude and altitude of the GA
     * @return success if a new GA is added, false otherwise
     */
    public boolean addNewGeoAreaToList(GeographicAreaList newGeoList, GeographicAreaDTO geoAreaDTO, LocalDTO localDTO) {
        Mapper mapper = new Mapper();
        GeographicArea geoToAdd = newGeoList.createGA(geoAreaDTO.getId(), new TypeArea(geoAreaDTO.getTypeArea()),
                geoAreaDTO.getLength(), geoAreaDTO.getLength(), mapper.dtoToLocal(localDTO));
        if (!(newGeoList.containsObjectMatchesParameters(geoAreaDTO.getId(), new TypeArea(geoAreaDTO.getTypeArea()),
                mapper.dtoToLocal(localDTO)))) {
            newGeoList.removeGeographicArea(geoToAdd);
            return newGeoList.addGeographicArea(geoToAdd);
        } else {
            return false;
        }
    }

    /**
     * Method to create a DTO of Geographic Area
     *
     * @param newName     name of the Geographic Area
     * @param typeAreaDTO Type area of the Geographic Area
     * @param localDTO    Localization of the Geographic Area
     * @param length      Length of the Geographic Area
     * @param width       width of the Geographic Area
     * @return Geographic Area DTO
     */
    public GeographicAreaDTO createGeoAreaDTO(String newName, TypeAreaDTO typeAreaDTO, LocalDTO localDTO, double length, double width) {
        Mapper mapper = new Mapper();
        GeographicArea geoArea = new GeographicArea(newName, mapper.dtoToTypeArea(typeAreaDTO), length, width, mapper.dtoToLocal(localDTO));
        return mapper.geographicAreaToDTO(geoArea);
    }

    /**
     * Method to create a DTO Localization
     *
     * @param latitude  value for latitude
     * @param longitude value for longitude
     * @param altitude  value for altitude
     * @return returns a Local DTO
     */
    public LocalDTO createLocalDTO(double latitude, double longitude, double altitude) {
        Local local = new Local(latitude, longitude, altitude);
        Mapper mapper = new Mapper();
        return mapper.localToDTO(local);
    }

    /* USER STORY 04 -  As an Administrator, I want to get a list of existing geographical areas of a given type. */

    /**
     * @param geographicAreaList is the Geographic Area List where we want to search for objects with a given type.
     * @param typeArea           is the type that we want to look for.
     * @return is a list of all the objects in the original list with a type that matches the given type.
     */
    public GeographicAreaList matchGAByTypeArea(GeographicAreaList geographicAreaList, TypeAreaDTO typeArea) {
        String typeAreaName = typeArea.getName();
        return geographicAreaList.getGeoAreasByType(typeAreaName);
    }

    /**
     * @param typeAreaDTO is the Type of Area we want to get the name of.
     * @return is the name of the given type of area.
     */
    public String getTypeAreaName(TypeAreaDTO typeAreaDTO) {
        return typeAreaDTO.getName();
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
     * @return true if the area was successfully added.
     */
    public boolean setMotherArea(GeographicArea daughterArea, GeographicArea motherArea) {
        return daughterArea.setMotherArea(motherArea);
    }

    /*USER STORY 08 - As an Administrator, I want to find out if a geographical area is included, directly
    or indirectly, in another one. */

    /**
     * @param motherGA   the geographic area that contains the daughter geographic area.
     * @param daughterGA the geographic area that is contained in the mother geographic area.
     * @return returns true if one area is contained in the other, false if it isn't.
     */

    public boolean isAreaContained(GeographicArea motherGA, GeographicArea daughterGA) {
        return daughterGA.isContainedInArea(motherGA);
    }

    /**
     * Select sensor from sensorList and convert it to DTO
     *
     * @param geographicArea with the sensor list
     * @return sensorDTO
     */
    public SensorDTO selectSensorDTOfFromGeoArea(GeographicArea geographicArea) {
        Mapper mapper = new Mapper();
        Sensor sensor = InputUtils.getInputSensorByList(geographicArea.getSensorList());
        sensor.activateOrDeactivate();
        return mapper.sensorToDTO(sensor);

    }


    public void isSensorActive(SensorDTO sensorDTO) {
        Mapper mapper = new Mapper();
        Sensor sensor = mapper.sensorDTOToObject(sensorDTO);
        if (sensor.isActive()) {
            System.out.println("Sensor successfully activated!");
        } else {
            System.out.println("Sensor successfully deactivated!");
        }
    }


}
