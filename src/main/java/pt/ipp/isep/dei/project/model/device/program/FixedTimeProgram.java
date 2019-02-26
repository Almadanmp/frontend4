package pt.ipp.isep.dei.project.model.device.program;

import java.util.ArrayList;
import java.util.List;

public class FixedTimeProgram implements Program {

    public static final String DURATION = "Duration";
    public static final String ENERGY_CONSUMPTION = "Energy Consumption";

    private String programName;
    private double programDuration;
    private double energyConsumption;

    public FixedTimeProgram() {
    }

    public FixedTimeProgram(String name, double duration, double energyConsumption) {
        setDuration(duration);
        setProgramName(name);
        setEnergyConsumption(energyConsumption);
    }

    public String buildString() {
        String result;
        result = "- The FixedTimeProgram Name is " + getProgramName() + ", its Duration is " +
                getDuration() + " hours and its Energy Consumption is " + getEnergyConsumption() + ".\n";
        return result;
    }

    public void setProgramName(String name) {
        this.programName = name;
    }

    public void setDuration(double duration) {
        this.programDuration = duration;
    }

    public void setEnergyConsumption(double energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public String getProgramName() {
        return this.programName;
    }

    public double getDuration() {
        return this.programDuration;
    }

    public double getEnergyConsumption() {
        return this.energyConsumption;
    }

    public List<String> getAttributeNames() {
        List<String> result = new ArrayList<>();
        result.add(DURATION);
        result.add(ENERGY_CONSUMPTION);
        return result;
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        switch (attributeName) {
            case DURATION:
                if (attributeValue instanceof Double) {
                    this.programDuration = (Double) attributeValue;
                    return true;
                }
                return false;
            case ENERGY_CONSUMPTION:
                if (attributeValue instanceof Double) {
                    this.energyConsumption = (Double) attributeValue;
                    return true;
                }
                return false;

            default:
                return false;
        }
    }

    public Object getAttributeUnit(String attributeName) {
        switch (attributeName) {
            case DURATION:
                return "min";
            case ENERGY_CONSUMPTION:
                return "kWh";
            default:
                return false;
        }
    }


    public Object getAttributeValue(String attributeName) {
        switch (attributeName) {
            case DURATION:
                return programDuration;
            case ENERGY_CONSUMPTION:
                return energyConsumption;
            default:
                return 0;
        }
    }

}
