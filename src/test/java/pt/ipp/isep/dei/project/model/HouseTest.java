package pt.ipp.isep.dei.project.model;


import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HouseTest {

    @Test
    void seeDistanceToSensor() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 6, 5),ga, roomList);
        Sensor s = new Sensor("sensor1", new TypeSensor("temperatura","Celsius"), new Local(4, 5, 5), new GregorianCalendar(2018, 10, 1).getTime());
        double result = house.calculateDistanceToSensor(s);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void seeMinDistanceToSensor() {
        RoomList roomList = new RoomList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperatura","Celsius"), new Local(4, 6, 5), new GregorianCalendar(2018, 10, 1).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperatura","Celsius"), new Local(4, 8, 5), new GregorianCalendar(2018, 10, 1).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"),2,3,new Local(4, 5, 50));
        ga.setSensorList(sensorList);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        double result = house.getMinDistanceFromHouseToSensor(ga);
        assertEquals(110.91871788829754, result, 0.01);
    }

    @Test
    void seeSensorWithMinDistance() {
        RoomList roomList = new RoomList();
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature","Celsius"), new Local(4, 6, 50), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature","Celsius"), new Local(4, 8, 50), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"),2,3,new Local(4, 5, 50));
        ga.setSensorList(sensorList);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga, house);
        assertEquals(s1, result);
    }

    @Test
    void seeSensorMinDistanceIfGAHasNoSensors() {
        GeographicArea ga = new GeographicArea("Portugal", new TypeArea("cidade"),2,3,new Local(4, 5, 50));
        RoomList roomList = new RoomList();
        SensorList sensorList1 = new SensorList();
        ga.setSensorList(sensorList1);
        Sensor s1 = new Sensor("sensor1", new TypeSensor("temperature","Celsius"), new Local(4, 8,50), new GregorianCalendar(4, 4, 4).getTime());
        Sensor s2 = new Sensor("sensor2", new TypeSensor("temperature","Celsius"), new Local(4, 6,50), new GregorianCalendar(4, 4, 4).getTime());
        SensorList sensorList = new SensorList();
        sensorList.addSensor(s1);
        sensorList.addSensor(s2);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5,50),ga, roomList);
        Sensor result = house.getSensorWithMinDistanceToHouse(ga, house);
        assertNull(result);
    }

    @Test
    void seeEqualToSameObject() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        House house2 = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);

        Boolean actualResult = house.equals(house2);

        assertEquals(true, actualResult);
    }

    @Test
    void seeEqualsToDifObject() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        House house2 = new House("casa de campo", "Rua do Sol", "4516", "Porto", new Local(4, 5, 50),ga, roomList);

        Boolean actualResult = house.equals(house2);

        assertEquals(false, actualResult);
    }

    @Test
    void seeEqualsToNullObject() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        House house2 = new House();

        Boolean actualResult = house.equals(house2);

        assertEquals(false, actualResult);
    }

    @Test
    void seeAddRoomToRoomList() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        RoomList rL1 = new RoomList();
        house.setRoomList(rL1);
        Room room = new Room("quarto", 1, 80,2,2);
        Boolean result = house.addRoomToRoomList(room);

        assertEquals(true, result);
    }

    @Test
    void seeAddRoomSameName() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        RoomList rL1 = new RoomList();
        house.setRoomList(rL1);
        Room room = new Room("quarto", 1, 80,2,2);
        Room room2 = new Room("quarto", 2, 80,2,2);
        house.addRoomToRoomList(room2);
        Boolean result = house.addRoomToRoomList(room);

        assertEquals(false, result);
    }

    @Test
    void seeIfRecognizesEmptyGridList () {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        EnergyGridList energyGridList = new EnergyGridList();
        house.setEGList(energyGridList);
        String expectedResult = "Invalid List - List is Empty\n";
        String actualResult = house.printGridList();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void seePrintedGridList (){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);
        EnergyGrid eg1 = new EnergyGrid("Rede",444);
        EnergyGrid eg2 = new EnergyGrid("Rede 2",555);
        EnergyGridList energyGridList = new EnergyGridList();
        energyGridList.addEnergyGridToEnergyGridList(eg1);
        energyGridList.addEnergyGridToEnergyGridList(eg2);
        house.setEGList(energyGridList);
        String expectedResult = "---------------\n" +
                "0) Designation: Rede | Max Power: 444.0\n" +
                "1) Designation: Rede 2 | Max Power: 555.0\n" +
                "---------------\n";
        String actualResult = house.printGridList();
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void seeSetId(){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        String expectedResult = "Casa do Porto";
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);

        //Act
        house.setId("Casa do Porto");
        String actualResult = house.getHouseId();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetStreet(){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        String expectedResult = "Rua da Praia";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);

        //Act
        house.setStreet("Rua da Praia");
        String actualResult = house.getStreet();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetZipCode(){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        String expectedResult = "6327-09";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);

        //Act
        house.setZip("6327-09");
        String actualResult = house.getZip();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetTown() {
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        String expectedResult = "Lisboa";
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);

        //Act
        house.setTown("Lisboa");
        String actualResult = house.getTown();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetHouseLocation(){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        Local expectedResult = new Local (7, 78, 50);

        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList);

        //Act
        house.setLocation(7, 78, 50);
        Local actualResult = house.getLocation();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetHouseLocation2(){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        //Arrange
        Local expectedResult = new Local (5, 35, 34);
        Local localHouse = new Local (8,9,1);
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", localHouse,ga, roomList);

        //Act
        localHouse.setAltitude(34);
        localHouse.setLatitude(5);
        localHouse.setLongitude(35);
        Local actualResult = house.getLocation();


        //Assert
        assertEquals(expectedResult, actualResult);
    }


    @Test
    void seeSetRoomList(){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        Room room1 = new Room("Quarto", 1,12,32,2);
        Room room2 = new Room("Cozinha", 1,42,42,2);
        roomList.addRoom(room1);
        roomList.addRoom(room2);

        //Arrange
        RoomList expectedResult = new RoomList();
        expectedResult.addRoom(room1);
        expectedResult.addRoom(room2);
        RoomList roomList1 = new RoomList();
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 50),ga, roomList1);

        //Act
        house.setRoomList(roomList);
        RoomList actualResult = house.getRoomList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetMotherArea(){
        //Arrange

        GeographicArea expectedResult = new GeographicArea();
        expectedResult.setId("Porto");
        expectedResult.setLocal(new Local (2,3,4));
        expectedResult.setTypeArea(new TypeArea("Cidade"));
        GeographicArea ga1 = new GeographicArea();
        ga1.setId("Porto");
        ga1.setLocal(new Local(2,3,4));
        ga1.setTypeArea(new TypeArea("Cidade"));

        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4),ga, roomList);

        //Act
        house.setmMotherArea(ga1);
        GeographicArea actualResult = house.getMotherArea();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seeSetEnergyGridList(){
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        EnergyGridList energyGridList = new EnergyGridList();
        EnergyGrid eg = new EnergyGrid("main grid", 25);
        energyGridList.addEnergyGridToEnergyGridList(eg);

        //Arrange
        EnergyGridList expectedResult = new EnergyGridList();
        expectedResult.addEnergyGridToEnergyGridList(eg);
        House house = new House("Casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4),ga, roomList);
        //Act
        house.setEGList(energyGridList);
        EnergyGridList actualResult = house.getEGList();

        //Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void seePrintHouse (){
        //ARRANGE
        GeographicArea ga = new GeographicArea();
        RoomList roomList = new RoomList();
        House house = new House("casa de praia", "Rua das Flores", "4512", "Porto", new Local(4, 5, 4),ga, roomList);
        String expectedResult = "casa de praia, Rua das Flores, 4512, Porto.\n";
        //ACT
        String actualResult = house.printHouse();
        //ASSERT
        assertEquals(expectedResult,actualResult);
    }



}

