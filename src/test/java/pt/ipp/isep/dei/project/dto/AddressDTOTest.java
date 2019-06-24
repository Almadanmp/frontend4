package pt.ipp.isep.dei.project.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AddressDTOTest {
    // Common testing artifacts for testing in this class.

    private AddressDTO validAddressDTO;
    private AddressLocalGeographicAreaIdDTO validAddressLocalDTO;

    @BeforeEach
    void arrangeArtifacts() {
        validAddressDTO = new AddressDTO();
        validAddressDTO.setStreet("Rua R. Dr. António Bernardino de Almeida 431");
        validAddressDTO.setTown("Porto");
        validAddressDTO.setZip("4200-072");
        validAddressLocalDTO = new AddressLocalGeographicAreaIdDTO();
        validAddressLocalDTO.setStreet("Rua R. Dr. António Bernardino de Almeida 431");
        validAddressLocalDTO.setTown("Porto");
        validAddressLocalDTO.setGeographicAreaId(23L);
        validAddressLocalDTO.setZip("4200-072");
    }

    @Test
    void seeIfGetSetStreetWorks() {
        //Arrange
        validAddressDTO.setStreet("Rua do ISEP");
        String expectedResult = "Rua do ISEP";
        //Act
        String actualResult = validAddressDTO.getStreet();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetZipWorks() {
        //Arrange
        validAddressDTO.setZip("4019-222");
        String expectedResult = "4019-222";
        //Act
        String actualResult = validAddressDTO.getZip();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetTownWorks() {
        //Arrange
        validAddressDTO.setTown("Gaia");
        String expectedResult = "Gaia";
        //Act
        String actualResult = validAddressDTO.getTown();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeGetNumberWorks() {

        //Arrange

        validAddressDTO.setNumber("300");

        //Act

        String actualResult = validAddressDTO.getNumber();

        //Assert

        assertEquals("300", actualResult);
    }

    @Test
    void seeIfGetCountryWorks() {

        //Arrange

        validAddressDTO.setCountry("Portugal");

        //Act

        String actualResult = validAddressDTO.getCountry();

        //Assert

        assertEquals("Portugal", actualResult);
    }

    @Test
    void seeIfEqualsWorks() {

        //Arrange

        AddressDTO validAddressDTO2 = new AddressDTO();
        validAddressDTO2.setTown("Porto");
        validAddressDTO2.setStreet("Rua R. Dr. António Bernardino de Almeida 431");
        validAddressDTO2.setZip("4200-072");

        AddressDTO validAddressDTO3 = new AddressDTO();
        validAddressDTO3.setTown("Lisbon");
        validAddressDTO3.setStreet("Rua R. Dr. António Bernardino de Almeida 431");
        validAddressDTO3.setZip("4200-072");

        AddressDTO validAddressDTO4 = new AddressDTO();
        validAddressDTO4.setTown("Porto");
        validAddressDTO4.setStreet("Rua R. Dr. António Bernardino de Almeida 431");
        validAddressDTO4.setZip("4200-073");

        //Act

        boolean actualResult1 = validAddressDTO.equals(validAddressDTO);
        boolean actualResult2 = validAddressDTO.equals(validAddressDTO2);
        boolean actualResult3 = validAddressDTO.equals(validAddressDTO3);
        boolean actualResult4 = validAddressDTO.equals(4D);
        boolean actualResult5 = validAddressDTO.equals(validAddressDTO4);

        //Assert

        assertTrue(actualResult1);
        assertTrue(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }

    @Test
    void seeIfHashcodeWorks() {
        //Assert

        assertEquals(1, validAddressDTO.hashCode());
    }

    @Test
    void seeIfHashcodeValidAddressWorks() {
        //Assert

        assertEquals(1, validAddressLocalDTO.hashCode());
    }

    @Test
    void seeIfEqualsWorksAddress() {

        //Arrange

        AddressLocalGeographicAreaIdDTO validAddressDTO2 = new AddressLocalGeographicAreaIdDTO();
        validAddressDTO2.setTown("Porto");
        validAddressDTO2.setGeographicAreaId(23L);
        validAddressDTO2.setStreet("Rua R. Dr. António Bernardino de Almeida 431");
        validAddressDTO2.setZip("4200-072");

        AddressLocalGeographicAreaIdDTO validAddressDTO3 = new AddressLocalGeographicAreaIdDTO();
        validAddressDTO3.setTown("Lisbon");
        validAddressDTO2.setGeographicAreaId(23L);
        validAddressDTO3.setStreet("Rua R. Dr. António Bernardino de Almeida 431");
        validAddressDTO3.setZip("4200-072");

        AddressLocalGeographicAreaIdDTO validAddressDTO4 = new AddressLocalGeographicAreaIdDTO();
        validAddressDTO4.setTown("Porto");
        validAddressDTO2.setGeographicAreaId(22L);
        validAddressDTO4.setStreet("Rua R. Dr. António Bernardino de Almeida 431");
        validAddressDTO4.setZip("4200-073");

        //Act

        boolean actualResult1 = validAddressLocalDTO.equals(validAddressLocalDTO);
        boolean actualResult2 = validAddressLocalDTO.equals(validAddressDTO2);
        boolean actualResult3 = validAddressLocalDTO.equals(validAddressDTO3);
        boolean actualResult4 = validAddressLocalDTO.equals(4D);
        boolean actualResult5 = validAddressLocalDTO.equals(validAddressDTO4);

        //Assert

        assertTrue(actualResult1);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
        assertFalse(actualResult4);
        assertFalse(actualResult5);
    }
}