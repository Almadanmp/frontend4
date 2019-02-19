package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class TypeSensorList {
    private List<TypeSensor> typeSensorList;

    public TypeSensorList() {
        typeSensorList = new ArrayList<>();
    }

    /**Method receives a type Sensor, checks if it already exists in list
     * and adds it in case it does not exist in list.
     * @return true in case the type sensor is added, false otherwise
     * **/
    public boolean add(TypeSensor typeSensor){
        if(!typeSensorList.contains(typeSensor)){
            typeSensorList.add(typeSensor);
            return true;
        }
        return false;
    }

    /**Method returns a list with all sensor types.
     * @return List that contains sensor types
     * **/
    public List<TypeSensor> getTypeSensorList(){
        return this.typeSensorList;
    }

    /**
     * This method prints all sensor types in a type sensor list.
     *
     * @return a string of sensor types in a list
     */
    public String buildString() {
        StringBuilder result = new StringBuilder(new StringBuilder("---------------\n"));
        if (typeSensorList.isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (int i = 0; i < typeSensorList.size(); i++) {
            TypeSensor aux = typeSensorList.get(i);
            result.append(i).append(") Name: ").append(aux.getName()).append(" | ");
            result.append("Unit: ").append(aux.getUnits()).append("\n");
        }
        result.append("---------------\n");
        return result.toString();
    }
}
