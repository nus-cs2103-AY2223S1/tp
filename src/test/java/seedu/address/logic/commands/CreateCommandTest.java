package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.getTypicalJeeqTracker;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static seedu.address.testutil.TypicalPoc.ALICE;
import static seedu.address.testutil.TypicalPoc.AMY;
import static seedu.address.testutil.TypicalPoc.BOB;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyJeeqTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Company;
import seedu.address.model.client.UniqueCompanyList;
import seedu.address.testutil.CompanyBuilder;



class CreateCommandTest {

    private final Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());

    @Test
    public void constructor_nullPoc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(INDEX_FIRST_COMPANY, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null, ALICE));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(INDEX_FIRST_COMPANY, ALICE).execute(null));
    }

    @Test
    public void execute_invalidCompanyIndex_failure() {
        assertThrows(CommandException.class, () -> new CreateCommand(Index.fromZeroBased(
                model.getFilteredCompanyList().size() + 10), ALICE).execute(model));

        Company validCompany = new CompanyBuilder().build();
        Model modelStub = new ModelStub(validCompany);

        assertThrows(CommandException.class, () -> new CreateCommand(Index.fromZeroBased(
                modelStub.getFilteredCompanyList().size() + 10), ALICE).execute(model));
    }

    @Test
    public void execute_duplicatePoc_failure() {
        Company validCompany = new CompanyBuilder().build();
        validCompany.addPoc(AMY);
        Model modelStub = new ModelStub(validCompany);

        assertThrows(CommandException.class, () -> new CreateCommand(INDEX_FIRST_COMPANY, AMY).execute(modelStub));
    }

    @Test
    public void execute_addPoc_success() throws Exception {
        Company validCompany = new CompanyBuilder().build();
        Model modelStub = new ModelStub(validCompany);
        CreateCommand createCommand = new CreateCommand(INDEX_FIRST_COMPANY, AMY);
        createCommand.execute(modelStub);
        assertTrue(modelStub.getFilteredCompanyList().get(0).hasPoc(AMY));
    }


    @Test
    public void equals() {
        CreateCommand createCommand = new CreateCommand(INDEX_FIRST_COMPANY, ALICE);

        // same values -> returns true
        CreateCommand createCommandCopy = new CreateCommand(INDEX_FIRST_COMPANY, ALICE);
        assertTrue(createCommand.equals(createCommandCopy));

        // same object -> returns true
        assertTrue(createCommand.equals(createCommand));

        // null -> returns false
        assertFalse(createCommand.equals(null));

        // different types -> returns false
        assertFalse(createCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(createCommand.equals(new CreateCommand(INDEX_SECOND_COMPANY, ALICE)));

        // different Poc -> returns false
        assertFalse(createCommand.equals(new CreateCommand(INDEX_FIRST_COMPANY, BOB)));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        private Company company;
        private final UniqueCompanyList coys = new UniqueCompanyList();

        public ModelStub(Company company) {
            this.company = company;
            coys.add(company);
        }

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
        public Path getJeeqTrackerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJeeqTrackerFilePath(Path jeeqTrackerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJeeqTracker(ReadOnlyJeeqTracker newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyJeeqTracker getJeeqTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompany(Company target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompany(Company target, Company editedCompany) {

        }

        @Override
        public ObservableList<Company> getFilteredCompanyList() {
            return coys.asUnmodifiableObservableList();
        }

        @Override
        public void updateFilteredCompanyList(Predicate<Company> predicate) {

        }
    }
}
