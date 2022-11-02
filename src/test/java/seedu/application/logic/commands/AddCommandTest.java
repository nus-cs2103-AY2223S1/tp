package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.application.commons.core.GuiSettings;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.model.ApplicationBook;
import seedu.application.model.Model;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.ReadOnlyUserPrefs;
import seedu.application.model.SortSetting;
import seedu.application.model.application.Application;
import seedu.application.model.application.interview.Interview;
import seedu.application.testutil.ApplicationBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_applicationAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingApplicationAdded modelStub = new ModelStubAcceptingApplicationAdded();
        Application validApplication = new ApplicationBuilder().build();

        CommandResult commandResult = new AddCommand(validApplication).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validApplication), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validApplication), modelStub.applicationsAdded);
    }

    @Test
    public void execute_duplicateApplication_throwsCommandException() {
        Application validApplication = new ApplicationBuilder().build();
        AddCommand addCommand = new AddCommand(validApplication);
        ModelStub modelStub = new ModelStubWithApplication(validApplication);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_APPLICATION, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Application google = new ApplicationBuilder().withCompany("Google").build();
        Application shopee = new ApplicationBuilder().withCompany("Shopee").build();
        AddCommand addGoogleCommand = new AddCommand(google);
        AddCommand addShopeeCommand = new AddCommand(shopee);

        // same object -> returns true
        assertTrue(addGoogleCommand.equals(addGoogleCommand));

        // same values -> returns true
        AddCommand addGoogleCommandCopy = new AddCommand(google);
        assertTrue(addGoogleCommand.equals(addGoogleCommandCopy));

        // different types -> returns false
        assertFalse(addGoogleCommand.equals(1));

        // null -> returns false
        assertFalse(addGoogleCommand.equals(null));

        // different application -> returns false
        assertFalse(addGoogleCommand.equals(addShopeeCommand));
    }

    /**
     * A default model stub that has all of the methods failing.
     */
    private class ModelStub implements Model {
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
        public Path getApplicationBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicationBookFilePath(Path applicationBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public SortSetting getSortSetting() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Application> getAllApplicationsInBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplication(Application application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplicationBook(ReadOnlyApplicationBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyApplicationBook getApplicationBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasApplication(Application application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSameInterviewTime(Interview interview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSameInterviewTime(Application application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSameInterviewTimeExcludeSelf(Interview interview, Application application) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void deleteApplication(Application target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void archiveApplication(Application target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void retrieveApplication(Application target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplication(Application target, Application editedApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Application> getFilteredApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Application> getApplicationsWithUpcomingInterviewList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Application> getApplicationListWithInterview() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicationList(Predicate<Application> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateApplicationListWithInterview(Predicate<Application> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        public void sortApplicationListByCompany(boolean shouldReverse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortApplicationListByPosition(boolean shouldReverse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortApplicationListByDate(boolean shouldReverse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortApplicationListByInterview(boolean shouldReverse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitApplicationBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoApplicationBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoApplicationBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoApplicationBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoApplicationBook() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single application.
     */
    private class ModelStubWithApplication extends ModelStub {
        private final Application application;

        ModelStubWithApplication(Application application) {
            requireNonNull(application);
            this.application = application;
        }

        @Override
        public boolean hasApplication(Application application) {
            requireNonNull(application);
            return this.application.isSameApplication(application);
        }
    }

    /**
     * A Model stub that always accepts the application being added.
     */
    private class ModelStubAcceptingApplicationAdded extends ModelStub {
        final ArrayList<Application> applicationsAdded = new ArrayList<>();

        @Override
        public boolean hasApplication(Application application) {
            requireNonNull(application);
            return applicationsAdded.stream().anyMatch(application::isSameApplication);
        }

        @Override
        public boolean hasSameInterviewTime(Application application) {
            requireNonNull(application);
            if (application.getInterview().isEmpty()) {
                return false;
            }
            for (int i = 0; i < applicationsAdded.size() - 1; i++) {
                if (applicationsAdded.get(i).getInterview().isEmpty()) {
                    continue;
                }
                if (applicationsAdded.get(i).getInterview().get().isOnSameTime(application.getInterview().get())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public void addApplication(Application application) {
            requireNonNull(application);
            applicationsAdded.add(application);
        }

        @Override
        public ReadOnlyApplicationBook getApplicationBook() {
            return new ApplicationBook();
        }
    }

}
