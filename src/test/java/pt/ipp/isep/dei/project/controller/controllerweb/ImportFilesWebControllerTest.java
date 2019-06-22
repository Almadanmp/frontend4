package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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
import pt.ipp.isep.dei.project.model.house.HouseRepository;
import pt.ipp.isep.dei.project.model.sensortype.SensorTypeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    private MockMultipartFile gaFile;
    private MockMultipartFile gaFileNoGa;
    private MockMultipartFile gaReadings;
    private MockMultipartFile houseData;

    @BeforeEach
    void arrangeArtifacts() {
        MockitoAnnotations.initMocks(this);
        gaFile = new MockMultipartFile("DataSet_sprint07_GA", "DataSet_sprint07_GA", "application/json", "DataSet_sprint07_GA".getBytes());
        gaFileNoGa = new MockMultipartFile("TEST_ONLY_NO_GA", "TEST_ONLY_NO_GA", "application/json", "TEST_ONLY_NO_GA".getBytes());
        gaReadings = new MockMultipartFile("GA_READINGS", "GA_READINGS", "application/json", "GA_READINGS".getBytes());
        houseData = new MockMultipartFile("DataSet_sprint06_HouseData", "DataSet_sprint06_HouseData", "application/json", "DataSet_sprint06_HouseData".getBytes());
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

}
