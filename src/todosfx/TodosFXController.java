package todosfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import todosfx.model.Task;
import todosfx.model.TodosFXModel;

/**
 * Todos application controller.
 * 
 * All our GUI components are declared in here.  We bind properties of these
 * components to properties contained in the model in order to keep the GUI
 * in sync with the data.  The controller also contains a number of actions
 * that are triggered by the GUI components.
 * 
 * @author Omar Kermad
 * @version 1.0
 */
public class TodosFXController implements Initializable {
    
    /**
     * Reference to the model.
     */
    private TodosFXModel model;

    /**
     * Clear completed tasks button.
     */
    @FXML
    private Button clearCompleted; // Value injected by FXMLLoader

    /**
     * New task text field.
     */
    @FXML
    private TextField newTask; // Value injected by FXMLLoader

    /**
     * Remaining tasks label.
     */
    @FXML
    private Label remainingTasks; // Value injected by FXMLLoader

    /**
     * ListView GUI component that will contain a list of the tasks.
     */
    @FXML
    private ListView<Task> tasksList; // Value injected by FXMLLoader

    /**
     * This method is called by the FXMLLoader when initialization is complete.
     * 
     * @param fxmlFileLocation
     * @param resources 
     */
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert clearCompleted != null : "fx:id=\"clearCompleted\" was not injected: check your FXML file 'TodosFX.fxml'.";
        assert newTask != null : "fx:id=\"newTask\" was not injected: check your FXML file 'TodosFX.fxml'.";
        assert remainingTasks != null : "fx:id=\"remainingTasks\" was not injected: check your FXML file 'TodosFX.fxml'.";
        assert tasksList != null : "fx:id=\"tasksList\" was not injected: check your FXML file 'TodosFX.fxml'.";

        // Setup the model.
        model = new TodosFXModel();
        
        // Configure the various GUI components.
        configureTasksList();
        configureRemainingTasks();
        configureClearCompleted();
    }
    
    /**
     * New task action.
     * 
     * This action is fired when creating a new task.
     * 
     * @param event 
     */
    public void newTaskFired(ActionEvent event) {
        // Add the task to the list in the model.
        model.addTask(newTask.getText());
        
        // Clear the new task text field.
        newTask.clear();
    }
    
    /**
     * Clear completed tasks action.
     * 
     * This action is fired on clicking the "Clear completed tasks" button.
     * 
     * @param event 
     */
    public void clearCompletedFired(ActionEvent event) {
        model.clearCompleted();
    }
    
    /**
     * Configures the ListView for the tasks.
     * 
     * Populates and binds the ListView with the observable tasks list.
     * This keeps the ListView GUI component in sync with the model data.
     */
    public void configureTasksList() {
        // Populate and bind the ListView with the observable tasks list.
        tasksList.setItems(model.getTasks());
        
        // Set the cell factory to use TaskCell. This will insert checkboxes
        // for each task into the ListView.
        tasksList.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
            @Override
            public ListCell<Task> call(ListView<Task> p) {
                return new TaskCell();
            }
        });
    }
    
    /**
     * Configures the remaining tasks Label.
     * 
     * Listens to changes on the incompleteCount property in the model and
     * updates the remaining tasks label with the new count.
     */
    public void configureRemainingTasks() {
        model.incompleteCountProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                remainingTasks.setText(newValue.intValue() + " tasks remaining");
            }
        });
    }
    
    /**
     * Configures the clear completed tasks Button.
     * 
     * Listens to changes on the completeCount property in the model and enables
     * the "clear completed tasks" button if one or more tasks have been marked
     * as completed.
     */
    public void configureClearCompleted() {
        model.completeCountProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                clearCompleted.setDisable(newValue.intValue() <= 0);
            }
        });
    }
    
    /**
     * ListCell cell factory.
     * 
     * Used by the ListView component to set the contents of each cell in the
     * list. Binds the checkbox's "selected" property to the task's
     * "complete" property.  Similarly, binds the checkbox's "text" property
     * to the task's "description" property.
     */
    static class TaskCell extends ListCell<Task> {
        @Override
        public void updateItem(Task item, boolean empty) {
            super.updateItem(item, empty);
            
            if (item != null) {
                CheckBox checkbox = new CheckBox();
                checkbox.textProperty().bindBidirectional(item.descriptionProperty());
                checkbox.selectedProperty().bindBidirectional(item.completeProperty());
                setGraphic(checkbox);
            }
        }
    }

}
