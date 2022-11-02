package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.team.Link;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;

/**
 * Represents the in-memory model of the TruthTable data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TruthTable truthTable;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given truthTable and userPrefs.
     */
    public ModelManager(ReadOnlyTruthTable truthTable, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(truthTable, userPrefs);

        logger.fine("Initializing with TruthTable: " + truthTable + " and user prefs " + userPrefs);

        this.truthTable = new TruthTable(truthTable);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.truthTable.getPersonList());
    }

    public ModelManager() {
        this(TruthTable.createNewTruthTable(), new UserPrefs());
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
    public Path getTruthTableFilePath() {
        return userPrefs.getTruthTableFilePath();
    }

    @Override
    public void setTruthTableFilePath(Path truthTableFilePath) {
        requireNonNull(truthTableFilePath);
        userPrefs.setTruthTableFilePath(truthTableFilePath);
    }

    //=========== TruthTable ================================================================================

    @Override
    public void setTruthTable(ReadOnlyTruthTable truthTable) {
        this.truthTable.resetData(truthTable);
    }

    @Override
    public ReadOnlyTruthTable getTruthTable() {
        return truthTable;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return truthTable.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        truthTable.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        truthTable.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        truthTable.setPerson(target, editedPerson);
    }

    //========== Team Accessors ===============================================================================
    @Override
    public Team getTeam() {
        return truthTable.getTeam();
    }

    @Override
    public ObjectProperty<Team> getTeamAsProperty() {
        return truthTable.getTeamAsProperty();
    }

    @Override
    public void setTeam(Team teamToSet) {
        truthTable.setTeam(teamToSet);
    }

    @Override
    public void setTeams(List<Team> teams) {
        truthTable.setTeams(teams);
    }

    @Override
    public void addTeam(Team teamToAdd) {
        truthTable.addTeam(teamToAdd);
    }

    @Override
    public void deleteTeam(Team teamToDelete) {
        truthTable.deleteTeam(teamToDelete);
    }

    @Override
    public ObservableList<Team> getTeamList() {
        return truthTable.getTeamList();
    }
    //=========== Link Accessors =============================================================================

    @Override
    public boolean hasLink(Link link) {
        return truthTable.hasLink(link);
    }

    @Override
    public void addLink(Link link) {
        truthTable.addLink(link);
    }

    @Override
    public void setLink(Link target, Link editedLink) {
        truthTable.setLink(target, editedLink);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        truthTable.setTask(target, editedTask);
    }

    @Override
    public void deleteLink(Link link) {
        truthTable.deleteLink(link);
    }

    @Override
    public ObservableList<Link> getLinkList() {
        return truthTable.getTeam().getLinkList();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedTruthTable}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedTruthTable} based on the specified predicate.
     */
    @Override
    public ObservableList<Person> getFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        return filteredPersons.filtered(predicate);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public ObservableList<Person> getFilteredMemberList() {
        return truthTable.getTeam().getFilteredMemberList();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return truthTable.getTeam().getFilteredTaskList();
    }

    @Override
    public void updateFilteredMembersList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        truthTable.getTeam().updateFilteredMembersList(predicate);
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        truthTable.getTeam().updateFilteredTaskList(predicate);
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
        return truthTable.equals(other.truthTable)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
