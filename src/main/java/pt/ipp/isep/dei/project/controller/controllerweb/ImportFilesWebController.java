package pt.ipp.isep.dei.project.controller.controllerweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/import")
public class ImportFilesWebController {
    private static final String IMPORT_TIME = "Import time: ";
    private static final String EMPTY_FILE = "ERROR: Imported file is empty.";
    private static final String MILLISECONDS = " millisecond(s).";
    private static final String SUCCESS = "Successfully imported - ";

    private static final String OPERATION_GA = "OPERATION_GA";
    private static final String OPERATION_GA_READINGS = "OPERATION_GA_READINGS";
    private static final String OPERATION_HOUSE = "OPERATION_HOUSE";
    private static final String OPERATION_HOUSE_SENSORS = "OP_HOUSE_SENSORS";
    private static final String OPERATION_HOUSE_READINGS = "OPERATION_HOUSE_READINGS";

    @Autowired
    GeographicAreaRepository geographicAreaRepository;
    @Autowired
    SensorTypeRepository sensorTypeRepository;
    @Autowired
    AreaTypeRepository areaTypeRepository;
    @Autowired
    InputHelperUI inputHelperUI;
    @Autowired
    GASettingsUI gaSettingsUI;
    @Autowired
    private ReaderController readerController;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private HouseConfigurationController houseConfigurationController;
    @Autowired
    private HouseConfigurationUI houseConfigurationUI;


    /**
     * Method to import files with Geographic Area and Area Sensors
     *
     * @param file file to import
     * @return response: string with information regarding success or failure
     */
    @PostMapping("/importGA")
    public ResponseEntity<Object> importGAFile(@RequestPart("file") MultipartFile file) {
        return dataImportProcessor(file, OPERATION_GA);
    }


    /**
     * Method to import Area Sensor Readings
     *
     * @param file file to import
     * @return response: string with information regarding success or failure
     */
    @PostMapping("/importAreaReadings")
    public ResponseEntity<Object> importGAReadings(@RequestPart("file") MultipartFile file) {
        return dataImportProcessor(file, OPERATION_GA_READINGS);
    }

    /**
     * Method to import files with House, rooms and Energy Grid Data
     *
     * @param file file to import
     * @return response: string with information regarding success or failure
     */
    @PostMapping("/importHouse")
    public ResponseEntity<Object> importHouseFile(@RequestPart("file") MultipartFile file) {
        return dataImportProcessor(file, OPERATION_HOUSE);
    }


    /**
     * Method to import a file with House Sensors data
     *
     * @param file file to import
     * @return response: string with information regarding success or failure
     */
    @PostMapping("/importHouseSensors")
    public ResponseEntity<Object> importHouseSensorsFile(@RequestPart("file") MultipartFile file) {
        return dataImportProcessor(file, OPERATION_HOUSE_SENSORS);
    }

    /**
     * Method to import House Sensor Readings
     *
     * @param file file to import
     * @return response: string with information regarding success or failure
     */
    @PostMapping("/importHouseReadings")
    public ResponseEntity<Object> importHouseReadings(@RequestPart("file") MultipartFile file) {
        return dataImportProcessor(file, OPERATION_HOUSE_READINGS);
    }


    private ResponseEntity<Object> dataImportProcessor(MultipartFile file, String operation) {
        String result;
        long startTime = System.currentTimeMillis();

        if (file.isEmpty()) {
            return createEmptyFileResponse();
        }

        try {
            Path path = saveUploadedFiles(file);
            switch (operation) {
                case OPERATION_GA:
                    result = importGAResult(path, startTime);
                    break;
                case OPERATION_GA_READINGS:
                    result = gaSettingsUI.selectImportGAReadingsMethod(path.toString());
                    break;
                case OPERATION_HOUSE:
                    result = importHouseResult(path, startTime);
                    break;
                case OPERATION_HOUSE_SENSORS:
                    result = importHouseSensorsResult(path, startTime);
                    break;
                case OPERATION_HOUSE_READINGS:
                    result = houseConfigurationUI.selectImportHouseReadingsMethod(path.toString());
                    break;
                default:
                    return createBadRequestResponse(new IllegalArgumentException());
            }
            Files.delete(path);
        } catch (IOException e) {
            return createBadRequestResponse(e);
        }
        return createSuccessResponse(file.getOriginalFilename(), result);
    }

    private String importGAResult(Path path, long startTime){
        int areas = inputHelperUI.acceptPathJSONorXMLAndReadFile(path.toString(), geographicAreaRepository, sensorTypeRepository, areaTypeRepository);
        if (areas > 0) {
            return areas + " Geographic Areas have been successfully imported. \n" + createIntervalMsg(startTime);
        } else {
            return "\nNo Geographic Areas were imported.";
        }
    }


    private String importHouseResult(Path path, long startTime){
        House house = houseRepository.getHouses().get(0);
        readerController.readJSONAndDefineHouse(house, path.toString());
        return " \n" + createIntervalMsg(startTime);
    }

    private String importHouseSensorsResult(Path path, long startTime){
        int[] importedSensors = houseConfigurationController.readSensors(path.toString());
        return importedSensors[0] + " Sensors successfully imported and " + importedSensors[1] + " rejected.\n" + createIntervalMsg(startTime);
    }

    private String createIntervalMsg(long startTime) {
        long stopTime = System.currentTimeMillis();
        return IMPORT_TIME + (stopTime - startTime) + MILLISECONDS;
    }

    /**
     * Method to save an uploaded file
     * Temporarily creates a (temporary) file from an uploaded file and return the path to the new file
     *
     * @param file the file to save
     * @return path to the saved file
     * @throws IOException if an exception occurs when saving the file
     */
    private Path saveUploadedFiles(MultipartFile file) throws IOException {
        Path path;
        byte[] bytes = file.getBytes();
        String uploadFolder = "src/test/resources/temp/";
        path = Paths.get(uploadFolder + file.getOriginalFilename());
        Files.write(path, bytes);

        return path;
    }

    private ResponseEntity<Object> createSuccessResponse(String filename, String result) {
        return new ResponseEntity<>(SUCCESS + filename + ".\n" + result, new HttpHeaders(), HttpStatus.OK);

    }

    private ResponseEntity <Object> createEmptyFileResponse() {
        return new ResponseEntity<>(EMPTY_FILE, HttpStatus.OK);
    }

    private ResponseEntity <Object> createBadRequestResponse(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }

}



