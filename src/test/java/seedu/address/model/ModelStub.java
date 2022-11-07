package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.event.Event;
import seedu.address.model.profile.EventsAttending;
import seedu.address.model.profile.Profile;

/**
 * A default model stub that has all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getNuSchedulerFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setNuSchedulerFilePath(Path nuSchedulerFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addProfile(Profile profile) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addEvent(Event profile) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setNuScheduler(ReadOnlyNuScheduler newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyNuScheduler getNuScheduler() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasEmail(Profile profile) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPhone(Profile profile) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTelegram(Profile profile) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasEvent(Event event) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteProfile(Profile target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setEventForAttendees(Event target, Event editedEvent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteEvent(Event target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setProfile(Profile target, Profile editedProfile) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setEvent(Event target, Event editedProfile) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addEventAttendees(Event event, List<Profile> profilesToAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteEventAttendees(Event event, List<Profile> profilesToDelete) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addEventToAttendees(Event event, List<Profile> profilesToAddEventTo) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void removeEventFromAttendees(Event target, List<Profile> profilesToEdit) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void refreshEvents(EventsAttending eventsToRefresh) {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public ObservableList<Profile> getFilteredProfileList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredProfileList(Predicate<Profile> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
