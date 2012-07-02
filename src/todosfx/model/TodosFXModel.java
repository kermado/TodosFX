package todosfx.model;

import java.util.Iterator;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
        
        task.getComplete().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                updateCounts();
            }
        });
        
        tasks.add(task);
        updateCounts();
    }
    
    /**
     * Removes all completed tasks from the list.
     */
    public void clearCompleted() {
        Iterator<Task> tasksIterator = tasks.iterator();
        while (tasksIterator.hasNext()) {
            if (tasksIterator.next().getComplete().get()) {
                tasksIterator.remove();
            }
        }
    }
    
    /**
     * Returns the completeCount property.
     * 
     * @return completeCount property
     */
    public SimpleIntegerProperty getCompleteCount() {
        return completeCount;
    }
    
    /**
     * Returns the incompleteCount property.
     * 
     * @return incompleteCount property
     */
    public SimpleIntegerProperty getIncompleteCount() {
        return incompleteCount;
    }
    
    /**
     * Updates the completeCount and incompleteCount properties.
     */
    private void updateCounts() {
        int count = 0;
        for (Task task : tasks) {
            if (task.getComplete().get()) count++;
        }
        
        completeCount.set(count);
        incompleteCount.set(tasks.size() - count);
    }
    
}
