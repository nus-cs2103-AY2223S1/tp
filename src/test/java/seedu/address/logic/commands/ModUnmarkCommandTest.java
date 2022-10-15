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
 * Test class for ModUnmarkCommand.
 */
public class ModUnmarkCommandTest {

    private static final Mod VALID_MOD_CS2100 = new Mod("CS2100", true);
    private static final Mod VALID_MOD_CS2101 = new Mod("CS2101", true);
    private static final Mod VALID_MOD_CS2103 = new Mod("CS2103", true);
    private static final Mod MOD_NOT_FOUND_CS2105 = new Mod("CS2105", true);
    private static Model model;

    /**
     * Sets up the model before each test.
     */
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    /**
     * Tests the behaviour of ModUnmarkCommand when index is not entered.
     */
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModUnmarkCommand(null, FXCollections.observableArrayList()));
    }

    /**
     * Tests the behaviour of ModUnmarkCommand when mod is not entered.
     */
    @Test
    public void constructor_nullMods_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModUnmarkCommand(INDEX_FIRST_PERSON, null));
    }

    /**
     * Tests the behaviour of ModUnmarkCommand when the student wants to unmark 1 existing mod
     * in the list of taken modules of a batchmate.
     *
     * @throws CommandException If an error which occurs during execution of ModUnmarkCommand.
     */
    @Test
    public void execute_unmarkOneMod_success() throws CommandException {

        Person batchmate = new PersonBuilder(BOB).withMods(VALID_MOD_CS2100.modName).build();
        model.addPerson(batchmate);

        batchmate.getMods().get(0).markMod();

        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        ModUnmarkCommand commandToExecute = new ModUnmarkCommand(indexLastPerson,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        CommandResult commandResult = commandToExecute.execute(model);

        String actualModStatus = batchmate.getMods().get(0).toString();
        String expectedModStatus = "[CS2100: false]";

        assertEquals(expectedModStatus, actualModStatus);
        assertEquals(ModUnmarkCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    /**
     * Tests the behaviour of ModUnmarkCommand when the student wants to unmark 2 existing mods
     * in the list of taken modules of a batchmate.
     *
     * @throws CommandException If an error which occurs during execution of ModUnmarkCommand.
     */
    @Test
    public void execute_unmarkMultipleMod_success() throws CommandException {

        Person batchmate = new PersonBuilder(BOB).withMods(
                        VALID_MOD_CS2100.modName,
                        VALID_MOD_CS2103.modName,
                        VALID_MOD_CS2101.modName)
                .build();
        model.addPerson(batchmate);

        batchmate.getMods().get(0).markMod();
        batchmate.getMods().get(1).markMod();
        batchmate.getMods().get(2).markMod();

        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        ModUnmarkCommand commandToExecute = new ModUnmarkCommand(indexLastPerson,
                FXCollections.observableArrayList(VALID_MOD_CS2101, VALID_MOD_CS2100));
        CommandResult commandResult = commandToExecute.execute(model);

        String actualFirstModStatus = batchmate.getMods().get(0).toString();
        String actualSecondModStatus = batchmate.getMods().get(1).toString();
        String actualThirdModStatus = batchmate.getMods().get(2).toString();
        String actualModStatus = actualFirstModStatus + actualSecondModStatus + actualThirdModStatus;

        String expectedModStatus = "[CS2100: false]" + "[CS2103: true]" + "[CS2101: false]";

        assertEquals(expectedModStatus, actualModStatus);
        assertEquals(ModUnmarkCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
    }

    /**
     * Tests the behaviour of ModUnmarkCommand when the student wants to unmark 1 non-existing mod
     * in the list of taken modules of a batchmate.
     */
    @Test
    public void execute_unmarkNonExistingMod1_throwsCommandException() {

        Person batchmate = new PersonBuilder(BOB).withMods(
                        VALID_MOD_CS2100.modName,
                        VALID_MOD_CS2103.modName,
                        VALID_MOD_CS2101.modName)
                .build();
        model.addPerson(batchmate);

        batchmate.getMods().get(0).markMod();
        batchmate.getMods().get(1).markMod();
        batchmate.getMods().get(2).markMod();

        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());

        try {
            ModUnmarkCommand commandToExecute = new ModUnmarkCommand(indexLastPerson,
                    FXCollections.observableArrayList(MOD_NOT_FOUND_CS2105));
            commandToExecute.execute(model);
            fail(); // Test should not reach this line.
        } catch (CommandException e) {
            assertEquals(ModUnmarkCommand.MESSAGE_INVALID_MOD, e.getMessage());
        }

    }

    /**
     * Tests the behaviour of ModUnmarkCommand when the student wants to unmark multiple mods containing
     * 1 non-existing mod in the list of taken modules of a batchmate.
     */
    @Test
    public void execute_unmarkNonExistingMod2_throwsCommandException() {

        Person batchmate = new PersonBuilder(BOB).withMods(
                        VALID_MOD_CS2100.modName,
                        VALID_MOD_CS2103.modName,
                        VALID_MOD_CS2101.modName)
                .build();
        model.addPerson(batchmate);

        batchmate.getMods().get(0).markMod();
        batchmate.getMods().get(1).markMod();
        batchmate.getMods().get(2).markMod();

        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());

        try {
            ModUnmarkCommand commandToExecute = new ModUnmarkCommand(indexLastPerson,
                    FXCollections.observableArrayList(VALID_MOD_CS2101, VALID_MOD_CS2103, MOD_NOT_FOUND_CS2105));
            commandToExecute.execute(model);
            fail(); // Test should not reach this line.
        } catch (CommandException e) {
            assertEquals(ModUnmarkCommand.MESSAGE_INVALID_MOD, e.getMessage());
        }

    }

    /**
     * Tests the behaviour of ModUnmarkCommand when index is out of range.
     *
     * @throws CommandException If an error which occurs during execution of ModUnmarkCommand.
     */
    @Test
    public void execute_indexOutOfBounds_throwsCommandException() throws CommandException {
        Index indexOutOfBounds = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ModUnmarkCommand invalidCommand = new ModUnmarkCommand(indexOutOfBounds,
                FXCollections.singletonObservableList(VALID_MOD_CS2100));
        assertCommandFailure(invalidCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

}
