package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.profile.EventsAttending;
import seedu.address.model.profile.Profile;

/**
 * Represents the in-memory model of the NUScheduler data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final NuScheduler nuScheduler;
    private final UserPrefs userPrefs;
    private final FilteredList<Profile> filteredProfiles;
    private final FilteredList<Event> filteredEvents;

    /**
     * Initializes a ModelManager with the given nuScheduler and userPrefs.
     */
    public ModelManager(ReadOnlyNuScheduler nuScheduler, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(nuScheduler, userPrefs);

        logger.fine("Initializing with NUScheduler: " + nuScheduler + " and user prefs " + userPrefs);

        this.nuScheduler = new NuScheduler(nuScheduler);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredProfiles = new FilteredList<>(this.nuScheduler.getProfileList());
        filteredEvents = new FilteredList<>(this.nuScheduler.getEventList());
    }

    public ModelManager() {
        this(new NuScheduler(), new UserPrefs());
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
    public Path getNuSchedulerFilePath() {
        return userPrefs.getNuSchedulerFilePath();
    }

    @Override
    public void setNuSchedulerFilePath(Path nuSchedulerFilePath) {
        requireNonNull(nuSchedulerFilePath);
        userPrefs.setNuSchedulerFilePath(nuSchedulerFilePath);
    }

    //=========== NuScheduler ================================================================================

    @Override
    public void setNuScheduler(ReadOnlyNuScheduler nuScheduler) {
        this.nuScheduler.resetData(nuScheduler);
    }

    @Override
    public ReadOnlyNuScheduler getNuScheduler() {
        return nuScheduler;
    }

    //========== Profiles ====================================================================================

    @Override
    public boolean hasEmail(Profile profile) {
        requireNonNull(profile);
        return nuScheduler.hasEmail(profile);
    }

    @Override
    public boolean hasPhone(Profile profile) {
        requireNonNull(profile);
        return nuScheduler.hasPhone(profile);
    }

    @Override
    public boolean hasTelegram(Profile profile) {
        requireNonNull(profile);
        return nuScheduler.hasTelegram(profile);
    }

    @Override
    public void deleteProfile(Profile target) {
        nuScheduler.removeProfile(target);
    }

    @Override
    public void addProfile(Profile profile) {
        nuScheduler.addProfile(profile);
        updateFilteredProfileList(PREDICATE_SHOW_ALL_PROFILES);
    }

    @Override
    public void setProfile(Profile target, Profile editedProfile) {
        requireAllNonNull(target, editedProfile);
        nuScheduler.setProfile(target, editedProfile);
    }

    //========== Events ======================================================================================

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return nuScheduler.hasEvent(event);
    }

    @Override
    public void deleteEvent(Event target) {
        nuScheduler.removeEvent(target);
    }

    @Override
    public void removeEventFromAttendees(Event target, List<Profile> profilesToEdit) {
        requireAllNonNull(target, profilesToEdit);
        nuScheduler.removeEventFromAttendees(target, profilesToEdit);
    }

    @Override
    public void addEvent(Event event) {
        nuScheduler.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        nuScheduler.setEvent(target, editedEvent);
    }

    @Override
    public void setEventForAttendees(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        nuScheduler.setEventForAttendees(target, editedEvent);
    }

    @Override
    public void addEventAttendees(Event event, List<Profile> profilesToAdd) {
        requireAllNonNull(event, profilesToAdd);
        addEventToAttendees(event, profilesToAdd);
        nuScheduler.addEventAttendees(event, profilesToAdd);
    }

    @Override
    public void deleteEventAttendees(Event event, List<Profile> profilesToDelete) {
        requireAllNonNull(event, profilesToDelete);
        nuScheduler.deleteEventAttendees(event, profilesToDelete);
    }

    @Override
     public void addEventToAttendees(Event event, List<Profile> profilesToAddEventTo) {
        requireAllNonNull(event, profilesToAddEventTo);
        nuScheduler.addEventToAttendees(event, profilesToAddEventTo);
    }

    @Override
    public void refreshEvents(EventsAttending eventsToRefresh) {
        requireNonNull(eventsToRefresh);
        nuScheduler.refreshEvents(eventsToRefresh);
    }

    //=========== Filtered Profile List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Profile} backed by the internal list of
     * {@code versionedNuScheduler}
     */
    @Override
    public ObservableList<Profile> getFilteredProfileList() {
        return filteredProfiles;
    }

    @Override
    public void updateFilteredProfileList(Predicate<Profile> predicate) {
        requireNonNull(predicate);
        filteredProfiles.setPredicate(predicate);
    }

    //=========== Filtered Event List Accessors =============================================================

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
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
        return nuScheduler.equals(other.nuScheduler)
                && userPrefs.equals(other.userPrefs)
                && filteredProfiles.equals(other.filteredProfiles)
                && filteredEvents.equals(other.filteredEvents);
    }

}
