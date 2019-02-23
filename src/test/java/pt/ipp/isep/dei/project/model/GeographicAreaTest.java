package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GeographicArea tests class.
 */

class GeographicAreaTest {


    @Test
    void seeIfGetSetTypeWorksIfSameAsGivenConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        TypeArea expectedResult = new TypeArea("Rua");
        TypeArea actualResult;
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        //Act
        c.setTypeArea(t1);
        actualResult = c.getTypeArea();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfConstructorSetsTypeArea() {
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        TypeArea expectedResult = new TypeArea("Rua");
        TypeArea actualResult;
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        actualResult = c.getTypeArea();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeIfGetSetTypeWorksIfDifferentOfGivenConstructor() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Freguesia");
        Local l1 = new Local(38, 7, 100);
        TypeArea actualResult;
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);

        //Act
        c.setTypeArea(t2);
        actualResult = c.getTypeArea();
        //Assert
        assertEquals(t2, actualResult);
    }

    @Test
    void seeIfEqualsSameObject() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        //Act
        c.setLocal(l1);
        boolean actualResult = c.equals(c);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfEqualsDifferentObject() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea d = new GeographicArea("Porto", t1, 2, 3, l1);
        //Act
        c.setLocal(l1);
        boolean actualResult = c.equals(d);

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetSetLocalIfSameAsConstructorWorksTypeAreaFALSE() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        TypeArea t2 = new TypeArea("Freguesia");
        Local l1 = new Local(38, 7, 100);
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea d = new GeographicArea("Porto", t2, 2, 3, l1);
        //Act
        c.setLocal(l1);
        boolean actualResult = c.equals(d);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfGetSetLocalIfSameAsConstructorWorksIDFALSE() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea d = new GeographicArea("Lisboa", t1, 2, 3, l1);
        //Act
        c.setLocal(l1);
        boolean actualResult = c.equals(d);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfGetSetLocalIfSameAsConstructorWorksLocalFALSE() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        Local l2 = new Local(30, 7, 100);
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea d = new GeographicArea("Porto", t1, 2, 3, l2);
        //Act
        c.setLocal(l1);
        boolean actualResult = c.equals(d);

        //Assert
        assertFalse(actualResult);
    }

    @Test
    void seeIfGetSetLocalIfSameAsConstructorWorksTypeArea() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        //Act
        c.setLocal(l1);
        boolean actualResult = c.getTypeArea().getTypeOfGeographicArea().equals("Rua");

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetSetLocalIfSameAsConstructorWorksID() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        //Act
        c.setLocal(l1);
        boolean actualResult = c.getId().equals("Porto");

        //Assert
        assertTrue(actualResult);
    }

    @Test
    void seeIfGetSetLocalIfDifferentOfConstructorWorks() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(38, 7, 100);
        Local l2 = new Local(65, 56, 100);
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        Local actualResult;
        //Act
        c.setLocal(l2);
        actualResult = c.getLocal();

        //Assert
        assertEquals(l2, actualResult);
    }

    @Test
    void seeIfGetSetSensorList() {
        //Arrange
        TypeArea t1 = new TypeArea("Rua");
        Local l1 = new Local(21, 38, 40);
        Sensor s1 = new Sensor("Vento", new TypeSensor("Atmosférico", "km/h"), new Local(12, 31, 21), new GregorianCalendar(2010, 8, 9).getTime());
        SensorList list1 = new SensorList(s1);
        SensorList actualResult;
        GeographicArea c = new GeographicArea("Porto", t1, 2, 3, l1);
        //Act
        c.setSensorList(list1);
        actualResult = c.getSensorList();
        //Assert
        assertEquals(list1, actualResult);
    }

    @Test
    void seeIfAreaIsContainedInArea() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 15, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertTrue(result);
    }

    @Test
    void seeIfAreaIsContainedInAreaEnhanced() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 20, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 20, l1);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertTrue(result);
    }

        @Test
    void seeIfAreaIsContainedInAreaEnhanced2() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 20, 20, l1);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro - Na linha mais abaixo
    @Test
    void seeIfAreaIsContainedInAreaBeta() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 20, 20, l1);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfAreaIsContainedInAreaEnhanced3() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro - Igual anterior mas ao contrario
    @Test
    void seeIfAreaIsContainedInAreaEnhanced4() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro - Só muda longitude
    @Test
    void seeIfAreaIsContainedInAreaEnhanced5() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, so muda longitude
    @Test
    void seeIfAreaIsContainedInAreaEnhanced6() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 20, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, so muda longitude
    @Test
    void seeIfAreaIsContainedInAreaEnhanced7() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 20, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 20, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro só muda longitude e length
    @Test
    void seeIfAreaIsContainedInAreaEnhanced8() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 25, 25, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro
    @Test
    void seeIfAreaIsContainedInAreaFails() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 2, 3, l1);
        //Act
        ga1.setWidth(11);
        ga1.setLength(20);
        ga2.setWidth(10);
        ga2.setLength(21);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, muda longitude e length
    @Test
    void seeIfAreaIsContainedInAreaEnhanced9() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 25, 25, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, só muda tamanho
    @Test
    void seeIfAreaIsContainedInAreaFails2() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 10, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 11, 21, l1);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, muda longitude
    @Test
    void seeIfAreaIsContainedInArea2() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 2, 3, l2);
        //Act
        ga1.setWidth(20);
        ga1.setLength(10);
        ga2.setWidth(10);
        ga2.setLength(5);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, muda longitude
    @Test
    void seeIfAreaIsContainedInArea3() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 15, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 2, 3, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 2, 3, l2);
        //Act
        ga1.setWidth(20);
        ga1.setLength(20);
        ga2.setWidth(10);
        ga2.setLength(10);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertTrue(result);
    }

    //Testes areaIsContained exactamente iguais aos anteriores, mas mudará só latitude em vez de longitude

    // Centro Esquerdo
    @Test
    void seeIfAreaIsContainedInAreaEnhanced10() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(15, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro - Igual anterior mas ao contrario
    @Test
    void seeIfAreaIsContainedInAreaEnhanced11() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(15, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }


    //Centro - Só muda latitude menor
    @Test
    void seeIfAreaIsContainedInAreaEnhanced12() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(15, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, so muda lat
    @Test
    void seeIfAreaIsContainedInAreaEnhanced13() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(15, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 20, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, so muda lat
    @Test
    void seeIfAreaIsContainedInAreaEnhanced14() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(15, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 20, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 20, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, muda lat
    @Test
    void seeIfAreaIsContainedInAreaEnhanced15() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(15, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 25, 25, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, muda lat
    @Test
    void seeIfAreaIsContainedInAreaEnhanced16() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(15, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 25, 25, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, muda lat
    @Test
    void seeIfAreaIsContainedInArea4() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(15, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 3, 2, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 3, 2, l2);
        //Act
        ga1.setWidth(10);
        ga1.setLength(20);
        ga2.setWidth(5);
        ga2.setLength(10);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, muda lat
    @Test
    void seeIfAreaIsContainedInArea5() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(15, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 3, 2, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 3, 2, l2);
        //Act
        ga1.setWidth(20);
        ga1.setLength(20);
        ga2.setWidth(10);
        ga2.setLength(10);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);
        //Assert
        assertTrue(result);
    }

    //LONG BOT E LAT TOP RESOLVIDOS

    //LAT BOT
    // Centro DIREITO
    @Test
    void seeIfAreaIsContainedInAreaEnhanced17() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(25, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro - Igual anterior mas ao contrario
    @Test
    void seeIfAreaIsContainedInAreaEnhanced18() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(25, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }


    //Centro - Só muda latitude maior
    @Test
    void seeIfAreaIsContainedInAreaEnhanced19() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(25, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, so muda lat
    @Test
    void seeIfAreaIsContainedInAreaEnhanced20() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(25, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 20, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, so muda lat
    @Test
    void seeIfAreaIsContainedInAreaEnhanced21() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(25, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 20, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 20, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, muda lat
    @Test
    void seeIfAreaIsContainedInAreaEnhanced22() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(25, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 25, 25, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, muda lat
    @Test
    void seeIfAreaIsContainedInAreaEnhanced23() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(25, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 25, 25, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, muda lat
    @Test
    void seeIfAreaIsContainedInArea6() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(25, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 3, 2, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 3, 2, l2);
        //Act
        ga1.setWidth(10);
        ga1.setLength(20);
        ga2.setWidth(5);
        ga2.setLength(10);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    //Centro, muda lat
    @Test
    void seeIfAreaIsContainedInArea7() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(25, 20, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 3, 2, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 3, 2, l2);
        //Act
        ga1.setWidth(20);
        ga1.setLength(20);
        ga2.setWidth(10);
        ga2.setLength(10);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);
        //Assert
        assertTrue(result);
    }

    //LONG TOP
    @Test
    void seeIfAreaIsContainedInAreaEnhanced24() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 25, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfAreaIsContainedInAreaEnhanced25() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 25, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfAreaIsContainedInAreaEnhanced26() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 25, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfAreaIsContainedInAreaEnhanced27() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 25, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 20, 21, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfAreaIsContainedInAreaEnhanced28() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 25, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 20, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 20, 20, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfAreaIsContainedInAreaEnhanced29() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 25, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 25, 25, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 19, 21, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfAreaIsContainedInAreaEnhanced30() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 25, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 19, 20, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 25, 25, l2);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfAreaIsContainedInArea8() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 25, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 3, 2, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 3, 2, l2);
        //Act
        ga1.setWidth(20);
        ga1.setLength(10);
        ga2.setWidth(10);
        ga2.setLength(5);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);

        //Assert
        assertFalse(result);
    }

    @Test
    void seeIfAreaIsContainedInArea9() {
        //Arrange
        TypeArea t1 = new TypeArea("Terriola");
        TypeArea t2 = new TypeArea("Cidade");
        Local l1 = new Local(20, 25, 100);
        Local l2 = new Local(20, 20, 100);
        GeographicArea ga1 = new GeographicArea("Porto", t1, 3, 2, l1);
        GeographicArea ga2 = new GeographicArea("Porto", t2, 3, 2, l2);
        //Act
        ga1.setWidth(20);
        ga1.setLength(20);
        ga2.setWidth(10);
        ga2.setLength(10);
        boolean result = ga1.isAreaContainedInAnotherArea(ga1, ga2);
        //Assert
        assertTrue(result);
    }
    //FIM DE TESTES SEE IF CONTAINED

    @Test
    void seeIfSetNameWorksNullAndThrowsStringMessage() {
        //Arrange
        String name = "Porto";
        TypeArea t1 = new TypeArea("rua");
        Local l1 = new Local(11, 12, 100);
        GeographicArea ga1 = new GeographicArea(name, t1, 2, 3, l1);

        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            ga1.setId(null);
        });

        //Assert
        assertEquals("Please Insert Valid Name", exception.getMessage());
    }

    @Test
    void seeIfSetNameWorksEmptyAndThrowsStringException() {
        //Arrange
        String name = "Porto";
        TypeArea t1 = new TypeArea("rua");
        Local l1 = new Local(11, 12, 100);
        GeographicArea ga1 = new GeographicArea(name, t1, 2, 3, l1);
        //Act
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            ga1.setId("");
        });
        //Assert
        assertEquals("Please Insert Valid Name", exception.getMessage());
    }


    @Test
    void seeIfWeSetMotherArea() {
        //Arrange
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 2, 5, new Local(22, 23, 100));
        GeographicArea ga2 = new GeographicArea("Portugal", new TypeArea("País"), 6, 7, new Local(22, 17, 100));
        ga1.setMotherArea(ga2);

        //Act
        GeographicArea actualResult = ga1.getMotherArea();

        //Assert
        assertEquals(ga2, actualResult);
    }

    @Test
    void seeIfSetDescription() {
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 2, 5, new Local(22, 23, 100));
        ga1.setDescription("cidade do Porto");

        String actualResult = ga1.getDescription();

        assertEquals("cidade do Porto", actualResult);
    }

    @Test
    void ensureThatAObjectIsNotAInstanceOf() {
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 2, 5, new Local(22, 23, 100));
        Sensor s1 = new Sensor("Sensor 1", new TypeSensor("Temperatura", "Celsius"), new Local(22, 22, 100), new GregorianCalendar(2018, 11, 25).getTime());

        boolean actualResult = ga1.equals(s1);

        assertFalse(actualResult);
    }


    @Test
    void ensureThatAreaIsContained() {
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 10, 20, new Local(22, 23, 100));
        GeographicArea ga2 = new GeographicArea("Portugal", new TypeArea("Cidade"), 200, 521, new Local(22, 23, 100));
        ga1.setMotherArea(ga2);
        ga1.setMotherArea(ga2);
        boolean actualResult = ga1.checkIfAreaIsContained(ga1, ga2);

        assertTrue(actualResult);
    }

    @Test
    void ensureThatAreaIsNotContained() {
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 10, 20, new Local(22, 23, 100));
        GeographicArea ga2 = new GeographicArea("Portugal", new TypeArea("Cidade"), 200, 521, new Local(22, 23, 100));
        ga1.setMotherArea(ga2);
        ga1.setMotherArea(ga2);
        boolean actualResult = ga1.checkIfAreaIsContained(ga2, ga1);

        assertFalse(actualResult);
    }

    @Test
    void ensureThatGrandsonAreaIsContainedInGrandmotherArea() {
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Cidade"), 2, 4, new Local(22, 22, 100));
        GeographicArea ga2 = new GeographicArea("Portugal", new TypeArea("Cidade"), 20, 40, new Local(22, 22, 100));
        GeographicArea ga3 = new GeographicArea("Europa", new TypeArea("Cidade"), 200, 400, new Local(22, 22, 100));
        ga1.setMotherArea(ga2);
        ga2.setMotherArea(ga3);
        boolean actualResult = ga1.checkIfAreaIsContained(ga1, ga3);

        assertTrue(actualResult);
    }

    @Test
    void seeAddSensorToGA() {
        GeographicArea ga = new GeographicArea("porto", new TypeArea("cidade"), 2, 3, new Local(4, 4, 100));
        Sensor sensor1 = new Sensor("Sensor 1", new TypeSensor("Temperature", "Celsius"), new GregorianCalendar(2018, 10, 25).getTime());
        Sensor sensor2 = new Sensor("Sensor 1", new TypeSensor("Temperature", "Celsius"), new GregorianCalendar(2018, 10, 25).getTime());
        Sensor sensor3 = new Sensor("Sensor 3", new TypeSensor("Temperature", "Celsius"), new GregorianCalendar(2018, 10, 25).getTime());

        boolean result1 = ga.addSensorToSensorList(sensor1);
        boolean result2 = ga.addSensorToSensorList(sensor2);
        boolean result3 = ga.addSensorToSensorList(sensor3);

        assertTrue(result1);
        assertFalse(result2);
        assertTrue(result3);
    }

    @Test
    void testBuildGeographicAreaString() {
        //Arrange
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Rua"), 16, 17, new Local(16, 17, 18));
        //Act
        String expectedResult = "Porto, Rua, 16.0º lat, 17.0º long\n";
        String actualResult = ga1.buildGeographicAreaString();
        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void hashCodeDummyTest() {
        GeographicArea ga1 = new GeographicArea("Porto", new TypeArea("Rua"), 16, 17, new Local(16, 17, 18));
        int expectedResult = 1;
        int actualResult = ga1.hashCode();
        Assertions.assertEquals(expectedResult, actualResult);
    }
}

