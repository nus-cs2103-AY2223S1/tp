package taskbook.logic.commands.modelstubs;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import taskbook.commons.core.GuiSettings;
import taskbook.model.Model;
import taskbook.model.ReadOnlyTaskBook;
import taskbook.model.ReadOnlyUserPrefs;
import taskbook.model.person.Name;
import taskbook.model.person.Person;
import taskbook.model.task.Task;

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
    public Path getTaskBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTaskBookFilePath(Path taskBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void commitTaskBook() {
        // Intentionally empty method.
    }

    @Override
    public boolean canUndoTaskBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void undoTaskBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean canRedoTaskBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void redoTaskBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTaskBook(ReadOnlyTaskBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Person findPerson(Name name) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean canDeletePerson(Person person) {
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
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTaskListPredicate(Predicate<Task> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Task> getSortedTaskList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateSortedTaskList(Comparator<Task> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void resetSortedTaskList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonListPredicate(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateSortedPersonList(Comparator<Person> comparator) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void resetSortedPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getSortedPersonList() {
        throw new AssertionError("This method should not be called.");
    }

}
