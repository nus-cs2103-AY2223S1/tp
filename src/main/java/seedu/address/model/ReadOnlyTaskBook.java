package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.task.Task;

public interface ReadOnlyTaskBook {

    ObservableList<Task> getTaskList();
}
