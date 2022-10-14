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
import seedu.address.model.client.Client;
import seedu.address.model.client.UniqueClientList;
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
                model.getFilteredClientList().size() + 10), SELL_PAPAYA).execute(model));

        Client validClient = new CompanyBuilder().build();
        Model modelStub = new ModelStub(validClient);

        assertThrows(CommandException.class, () -> new SellCommand(Index.fromZeroBased(
                modelStub.getFilteredClientList().size() + 10), SELL_PAPAYA).execute(model));
    }

    @Test
    public void execute_addTransaction_success() throws Exception {
        Client validClient = new CompanyBuilder().build();
        Model modelStub = new ModelStub(validClient);
        SellCommand sellCommand = new SellCommand(INDEX_FIRST_COMPANY, SELL_PAPAYA);
        sellCommand.execute(modelStub);
        assertEquals(27.5, modelStub.getFilteredClientList().get(0).getTotalTransacted());
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
        private Client client;
        private final UniqueClientList coys = new UniqueClientList();

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
        public void addClient(Client client) {
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
        public boolean hasClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteClient(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClient(Client target, Client editedClient) {
            // method body is left empty intentionally
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            return coys.asUnmodifiableObservableList();
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            // method body is left empty intentionally
        }
    }
}
