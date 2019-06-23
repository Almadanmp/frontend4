package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import pt.ipp.isep.dei.project.controller.controllercli.HouseConfigurationController;
import pt.ipp.isep.dei.project.controller.controllercli.ReaderController;
import pt.ipp.isep.dei.project.io.ui.commandline.GASettingsUI;
import pt.ipp.isep.dei.project.io.ui.commandline.HouseConfigurationUI;
import pt.ipp.isep.dei.project.io.ui.utils.InputHelperUI;
import pt.ipp.isep.dei.project.model.areatype.AreaTypeRepository;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ImportFilesWebControllerTest {
    @Mock
    InputHelperUI inputHelperUI;
    @Mock
    GASettingsUI gaSettingsUI;
    @Mock
    private ReaderController readerController;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private HouseConfigurationController houseConfigurationController;
    @Mock
    private HouseConfigurationUI houseConfigurationUI;
    @Mock
    GeographicAreaRepository geographicAreaRepository;
    @Mock
    SensorTypeRepository sensorTypeRepository;
    @Mock
    AreaTypeRepository areaTypeRepository;

    @InjectMocks
    ImportFilesWebController importFilesWebController;

    private MockMultipartFile gaFileNoGa;
    private MockMultipartFile gaReadings;
    private MockMultipartFile gaData;
    private MockMultipartFile houseData;
    private MockMultipartFile houseSensorsData;
    private MockMultipartFile houseReadingsData;


    private MockMultipartFile emptyFile;

    @Mock
    private MockMultipartFile invalidFile;


    @BeforeEach
    void arrangeArtifacts() throws IOException {
        MockitoAnnotations.initMocks(this);

        gaData = generateFile("src/test/resources/geoAreaFiles/DataSet_sprint06_GA.json");

        houseData = generateFile("src/test/resources/houseFiles/DataSet_sprint06_House.json");
        houseSensorsData = generateFile("src/test/resources/houseSensorFiles/DataSet_sprint06_HouseSensors.json");
        houseReadingsData = generateFile("src/test/resources/readingsFiles/DataSet_sprint06_HouseSensorData.json");


        gaFileNoGa = new MockMultipartFile("TEST_ONLY_NO_GA", "TEST_ONLY_NO_GA", "application/json", "TEST_ONLY_NO_GA".getBytes());
        gaReadings = new MockMultipartFile("GA_READINGS", "GA_READINGS", "application/json", "GA_READINGS".getBytes());


        emptyFile = new MockMultipartFile("EMPTY_FILE", "EMPTY_FILE", "application/json", "".getBytes());
    }

    private MockMultipartFile generateFile(String path) throws IOException {
        File file = new File(path);
        try {
            byte[] data = new byte[(int) file.length()];
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dis.readFully(data);


            return new MockMultipartFile("file", file.getName(), "text/plain", data);
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    void importGAFileSuccessNoAreas() {
        // Act

        ResponseEntity<?> expectedResult = new ResponseEntity<>("Successfully imported - TEST_ONLY_NO_GA.\n" +
                "\n" +
                "No Geographic Areas were imported.", HttpStatus.OK);

        ResponseEntity<?> actualResult = importFilesWebController.importGAFile(gaFileNoGa);

        // Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void importGAReadingsSuccess() {
        // Act
        ResponseEntity<?> expectedResult = new ResponseEntity<>("Successfully imported - GA_READINGS.\n" +
                "null", HttpStatus.OK);

        ResponseEntity<?> actualResult = importFilesWebController.importGAReadings(gaReadings);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void importGA() {
        String resultContained = "Successfully imported - DataSet_sprint06_GA.json.\n4 Geographic Areas have been successfully imported. \nImport time";

        Mockito.when(inputHelperUI.acceptPathJSONorXMLAndReadFile(any(String.class), any(GeographicAreaRepository.class),
                any(SensorTypeRepository.class), any(AreaTypeRepository.class))).thenReturn(4);

        // Act

        ResponseEntity<?> actualResult = importFilesWebController.importGAFile(gaData);

        // Assert
        assertTrue(actualResult.getBody().toString().contains(resultContained));
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }


    @Test
    void importHouse() {
        String resultContained = "Successfully imported - DataSet_sprint06_House.json.\nHouse imported \nImport time: ";

        List<House> houseList = new ArrayList<>();
        House house = new House();
        houseList.add(house);

        Mockito.when(houseRepository.getHouses()).thenReturn(houseList);

        // Act

        ResponseEntity<?> actualResult = importFilesWebController.importHouseFile(houseData);

        // Assert
        assertTrue(actualResult.getBody().toString().contains(resultContained));
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }

    @Test
    void importHouseSensors() {
        int[] responseArray = {20, 1};
        String resultContained = "Successfully imported - DataSet_sprint06_HouseSensors.json.\n" +
                "20 Sensors successfully imported and 1 rejected.\nImport time:";

        Mockito.when(houseConfigurationController.readSensors(any(String.class))).thenReturn(responseArray);
        ResponseEntity<?> actualResult = importFilesWebController.importHouseSensorsFile(houseSensorsData);

        // Assert
        assertTrue(actualResult.getBody().toString().contains(resultContained));
        assertEquals(HttpStatus.OK, actualResult.getStatusCode());
    }

    @Test
    void importHouseReadings() {
        String result = "Successfully imported - DataSet_sprint06_HouseSensorData.json.\nSuccessfully imported 300 readings";

        ResponseEntity<?> expectedResult = new ResponseEntity<>(result, HttpStatus.OK);

        Mockito.when(houseConfigurationUI.selectImportHouseReadingsMethod(any(String.class))).thenReturn("Successfully imported 300 readings");
        ResponseEntity<?> actualResult = importFilesWebController.importHouseReadings(houseReadingsData);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void importEmptyFile() {
        // Act

        ResponseEntity<?> expectedResult = new ResponseEntity<>("WARNING: Imported file is empty.", HttpStatus.OK);

        ResponseEntity<?> actualResult = importFilesWebController.importGAReadings(emptyFile);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void importInvalidFile() throws IOException {
        // Act

        String errorMsg = "Error: Unable to read file bytes";
        ResponseEntity<?> expectedResult = new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);

        Mockito.when(invalidFile.getBytes()).thenThrow(new IOException(errorMsg));

        ResponseEntity<?> actualResult = importFilesWebController.importGAReadings(invalidFile);

        // Assert

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void dataImportDefault() {
        // Act

        ResponseEntity<?> expectedResult = new ResponseEntity<>("Internal Error (Invalid Operation)", HttpStatus.BAD_REQUEST);


        ResponseEntity<?> actualResult = importFilesWebController.dataImportProcessor(gaData, "Internal Error (Invalid Operation)");

        // Assert

        assertEquals(expectedResult, actualResult);
    }

}
