package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.profile.Profile;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Profile> PREDICATE_SHOW_ALL_PROFILES = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Event> PREDICATE_SHOW_ALL_EVENTS = unused -> true;

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
     * Returns the user prefs' NUS scheduler file path.
     */
    Path getNuSchedulerFilePath();

    /**
     * Sets the user prefs' NUS scheduler file path.
     */
    void setNuSchedulerFilePath(Path nuSchedulerFilePath);

    /**
     * Replaces NUS scheduler data with the data in {@code nuScheduler}.
     */
    void setNuScheduler(ReadOnlyNuScheduler nuScheduler);

    /** Returns the NuScheduler */
    ReadOnlyNuScheduler getNuScheduler();

    /**
     * Returns true if a profile with the same identity as {@code profile} exists in the NUS scheduler.
     */
    boolean hasProfile(Profile profile);

    /**
     * Deletes the given profile.
     * The profile must exist in the NUS scheduler.
     */
    void deleteProfile(Profile target);

    /**
     * Adds the given profile.
     * {@code profile} must not already exist in the NUS scheduler.
     */
    void addProfile(Profile profile);

    /**
     * Replaces the given profile {@code target} with {@code editedProfile}.
     * {@code target} must exist in the NUS scheduler.
     * The profile identity of {@code editedProfile} must not be the same as another existing
     * profile in the NUS scheduler.
     */
    void setProfile(Profile target, Profile editedProfile);

    /** Returns an unmodifiable view of the filtered profile list */
    ObservableList<Profile> getFilteredProfileList();

    /**
     * Updates the filter of the filtered profile list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProfileList(Predicate<Profile> predicate);

    /**
     * Returns true if an event with the same identity as {@code event} exists in the NUS scheduler.
     */
    boolean hasEvent(Event event);

    /**
     * Deletes the given event.
     * The event must exist in the NUS scheduler.
     */
    void deleteEvent(Event target);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the NUS scheduler.
     */
    void addEvent(Event event);

    /**
     * Replaces the given event {@code target} with {@code editedEvent}.
     * {@code target} must exist in the NUS scheduler.
     * The event identity of {@code editedEvent} must not be the same as another existing
     * event in the NUS scheduler.
     */
    void setEvent(Event target, Event editedEvent);

    /** Returns an unmodifiable view of the filtered event list */
    ObservableList<Event> getFilteredEventList();

    /**
     * Updates the filter of the filtered event list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEventList(Predicate<Event> predicate);
}
