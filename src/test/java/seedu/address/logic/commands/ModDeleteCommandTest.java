package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Test class for ModDeleteCommand.
 */
public class ModDeleteCommandTest {

    private static final Mod VALID_MOD_CS2100 = new Mod("CS2100", false);
    private static final Mod VALID_MOD_CS2101 = new Mod("CS2101", false);
    private static final Mod VALID_MOD_CS2103 = new Mod("CS2103", false);
    private static final Mod MOD_NOT_FOUND_CS2105 = new Mod("CS2105", false);
    private static Model model;

    /**
     * Sets up the model before each test.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    /**
     * Tests the behaviour of ModDeleteCommand when index is not entered.
     */
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModDeleteCommand(null, FXCollections.observableArrayList()));
    }

    /**
     * Tests the behaviour of ModDeleteCommand when mod is not entered.
     */
    @Test
    public void constructor_nullMods_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModDeleteCommand(INDEX_FIRST_PERSON, null));
    }

    /**
     * Tests the behaviour of ModDeleteCommand when the student wants to delete 1 existing mod
     * from the list of modules of a batchmate.
     *
     * @throws CommandException If an error which occurs during execution of ModDeleteCommand.
     */
    @Test
    public void execute_deleteOneMod_success() throws CommandException {

        Person batchmate = new PersonBuilder(BOB)
                .withMods(VALID_MOD_CS2100.getModName(), VALID_MOD_CS2101.getModName())
                .build();
        model.addPerson(batchmate);

        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        ModDeleteCommand commandToExecute = new ModDeleteCommand(indexLastPerson,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        CommandResult commandResult = commandToExecute.execute(model);

        String actualModList = batchmate.getMods().toString();
        String expectedModList = "[[CS2101: false]]";

        assertEquals(actualModList, expectedModList);
        assertEquals(ModDeleteCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    /**
     * Tests the behaviour of ModDeleteCommand when the student wants to delete 2 existing mods
     * from the list of modules of a batchmate.
     *
     * @throws CommandException If an error which occurs during execution of ModDeleteCommand.
     */
    @Test
    public void execute_deleteMultipleMods_success() throws CommandException {

        Person batchmate = new PersonBuilder(BOB).withMods(
                VALID_MOD_CS2100.getModName(),
                VALID_MOD_CS2103.getModName(),
                VALID_MOD_CS2101.getModName())
                .build();
        model.addPerson(batchmate);

        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        ModDeleteCommand commandToExecute = new ModDeleteCommand(indexLastPerson,
                FXCollections.observableArrayList(VALID_MOD_CS2101, VALID_MOD_CS2100));
        CommandResult commandResult = commandToExecute.execute(model);

        String actualModList = batchmate.getMods().toString();
        String expectedModList = "[[CS2103: false]]";

        assertEquals(actualModList, expectedModList);
        assertEquals(ModDeleteCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    /**
     * Tests the behaviour of ModDeleteCommand when the student wants to delete 1 non-existing mod
     * from the list of modules of a batchmate.
     */
    @Test
    public void execute_delete1NonExistingMod_throwsCommandException() {

        Person batchmate = new PersonBuilder(BOB).withMods(
                        VALID_MOD_CS2100.getModName(),
                        VALID_MOD_CS2103.getModName(),
                        VALID_MOD_CS2101.getModName())
                .build();
        model.addPerson(batchmate);

        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());

        try {
            ModDeleteCommand commandToExecute = new ModDeleteCommand(indexLastPerson,
                    FXCollections.observableArrayList(MOD_NOT_FOUND_CS2105));
            commandToExecute.execute(model);
            fail(); // Test should not reach this line.
        } catch (CommandException e) {
            assertEquals(ModDeleteCommand.MESSAGE_INVALID_MOD, e.getMessage());
        }

    }

    /**
     * Tests the behaviour of ModDeleteCommand when the student wants to delete multiple mods containing
     * a mix of existing and non-existing mods from the list of modules of a batchmate.
     */
    @Test
    public void execute_deleteMixExistingAndNonExistingMods_throwsCommandException() {

        Person batchmate = new PersonBuilder(BOB).withMods(
                        VALID_MOD_CS2100.getModName(),
                        VALID_MOD_CS2103.getModName(),
                        VALID_MOD_CS2101.getModName())
                .build();
        model.addPerson(batchmate);

        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());

        try {
            ModDeleteCommand commandToExecute = new ModDeleteCommand(indexLastPerson,
                    FXCollections.observableArrayList(VALID_MOD_CS2101, VALID_MOD_CS2103, MOD_NOT_FOUND_CS2105));
            commandToExecute.execute(model);
            fail(); // Test should not reach this line. Goes to Catch block.
        } catch (CommandException e) {
            assertEquals(ModDeleteCommand.MESSAGE_INVALID_MOD, e.getMessage());
        }

    }

    /**
     * Tests the behaviour of ModDeleteCommand when index is out of range.
     *
     * @throws CommandException If an error which occurs during execution of ModDeleteCommand.
     */
    @Test
    public void execute_indexOutOfBounds_throwsCommandException() throws CommandException {
        Index indexOutOfBounds = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ModDeleteCommand invalidCommand = new ModDeleteCommand(indexOutOfBounds,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        assertCommandFailure(invalidCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
