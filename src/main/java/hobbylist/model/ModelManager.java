package hobbylist.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import hobbylist.commons.core.GuiSettings;
import hobbylist.commons.core.LogsCenter;
import hobbylist.commons.util.CollectionUtil;
import hobbylist.model.activity.Activity;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the HobbyList data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final HobbyList hobbyList;
    private final UserPrefs userPrefs;
    private final FilteredList<Activity> filteredActivities;

    /**
     * Initializes a ModelManager with the given hobbyList and userPrefs.
     */
    public ModelManager(ReadOnlyHobbyList hobbyList, ReadOnlyUserPrefs userPrefs) {
        CollectionUtil.requireAllNonNull(hobbyList, userPrefs);

        logger.fine("Initializing with HobbyList: " + hobbyList + " and user prefs " + userPrefs);

        this.hobbyList = new HobbyList(hobbyList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredActivities = new FilteredList<>(this.hobbyList.getActivityList());
    }

    public ModelManager() {
        this(new HobbyList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getHobbyListFilePath() {
        return userPrefs.getHobbyListFilePath();
    }

    @Override
    public void setHobbyListFilePath(Path hobbyListFilePath) {
        requireNonNull(hobbyListFilePath);
        userPrefs.setHobbyListFilePath(hobbyListFilePath);
    }

    //=========== HobbyList ================================================================================

    @Override
    public void setHobbyList(ReadOnlyHobbyList hobbyList) {
        this.hobbyList.resetData(hobbyList);
    }

    @Override
    public ReadOnlyHobbyList getHobbyList() {
        return hobbyList;
    }

    @Override
    public boolean hasActivity(Activity activity) {
        requireNonNull(activity);
        return hobbyList.hasActivity(activity);
    }

    @Override
    public void deleteActivity(Activity target) {
        hobbyList.removeActivity(target);
    }

    @Override
    public void addActivity(Activity activity) {
        hobbyList.addActivity(activity);
        updateFilteredActivityList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setActivity(Activity target, Activity editedActivity) {
        CollectionUtil.requireAllNonNull(target, editedActivity);

        hobbyList.setActivity(target, editedActivity);
    }

    //=========== Filtered Activity List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Activity} backed by the internal list of
     * {@code versionedHobbyList}
     */
    @Override
    public ObservableList<Activity> getFilteredActivityList() {
        return filteredActivities;
    }

    @Override
    public void updateFilteredActivityList(Predicate<Activity> predicate) {
        requireNonNull(predicate);
        filteredActivities.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return hobbyList.equals(other.hobbyList)
                && userPrefs.equals(other.userPrefs)
                && filteredActivities.equals(other.filteredActivities);
    }

}
