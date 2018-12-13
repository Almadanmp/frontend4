package Sprint0_Test.ControllerTest;

import Sprint0.Controller.US03Controller;
import Sprint0.Model.GeographicAreaList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * User Story 03 - Controller Tests
 */
public class US03ControllerTest {

    @Test
    public void seeIfCreatesGeographicAreaAndAddsItToList() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;
        US03Controller us3 = new US03Controller();
        boolean result = us3.addNewGeoArea(geoList, name, typeArea, latitude, longitude);

        assertTrue(result);
        assertEquals(1, geoList.getGeographicAreaList().size());
    }

    @Test
    public void seeIfFailsCreatingSecondEqualGeographicArea() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;

        US03Controller us3 = new US03Controller();
        boolean result1 = us3.addNewGeoArea(geoList, name, typeArea, latitude, longitude);
        boolean result2 = us3.addNewGeoArea(geoList, name, typeArea, latitude, longitude);

        assertTrue(result1); //safety check (already covered on previous test)
        assertFalse(result2);
        assertEquals(1, geoList.getGeographicAreaList().size());
    }

    @Test
    public void seeIfCreatesTwoDifferentGeographicAreas() {
        GeographicAreaList geoList = new GeographicAreaList();
        String name1 = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;
        String name2 = "Lisboa";

        US03Controller us3 = new US03Controller();
        boolean result1 = us3.addNewGeoArea(geoList, name1, typeArea, latitude, longitude);
        boolean result2 = us3.addNewGeoArea(geoList, name2, typeArea, latitude, longitude);

        assertTrue(result1); //safety check (already covered on previous test)
        assertTrue(result2);
        assertEquals(2, geoList.getGeographicAreaList().size());
    }

    @Test
    public void seeIfFailsWithNullInputGeoList() {
        String name1 = "Porto";
        String typeArea = "Distrito";
        double latitude = 38;
        double longitude = 7;

        US03Controller us3 = new US03Controller();
        boolean result = us3.addNewGeoArea(null, name1, typeArea, latitude, longitude);

        assertFalse(result);
    }
}