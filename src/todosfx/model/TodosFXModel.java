package todosfx.model;

import java.util.Iterator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;

/**
 * Todos application model.
 * 
 * @author Omar Kermad
 * @version 1.0
 */
public class TodosFXModel {
    
    /**
     * Observable ArrayList of tasks.
     */
    private ObservableList<Task> tasks;
    
    /**
     * Number of tasks completed property.
     */
    private SimpleIntegerProperty completeCount;
    
    /**
     * Number of incomplete tasks property.
     */
    private SimpleIntegerProperty incompleteCount;
    
    /**
     * Model constructor.
     */
    public TodosFXModel() {
        tasks = FXCollections.observableArrayList();
        completeCount = new SimpleIntegerProperty();
        incompleteCount = new SimpleIntegerProperty();
        
        // Update counts when changes are made to the tasks list
        tasks.addListener(new ListChangeListener<Task>() {
            @Override
            public void onChanged(Change<? extends Task> change) {
                updateCounts();
            }
        });
    }
    
    /**
     * Returns the observable ArrayList of tasks.
     * 
     * @return observable ArrayList of tasks
     */
    public ObservableList<Task> getTasks() {
        return tasks;
    }
    
    /**
     * Adds a task to the list.
     * 
     * @param description task description
     */
    public void addTask(String description) {
        Task task = new Task(description);
        
        task.completeProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                updateCounts();
            }
        });
        
        tasks.add(task);
    }
    
    /**
     * Removes all completed tasks from the list.
     */
    public void clearCompleted() {
        Iterator<Task> tasksIterator = tasks.iterator();
        while (tasksIterator.hasNext()) {
            if (tasksIterator.next().getComplete()) {
                tasksIterator.remove();
            }
        }
    }
    
    /**
     * Returns the completeCount property.
     * 
     * @return completeCount property
     */
    public SimpleIntegerProperty completeCountProperty() {
        return completeCount;
    }
    
    /**
     * Returns the completeCount value.
     * 
     * @return number of tasks marked as complete 
     */
    public Integer getCompleteCount() {
        return completeCount.get();
    }
    
    /**
     * Returns the incompleteCount property.
     * 
     * @return incompleteCount property
     */
    public SimpleIntegerProperty incompleteCountProperty() {
        return incompleteCount;
    }
    
    /**
     * Returns the incompleteCount value.
     * 
     * @return number of tasks marked as incomplete
     */
    public Integer getIncompleteCount() {
        return incompleteCount.get();
    }
    
    /**
     * Updates the completeCount and incompleteCount properties.
     */
    private void updateCounts() {
        int count = 0;
        for (Task task : tasks) {
            if (task.getComplete()) count++;
        }
        
        completeCount.set(count);
        incompleteCount.set(tasks.size() - count);
    }
    
}
