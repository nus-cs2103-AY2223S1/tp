package seedu.rc4hdb.logic;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.rc4hdb.commons.core.GuiSettings;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.ReadOnlyUserPrefs;
import seedu.rc4hdb.model.resident.Resident;

/**
 * A default model stub where methods fail.
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
    public Path getResidentBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setResidentBookFilePath(Path residentBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addResident(Resident resident) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setResidentBook(ReadOnlyResidentBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyResidentBook getResidentBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasResident(Resident resident) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteResident(Resident target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setResident(Resident target, Resident editedResident) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Resident> getFilteredResidentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredResidentList(Predicate<Resident> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<String> getObservableFields() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setObservableFields(List<String> observableFields) {
        throw new AssertionError("This method should not be called.");
    }
}
