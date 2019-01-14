package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controller.RoomConfigurationController;
import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.model.devicetypes.DeviceType;

import java.util.Scanner;

class RoomConfigurationUI {

    private House mHouse;
    private GeographicArea mGeoArea;
    private RoomConfigurationController mRoomConfigurationController;
    private Room mRoom;
    private Sensor mSensor;
    private Device mDevice;
    private double mNominalPower;
    private double mVolumeOfWater;
    private double mHotWaterTemperature;
    private double mColdWaterTemperature;
    private double mPerformanceRatio;
    private double mCapacity;
    private String mDeviceName;
    private String mRoomName;
    private SensorList mSensorList;
    private String mSensorName;
    private String mStringRequestRoom = "You have chosen the following Room:";
    private String mStringRequestDevice = "You have chosen the following Device:";
    private String mStringChosenSensor = "You have chosen the following Sensor:";

    RoomConfigurationUI() {
        this.mRoomConfigurationController = new RoomConfigurationController();
    }

    void run(House house) {
        if (house == null) {
            System.out.println("Please create a House before you continue.");
            return;
        }
        this.mHouse = house;
        RoomList roomList = mHouse.getRoomList();
        if (roomList == null || roomList.getRoomList().isEmpty()) {
            System.out.println("There are no available rooms in the house. Please add a room to continue.");
            return;
        }
        this.mGeoArea = house.getMotherArea();
        this.mSensorList = mGeoArea.getSensorList();
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        boolean activeInput = true;
        int option;

        System.out.println("--------------\n");
        System.out.println("Room Configuration\n");
        System.out.println("--------------\n");
        while (activeInput) {
            printRoomConfigMenu();
            option = inputUtils.readInputNumberAsInt();
            switch (option) {
                case 1: //US201
                    getInputRoomByList();
                    getRoomDeviceList(mRoom);
                    activeInput = false;
                    break;
                case 2: //US210
                    activeInput = false;
                    break;
                case 3: //215
                    getInputRoomByList();
                    getInputDeviceByList();
                    getInputDeviceCharacteristicsUS215();
                    updateDeviceUS215();
                    displayDeviceUS215();
                    activeInput = false;
                    break;
                case 4: //US230
                    getInputRoomByList();
                    getRoomNominalPower();
                    activeInput = false;
                    break;
                case 5: //US250
                    getInputRoomByList();
                    getInputSensorByListRoom();
                    activeInput = false;
                    break;
                case 6: //US253
                    if (mGeoArea.getSensorList().getSensorList().isEmpty() || mGeoArea.getSensorList() == null) {
                        System.out.println("There's no available sensors in the Geographical Area");
                        return;
                    }
                    getInputRoomByList();
                    getInputSensorByList();
                    activeInput = false;
                    break;
                case 0:
                    return;
                default:
                    System.out.println(utils.invalidOption);
                    break;
            }
        }
    }


    //  SHARED METHODS

    private void getInputRoomByList() {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (mHouse.getRoomList().getRoomList().isEmpty()) {
            System.out.println("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = true;
        System.out.println("Please select one of the existing Rooms in the House: ");
        while (activeInput) {
            System.out.println(mRoomConfigurationController.printRoomList(mHouse));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mHouse.getRoomList().getRoomList().size()) {
                this.mRoom = mHouse.getRoomList().getRoomList().get(aux);
                this.mRoomName = mRoom.getRoomName();
                System.out.println(mStringRequestRoom);
                System.out.println(mRoomConfigurationController.printRoom(mRoom));
                activeInput = false;
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    private void getInputDeviceByList() {
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (mRoom.getDeviceList().isEmpty()) {
            System.out.println("Invalid Device List - List Is Empty\n");
            return;
        }
        boolean activeInput = true;
        System.out.println("Please select one of the existing Devices in the selected Room: ");
        while (activeInput) {
            System.out.println(mRoomConfigurationController.printDeviceList(mRoom));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mRoom.getDeviceList().size()) {
                this.mDevice = mRoom.getDeviceList().get(aux);
                this.mDeviceName = mDevice.getName();
                System.out.println(mStringRequestDevice);
                System.out.println(mRoomConfigurationController.printDevice(mDevice));
                activeInput = false;
            } else {
                System.out.println(utils.invalidOption);
            }

        }
    }
    /*US201 As an administrator, I want to get a list of all devices in a room, so that I can configure them.*/

    private void getRoomDeviceList(Room room) {
        System.out.println("Available Devices in Room");
        System.out.println(mRoomConfigurationController.printDeviceList(room));
    }

    /*US215 As an Administrator, I want to edit the configuration of an existing device, so that I
    can reconfigure it. - CARINA ALAS*/

    private void getInputDeviceCharacteristicsUS215() {

        Scanner scanner = new Scanner(System.in);

        // get device name
        System.out.print("Please, type the new name of the Device: ");
        this.mDeviceName = scanner.nextLine();

        //get room
        mRoom.removeDevice(mDevice);
        InputUtils inputUtils = new InputUtils();
        UtilsUI utils = new UtilsUI();
        if (mHouse.getRoomList().getRoomList().isEmpty()) {
            System.out.println("Invalid Room List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select one of the existing rooms on the selected House to which you want to add your Device: ");
        while (!activeInput) {
            System.out.println(mRoomConfigurationController.printRoomList(mHouse));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mHouse.getRoomList().getRoomList().size()) {
                this.mRoom = mHouse.getRoomList().getRoomList().get(aux);
                this.mRoomName = mRoom.getRoomName();
                System.out.println(mStringRequestRoom);
                System.out.println(mRoomConfigurationController.printRoom(mRoom));
                activeInput = true;
                mRoom.addDevice(mDevice);
                mDevice.setmParentRoom(mRoom);
            } else {
                System.out.println(utils.invalidOption);
            }
        }
        //get nominal power
        String onlyNumbers = "Please,try again. Only numbers this time:";
        System.out.print("Please, type the new Nominal Power: ");
        while (!scanner.hasNextDouble()) {
            System.out.println(onlyNumbers);
            scanner.next();
        }
        this.mNominalPower = scanner.nextDouble();

        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            System.out.print("Please, type the new Water Volume that the Water Heater will heat: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mVolumeOfWater = scanner.nextDouble();

            System.out.print("Please, type the Maximum Temperature of the water in the Water Heater: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mHotWaterTemperature = scanner.nextDouble();

            System.out.print("Please, type the Minimum Temperature of the water in the Water Heater: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mColdWaterTemperature = scanner.nextDouble();

            System.out.println(
                    "Do you wish to alter the performance ration?\n" +
                            "1) Yes;\n" +
                            "2) No;\n");
            int option = inputUtils.readInputNumberAsInt();

            switch (option) {
                case 1:
                    System.out.print("Please, type the Performance Ration of the Water Heater: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.println(onlyNumbers);
                        scanner.next();
                    }
                    this.mPerformanceRatio = scanner.nextDouble();
                    mRoomConfigurationController.setPerformanceRatio(mPerformanceRatio,mDevice);
                    break;
                case 2:
                    break;
            }

        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE) {
            System.out.print("Please, type the new Capacity in Kg for the Washing Machine: ");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mCapacity = scanner.nextDouble();
        }
        if (mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            System.out.print("Please, type the new Capacity in Kg for the Dishwasher:");
            while (!scanner.hasNextDouble()) {
                System.out.println(onlyNumbers);
                scanner.next();
            }
            this.mCapacity = scanner.nextDouble();
        }

        }

    /*
    US215 As an Administrator, I want to edit the configuration of an existing device, so that I
    can reconfigure it.*/
    private void updateDeviceUS215() {
        mRoomConfigurationController.setDeviceName(mDeviceName, mDevice);
        mRoomConfigurationController.setNominalPower(mNominalPower, mDevice);
        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            mRoomConfigurationController.setVolumeWater(mVolumeOfWater, mDevice);
            mRoomConfigurationController.setHotWaterTemp(mHotWaterTemperature, mDevice);
            mRoomConfigurationController.setColdWaterTemp(mColdWaterTemperature,mDevice);
        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE) {
            mRoomConfigurationController.setCapacity(mCapacity, mDevice);
        }
        if (mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            mRoomConfigurationController.setCapacity(mCapacity, mDevice);
        }
        }

    /*
    US215 As an Administrator, I want to edit the configuration of an existing device, so that I
    can reconfigure it.*/
    private void displayDeviceUS215() {
        System.out.println("You have successfully changed the Device name to " + mDeviceName + ". \n"
                + "The Nominal Power is: " + mNominalPower + " kW. \n" + "And the room is " + mRoom.getRoomName() + "\n");
        if (mDevice.getDeviceType() == DeviceType.WATER_HEATER) {
            System.out.println("The volume of water is " + mVolumeOfWater + " L, the Max Water Temperature " +
                    mHotWaterTemperature + " ºC, the Min Temperature is "+mColdWaterTemperature+" ºC.");
        }
        if (mDevice.getDeviceType() == DeviceType.WASHING_MACHINE || mDevice.getDeviceType() == DeviceType.DISHWASHER) {
            System.out.println("The capacity is "+mCapacity+" Kg.");
        }
    }

    /*USER STORY 230 - As a Room Owner [or Power User, or Administrator], I want to know the total
    nominal power of a room, i.e. the sum of the nominal power of all devices in the
    room. - TERESA VARELA */

    private void getRoomNominalPower() {
        double roomNominalPower = mRoomConfigurationController.getRoomNominalPower(this.mRoom);
        System.out.println("This room has a total nominal power of "+ roomNominalPower + " kW.\nThis results " +
                "from the sum of the nominal power of all devices in the room.");
    }

    /*US250 - As an Administrator, I want to get a list of all sensors in a room, so that I can configure them.
    MIGUEL ORTIGAO*/

    private void getInputSensorByListRoom() {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        RoomConfigurationController ctrl = new RoomConfigurationController();
        mSensorList = mRoom.getmRoomSensorList();
        if (mSensorList.getSensorList().isEmpty()) {
            System.out.print("Invalid Sensor List - List Is Empty\n");
            return;
        }
        boolean activeInput = false;
        System.out.println("Please select a Sensor from the Chosen Room in order to Configure it: ");
        while (!activeInput) {
            System.out.println(ctrl.printSensorList(mSensorList));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mSensorList.getSensorList().size()) {
                this.mSensor = mSensorList.getSensorList().get(aux);
                this.mSensorName = mSensor.getName();
                activeInput = true;
                System.out.println(mStringChosenSensor);
                System.out.println(mRoomConfigurationController.printSensor(mSensor));
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    /* USER STORY 253 - As an Administrator, I want to add a new sensor to a room from the list of available
    sensor types, in order to configure it. - ANDRÉ RUA */

    private void getInputSensorByList() {
        UtilsUI utils = new UtilsUI();
        InputUtils inputUtils = new InputUtils();
        RoomConfigurationController ctrl = new RoomConfigurationController();
        if (mGeoArea.getSensorList().getSensorList().isEmpty()) {
            System.out.print("Invalid Sensor List - List Is Empty\n");
            return;
        }
        boolean activeInput = true;
        System.out.println("Please select a sensor from the House Geographical Area from the list: ");
        while (activeInput) {
            System.out.println(ctrl.printSensorList(mSensorList));
            int aux = inputUtils.readInputNumberAsInt();
            if (aux >= 0 && aux < mSensorList.getSensorList().size()) {
                this.mSensor = mSensorList.getSensorList().get(aux);
                this.mSensorName = mSensor.getName();
                activeInput = false;
                System.out.println(mStringChosenSensor);
                System.out.println(mRoomConfigurationController.printSensor(mSensor));
                System.out.print("Sensor " + mSensor.getName() + " was successfully added to " + this.mRoomName);
            } else {
                System.out.println(utils.invalidOption);
            }
        }
    }

    /* UI SPECIFIC METHODS - NOT USED ON USER STORIES */

    private void printRoomConfigMenu() {
        System.out.println("Room Configuration Options:\n");
        System.out.println("1) Get a list of all devices in a room. (US201)");
        System.out.println("2) Add a new device to the room from the list of device types (US210)");
        System.out.println("3) Edit the configuration of an existing device (US215)");
        System.out.println("4) Get the total nominal power of a room (US230)");
        System.out.println("5) Get a list of all sensors in a room (US250)");
        System.out.println("6) Add a sensor to a room from the list of sensor types (US253)");
        System.out.println("0) (Return to main menu)\n");
    }
}




