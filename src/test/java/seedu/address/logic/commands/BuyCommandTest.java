package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.getTypicalJeeqTracker;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static seedu.address.testutil.TypicalTransaction.BUY_ORANGE;
import static seedu.address.testutil.TypicalTransaction.BUY_TOYS;

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
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueCompanyList;
import seedu.address.testutil.CompanyBuilder;

class BuyCommandTest {

    private final Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());

    @Test
    public void constructor_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BuyCommand(INDEX_FIRST_COMPANY, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BuyCommand(null, BUY_ORANGE));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BuyCommand(INDEX_FIRST_COMPANY, BUY_ORANGE)
                .execute(null));
    }

    @Test
    public void execute_invalidCompanyIndex_failure() {
        assertThrows(CommandException.class, () -> new BuyCommand(Index.fromZeroBased(
                model.getFilteredCompanyList().size() + 10), BUY_ORANGE).execute(model));

        Client validClient = new CompanyBuilder().build();
        Model modelStub = new ModelStub(validClient);

        assertThrows(CommandException.class, () -> new BuyCommand(Index.fromZeroBased(
                modelStub.getFilteredCompanyList().size() + 10), BUY_ORANGE).execute(model));
    }

    @Test
    public void execute_addTransaction_success() throws Exception {
        Client validClient = new CompanyBuilder().build();
        Model modelStub = new ModelStub(validClient);
        BuyCommand buyCommand = new BuyCommand(INDEX_FIRST_COMPANY, BUY_ORANGE);
        buyCommand.execute(modelStub);
        assertEquals(-10101.00, modelStub.getFilteredCompanyList().get(0).getTotalTransacted());
    }

    @Test
    public void equals() {
        BuyCommand buyCommand = new BuyCommand(INDEX_FIRST_COMPANY, BUY_ORANGE);

        // same values -> returns true
        BuyCommand buyCommandCopy = new BuyCommand(INDEX_FIRST_COMPANY, BUY_ORANGE);
        assertTrue(buyCommand.equals(buyCommandCopy));

        // same object -> returns true
        assertTrue(buyCommand.equals(buyCommand));

        // null -> returns false
        assertFalse(buyCommand.equals(null));

        // different types -> returns false
        assertFalse(buyCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(buyCommand.equals(new BuyCommand(INDEX_SECOND_COMPANY, BUY_ORANGE)));

        // different Transaction -> returns false
        assertFalse(buyCommand.equals(new BuyCommand(INDEX_FIRST_COMPANY, BUY_TOYS)));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        private Client client;
        private final UniqueCompanyList coys = new UniqueCompanyList();

        public ModelStub(Client client) {
            this.client = client;
            coys.add(client);
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
        public void addCompany(Client client) {
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
        public boolean hasCompany(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompany(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompany(Client target, Client editedClient) {
            // method body is left empty intentionally
        }

        @Override
        public ObservableList<Client> getFilteredCompanyList() {
            return coys.asUnmodifiableObservableList();
        }

        @Override
        public void updateFilteredCompanyList(Predicate<Client> predicate) {
            // method body is left empty intentionally
        }
    }
}
