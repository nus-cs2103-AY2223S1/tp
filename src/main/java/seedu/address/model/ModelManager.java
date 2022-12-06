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
import seedu.address.model.client.UniqueClientList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final JeeqTracker jeeqTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClientsList;

    /**
     * Initializes a ModelManager with the given jeeqTracker and userPrefs.
     */
    public ModelManager(ReadOnlyJeeqTracker jeeqTracker, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(jeeqTracker, userPrefs);

        logger.fine("Initializing with address book: " + jeeqTracker + " and user prefs " + userPrefs);

        this.jeeqTracker = new JeeqTracker(jeeqTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredClientsList = new FilteredList<>(this.jeeqTracker.getClientList());
    }

    public ModelManager() {
        this(new JeeqTracker(), new UserPrefs());
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
    public Path getJeeqTrackerFilePath() {
        return userPrefs.getJeeqTrackerFilePath();
    }

    @Override
    public void setJeeqTrackerFilePath(Path jeeqTrackerFilePath) {
        requireNonNull(jeeqTrackerFilePath);
        userPrefs.setJeeqTrackerFilePath(jeeqTrackerFilePath);
    }

    //=========== JeeqTracker ================================================================================

    @Override
    public void setJeeqTracker(ReadOnlyJeeqTracker jeeqTracker) {
        this.jeeqTracker.resetData(jeeqTracker);
    }

    @Override
    public ReadOnlyJeeqTracker getJeeqTracker() {
        return jeeqTracker;
    }

    @Override
    public boolean hasClient(Client client) {
        requireNonNull(client);
        return jeeqTracker.hasClient(client);
    }

    @Override
    public void deleteClient(Client target) {
        jeeqTracker.removeClient(target);
    }

    @Override
    public void addClient(Client client) {
        jeeqTracker.addClient(client);
        updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
    }

    @Override
    public void setClient(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        jeeqTracker.setClient(target, editedClient);
    }

    //=========== Filtered Client List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Client} backed by the internal list of
     * {@code versionedJeeqTracker}
     */
    @Override
    public ObservableList<Client> getFilteredClientList() {
        return filteredClientsList;
    }

    @Override
    public void updateFilteredClientList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClientsList.setPredicate(predicate);
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
        return jeeqTracker.equals(other.jeeqTracker)
                && userPrefs.equals(other.userPrefs)
                && filteredClientsList.equals(other.filteredClientsList);
    }

    /**
     * Calculate the total transactions made with clients in the given filtered client list.
     *
     * @param filteredClientList list of clients.
     * @return double value of total transactions.
     */
    public double calculateTotalTransaction(ObservableList<Client> filteredClientList) {
        UniqueClientList filteredList = new UniqueClientList();
        for (Client client : filteredClientList) {
            filteredList.add(client);
        }
        return filteredList.calculateTotalTransaction();
    }

}
