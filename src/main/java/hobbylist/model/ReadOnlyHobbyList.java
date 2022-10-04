package hobbylist.model;

import hobbylist.model.activity.Activity;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a HobbyList
 */
public interface ReadOnlyHobbyList {

    /**
     * Returns an unmodifiable view of the activity list.
     * This list will not contain any duplicate activities.
     */
    ObservableList<Activity> getActivityList();

}
