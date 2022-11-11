package seedu.address.logic.commands.stubs;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTruthTable;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.team.Link;
import seedu.address.model.team.Task;
import seedu.address.model.team.Team;

/**
 * A default model stub that have all of the methods failing.
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
    public Path getTruthTableFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTruthTableFilePath(Path truthTableFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTruthTable(ReadOnlyTruthTable newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyTruthTable getTruthTable() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Team getTeam() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTeam(Team teamToSet) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTeams(List<Team> teams) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addTeam(Team teamToAdd) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTeam(Team teamToDelete) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Team> getTeamList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredMemberList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredMembersList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObjectProperty<Team> getTeamAsProperty() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasLink(Link link) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addLink(Link link) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setLink(Link target, Link editedLink) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteLink(Link link) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Link> getLinkList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        throw new AssertionError("This method should not be called.");
    }
}



