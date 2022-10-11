package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.getTypicalJeeqTracker;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static seedu.address.testutil.TypicalTransaction.SELL_PAPAYA;
import static seedu.address.testutil.TypicalTransaction.SELL_SOCKS;

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
import seedu.address.model.company.Company;
import seedu.address.model.company.UniqueCompanyList;
import seedu.address.testutil.CompanyBuilder;



class SellCommandTest {

    private final Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());

    @Test
    public void constructor_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SellCommand(INDEX_FIRST_COMPANY, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SellCommand(null, SELL_PAPAYA));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SellCommand(INDEX_FIRST_COMPANY, SELL_PAPAYA)
                .execute(null));
    }

    @Test
    public void execute_invalidCompanyIndex_failure() {
        assertThrows(CommandException.class, () -> new SellCommand(Index.fromZeroBased(
                model.getFilteredCompanyList().size() + 10), SELL_PAPAYA).execute(model));

        Company validCompany = new CompanyBuilder().build();
        Model modelStub = new ModelStub(validCompany);

        assertThrows(CommandException.class, () -> new SellCommand(Index.fromZeroBased(
                modelStub.getFilteredCompanyList().size() + 10), SELL_PAPAYA).execute(model));
    }

    @Test
    public void execute_addTransaction_success() throws Exception {
        Company validCompany = new CompanyBuilder().build();
        Model modelStub = new ModelStub(validCompany);
        SellCommand sellCommand = new SellCommand(INDEX_FIRST_COMPANY, SELL_PAPAYA);
        sellCommand.execute(modelStub);
        assertEquals(27.5, modelStub.getFilteredCompanyList().get(0).getTotalTransacted());
    }


    @Test
    public void equals() {
        SellCommand sellCommand = new SellCommand(INDEX_FIRST_COMPANY, SELL_PAPAYA);

        // same values -> returns true
        SellCommand sellCommandCopy = new SellCommand(INDEX_FIRST_COMPANY, SELL_PAPAYA);
        assertTrue(sellCommand.equals(sellCommandCopy));

        // same object -> returns true
        assertTrue(sellCommand.equals(sellCommand));

        // null -> returns false
        assertFalse(sellCommand.equals(null));

        // different types -> returns false
        assertFalse(sellCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(sellCommand.equals(new SellCommand(INDEX_SECOND_COMPANY, SELL_PAPAYA)));

        // different Transaction -> returns false
        assertFalse(sellCommand.equals(new SellCommand(INDEX_FIRST_COMPANY, SELL_SOCKS)));
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
