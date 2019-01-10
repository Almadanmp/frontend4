package pt.ipp.isep.dei.project.controller;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.project.model.*;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.testng.Assert.*;

class SensorSettingsControllerTest {

    //USER STORY 006 TESTS

    @Test
    void seeIfLocalIsCreated() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        ctrl.createLocal(lat, lon, alt);
        Local expectedResult = new Local(50, 50, 50);

        //Act
        Local actualResult = ctrl.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfLocalIsCreated2() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        double lat = 500.0;
        double lon = 500.0;
        double alt = 500.0;
        ctrl.createLocal(lat, lon, alt);
        Local expectedResult = new Local(500, 500, 500);

        //Act
        Local actualResult = ctrl.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfLocalIsCreated3() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        double lat = -50.0;
        double lon = -50.0;
        double alt = -50.0;
        ctrl.createLocal(lat, lon, alt);
        Local expectedResult = new Local(-50, -50, -50);

        //Act
        Local actualResult = ctrl.getLocal();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfTypeIsCreated() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String typeString = "Humedade";
        String units = "kg/m³";
        ctrl.createType(typeString, units);
        String expectedResult = "Humedade";

        //Act
        String actualResult = ctrl.getType().getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfTypeIsCreated2() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String typeString = "Humedade";
        String units = "kg/m³";
        ctrl.createType(typeString, units);
        String expectedResult = "Humedade";

        //Act
        String actualResult = ctrl.getType().getName();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfDateIsCreated() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        int year = 1989;
        int month = 7;
        int day = 12;
        ctrl.createDate(year, month, day);
        Date expectedResult = new GregorianCalendar(1989, 7, 12).getTime();

        //Act
        Date actualResult = ctrl.getDate();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsCreated() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        String unit = "kg/m³";
        TypeSensor type1 = ctrl.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        ctrl.createSensor(nameString, type1, loc1, date1);
        TypeSensor t1 = new TypeSensor("Humedade", "kg/m³");
        Sensor expectedResult = new Sensor("XV-56D", t1, loc1, new GregorianCalendar(2018, 8, 9).getTime());

        //Act
        Sensor actualResult = ctrl.createSensor(nameString, type1, loc1, date1);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsCreatedAndGetSensor() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        String unit = "kg/m³";
        TypeSensor type1 = ctrl.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        ctrl.createSensor(nameString, type1, loc1, date1);
        TypeSensor t1 = new TypeSensor("Humedade", "kg/m³");
        Sensor expectedResult = new Sensor("XV-56D", t1, loc1, new GregorianCalendar(2018, 8, 9).getTime());

        //Act
        Sensor actualResult = ctrl.getSensor();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfSensorIsAddedToSensorList() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        String unit = "kg/m³";
        TypeSensor type1 = ctrl.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();

        //Act
        boolean actualResult = ctrl.addSensor(sens1, xSensorList);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfSensorIsNotAddedToSensorList() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        String unit = "kg/m³";
        TypeSensor type1 = ctrl.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);

        //Act
        boolean actualResult = ctrl.addSensor(sens1, xSensorList);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfSensorListIsAddedToGeographicArea() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        String unit = "kg/m³";
        TypeSensor type1 = ctrl.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea areaG = new GeographicArea("Porto", t1, 2, 3, l1);
        areaG.setId("Alegria");

        String areaNameInput = "Alegria";
        GeographicAreaList xgaList = new GeographicAreaList();
        xgaList.addGeographicAreaToGeographicAreaList(areaG);

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaNameInput, xgaList, xSensorList);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfSensorListIsAddedToGeographicAreaFalse() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        String unit = "kg/m³";
        TypeSensor type1 = ctrl.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea areaG = new GeographicArea("Porto", t1, 2, 3, l1);
        areaG.setId("Alegria");

        String areaNameInput = "Direita";
        GeographicAreaList xgaList = new GeographicAreaList();
        xgaList.addGeographicAreaToGeographicAreaList(areaG);

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaNameInput, xgaList, xSensorList);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfSensorListIsAddedToGeographicAreaEmptyList() {

        //Arrange
        SensorSettingsController ctrl = new SensorSettingsController();
        String nameString = "XV-56D";
        double lat = 50.0;
        double lon = 50.0;
        double alt = 50.0;
        Local loc1 = ctrl.createLocal(lat, lon, alt);
        String typeStr = "Humedade";
        String unit = "kg/m³";
        TypeSensor type1 = ctrl.createType(typeStr, unit);
        int year = 2018;
        int month = 8;
        int day = 9;
        Date date1 = ctrl.createDate(year, month, day);
        Sensor sens1 = ctrl.createSensor(nameString, type1, loc1, date1);
        SensorList xSensorList = new SensorList();
        xSensorList.addSensor(sens1);
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea areaG = new GeographicArea("Porto", t1, 2, 3, l1);
        areaG.setId("Alegria");

        String areaNameInput = "Alegria";
        GeographicAreaList xgaList = new GeographicAreaList();

        //Act
        boolean actualResult = ctrl.addSensorToGeographicArea(areaNameInput, xgaList, xSensorList);

        //Assert
        assertFalse(actualResult);
    }

    //TODO REVIEW TEST AND METHOD - Sensor List is not being used and test is still working even without sensors
    @Test
    void seeIfSetTypeWorksFalse() {
        //Arrange
       /* Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "Km/h"),
                new Local(12, 31, 21),
                new GregorianCalendar(118, 10, 4).getTime());
        Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
                new Local(10, 30, 20),
                new GregorianCalendar(118, 1, 4).getTime());
        SensorList lc = new SensorList();
        lc.addSensor(s1);
        lc.addSensor(s2);*/
        boolean expectedResult = false;
        SensorSettingsController ctrl = new SensorSettingsController();

        //Act
        boolean actualResult = ctrl.setTypeSensor("Portugal", "Movement");

        //Assert
        assertEquals(expectedResult, actualResult);
    }

//TODO REVIEW TEST AND METHOD
/*
@Test
void seeIfSetTypeWorks() {
    //Arrange
    Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosphere", "Km/h"),
            new Local(12, 31, 21),
            new GregorianCalendar(118, 10, 4).getTime());
    Sensor s2 = new Sensor("Chuva", new TypeSensor("Atmosphere", "l/m2"),
            new Local(10, 30, 20),
            new GregorianCalendar(118, 1, 4).getTime());
    SensorList lc = new SensorList();
    lc.addSensor(s1);
    lc.addSensor(s2);
    boolean expectedResult = true;
    SensorSettingsController ctrl = new SensorSettingsController();

    //Act
    boolean actualResult = ctrl.setTypeSensor("Vento", "Movement");

    //Assert
    assertEquals(expectedResult, actualResult);
}
*/

}