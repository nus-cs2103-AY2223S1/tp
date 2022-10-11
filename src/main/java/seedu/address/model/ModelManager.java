package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * Represents the in-memory model of the client book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MyInsuRec myInsuRec;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;
    private final FilteredList<Meeting> filteredMeetings;
    private final FilteredList<Meeting> detailedMeetings;

    /**
     * Initializes a ModelManager with the given myInsuRec and userPrefs.
     */
    public ModelManager(ReadOnlyMyInsuRec myInsuRec, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(myInsuRec, userPrefs);

        logger.fine("Initializing with client book: " + myInsuRec + " and user prefs " + userPrefs);

        this.myInsuRec = new MyInsuRec(myInsuRec);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredClients = new FilteredList<>(this.myInsuRec.getClientList());
        filteredMeetings = new FilteredList<>(this.myInsuRec.getMeetingList());

        detailedMeetings = new FilteredList<>(this.myInsuRec.getMeetingList());
        this.setDetailedMeeting(null);
    }

    public ModelManager() {
        this(new MyInsuRec(), new UserPrefs());
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
    public Path getMyInsuRecFilePath() {
        return userPrefs.getMyInsuRecFilePath();
    }

    @Override
    public void setMyInsuRecFilePath(Path myInsuRecFilePath) {
        requireNonNull(myInsuRecFilePath);
        userPrefs.setMyInsuRecFilePath(myInsuRecFilePath);
    }

    //=========== myInsuRec ================================================================================

    @Override
    public void setMyInsuRec(ReadOnlyMyInsuRec myInsuRec) {
        this.myInsuRec.resetData(myInsuRec);
    }

    @Override
    public ReadOnlyMyInsuRec getMyInsuRec() {
        return myInsuRec;
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return myInsuRec.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) {
        myInsuRec.removeClient(target);
    }

    @Override
    public void addClient(Client client) {
        myInsuRec.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        myInsuRec.setClient(target, editedClient);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        requireNonNull(meeting);
        myInsuRec.removeMeeting(meeting);
    }

    @Override
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return myInsuRec.hasMeeting(meeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        requireNonNull(meeting);
        myInsuRec.addMeeting(meeting);
    }

    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedMyInsuRec}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClients;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    //=========== Filtered Meeting List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Meeting} backed by the internal list of
     * {@code versionedMyInsuRec}.
     */
    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return filteredMeetings;
    }

    @Override
    public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
        requireNonNull(predicate);
        filteredMeetings.setPredicate(predicate);
    }

    //=========== Detailed Meeting List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Meeting} to be viewed in details backed by the internal
     * list of {@code versionedMyInsuRec}. By default, no meetings are set to be viewed in detail.
     */
    @Override
    public ObservableList<Meeting> getDetailedMeetingList() {
        return detailedMeetings;
    }

    @Override
    public void setDetailedMeeting(Meeting meeting) {
        detailedMeetings.setPredicate(x -> x.equals(meeting));
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
        return myInsuRec.equals(other.myInsuRec)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients);
    }

}
