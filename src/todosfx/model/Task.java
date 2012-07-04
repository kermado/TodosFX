package todosfx.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Represents a single task.
 * 
 * Provides getters and setters for the description and complete properties.
 * Note that properties are used to allow us to bind these fields to GUI
 * components in the controller.
 * 
 * @author Omar Kermad
 * @version 1.0
 */
public class Task {
    
    /**
     * Description for the task.
     */
    private SimpleStringProperty description;
    
    /**
     * True if the task has been completed, false otherwise.
     */
    private SimpleBooleanProperty complete;
    
    /**
     * Task constructor.
     * 
     * @param description task description
     */
    public Task(String description) {
        this.description = new SimpleStringProperty(description);
        this.complete = new SimpleBooleanProperty();
    }
    
    /**
     * Returns the task description property.
     * 
     * @return description property 
     */
    public SimpleStringProperty descriptionProperty() {
        return description;
    }
    
    /**
     * Returns the task description value.
     * 
     * @return description text 
     */
    public String getDescription() {
        return description.get();
    }
    
    /**
     * Sets the task description.
     * 
     * @param description task description
     */
    public void setDescription(String description) {
        this.description.set(description);
    }
    
    /**
     * Returns the task complete property.
     * 
     * @return complete property 
     */
    public SimpleBooleanProperty completeProperty() {
        return complete;
    }
    
    /**
     * Returns the task complete value.
     * 
     * @return complete value
     */
    public Boolean getComplete() {
        return complete.get();
    }
    
    /**
     * Sets the task completion status.
     * 
     * @param complete true to mark the task as complete, false otherwise
     */
    public void setComplete(Boolean complete) {
        this.complete.set(complete);
    }
    
}
