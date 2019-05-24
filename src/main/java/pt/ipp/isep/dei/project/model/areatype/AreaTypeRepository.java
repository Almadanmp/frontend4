package pt.ipp.isep.dei.project.model.areatype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.controller.controllercli.utils.LogUtils;
import pt.ipp.isep.dei.project.repository.AreaTypeCrudRepo;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that groups a number of Types of Geographical Areas.
 */
@Service
public class AreaTypeRepository {

    @Autowired
    AreaTypeCrudRepo repository;

    List<AreaType> getAreaTypes() {
        return repository.findAll();
    }

    /**
     * This method creates a new Type of Geographic Area and adds it to a list.
     *
     * @param name String of the new Area Type that one wishes to create and addWithoutPersisting to a list.
     * @return true or false depending on if it adds the type to the list or not.
     */
    public AreaType create(String name) {
        return new AreaType(name);
    }

    /**
     * This method adds a previously stated Area Type to the repository.
     *
     * @param type Type of Geographic Area one wishes to saveSensor.
     * @return true or false if the saveSensor was successful
     */
    public boolean add(AreaType type) {
        Optional<AreaType> value = repository.findByName(type.getName());
        if (value.isPresent()) {
            return false;
        }
        repository.save(type);
        return true;
    }


    /**
     * This method builds a string of all the individual members of Area Type Repository
     *
     * @return builds a string of all the individual members of the Area Type Repository.
     */
    public String getAllAsString() {
        StringBuilder result = new StringBuilder("---------------\n");
        List<AreaType> typeAreas = getAreaTypes();
        if (isEmpty()) {
            return "Invalid List - List is Empty\n";
        }
        for (AreaType ta : typeAreas) {
            result.append("Name: ").append(ta.getName()).append(" \n");
        }
        result.append("---------------\n");
        return result.toString();
    }


    /**
     * This method checks if type area list is empty.*
     *
     * @return true if list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }


    /**
     * Method to get the TypeArea Repository Size
     *
     * @return repository size
     */
    public int size() {
        return repository.findAll().size();
    }


    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param id selected id
     * @return Type Area corresponding to the given id
     */
    public AreaType getById(String id) {
        Optional<AreaType> value = repository.findById(id);
        return value.orElse(null);
    }

    /**
     * Method to get a TypeArea from the Repository through a given id
     *
     * @param name selected name
     * @return Type Area corresponding to the given id
     */
    public AreaType getAreaTypeByName(String name) {
        Logger logger = LogUtils.getLogger("areaTypeLogger", "resources/logs/areaTypeLogHtml.html", Level.FINE);
        Optional<AreaType> value = repository.findByName(name);
        if (!(value.isPresent())) {
            String message = "The area Type " + name + " does not yet exist in the Data Base. Please create the Area" +
                    "Type first.";
            logger.fine(message);
            LogUtils.closeHandlers(logger);
            return null;
        } else {
            LogUtils.closeHandlers(logger);
            return value.orElseGet(() -> new AreaType(name));
        }
    }
}