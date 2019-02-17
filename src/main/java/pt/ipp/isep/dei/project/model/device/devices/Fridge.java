package pt.ipp.isep.dei.project.model.device.devices;

import pt.ipp.isep.dei.project.model.Metered;
import pt.ipp.isep.dei.project.model.device.Log;
import pt.ipp.isep.dei.project.model.device.LogList;
import pt.ipp.isep.dei.project.model.device.devicespecs.FridgeSpec;
import pt.ipp.isep.dei.project.model.device.programs.ProgramList;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Fridge implements Device, Metered {
    public static final String NOMINAL_POWER = "Nominal Power";

    private String mName;
    private double mNominalPower;
    private String mType = "Fridge";
    private FridgeSpec mDeviceSpecs;
    private boolean mActive;
    private LogList mLogList;


    public Fridge(FridgeSpec mDeviceSpecs) {
        this.mDeviceSpecs = mDeviceSpecs;
        this.mActive = true;
        mLogList = new LogList();
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getType() {
        return this.mType;
    }

    public void setNominalPower(double nominalPower) {
        this.mNominalPower = nominalPower;
    }

    public double getNominalPower() {
        return this.mNominalPower;
    }

    public boolean isActive() {
        return this.mActive;
    }

    public boolean deactivate() {
        if (isActive()) {
            this.mActive = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean isProgrammable() {
        return false;
    }

    public ProgramList getProgramList() throws IncompatibleClassChangeError {
        throw new IncompatibleClassChangeError("ERROR: Unable to get list. Device is not programmable.");
    }


    public String buildDeviceString() {
        return "The device Name is " + this.mName + ", and its NominalPower is " + this.mNominalPower + " kW.\n";
    }

    /**
     * This method returns the Device LogList.
     *
     * @return Device LogList.
     */
    public LogList getLogList() {
        return mLogList;
    }

    /**
     * This method adds a Log to the device LogList, if the Log isn't already in the LogList.
     *
     * @param log - Parameter which will be used to add to the Device LogList.
     * @return true if log was added
     */
    public boolean addLog(Log log) {
        if (!(mLogList.getLogList().contains(log)) && this.mActive) {
            mLogList.getLogList().add(log);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method determines the amount of data logs that fall within a given time interval.
     *
     * @param initialTime is the start time of the interval.
     * @param finalTime   is the end time of the interval.
     * @return is the number of valid data logs in the given interval.
     */
    public int countLogsInInterval(Date initialTime, Date finalTime) {
        int counter = 0;
        for (Log l : mLogList.getLogList()) {
            if ((l.getInitialDate().after(initialTime) || l.getInitialDate().equals(initialTime)) &&
                    ((l.getFinalDate().before(finalTime)) || l.getFinalDate().equals(finalTime))) {
                counter++;
            }
        }
        return counter;
    }

    public LogList getLogsInInterval(Date startDate, Date endDate) {
        LogList result = new LogList();
        for (Log l : this.getLogList().getLogList()) {
            if ((l.getInitialDate().after(startDate) || l.getInitialDate().equals(startDate)) &&
                    ((l.getFinalDate().before(endDate)) || l.getFinalDate().equals(endDate))) {
                result.addLog(l);
            }
        }
        return result;
    }

    /**
     * This method checks the Logs registered in a periods which are totally contained in the defined interval.
     *
     * @param initialTime - Beginning of the interval
     * @param finalTime   - Ending of the interval
     * @return total consumption within the defined interval
     */
    public double getConsumptionWithinGivenInterval(Date initialTime, Date finalTime) {
        double result = 0;
        for (Log l : mLogList.getLogList()) {
            if ((l.getInitialDate().after(initialTime) || l.getInitialDate().equals(initialTime)) &&
                    ((l.getFinalDate().before(finalTime)) || l.getFinalDate().equals(finalTime))) {
                result += l.getValue();
            }
        }
        return result;
    }

    /**
     * Energy consumption (daily) = annual energy consumption / 365 (kWh)
     *
     * @param time
     * @return
     */
    public double getEnergyConsumption(float time) {
        return mNominalPower * time;
    }


    // WRAPPER METHODS TO DEVICE SPECS
    public List<String> getAttributeNames() {
        return mDeviceSpecs.getAttributeNames();
    }

    public Object getAttributeValue(String attributeName) {
        return mDeviceSpecs.getAttributeValue(attributeName);
    }

    public boolean setAttributeValue(String attributeName, Object attributeValue) {
        return mDeviceSpecs.setAttributeValue(attributeName, attributeValue);
    }

    public Object getAttributeUnit(String attributeName) {
        return mDeviceSpecs.getAttributeUnit(attributeName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device device = (Device) o;
        return Objects.equals(mName, device.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

}