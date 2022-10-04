package hobbylist.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import hobbylist.commons.core.GuiSettings;
import hobbylist.model.activity.Activity;
import javafx.collections.ObservableList;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Activity> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' HobbyList file path.
     */
    Path getHobbyListFilePath();

    /**
     * Sets the user prefs' HobbyList file path.
     */
    void setHobbyListFilePath(Path hobbyListFilePath);

    /**
     * Replaces HobbyList data with the data in {@code hobbyList}.
     */
    void setHobbyList(ReadOnlyHobbyList hobbyList);

    /** Returns the HobbyList */
    ReadOnlyHobbyList getHobbyList();

    /**
     * Returns true if an activity with the same identity as {@code activity} exists in the HobbyList.
     */
    boolean hasActivity(Activity activity);

    /**
     * Deletes the given activity.
     * The person must exist in the HobbyList.
     */
    void deleteActivity(Activity target);

    /**
     * Adds the given activity.
     * {@code person} must not already exist in the HobbyList.
     */
    void addActivity(Activity activity);

    /**
     * Replaces the given activity {@code target} with {@code editedActivity}.
     * {@code target} must exist in the HobbyList.
     * The activity identity of {@code editedActivity} must not be the same as another existing activity in the
     * HobbyList.
     */
    void setActivity(Activity target, Activity editedActivity);

    /** Returns an unmodifiable view of the filtered activity list */
    ObservableList<Activity> getFilteredActivityList();

    /**
     * Updates the filter of the filtered activity list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredActivityList(Predicate<Activity> predicate);
}
