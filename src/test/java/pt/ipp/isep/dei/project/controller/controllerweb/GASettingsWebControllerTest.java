package pt.ipp.isep.dei.project.controller.controllerweb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import pt.ipp.isep.dei.project.dto.GeographicAreaDTO;
import pt.ipp.isep.dei.project.dto.LocalDTO;
import pt.ipp.isep.dei.project.model.geographicarea.GeographicAreaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@WebMvcTest
@ContextConfiguration(classes = HibernateJpaAutoConfiguration.class)
class GASettingsWebControllerTest {

    @Mock
    private GeographicAreaRepository geographicAreaRepository;
    @InjectMocks
    private GASettingsWebController gaSettingsWebController;

    @BeforeEach
    void insertData() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void seeIfCreateGeoAreaWorks() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setId(66L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

        Mockito.doReturn(true).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));
        Link link = linkTo(methodOn(GASettingsWebController.class).getAllGeographicAreas()).withRel("See all geographic areas");

        validGeographicAreaDTO.add(link);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(HttpStatus.CREATED, actualResult.getStatusCode());
    }


    @Test
    void seeIfCreateGeoAreaDoesntWorkIsRepeated() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setId(66L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(validGeographicAreaDTO);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. That Area already exists.", HttpStatus.CONFLICT);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkIDNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkNameNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setId(2L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkTypeNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setId(2L);
        validGeographicAreaDTO.setName("Porto");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkLocalNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();

        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setId(2L);
        validGeographicAreaDTO.setName("Porto");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");
//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaWorksAllNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();

        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaIDNotNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        validGeographicAreaDTO.setId(66L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaNameNotNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaTypeNotNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        validGeographicAreaDTO.setTypeArea("urban");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfCreateGeoAreaDoesntWorkLocalNotNull() {
        //Arrange

        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);

//        Mockito.doReturn(false).when(geographicAreaRepository).addAndPersistDTO(any(GeographicAreaDTO.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been created. You have entered an invalid Area.", HttpStatus.BAD_REQUEST);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.createGeoArea(validGeographicAreaDTO);

        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void getAllGeoAreasDTO() {
        //Arrange
        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setId(66L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

        List<GeographicAreaDTO> geographicAreas = new ArrayList<>();
        geographicAreas.add(validGeographicAreaDTO);

        Mockito.when(geographicAreaRepository.getAllDTO()).thenReturn(geographicAreas);

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(geographicAreas, HttpStatus.OK);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.getAllGeographicAreas();

        //Assert
        assertEquals(expectedResult, actualResult);

    }
//
//    @Test
//    void addDaughterArea() {
//        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
//
//        validGeographicAreaDTO.setDescription("3rd biggest city");
//        validGeographicAreaDTO.setId(2L);
//        validGeographicAreaDTO.setWidth(100);
//        validGeographicAreaDTO.setLength(500);
//        validGeographicAreaDTO.setTypeArea("urban area");
//
//        Link link = linkTo(methodOn(GASettingsWebController.class).getGeographicArea(validGeographicAreaDTO.getGeographicAreaId())).withRel("See geographic area");
//        validGeographicAreaDTO.add(link);
//
//        Mockito.doReturn(true).when(geographicAreaRepository).addDaughterArea(any(long.class), any(long.class));
//
//        ResponseEntity<Object> expectedResult = new ResponseEntity<>(validGeographicAreaDTO, HttpStatus.OK);
//
//        //Act
//        ResponseEntity<Object> actualResult = gaSettingsWebController.addDaughterArea(1L, 3L);
//
//        //Assert
//        assertEquals(expectedResult, actualResult);
//    }

    @Test
    void addDaughterAreaContainsDaughter() {
        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();

        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setId(2L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

        Mockito.doReturn(false).when(geographicAreaRepository).addDaughterArea(any(long.class), any(long.class));

        ResponseEntity<String> expectedResult = new ResponseEntity<>("The Geographic Area hasn't been added. The daughter area is already contained in the mother area.", HttpStatus.CONFLICT);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.addDaughterArea(6L, validGeographicAreaDTO.getGeographicAreaId());

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addDaughterAreaNotFound() {

        Mockito.doThrow(NoSuchElementException.class).when(geographicAreaRepository).addDaughterArea(any(long.class), any(long.class));

        ResponseEntity<Object> actualResult = gaSettingsWebController.addDaughterArea(6L, 3L);

        assertEquals(HttpStatus.NOT_FOUND, actualResult.getStatusCode());
    }

    @Test
    void getGeoAreaDTO() {
        //Arrange
        GeographicAreaDTO validGeographicAreaDTO = new GeographicAreaDTO();
        LocalDTO localDTO = new LocalDTO();

        localDTO.setLatitude(41);
        localDTO.setLongitude(-8);
        localDTO.setAltitude(100);

        validGeographicAreaDTO.setLocal(localDTO);
        validGeographicAreaDTO.setDescription("3rd biggest city");
        validGeographicAreaDTO.setName("Gaia");
        validGeographicAreaDTO.setId(66L);
        validGeographicAreaDTO.setWidth(100);
        validGeographicAreaDTO.setLength(500);
        validGeographicAreaDTO.setTypeArea("urban area");

        ResponseEntity<Object> expectedResult = new ResponseEntity<>(HttpStatus.OK);

        //Act
        ResponseEntity<Object> actualResult = gaSettingsWebController.getGeographicArea(validGeographicAreaDTO.getGeographicAreaId());

        //Assert
        assertEquals(expectedResult, actualResult);

    }
}


