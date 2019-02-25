package pt.ipp.isep.dei.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class that groups a number of Sensors.
 */

public class SensorList {

    private List<Sensor> sensors;

    /**
     * SensorList() empty constructor that initializes an ArrayList of Sensors.
     */
    public SensorList() {
        this.sensors = new ArrayList<>();
    }


    /**
     * Constructor to always create an Array of Sensors.
     * @param sensorToAdd the selected sensor.
     */
    public SensorList(Sensor sensorToAdd) {
        sensors = new ArrayList<>();
        sensors.add(sensorToAdd);
    }

    /**
     * Method to Add a sensor only if it's not contained in the list already.
     *
     * @param sensorToAdd is the sensor we want to add to the sensorList.
     * @return true if sensor was successfully added to the SensorList, false otherwise.
     */

    public boolean addSensor(Sensor sensorToAdd) {
        if (!(sensors.contains(sensorToAdd))) {
            sensors.add(sensorToAdd);
        }
        return false;
    }

    /**
     * Gettter (list of sensors)
     *
     * @return list of sensors
     */
    public List<Sensor> getListOfSensors() {
        return this.sensors;
    }

    /**
     * Method that goes through the sensor list and looks for the sensor
     * that was most recently used (that as the most recent reading).
     *
     * @return the most recently used sensor
     */
    public Sensor getMostRecentlyUsedSensor() {
        Date d1 = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        try {
            d1 = sd.parse("01/00/1900");
        } catch (ParseException c) {
            c.printStackTrace();
        }
        Sensor error = new Sensor("emptySensor", new TypeSensor("type", " "), d1);
        if (this.sensors.isEmpty()) {
            return error;
        }
        Sensor mostRecent = this.sensors.get(0);
        Date recent = mostRecent.getReadingList().getMostRecentReading().getDate();
        for (Sensor s : this.sensors) {
            Date test = s.getReadingList().getMostRecentReading().getDate();
            if (recent.before(test)) {
                recent = test;
                mostRecent = s;
            }
        }
        return mostRecent;
    }

    /**
     * @param name String of the sensor we wish to compare with the existent sensors on the sensor list.
     * @return builds a list of sensors with the same type as the one introduced as parameter.
     */

    SensorList getSensorListByType(String name) {
        SensorList containedTypeSensors = new SensorList();
        for (Sensor sensor : this.sensors) {
            if (name.equals(sensor.getTypeSensor().getName())) {
                containedTypeSensors.addSensor(sensor);
            }
        }
        return containedTypeSensors;
    }

    /**
     * Method to print a Whole Sensor List.
     * It will print the attributes needed to check if a Sensor is different from another Sensor
     * (name, type of Sensor and Units)
     * @param sensorList the list of sensors.
     * @return a string of the sensors contained in the list.
     */
    public String buildSensorWholeListString(SensorList sensorList) {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));

        if (sensorList.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }

        for (int i = 0; i < sensorList.size(); i++) {
            Sensor aux = sensorList.get(i);
            result.append(i).append(") Name: ").append(aux.getName()).append(" | ");
            result.append("Type: ").append(aux.getTypeSensor().getName()).append("\n");
        }
        result.append("---------------\n");
        return result.toString();
    }

    /**
     * Method that goes through every sensor in the sensor list and gets
     * every reading within that sensor. In the end we will get a Reading list
     * that contains every reading from every sensor of the sensor list.
     *
     * @return a list with all readings from sensor list
     **/
    public ReadingList getReadings() {
        ReadingList finalList = new ReadingList();
        if (this.sensors.isEmpty()) {
            return finalList;
        }
        for (Sensor s : this.sensors) {
            for (Reading r : s.getReadingList().getListOfReadings()) {
                finalList.addReading(r);
            }
        }
        return finalList;
    }

    /**
     * Method checks if sensor list is empty.
     *
     * @return true if empty, false otherwise.
     **/
    public boolean isEmpty() {
        return this.sensors.isEmpty();
    }

    /**
     * Checks the sensor list size and returns the size as int.\
     *
     * @return SensorList size as int
     **/
    public int size() {
        return this.sensors.size();
    }

    /**
     * This method receives an index as parameter and gets a sensor from sensor list.
     * @param index the index of the Sensor.
     * @return returns sensor that corresponds to index.
     */
    public Sensor get(int index) {
        return this.sensors.get(index);
    }

    /**
     * Method 'equals' for comparison between objects of the same class
     *
     * @param testObject is the object we want to test.
     * @return true if it's equal, false otherwise.
     */

    @Override
    public boolean equals(Object testObject) {
        if (this == testObject) {
            return true;
        }
        if (!(testObject instanceof SensorList)) {
            return false;
        }
        SensorList list = (SensorList) testObject;
        return Arrays.equals(this.getListOfSensors().toArray(), list.getListOfSensors().toArray());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}
